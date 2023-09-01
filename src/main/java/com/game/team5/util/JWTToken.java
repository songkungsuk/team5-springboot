package com.game.team5.util;

import java.security.Key;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.game.team5.vo.UserInfoVO;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JWTToken {
	
	
	private final String JWT_TOKEN_KEY;
	private final long JWT_TOKEN_EXPRIE;
	
	public JWTToken(@Value("${jwt.token.key}") String jwtTokenKey , @Value("${jwt.token.expire}") long jwtTokenExpire) {
		this.JWT_TOKEN_KEY = jwtTokenKey;
		this.JWT_TOKEN_EXPRIE = jwtTokenExpire;
	}
	
	public String getToken() {
		return JWT_TOKEN_KEY;
	}
	
	public long getExpire() {
		return JWT_TOKEN_EXPRIE;
	}
	
	public String getJwtToken(String uiId) {
		Date date = new Date();
		long expireDate = date.getTime() + JWT_TOKEN_EXPRIE;
		
		byte[] keyBytes = DatatypeConverter.parseBase64Binary(JWT_TOKEN_KEY);
		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
		log.info("key =>{}"+key);
		String token = Jwts.builder()
		.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
		.setSubject(uiId)
		.setIssuedAt(date)
		.setExpiration(new Date(expireDate))
		.signWith(SignatureAlgorithm.HS256, keyBytes)
		.compact();
		
		return token;
	}
	public UserInfoVO validToken(String token) {
		Key key = Keys.hmacShaKeyFor(JWT_TOKEN_KEY.getBytes());
		try {
			String userId =  Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(token)
					.getBody()
					.getSubject();
			
					log.info("userId=>"+userId);
					
		} catch (ExpiredJwtException e) {
			log.error("expired");
		} catch (SignatureException e) {
			log.error("invalid signature");
		}
		
		
		return null;
	}
}

package com.game.team5;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenTest {
	private final static Long EXPIRED_TIME = 10000L;
	
	public static void main(String args[]) {
		
		Date date = new Date();
		Date expiredDate = new Date(date.getTime() + EXPIRED_TIME);
		log.info("ExpiredDate=>{}", expiredDate);
		
		String keyStr = "2132132132132132132132132213212313132123123"; //key 32자리
		Key key = Keys.hmacShaKeyFor(keyStr.getBytes());
		
		String token = Jwts.builder()
					.setHeaderParam(Header.TYPE, Header.JWT_TYPE) //토큰타입헤더
					.setSubject("id") // 토큰종류
					.setIssuedAt(date) // 토근생성일
					.setExpiration(expiredDate) //토큰만료일
					.signWith(key, SignatureAlgorithm.HS256) // 일련번호 같은느낌
					.compact(); // 토큰생성
	 
		log.info("token =>"+ token); //토큰내용
		
		try {
			Thread.sleep(200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String subject = Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(token)
					.getBody().getSubject();
		log.info("subject =>", subject);
		
	}
}

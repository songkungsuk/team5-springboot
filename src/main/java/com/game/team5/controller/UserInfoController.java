package com.game.team5.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.game.team5.service.UserInfoService;
import com.game.team5.util.JWTToken;
import com.game.team5.vo.UserInfoVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin("*")
public class UserInfoController {
	//@componentscan이 com.game.team5에있는 모든것들의 @RestController @Controller @Service @Reoisitory 이런것들을 전부 찾아서 메모리 생성해줌
	@Autowired //메모리를 자동으로 생성해준다
	private UserInfoService uiService;
	@Autowired
	private JWTToken jwtToken;
	
	@GetMapping("/expire")
	public Long getExpire() {
		return jwtToken.getExpire();
	}
	
	@GetMapping("/user-infos/**")
	public List<UserInfoVO> getUserInfos(HttpServletRequest request, UserInfoVO user){
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		jwtToken.getJwtToken(token);
		jwtToken.validToken(token);
		return uiService.getUserInfos(user);
	}
	
	@GetMapping("/user-infos/{uiNum}")
	public UserInfoVO getUserInfo(@PathVariable int uiNum) {
		return uiService.getUserInfo(uiNum);
	}
	
	@PostMapping("/user-infos")
	public int insertUserInfo(@RequestBody UserInfoVO user) {
		log.info("insert user =>"+user);
		return uiService.insertUserInfo(user);
	}
	
	@PutMapping("/user-infos")//원래는 /{uiNum} 으로 PathVariable 도같이 넣어줘야한다
	public int updateUserInfo(@RequestBody UserInfoVO user) {
		log.info("update user =>"+user);
		return uiService.updateUserInfo(user);
	}
	
	@DeleteMapping("/user-infos/{uiNum}")
	public int deleteUserInfo(@PathVariable int uiNum) {
		log.info("delete user =>"+ uiNum);
		return uiService.deleteUserInfo(uiNum);
	}
	
	@PostMapping("/user-infos/login")
	public UserInfoVO login(@RequestBody UserInfoVO user){
		return uiService.login(user);
	}
}

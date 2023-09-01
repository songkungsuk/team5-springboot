package com.game.team5.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin("*")
public class TestController {
	
	@RequestMapping(value = "/test/**", method=RequestMethod.GET)
	public String test() {
		return "str";
	}
	
	@RequestMapping(value = "api/list", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getList(){
		List<String> list = new ArrayList<>();
		list.add("so");
		list.add("ng");
		list.add("ky");
		
		return list;
	}
	
	@GetMapping("/api/users")
	@ResponseBody
	public List<Map<String, String>> getUser(){
		List<Map<String, String>> users = new ArrayList<Map<String,String>>();
		for(int i=1; i<11; i++) {
			Map<String, String> user = new HashMap<String, String>();
			user.put("name", "이름"+i);
			user.put("num", ""+i);
			user.put("age", ""+i);
			users.add(user);
		}
		return users;
	}
	
}

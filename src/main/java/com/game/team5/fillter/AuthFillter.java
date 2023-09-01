package com.game.team5.fillter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.GenericFilterBean;

import com.game.team5.util.JWTToken;
import com.game.team5.vo.UserInfoVO;

import lombok.extern.slf4j.Slf4j;

@WebFilter(value = {"/", "/views/*", "/api/*"}) //resources 폴더에는 접근가능 설정한 uri패턴아니면 필터가 작동안함
@Slf4j
public class AuthFillter extends GenericFilterBean{

	@Autowired
	JWTToken jwtToken;
	
	private List<String> execludeUrls = new ArrayList<>();
	{
		execludeUrls.add("/views/login");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if(request instanceof HttpServletRequest req 
				&& response instanceof HttpServletResponse res) {
			HttpSession session = req.getSession();
			UserInfoVO user = (UserInfoVO) session.getAttribute("user");
			log.info("user=>{}", user);
			String uri = req.getRequestURI();
			if(!execludeUrls.contains(uri)) {
				if(user == null) {
					res.sendRedirect("/views/login");
					return;
				}
			}
			
		}
		chain.doFilter(request, response);
			
		
	}
	
}

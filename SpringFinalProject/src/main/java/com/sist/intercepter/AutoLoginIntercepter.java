package com.sist.intercepter;
// <bean> 이용

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutoLoginIntercepter extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("preHandel Call...");
		Cookie[] cookies=request.getCookies();
		if(cookies!=null) {
			for(int i=0;i<cookies.length;i++) {
				String key=cookies[i].getName();
				if(key.equals("id")) {
					String id=cookies[i].getValue();
					request.setAttribute("id", id);
					break;
				}
			}
		}
		return super.preHandle(request, response, handler);
	}
	
}

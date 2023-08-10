package com.sist.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// extends / implements
/*
	intercepter 동작 과정
	
	.do (요청) => DispatcherServlet => (Mapping을 찾기 전에) 인터셉트(preHandle) =>  
														-------------------
														로그인으로 넘어가기 전에, 아이디 쿠키가 존재한다면,
														로그인된 상태로 넘어가도록 설정
	=> HandlerMapping(@Controller / @RestController => @GetMapping, @ PostMapping)
	
	=> 인터셉트 postHandle (viewResolver로 넘어가기 전에 다른 데이터 첨부 가능) 
	
	=> ViewResolver 작동 (화면 이동) 리턴값을 받아서 화면 변경
	
	=> 인터셉트 afterCompletion (메뉴 권한 부여에서 사용)
	
	=> JSP화면 출력
	
	
	Spring 
		= Setting : AOP / DI (클래스 등록)
		= ORM : MyBatis or JPA
		 		1) XML
		 		2) Annotation
		 		3) XML + Annotation (****)
		 		 	why? Annotation으로 SQL문장 작성시, 복잡해지기 때문에 
		= Transaction
		------------------- Basic
		= WebSocket
		= Security
		= Task
		------------------- Option
		= MVC
		
	Front (HTML5, CSS, JavaScript)
					   ----------
					   		|
			Jquery(Ajax) , VueJS , ReactJS
				+ NodeJS , AngularJS , TypeScript
	
		Spring-Cloud + MSA
	
*/
public class AutoInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("preHandel Call...");
		
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandel Call...");
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion Call...");
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterConcurrentHandlingStarted(request, response, handler);
	}
	
}

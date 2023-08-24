package com.sist.aop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import com.sist.dao.AspectDAO;
import com.sist.vo.FoodVO;

@Aspect
@Component
public class FooterAspect {
	@Autowired
	private AspectDAO dao;
	
	@After("execution(* com.sist.web.*Controller.*(..))")
	public void footerData() {
		// 매개변수에 값을 채워준다 => DispatcherServlet
		// => @Controller , @RestController 에서만 가능
		HttpServletRequest request=
				((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		List<FoodVO> fList=dao.foodTop7Data();
		List<String> sList=dao.seoulTop7Data();
		List<String> rList=dao.recipeTop7Data();
		
		request.setAttribute("fList", fList);
		request.setAttribute("sList", sList);
		request.setAttribute("rList", rList);
	}
}

package com.sist.aop;
/*
	aspect : AOP는 OOP를 대체하는 프로그램이 아니다.
				OOP를 보완
				--- 콜백함수(시스템에 의해 자동으로 호출)가 존재하지 않음
							ex) OOP: getConnection, disConnection을 직접 작성한 후, 직접 호출해야함
								콜백함수: main, service ... 
					=> 한 개 혹은 두 개의 공통으로 사용되는 내용을 메소드로 처리
						getConnection() , disConnection()
					=> 여러 개가 공통으로 사용되는 내용 => 클래스화
						CreateDataBase
					-------------------------------------------------- 공통모듈
					스프링에서는 반복적인 코딩을 모아서 관리 => 필요시에 자동으로 호출 
					================================================== AOP
					*** 사용자 정의는 많이 사용하지 않는다.
		AOP (Aspect-Oriented Programming)
			=> 핵심 비즈니스 로직을 담고 있지는 않지만 어플리케이션에 부가됨으로써 의미를 갖는 특별한 모듈
		---
		 1) 어떤 메소드에 적용되는지 설정
		 	pointcut="execution(* com.sist.main. * .   *    (..))"
		 					  리턴형			  클래스명 메소드명 매개변수
		 	pointcut="within("com.sist.main.*")"
		 				패키지 단위로 등록
		 				
		 2) 호출되는 위치 설정 : JoinPoint
		 		public String dislay(){
		 			@Before : getConnection()								1
		 			try{
		 				---------------	@Around : setAutoCommit(false)		2
		 					핵심 코딩
		 				--------------- @Around : Commit()					2
		 			}catch(Exception e){
		 				@After-Throwing :오류처리 rollback()					3
		 			}finally{
		 				@After : setAutoCommit(true) , disConnection()		4
		 			}
		 			return "" @After-Returning								5
		 		}
		 	
		 	JoinPoint + Pointcut => Advice => Asject (Advice여러개 모음) 
		 	Weaving : 통합 => Proxy패턴 (대리자)
		 			before, after 등 메소드까지 한 번에 합치기
		 		ex) 트랜잭션
		 			@Transaction
		 			
		 	=> Spring에서 처리하므로, 개발자가 해야할 일은 적용될 메소드와 호출될 위치를 정해주는 것.
		 	
		 	단, MyBatis를 사용하므로 getConnection과 disConnection 등의 AOP의 사용빈도가 적다.
		 	
		 	public class Proxy
		 	{
		 		private A a;
		 		public Proxy(A a)
		 		{
		 			this.a=a;
		 		}
		 		public void display()
		 		{
		 			@Before => 설정된 메소드 호출
		 			a.display()
		 			@After  => 설정된 메소드 호출
		 		}
		 	}
		 								
		 	
*/
import java.util.*;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sist.dao.*;

@Aspect			// 공통 모듈임을 알려주는 어노테이션
@Component
public class EmpAspect {
	@Autowired
	private EmpDAO dao;
	
	@Before("execution(* com.sist.dao.EmpDAO.emp*(..))")
	// try 시작과 동시에 처리
	public void getConnection() {
		System.out.println("오라클 연결");
		dao.getConnection();
	}
	
	@After("execution(* com.sist.dao.EmpDAO.emp*(..))")
	// finally에서 호출
	public void disConnection() {
		System.out.println("오라클 해제");
		dao.disConnection();
	}
	
	@AfterReturning(value = "execution(* com.sist.dao.EmpDAO.emp*(..))", returning = "obj")
	public void print(Object obj) {
		List<EmpVO> list=(List<EmpVO>)obj;
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+". "+vo.getEname()+"|"+vo.getJob()+"|"+vo.getDbday()
								+" "+vo.getSal());
		}
	}
	// 매개변수의 이름과 throwing="" 에 들어가는 이름이 같아야한다.
	@AfterThrowing(value = "execution(* com.sist.dao.EmpDAO.emp*(..))", throwing = "ex")
	public void exception(Throwable ex){
		System.out.println("에러발생!");
		ex.printStackTrace();
	}
	
}

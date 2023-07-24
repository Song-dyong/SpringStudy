package com.sist.main4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*	
	컨테이너 (스프링이 주로 하는 역할)
		=> 개발자가 만드는 클래스 ( 한 개의 기능 수행 :컴포넌트)
		=> 컴포넌트 여러 개를 관리하는 역할 (컨테이너)
			JButton , JTextField .. (component)
			----------------------- JFrame (컨테이너)
		
		컨테이너의 역할
			1) 객체(컴포넌트) 생성	=> (new를 사용하는 경우: VO why? 사용자 정의 데이터형이므로..)
									컴포넌트: DAO, Model.. / VO : 데이터형
			2) 객체의 변수 초기화 	=> DI(의존성 주입) 
			3) 객체 소멸시키는 역할
			------------------ 객체의 생명주기 관리
			=> 핵심 기능에만 집중해서 개발
			
		
		스프링에서 지원하는 컨테이너
		---------------------
			BeanFactory	===> core (DI)
				|
			ApplicationContext (Application) => core(DI), AOP
				| -- AnnotationConfigApplicationContext
				|	------------------------------------
				|		Spring5의 핵심 (XML을 사용하지 않고, 순수 자바로 코딩)
		WebApplicationContext (MVC)	=> core(DI), AOP, MVC
			

*/
public class MainClass {
	public static void main(String[] args) {

		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		
		// ClassPathXmlApplicationContext : xml 파싱 (xml에 등록된 클래스 읽기)
		// ClassPath : src/main/java
		
		A a=(A)app.getBean("a");
		a.display();

	}
}

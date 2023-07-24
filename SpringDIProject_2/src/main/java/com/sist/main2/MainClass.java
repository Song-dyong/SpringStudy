package com.sist.main2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
//		String[] xml= {"member.xml","sawon.xml","student.xml"};
		ApplicationContext app=new ClassPathXmlApplicationContext("app-*.xml");
		
		Sawon s=(Sawon)app.getBean("sa");
		// s.init(); => 스프링 자체에서 처리 
		// but! 동시에 3개의 init이 실행될 경우, 
		// 클래스 메모리 할당과 동시에 init 메소드를 호출하기 때문에
		// init 메소드 3개가 처음에 동시에 호출되면서 순서가 어긋남.
		// m.init으로 개별 메소드 호출 필요
		s.init();
		s.print();
		
		Member m=(Member)app.getBean("mem");
		m.init();
		m.print();
		
		Student std=app.getBean("std",Student.class);
		std.init();
		std.print();
	}
}

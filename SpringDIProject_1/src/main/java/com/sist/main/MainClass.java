package com.sist.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		// XML 파싱 => 컨테이너에 등록
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		Sawon s1=(Sawon)app.getBean("sa1");
		System.out.print(s1.getSabun());
		System.out.print(s1.getName());
		System.out.print(s1.getDept());
		System.out.print(s1.getJob());
		System.out.println(s1.getPay());
		
//		Sawon s2=(Sawon)app.getBean("sa2");
		// 제네릭스를 활용한 형변환
		Sawon s2=app.getBean("sa1",Sawon.class);
		s2.setSabun(2);
		s2.setName("Shim");
		s2.setDept("Develop");
		s2.setJob("Pre");
		s2.setPay(4000);
		System.out.print(s1.getSabun());
		System.out.print(s1.getName());
		System.out.print(s1.getDept());
		System.out.print(s1.getJob());
		System.out.println(s1.getPay());
		// SingleTon이므로, 객체명이 다르더라도, 같은 객체를 사용하고 있음.
		// id가 sa1인 객체.
		System.out.print(s2.getSabun());
		System.out.print(s2.getName());
		System.out.print(s2.getDept());
		System.out.print(s2.getJob());
		System.out.println(s2.getPay());
		
		System.out.println("s1:"+s1);
		System.out.println("s2:"+s2);
		
		System.out.println("-------------------------");
		Sawon s3=(Sawon)app.getBean("sa3");
		System.out.print(s3.getSabun());
		System.out.print(s3.getName());
		System.out.print(s3.getDept());
		System.out.print(s3.getJob());
		System.out.println(s3.getPay());
		
	}
}

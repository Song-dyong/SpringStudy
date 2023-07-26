package com.sist.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import com.sist.dao.*;
@Component("mc")
public class MainClass {
	@Autowired // 자동 주입 역할 => 스프링에서 자동으로 객체 주소값을 찾아서 주입
	@Qualifier("bdao")	// bdao, fdao라는 2가지 클래스가 Board를 상속받았으므로, 둘 중 하나를 선택해야함
	private Board board;	// 해당 객체를 지정 => 다중 상속을 내린 클래스의 충돌을 방지
	
	@Autowired					// 메모리 할당할 때마다 어노테이션 올리기
	private FreeBoardDAO f;
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		
	}
}

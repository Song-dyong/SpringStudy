package com.sist.main;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.Setter;
public class MainClass {
	@Setter
	private GoodsDAO dao;
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		//MainClass mc=new MainClass();
		// new 를 통해 생성하면 Spring에 설정된 값들이 주입되지 않은 null값을 가진 클래스가 생성됨
		// 데이터를 받아오기 위해서 오라클과 연결된 dao를 가져와야 하므로, app.getBean("id")를 사용　必
		// 느슨한 결합 (loosely Couple Ring) => 에러가 발생할 경우, 하나의 클래스 안에서 찾아야하므로.
		
		MainClass mc=app.getBean("mc",MainClass.class);
		List<String> list = mc.dao.goodsNameList();
		
		for(String name:list) {
			System.out.println(name);
		}
	}
}

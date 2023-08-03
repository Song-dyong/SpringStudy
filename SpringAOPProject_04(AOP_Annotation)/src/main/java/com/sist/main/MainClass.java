package com.sist.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.dao.EmpDAO;

@Component
public class MainClass {
	@Autowired
	private EmpDAO dao;
	
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		MainClass mc=(MainClass)app.getBean("mainClass");
		mc.dao.empListData();	// AOP 안에서 출력을 해뒀기 때문에, List<EmpVO>로 받아서 출력하지 않아도 됨.
								// Returning에서 출력하므로, 연결 해제한 뒤 값출력이 나중에 나옴
	}
}

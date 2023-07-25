package com.sist.main;
import java.util.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.dao.*;
import com.sist.vo.*;

// <bean id="mainClass" class="com.sist.main.MainClass"/>
// id가 없는 경우, 클래스명이 id로 설정된다. 단! 첫번째 글자는 소문자로 변환
// EmpDAO => empDAO 
// id를 직접 설정할 경우, ("직접 설정할 아이디")
@Component("mc")
public class MainClass {
	@Autowired
	private EmpDAO dao;
	@Test
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		MainClass mc=(MainClass)app.getBean("mc");
		List<EmpVO> list=mc.dao.empListData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+". "+vo.getEname()+" "+vo.getJob()
							+" "+vo.getDbday()+vo.getDbsal());
		}
		System.out.println("=====================");
		Scanner scan=new Scanner(System.in);
		System.out.println("사번 입력!");
		int empno=scan.nextInt();
		EmpVO vo=mc.dao.empDetailData(empno);
		System.out.println("==============사원 정보=============");
		System.out.println(vo.getEmpno()+". "+vo.getEname());
		System.out.println(vo.getDbday()+" "+vo.getDbsal());
		System.out.println(vo.getJob()+" "+vo.getDeptno());
		// 검색
		System.out.println("검색어 입력");
		String fd=scan.next();
		list=mc.dao.empSearchData(fd);
		for(EmpVO fvo:list) {
			System.out.println(fvo.getEmpno()+". "+fvo.getEname()+" "+fvo.getJob()
							+" "+fvo.getDbday()+fvo.getDbsal());
		}
	}
	
}

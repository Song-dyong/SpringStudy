package com.sist.main;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.vo.*;
import com.sist.dao.*;
public class MainClass {
	public static void main(String[] args) {
		// 파싱 => 등록된 클래스를 스프링 컨테이너로 전송
		ApplicationContext app = new ClassPathXmlApplicationContext("app.xml");
		// DL : 클래스 찾아주는 역할 getBean() / @AutoWired
		EmpDAO dao=(EmpDAO)app.getBean("dao");
		List<EmpVO> list=dao.empdeptListData();
		for(EmpVO vo:list) {
			System.out.println(
				vo.getEmpno()+" "
				+vo.getEname()+" "
				+vo.getJob()+" "
				+vo.getDbday()+" "
				+vo.getSal()+" "
				+vo.getDvo().getDname()+" "
				+vo.getDvo().getLoc()
			);
		}
		System.out.println("============================");
		Scanner scan=new Scanner(System.in);
		System.out.println("사번 입력!");
		int empno=scan.nextInt();
		EmpVO vo=dao.empdeptDetailData(empno);
		System.out.println(vo.getEmpno());
		System.out.println(vo.getEname());
		System.out.println(vo.getJob());
		System.out.println(vo.getDbday());
		System.out.println(vo.getSal());
		System.out.println(vo.getDvo().getDeptno());
		System.out.println(vo.getDvo().getLoc());
	}
}

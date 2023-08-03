package com.sist.main;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;
public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		EmpDAO dao=(EmpDAO)app.getBean("dao");
		List<EmpVO> list=dao.empListData();
		//dao.empDetailData(7788);
		dao.empFindData("A");
	}
//	아래의 메소드를 위에서 부르는 것.
//	public void afterReturning(Object obj) throws Throwable{
//		System.out.println("afterReturning Call... 정상수행");
//		List<EmpVO> list=(List<EmpVO>)obj;
//		for(EmpVO vo:list) {
//			System.out.println(vo.getEname()+" "
//					+vo.getEname()+" "
//					+vo.getSal()+" "
//					+vo.getJob()+" "
//					+vo.getDbday());
//		}
//	}
}

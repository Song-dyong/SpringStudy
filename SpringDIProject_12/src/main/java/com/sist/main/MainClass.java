package com.sist.main;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.dao.*;
import com.sist.config.*;
@Component("mc")
public class MainClass {
	@Autowired
	private EmpDAO dao;
	
	public static void main(String[] args) {
		// class를 넘겨서 사용 가능하도록 
		AnnotationConfigApplicationContext app=
				new AnnotationConfigApplicationContext(EmpConfig.class);
		MainClass mc=(MainClass)app.getBean("mc");
		List<EmpVO> list=mc.dao.empListData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "+vo.getEname()+" "+vo.getSal()+" "+vo.getJob());
		}
		EmpVO vo=mc.dao.empDetailData(7788);
		System.out.println(vo.getEname());
	}
}

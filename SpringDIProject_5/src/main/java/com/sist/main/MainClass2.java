package com.sist.main;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;
public class MainClass2 {
	public static void main(String[] args) {
		ApplicationContext app= new ClassPathXmlApplicationContext("app.xml");
		FoodDAO dao=(FoodDAO)app.getBean("fdao");
		List<FoodVO> list=dao.foodCategoryList();
		for(FoodVO vo:list) {
			System.out.println(vo.getCno()+". "+vo.getTitle()+"|"+vo.getSubject());
		}
	}
}

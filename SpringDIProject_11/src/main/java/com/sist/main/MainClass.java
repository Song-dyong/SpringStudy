package com.sist.main;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.dao.*;

@Component
public class MainClass {
	@Autowired
	private FoodService service;
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		Scanner scan=new Scanner(System.in);
		
		MainClass mc=(MainClass)app.getBean("mainClass");	// Default ID => 첫 글자 소문자로 변경됨
		
		List<CategoryVO> list=mc.service.categoryListData();
		for(CategoryVO vo:list) {
			System.out.println(vo.getCno()+". "+vo.getTitle()+": "+vo.getSubject());
		}
		System.out.println("=========================");
		System.out.println("카테고리 번호 선택");
		int cno=scan.nextInt();
		List<FoodVO> fList=mc.service.foodCategoryListData(cno);
		for(FoodVO vo:fList) {
			System.out.println(vo.getFno()+". "+vo.getName());
		}
		System.out.println("============================");
		System.out.println("맛집 번호 선택");
		int fno=scan.nextInt();
		FoodVO fvo=mc.service.foodDetailData(fno);
		System.out.println(fvo.getName());
		System.out.println(fvo.getAddress());
		System.out.println(fvo.getPhone());
		System.out.println(fvo.getMenu());
		System.out.println(fvo.getParking());
		System.out.println(fvo.getTime());
		System.out.println(fvo.getScore());
		System.out.println(fvo.getPrice());
		System.out.println(fvo.getType());
	}

}

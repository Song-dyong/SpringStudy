package com.sist.main;
import java.util.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;
import com.sist.vo.*;
public class MainClass {
	
	public static void main(String[] args) {
		
	}
	@Test
	public void locationMain() {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		LocationDAO dao=(LocationDAO)app.getBean("ldao");
		List<SeoulLocationVO> list=dao.locationListData();
		for(SeoulLocationVO vo:list) {
			System.out.println(vo.getNo()+". "+vo.getTitle());
		}
	}
	@Test
	public void locationDetail() {
		Scanner scan=new Scanner(System.in);
		System.out.println("number");
		int no=scan.nextInt();
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		LocationDAO dao=(LocationDAO)app.getBean("ldao");
		SeoulLocationVO vo=dao.locationDetailData(no);
		System.out.println("title: "+vo.getTitle());
		System.out.println("address: "+vo.getAddress());
		System.out.println("msg: "+vo.getMsg());
	}
	@Test
	public void natureMain() {
		ApplicationContext app= new ClassPathXmlApplicationContext("app.xml");
		NatureDAO dao=(NatureDAO)app.getBean("ndao");
		List<SeoulNatureVO> list=dao.natureListData();
		for(SeoulNatureVO vo:list) {
			System.out.println(vo.getNo()+". "+vo.getTitle());
		}
	}
	@Test
	public void natureDetail() {
		Scanner scan=new Scanner(System.in);
		System.out.println("number");
		int no=scan.nextInt();
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		NatureDAO dao=(NatureDAO)app.getBean("ndao");
		SeoulNatureVO vo=dao.natureDetail(no);
		System.out.println("title: "+vo.getTitle());
		System.out.println("address: "+vo.getAddress());
		System.out.println("msg: "+vo.getMsg());
	}
	@Test
	public void shopMain() {
		ApplicationContext app= new ClassPathXmlApplicationContext("app.xml");
		ShopDAO dao=(ShopDAO)app.getBean("sdao");
		List<SeoulShopVO> list=dao.shopListData();
		for(SeoulShopVO vo:list) {
			System.out.println(vo.getNo()+". "+vo.getTitle());
		}
	}
	@Test
	public void shopDetail() {
		Scanner scan=new Scanner(System.in);
		System.out.println("number");
		int no=scan.nextInt();
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		ShopDAO dao=(ShopDAO)app.getBean("sdao");
		SeoulShopVO vo=dao.shopDetail(no);
		System.out.println("title: "+vo.getTitle());
		System.out.println("address: "+vo.getAddress());
		System.out.println("msg: "+vo.getMsg());
	}
}

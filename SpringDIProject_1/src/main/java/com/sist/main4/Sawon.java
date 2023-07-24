package com.sist.main4;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Sawon {
	private int sabun;
	private String name;
	private String dept;
	private String job;
	private int pay;
	// default constructor 
	public Sawon() {}
	
	public void print() {
		System.out.println("no: "+sabun);
		System.out.println("name: "+name);
		System.out.println("dept: "+dept);
		System.out.println("job: "+job);
		System.out.println("pay: "+pay);
	}
	
	public void init() {
		System.out.println("============사원정보=============");
	}
	public void destroy() {
		System.out.println("객체 메모리 해제...");
	}
	
}

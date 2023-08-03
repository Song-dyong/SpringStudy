package com.sist.dao;

public class BoardDAO {
	
	public String find(String name) {
		System.out.println("find ...");
		return name;
	}
	
	public void aopSelect(String name) {					// AOP는 공통모듈
		System.out.println(name+" - SELECT 문장 수행");	// 핵심모듈
	}
	public void aopUpdate() {
		System.out.println("Update 문장 수행");
	}
	public void aopInsert() {
		System.out.println("Insert 문장 수행");
	}
	public void aopDelete() {
		System.out.println("Delete문장 수행");
	}
}

package com.sist.dao;

/*
	공통으로 사용되는 메소드를 AOP에 등록해서 필요시마다 호출
	단, Spring은 자동으로 호출
*/
public class MyDAO {
	public void getConnection() {
		System.out.println("오라클 getConnection");
	}
	public void disConnection() {
		System.out.println("Oracle disConnection");
	}
	public void aopSelect() {
		getConnection();
		System.out.println("SELECT...");
		disConnection();
	}
	public void aopInsert() {
		getConnection();
		System.out.println("INSERT...");
		disConnection();
	}
	public void aopUpdate() {
		getConnection();
		System.out.println("Update...");
		disConnection();
	}
	public void aopDelete() {
		getConnection();
		System.out.println("DELETE...");
		disConnection();
	}
	
}

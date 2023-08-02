package com.sist.main;

/*
	AOP => 원하는 메소드를 묶은 뒤, 원하는 지점에서 출력하기 위해 사용
		CallBack 함수 
		Trigger와 유사
*/
public class MainClass {
	public static void main(String[] args) {
		Sawon s=new Sawon();
		s.display();
		Proxy p=new Proxy(s);
		p.display();
	}
}

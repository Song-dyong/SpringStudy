package com.sist.main2;

public class MainClass {
	public static void main(String[] args) {
		Hello hello=new HelloImp1();
		String msg=hello.sayHello("hong");
		
		System.out.println(msg);
	}
}

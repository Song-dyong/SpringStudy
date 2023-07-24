package com.sist.main2;

public class HelloImp1 implements Hello {

	// interface 사용시,리팩토링을 거쳐도 본인 클래스에만 에러 발생.
	
	@Override
	public String sayHello(String name) {
		// TODO Auto-generated method stub
		return name+"님 환영합니다.";
	}

}

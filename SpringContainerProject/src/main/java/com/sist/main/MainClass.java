package com.sist.main;

/*
		MainClass는 Hello클래스에 의존을 한다.
		=> 결합성이 강한 프로그램 (의존성이 강한 프로그램)
		=> 장점 : 연결하기 쉽다 / 단점 : 변경시에 다른 클래스에 영향력이 강하다.
		
			스프링에서는 방지 (의존성이 낮은 프로그램 작성)
			=> 클래스 수정 => 다른 클래스에 영향이 없게 만든다.
			=> 스프링에서는 가급적 new를 사용하지 않는다.
			
		인터페이스는 메인과 메소드를 연결하는 역할
		=> 의존성을 낮추기 위함.
		
		Spring => Class 여러개를 묶어서 관리하는 Container 역할 (클래스 관리자)
		
*/
public class MainClass {
	public static void main(String[] args) {
		Hello hello=new Hello();
		String msg=hello.sayHello2("홍길동");
		System.out.println(msg);
	}
}

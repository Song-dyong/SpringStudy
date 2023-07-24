package com.sist.main2;

import javax.security.auth.Destroyable;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/*
	<bean id="a" class="A"/>
	1. 메모리 할당 => 생성자 호출
	2. setter DI => setXxx() 값을 채운다.
	3. init-method에 등록된 메소드 호출
	4. 개발자에 의해 사용자 정의 메소드 호출
	5. destroy-method 호출 => 메모리 해제 

*/
public class Sawon implements BeanNameAware,InitializingBean, DisposableBean {
	private int sabun;
	private String name, dept;
	// <bean id="sa" class="com.sist.main2.Sawon"/>
	// => 태그를 읽자마자 메모리할당(생성자 호출)
	
	public Sawon() {
		System.out.println("Sawon() Call...");
	}
	
	public int getSabun() {
		return sabun;
	}
	public void setSabun(int sabun) {
		System.out.println("setSabun() Call...:"+sabun);
		this.sabun = sabun;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		System.out.println("setName() Call...:"+name);
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		System.out.println("setDept() Call...:"+dept);
		this.dept = dept;
	}
	public void init() {
		System.out.println("init() Call...");
		System.out.println("===사원정보===");
	}
	public void print() {
		System.out.println("사용자 정의 호출...");
		System.out.println("Sno: "+sabun);
		System.out.println("name: "+name);
		System.out.println("dept: "+dept);
	}
	
	// setter를 읽었을 때 메소드
	@Override
	public void setBeanName(String name) {
		System.out.println("setBeanName():"+name);
	}
	
	// 속성의 setter를 채운 후 메소드
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Setter Set End...");
	}

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("memory end");
	}
}

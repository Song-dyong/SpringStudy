package com.sist.main2;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
	private int hakbun;
	private String name, sex;
	
	public void init() {
		System.out.println("===학생정보===");
	}
	public void print() {
		System.out.println("Sno: "+hakbun);
		System.out.println("name: "+name);
		System.out.println("sex: "+sex);
	}
}

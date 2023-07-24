package com.sist.main2;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
	private String id, name, sex;
	
	public void init() {
		System.out.println("===회원정보===");
	}
	public void print() {
		System.out.println("Mno: "+id);
		System.out.println("name: "+name);
		System.out.println("sex: "+sex);
	}
}

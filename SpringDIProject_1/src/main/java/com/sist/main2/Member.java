package com.sist.main2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
//DI는 Setter를 통해 값 주입 or 생성자()를 통해 주입.
public class Member {
	private String id;
	private String name;
	private String address;
	private String phone;
	private String sex;
	// 개발자가 호출 
	public void print() {
		System.out.println("ID: "+id);
		System.out.println("NAME: "+name);
		System.out.println("address: "+address);
		System.out.println("phone: "+phone);
		System.out.println("sex: "+sex);
	}
}

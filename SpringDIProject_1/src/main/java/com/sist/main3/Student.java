package com.sist.main3;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
	private int hakbun;
	private String name;
	private int kor,eng,math;
	
	public void print() {
		System.out.println("학번: "+hakbun);
		System.out.println("name: "+name);
		System.out.println("kor: "+kor);
		System.out.println("eng: "+eng);
		System.out.println("math: "+math);
		System.out.println("총점: "+(kor+eng+math));
		System.out.printf("평균:%.2f\n ",(kor+eng+math)/3.0);
	}
}

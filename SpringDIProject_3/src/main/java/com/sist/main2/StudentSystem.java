package com.sist.main2;

import java.util.ArrayList;
import java.util.List;

import lombok.Setter;

public class StudentSystem {
	@Setter
	private List<Student> list = new ArrayList<Student>();
	
	public void print() {
		for(Student s:list) {
			System.out.println(s.getHakbun()+" "
					+s.getName()+" "
					+s.getKor()+" "
					+s.getEng()+" "
					+s.getMath());
		}
	}
}

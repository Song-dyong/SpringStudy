package com.sist.web.vo;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpVO {
	private int empno;
	private int sal, deptno;
	private String ename, job, mgr, hiredate,comm,address;
}

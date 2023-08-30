package com.sist.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="emp")
public class EmpEntity {
	@Id
	private int empno;
	private int sal, deptno;
	private String ename, job, mgr, hiredate,comm,address;
}

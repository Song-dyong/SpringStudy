package com.sist.dao2;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper2.EmpDeptMapper;
import com.sist.vo.*;
@Repository
public class EmpDAO {
	// 스프링 MyBatis를 통해 자동 주입받기 (메모리 할당)
	@Autowired
	private EmpDeptMapper mapper;
//	@Results({
//		@Result(column = "dname", property = "dvo.dname"),
//		@Result(column = "loc", property = "dvo.loc")
//	})
//	/*
//		SELECT empno, ename, job,TO_CHAR(hiredate,'YYYY-MM-DD') as dbday, sal, dname, loc
//  		FROM emp, dept WHERE emp.deptno=dept.deptno ORDER BY sal DESC
//	*/
//	@Select("SELECT empno, ename, job,TO_CHAR(hiredate,'YYYY-MM-DD') as dbday, sal, dname, loc "
//			+ "FROM emp, dept WHERE emp.deptno=dept.deptno ORDER BY sal DESC")
	public List<EmpVO> empdeptListData(){
		return mapper.empdeptListData();
	}
	
//	@Results({
//		@Result(column = "dname", property = "dvo.dname"),
//		@Result(column = "loc", property = "dvo.loc")
//	})
//	@Select("SELECT empno, ename, job,TO_CHAR(hiredate,'YYYY-MM-DD') as dbday, sal, dname, loc "
//			+ "FROM emp, dept WHERE emp.deptno=dept.deptno AND empno=#{empno}")
	public EmpVO empdeptDetailData(int empno) {
		return mapper.empdeptDetailData(empno);
	}
}

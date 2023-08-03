package com.sist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
public class EmpDAO {
	// 어노테이션을 통해 코딩하지 않고 있기 때문에, 값을 채우기 위해 setter를 사용해야한다.
	private EmpMapper mapper;
	public void setMapper(EmpMapper mapper) {
		this.mapper = mapper;
	}
//	@Select("SELECT empno, ename, job, TO_CHAR(hiredate,'YYYY-MM-DD') as dbday, sal "
//			+ "FROM emp")

	public List<EmpVO> empListData(){
		return mapper.empListData();
	}
	
//	@Select("SELECT empno, ename, job, TO_CHAR(hiredate,'YYYY-MM-DD') as dbday, sal "
//			+ "FROM emp "
//			+ "WHERE empno=#{empno}")
	public EmpVO empDetailData(int empno) {
		return mapper.empDetailData(empno);
	}
	
//	@Select("SELECT empno, ename, job, TO_CHAR(hiredate,'YYYY-MM-DD') as dbday, sal "
//			+ "FROM emp "
//			+ "WHERE ename LIKE '%'||#{ename}||'%'")
	public List<EmpVO> empFindData(String ename){
		return mapper.empFindData(ename);
	}
}

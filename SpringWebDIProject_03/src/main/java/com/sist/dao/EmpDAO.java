package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;
							// xml 코딩시 사용
@Repository
public class EmpDAO extends SqlSessionDaoSupport{
	/*
		TYPE, CONSTRUCTOR, FIELD, METHOD, PARAMETER})
		객체		생성자		멤버변수	메소드	매개변수
		=> @Autowired는 객체생성시에만 사용되는 것은 아님
		Class A{
			@Autowired
			private B b;
			
			@Autowired
			public A(B b){}
			
			@Autowired
			public void setB(B b){}
		}
	*/
	
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	/*
	<select id="empInfoData" resultType="EmpVO" parameterType="hashmap">
		SELECT empno, ename, job, TO_CHAR(hiredate,'YYYY-MM-DD') as dbday, sal, comm, deptno 
		FROM emp
	*/
	public List<String> empGetNameData(){
		return getSqlSession().selectList("empGetNameData");
	}
	public List<EmpVO> empInfoData(Map map){
		return getSqlSession().selectList("empInfoData", map);
	}
}

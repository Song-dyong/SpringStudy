package com.sist.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sist.vo.EmpVO;

/*
	service(interface) => serviceImp1 => repository(DAO)
	
	service => 유지보수 용이하도록 (DAO) 모아두기
*/
/*
	<sql id="select-emp">
  		SELECT empno, ename, job,TO_CHAR(hiredate,'YYYY-MM-DD') as dbday,
  				sal, dname, loc
  		FROM emp, dept
  		WHERE emp.deptno=dept.deptno
  	</sql>
  	
  	<select id="empdeptListData" resultMap="empMap">
  		<include refid="select-emp"/>
  		ORDER BY sal DESC
  	</select>								<!-- parameterMap => 프로시저 호출용 -->
  	<select id="empdeptDetailData" resultMap="empMap" parameterType="int">
  		<include refid="select-emp"/>
  		WHERE empno=#{empno}
  	</select>
*/
import java.util.*;
public class EmpDAO extends SqlSessionDaoSupport{
	
	public List<EmpVO> empdeptListData(){
		return getSqlSession().selectList("empdeptListData");
	}
	
	public EmpVO empdeptDetailData(int empno) {
		return getSqlSession().selectOne("empdeptDetailData",empno);
	}
	
}

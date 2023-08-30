package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.EmpEntity;

@Repository
public interface EmpDAO extends JpaRepository<EmpEntity, Integer> {
	
	@Query(value = "SELECT * FROM emp WHERE ename LIKE CONCAT('%',:ename,'%') "
			+ "ORDER BY empno ASC "
			+ "LIMIT :start , 3" , nativeQuery=true)
	public List<EmpEntity> empFindListData(@Param("ename") String ename,  @Param("start") Integer start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/3.0) FROM emp "
			+ "WHERE ename LIKE CONCAT('%',:ename,'%')", nativeQuery=true)
	public int empFindTotalPage(@Param("ename") String ename);
	
	public EmpEntity findByEmpno(int empno);
}

package com.sist.web.dao;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.*;
import com.sist.web.vo.CategoryVO;
@Repository												// id의 데이터형 (double은 Double)
public interface FoodDAO extends JpaRepository<FoodEntity, Integer>{
	// public FoodEntity findByFno(int fno);
	// SELECT * FROM food_location WHERE fno=1
	// insert , update , delete => 메소드로 만들어진 상태.
	@Query(value = "SELECT * FROM food_category "
			+ "WHERE title LIKE CONCAT('%',:title,'%') "
			+ "ORDER BY cno ASC "
			+ "LIMIT :start , 3", nativeQuery=true)			// nativeQuery=> 있는 그대로 실행
	public List<FoodEntity> foodFindData(@Param("title") String title, @Param("start") Integer start);
	// 매개변수가 2개 이상 들어갈 경우, @Param으로 설정 org.springframework.data.repository.query.Param
	// mybatis에서도 사용 가능 => 기존에는 Map map으로 넣었음
	// 데이터베이스 값 가져오기를 더 편하게 JPA
	
	@Query(value = "SELECT CEIL(COUNT(*)/3.0) FROM food_category "
			+ "WHERE title LIKE CONCAT('%',:title,'%')", nativeQuery=true)
	public int foodFindTotalPage(@Param("title") String title);
	
	public FoodEntity findByCno(@Param("cno") Integer cno);
	
}

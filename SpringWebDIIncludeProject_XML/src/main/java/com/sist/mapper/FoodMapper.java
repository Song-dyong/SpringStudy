package com.sist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;

public interface FoodMapper {
	// 동적 쿼리를 이용한 경우 <script></script> 사용!
	// Q. @Select가 어디에 적용되길래 <script> 태그를 사용하는지? => 자바에서 처리하는거 아닌가.
	// <script>를 넣지 않으면 <if> 의 태그를 읽을 수 없음.
	@Select("<script>"
			+ "SELECT cno,poster,title "
			+ "FROM food_category "
			+ "WHERE "
			+ "<if test=\"cno==1\">"
			+ "cno BETWEEN 1 AND 12 "
			+ "</if>"
			+ "<if test=\"cno==2\">"
			+ "cno BETWEEN 13 AND 18 "
			+ "</if>"
			+ "<if test=\"cno==3\">"
			+ "cno BETWEEN 19 AND 30 "
			+ "</if>"
			+ "ORDER BY cno ASC"
			+ "</script>")
	public List<CategoryVO> foodCategoryData(Map map);

	/*
		xml을 이용한 동적쿼리
<mapper namespace="com.sist.mapper.emp-mapper">
	<select id="empGetNameData" resultType="string">
		SELECT ename FROM emp
	</select>
	<select id="empInfoData" resultType="EmpVO" parameterType="hashmap">
		SELECT empno, ename, job, TO_CHAR(hiredate,'YYYY-MM-DD') as dbday, sal, comm, deptno 
		FROM emp
		
		<trim prefix="WHERE ename IN(" suffix=")" suffixOverrides=")" prefixOverrides="(">
			<foreach collection="names" item="name" open="(" close=")" separator=",">
				#{name}
			</foreach>
		</trim>
	</select>
</mapper>
			=> <script>는 어디서 나온건지?
	*/
	// 카테고리 정보 읽기
	@Select("SELECT title, subject FROM food_category WHERE cno=#{cno}")
	public CategoryVO foodCategoryInfoData(int cno);
	// 카테고리별 맛집 정보 읽기
	@Select("SELECT fno, cno, name, poster, address, score, type, phone "
			+ "FROM food_house "
			+ "WHERE cno=#{cno}")
	public List<FoodVO> foodListData(int cno);
	
	// 맛집별 상세보기
	@Select("SELECT fno, cno, name, score, poster, phone, address, type, time, parking, menu, price "
			+ "FROM food_house "
			+ "WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
	/*
		Mapper에서는 반드시 매개변수가 1개여야 한다.
		매개변수가 여러 개인 경우, VO 혹은 Map에 담아서 전송해야한다.
		데이터가 VO에 있는 경우, VO
		데이터가 VO에 없는 경우, Map ex) start / end (페이징) 
		=> 동적 쿼리를 이용할 경우 => Map
		DAO는 사용자가 필요한 내용으로 설정 
	*/
	
}

package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.sist.vo.SeoulVO;

public interface SeoulMapper {
	// 만들어진 프로시저를 호출하기 위해, 기존의 preparedStatement가 아닌, Callable을 설정
	// mode=IN => IN변수를 의미
	@Select(value="{CALL seoulListData(#{pNo,mode=IN,javaType=java.lang.Integer},#{pStart,mode=IN,javaType=java.lang.Integer}"
			+ ",#{pEnd,mode=IN,javaType=java.lang.Integer},#{pResult,mode=OUT,jdbcType=CURSOR,resultMap=seoulMap})}")
	@Options(statementType = StatementType.CALLABLE)	// 프로시저호출 임포트 => ibatis.mapping
	public List<SeoulVO> seoulListData(Map map);		// jdbcType=CURSOR인 경우, resultMap을 설정해주어야한다.
	
	@Select(value="{CALL seoulTotalPage(#{pNo,mode=IN,javaType=java.lang.Integer},#{pTotal,mode=OUT,jdbcType=INTEGER})}")
	@Options(statementType = StatementType.CALLABLE)
	public Integer seoulTotalPage(Map map);
	
	/*
		<select id="seoulDetailData" resultType="com.sist.vo.SeoulVO" parameterType="hashmap">
			SELECT no,title,poster,msg,address
			FROM ${table_name}
			WHERE no=${no}
		</select>
		
		resultType	  => 리턴형
		parameterType => 매개변수
	*/
	//	  resultType    id		parameterType
	//	  --------- 	--		-------------
	public SeoulVO seoulDetailData(Map map);
}

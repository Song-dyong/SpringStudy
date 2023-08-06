package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;
@Repository
public class SeoulDAO {
	@Autowired
	private SeoulMapper mapper;
	
	public List<SeoulVO> seoulListData(Map map){
		mapper.seoulListData(map);
		return (List<SeoulVO>)map.get("pResult");
	}
	
//	@Select(value="{CALL seoulTotalPage(#{pNo,mode=IN,javaType=java.lang.Integer},#{pTotal,mode=OUT,javaType=java.lang.Integer})}")
//	@Options(statementType = StatementType.CALLABLE)
	public Integer seoulTotalPage(Map map) {
		mapper.seoulTotalPage(map);
		return (Integer)map.get("pTotal");
	}

	/*
		프로시저, 쿼리문장이 복잡한 경우, 
		seoul-mapper.xml 처럼 xml에서 쿼리문 처리 => SeoulMapper에서 인터페이스 작성 => DAO에서 구현
	
	*/
	public SeoulVO seoulDetailData(Map map) {
		return mapper.seoulDetailData(map);
	}
	
}

package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class FoodDAO {
	@Autowired // 스프링으로부터 구현된 클래스의 주소값을 받는다 (자동 주입)
	private FoodMapper mapper;
	
	public List<CategoryVO> foodCategoryData(Map map){
		return mapper.foodCategoryData(map);
	}
	public CategoryVO foodCategoryInfoData(int cno){
		return mapper.foodCategoryInfoData(cno);
	}
	// 카테고리별 맛집 정보 읽기
	public List<FoodVO> foodListData(int cno){
		return mapper.foodListData(cno);
	}
	
//	@Select("SELECT fno, cno, name, score, poster, phone, address, type, time, parking, menu "
//			+ "FROM food_house "
//			+ "WHERE fno=#{fno}")  sql문장을 확인하기 위해, 주석으로 남겨두기
	public FoodVO foodDetailData(int fno) {
		return mapper.foodDetailData(fno);
	}
	
}


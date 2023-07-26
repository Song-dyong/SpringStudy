package com.sist.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
// DAO 메모리 할당 => Repository why? Component의 상속을 받아서 Component를 사용 가능
//	but! 개발자 간의 

import com.sist.mapper.FoodMapper;
@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	//@Select("SELECT fno,name FROM food_house WHERE cno=#{cno}")
	public List<FoodVO> foodCategoryListData(int cno){
		return mapper.foodCategoryListData(cno);
	}
	
	//@Select("SELECT fno,name,phone,address,parking,time,menu,price,score FROM food_house "
	//		+ "WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno) {
		return mapper.foodDetailData(fno);
	}
}

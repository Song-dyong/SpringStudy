package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	public List<CategoryVO> foodCategoryListData(){
		return mapper.foodCategoryListData();
	}
	
	public CategoryVO categoryInfoData(int cno) {
		return mapper.categoryInfoData(cno);
	}
	
	public List<FoodVO> foodFindData(Map map){
		return mapper.foodFindData(map);
	}
	
	public int foodFindTotalPage(Map map) {
		return mapper.foodFindTotalPage(map);
	}
	
	public FoodVO foodDetailData(int fno) {
		return mapper.foodDetailData(fno);
	}
	
	public List<FoodVO> foodListData(int cno){
		return mapper.foodListData(cno);
	}
	
	public FoodVO foodDetailHouseData(int fno) {
		return mapper.foodDetailHouseData(fno);
	}
	
	
	
}

package com.sist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;

public interface FoodMapper {
	
	@Select("SELECT cno,title,poster,subject "
			+ "FROM food_category "
			+ "ORDER BY cno ASC")
	public List<CategoryVO> foodCategoryListData();
	
	@Select("SELECT title, subject FROM food_category WHERE cno=#{cno}")
	public CategoryVO categoryInfoData(int cno); 
	
	public List<FoodVO> foodFindData(Map map);
	
	public int foodFindTotalPage(Map map);
}

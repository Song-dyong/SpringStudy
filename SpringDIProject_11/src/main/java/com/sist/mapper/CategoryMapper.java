package com.sist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.sist.dao.CategoryVO;
import com.sist.dao.FoodVO;

public interface CategoryMapper {
	@Select("SELECT cno,title,subject FROM food_category ORDER BY cno ASC")
	public List<CategoryVO> categoryListData();
}

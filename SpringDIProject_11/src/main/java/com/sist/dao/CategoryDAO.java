package com.sist.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.CategoryMapper;

@Repository
public class CategoryDAO {
	@Autowired	// Spring으로 CategoryMapper를 구현한 주소의 값을 가져오기
	private CategoryMapper mapper;
	
	//@Select("SELECT cno,title,subject FROM food_category ORDER BY cno ASC")
	public List<CategoryVO> categoryListData(){
		return mapper.categoryListData();
	}
}

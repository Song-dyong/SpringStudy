package com.sist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.AspectMapper;
import com.sist.vo.FoodVO;
import com.sist.vo.RecipeVO;
import com.sist.vo.SeoulVO;

@Repository
public class AspectDAO {
	@Autowired
	private AspectMapper mapper;
	
	public List<FoodVO> foodTop7Data(){
		return mapper.foodTop7Data();
	}
	
	public List<String> seoulTop7Data(){
		return mapper.seoulTop7Data();
	}
	
	public List<String> recipeTop7Data(){
		return mapper.recipeTop7Data();
	}
}

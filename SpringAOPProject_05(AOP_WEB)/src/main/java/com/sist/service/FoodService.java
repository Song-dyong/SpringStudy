package com.sist.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;
import com.sist.vo.SeoulVO;

/*
	Service를 통해 의존성을 약화시킴
*/

public interface FoodService {
	public List<CategoryVO> foodCategoryData();
	public List<FoodVO> foodTop7();
	public List<SeoulVO> seoulTop7();
}

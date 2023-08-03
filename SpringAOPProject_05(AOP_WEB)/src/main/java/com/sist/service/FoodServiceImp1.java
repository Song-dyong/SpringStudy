package com.sist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.FoodDAO;
import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;
import com.sist.vo.SeoulVO;

@Service
public class FoodServiceImp1 implements FoodService {
	@Autowired
	private FoodDAO dao;
	@Override
	public List<CategoryVO> foodCategoryData() {
		return dao.foodCategoryData();
	}

	@Override
	public List<FoodVO> foodTop7() {
		return dao.foodTop7();
	}

	@Override
	public List<SeoulVO> seoulTop7() {
		return dao.seoulTop7();
	}

}

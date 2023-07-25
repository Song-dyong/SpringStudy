package com.sist.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class FoodDAO extends SqlSessionDaoSupport{
	
	public List<FoodVO> foodCategoryList(){
		return getSqlSession().selectList("foodCategoryList");
	}
	
}

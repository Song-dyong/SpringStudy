package com.sist.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.CategoryVO;

@Repository
@Mapper
public interface FoodMapper {
	public List<CategoryVO> categoryListData();
}

package com.sist.web.service;

import java.util.List;
import java.util.Map;

import com.sist.web.vo.CategoryVO;

public interface FoodService {
	public List<CategoryVO> foodFindData(Map map);
	public int foodFindTotalPage(String title);
}

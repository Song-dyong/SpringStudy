package com.sist.dao;
// 여러 개의 DAO를 합쳐서 사용하는 Service

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {
	@Autowired
	private FoodDAO fdao;
	@Autowired
	private CategoryDAO cdao;

	/*
		dao => service 로 거쳐오면서 다른 클래스에 영향이 적게 만들기.
	*/
	public List<CategoryVO> categoryListData(){
		return cdao.categoryListData();
	}
	
	public List<FoodVO> foodCategoryListData(int cno){
		return fdao.foodCategoryListData(cno);
	}
	
	public FoodVO foodDetailData(int fno) {
		return fdao.foodDetailData(fno);
	}
}

package com.sist.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
	Model 클래스 => 요청을 받아서 응답하는 역할
	Controller 어노테이션 = Model
*/
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;
@Controller
// 경로명이 긴 경우, 모델 자체에 경로명 부여
@RequestMapping("food/")
public class FoodModel {
	@Autowired
	private FoodDAO dao;
	@RequestMapping("category.do")
	public String food_category(HttpServletRequest request, HttpServletResponse response) {
		
		List<CategoryVO> list=dao.categoryListData();
		request.setAttribute("list", list);
		return "food/category";
	}
	
	@RequestMapping("food_list.do")
	public String food_list(HttpServletRequest request, HttpServletResponse response) {
		String cno=request.getParameter("cno");
		List<FoodVO> list=dao.foodListData(Integer.parseInt(cno));
		request.setAttribute("list", list);
		return "food/list";
	}
	
}

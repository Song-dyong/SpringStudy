package com.sist.web;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FoodController {
	@Autowired
	private FoodDAO dao;
	
	// 4.3 => GET / POST / RequestMapiing (get+post) 
	// ex) 검색 => get + post
	// 최초의 창은 get / 검색값이 넘어갈 때 post
	@RequestMapping("food/category.do")
	public String food_category(String cno, Model model) {
								// 최초로 넘어올 때, int형은 null값이 없으므로 String으로 가져와서 null 처리
								// 이후, 형변환
		if(cno==null)
			cno="1";
		Map map=new HashMap();
		map.put("cno", Integer.parseInt(cno));
		List<CategoryVO> list=dao.categoryListData(map);
		
		model.addAttribute("list",list);
		return "food/category";
	}
	
}

package com.sist.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.FoodDAO;
import com.sist.vo.*;
import java.util.*;

import javax.servlet.http.HttpSession;

@RestController
public class FoodRestController {
	
	@Autowired
	private FoodDAO dao;
	
	@GetMapping(value = "food/food_category_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_category(int cno) throws Exception{
		Map map = new HashMap();
		map.put("cno", cno);
		List<CategoryVO> list=dao.foodCategoryListData(map);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		
		return json;
	}
	
	@GetMapping(value = "food/food_category_info_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_category_info(int cno) throws Exception{
		CategoryVO vo=dao.foodCategoryInfoData(cno);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
	}
	
	@GetMapping(value = "food/food_list_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_list(int cno) throws Exception{
		List<FoodVO> list=dao.foodListData(cno);
		for(FoodVO vo:list) {
			String poster=vo.getPoster();
			poster=poster.substring(0,poster.indexOf("^"));
			poster=poster.replace("#", "&");
			vo.setPoster(poster);
			
			String address=vo.getAddress();
			address=address.substring(0,address.indexOf("지번"));
			vo.setAddress(address);
		}
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	@GetMapping(value = "food/food_detail_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_detail_vue(int fno, HttpSession session) throws Exception{
		String id=(String)session.getAttribute("id");
		FoodVO vo=dao.foodDetailData(fno);
		String addr=vo.getAddress();
		addr=addr.substring(0,addr.indexOf("지번"));
		vo.setAddress(addr.trim());
		// jstl을 사용하지 않기 위해, 세션아이디를 따로 저장해서 넘어오기
		vo.setSessionId(id);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		// writeValueAsString => Jackson의 메소드 (JSONObject, JSONArray의 축약 obj.put)
		return json;
	}
	

	
}

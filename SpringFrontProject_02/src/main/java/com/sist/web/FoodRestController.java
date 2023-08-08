package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.dao.*;
import com.sist.vo.*;
import java.util.*;

@RestController
public class FoodRestController {
	@Autowired
	private FoodDAO dao;
	
	@GetMapping(value = "food/list_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_list_vue(String page) {
		String result="";
		
		try {
			if(page==null)
				page="1";
			int curpage=Integer.parseInt(page);
			Map map=new HashMap();
			map.put("start", (curpage*12)-11);
			map.put("end", (curpage*12));
			List<FoodLocationVO> list=dao.foodListData(map);
			int totalpage=dao.foodTotalPage();
			// List => []			=> JSONArray
			// FoodLocationVO => {}	=> JSONObject
			// [{},{},{}...]
/*
			{fno:1,name="맛있는 집",poster:"http://~~~",score:4.7}
*/
			JSONArray arr=new JSONArray();
			int i=0;
			for(FoodLocationVO vo:list) {
				JSONObject obj=new JSONObject();
				obj.put("fno", vo.getFno());
				obj.put("name", vo.getName());
				obj.put("score", vo.getScore());
				
				String poster=vo.getPoster();
				poster=poster.substring(0,poster.indexOf("^"));
				poster=poster.replace("#", "&");
				obj.put("poster", poster);
				if(i==0) {
					obj.put("curpage", curpage);
					obj.put("totalpage", totalpage);
				}
				arr.add(obj);
				i++;
			}
			result=arr.toJSONString();
		} catch (Exception e) {}
		return result;
	}
	
	/*
		1. 일반 문자열 => NOID , NOPWD , OK ... => text/html
		2. 데이터 묶음(JSON) => text/plain
			List / VO
			----   --	=> 서로 다른 언어의 호환 Rest (자바에서는 JSON이 담당)
			배열[]  {}							jackson (잭슨은 데이터를 자동으로 변환해줌)
		3. XML전송 => text/xml					Spring-boot => 자동화 처리
	*/
	@GetMapping(value = "food/find_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_find_vue(int page, String fd) {
		String result="";
		try {
			int curpage=page;
			Map map = new HashMap();
			map.put("start", (curpage*12)-11);
			map.put("end", curpage*12);
			map.put("address", fd);
			List<FoodLocationVO> list = dao.foodFindData(map);
			
			int totalpage=dao.foodFindTotalPage(fd);
			final int BLOCK=5;
			int startPage=((curpage-1)/BLOCK*BLOCK)+1;
			int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
			if(endPage>totalpage) {
				endPage=totalpage;
			}
			// JSON 변경
			int i=0;
			// fno, name, poster, score
			JSONArray arr=new JSONArray();
			for(FoodLocationVO vo:list) {
				JSONObject obj=new JSONObject();
				obj.put("fno", vo.getFno());
				obj.put("name", vo.getName());
				obj.put("score", vo.getScore());
				String poster=vo.getPoster();
				poster=poster.replace("#", "&");
				poster=poster.substring(0,poster.indexOf("^"));
				obj.put("poster", poster);
				if(i==0) {
					obj.put("curpage", curpage);
					obj.put("totalpage", totalpage);
					obj.put("startPage", startPage);
					obj.put("endPage", endPage);
					// 0번째 (첫번째) VO에만 주는 이유
					// 첫 페이지 출력을 위해서 => 이후의 page들은 Vue의 메소드를 통해 값 변환
				}
				arr.add(obj);
				i++;
			}
			result=arr.toJSONString();
		} catch (Exception e) {}
		
		return result;
	}
	
	
	
	
}

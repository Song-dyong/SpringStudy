package com.sist.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.dao.ActivityDAO;
import com.sist.vo.ActivityVO;

@RestController
public class ActivityRestController {
	@Autowired
	private ActivityDAO dao;
	
	@GetMapping(value = "activity/list_vue.do", produces = "text/plain;charset=UTF-8")
	public String activity_list_vue(String page) {
		
		String result="";
		try {
			if(page==null)
				page="1";	
			int curpage=Integer.parseInt(page);
			Map map=new HashMap();
			map.put("start", (curpage*12)-11);
			map.put("end", (curpage*12));
			List<ActivityVO> list=dao.activityListData(map);
			int totalpage=dao.activityTotalPage();
			
			JSONArray arr=new JSONArray();
			int i=0;
			for(ActivityVO vo:list) {
				JSONObject obj=new JSONObject();
				obj.put("acino", vo.getAcino());
				obj.put("title", vo.getTitle());
				obj.put("score", vo.getScore());
				obj.put("review_count", vo.getReview_count());
				obj.put("price", vo.getPrice());
				String main_poster=vo.getMain_poster();
				main_poster=main_poster.replace("#", "&");
				obj.put("main_poster", main_poster);
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
	
}

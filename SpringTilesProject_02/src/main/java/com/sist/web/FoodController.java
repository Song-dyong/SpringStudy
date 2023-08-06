package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

@Controller
public class FoodController {
	@Autowired
	private FoodDAO dao;
	
	@GetMapping("food/food_list.do")
	public String food_list(int cno, Model model) {
		List<FoodVO> list=dao.foodListData(cno);
		for(FoodVO vo:list) {
			String address=vo.getAddress();
			address=address.substring(0,address.lastIndexOf("지번"));
			vo.setAddress(address);
			
			String poster=vo.getPoster();
			poster=poster.replace("#", "&");
			poster=poster.substring(0,poster.indexOf("^"));
			vo.setPoster(poster);
		}
		CategoryVO cvo=dao.foodCategoryInfoData(cno);
		model.addAttribute("cvo",cvo);
		model.addAttribute("list",list);
		return "food/food_list";
		// include의 경우, main/main을 사용했지만, main을 상속 받은 상태에서 content의 내용을 보내줘야하므로
		// food/food_list.jsp를 보내줘야 한다.
		// tiles.xml에 설정된 */* => content라는 이름으로 설정된 경로의 /WEB-INF/{1}/{2}.jsp에서
		// 1번에 food 2번에 food_list를 설정 => main은 유지된 채로 content의 내용만 변경 가능 
	}
	
	@GetMapping("food/food_find.do")
	public String food_find(String fd,String page, Model model) {
		if(fd==null)
			fd="마포";
		if(page==null)
			page="1";
		
		int curpage=Integer.parseInt(page);
		// DAO 연결
		Map map=new HashMap();
		int rowSize=20;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		
		map.put("start", start);
		map.put("end", end);
		map.put("fd", fd);
		List<FoodVO> list=dao.foodFindData(map);
		for(FoodVO vo:list) {
			String poster=vo.getPoster();
			poster=poster.substring(0,poster.indexOf("^"));
			poster=poster.replace("#", "&");
		}
		
		int totalpage=dao.foodFindTotalPage(fd);
		
		final int BLOCK=5;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("list",list);
		model.addAttribute("fd",fd);
		return "food/food_find";
	}
	
}

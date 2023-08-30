package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
//import com.sist.web.service.*;
//import com.sist.web.vo.*;

import com.sist.web.entity.*;
import com.sist.web.dao.*;

@Controller
public class FoodController {
	//private FoodService service;
	@Autowired
	private FoodDAO dao;
	
	@RequestMapping("food/find")
	public String food_find(String page, String fd, Model model) {
		if(fd==null) {
			fd="맛집";
		}
		if(page==null) {
			page="1";
		}
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowsize=3;
		int start=(rowsize*curpage)-(rowsize);
		//int end=rowsize*curpage;
		map.put("start", start);
		//map.put("end", end);
		map.put("title", fd);
		int totalpage=dao.foodFindTotalPage(fd);
		List<FoodEntity> list=dao.foodFindData(fd, start);
		
		final int BLOCK=3;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) {
			endPage=totalpage;
		}
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("list",list);
		model.addAttribute("fd",fd);
		return "food/find";
	}
	// https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/he53hhdkhy_-fvxz.jpg?fit=around|600:400#crop=600:400;*,*#output-format=jpg#output-quality=80
	@GetMapping("food/detail")
	public String food_detail(int cno, Model model) {
		FoodEntity vo=dao.findByCno(cno);
		String poster=vo.getPoster();
//		String[] temp=poster.split("\\^");
//		List<String> pList=Arrays.asList(temp);
//		model.addAttribute("pList",pList);
		poster=poster.replace("#", "&");
		vo.setPoster(poster);
		model.addAttribute("vo",vo);
		return "food/detail";
	}
}

package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.web.dao.EmpDAO;
import com.sist.web.entity.EmpEntity;

@Controller
public class EmpController {
	@Autowired
	private EmpDAO dao;
	
	@RequestMapping("emp/find")
	public String emp_find(String fd, String page, Model model) {
		if(fd==null) {
			fd="A";
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
		map.put("ename", fd);
		int totalpage=dao.empFindTotalPage(fd);
		List<EmpEntity> list=dao.empFindListData(fd, start);
		
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
		
		return "emp/find";
	}
}

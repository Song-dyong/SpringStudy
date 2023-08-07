package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.dao.ActivityDAO;

@Controller
public class ActivityController {
	@Autowired
	private ActivityDAO dao;
	
	@GetMapping("activity/list.do")
	public String activity_list() {
		return "activity/list";
	}
	
}

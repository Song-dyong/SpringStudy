package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;
import com.sist.vo.*;

@Controller
public class FoodController {
	@Autowired
	private FoodDAO dao;

	@GetMapping("food/food_list.do")
	public String food_list(int cno, Model model) {
		
		// Database에서 데이터 읽기 => food_list.jsp로 전송 => 화면 출력
		CategoryVO vo=dao.foodCategoryInfoData(cno);
		List<FoodVO> list=dao.foodListData(cno);
		
		model.addAttribute("cvo",vo);
		model.addAttribute("list",list);
		model.addAttribute("main_jsp","../food/food_list.jsp");
		return "main/main";
	}
	
	@GetMapping("food/food_detail.do")
	public String food_detail(int fno, Model model) {
		
		// 상세보기에 필요한 데이터를 오라클에서 읽기
		// 전송
		FoodVO vo=dao.foodDetailData(fno);
		model.addAttribute("vo",vo);
		model.addAttribute("main_jsp","../food/food_detail.jsp");
		return "main/main";
	}
	// 쿠키 => response사용해야함 => cookie에 저장된 값을 가져오려면 request 사용해야함 (매개변수에)
	@GetMapping("food/food_before_detail.do")
	public String food_before_detail(int fno, RedirectAttributes ra, HttpServletResponse response) {

		// 저장되는 쿠키값은 String이어야 하므로, fno를 String으로 형변환
		Cookie cookie=new Cookie("food_"+fno, String.valueOf(fno));
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);	// 초 단위
		// 브라우저로 전송
		response.addCookie(cookie);
//		Model : forward일 때 값을 전송
//		RedirectAttributes : sendRedirect일 때 값을 전송
		
// 		ra.addAttribute를 통해 redirect에 값을 첨부한 채 전송 가능
//		ra.addFlashAttribute("fno",fno);	=> ? 뒤의 값을 감춘 채 전송 가능 (id, pwd 등등..)
		ra.addAttribute("fno",fno);
		return "redirect:../food/food_detail.do";
//		return "redirect:../food/food_detail.do?no="+fno; => 기존 코딩
	}
	
}

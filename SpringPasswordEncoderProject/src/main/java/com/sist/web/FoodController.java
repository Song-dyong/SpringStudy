package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FoodController {
// RestController 는 JSON, 일반 문자열만 보낼 수 있음 => 화면 이동은 Controller에서 해야함
	@GetMapping("food/food_category.do")
	public String food_category() {
		return "food/food_category";
	}
}

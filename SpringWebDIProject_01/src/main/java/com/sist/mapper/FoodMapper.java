package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
public interface FoodMapper {
	@Select("SELECT cno, title, subject, poster FROM food_category "
			+ "ORDER BY cno ASC")
	public List<CategoryVO> categoryListData();
	
	@Select("SELECT fno, name, score, address, phone, type, poster "
			+ "FROM food_house "
			+ "WHERE cno=#{cno}")
	public List<FoodVO> foodListData(int cno);
}

package com.sist.mapper;
import java.util.*;


import org.apache.ibatis.annotations.Select;
import com.sist.vo.*;
public interface FoodMapper {
	@Select("SELECT cno,title,poster FROM food_category ORDER BY cno ASC")
	public List<CategoryVO> foodCategoryData();
	
	@Select("SELECT title,subject FROM food_category "
			+ "WHERE cno=#{cno}")
	public CategoryVO foodCategoryInfoData(int cno);
	
	@Select("SELECT fno,cno,name,poster,phone,type,score,address FROM food_house WHERE cno=#{cno}")
	public List<FoodVO> foodListData(int cno);
	
	@Select("SELECT fno,name,poster,num "
			+ "FROM (SELECT fno,name,poster,rownum as num "
			+ "FROM (SELECT fno,name,poster "
			+ "FROM food_house WHERE address LIKE '%'||#{fd}||'%' ORDER BY fno ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodFindData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM food_house "
			+ "WHERE address LIKE '%'||#{fd}||'%'")
	public int foodFindTotalPage(String fd);
}

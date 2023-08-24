package com.sist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;
import com.sist.vo.ReplyVO;

public interface FoodMapper {
	
	@Select("SELECT cno,title,poster,subject "
			+ "FROM food_category "
			+ "ORDER BY cno ASC")
	public List<CategoryVO> foodCategoryListData();
	
	@Select("SELECT title, subject FROM food_category WHERE cno=#{cno}")
	public CategoryVO categoryInfoData(int cno); 
	
	public List<FoodVO> foodFindData(Map map);
	
	public int foodFindTotalPage(Map map);
	
	@Select("SELECT fno,name,tel as phone,address,type,time,parking,menu,price,score,poster "
			+ "FROM food_location "
			+ "WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
	
	@Select("SELECT fno, name, address, phone, type, poster,score "
			+ "FROM food_house "
			+ "WHERE cno=#{cno}")
	public List<FoodVO> foodListData(int cno);
	
	@Select("SELECT cno,fno,name, phone,address,type,time,parking,price,score,poster "
			+ "FROM food_house "
			+ "WHERE fno=#{fno}")
	public FoodVO foodDetailHouseData(int fno);
	
	// 맛집 추천
	@Select("SELECT DISTINCT name FROM food_location WHERE LENGTH(name)>1")
	public List<String> foodNameGetData();
	// 실제 정보			LENGTH(name)>1 => 한글자인 맛집을 걸러내려고
	@Select("SELECT fno, name, poster, rownum FROM food_location "
			+ "WHERE name=#{name} AND rownum<=1")
	public FoodVO foodRecommandInfoData(String name);
}

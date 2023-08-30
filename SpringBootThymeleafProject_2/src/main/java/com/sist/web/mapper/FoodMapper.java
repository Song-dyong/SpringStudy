package com.sist.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.web.vo.*;

@Repository
@Mapper
public interface FoodMapper {
	//<select id="foodFindData" resultType="CategoryVO" parameterType="hashmap">
	public List<CategoryVO> foodFindData(Map map);
	//<select id="foodFindTotalPage" resultType="int" parameterType="String">
	public int foodFindTotalPage(String title);
}

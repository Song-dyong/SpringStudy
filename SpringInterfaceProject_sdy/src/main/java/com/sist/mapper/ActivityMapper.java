package com.sist.mapper;


import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
import java.util.*;

public interface ActivityMapper {
	
	@Select("SELECT acino,price, title, score, review_count, reviewer, review_content, hours_use, location_name, location_poster, how_use, poster, main_poster, num "
			+ "FROM (SELECT acino,price, title, score, review_count, reviewer, review_content, hours_use, location_name, location_poster, how_use, poster, main_poster, rownum as num "
			+ "FROM (SELECT acino,price, title, score, review_count, reviewer, review_content, hours_use, location_name, location_poster, how_use, poster, main_poster "
			+ "FROM activity_info ORDER BY acino ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<ActivityVO> activityListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM activity_info")
	public int activityTotalPage();
	
}

package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.sist.vo.*;

public interface DataBoardMapper {
	@Select("SELECT no, subject, name, TO_CHAR(regdate,'YYYY-MM-DD') as dbday, hit, num "
			+ "FROM (SELECT no, subject, name, regdate, hit, rownum as num "
			+ "FROM (SELECT /*+ INDEX_DESC(springDataBoard1 sdb1_no_pk)*/no, subject, name, regdate, hit "
			+ "FROM springDataBoard1)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<DataBoardVO> databoardListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM springDataBoard1")
	public int databoardTotalPage();
	// Sequence만들기 in mybatis => no에 넣을 값을 먼저 설정
	@SelectKey(keyProperty = "no", resultType = int.class, before = true,
			statement="SELECT NVL(MAX(no)+1,1) as no FROM springDataBoard1")
	@Insert("INSERT INTO springDataBoard1(no,name,subject,content,pwd,filename,filesize,filecount) "
			+ "VALUES(#{no},#{name},#{subject},#{content},#{pwd},#{filename},#{filesize},#{filecount})")
	public void databoardInsert(DataBoardVO vo);
}

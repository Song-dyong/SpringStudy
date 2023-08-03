package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

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
	
	// 상세보기
	@Update("UPDATE springDataBoard1 SET "
			+ "hit=hit+1 "
			+ "WHERE no=#{no}")
	public void hitIncrement(int no);
	
	@Select("SELECT no,name,subject,content,hit,TO_CHAR(regdate,'YYYY-MM-DD') as dbday, "
			+ "filename, filesize, filecount "
			+ "FROM springDataBoard1 "
			+ "WHERE no=#{no}")
	public DataBoardVO databoardDetailData(int no);
	
	// 검색		$(컬럼명) 			/ #(일반 데이터)
	//		name, subject, content		'name', 'subject' , 'content'
	// 자바 코딩 => "WHERE " + fs + " LIKE '%'||?||'%'"
	// ${fs} => name, subject, content 
	@Select("SELECT no, subject, name, TO_CHAR(regdate,'YYYY-MM-DD') as dbday, hit "
			+ "FROM springDataBoard1 "
			+ "WHERE ${fs} LIKE '%'||#{ss}||'%'")
	public List<DataBoardVO> databoardFindData(Map map);
	
	// 수정하기
	// 비밀번호 검색
	@Select("SELECT pwd from springDataBoard1 WHERE no=#{no}")
	public String databoardGetPassword(int no);
	// 실제 수정하기 
	@Update("UPDATE springDataBoard1 SET "
			+ "name=#{name}, subject=#{subject}, content=#{content} "
			+ "WHERE no=#{no}")
	public void databoardUpdate(DataBoardVO vo);
	// 삭제하기
	@Delete("DELETE FROM springDataBoard1 WHERE no=#{no}")
	public void databoardDelete(int no);
	
	
}

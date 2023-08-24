package com.sist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.DataBoardVO;

public interface DataBoardMapper {
	@Select("SELECT no, subject, name, TO_CHAR(regdate,'YYYY-MM-DD') as dbday, hit, num "
			+ "FROM (SELECT no, subject, name, regdate, hit, rownum as num "
			+ "FROM (SELECT no, subject, name, regdate, hit "
			+ "FROM springDataBoard ORDER BY no DESC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<DataBoardVO> dataBoardList(Map map);
	
	@Insert("INSERT INTO springDataBoard VALUES("
			+ "sdb_no_seq.nextval,#{name},#{subject},#{content},#{pwd},SYSDATE,0,"
			+ "#{filename},#{filesize},#{filecount})")
	public void dataBoardInsert(DataBoardVO vo);
	
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM springDataBoard")
	public int databoardTotalPage();
	
	@Update("UPDATE springDataBoard SET "
			+ "hit=hit+1 "
			+ "WHERE no=#{no}")
	public void dataBoardHitIncrement(int no);
	
	@Select("SELECT no, subject, name,content, TO_CHAR(regdate,'YYYY-MM-DD') as dbday, hit, filecount,"
			+ "filename, filesize "
			+ "FROM springDataBoard WHERE no=#{no}")
	public DataBoardVO databoardDetailData(int no);
	
	@Select("SELECT pwd FROM springDataBoard "
			+ "WHERE no=#{no}")
	public String databoardGetPassword(int no);
	
	@Update("UPDATE springDataBoard SET "
			+ "name=#{name},subject=#{subject},content=#{content} "
			+ "WHERE no=#{no}")
	public void databoardUpdate(DataBoardVO vo);
	
	@Delete("DELETE FROM springDataBoard WHERE no=#{no}")
	public void databoardDelete(int no);
	
	@Select("SELECT filename,filecount "
			+ "FROM springDataBoard "
			+ "WHERE no=#{no}")
	public DataBoardVO databoardFileInfoData(int no);
	
//	@Select("SELECT content FROM springDataBoard "
//			+ "WHERE name=#{name}")
//	public List<String> databoardContentData(String name);
	
}

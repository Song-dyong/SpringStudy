package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.DataBoardMapper;
import com.sist.vo.DataBoardVO;

@Repository
public class DataBoardDAO {
	@Autowired
	private DataBoardMapper mapper;
	
//	@Select("SELECT no, subject, name, TO_CHAR(regdate,'YYYY-MM-DD') as dbday, hit, num "
//			+ "FROM (SELECT no, subject, name, regdate, hit, rownum as num "
//			+ "FROM (SELECT /*+ INDEX_DESC(springDataBoard1 sdb1_no_pk)*/no, subject, name, regdate, hit "
//			+ "FROM springDataBoard1)) "
//			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<DataBoardVO> databoardListData(Map map){
		return mapper.databoardListData(map);
	}
	
//	@Select("SELECT CEIL(COUNT(*)/10.0) FROM springDataBoard1")
	public int databoardTotalPage() {
		return mapper.databoardTotalPage();
	}
	// Sequence만들기 in mybatis => no에 넣을 값을 먼저 설정
//	@SelectKey(keyProperty = "no", resultType = int.class, before = true,
//			statement="SELECT NVL(MAX(no)+1,1) as no FROM springDataBoard1")
//	@Insert("INSERT INTO springDataBoard1(no,name,subject,content,pwd,filename,filesize,filecount) "
//			+ "VALUES(#{no},#{name},#{subject},#{content},#{pwd},#{filename},#{filesize},#{filecount})")
	public void databoardInsert(DataBoardVO vo) {
		mapper.databoardInsert(vo);
	}
	
//	@Select("SELECT no,name,subject,content,hit,TO_CHAR(regdate,'YYYY-MM-DD') as dbday, "
//			+ "filename, filesize, filecount "
//			+ "FROM springDataBoard1 "
//			+ "WHERE no=#{no}")
	public DataBoardVO databoardDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.databoardDetailData(no);
	}
	
//	@Select("SELECT no, subject, name, TO_CHAR(regdate,'YYYY-MM-DD') as dbday, hit "
//			+ "FROM springDataBoard1 "
//			+ "WHERE ${fs} LIKE '%'||#{ss}||'%'")
	public List<DataBoardVO> databoardFindData(Map map){
		return mapper.databoardFindData(map);
	}
	// update
	public DataBoardVO databoardUpdateData(int no) {
		return mapper.databoardDetailData(no);
	}
	
//	@Select("SELECT pwd from springDataBoard1 WHERE no=#{no}")
//	public String databoardGetPassword(int no);
//	@Update("UPDATE springDataBoard1 SET "
//			+ "name=#{name}, subject=#{subject}, content=#{content} "
//			+ "WHERE no=#{no}")
	public boolean databoardUpdate(DataBoardVO vo) {
		boolean bCheck=false;
		String db_pwd=mapper.databoardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd())) {
			bCheck=true;
			mapper.databoardUpdate(vo);
		}
		return bCheck;
	}
//	@Delete("DELETE FROM springDataBoard1 WHERE no=#{no}")
	// MyBatis는 매개변수 1개 고정 / 사용자 정의 DAO는 매개변수의 갯수가 여러 개여도 상관 없음
	// 이 DAO는 Controller에서 처리될 예정이므로, 모델에서 pwd를 받아서 값을 처리한 뒤 사용 가능
	// mapper에서 만들어둔 마이바티스 인터페이스에 들어가는 no를 여기서 처리
	public boolean databoardDelete(int no, String pwd) {
		boolean bCheck=false;
		String db_pwd=mapper.databoardGetPassword(no);
		if(db_pwd.equals(pwd)) {
			bCheck=true;
			mapper.databoardDelete(no);
		}
		return bCheck;
	}
}

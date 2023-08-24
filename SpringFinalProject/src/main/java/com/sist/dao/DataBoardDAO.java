package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.DataBoardMapper;
import com.sist.vo.DataBoardVO;

@Repository
public class DataBoardDAO {
	@Autowired
	private DataBoardMapper mapper;
	
	public List<DataBoardVO> dataBoardList(Map map){
		return mapper.dataBoardList(map);
	}
	
	public void dataBoardInsert(DataBoardVO vo) {
		mapper.dataBoardInsert(vo);
	}
	
	public int databoardTotalPage() {
		return mapper.databoardTotalPage();
	}
	
	public DataBoardVO databoardDetailData(int no) {
		mapper.dataBoardHitIncrement(no);
		return mapper.databoardDetailData(no);
	}
	
	public DataBoardVO databoardUpdateData(int no) {
		return mapper.databoardDetailData(no);
	}
	
	public String databoardGetPassword(int no) {
		return mapper.databoardGetPassword(no);
	}
	
	public String databoardUpdate(DataBoardVO vo) {
		String result="";
		String db_pwd=mapper.databoardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd())) {
			result="yes";
			mapper.databoardUpdate(vo);
		}else {
			result="no";
		}
		
		return result;
	}
	
	public String databoardDelete(int no,String pwd) {
		String result="";
		String db_pwd=mapper.databoardGetPassword(no);
		if(db_pwd.equals(pwd)) {
			result="yes";
			mapper.databoardDelete(no);
		}else {
			result="no";
		}
		return result;
	}
	
	public DataBoardVO databoardFileInfoData(int no) {
		return mapper.databoardFileInfoData(no);
	}
	
//	public List<String> databoardContentData(String name){
//		return mapper.databoardContentData(name);
//	}
}

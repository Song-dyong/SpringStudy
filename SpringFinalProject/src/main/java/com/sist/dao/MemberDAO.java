package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.MemberMapper;

@Repository
public class MemberDAO {
	@Autowired
	private MemberMapper mapper;
	
//	@Select("SELECT COUNT(*) FROM springMember "
//			+ "WHERE id=#{id}")
	public int memberIdCheck(String id) {
		return mapper.memberIdCheck(id);
	}
}	

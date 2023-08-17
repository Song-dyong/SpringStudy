package com.sist.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.MemberMapper;
import com.sist.vo.MemberVO;

@Repository
public class MemberDAO {
	@Autowired
	private MemberMapper mapper;
	
//	@Select("SELECT COUNT(*) FROM springMember "
//			+ "WHERE id=#{id}")
	public int memberIdCheck(String id) {
		return mapper.memberIdCheck(id);
	}
	
//	@Select("SELECT COUNT(*) FROM springMember "
//			+ "WHERE email=#{email}")
	public int memberEmailCheck(String email) {
		return mapper.memberEmailCheck(email);
	}
	
//	@Insert("INSERT INTO springMember VALUES("
//			+ "#{id},#{pwd},#{name},#{sex},#{birthday},#{email},#{post},#{addr1},#{addr2},#{phone}"
//			+",#{cnotent},'n',SYSDATE)")
	public void memberInsert(MemberVO vo) {
		mapper.memberInsert(vo);
	}
	
//	@Select("SELECT pwd, name, role FROM springMember "
//			+ "WHERE id=#{id}")
	public MemberVO memberInfoData(String id) {
		return mapper.memberInfoData(id);
	}
	
}	

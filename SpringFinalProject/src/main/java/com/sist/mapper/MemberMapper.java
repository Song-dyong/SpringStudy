package com.sist.mapper;

import org.apache.ibatis.annotations.Select;

public interface MemberMapper {
	@Select("SELECT COUNT(*) FROM springMember "
			+ "WHERE id=#{id}")
	public int memberIdCheck(String id);
}

package com.sist.dao;
import java.util.*;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class StudentDAO extends SqlSessionDaoSupport{
	// 목록 읽기
	/*
					메소드명					리턴	데이터형
	<select id="studentListData" resultType="StudentVO">
		SELECT * from student
	</select>
	*/
	public List<StudentVO> studentListData(){
		return getSqlSession().selectList("studentListData");
	}
	// 상세 보기 
	/*
	<select id="studentDetailData" resultType="StudentVO" parameterType="int">
		SELECT * from student 
		WHERE hakbun=#{hakbun}
	</select>
	*/
	public StudentVO studentDetailData(int hakbun) {
		return getSqlSession().selectOne("studentDetailData",hakbun);
	}
	// 검색
	public List<StudentVO> studentFindData(String name){
		return getSqlSession().selectList("studentFindData",name);
	}
	// 추가
	public void studentInsert(StudentVO vo) {
		getSqlSession().insert("studentInsert",vo);
	}
	// 수정
	public void studentUpdate(StudentVO vo) {
		getSqlSession().update("studentUpdate",vo);
	}
	// 삭제
	public void studentDelete(int hakbun) {
		getSqlSession().delete("studentDelete", hakbun);
	}
}

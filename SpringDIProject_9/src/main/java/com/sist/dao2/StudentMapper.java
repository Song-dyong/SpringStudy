package com.sist.dao2;
import java.util.*;
// XML , 어노테이션 SQL 작성법
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
public interface StudentMapper {
	/*
		<select id="studentListData" resultType="StudentVO">
			SELECT * from student
		</select>
	*/
	@Select("SELECT * from student")
	public List<StudentVO> studentListData();
/*	
	<select id="studentDetailData" resultType="StudentVO" parameterType="int">
		SELECT * from student 
		WHERE hakbun=#{hakbun}
	</select>
*/
	@Select("SELECT * from student " + 
			"WHERE hakbun=#{hakbun}")
	public StudentVO studentDetailData(int hakbun);
	
	/*
		<select id="studentFindData" resultType="StudentVO" parameterType="String">
			SELECT * FROM student
			WHERE name LIKE '%'||#{name}||'%'
		</select>
	*/
	@Select("SELECT * FROM student " + 
			"WHERE name LIKE '%'||#{name}||'%'")
	public List<StudentVO> studentFindData(String name);
	
	
	@Update("UPDATE student SET name=#{name},kor=#{kor},eng=#{eng},math=#{math} WHERE hakbun=#{hakbun}")
	public void studentUpdate(StudentVO vo);
	
	@Delete("DELETE FROM student WHERE hakbun=#{hakbun}")
	public void studentDelete(int hakbun);
	
	/*
	<insert id="studentInsert" parameterType="StudentVO">
		<selectKey keyProperty="hakbun" order="BEFORE" resultType="int">
		<!-- Sequence 작성 => seq.nextval() / subQuery / mapper에서 처리 -->
			SELECT NVL(max(hakbun)+1,1) as hakbun FROM student 
		</selectKey>
		<!-- getHakbun()... 자동 처리 -->
		INSERT INTO student VALUES(
			#{hakbun},
			#{name},
			#{kor},
			#{eng},
			#{math}
		)
	</insert>
	*/
	@SelectKey(keyProperty = "hakbun" , resultType = int.class, before = true , 
			statement = "SELECT NVL(max(hakbun)+1,1) as hakbun FROM student")
	@Insert("INSERT INTO student VALUES(#{hakbun},#{name},#{kor},#{eng},#{math})")
	public void studentInsert(StudentVO vo);
}

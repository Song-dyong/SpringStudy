package com.sist.vo;
import java.util.*;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataBoardVO {
	private int no,hit,filecount;
	private String name,subject,content,pwd,filename,filesize, dbday;
	private Date regdate;
	private List<MultipartFile> files;	// 업로드된 파일
	/*
		<input type="file" name="files[0]">	
		=> files[0]을 통해 배열로 받지 않으면 bad request(400) => 매개변수 오류
	*/
}

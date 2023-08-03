package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.vo.*;
import com.sist.dao.DataBoardDAO;

@Controller
public class DataBoardController {
	@Autowired
	private DataBoardDAO dao;
	// 목록 출력
	@GetMapping("databoard/list.do")
	public String databoard_list(String page, Model model) {
		if(page==null)
			page="1";
		int curpage = Integer.parseInt(page);
		int rowSize = 10;
		int start = (rowSize*curpage)-(rowSize-1);
		int end = rowSize*curpage;
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<DataBoardVO> list = dao.databoardListData(map);
		int totalpage = dao.databoardTotalPage();
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("main_jsp", "../databoard/list.jsp");
		return "main/main";
	}
	// 데이터 추가
	@GetMapping("databoard/insert.do")
	public String databoard_insert(Model model) {
		
		model.addAttribute("main_jsp","../databoard/insert.jsp");
		return "main/main";
	}
	
	@PostMapping("databoard/insert_ok.do")
	public String databoard_insert_ok(DataBoardVO vo) {
		
		List<MultipartFile> list=vo.getFiles();
		if(list==null) {	// file을 업로드하지 않은 경우
			vo.setFilename("");
			vo.setFilesize("");
			vo.setFilecount(0);
		}else {				// file을 업로드한 경우
			String filenames="";
			String filesizes="";
			for(MultipartFile mf:list) {
				File file=new File("/Users/dyongsong/Desktop/testUpload/"+mf.getOriginalFilename());
				try {
					mf.transferTo(file);	// 파일 업로드하기
				} catch (Exception e) {
					e.printStackTrace();
				}
				filenames+=file.getName()+",";
				long len=file.length();
				filesizes+=len+",";
			}
			filenames=filenames.substring(0,filenames.lastIndexOf(","));
			filesizes=filesizes.substring(0,filesizes.lastIndexOf(","));
			vo.setFilecount(list.size());
			vo.setFilename(filenames);
			vo.setFilesize(filesizes);
		}
		
		dao.databoardInsert(vo);
		return "redirect:../databoard/list.do";
	}
	
	// 요청 데이터가 없는 경우, String으로 설정
	// 모든 데이터는 String으로 받을 수 있다.
	// 매개변수는 순서가 없음, 여러 개를 동시에 받을 수 있음 
	/*
	 
	 	MVC의 핵심
	 		
	 
		1) Model(Controller) => 매개변수 설정
			매개변수 : 사용자가 보내준 값을 받는다.
				1| 일반 데이터 (int, String..)
				2| 데이터를 모아서 처리 (~VO 커맨드 객체)
				3| CheckBox => String[] 배열
			
				
				내장 객체
				-------
				HttpServletRequest , HttpServletResponse
				----------------------------------------
					=> 다운로드 , Cookie 사용
				HttpSession, Model, RedirectAttributes , PasswordEncoder
		
		DAO : 데이터베이스 연동
		Controller : 조립기 (DAO 연동 => 데이터 추출 => 브라우저 전송)
		VO : 관련된 데이터를 모아서 관리 (사용자 정의 데이터형)
		Manager : OpenAPI
		RestController : JavaScript로 JSON을 전송할 목적
		--------------- 다른 언어와 연결 => 데이터 전송
						1. WEB => JavaScript
						2. Mobile => Kotlin
			
	*/
	@GetMapping("databoard/detail.do")
	public String databoard_detail(int no, Model model) {
		
		DataBoardVO vo=dao.databoardDetailData(no);
		if(vo.getFilecount()>0) {
			/*
			 	저장된 파일 이름과 사이즈를 동시에 하나씩 출력할 수 없기 때문에,
			 	각자 리스트에 저장한 뒤, 인덱스 번호를 통해서 매칭
			*/
			String filenames=vo.getFilename();
			StringTokenizer st=new StringTokenizer(filenames,",");
			List<String> nList=new ArrayList<String>();
			while(st.hasMoreTokens()) {
				nList.add(st.nextToken());
			}
			String filesizes=vo.getFilesize();
			st=new StringTokenizer(filesizes,",");
			List<String> sList=new ArrayList<String>();
			while(st.hasMoreTokens()) {
				sList.add(st.nextToken());
			}
			
			model.addAttribute("nList",nList);
			model.addAttribute("sList",sList);
		}
		
		model.addAttribute("vo",vo);
		model.addAttribute("main_jsp","../databoard/detail.jsp");
		return "main/main";
	}
	// 다운로드는 화면 변경이 없기 때문에, void
	@GetMapping("databoard/download.do")
	public void databoardDownload(String fn, HttpServletResponse response) {
		try {
			File file=new File("/Users/dyongsong/Desktop/testUpload/"+fn);
			// 다운로드 창 띄우기
			response.setHeader("Content-Disposition", "attachement;filename="+
					URLEncoder.encode(fn,"UTF-8"));
			// DOWNLOAD
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
			// 서버에서 파일 읽기
			BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
			// 사용자에게 전송
			int i=0;	// 읽은 바이트 수
			byte[] buffer=new byte[1024];	// TCP(1024) / UDP(512)
			
			while((i=bis.read(buffer,0,1024))!=-1) {
				bos.write(buffer,0,i);
			}
			bis.close();
			bos.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@PostMapping("databoard/find.do")
	public String databoard_find(String fs, String ss, Model model) {
		
		// DAO 연결
		Map map=new HashMap();
		map.put("fs",fs);
		map.put("ss",ss);
		List<DataBoardVO> list=dao.databoardFindData(map);
		
		model.addAttribute("list",list);
		model.addAttribute("count",list.size());
		model.addAttribute("main_jsp","../databoard/find.jsp");
		return "main/main";
	}
	
	@GetMapping("databoard/update.do")
	public String databoard_update(int no, Model model) {
		
		// DAO 연동
		DataBoardVO vo=dao.databoardUpdateData(no);
		
		model.addAttribute("vo",vo);
		model.addAttribute("main_jsp","../databoard/update.jsp");
		return "main/main";
	}
	
	@PostMapping("databoard/update_ok.do")
	@ResponseBody	//	원하는 자바크립트 전송 가능 => 승격 => @RestController
	// responsebody => 원하는 데이터만 전송 가능  // JSP 안에서 처리하던 내용을 자바 내에서 처리 가능 responsebody
	public String databoard_update_ok(DataBoardVO vo) {
		String result="";
		// DAO 연동
		boolean bCheck=dao.databoardUpdate(vo);
		if(bCheck==true) {
			result="<script>"
					+ "location.href=\"../databoard/detail.do?no="+vo.getNo()+"\";"
					+ "</script>";
		}else {
			result="<script>"
					+ "alert(\"비밀번호가 다릅니다.\");"
					+ "history.back();"
					+ "</script>";
		}
		return result;
	}
	
	// 삭제하는 창으로 이동
	@GetMapping("databoard/delete.do")
	public String databoard_delete(int no, Model model) {
		// session은 사이트 전체의 전역변수 / no와 같은 request는 지역변수에 해당한다.
		// session은 서버 외부에 저장된 데이터 (브라우저 자체에 저장)
		/*
			브라우저 내의 모든 화면은 dispatcherServlet의 화면을 띄우는 것
			=> .do로 끝나는 모든 파일을 불렀을 때, DispatcherServlet 위에 화면을 덮어씌운다.
						< forward기법 >
		*/
		model.addAttribute("no",no);
		model.addAttribute("main_jsp","../databoard/delete.jsp");
		return "main/main";
	}
	// 실제 삭제	=>	@ResponseBody (@RestController) 메소드에서 클래스로 승격
	
	@PostMapping("databoard/delete_ok.do")
	@ResponseBody
	public String databoard_delete_ok(int no, String pwd) {
		String result="";
		// DAO 연동
		boolean bCheck=dao.databoardDelete(no, pwd);
		if(bCheck==true) {
			result="<script>"
					+ "location.href=\"../databoard/list.do\";"
					+ "</script>";
		}else {
			result="<script>"
					+ "alert(\"비밀번호가 다릅니다.\");"
					+ "history.back();"
					+ "</script>";
		}
		return result;
	}
	
}

package com.sist.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.snu.ids.ha.index.Keyword;
import org.snu.ids.ha.index.KeywordExtractor;
import org.snu.ids.ha.index.KeywordList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.sist.dao.*;
import com.sist.vo.*;


@Controller
public class DataBoardController {

	@Autowired
	private DataBoardDAO dao;
	
	@GetMapping("databoard/list.do")
	public String databoard_list(Model model) {
		model.addAttribute("main_jsp","../databoard/list.jsp");
		return "main/main";
	}
	
	@GetMapping("databoard/insert.do")
	public String databoard_insert(Model model){
		model.addAttribute("main_jsp","../databoard/insert.jsp");
		return "main/main";
	}
	
	@GetMapping("databoard/detail.do")
	public String databoard_detail(int no,Model model){
		DataBoardVO vo=dao.databoardDetailData(no);
		String keyword=vo.getContent();
		KeywordExtractor ke=new KeywordExtractor();
		KeywordList list=ke.extractKeyword(keyword, true);
		List<WordVO> wList=new ArrayList<WordVO>();
		for(int i=0;i<list.size();i++) {
			Keyword wrd=list.get(i);
			if(wrd.getCnt()>1 ) {
				WordVO wvo=new WordVO();
				wvo.setCount(wrd.getCnt());
				wvo.setWord(wrd.getString());
				wList.add(wvo);
			}
		}
		model.addAttribute("wList",wList);
		model.addAttribute("no",no);
		model.addAttribute("main_jsp","../databoard/detail.jsp");
		return "main/main";
	}
	
	@GetMapping("databoard/download.do")
	public void databoard_download(String fn,HttpServletResponse response, HttpServletRequest request){
		String path=request.getSession().getServletContext().getRealPath("/")+"upload\\";
		path=path.replace("\\", File.separator);
		try {
			File file=new File(path+fn);
			// 다운로드 창 띄우기
			response.setHeader("Content-Disposition", "attachement;filename="
					+URLEncoder.encode(fn,"UTF-8"));
			response.setContentLength((int)file.length());
			// 서버에서 값 읽어오기
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
			// 사용자의 저장 위치
			BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
			int i=0;
			byte[] buffer=new byte[1024];
			while((i=bis.read(buffer,0,1024))!=-1) {
				bos.write(buffer,0,i);
			}
			bis.close();
			bos.close();
		} catch (Exception e) {}
	}
	
	@GetMapping("databoard/update.do")
	public String databoardUpdate(int no,Model model) {
		model.addAttribute("no",no);
		model.addAttribute("main_jsp","../databoard/update.jsp");
		return "main/main";
	}
	
	@GetMapping("databoard/delete.do")
	public String databoardDelete(int no,Model model) {
		model.addAttribute("no",no);
		model.addAttribute("main_jsp","../databoard/delete.jsp");
		return "main/main";
	}
}

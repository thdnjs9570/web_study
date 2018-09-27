package com.gura.spring03.cafe.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring03.cafe.dao.CafeCommentDao;
import com.gura.spring03.cafe.dao.CafeDao;
import com.gura.spring03.cafe.dto.CafeCommentDto;
import com.gura.spring03.cafe.dto.CafeDto;

@Service
public class CafeServiceImpl implements CafeService{

	//한 페이지에 나타낼 로우의 갯수
	private static final int PAGE_ROW_COUNT=5;
	//하단 디스플레이 페이지 갯수
	private static final int PAGE_DISPLAY_COUNT=3;	
	
	@Autowired
	private CafeDao dao; 
	
	@Autowired
	private CafeCommentDao commentdao; 
	
	@Override
	public void getList(ModelAndView mView, int pageNum) {
		//보여줄 페이지 데이터의 시작 ResultSet row 번호
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//보여줄 페이지 데이터의 끝 ResultSet row 번호
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		
		CafeDto dto = new CafeDto();///////?????????????????????여기서 생성?
		
		//전체 row 의 갯수를 읽어온다.
		int totalRow= dao.getCount(dto);
		//전체 페이지의 갯수 구하기
		int totalPageCount=
				(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		//시작 페이지 번호
		int startPageNum=
			1+((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		//끝 페이지 번호가 잘못된 값이라면 
		if(totalPageCount < endPageNum){
			endPageNum=totalPageCount; //보정해준다. 
		}
		
		
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
		
		List<CafeDto> list= dao.getList(dto);
		
		mView.addObject("list",list);
		// 페이징 처리에 관련된 값도 request 에 담기 
		mView.addObject("pageNum",pageNum);
		mView.addObject("startPageNum",startPageNum);
		mView.addObject("endPageNum",endPageNum);
		mView.addObject("totalPageCount",totalPageCount);
			
	}
	
	
	@Override
	public void insert(HttpServletRequest request, CafeDto dto) {
		
		String writer = (String)request.getSession().getAttribute("id");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		dto.setWriter(writer);
		dto.setTitle(title);
		dto.setContent(content);
		
		dao.insert(dto);
	}

	@Override
	public void delete(HttpServletRequest request, int num) {
		dao.delete(num);
	}

	@Override
	public void update(HttpServletRequest request,ModelAndView mView, CafeDto dto) {
		boolean isSuccess = true;
		dao.update(dto);
		mView.addObject("isSuccess",isSuccess);
	}
	
	
	@Override
	public void detail(ModelAndView mView, int num, String keyword, String condition,HttpSession session) {
		
		CafeDto dto = new CafeDto();
		
		if(keyword.equals(""))keyword=null;
	    if(condition.equals(""))condition=null;
	     
		if(keyword != null) {
			if(condition.equals("titlecontent")) {//제목+내용 검색
				dto.setTitle(keyword);
				dto.setContent(keyword);
				
			}else if(condition.equals("title")) {//제목 검색
				dto.setTitle(keyword);
				
			}else if(condition.equals("writer")) {//작성자 검색
				dto.setWriter(keyword);
			}
			//list.jsp 에서 필요한 내용 담기
			mView.addObject("condition",condition);
			mView.addObject("keyword",keyword);
		}
		
		//글 번호도 dto에 담는다.
		dto.setNum(num);
		
		CafeDto resultDto = dao.getData(dto);
		
		dao.addViewCount(num);
		
		mView.addObject("dto",resultDto);
		
		//String id = (String)request.getSession().getAttribute("id");
		String id = (String) session.getAttribute("id");
		
		boolean isLogin = false;
		if(id != null) {
			isLogin = true;
		}
		
		mView.addObject("isLogin",isLogin);
		
		List<CafeCommentDto> CommentList = new ArrayList<>(); 
		
		CommentList = commentdao.getList(num);//댓글 정보 받아옴
		
		mView.addObject("CommentList",CommentList);
		
	}
	
	
}

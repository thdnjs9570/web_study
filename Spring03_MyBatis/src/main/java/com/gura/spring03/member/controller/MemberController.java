package com.gura.spring03.member.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring03.member.dao.MemberDao;
import com.gura.spring03.member.dto.MemberDto;
import com.gura.spring03.member.service.MemberService;

@Controller
public class MemberController {
	//의존 객체 
	@Autowired //관리하는 객체중에서 혹시 밑에 써져있는게 있으면 넣어주라!
	//class MemberServiceImpl implements MemberService{} bean으로 만들어놓았음 그래서 찾을 수 있다 
	
	//MemberServiceImpl는 @Service라고 씀으로서 bean으로 만든겨
	private MemberService mService;
	
	//회원 추가 요청 처리
//	@RequestMapping("/member/insert")
//	public String insert(HttpServletRequest request) {
//		String name=request.getParameter("name");
//		String addr=request.getParameter("addr");
//		MemberDto dto=new MemberDto();
//		dto.setName(name);
//		dto.setAddr(addr);
//		
//		mService.insert(dto);
//		
//		return "redirect:/member/list.do";
//	}
//	@RequestMapping("/member/insert")
//	public String insert(@RequestParam String name, 
//				@RequestParam String addr) {
//		MemberDto dto=new MemberDto();
//		dto.setName(name);
//		dto.setAddr(addr);
//		
//		mService.insert(dto);
//		
//		return "redirect:/member/list.do";
//	}
	
	@RequestMapping("/member/insert")
	public String insert(@ModelAttribute MemberDto dto) {
		/*
		 	회원정보가 담긴 MemberDto 객체를 MeberService 객체를 이용해서 DB에 저장하기
		 */
		mService.insert(dto);
		
		return "member/insert";
	}
	
	//회원 추가 폼 요청 처리
	@RequestMapping("/member/insertform")
	public String insertForm() {
		return "member/insertform";
	}
	
	@RequestMapping("/member/delete")
	public String delete(@RequestParam int num) {
		mService.delete(num);
		return "redirect:/member/list.do";

	}
	
	@RequestMapping("/member/updateform")
	public ModelAndView updateform(@RequestParam int num) {
		
		ModelAndView mView = new ModelAndView();
		
		mService.getData(mView, num);//service 에 전달하면서 mView 에 
									//정보를 담아달라고 하는거임 
		
		mView.setViewName("member/updateform");
		return mView;//그리고 정보가 담겨지 mView를 넘겨줌 
					//그러면 updatform.jsp에서 그냥 ${dto.num}이렇게 빼서 쓸수있음
	}
	
	
	@RequestMapping("/member/update")
	public String update(@ModelAttribute MemberDto dto) {
		
		mService.update(dto);
		
		return "redirect:/member/list.do";
	}
	
	
	
	@RequestMapping("/member/list")
	public ModelAndView list() {
		ModelAndView mView=new ModelAndView();
		//MemberService 객체를 이용해서 비즈니스 로직 처리하고 
		mService.list(mView);
		
		// 뷰 페이지의 정보 설정 
		mView.setViewName("member/list");
		return mView;
	}
	
	
}


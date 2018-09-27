package com.gura.spring03.users.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring03.users.dto.UsersDto;
import com.gura.spring03.users.service.UsersServiceImpl;


@Controller
public class UsersController {

	@Autowired
	private UsersServiceImpl uService;
	
	@RequestMapping("/users/delete")
	public ModelAndView authDelete(HttpServletRequest request, ModelAndView mView) {
		uService.delete(mView,request.getSession());
		
		mView.setViewName("users/delete");
		return mView;
	}
	
	
	
	//비밀번호 수정 반영하는 요청 처리
	@RequestMapping("/users/pw_update")
	public ModelAndView authoPwUpdate(HttpServletRequest request, @RequestParam String pwd, HttpSession session) {
		uService.updatePwd(pwd,session);
		return new ModelAndView("redirect:/users/info.do");
	}
	
	
	
	@RequestMapping("/users/pwd_check")
	@ResponseBody
	public Map<String,Object> pwCheck(@RequestParam String inputPwd,HttpSession session){
		
		boolean result = uService.isValidPwd(inputPwd,session);
		
		Map<String,Object> map = new HashMap<>();
		map.put("isValid", result);
		
		return map;
	}
	
	@RequestMapping("/users/pw_changeform")
	public ModelAndView authPwUpdateForm(HttpServletRequest request) {
	
		
		return new ModelAndView("users/pw_changeform");
	}
	
	
	@RequestMapping("/users/update")
	public ModelAndView authUpdate(HttpServletRequest request,@ModelAttribute UsersDto dto) {
		
		uService.update(dto);
		
		//ModelAndView mView = new ModelAndView("redirect:/users/info.do");
		//mView.setViewName("redirect:/users/info.do");//위처럼 바로 담아도 됨!
		//return mView;
		
		//위에 세줄을 한줄로 줄인것!!
		return new ModelAndView("redirect:/users/info.do");
	}
	
	
	
	
	@RequestMapping("/users/updateform")
	public ModelAndView authUpdateform(HttpServletRequest request,HttpSession session) {
		
		ModelAndView mView = new ModelAndView();
		uService.updateForm(mView,session);
		
		mView.setViewName("users/update_form");
		
		return mView;
	}
	
	
	@RequestMapping("/users/info")  //aop에서 request 필요함
	public ModelAndView authinfo(HttpServletRequest request,HttpSession session) {//저 앞에 인자는 aop에서 필요
		ModelAndView mView = new ModelAndView();
		uService.info(mView,session);
		
		mView.setViewName("users/info");
		return mView;
	}
	
	
	//로그아웃 요청 처리
	@RequestMapping("/users/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "users/logout";
	}
	
	//로그인 폼 요청 처리
	@RequestMapping("/users/login_form")
	public ModelAndView login_form(HttpServletRequest request) {
		
		//로그인 후 이동할 url 주소 받아오기
		String url = request.getParameter("url");//이 url 은 어디서 받아옴?아 만약에 이쪽으로 보내고 싶으면 login_form 으로 보낼때 url 전송
		if(url == null) {
			url = request.getContextPath()+"/";//아무도 안보냈으면 그냥 이걸로
		}
		ModelAndView mView = new ModelAndView();
		mView.addObject("url",url);//model도 담을수있고
		mView.setViewName("users/login_form");//view도 담을수있다
		
		return mView;
	}

	
	@RequestMapping("users/login")//url ==> login_form 에서 보내준 url 즉 login 후 이동할 url
	public ModelAndView login(@ModelAttribute UsersDto dto,@RequestParam String url,HttpSession session,HttpServletRequest request) {
		ModelAndView mView = new ModelAndView();
		uService.login(mView,dto,session);
		
		mView.addObject("url",url);//로그인 후 이동할 url 은 달고댕겨야함
		
		mView.setViewName("users/login");
		
		return mView;
	}
	
	
	@RequestMapping("/users/signup_form")
	public String signup_form() {
		return "users/signup_form3";
	}
	
	//회원가입 요청 처리
	@RequestMapping("/users/signup")
	public ModelAndView signup(@ModelAttribute UsersDto dto) {//근데 ModelAndView mView라고 인자에 선언만해도 알아서 해줌 
		ModelAndView mView = new ModelAndView();
		uService.signup(mView, dto);
		
		mView.setViewName("users/signup");
		return mView; //여기에는 msg 정보까지 담겨져있다 (Service 에서 담아둔) 
	}
	
	
	
	//아이디 중복 확인 ajax 요청에 대한 응답 이건 json 응답으로 처리해준다 
	//이렇게 하기 위해서 하는 단계
	//1. pom.xml 에 jackson 라이브러리 추가
	//2. @ResponseBody 어노테이션
	//3. {} : map or dto 리턴
	//   [] : List 리턴
	
	@RequestMapping("users/checkid")
	@ResponseBody
	public Map<String,Object> checkid(@RequestParam String inputId) {
		//서비스 객체를 이용해서 사용가능 여부를 boolean type
		//으로 리턴 받는다.
		
		//Map<String,Object> map = new //갑자기 map? jackson라이브러리 이용해서 ajax로 응답해야해서
		boolean canUse = uService.canUseId(inputId);
		
		Map<String,Object> map = new HashMap<>();
		map.put("canUse", canUse);//이렇게 하면 {"canUse":true},{"canUse":false} 이 둘 중에 하나가 응답이 된다.
		//자동으로 responseData 함수에 받아짐
		
		return map;
	}
	
	
}

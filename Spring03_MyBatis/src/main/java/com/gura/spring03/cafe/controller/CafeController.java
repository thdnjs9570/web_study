package com.gura.spring03.cafe.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring03.cafe.dto.CafeCommentDto;
import com.gura.spring03.cafe.dto.CafeDto;
import com.gura.spring03.cafe.service.CafeCommentService;
import com.gura.spring03.cafe.service.CafeService;

@Controller
public class CafeController {

	@Autowired
	private CafeService cService;
	
	@RequestMapping("/cafe/update")
	public ModelAndView authUpdate(HttpServletRequest request,ModelAndView mView,@ModelAttribute CafeDto dto) {
		cService.update(request,mView,dto);
		return new ModelAndView("redirect:/cafe/list.do");
	}
	

	@RequestMapping("/cafe/updateform")
	public ModelAndView authUpdate_form(HttpServletRequest request,ModelAndView mView,@RequestParam int num) {
		return new ModelAndView("cafe/update_form");
	}
	
	@RequestMapping("/cafe/detail")
	public ModelAndView detail(ModelAndView mView,@RequestParam int num ,@RequestParam(defaultValue="") String keyword,@RequestParam(defaultValue="") String condition,HttpSession session) {
		cService.detail(mView,num,keyword,condition,session);
		mView.setViewName("cafe/detail");
		return mView;
	}
	
	@RequestMapping("/cafe/list")
	public ModelAndView list(ModelAndView mView, @RequestParam(defaultValue = "1") int pageNum) {
		
		cService.getList(mView, pageNum);
		
		mView.setViewName("cafe/list");

		return mView;
	}
	
	@RequestMapping("/cafe/insertform")
	public ModelAndView authInsertform(HttpServletRequest request) {
		return new ModelAndView("cafe/insert_form");
	}
	
	@RequestMapping("/cafe/insert")
	public ModelAndView authInsert(HttpServletRequest request,@ModelAttribute CafeDto dto) {
		cService.insert(request, dto);
		return new ModelAndView("redirect:/cafe/list.do");
	}
	
	
	@RequestMapping("/cafe/delete")
	public ModelAndView authDelete(HttpServletRequest request,@RequestParam int num ) {
		cService.delete(request, num);
		return new ModelAndView("redirect:/cafe/list.do");
	}
	
	

	
}

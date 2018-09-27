package com.gura.spring03.cafe.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring03.cafe.dto.CafeCommentDto;
import com.gura.spring03.cafe.dto.CafeDto;
import com.gura.spring03.cafe.service.CafeCommentService;

@Controller
public class CafeCommentController {
	
	@Autowired
	private CafeCommentService commentService;

	
	@RequestMapping("/cafe/comment_insert")
	public ModelAndView authCfommentInsert(HttpServletRequest request,@ModelAttribute CafeCommentDto dto) {
		commentService.insert(dto);//detail 에서 댓글 정보 받은거 가져오기
		
		return new ModelAndView("redirect:/cafe/detail.do?num="+dto.getRef_group());
	}
	
	
	@RequestMapping("/cafe/comment_delete")
	public ModelAndView authCommentDelete(HttpServletRequest request,int num, int num2) {//num == 댓글 번호 num2==글번호
	
		commentService.delete(request,num);
	
		return new ModelAndView("redirect:/cafe/detail.do?num="+num2);
	}
	
	
	

}

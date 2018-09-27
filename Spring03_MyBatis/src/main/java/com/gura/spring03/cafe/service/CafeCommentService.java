package com.gura.spring03.cafe.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.gura.spring03.cafe.dto.CafeCommentDto;

public interface CafeCommentService {

	public void insert(CafeCommentDto dto);
	public void delete(HttpServletRequest request, int num);

}

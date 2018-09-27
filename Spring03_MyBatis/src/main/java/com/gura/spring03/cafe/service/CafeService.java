package com.gura.spring03.cafe.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.gura.spring03.cafe.dto.CafeDto;

public interface CafeService {
	public void getList(ModelAndView mView, int pageNum);
	public void insert(HttpServletRequest request, CafeDto dto);
	public void delete(HttpServletRequest request, int num);
	public void detail(ModelAndView mView,int num ,String keyword,String condition,HttpSession session);
	public void update(HttpServletRequest request,ModelAndView mView, CafeDto dto);
}

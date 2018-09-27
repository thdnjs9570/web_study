package com.gura.spring04.users.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gura.spring04.users.service.*;
import com.gura.spring04.users.dto.UsersDto;

@Controller
public class UsersController {

	@Autowired
	private UsersServiceImpl uService;
	
	
	@RequestMapping("/users/signup_form")
	public String signupForm() {
		return "users/signup_form";
	}
	
	
	@RequestMapping("/users/signup")
	public String insert(@ModelAttribute UsersDto dto) {
		uService.insert(dto);
		return "users/signup";
	}
	
	
	@RequestMapping("/users/login_form")
	public String login_form() {
		return "users/login_form";
	}

	
	@RequestMapping("/users/login")
	public String login(@ModelAttribute UsersDto dto, HttpSession session,HttpServletRequest request) {//로그인 폼에서 받은 parameter 자동으로
		boolean result = uService.isValid(dto);
		if(result == true) {
			session.setAttribute("id", dto.getId());
			request.setAttribute("isLoginSuccess",result);
			return "users/login";
		}
		else {
			return "users_login_form";
		}
		
	}
	
	
}

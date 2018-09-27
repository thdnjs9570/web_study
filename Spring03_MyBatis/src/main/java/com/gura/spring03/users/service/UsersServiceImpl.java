package com.gura.spring03.users.service;

import java.util.Base64.Decoder;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring03.users.dao.UsersDao;
import com.gura.spring03.users.dto.UsersDto;

@Service
public class UsersServiceImpl implements UsersService {

	// 의존 객체 DI
	@Autowired
	private UsersDao dao;

	@Override
	public boolean canUseId(String id) {
		
		boolean result = dao.canUseId(id);
		return result;
		
	}

	// 회원 가입 처리를 하는 서비스 메소드
	@Override
	public void signup(ModelAndView mView, UsersDto dto) {
		// 비밀번호 암호화를 도와주는 객체 생성
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		// UsersDto에 있는 비밀번호를 암호화 한다.
		String encodedPwd = encoder.encode(dto.getPwd());

		// 암호화된 비밀번호를 UsersDto에 다시 담는다.
		dto.setPwd(encodedPwd);

		//////// insert하기 전에 암호화 처리해야함!!!!!!!
		// dao를 이용해서 회원 정보를 저장한다.
		dao.insert(dto);
		// request에 담을 내용을 ModelAndView 객체에 담는다.k
		mView.addObject("msg", dto.getId() + "회원님 가입되었습니다.");
	}

	@Override
	public void login(ModelAndView mView, UsersDto dto, HttpSession session) {

		boolean isLoginSuccess = false;

		// dto == 사용자가 입력한 loginform에서
		UsersDto resultDto = dao.getData(dto.getId());// loginform으로 부터 사용자가 입력한 받은 id를 가지고 db에서 찾은 dto

		// 위에서 뽑은 resultDto가 존재한다면, 해당 아이디가 DB에 존재한다면!!!
		if (resultDto != null) {
			isLoginSuccess = BCrypt.checkpw(dto.getPwd(), resultDto.getPwd());// 같으면 true, 다르면 false 리턴
		}
		if (isLoginSuccess) {
			session.setAttribute("id", dto.getId());
		}
		mView.addObject("isLoginSuccess", isLoginSuccess);

	}

	@Override
	public void info(ModelAndView mView, HttpSession session) {

		String id = (String) session.getAttribute("id");

		UsersDto dto = dao.getData(id);

		// ModelAndView 객체에 담는다.
		mView.addObject("dto", dto);

	}

	@Override
	public void updateForm(ModelAndView mView, HttpSession session) {
		String id = (String) session.getAttribute("id");

		UsersDto dto = dao.getData(id);

		mView.addObject("dto", dto);
	}

	@Override
	public void update(UsersDto dto) {
		// form 으로부터 얻은 dto
		dao.update(dto);
	}

	@Override
	public boolean isValidPwd(String inputPwd, HttpSession session) {

		// 세션 영역에 저장된 아이디 읽어와서
		String id = (String) session.getAttribute("id");

		UsersDto dto = dao.getData(id);
		boolean isValid = BCrypt.checkpw(inputPwd, dto.getPwd());

		return isValid;
	}

	@Override
	public void updatePwd(String pwd, HttpSession session) {

		String id = (String) session.getAttribute("id");

		String encodePwd = new BCryptPasswordEncoder().encode(pwd);

		// UsersDto 객체에 두개의 정보 담기
		UsersDto dto = new UsersDto();
		dto.setId(id);
		dto.setPwd(encodePwd);

		dao.updatePwd(dto);

	}

	@Override
	public void delete(ModelAndView mView, HttpSession session) {
		String id = (String) session.getAttribute("id");
		// DB 에서 해당 정보를 삭제한다.
		dao.delete(id);
		// 로그아웃처리를 한다.
		session.invalidate();
		// ModelAndView 객체에 메세지를 담는다.
		mView.addObject("msg", id + "님 회원 탈퇴 되었습니다.");
	}

}

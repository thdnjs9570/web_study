package com.gura.spring03.users.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring03.users.dto.UsersDto;

@Repository
public class UsersDaoImpl implements UsersDao{

	@Autowired
	private SqlSession session;
	
	//회원 정보를 추가하는 메소드
	@Override
	public void insert(UsersDto dto) {
		session.insert("users.insert",dto);
	}

	//회원 한명의 정보를 id로 select 해서 리턴하는 메소드
	@Override
	public UsersDto getData(String id) {
		UsersDto dto = session.selectOne("users.getData",id);
		return dto;
	}

	@Override
	public void update(UsersDto dto) {
		session.update("users.update",dto);
	}

	@Override
	public void delete(String id) {
		session.delete("users.delete",id);
	}

	
	//사용할 수 있는 아이디 인지 여부 판단
	@Override
	public boolean canUseId(String id) {
		String result = session.selectOne("users.isExist",id);
		if(result == null) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void updatePwd(UsersDto dto) {
		session.update("users.updatePwd",dto);
		
	}

}

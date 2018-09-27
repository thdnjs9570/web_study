package com.gura.spring04.users.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring04.users.dto.UsersDto;


@Repository
public class UsersDaoImpl implements UsersDao{

	@Autowired//컨테이너에서 sqlsession을 찾아서 주입시킨다.
	private SqlSession session;//얘가 있어야지 작업이 된다 
	
	@Override
	public void insert(UsersDto dto) {
		session.insert("users.insert",dto);
	}

	@Override
	public void update(UsersDto dto) {
		session.update("users.update",dto);
	}

	@Override
	public void delete(String id) {
		session.delete("users.delete",id);
	}

	@Override
	public UsersDto getData(String id) {
		UsersDto dto = session.selectOne("users.getData",id);
		return dto;
	}

	@Override
	public List<UsersDto> getList() {
		List<UsersDto> list = session.selectList("users.getList");
		return list;
	}

	
}

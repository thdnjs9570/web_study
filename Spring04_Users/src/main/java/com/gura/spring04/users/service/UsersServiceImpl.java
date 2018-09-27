package com.gura.spring04.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gura.spring04.users.dao.UsersDao;
import com.gura.spring04.users.dto.UsersDto;

@Service
public class UsersServiceImpl implements UsersService{

	@Autowired
	private UsersDao dao;//dao에 의존한다
	
	@Override
	public void insert(UsersDto dto) {
		dao.insert(dto);
	}
	
	@Override
	public void update(UsersDto dto) {
		dao.update(dto);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}
	
	@Override
	public boolean isValid(UsersDto dto) {
		boolean isValid=false;
		UsersDto resultDto=dao.getData(dto.getId());
		if(resultDto != null) {
			if(resultDto.getPwd().equals(dto.getPwd())) {
				isValid=true;
			}
		}
		return isValid;
	}
	
	
}

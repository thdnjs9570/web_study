package com.gura.spring04.users.service;

import com.gura.spring04.users.dto.UsersDto;


public interface UsersService {
	public void insert(UsersDto dto);
	public void update(UsersDto dto);
	public void delete(String id);
	public boolean isValid(UsersDto dto);
}

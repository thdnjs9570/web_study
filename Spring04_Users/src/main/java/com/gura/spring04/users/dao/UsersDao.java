package com.gura.spring04.users.dao;

import java.util.List;

import com.gura.spring04.users.dto.UsersDto;

public interface UsersDao {
	public void insert(UsersDto dto);
	public void update(UsersDto dto);
	public void delete(String id);
	public UsersDto getData(String id);
	public List<UsersDto> getList();

}

package com.gura.spring03.cafe.dao;

import java.util.List;

import com.gura.spring03.cafe.dto.CafeCommentDto;


public interface CafeCommentDao {
	public List<CafeCommentDto> getList(int num); 
	public void insert(CafeCommentDto dto);
	public int getSequence();
	public void delete(int num);
}

package com.gura.spring03.cafe.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring03.cafe.dto.CafeCommentDto;

@Repository
public class CafeCommentDaoImpl implements CafeCommentDao{

	@Autowired
	private SqlSession session;
	
	@Override
	public List<CafeCommentDto> getList(int num) {
		return session.selectList("CafeComment.getList", num);
	}

	@Override
	public void insert(CafeCommentDto dto) {
		session.insert("CafeComment.insert",dto);
	}
	
	@Override
	public int getSequence() {
		return session.selectOne("CafeComment.getSequence");
	}

	@Override
	public void delete(int num) {
		session.delete("CafeComment.delete",num);
	}
	
}

package com.gura.spring03.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring03.member.dto.MemberDto;

//1. MemberDaoImpl 객체가 spring bean 컨테이너에서 관리 되게 한다.
@Repository//controller는 controller붙이고 dao는 respository 붙인다
public class MemberDaoImpl implements MemberDao{
	
	//의존객체 자동 주입
	@Autowired//컨테이너에서 sqlsession을 찾아서 주입시킨다.
	private SqlSession session;//얘가 있어야지 작업이 된다 
	//그리고 close도 할 필요없다 try catch 로 (자동으로 관리해줌) 
	@Override
	public void insert(MemberDto dto) {
		session.insert("member.insert",dto);
	}

	@Override
	public void update(MemberDto dto) {
		session.update("member.update",dto);
	}

	@Override
	public void delete(int num) {
		session.delete("member.delete",num);
	}

	@Override
	public MemberDto getData(int num) {
		MemberDto dto = session.selectOne("member.getData",num);
		return dto;
	}

	@Override
	public List<MemberDto> getList() {
		List<MemberDto> list = session.selectList("member.getList");
		//그냥 이렇게 하면 끝남 간단
		
		return list;
	}

}

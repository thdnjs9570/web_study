package com.gura.spring03.cafe.dao;

import java.util.List;

import com.gura.spring03.cafe.dto.CafeDto;

public interface CafeDao {
	
	public void insert(CafeDto dto);
	public int getCount(CafeDto dto);//키워드 검색이 있는경우에 getCount에 인자를 넣고 아니면 그냥 빈것으로!
	public List<CafeDto> getList(CafeDto dto);//몇페이지의 내용을 출력할지의 정보가 dto에 들어있다. 검색키워드도 들어있으면 거기에 맞게
	public void delete(int num);
	public CafeDto getData(CafeDto dto);//왜 dto를 전달했냐? -> 키워드 검색을 했으면 이전글의 글번호와 다음글의 글번호는 해당 키워드에 맞게 뽑아야함
	public void addViewCount(int num);
	public void update(CafeDto dto);
	
}

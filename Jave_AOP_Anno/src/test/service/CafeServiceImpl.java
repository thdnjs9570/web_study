package test.service;

import org.springframework.stereotype.Component;

@Component//bean으로 만들즈아
public class CafeServiceImpl implements CafeService{

	@Override
	public void insert() {
		System.out.println("카페 글을 추가합니다.");
	}

	@Override
	public void select() {
		System.out.println("카페 글 목록을 불러옵니다.");
	}
	
}

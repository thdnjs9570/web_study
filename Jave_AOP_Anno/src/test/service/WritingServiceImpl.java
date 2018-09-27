package test.service;

import org.springframework.stereotype.Component;

@Component
public class WritingServiceImpl implements WritingService{//스프링에서는 bean에 인터페이스 타입으로 많이씀 왜? 의존성 낮추기 위해

	@Override
	public void write() {
		System.out.println("글쓰기 작업중...");
		try {
			Thread.sleep(2000);
		}catch(Exception e) {};
	}

	@Override
	public void writeToFriend() {
		System.out.println("친구에게 글쓰기 작업중...");
		try {
			Thread.sleep(2000);
		}catch(Exception e) {};
	}

	@Override
	public void writeToTeacher(String name) {//around에서 는 이 메소드를 어디선가 호출할 수 있다 이때 전달된 값을 미리 읽어내서 뭔가 작업을 할 수 있음 
		System.out.println(name+"선생님에게 글쓰기 작업중...");
		try {
			Thread.sleep(2000);
		}catch(Exception e) {};
		
	}

	@Override
	public String writeAndGet(int num) {
		System.out.println(num+"번 글 쓰기 작업하고 문자열을 돌려줍니다.");
		
		return "문자열~";//이게 만약 view라면 조작해서 다른 뷰로 넘길수 있다.
	}
	
	

}

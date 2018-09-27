package test.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.service.WritingService;

public class MainClass2 {
	public static void main(String[] args) {
		
		//spring bean 컨테이너로 부터 WritingService type 객체의 참조값 얻어내기
		ApplicationContext context = new ClassPathXmlApplicationContext("test/main/init.xml");
		
		WritingService service =  context.getBean(WritingService.class);
		
		//service.writeAndGet(9999);
		String result = service.writeAndGet(999);
		
		System.out.println("main클래스에서 writeAndGet 메소드 result : "+result);
		
	}
}

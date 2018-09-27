package test.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.service.WritingService;

public class MainClass3 {
	public static void main(String[] args) {
		
		//spring bean 컨테이너로 부터 WritingService type 객체의 참조값 얻어내기
		ApplicationContext context = new ClassPathXmlApplicationContext("test/main/init.xml");
		
		WritingService service =  context.getBean(WritingService.class);
		
		service.write();
		
		System.out.println("-------------------------");
		
		service.writeToFriend();
		
		System.out.println("-------------------------");
		
		service.writeToTeacher("박소원");
		
		System.out.println("-------------------------");
		
	}
}

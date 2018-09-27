package test.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WritingAspect {
	/*
	 * 접근 지정자 : public
	 * 리턴 type : void
	 * 메소드명 :  write로 시작하는 메소드
	 * 메소드에 전달되는 인자 : 없다
	 * 
	 * 위와 같은 모양의 메소드가 실행되기 이전에 적용되는 Advice
	 * 
	 */
	
	@Before("execution(public void write*(..))")//""안에 있는 이런 모양의 메소드가 실행되는 이전에 밑에 작업을 할거임!
	public void preparePen() {
		System.out.println("[글 쓰기 전에 할 작업 : 펜을 들어요]");
	}
	
	
	/*
	 * 접근 지정자 : 상관없음
	 * 리턴 type : 상관없음
	 * 메소드명 : write로 시작
	 * 메소드에 전달되는 인자 : 없다
	 * 위와 같은 모양의 메소드가 실행된 이후에 적용되는 advice
	 * 
	 */
	@After("execution(* write*())")
	public void endPen() {
		System.out.println("[글 쓴 후에 할 작업 : 펜을 닫아요!]");
	}
	
	
	@Around("execution(* write*(java.lang.String))")//인자는 하나 받음(선생님만)
	public void aroundWrite(ProceedingJoinPoint joinPoint) throws Throwable {
		
		//aop가 적용된 메소드에 전달된 인자를 object[]로 얻어내기 (MainClass2의 writeToTeacher)
		Object[] args = joinPoint.getArgs();//object 배열의 형태로 가져와줌
	
		System.out.println("[준비 작업을 해요]");
		
		for(Object tmp:args) {
			if(tmp instanceof String) {//타입이 string이면!!
				//원하는 작업을 한다.
				System.out.println("aop에서 미리 조사함");
				System.out.println("전달된 name:"+tmp);
				
			}
		}
		// aop가 적용된 메소드 수행하기 
		joinPoint.proceed();//
		System.out.println("[마무리 작업을 해요]");
	}
	
	@Around("execution(String write*(int))")
	public Object aroundWrite2(ProceedingJoinPoint joinPoint) throws Throwable{
		
		Object[] args = joinPoint.getArgs();
		int num = (int)args[0];
		
		System.out.println("인자로 전달된 숫자 : "+num);
		
		//aop가 적용되는 메소드를 호출하고 그 메소드가 리턴해주는 객체를 object type으로 받기
		Object obj = joinPoint.proceed();
		
		//return type이 String이므로 casting 
		String result = (String)obj;
		System.out.println("리턴된 문자열 : "+result);
		
		//원한다면 다른 정보를 리턴해줄수도 있다.
		result = "조작한 문자열";
		return result;//이건 조작한걸 return한것
	}
	

}

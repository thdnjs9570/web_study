package test.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

/*
 * 횡단 관심사(Cross Concern) 를 Aspect로 작성하기 
 */

public class PrintLogAspect {
	public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable{
		System.out.println("----비즈니스 로직을 수행하기 전입니다.-----");//그 전에 들어갈 코드
		/*
		 * joinPoint 객체의 proceed() 메소드를 호출하는 시점이 AOP가 적용된 메소드가 수행되는 시점이다,
		 * .proceed() 메소드가 리턴해주는 객체가 AOP가 적용된 메소드가 리턴해주는 객체이다.
		 * 만일 AOP가 적용된 메소드의 리턴 type이 void이면 obj는 null이다.
		 */
		Object obj = joinPoint.proceed();//비즈니스 로직 수행//이게 sysout("회원정보를 저장합니다")
		System.out.println("----비즈니스 로직을 수행했습니다.-----");//그 후에 들어갈 코드
		
		return obj;
	}
	
}

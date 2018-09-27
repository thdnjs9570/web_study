package com.gura.spring03.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



//403 FORBIDDEN 응답을 프로그래머가 하고싶을때 
//이 exception을 발생시킬 시점에 throw new ForbiddenException()  하면된다.

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException{
	
}



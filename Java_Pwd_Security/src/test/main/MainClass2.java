package test.main;

import java.util.Scanner;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MainClass2 {
   public static void main(String[] args) {
      //가입할 때 입력한 비밀번호라고 가정
      String pwd="1234";
      
      BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
      //DB에 저장된 암호화된 비밀번호라고 가정
      String savedPwd=encoder.encode(pwd); 
      
      Scanner scan = new Scanner(System.in);
      System.out.println("비밀번호 입력");
      
      //로그인 할 때 입력한 비밀번호라고 가정
      String inputPwd = scan.nextLine();
      
      //savedPwd와 inputPwd를 비교해야 함
      boolean isValid = BCrypt.checkpw(inputPwd, savedPwd);
      
      if(isValid) {
    	  System.out.println("비밀번호가 일치해요");
      }
      else {
    	  System.out.println("비밀번호를 잘못 입력했습니다.");
      }
     
   }
}
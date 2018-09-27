package com.gura.spring03.file.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring03.file.dto.FileDto;
import com.gura.spring03.file.service.FileService;

@Controller
public class FileController {
	
	@Autowired
	private FileService fService;
	
	
	//파일 삭제 요청 처리
	@RequestMapping("/file/delete")
	public ModelAndView authDelete(HttpServletRequest request, @RequestParam int num) {
		fService.delete(request,num);
		return new ModelAndView("redirect:/file/list.do");
	}
	
	
	//파일 다운로드 요청 처리
	@RequestMapping("/file/download")
	public ModelAndView download(@RequestParam int num , ModelAndView mView) {
		
		//ModelAndView객체에 다운로드 할 파일의 정보를 담기게 한다.
		fService.getData(mView, num);
		mView.setViewName("fileDownView");
		
		//파일 다운로드 view로 forward 이동해서 다운로드 시키기
		return mView;//bean의 이름으로 찾아갈 수 있게
	}
	
	@RequestMapping("/file/upload")//@ModelAttribute FileDto dto 로 한번에 담고싶은데 file이 문제이다! 
								  //file 명도 있고 좀 복잡 그래서 fileDto 가서 뭐 추가해야함
	public ModelAndView authUpload(HttpServletRequest request, @ModelAttribute FileDto dto) {
		fService.insert(request, dto);
		return new ModelAndView("redirect:/file/list.do");
	}
	
	
	@RequestMapping("/file/upload_form")
	public ModelAndView authUploadForm(HttpServletRequest request) {
		return new ModelAndView("file/upload_form");
	}
	
	@RequestMapping("/file/list")
	public ModelAndView list(ModelAndView mView, @RequestParam(defaultValue = "1") int pageNum) {
		
		fService.getList(mView, pageNum);
		
		mView.setViewName("file/list");

		return mView;
	}
	
}

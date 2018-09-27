package com.gura.spring03.cafe.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gura.spring03.cafe.dao.CafeCommentDao;
import com.gura.spring03.cafe.dto.CafeCommentDto;
import com.gura.spring03.cafe.dto.CafeDto;
import com.gura.spring03.exception.ForbiddenException;

@Service
public class CafeCommentServiceImpl implements CafeCommentService{

	@Autowired
	private CafeCommentDao commentdao; 
	
	@Override
	public void insert(CafeCommentDto dto) {
		
		//저장할 댓글의 번호를 미리 얻어낸다.
		int seq = commentdao.getSequence();

		
		dto.setNum(seq);
		dto.setIsDelete("0");
		
		//0이면 원글에 단 댓글이다.
		int comment_group = dto.getComment_group();
				
		if(comment_group == 0) {//원글에 단 댓글!
			dto.setComment_group(seq);
		}else { //댓글의 댓글인 경우
			dto.setComment_group(comment_group);
		}
		
		commentdao.insert(dto);
		
	}

	@Override
	public void delete(HttpServletRequest request, int num) {
		String id = (String)request.getSession().getAttribute("id");
		/*CafeDto dto = new CafeDto();
		dto.setNum(num);
		String writer = commentdao.getData(dto).getWriter();
		if(!id.equals(writer)) {
			throw new ForbiddenException();
		}
		이거 해야함
		*/
		
		commentdao.delete(num);
	}

	
}

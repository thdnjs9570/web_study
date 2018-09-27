package com.gura.spring03.cafe.dto;


public class CafeCommentDto {
	
	private int num;
	private String writer;
	private String content;
	private String target_id;
	private int ref_group;
	private int comment_group;
	private String regdate;
	private String isDelete;
	private int likeNum;
	private int dislikeNum;
	
	public CafeCommentDto() {
		
	}

	public CafeCommentDto(int num, String writer, String content, String target_id, int ref_group, int comment_group,
			String regdate, String isDelete, int likeNum, int dislikeNum) {
		super();
		this.num = num;
		this.writer = writer;
		this.content = content;
		this.target_id = target_id;
		this.ref_group = ref_group;
		this.comment_group = comment_group;
		this.regdate = regdate;
		this.isDelete = isDelete;
		this.likeNum = likeNum;
		this.dislikeNum = dislikeNum;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTarget_id() {
		return target_id;
	}

	public void setTarget_id(String target_id) {
		this.target_id = target_id;
	}

	public int getRef_group() {
		return ref_group;
	}

	public void setRef_group(int ref_group) {
		this.ref_group = ref_group;
	}

	public int getComment_group() {
		return comment_group;
	}

	public void setComment_group(int comment_group) {
		this.comment_group = comment_group;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public int getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}

	public int getDislikeNum() {
		return dislikeNum;
	}

	public void setDislikeNum(int dislikeNum) {
		this.dislikeNum = dislikeNum;
	}

	
}
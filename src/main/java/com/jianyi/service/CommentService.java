package com.jianyi.service;

import com.jianyi.bean.CommentFirstLevel;
import com.jianyi.bean.CommentSecondLevel;


public interface CommentService {
	
	public void addFlcComment(CommentFirstLevel commentFirstLevel);
	
	public void removeFlcComment(int commentId, int sayingId);
	
	public void addSlcComment(CommentSecondLevel commentSecondLevel);
	
	public void removeSlcComment(int commentId, int sayingId);
}

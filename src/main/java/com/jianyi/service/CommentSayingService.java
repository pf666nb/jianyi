package com.jianyi.service;

import com.jianyi.bean.CommentSaying;


import java.util.List;

public interface CommentSayingService {

	public void insertComment(CommentSaying commentSaying);

	public List<CommentSaying> selectListBySectionId(int id);

	public CommentSaying queryOneSaying(int id);

	public void deleteById(int sayingId);

	public void modifySayingLikes(int id, String likes);

}
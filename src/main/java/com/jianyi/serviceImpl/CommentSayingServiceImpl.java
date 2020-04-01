package com.jianyi.serviceImpl;

import com.jianyi.bean.CommentSaying;
import com.jianyi.mapper.CommentSayingMapper;
import com.jianyi.service.CommentSayingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentSayingServiceImpl implements CommentSayingService {
	
	@Resource
    private CommentSayingMapper commentSayingMapper;

	@Override
	public void insertComment(CommentSaying commentSaying){
		commentSayingMapper.insertComment(commentSaying);}

	@Override
	public List<CommentSaying> selectListBySectionId(int id){
		return commentSayingMapper.selectListBySectionId(id);
	}

	@Override
	public CommentSaying queryOneSaying(int id) {
		return commentSayingMapper.selectOneById(id);
	}

	@Override
	public void deleteById(int sayingId) {

		commentSayingMapper.deleteById(sayingId);
	}

	@Override
	public void modifySayingLikes(int id, String likes) {
		commentSayingMapper.updateLikesById(id, likes);
	}
}

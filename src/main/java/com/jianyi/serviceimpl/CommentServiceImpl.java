package com.jianyi.serviceimpl;
import com.jianyi.bean.CommentFirstLevel;
import com.jianyi.bean.CommentSecondLevel;
import com.jianyi.mapper.CommentFirstLevelMapper;
import com.jianyi.mapper.CommentSecondLevelMapper;
import com.jianyi.service.CommentService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;


@Service
public class CommentServiceImpl implements CommentService {
	
    @Resource
	private CommentFirstLevelMapper commentFirstLevelMapper;
	
	@Resource
	private CommentSecondLevelMapper commentSecondLevelMapper;

	@Override
	public void addFlcComment(CommentFirstLevel commentFirstLevel) {
		commentFirstLevelMapper.insertFlcComment(commentFirstLevel);
	}

	@Override
	public void removeFlcComment(int commentId, int sayingId) {
		commentFirstLevelMapper.deleteFlcComment(commentId, sayingId);
		commentFirstLevelMapper.deleteFlcComment2(commentId,sayingId);

	}

	@Override
	public void addSlcComment(CommentSecondLevel commentSecondLevel) {
		commentSecondLevelMapper.insertSlcComment(commentSecondLevel);
	}

	@Override
	public void removeSlcComment(int commentId, int sayingId) {
		commentSecondLevelMapper.deleteSlcComment(sayingId, commentId);
	}

}

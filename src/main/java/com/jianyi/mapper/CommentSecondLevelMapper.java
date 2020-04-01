package com.jianyi.mapper;

import com.jianyi.bean.CommentSecondLevel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface CommentSecondLevelMapper {
	
	public int insertSlcComment(CommentSecondLevel commentSecondLevel);
	public void deleteSlcComment(@Param("sayingId") int sayingId, @Param("commentId") int commentId);
}

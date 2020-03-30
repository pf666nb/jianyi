package com.jianyi.mapper;

import com.jianyi.bean.CommentFirstLevel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface CommentFirstLevelMapper {
	/**
	 * @param commentFirstLevel 父级评论
	 * */
	public void insertFlcComment(CommentFirstLevel commentFirstLevel);
	/**
	 * @param commentId 评论的id
	 * @param sayingId 评论者的id
	 * */
	public void deleteFlcComment(@Param("commentId") int commentId, @Param("sayingId") int sayingId);
	/**
	 *
	 * @param sayingId 评论id
	 * @param commentId 评论者id
	 * */
	public void deleteFlcComment2(@Param("commentId") int commentId, @Param("sayingId") int sayingId);


}

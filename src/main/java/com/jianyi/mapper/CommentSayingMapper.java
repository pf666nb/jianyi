package com.jianyi.mapper;

import com.jianyi.bean.CommentSaying;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface CommentSayingMapper {

	public List<CommentSaying> selectListBySectionId(int id);

	public void insertComment(CommentSaying commentSaying);

	public CommentSaying selectOneById(int id);

	public void deleteById(int sayingId);

	public void updateLikesById(@Param("id") int id, @Param("likes") String likes);
}

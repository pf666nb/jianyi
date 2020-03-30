package com.jianyi.controller;
import com.jianyi.bean.CommentFirstLevel;
import com.jianyi.bean.CommentSecondLevel;
import com.jianyi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Whitte
 * @Description 用于处理一、二级评论的CRUD
 * @date 2019/7/26 9:08
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;

	/**
	  * @Description 添加一级评论
	  * @param commentFirstLevel 一级评论的pojo，包含评论内容等信息
	  * @return 评论pojo
	  */
	@RequestMapping(value="/add/first", method= RequestMethod.POST)
    public CommentFirstLevel addFirstLevelCommment(CommentFirstLevel commentFirstLevel) {
		commentService.addFlcComment(commentFirstLevel);
		return commentFirstLevel;
    }

    /**
      * @Description 用于删除一级评论
      * @param sayingId 父评论的ID
	  * @param commentId 回复的ID
      * @return 无
      */
	@RequestMapping(value="/delete/first")
    public void removeFirstLevelCommment(int sayingId, int commentId) {
		commentService.removeFlcComment(commentId, sayingId);
    }

    /**
      * @Description 增加二级评论
      * @param commentSecondLevel 二级评论pojo，包含评论内容
      * @return 评论pojo
      */
	@RequestMapping(value="/add/second", method= RequestMethod.POST)
    public CommentSecondLevel addSecondLevelCommment(CommentSecondLevel commentSecondLevel) {
		commentService.addSlcComment(commentSecondLevel);
		return commentSecondLevel;
    }

    /**
      * @Description 删除二级评论
      * @param sayingId 父级评论ID
	  * @param commentId 回复评论ID
      * @return 无
      */
	@RequestMapping(value="/delete/second")
		public void removeSecondLevelCommment(int sayingId,  int commentId) {
		commentService.removeSlcComment(commentId, sayingId);
    }
}
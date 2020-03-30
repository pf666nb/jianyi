package com.jianyi.controller;

import com.jianyi.bean.CommentSaying;
import com.jianyi.service.CommentSayingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

/**
 * @author Whitte
 * @Description 用于处理父级评论请求
 * @date 2019/7/26 9:15
 */
@RequestMapping("/comment")
@RestController
public class CommentSayingController {
	
	@Autowired
	private CommentSayingService commentSayingService;

	/**
	  * @Description 新增父级评论
	  * @param commentSaying 父级评论pojo
	  * @return 父级评论pojo
	  */
	@RequestMapping(value="/saying/add/first", method= RequestMethod.POST)
	public CommentSaying addFirstLevelCommment(CommentSaying commentSaying) {
		commentSaying.setCreateTime(new Date());
		commentSayingService.insertComment(commentSaying);
		return commentSaying;
	}

	/**
	  * @Description 根据评论所属物品，查找评论
	  * @param goods_id 评论所属物品ID
	  * @return 评论pojo
	  */
	@RequestMapping("/saying/get/comment/list")
	public List<CommentSaying> showList(int goods_id) {
		return commentSayingService.selectListBySectionId(goods_id);
	}

	/**
	  * @Description 删除父级评论
	  * @param sayingId 父级评论ID
	  * @return 无
	  */
	@RequestMapping("/saying/delete")
	public void delete(int sayingId) {
		commentSayingService.deleteById(sayingId);
	}


	/**
	  * @Description 根据ID查找父级评论
	  * @param id 父级评论ID
	  * @return 父级评论pojo
	  */
	@RequestMapping("/saying/get/comment")
    public CommentSaying showCommment(int id) {
		return commentSayingService.queryOneSaying(id);
    }

//    /**
//      * @Description 更新父级评论likes
//      * @param id 父级评论ID
//	  * @param likes likes参数
//      * @return 无
//      */
//	@RequestMapping(value="/saying/likes", method= RequestMethod.POST)
//    public void changeLikes(@RequestParam int id, @RequestParam String likes) {
//		commentSayingService.modifySayingLikes(id, likes);
//    }
}

package com.jianyi.bean;

import java.util.Date;
import java.util.List;

public class CommentSaying {
	private Integer id;
	private String sayingContent;
	private String author;
	private Integer goods_id;
	private String avatar;
	private Date createTime;
	private List<CommentFirstLevel> flcs;
	
	public CommentSaying() {}

	@Override
	public String toString() {
		return "CommentSaying{" +
				"id=" + id +
				", sayingContent='" + sayingContent + '\'' +
				", author='" + author + '\'' +
				", goods_id=" + goods_id +
				", avatar='" + avatar + '\'' +
				", createTime=" + createTime +
				", flcs=" + flcs +
				'}';
	}


	public Integer getgoods_id() {
		return goods_id;
	}

	public void setgoods_id(Integer sectionId) {
		this.goods_id = sectionId;
	}

	public List<CommentFirstLevel> getFlcs() {
		return flcs;
	}

	public void setFlcs(List<CommentFirstLevel> flcs) {
		this.flcs = flcs;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getSayingContent() {
		return sayingContent;
	}
	
	public void setSayingContent(String sayingContent) {
		this.sayingContent = sayingContent;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getAvatar() {
		return avatar;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
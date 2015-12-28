package com.gcs.aol.entity;

import javax.persistence.Column;

import com.gcs.sysmgr.entity.IdAbstartEntity;

/**
 * 
 * @ClassName: Video
 * @Description: 宣传视频
 * @author Administrator
 * @date 2015年12月28日
     *
 */
public class Video extends IdAbstartEntity{

	private static final long serialVersionUID = 3240829083917610464L;

	@Column(name="title")
	private String title;
	
	@Column(name="content")
	private String content;
	
	@Column(name="url")
	private String url;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@Column(name="create_date")
	private Long createDate;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
}

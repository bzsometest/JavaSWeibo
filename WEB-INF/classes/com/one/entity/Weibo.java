package com.one.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 微博实体
 * 
 * @author Administrator
 *
 */
public class Weibo {
	private int wid;// 微博id
	private User user;// 用户id
	private String content;// 微博内容
	private Date time;// 发表时间
	private String img;// 图片路径

	/**
	 * 无参构造方法
	 */
	public Weibo() {
		super();
	}

	/**
	 * 有参构造方法
	 * 
	 * @param wid
	 * @param user
	 * @param content
	 * @param time
	 */
	public Weibo(int wid, User user, String content, Date time) {
		super();
		this.wid = wid;
		this.user = user;
		this.content = content;
		this.time = time;
	}

	// get和set方法
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getWid() {
		return wid;
	}

	public void setWid(int wid) {
		this.wid = wid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Weibo [wid=" + wid + ", user=" + user + ", content=" + content + ", time=" + time + ", img=" + img
				+ "]";
	}

	public String getImg() {
		return img;
	}

	public String getTimeString() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(this.time);
		return dateString;
	}

	public void setImg(String img) {
		this.img = img;
	}

}

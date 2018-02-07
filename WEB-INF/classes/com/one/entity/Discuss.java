package com.one.entity;

import java.util.Date;

/**
 * 评论实体
 * 
 * @author Administrator
 *
 */
public class Discuss {
	private int did;// 当前评论的编号
	private Weibo weibo;// 当前评论的微博
	private User user;// 当前评论用户
	private String content;// 当前评论的内容
	private Date time;// 当前评论时间

	/**
	 * 无参构造方法
	 */
	public Discuss() {
		super();
	}

	/**
	 * 有参构造方法
	 * 
	 * @param did
	 * @param weibo
	 * @param user
	 * @param context
	 * @param time
	 */
	public Discuss(int did, Weibo weibo, User user, String content, Date time) {
		super();
		this.did = did;
		this.weibo = weibo;
		this.user = user;
		this.content = content;
		this.time = time;
	}

	// get和set方法
	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public Weibo getWeibo() {
		return weibo;
	}

	public void setWeibo(Weibo weibo) {
		this.weibo = weibo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	// 重写tostring方法
	@Override
	public String toString() {
		return "Discuss [did=" + did + ", weibo=" + weibo + ", user=" + user + ", context=" + content + ", time=" + time
				+ "]";
	}

}

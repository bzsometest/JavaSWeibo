package com.one.entity;

import java.util.List;

/**
 * 二次封装类，用于前段jsp显示评论
 * 
 * @author chao
 *
 */
public class WeiboShow {
	private Weibo weibo;
	private List<Discuss> discuss;
	private int zancount;

	/**
	 * @return the weibo
	 */
	public Weibo getWeibo() {
		return weibo;
	}

	/**
	 * @param weibo
	 *            the weibo to set
	 */
	public void setWeibo(Weibo weibo) {
		this.weibo = weibo;
	}

	/**
	 * @return the zancount
	 */
	public int getZancount() {
		return zancount;
	}

	/**
	 * @param zancount
	 *            the zancount to set
	 */
	public void setZancount(int zancount) {
		this.zancount = zancount;
	}

	/**
	 * @return the discuss
	 */
	public List<Discuss> getDiscuss() {
		return discuss;
	}

	/**
	 * @param discuss
	 *            the discuss to set
	 */
	public void setDiscuss(List<Discuss> discuss) {
		this.discuss = discuss;
	}
}

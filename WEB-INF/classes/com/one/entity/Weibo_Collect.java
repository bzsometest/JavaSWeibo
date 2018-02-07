package com.one.entity;

public class Weibo_Collect {
	   private int cid;//收藏id
	   private Weibo weibo;//被收藏的微博
	   private User User;//收藏此微博的用户
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public Weibo getWeibo() {
		return weibo;
	}
	public void setWeibo(Weibo weibo) {
		this.weibo = weibo;
	}
	public User getUser() {
		return User;
	}
	public void setUser(User user) {
		User = user;
	}
	@Override
	public String toString() {
		return "Weibo_Collect [cid=" + cid + ", weibo=" + weibo + ", User=" + User + "]";
	}
	   
}

package com.one.entity;

public class Weibo_Zan {
   private int zid;//点赞id
   private Weibo weibo;//被点赞的微博
   private User User;//点赞此微博的用户
public int getZid() {
	return zid;
}
public void setZid(int zid) {
	this.zid = zid;
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
	return "Weibo_Zan [zid=" + zid + ", weibo=" + weibo + ", User=" + User + "]";
}
   
}

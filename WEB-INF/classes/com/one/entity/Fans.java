package com.one.entity;
/**
 * 粉丝实体
 * @author Administrator
 *
 */
public class Fans {
   private int fid;//粉丝的编号
   private User mUser;//用户
   private User fUser;//用户的粉丝
   /**
    * 无参构造方法
    */
public Fans() {
	super();
}

public Fans(int fid, User mUser, User fUser) {
	super();
	this.fid = fid;
	this.mUser = mUser;
	this.fUser = fUser;
}
//get、set方法
public int getFid() {
	return fid;
}

public void setFid(int fid) {
	this.fid = fid;
}

public User getmUser() {
	return mUser;
}

public void setmUser(User mUser) {
	this.mUser = mUser;
}

public User getfUser() {
	return fUser;
}

public void setfUser(User fUser) {
	this.fUser = fUser;
}
//重写toString方法
@Override
public String toString() {
	return "Fans [fid=" + fid + ", mUser=" + mUser + ", fUser=" + fUser + "]";
}
    
}

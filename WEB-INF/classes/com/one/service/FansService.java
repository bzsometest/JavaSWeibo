package com.one.service;

import java.util.List;

import com.one.entity.User;

public interface FansService {
	/**
	 * 获取此用户的所有粉丝
	 * 
	 * @param user
	 * @return
	 */
	public List<User> getFans(User user);

	/**
	 * 获取此用户的关注用户
	 * 
	 * @param user
	 * @return
	 */
	public List<User> getFollow(User user);

	/**
	 * 关注用户
	 * 
	 * @param user
	 * @param userFan
	 * @return
	 */
	public int addFans(User user, User fUser);

	/**
	 * 取消关注
	 * 
	 * @param user
	 * @param userFan
	 * @return
	 */
	public int deleteFans(User user, User fUser);

	/**
	 * 关注用户数
	 * 
	 * @param user
	 * @return
	 */
	public int getCountByMid(User user);

	/**
	 * 粉丝数
	 * 
	 * @param user
	 * @return
	 */
	public int getCountByUid(User user);

	/**
	 * fuser是否关注了user
	 * 
	 * @param user
	 * @param fUser
	 * @return
	 */
	public boolean isFans(User user, User fUser);
}

package com.one.dao;

import java.util.List;

import com.one.entity.User;

/**
 * 粉丝接口
 * 
 * @author Administrator
 *
 */
public interface FansDao {
	/**
	 * 获取此用户的所有粉丝
	 * 
	 * @param user
	 * @return
	 */
	public List<User> getFans(int uid);

	/**
	 * 获取此用户的关注用户
	 * 
	 * @param user
	 * @return
	 */
	public List<User> getFollow(int uid);

	/**
	 * 关注用户
	 * 
	 * @param user
	 * @param userFan
	 * @return
	 */
	public int addFans(int uid, int fid);

	/**
	 * 取消关注
	 * 
	 * @param user
	 * @param userFan
	 * @return
	 */
	public int deleteFans(int uid, int fid);

	/**
	 * 查看用户关注的博主数
	 * 
	 * @param uid
	 * @return
	 */
	public int getCountByMid(int mid);

	/**
	 * 查看用户粉丝数
	 * 
	 * @param uid
	 * @return
	 */
	public int getCountByUid(int uid);
	/**
	 * 判断是否关注了博主
	 * @param uid
	 * @param fid
	 * @return
	 */
	public boolean isFans(int uid,int fid);
    /**
     * 删除用户的关注对象和粉丝
     * @param uid
     * @return
     */
	public int deleteByUid(int uid);



}

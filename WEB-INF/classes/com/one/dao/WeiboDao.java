package com.one.dao;
/*微博接口*/

import java.util.List;

import com.one.entity.User;
import com.one.entity.Weibo;

public interface WeiboDao {
	/** 根据微博wID获取微博 */
	public Weibo get(int wid);

	/** 新增微博 */
	public int add(Weibo weibo);

	/** 删除微博 */
	int delete(int wid);

	/** 修改微博(可选) */
	public int alter(int wid, Weibo weibo);

	/** 获取数据库中存放的微博数 */
	public int getCount();

	/** 根据用户获取微博 */
	public List<Weibo> get(User user);

	/** 获取本站所有微博 */
	public List<Weibo> getAll();

	/** 根据起始位置和数量显示微博 */
	public List<Weibo> get(int start, int count);

	/**
	 * 查询用户的微博数
	 * 
	 * @param uid
	 * @return
	 */
	public int getCount(int uid);

	public List<Weibo> getByMid(int uid);
    /**
     * 根据用户的uid删除微博
     * @param uid
     * @return
     */
	public int deleteByUid(int uid);

}

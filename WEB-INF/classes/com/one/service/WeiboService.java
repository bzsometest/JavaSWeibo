package com.one.service;

import java.util.List;

import com.one.entity.User;
import com.one.entity.Weibo;

public interface WeiboService {
	/**
	 * 根据微博id查询信息
	 * 
	 * @param wid
	 * @return
	 */
	public Weibo get(int wid);

	/***
	 * 添加微博
	 * 
	 * @param weibo
	 * @return
	 */
	public int add(Weibo weibo);

	/**
	 * 删除微博
	 * 
	 * @param wid
	 * @return
	 */
	public int delete(Weibo weibo, User user);

	/**
	 * 管理员删除微博
	 * 
	 * @param wid
	 * @return
	 */
	public int adminDelete(Weibo weibo, User admin);

	/**
	 * 修改微博
	 * 
	 * @param wid
	 * @param weibo
	 * @return
	 */
	public int alter(int wid, Weibo weibo);

	/**
	 * 得到微博记录的条数
	 */
	public int getCount();

	/**
	 * 根据用户id得到微博记录的条数
	 */
	public int getCount(User user);

	/**
	 * 根据用户id查询微博
	 * 
	 * @param user
	 * @return
	 */
	public List<Weibo> get(User user);

	/**
	 * 查询所有的微博
	 * 
	 * @return
	 */
	public List<Weibo> getAll();

	/**
	 * 分页查询
	 * 
	 * @param start
	 * @param count
	 * @return
	 */
	public List<Weibo> get(int start, int count);

	/**
	 * 显示用户关注的博主的微博
	 * 
	 * @param user
	 * @return
	 */
	public List<Weibo> getByMid(User user);
}

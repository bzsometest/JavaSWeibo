package com.one.service;

import java.util.List;

import com.one.entity.Discuss;
import com.one.entity.User;
import com.one.entity.Weibo;

public interface DiscussService {
	/**
	 * 根据wid得到评论
	 * 
	 * @param wid
	 * @return
	 */
	public List<Discuss> getWid(Weibo weibo);

	/**
	 * 新增评论
	 * 
	 * @param discuss
	 * @return
	 */
	public int add(Discuss discuss);

	/**
	 * 删除评论
	 * 
	 * @param did
	 * @return
	 */
	public int delete(Discuss discuss, User user);

	/**
	 * 管理员删除评论
	 * 
	 * @param discuss
	 * @return
	 */
	public int adminDelete(Discuss discuss, User admin);

	/**
	 * 评论者删除评论
	 * 
	 * @param discuss
	 * @return
	 */
	public int Delete(Discuss discuss, User user);

	/**
	 * 博主删除评论
	 * 
	 * @param discuss
	 * @param user
	 * @return
	 */
	public int DeleteByOwn(Discuss discuss, User user);

	/**
	 * 管理员获取所有的评论
	 * 
	 * @param user
	 * @return
	 */
	public List<Discuss> getAll(User user);

	/**
	 * 根据did获取评论信息
	 * 
	 * @param discuss
	 * @return
	 */
	public Discuss getByDid(int did);

}

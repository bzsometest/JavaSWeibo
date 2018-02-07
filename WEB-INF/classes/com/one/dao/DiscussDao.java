package com.one.dao;

import java.util.List;

import com.one.entity.Discuss;

/**
 * 评论接口
 * 
 * @author Administrator
 *
 */
public interface DiscussDao {

	/**
	 * 根据评论ID获取评论
	 * 
	 * @param did
	 * @return
	 */
	public Discuss get(int did);

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
	public int delete(int did);

	/**
	 * 根据wid获取评论
	 * 
	 * @param wid
	 * @return
	 */
	public List<Discuss> getWid(int wid);

	/**
	 * 根据wid删除评论
	 * 
	 * @param wid
	 * @return
	 */
	public int deleteByWid(int wid);

	/**
	 * 获取所有的评论
	 * 
	 * @return
	 */
	public List<Discuss> getAll();

}

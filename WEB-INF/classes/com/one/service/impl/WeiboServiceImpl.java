package com.one.service.impl;

import java.util.List;

import com.one.dao.DiscussDao;
import com.one.dao.FansDao;
import com.one.dao.UserDao;
import com.one.dao.WeiboDao;
import com.one.dao.Weibo_CollectDao;
import com.one.dao.Weibo_ZanDao;
import com.one.dao.impl.DiscussDaoImpl;
import com.one.dao.impl.FansDaoImpl;
import com.one.dao.impl.UserDaoImpl;
import com.one.dao.impl.WeiboDaoImpl;
import com.one.dao.impl.Weibo_CollectDaoImpl;
import com.one.dao.impl.Weibo_ZanDaoImpl;
import com.one.entity.User;
import com.one.entity.Weibo;
import com.one.service.WeiboService;

public class WeiboServiceImpl implements WeiboService {
	WeiboDao wdao = new WeiboDaoImpl();
	UserDao udao = new UserDaoImpl();
	DiscussDao ddao = new DiscussDaoImpl();
	FansDao fdao = new FansDaoImpl();
	Weibo_CollectDao cdao = new Weibo_CollectDaoImpl();
	Weibo_ZanDao zdao = new Weibo_ZanDaoImpl();

	/**
	 * 根据用户id实现查询微博
	 */
	@Override
	public Weibo get(int wid) {
		Weibo weibo = wdao.get(wid);
		User user = udao.findByID(weibo.getUser().getUid());
		weibo.setUser(user);
		return weibo;
	}

	/**
	 * 添加微博
	 */
	@Override
	public int add(Weibo weibo) {
		int result = 0;
		User user = weibo.getUser();
		User user2 = new UserServiceImpl().login(user.getUsername(), user.getUserpass());
		if (user2 != null) {

			result = wdao.add(weibo);
		}
		return result;
	}

	/**
	 * 删除微博
	 */
	@Override
	public int delete(Weibo weibo, User user) {
		int result = 0;
		if (user != null) {//先对用户登录验证
			user = new UserServiceImpl().login(user.getUsername(), user.getUserpass());
		}
		if (user != null) {// 密码验证成功
			User wUser = weibo.getUser();
			if (wUser.getUid() == user.getUid()) {// 判断是否是同一用户
				// 删除评论
				ddao.deleteByWid(weibo.getWid());
				// 删除收藏
				cdao.deleteByWid(weibo.getWid());
				// 删除点赞
				zdao.deleteByWid(weibo.getWid());
				// 删除微博
				result = wdao.delete(weibo.getWid());
			} else {
				System.out.println("删除微博：不是同一用户！");
			}
		}
		return result;
	}

	@Override
	public int alter(int wid, Weibo weibo) {
		int result = 0;
		User user = weibo.getUser();
		User user2 = new UserServiceImpl().login(user.getUsername(), user.getUserpass());
		if (user2 != null) {
			result = wdao.alter(wid, weibo);
		}
		return result;
	}

	/**
	 * 获得微博总条数
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return wdao.getCount();
	}

	/**
	 * 查询用户的微博
	 */
	@Override
	public List<Weibo> get(User user) {
		List<Weibo> list = wdao.get(user);
		// 遍历数组
		for (Weibo weibo : list) {
			// 根据用户id查询用户信息并返回加入微博
			weibo.setUser(udao.findByID(weibo.getUser().getUid()));
		}
		return list;
	}

	/*
	 * 获取所有的微博
	 */
	@Override
	public List<Weibo> getAll() {
		List<Weibo> list = wdao.getAll();
		for (Weibo weibo : list) {
			// 根据用户id查询用户信息并返回加入微博
			User user = udao.findByID(weibo.getUser().getUid());
			weibo.setUser(user);
		}
		return list;
	}

	/**
	 * 分页查询
	 */
	@Override
	public List<Weibo> get(int start, int count) {
		List<Weibo> list = wdao.get(start, count);
		for (Weibo weibo : list) {
			// 根据用户id查询用户信息并返回加入微博
			User user = udao.findByID(weibo.getUser().getUid());
			weibo.setUser(user);
		}
		return list;
	}

	/**
	 * 查询用户的微博数
	 */
	@Override
	public int getCount(User user) {
		// TODO Auto-generated method stub
		return wdao.getCount(user.getUid());
	}

	/**
	 * 显示用户关注的用户微博
	 */
	@Override
	public List<Weibo> getByMid(User user) {
		List<Weibo> list = wdao.getByMid(user.getUid());
		for (Weibo weibo : list) {
			// 根据用户id查询用户信息并返回加入微博
			User user2 = udao.findByID(weibo.getUser().getUid());
			weibo.setUser(user2);
		}
		return list;
	}

	@Override
	public int adminDelete(Weibo weibo, User admin) {
		int result = 0;
		User user2 = new UserServiceImpl().adminLogin(admin.getUsername(), admin.getUserpass());
		if (user2 != null) {
			result = ddao.deleteByWid(weibo.getWid());
			result = cdao.deleteByWid(weibo.getWid());
			result = zdao.deleteByWid(weibo.getWid());
			result = wdao.delete(weibo.getWid());
		}
		return result;
	}

}

package com.one.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.one.dao.*;
import com.one.dao.impl.*;
import com.one.entity.*;
import com.one.service.*;

public class DiscussServiceImpl implements DiscussService {
	DiscussDao ddao = new DiscussDaoImpl();
	UserDao udao = new UserDaoImpl();
	WeiboDao wdao = new WeiboDaoImpl();

	/**
	 * 得到评论
	 */
	@Override
	public int add(Discuss discuss) {
		int result = 0;
		User user = discuss.getUser();
		if (user == null) {
			System.out.println("DiscussServiceImpl:发布用户为空！");
			return result;
		}
		User user2 = new UserServiceImpl().login(user.getUsername(), user.getUserpass());
		if (user2 != null) {
			result = ddao.add(discuss);
		} else {
			System.out.println("DiscussServiceImpl:用户登录失败！！");
		}
		return result;
	}

	/**
	 * 删除评论，这是自己的评论
	 */
	@Override
	public int delete(Discuss discuss, User user) {
		int result = 0;
		if (user != null) {
			user = new UserServiceImpl().login(user.getUsername(), user.getUserpass());
		}
		if (user != null) {// 密码验证成功
			User dUser = discuss.getUser();
			if (dUser.getUid() == user.getUid()) {// 判断是否是同一用户
				result = ddao.delete(discuss.getDid());
			}
		}
		return result;
	}

	/**
	 * 管理员删除评论
	 */
	@Override
	public int adminDelete(Discuss discuss, User admin) {
		int result = 0;
		User user2 = new UserServiceImpl().adminLogin(admin.getUsername(), admin.getUserpass());
		if (user2 != null) {
			System.out.println("adminDelete admin身份验证成功！");
			result = ddao.delete(discuss.getDid());
		}
		return result;
	}

	/**
	 * 评论删除评论
	 */
	@Override
	public int Delete(Discuss discuss, User user) {
		int result = 0;
		// 验证登录
		user = new UserServiceImpl().login(user.getUsername(), user.getUserpass());
		if (user != null) {
			User dUser = discuss.getUser();
			System.out.println(dUser.toString());
			if (user.getUid() == dUser.getUid()) {
				result = ddao.delete(discuss.getDid());
			}
		}
		return result;
	}

	/**
	 * 博主删除评论
	 * 
	 */
	@Override
	public int DeleteByOwn(Discuss discuss, User user) {
		int result = 0;

		if (user != null) {// 先对用户登录验证
			user = new UserServiceImpl().login(user.getUsername(), user.getUserpass());
		}
		if (user != null) {// 密码验证成功
			/* 获得此评论微博的用户id */
			User wUser = discuss.getWeibo().getUser();
			if (wUser.getUid() == user.getUid()) {// 判断是否是同一用户
				// 删除微博
				result = ddao.delete(discuss.getDid());
			} else {
				System.out.println("删除评论：不是同一用户！此评论不属于此用户。");
			}
		}
		return result;
	}

	/**
	 * 根据wid获取评论
	 */
	@Override
	public List<Discuss> getWid(Weibo weibo) {
		List<Discuss> list = ddao.getWid(weibo.getWid());
		for (Discuss discuss : list) {
			User user = udao.findByID(discuss.getUser().getUid());
			// WeiboServiceImpl中已设置微博信息，包括用户信息
			weibo = new WeiboServiceImpl().get(weibo.getWid());
			discuss.setUser(user);
			discuss.setWeibo(weibo);
		}
		return list;
	}

	@Override
	public List<Discuss> getAll(User user) {
		// 验证是否是管理员
		List<Discuss> list = new ArrayList<Discuss>();
		User user2 = new UserServiceImpl().adminLogin(user.getUsername(), user.getUserpass());
		if (user2 != null) {
			list = ddao.getAll();
			for (Discuss discuss : list) {
				Weibo weibo = wdao.get(discuss.getWeibo().getWid());
				User user3 = udao.findByID(discuss.getUser().getUid());
				discuss.setUser(user3);
				discuss.setWeibo(weibo);
			}
		}
		return list;
	}

	@Override
	public Discuss getByDid(int uid) {
		Discuss discuss = null;
		// 判断传过来的uid不等于0
		if (uid != 0) {
			discuss = ddao.get(uid);// 获取评论
			int wid = discuss.getWeibo().getWid();
			Weibo weibo = wdao.get(wid);
			discuss.setWeibo(weibo);
		}
		return discuss;
	}
}

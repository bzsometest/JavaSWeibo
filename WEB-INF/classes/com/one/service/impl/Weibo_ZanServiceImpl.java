package com.one.service.impl;

import com.one.dao.Weibo_ZanDao;
import com.one.dao.impl.Weibo_ZanDaoImpl;
import com.one.entity.User;
import com.one.entity.Weibo;
import com.one.service.Weibo_ZanService;

public class Weibo_ZanServiceImpl implements Weibo_ZanService {
	Weibo_ZanDao zdao = new Weibo_ZanDaoImpl();

	@Override
	public int addZan(Weibo weibo, User user) {
		int result = 0;
		if (user == null) {
			System.out.println("weibozanServiceImpl:发布用户为空！");
			result = 0;
		}
		User user2 = new UserServiceImpl().login(user.getUsername(), user.getUserpass());
		if (user2 != null) {
			// 增加赞时，先删除赞
			zdao.DeleteZan(weibo.getWid(), user.getUid());
			result = zdao.addZan(weibo.getWid(), user.getUid());
		} else {
			System.out.println("weibozanServiceImpl:登陆失败");
			result = 0;// 用户登录失败
		}
		return result;
	}

	@Override
	public int cancelZan(Weibo weibo, User user) {
		int result = 0;
		if (user == null) {
			System.out.println("weibozanServiceImpl:取消赞失败 发布用户为空！");
			result = 0;
		}
		User user2 = new UserServiceImpl().login(user.getUsername(), user.getUserpass());
		if (user2 != null) {
			result = zdao.DeleteZan(weibo.getWid(), user.getUid());
		} else {
			System.out.println("weibozanServiceImpl:登陆失败");
			result = 0;// 用户登录失败
		}
		return result;
	}

	@Override
	public boolean isZan(Weibo weibo, User user) {
		boolean b = false;
		if (user != null) {
			b = zdao.isZan(weibo.getWid(), user.getUid());
		}
		return b;
	}

	@Override
	public int countZan(Weibo weibo) {
		int result = 0;
		if (weibo.getWid() != 0) {
			result = zdao.countZan(weibo.getWid());
		}
		return result;
	}

}

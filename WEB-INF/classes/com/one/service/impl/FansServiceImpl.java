package com.one.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.one.dao.FansDao;
import com.one.dao.UserDao;
import com.one.dao.impl.FansDaoImpl;
import com.one.dao.impl.UserDaoImpl;
import com.one.entity.User;
import com.one.service.FansService;

public class FansServiceImpl implements FansService {
	FansDao fdao = new FansDaoImpl();
	UserDao udao = new UserDaoImpl();

	/**
	 * 根据偶像id得到粉丝
	 */
	@Override
	public List<User> getFans(User user) {
		List<User> list = fdao.getFans(user.getUid());
		List<User> list2 = new ArrayList<>();
		// 根据
		for (User user2 : list) {
			User user3 = udao.findByID(user2.getUid());
			list2.add(user3);
		}
		return list2;
	}

	@Override
	public List<User> getFollow(User user) {
		List<User> list = fdao.getFollow(user.getUid());
		List<User> list2 = new ArrayList<>();
		for (User user2 : list) {
			User user3 = udao.findByID(user2.getUid());
			list2.add(user3);
		}
		return list2;
	}

	/**
	 * 关注
	 */
	@Override
	public int addFans(User user, User fUser) {
		int result = 0;
		if (fUser == null) {
			return 0;
		}
		User user2 = new UserServiceImpl().login(fUser.getUsername(), fUser.getUserpass());
		if (user2 != null) {
			fdao.deleteFans(user.getUid(), fUser.getUid());
			result = fdao.addFans(user.getUid(), fUser.getUid());
		}
		return result;
	}

	/**
	 * 取消关注
	 */
	@Override
	public int deleteFans(User user, User fUser) {
		int result = 0;
		if (fUser == null) {
			return 0;
		}
		User user2 = new UserServiceImpl().login(fUser.getUsername(), fUser.getUserpass());
		if (user2 != null) {
			result = fdao.deleteFans(user.getUid(), fUser.getUid());
		}
		return result;

	}

	/**
	 * 
	 * 关注用户数
	 */
	@Override
	public int getCountByMid(User user) {

		return fdao.getCountByMid(user.getUid());
	}

	/**
	 * 粉丝数
	 */
	@Override
	public int getCountByUid(User user) {
		// TODO Auto-generated method stub
		return fdao.getCountByUid(user.getUid());
	}

	@Override
	public boolean isFans(User user, User fUser) {
		boolean b = false;
		if (user != null && fUser != null) {
			b = fdao.isFans(user.getUid(), fUser.getUid());
		}
		return b;
	}

}

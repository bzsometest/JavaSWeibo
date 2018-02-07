package com.one.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.one.dao.WeiboDao;
import com.one.dao.Weibo_CollectDao;
import com.one.dao.impl.WeiboDaoImpl;
import com.one.dao.impl.Weibo_CollectDaoImpl;
import com.one.entity.User;
import com.one.entity.Weibo;
import com.one.entity.Weibo_Collect;
import com.one.service.Weibo_CollectService;

public class Weibo_CollectServiceImpl implements Weibo_CollectService {
	Weibo_CollectDao cdao = new Weibo_CollectDaoImpl();
	WeiboDao wdao = new WeiboDaoImpl();

	@Override
	public int addCollect(Weibo weibo, User user) {
		int result = 0;
		if (user != null) {
			User user2 = new UserServiceImpl().login(user.getUsername(), user.getUserpass());
			if (user2 != null) {
				/* 先取消收藏，再收藏！ */
				cdao.addCollect(weibo.getWid(), user.getUid());
				result = cdao.addCollect(weibo.getWid(), user.getUid());
			} else {
				System.out.println("Collect service :用户登录失败");
				result = 0;
			}
		}

		return result;
	}

	@Override
	public int cancleCollect(Weibo weibo, User user) {
		int result = 0;
		if (user != null) {
			User user2 = new UserServiceImpl().login(user.getUsername(), user.getUserpass());
			if (user2 != null) {
				result = cdao.DeleteCollect(weibo.getWid(), user.getUid());
			} else {
				result = 0;
			}
		}

		return result;
	}

	public int deleteCollectBywid(Weibo weibo) {
		int result = 0;
		if (weibo != null) {
			result = cdao.deleteCollectBywid(weibo.getWid());
		}
		return result;
	}

	@Override
	public boolean isCollect(Weibo weibo, User user) {
		boolean b = false;
		if (user == null) {
			return false;
		}
		b = cdao.isCollect(weibo.getWid(), user.getUid());
		return b;
	}

	@Override
	public int countCollect(Weibo weibo) {
		int result = 0;
		result = cdao.countCollect(weibo.getWid());
		return result;
	}

	@Override
	public List<Weibo> getByCollect(User user) {
		// 实例化
		List<Weibo> wList = new ArrayList<Weibo>();
		// 判断用户是否为空
		if (user != null) {
			// 查询用户所有的收藏
			List<Weibo_Collect> cList = cdao.getByCollect(user.getUid());
			// 遍历收藏信息
			for (Weibo_Collect weibo_Collect : cList) {
				// 根据wid查询微博信息并返回
				Weibo weibo = new WeiboServiceImpl().get(weibo_Collect.getWeibo().getWid());
				// 加入list
				wList.add(weibo);
			}
		}
		// 返回list
		return wList;
	}

}

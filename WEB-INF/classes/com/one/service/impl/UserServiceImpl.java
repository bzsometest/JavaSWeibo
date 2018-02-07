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
import com.one.service.UserService;
import com.one.tool.CheckUtil;
import com.one.tool.MD5Tools;
import com.one.tool.MySaveLog;
import com.one.tool.SendMailUtil;

public class UserServiceImpl implements UserService {
	UserDao userDao = new UserDaoImpl();
	WeiboDao wdao = new WeiboDaoImpl();
	UserDao udao = new UserDaoImpl();
	DiscussDao ddao = new DiscussDaoImpl();
	FansDao fdao = new FansDaoImpl();
	Weibo_CollectDao cdao = new Weibo_CollectDaoImpl();
	Weibo_ZanDao zdao = new Weibo_ZanDaoImpl();

	/**
	 * 注册
	 */
	@Override
	public int reg(User user) {
		int res = 0;
		String email = user.getEmail();
		String phone = user.getPhone();
		String userpass = user.getUserpass();
		String username = user.getUsername();
		if ((CheckUtil.checkEmail(email) || CheckUtil.checkMobileNumber(phone)) && CheckUtil.checkUserName(username)) {
			user.setUserpass(MD5Tools.MD5(userpass));
			res = userDao.reg(user);
			if (res > 0) {// 注册成功，则向用户发送注册邮件
				sendRegEmail(email, userpass);
			}
		} else {
			System.out
					.println("注册信息验证失败！" + CheckUtil.checkEmail(email) + CheckUtil.checkUserName(username) + username);
		}
		return res;
	}

	public void sendRegEmail(final String email, final String userpass) {
		new Thread() {// 启用后台线程发送邮件
			public void run() {
				SendMailUtil.send(email, "欢迎注册！", "你已成功注册！请保管好你的密码！<br>您的密码：" + userpass);
			};
		}.start();
	}

	@Override
	public User login(String account, String userPass) {
		String userpass_encode = MD5Tools.MD5(userPass);
		// 注意，这里只返回登录时输入的userpass，不是MD5,但是dao层需要用encode登录
		User user = userDao.login(account, userpass_encode);
		if (user != null) {
			user.setUserpass(userPass);
		}
		return user;
	}

	@Override
	public User getUser(String account) {
		return userDao.getUser(account);
	}

	@Override
	public int alter(User user, String userpass) {
		/* 修改信息前验证原始密码 */
		int result = 0;
		User oldUser = login(user.getUsername(), userpass);
		System.out.println(oldUser.toString());
		if (oldUser != null) {
			String email = user.getEmail();
			String phone = user.getPhone();
			String sign = user.getSign();
			userpass = user.getUserpass();
			oldUser.setUserpass(MD5Tools.MD5(userpass));
			// 判断输入的数据是否符合
			if (!"".equals(email)) {
				oldUser.setEmail(email);
			}
			if (!"".equals(phone)) {
				oldUser.setPhone(phone);
			}
			if (!"".equals(sign)) {
				oldUser.setSign(sign);
			}
			result = userDao.alter(oldUser);
		}
		return result;
	}

	@Override
	public List<User> getAll(User user) {
		User user2 = new UserServiceImpl().adminLogin(user.getUsername(), user.getUserpass());
		List<User> list = null;
		if (user2 != null) {
			list = userDao.getAll();
			MySaveLog.save("登录成功！");
		} else {
			MySaveLog.save("登录失败！");
		}
		return list;
	}

	@Override
	public boolean isHaveUsername(String username) {
		// 添加一个变量
		boolean b = false;
		// 验证用户名首字母不能为特殊字符并且不能是邮箱号
		if (CheckUtil.checkUserName(username) && !CheckUtil.checkEmail(username)) {
			b = userDao.isHaveUsername(username);
		}
		return b;
	}

	@Override
	public boolean isHavePhone(String phone) {
		// 添加一个变量
		boolean b = false;
		// 判断输入的手机号是否符合格式
		if (CheckUtil.checkMobileNumber(phone)) {
			// 验证手机号是否存在
			b = userDao.isHavePhone(phone);
		}
		// 返回结果
		return b;
	}

	@Override
	public boolean isHaveEmail(String email) {
		// 添加一个变量
		boolean b = false;
		// 判断输入的邮箱是否符合格式
		if (CheckUtil.checkEmail(email)) {
			// 验证邮箱是否存在
			b = userDao.isHaveEmail(email);
		}
		// 返回结果
		return b;
	}

	@Override
	public boolean isHaveAccount(String account) {
		return userDao.isHaveAccount(account);
	}

	@Override
	public User adminLogin(String account, String userPass) {
		String userpass_encode = MD5Tools.MD5(userPass);
		// 注意，这里只返回登录时输入的userpass，不是MD5,但是dao层需要用encode登录
		User user = userDao.adminLogin(account, userpass_encode);
		if (user != null) {
			user.setUserpass(userPass);
		}
		return user;
	}

	@Override
	public int adminDelete(User user, User admin) {
		String userpass_encode = MD5Tools.MD5(admin.getUserpass());
		int result = 0;
		// 注意，这里只返回登录时输入的userpass，不是MD5,但是dao层需要用encode登录
		User user2 = userDao.adminLogin(admin.getUsername(), userpass_encode);
		if (user2 != null && user != null) {
			// 删除粉丝
			fdao.deleteByUid(user.getUid());
			// 删除收藏
			cdao.deleteByUid(user.getUid());
			// 删除点赞
			zdao.deleteByUid(user.getUid());
			// 删除微博
			wdao.deleteByUid(user.getUid());
			// 删除用户
			result = udao.adminDelete(user.getUid());
		}
		return result;
	}

	@Override
	public User getByUid(int uid) {
		if (uid != 0) {
			return userDao.getByUid(uid);
		}
		return null;
	}

	@Override
	public int deleteUser(User admin, User user) {
		int result = 0;
		String userpass_encode = MD5Tools.MD5(admin.getUserpass());
		// 注意，这里只返回登录时输入的userpass，不是MD5,但是dao层需要用encode登录
		User user2 = userDao.adminLogin(admin.getUsername(), userpass_encode);
		if (user2 != null) {
			// 删除粉丝
			result = fdao.deleteByUid(user.getUid());
			// 删除收藏
			result = cdao.deleteByUid(user.getUid());
			// 删除点赞
			result = zdao.deleteByUid(user.getUid());
			// 删除微博
			result = wdao.deleteByUid(user.getUid());
			// 删除用户
			result = udao.adminDelete(user.getUid());
		}
		return result;
	}
}

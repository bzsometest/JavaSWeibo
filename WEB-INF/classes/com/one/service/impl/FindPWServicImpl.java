package com.one.service.impl;

import com.one.dao.FindePWDao;
import com.one.dao.UserDao;
import com.one.dao.impl.FindPWDaoImpl;
import com.one.dao.impl.UserDaoImpl;
import com.one.entity.User;
import com.one.service.FindPWService;
import com.one.tool.*;

/**
 * * 找回密码实现类
 * 
 * @author chao
 *
 */
public class FindPWServicImpl implements FindPWService {
	UserDao userDao = new UserDaoImpl();
	FindePWDao findePWDao = new FindPWDaoImpl();

	@Override
	public int sendEmail(String email, String webUrl) {
		// 产生八位验证码
		String emailCode = MyRandomCode.get(8);
		// 在数据库中创建找回密码列
		int res = findePWDao.sendEmail(email, emailCode);
		if (res > 0) {// 创建成功，发送邮件
			String url = webUrl + "?email=" + email + "&emailCode=" + emailCode;
			String urls = "<a href='" + url + "'>" + url + "<a/>";
			String urls2 = "<p><a href='" + url + "'>点击这里打开</a></p>";
			String content = "你正在找回密码，请点击链接来找回您的密码：<br>";
			String content2 = content + urls + urls2;
			// 发送邮件
			SendMailUtil.send(email, "找回用户密码:" + email, content2);
		}
		return res;
	}

	@Override
	public int sendPhone(String phone, String webUrl) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int emailCode(String email, String emailCode) {
		User user = userDao.getUser(email);
		if (user == null) {// 未查找到此邮箱
			return 0;
		}
		int res = findePWDao.emailCode(user.getUid(), emailCode);
		return res;
	}

	@Override
	public int phoneCode(String phone, String phoneCode) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int alterEmailpass(String email, String emailCode, String newPass) {
		// 验证邮箱，code代码是否正确！
		User user = userDao.getUser(email);
		if (user == null) {
			return 0;
		}
		int userID = findePWDao.emailCode(user.getUid(), emailCode);
		if (userID > 0) {// 数据库比对邮箱code正确！返回用户id
			newPass = MD5Tools.MD5(newPass);
			// 在数据库中修改密码
			int res = findePWDao.alterPass(userID, newPass);
			return res;
		}
		return 0;
	}

	@Override
	public int phoneCode(String phone, String phoneCode, String newPass) {
		// TODO Auto-generated method stub
		return 0;
	}

}

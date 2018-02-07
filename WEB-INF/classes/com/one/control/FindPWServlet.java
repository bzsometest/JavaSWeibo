package com.one.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.one.dao.FindePWDao;
import com.one.dao.impl.FindPWDaoImpl;
import com.one.dao.impl.UserDaoImpl;
import com.one.entity.User;
import com.one.service.FindPWService;
import com.one.service.impl.FindPWServicImpl;
import com.one.tool.MyJump;

/**
 * 找回密码页面控制 Servlet implementation class FindPWServlet
 */
@WebServlet({ "/findPW/findPW.do", "/findPW/findEmail.do", "/findPW/findPhone.do", "/findPW/findEmailCode.do",
		"/findPW/findPW.action", "/findAlterpass.action" })
public class FindPWServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FindePWDao findePWDao = new FindPWDaoImpl();
	FindPWService findPWService = new FindPWServicImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		if (uri.endsWith("findPW.action")) {
			findPW(request, response);
		} else if (uri.endsWith("findAlterpass.action")) {
			findAlterpass(request, response);
		} else if (uri.endsWith("findEmail.do")) {
			findEmail(request, response);
		} else if (uri.endsWith("findPhone.do")) {
			findPhone(request, response);
		} else if (uri.endsWith("findEmailCode.do")) {
			findEmailCode(request, response);
		} else if (uri.endsWith("findPhoneCode.do")) {
			findPhoneCode(request, response);
		}
	}

	private void findPW(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String account = request.getParameter("account");
		User user = new UserDaoImpl().getUser(account);
		// 判断是否存在此用户
		if (user == null) {
			System.out.println("findPW 账号不存在");
			MyJump jump = new MyJump();
			jump.set("找回密码", "账号不存在，请重新输入！", "正在返回...", "/findPW/");
			jump.jump(request, response);
		} else {
			// 存在用户
			request.setAttribute("user", user);
			request.getRequestDispatcher("findWay.jsp").forward(request, response);
		}
	}

	private void findEmail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		// 获得用户找回密码页面地址
		String webUrl = basePath + "findPW/findEmailCode.do";
		int res = findPWService.sendEmail(email, webUrl);
		if (res > 0) {// 发送邮箱链+接成功！
			MyJump jump = new MyJump();
			jump.set("邮箱找回密码", "找回密码邮件已发送到你的邮箱，请查收邮件。", "正在返回首页...", "/");
			jump.jump(request, response);
		} else {
			MyJump jump = new MyJump();
			jump.set("邮箱找回密码", "邮件发送失败，请更换其他方式找回密码或重试。", "正在返回首页...", "/");
			jump.jump(request, response);
		}
	}

	private void findPhone(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MyJump jump = new MyJump();
		jump.set("找回密码", "暂不支持手机找回！正在开发中，敬请期待。", "正在返回...", "/findPW/");
		jump.jump(request, response);
	}

	private void findEmailCode(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		String emailCode = request.getParameter("emailCode");
		/* 验证Code代码 */
		int res = findPWService.emailCode(email, emailCode);
		if (res > 0) {// 邮箱Code代码验证成功，显示重置密码页面
			session.setAttribute("email", email);
			session.setAttribute("emailCode", emailCode);
			request.setAttribute("email", email);
			request.setAttribute("emailCode", emailCode);
			// 请求转发到通过邮箱修改密码界面，用户输入新密码
			request.getRequestDispatcher("emailCode.jsp").forward(request, response);
		} else {
			MyJump jump = new MyJump();
			jump.set("邮箱找回密码", "此链接已失效！请重新找回密码。", "正在返回首页...", "/findPW/");
			jump.jump(request, response);
		}

	}

	private void findPhoneCode(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	// 修改用户密码
	private void findAlterpass(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		String emailCode = (String) session.getAttribute("emailCode");
		String newPass = request.getParameter("newpass");
		// 修改密码前再次验证code代码是否正确
		int res = new FindPWServicImpl().alterEmailpass(email, emailCode, newPass);
		if (res > 0) {
			MyJump jump = new MyJump();
			jump.set("密码修改结果-密码修改成功！", "密码修改成功！", "正在打开登录界面...", "/login/");
			jump.jump(request, response);
		} else {
			MyJump jump = new MyJump();
			jump.set("密码修改结果-修改失败", "密码修改失败！", "正在返回首页...", "/");
			jump.jump(request, response);
		}
	}

}

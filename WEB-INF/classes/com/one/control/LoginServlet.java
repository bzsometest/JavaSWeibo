package com.one.control;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.one.entity.*;
import com.one.service.UserService;
import com.one.service.impl.UserServiceImpl;
import com.one.tool.MyJump;

/**
 * 登录控制，cookie登录，验证身份， Servlet implementation class LoginServlet
 ***
 * 通过读取cookie获取用户信息
 */
@WebServlet({ "/login.action", "/login/login.action", "/getUser.do", "/loginOut.do" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserService userService = new UserServiceImpl();
	public final static int Max_Time = 60 * 60 * 24;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		if (uri.endsWith("login.action")) {
			// 获取并验证登陆验证
			setLogin(request, response);
		} else if (uri.endsWith("loginOut.do")) {
			// 退出登录
			loginOut(request, response);
		} else if (uri.endsWith("getUser.do")) {
			// 获得用户，返回json格式，用于前端显示用户信息
			getUser(request, response);
		}

	}

	/** 退出登录 */
	private void loginOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("account")) {
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
				if (cookie.getName().equals("userpass")) {
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
		}
		response.sendRedirect(request.getContextPath());
	}

	/** 获得用户，返回json格式，用于前端显示用户信息 */
	private void getUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();

		User user = LoginServlet.login(request, response);

		out.println("[");
		out.print("{");
		if (user != null) {// 用户名可能是null,需要区分
			out.print("state:true,");
			out.print("username:'" + user.getUsername() + "',");
		} else {
			out.print("state:false,");
			out.print("username:'游客',");
		}
		out.println("},");
		out.println("]");

	}

	/** 用于接收前端登录信息，获取并验证登陆验证, 设置到cookie中 */
	private void setLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String account = request.getParameter("account");
		String userpass = request.getParameter("userpass");
		System.out.println("set:" + account + userpass);
		User user = userService.login(account, userpass);
		if (user == null) {
			// 前端已验证登录。提交到后台，正常情况下应该验证成功。
			// 这里后端验证失败，则是非法请求，有黑客攻击嫌疑
			MyJump jump = new MyJump();
			jump.set("用户登录", "非法登录！系统已记录你的登录信息！！", "正在返回首页...", "/");
			jump.jump(request, response);
		} else {
			// 登录成功！讲用户信息存到cookie中
			setCookie(request, response, account, userpass);
			// 转到.do
			response.sendRedirect(request.getContextPath() + "/userCenter.do");
		}
	}

	/**
	 * 获取当前登录用户，加载cookie中的用户信息
	 */
	public static User login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取cookie信息，判断用户是否已经登陆过
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charser=utf-8");
		Cookie[] cookies = request.getCookies();
		String account = null;
		String userPass = null;
		// 直接遍历cookies可能出错，cookies可能为空！
		if (cookies == null) {// 为空，则提前返回
			return null;
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("account")) {
				account = cookie.getValue();
				account = URLDecoder.decode(account, "utf-8");
			}
			if (cookie.getName().equals("userpass")) {
				userPass = cookie.getValue();
			}
		}
		// 验证登录信息
		User user = userService.login(account, userPass);
		return user;
	}

	/**
	 * 设置用户cookie信息
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String account,
			String userpass) throws UnsupportedEncodingException {
		// 讲用户信息存到cookie中
		account = URLEncoder.encode(account, "utf-8");
		Cookie accountCookie = new Cookie("account", account);
		Cookie userPassCookie = new Cookie("userpass", userpass);
		accountCookie.setMaxAge(Max_Time);
		accountCookie.setPath("/");
		userPassCookie.setMaxAge(Max_Time);
		userPassCookie.setPath("/");
		response.addCookie(accountCookie);
		response.addCookie(userPassCookie);
	}
}

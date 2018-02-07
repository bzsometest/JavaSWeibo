package com.one.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.one.entity.*;
import com.one.service.*;
import com.one.service.impl.*;
import com.one.tool.MyJump;
import com.one.tool.MySaveLog;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({ "/admin/test.do", "/admin/isLogin.do", "/admin/login.action", "/admin/loginOut.do", "/admin/admin.do",
		"/admin/addUser.do", "/admin/deleteUser.do", "/admin/deleteWeibo.do", "/admin/deleteDiscuss.do",
		"/admin/userAll.do", "/admin/weiboAll.do" })
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	WeiboService weiboService = new WeiboServiceImpl();
	static UserService userService = new UserServiceImpl();
	public final static int Max_Time = 60 * 60 * 24;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		if (uri.endsWith("login.action")) {
			/* 从前台获取登录信息，并储存在cookie中 */
			setLogin(request, response);
		} else if (uri.endsWith("isLogin.do")) {
			/* 从前台获取登录信息，并储存在cookie中 */
			isLogin(request, response);
		} else if (uri.endsWith("admin.do")) {
			/* 打开登录后台管理界面 */
			admin(request, response);
		} else if (uri.endsWith("loginOut.do")) {
			// 退出登录
			loginOut(request, response);
		} else if (uri.endsWith("deleteDiscuss.do")) {
			deleteDiscuss(request, response);
		} else if (uri.endsWith("deleteUser.do")) {
			deleteUser(request, response);
		} else if (uri.endsWith("deleteWeibo.do")) {
			deleteWeibo(request, response);
		}
	}

	private void admin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String fileName = request.getServletContext().getRealPath("/") + "log.txt";
		MySaveLog.setFile(fileName);
		User admin = login(request, response);
		if (admin == null) {
			response.sendRedirect(request.getContextPath() + "/admin/");
			return;
		}
		List<User> userList = new UserServiceImpl().getAll(admin);
		List<Weibo> weiboList = new WeiboServiceImpl().getAll();
		List<Discuss> didcussList = new DiscussServiceImpl().getAll(admin);

		request.setAttribute("userList", userList);
		request.setAttribute("weiboList", weiboList);
		request.setAttribute("discussList", didcussList);

		if (userList != null) {
			MySaveLog.save(userList.toString());
		} else {
			MySaveLog.save("null");
		}
		request.getRequestDispatcher("/admin/admin.jsp").forward(request, response);
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		User admin = login(request, response);
		if (admin == null) {
			return;
		}
		String uidStr = request.getParameter("uid");
		int uid = Integer.valueOf(uidStr);
		User user = userService.getByUid(uid);
		int res = userService.adminDelete(user, admin);
		if (res > 0) {
			System.out.println("删除user成功！");
		} else {
			System.out.println("删除user失败！");
		}
		response.sendRedirect(request.getContextPath() + "/admin/admin.do");
	}

	private void deleteDiscuss(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		User admin = login(request, response);
		if (admin == null) {
			return;
		}
		DiscussService discussService = new DiscussServiceImpl();
		String didStr = request.getParameter("did");
		int did = Integer.valueOf(didStr);
		Discuss discuss = discussService.getByDid(did);
		int res = discussService.adminDelete(discuss, admin);
		if (res > 0) {
		} else {
			System.out.println("删除discuss失败！");
		}
		response.sendRedirect(request.getContextPath() + "/admin/admin.do");
	}

	private void deleteWeibo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		User admin = login(request, response);
		if (admin == null) {
			return;
		}
		String widStr = request.getParameter("wid");
		int wid = Integer.valueOf(widStr);
		Weibo weibo = weiboService.get(wid);
		int res = weiboService.adminDelete(weibo, admin);
		if (res > 0) {
			System.out.println("删除weibo成功！");
		} else {
			System.out.println("删除weibo失败！");
		}
		response.sendRedirect(request.getContextPath() + "/admin/admin.do");
	}

	/** 退出登录 */
	private void loginOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("admin")) {
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
				if (cookie.getName().equals("pass")) {
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
		}
		response.sendRedirect(request.getContextPath());
	}

	/** 用于接收前端登录信息，获取并验证登陆验证, 设置到cookie中 */
	private void setLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String admin = request.getParameter("account");
		String pass = request.getParameter("userpass");
		User user = userService.adminLogin(admin, pass);
		if (user == null) {
			// 前端已验证登录。提交到后台，正常情况下应该验证成功。
			// 这里后端验证失败，则是非法请求，有黑客攻击嫌疑
			MyJump jump = new MyJump();
			jump.set("用户登录", "非法登录！系统已记录你的登录信息！！", "正在返回到管理界面...", "/admin/");
			jump.jump(request, response);
		} else {
			// 登录成功！讲用户信息存到cookie中
			setCookie(request, response, admin, pass);
			// 转到.do
			response.sendRedirect(request.getContextPath() + "/admin/admin.do");
		}
	}

	/**
	 * 获取当前登录用户，加载cookie中的用户信息
	 */
	public static User login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取cookie信息，判断用户是否已经登陆过
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charser=utf-8");
		Cookie[] cookies = request.getCookies();
		String admin = null;
		String pass = null;
		// 直接遍历cookies可能出错，cookies可能为空！
		if (cookies == null) {// 为空，则提前返回
			return null;
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("admin")) {
				admin = cookie.getValue();
			}
			if (cookie.getName().equals("pass")) {
				pass = cookie.getValue();
			}
		}
		// 验证登录信息
		User user = userService.adminLogin(admin, pass);
		return user;
	}

	/**
	 * 设置用户cookie信息
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String admin, String pass)
			throws UnsupportedEncodingException {
		// 讲用户信息存到cookie中
		request.setCharacterEncoding("UTF-8");
		Cookie adminCookie = new Cookie("admin", admin);
		Cookie passCookie = new Cookie("pass", pass);
		adminCookie.setMaxAge(Max_Time);
		adminCookie.setPath("/");
		passCookie.setMaxAge(Max_Time);
		passCookie.setPath("/");
		response.addCookie(adminCookie);
		response.addCookie(passCookie);
	}

	private void isLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		String admin = request.getParameter("account");
		String pass = request.getParameter("userpass");
		User user = userService.adminLogin(admin, pass);
		System.out.println(admin + ":" + pass);
		PrintWriter out = response.getWriter();
		if (user != null) {
			out.println("<response>");
			out.println("<isLogin>" + true + "</isLogin>");
			out.println("</response>");
		} else {
			out.println("<response>");
			out.println("<isLogin>" + false + "</isLogin>");
			out.println("</response>");
		}
	}
}

package com.one.control;

import java.io.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.commons.lang.StringEscapeUtils;

import com.one.entity.*;
import com.one.service.*;
import com.one.service.impl.*;
import com.one.tool.MyJump;

/**
 * 评论后天控制servlet Servlet implementation class Discuss
 */
@WebServlet({ "/discussAdd.do", "/addDiscuss.action", "/discuss.do" })
public class DiscussServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DiscussService discussService = new DiscussServiceImpl();
	WeiboService weiboService = new WeiboServiceImpl();

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

		if (uri.endsWith("discussAdd.do")) {
			// 发布评论,Ajax请求
			discussAdd(request, response);

		} else if (uri.endsWith("addDiscuss.action")) {
			// 发布评论,form表单
			addDiscuss(request, response);

		} else if (uri.endsWith("discuss.do")) {
			// 获得此微博的评论
			discuss(request, response);

		}
	}

	private void addDiscuss(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String widStr = request.getParameter("wid");
		String content = request.getParameter("content");
		String tourl = request.getParameter("tourl");

		User my = LoginServlet.login(request, response);
		if (my == null) {
			MyJump jump = new MyJump();
			jump.set("发布评论", "您未登陆，请先登陆！", "正在打开登录页...", "/login/");
			jump.jump(request, response);
			return;// 提前终止
		}
		if (content != null) {
			if (content.length() >= 256) {
				MyJump jump = new MyJump();
				jump.set("发布评论", "发布微博失败，您的字数过长！请重新输入。", "正在返回...", "/" + tourl);
				jump.jump(request, response);
				return;
			}
		}
		int wid = Integer.valueOf(widStr);
		Weibo weibo = weiboService.get(wid);

		Discuss discuss = new Discuss();
		discuss.setContent(content);
		discuss.setWeibo(weibo);
		discuss.setUser(my);
		int res = discussService.add(discuss);

		if (res > 0) {
			MyJump jump = new MyJump();
			jump.set("发布评论", "发布评论成功！", "正在返回...", "/" + tourl);
			jump.jump(request, response);
		} else {
			MyJump jump = new MyJump();
			jump.set("发布评论", "发布评论失败！", "正在返回...", "/" + tourl);
			jump.jump(request, response);
		}
	}

	private void discuss(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String widStr = request.getParameter("wid");
		int wid = Integer.valueOf(widStr);
		Weibo weibo = new WeiboServiceImpl().get(wid);
		List<Discuss> list = discussService.getWid(weibo);

		out.println("[");
		for (Discuss discuss : list) {
			String content = StringEscapeUtils.escapeHtml(discuss.getContent());
			out.print("{");
			out.print("uid:'" + discuss.getUser().getUid() + "',");
			out.print("did:'" + discuss.getDid() + "',");
			out.print("content:'" + content + "',");
			out.print("username:'" + discuss.getUser().getUsername() + "',");
			out.print("time:'" + discuss.getTime() + "',");
			out.println("},");
		}
		out.print("]");
	}

	/**
	 * 增加评论
	 * 
	 */
	private void discussAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String content = request.getParameter("content");
		String widStr = request.getParameter("wid");

		if (content != null) {
			System.out.println("评论长度:" + content.length());
			if (content.length() >= 256) {
				System.out.println("发布评论失败，字数过长！");
			}
		}

		int wid = Integer.valueOf(widStr);
		Weibo weibo = weiboService.get(wid);
		User user = LoginServlet.login(request, response);
		Discuss discuss = new Discuss();
		discuss.setWeibo(weibo);
		discuss.setUser(user);
		discuss.setContent(content);
		int res = discussService.add(discuss);
		if (res > 0) {
			System.out.println("发布评论成功！");
		} else {
			System.out.println("发布评论失败！");
		}
	}

}

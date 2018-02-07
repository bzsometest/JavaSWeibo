package com.one.control;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.commons.lang.StringEscapeUtils;

import com.one.entity.*;
import com.one.service.WeiboService;
import com.one.service.impl.*;
import com.one.tool.MyJump;
import com.one.tool.MySaveLog;

/**
 * 有关微博数据接收，微博数据显示，微博转发等 Servlet implementation class addWeibo
 */
@MultipartConfig
@WebServlet({ "/addZan.do", "/addCollect.do", "/forward.do", "/weiboAll.do", "/weiboCollect.do", "/weiboFollow.do",
		"/weiboAdd.action", "/addWeibo.action" })
public class WeiboServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
		response.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		String fileName = request.getServletContext().getRealPath("/") + "log.txt";
		MySaveLog.setFile(fileName);
		if (uri.endsWith("addZan.do")) {
			addZan(request, response);
		} else if (uri.endsWith("addCollect.do")) {
			// 增加微博收藏
			addCollect(request, response);
		} else if (uri.endsWith("forward.do")) {
			// 转发微博，跳转
			weiboForward(request, response);
		} else if (uri.endsWith("weiboAdd.action")) {
			// 发布微博借口，Ajax获取前端传过来的微博内容
			weiboAdd(request, response);
		} else if (uri.endsWith("weiboAll.do")) {
			// 本站所有的微博，在主页显示微博，返回json
			weiboAll(request, response);
		} else if (uri.endsWith("weiboCollect.do")) {
			// 我收藏的微博，在收藏界面显示为微博，返回json
			weiboCollect(request, response);
		} else if (uri.endsWith("weiboFollow.do")) {
			// 我关注的微博，在收藏界面显示为微博，返回json
			weiboFollow(request, response);
		} else if (uri.endsWith("addWeibo.action")) {
			// 发布微博借口，获取前端传过来的微博内容,并跳转页面
			addWeibo(request, response);
		}
		// 首页跳转控制，请查看UserCenterServlet
	}

	/***
	 * 发布微博借口，获取前端传过来的微博内容,并跳转页面
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	private void addWeibo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String content = request.getParameter("content");
		String tourl = request.getParameter("tourl");

		User my = LoginServlet.login(request, response);
		if (my == null) {
			MyJump jump = new MyJump();
			jump.set("发布微博", "您未登陆，请先登陆！", "正在打开登录页...", "/login/");
			jump.jump(request, response);
			return;// 提前终止
		}

		if (content != null) {
			if (content.length() >= 1000) {
				MyJump jump = new MyJump();
				jump.set("发布微博", "发布微博失败，您的字数过长！请重新输入。", "正在返回...", "/" + tourl);
				jump.jump(request, response);
				return;
			}
		}
		Weibo weibo = new Weibo();
		weibo.setContent(content);
		weibo.setUser(my);
		int res = weiboService.add(weibo);

		if (res > 0) {
			MyJump jump = new MyJump();
			jump.set("发布微博", "发布微博成功！", "正在返回...", "/" + tourl);
			jump.jump(request, response);
		} else {
			MyJump jump = new MyJump();
			jump.set("发布微博", "发布微博失败！", "正在返回...", "/" + tourl);
			jump.jump(request, response);
		}
	}

	private void addZan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String widStr = request.getParameter("wid");
		int wid = Integer.valueOf(widStr);
		String iszan = request.getParameter("iszan");
		Weibo weibo = weiboService.get(wid);
		User user = LoginServlet.login(request, response);
		if (user != null) {
			System.out.println("addZan 开始点赞：");
			if ("true".equals(iszan)) {
				new Weibo_ZanServiceImpl().addZan(weibo, user);
			} else {// 取消赞
				new Weibo_ZanServiceImpl().cancelZan(weibo, user);
			}
		}
	}

	private void addCollect(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String widStr = request.getParameter("wid");
		int wid = Integer.valueOf(widStr);
		String iscollect = request.getParameter("iscollect");
		Weibo weibo = weiboService.get(wid);
		User user = LoginServlet.login(request, response);
		if (user != null) {
			if ("true".equals(iscollect)) {
				new Weibo_CollectServiceImpl().addCollect(weibo, user);
			} else {// 取消收藏
				new Weibo_CollectServiceImpl().cancleCollect(weibo, user);
			}
		}
	}

	private void weiboForward(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String widStr = request.getParameter("wid");
		int wid = Integer.valueOf(widStr);
		Weibo weibo = weiboService.get(wid);
		User my = LoginServlet.login(request, response);
		if (my == null) {
			MyJump jump = new MyJump();
			jump.set("转发微博", "您未登陆，请先登陆！", "正在打开登录页...", "/login/");
			jump.jump(request, response);
			return;// 提前终止
		}
		weibo.setUser(my);
		String content = StringEscapeUtils.unescapeHtml(weibo.getContent());
		weibo.setContent("转发微博：" + content);
		int res = weiboService.add(weibo);
		if (res > 0) {
			MyJump jump = new MyJump();
			jump.set("转发微博", "转发微博成功！", "正在返回首页", "/");
			jump.jump(request, response);
		} else {
			MyJump jump = new MyJump();
			jump.set("转发微博", "转发微博失败！", "正在返回首页", "/");
			jump.jump(request, response);
		}
	}

	/**
	 * 本站所有微博
	 * 
	 * @throws ServletException
	 */
	private void weiboAll(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		User user = LoginServlet.login(request, response);
		List<Weibo> list = weiboService.getAll();
		showWeibo(list, out, user);
	}

	/**
	 * 我关注的微博，返回json
	 */
	private void weiboFollow(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		User user = LoginServlet.login(request, response);
		List<Weibo> list = weiboService.getByMid(user);
		showWeibo(list, out, user);
	}

	/**
	 * 我收藏的微博，返回json
	 */
	private void weiboCollect(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		User user = LoginServlet.login(request, response);
		List<Weibo> list = new Weibo_CollectServiceImpl().getByCollect(user);
		showWeibo(list, out, user);
	}

	/**
	 * 新增发布微博
	 */
	private void weiboAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String imgPath = UpImgFile.getFile(request, response);
		String content = request.getParameter("content");
		User user = LoginServlet.login(request, response);
		if (user == null) {
			MyJump jump = new MyJump();
			jump.set("发布微博", "发布微博失败，您未登陆！", "正在打开登录界面...", "/login/");
			jump.jump(request, response);
			return;
		}
		if (content != null) {
			if (content.length() >= 1000) {
				MyJump jump = new MyJump();
				jump.set("发布微博", "发布微博失败，您的字数过长！请重新输入。", "正在返回...", "/");
				jump.jump(request, response);
				return;
			}
		}
		Weibo weibo = new Weibo();
		weibo.setUser(user);
		weibo.setImg(imgPath);
		weibo.setContent(replaceBlank(content));
		// 去掉前段传过来的空格
		int res = weiboService.add(weibo);
		if (res > 0) {
			MyJump jump = new MyJump();
			jump.set("发布微博", "发布微博成功!", "正在返回...", "/");
			jump.jump(request, response);
		} else {
			MyJump jump = new MyJump();
			jump.set("发布微博", "发布微博失败，可能是字数过长！！", "正在返回...", "/");
			jump.jump(request, response);
		}
	}

	/** 将微博内容显示出来 */
	private void showWeibo(List<Weibo> list, PrintWriter out, User user) {
		out.println("[");
		for (Weibo weibo : list) {
			int zancount = new Weibo_ZanServiceImpl().countZan(weibo);
			boolean iszan = new Weibo_ZanServiceImpl().isZan(weibo, user);
			boolean focuson = new FansServiceImpl().isFans(weibo.getUser(), user);
			boolean iscollect = new Weibo_CollectServiceImpl().isCollect(weibo, user);
			String content = StringEscapeUtils.escapeHtml(weibo.getContent());
			String img = weibo.getImg();
			if (img == null) {
				img = "";
			}

			out.print("{");
			out.print("uid:'" + weibo.getUser().getUid() + "',");
			out.print("wid:'" + weibo.getWid() + "',");
			out.print("content:'" + content + "',");
			out.print("username:'" + weibo.getUser().getUsername() + "',");
			out.print("time:'" + weibo.getTimeString() + "',");
			out.print("focuson:" + focuson + ",");
			out.print("iszan:" + iszan + ",");
			out.print("iscollect:" + iscollect + ",");
			out.print("zancount:'" + zancount + "',");
			out.print("img:'" + img + "',");
			out.println("},");
		}
		out.println("]");
	}

	/**
	 * 当前用户是否关注此微博
	 * 
	 * @param weibo
	 * @return
	 */
	public boolean isFans(Weibo weibo, User fUser) {
		User user = weibo.getUser();
		return new FansServiceImpl().isFans(user, fUser);
	}

	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\r\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("<br>");

			p = Pattern.compile("\'");
			m = p.matcher(dest);
			dest = m.replaceAll("&apos;");

			p = Pattern.compile("\"");
			m = p.matcher(dest);
			dest = m.replaceAll("&quot;");
		}
		return dest;
	}
}

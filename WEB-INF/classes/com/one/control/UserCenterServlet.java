package com.one.control;

import java.io.IOException;
import java.util.ArrayList;
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
 * 有关用户个人微博的操作 Servlet implementation class UserCenterServlet
 */
@WebServlet({ "/myWeibo.do", "/myFollow.do", "/myCollect.do", "/userCenter.do", "/userWeibo.do", "/userFollow.do",
		"/deleteWeibo.do", "/deleteDiscuss.do" })
public class UserCenterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userService = new UserServiceImpl();
	WeiboService weiboService = new WeiboServiceImpl();
	DiscussService discussService = new DiscussServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		if (uri.endsWith("myWeibo.do")) {
			// 页面跳转至我的微博
			myWeibo(request, response);
		} else if (uri.endsWith("myFollow.do")) {
			// 页面跳转至我关注的微博
			myFollow(request, response);
		} else if (uri.endsWith("myCollect.do")) {
			// 页面跳转至我收藏的微博
			myCollect(request, response);
		} else if (uri.endsWith("userCenter.do")) {
			userCenter(request, response);
		} else if (uri.endsWith("userWeibo.do")) {
			// 页面跳转至某个用户的微博
			userWeibo(request, response);
		} else if (uri.endsWith("userFollow.do")) {
			// 关注此用户
			userFollow(request, response);
		} else if (uri.endsWith("deleteWeibo.do")) {
			// 用户删除微博
			deleteWeibo(request, response);
		} else if (uri.endsWith("deleteDiscuss.do")) {
			// 用户删除评论
			deleteDiscuss(request, response);
		}
	}

	private void deleteDiscuss(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = LoginServlet.login(request, response);
		if (user == null) {
			MyJump jump = new MyJump();
			jump.set("删除评论", "您的身份已失效，请先登陆", "正在打开登录页...", "/login/");
			jump.jump(request, response);
			return;
		}
		String didStr = request.getParameter("did");
		int did = Integer.valueOf(didStr);
		Discuss discuss = discussService.getByDid(did);
		int res = discussService.DeleteByOwn(discuss, user);
		if (res > 0) {
			MyJump jump = new MyJump();
			jump.set("删除评论", "删除评论成功！", "正在返回我的微博", "/myWeibo.do");
			jump.jump(request, response);
		} else {
			MyJump jump = new MyJump();
			jump.set("删除评论", "删除评论失败！", "正在返回我的微博", "/myWeibo.do");
			jump.jump(request, response);
		}
	}

	private void deleteWeibo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = LoginServlet.login(request, response);
		if (user == null) {
			MyJump jump = new MyJump();
			jump.set("删除微博", "您的身份已失效，请先登陆", "正在打开登录页...", "/login/");
			jump.jump(request, response);
			return;
		}

		String widStr = request.getParameter("wid");
		int wid = Integer.valueOf(widStr);
		Weibo weibo = weiboService.get(wid);
		int res = weiboService.delete(weibo, user);
		if (res > 0) {
			MyJump jump = new MyJump();
			jump.set("删除微博", "删除微博成功！", "正在返回我的微博", "/myWeibo.do");
			jump.jump(request, response);
		} else {
			MyJump jump = new MyJump();
			jump.set("删除微博", "删除微博失败！", "正在返回我的微博", "/myWeibo.do");
			jump.jump(request, response);
		}
	}

	/**
	 * 打开jsp页面，显示我的微博
	 */
	private void myWeibo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = LoginServlet.login(request, response);
		if (user != null) {
			request.setAttribute("user", user);
			List<User> fansList = new FansServiceImpl().getFans(user);
			List<User> followList = new FansServiceImpl().getFollow(user);

			// 生成weiboshow集合
			List<Weibo> weiboList = weiboService.get(user);
			List<WeiboShow> weiboShowList = new ArrayList<>();
			for (Weibo weibo : weiboList) {
				int zancount = new Weibo_ZanServiceImpl().countZan(weibo);
				String content = StringEscapeUtils.escapeHtml(weibo.getContent());
				weibo.setContent(content);
				WeiboShow weiboShow = new WeiboShow();
				weiboShow.setWeibo(weibo);
				weiboShow.setZancount(zancount);
				weiboShow.setDiscuss(new DiscussServiceImpl().getWid(weibo));
				weiboShowList.add(weiboShow);
			}

			request.setAttribute("weiboShowList", weiboShowList);
			request.setAttribute("fansList", fansList);
			request.setAttribute("followList", followList);
			request.getRequestDispatcher("/myWeibo.jsp").forward(request, response);
		} else {
			MyJump jump = new MyJump();
			jump.set("我的微博", "您未登陆，请先登陆！", "正在打开登录页...", "/login/");
			jump.jump(request, response);
		}
	}

	private void myCollect(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = LoginServlet.login(request, response);
		if (user != null) {
			response.sendRedirect(request.getContextPath() + "/myCollect.html");
		} else {
			MyJump jump = new MyJump();
			jump.set("我的微博", "您未登陆，请先登陆！", "正在打开登录页...", "/login/");
			jump.jump(request, response);
		}
	}

	private void myFollow(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		User my = LoginServlet.login(request, response);
		if (my != null) {
			response.sendRedirect(request.getContextPath() + "/myFollow.html");
		} else {
			MyJump jump = new MyJump();
			jump.set("我的微博", "您未登陆，请先登陆！", "正在打开登录页...", "/login/");
			jump.jump(request, response);
		}
	}

	private void userFollow(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String uidStr = request.getParameter("uid");
		int uid = Integer.valueOf(uidStr);
		User my = LoginServlet.login(request, response);
		if (my == null) {
			MyJump jump = new MyJump();
			jump.set("关注用户", "您未登陆，请先登陆！", "正在打开登录页...", "/login/");
			jump.jump(request, response);
			return;// 提前终止
		}

		User user = userService.getByUid(uid);
		int res = new FansServiceImpl().addFans(user, my);
		if (res > 0) {
			MyJump jump = new MyJump();
			jump.set("关注用户", "关注用户成功！", "正在返回用户微博...", "/userWeibo.do?uid=" + uid);
			jump.jump(request, response);
		} else {
			MyJump jump = new MyJump();
			jump.set("关注用户", "关注用户失败！", "正在返回用户微博...", "/userWeibo.do?uid=" + uid);
			jump.jump(request, response);
		}
	}

	private void userWeibo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uidStr = request.getParameter("uid");
		int uid = Integer.valueOf(uidStr);
		User user = userService.getByUid(uid);
		User my = LoginServlet.login(request, response);
		if (user != null) {
			request.setAttribute("my", my);
			request.setAttribute("user", user);
			List<User> fansList = new FansServiceImpl().getFans(user);
			List<User> followList = new FansServiceImpl().getFollow(user);

			// 生成weiboshow集合
			List<Weibo> weiboList = new WeiboServiceImpl().get(user);
			List<WeiboShow> weiboShowList = new ArrayList<>();
			for (Weibo weibo : weiboList) {
				String content = StringEscapeUtils.escapeHtml(weibo.getContent());
				weibo.setContent(content);
				WeiboShow weiboShow = new WeiboShow();
				weiboShow.setWeibo(weibo);

				// 对评论的content转码后，再传给前端
				List<Discuss> dList = new DiscussServiceImpl().getWid(weibo);
				for (Discuss discuss : dList) {
					String dContent = StringEscapeUtils.escapeHtml(discuss.getContent());
					discuss.setContent(dContent);
				}

				weiboShow.setDiscuss(dList);
				weiboShowList.add(weiboShow);
			}

			request.setAttribute("weiboShowList", weiboShowList);
			request.setAttribute("fansList", fansList);
			request.setAttribute("followList", followList);
			request.getRequestDispatcher("/userWeibo.jsp").forward(request, response);
		} else {
			MyJump jump = new MyJump();
			jump.set("我的微博", "您未登陆，请先登陆！", "正在打开登录页...", "/login/");
			jump.jump(request, response);
		}
	}

	private void userCenter(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 登录身份验证，加载cookie中的信息
		User user = LoginServlet.login(request, response);
		if (user != null) {// 用户信息验证成功！
			// 用户信息保存至request中，方便前端读取。
			request.setAttribute("user", user);
			request.getRequestDispatcher("/userCenter.jsp").forward(request, response);
		} else {
			MyJump jump = new MyJump();
			jump.set("提示信息", "用户信息已失效，请重新登录!", "正在打开登录界面...", "/login/");
			jump.jump(request, response);
		}
	}
}

package com.one.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.one.entity.User;
import com.one.entity.Weibo;
import com.one.service.*;
import com.one.service.impl.*;

/**
 * Servlet implementation class FansServlet
 */
@WebServlet("/fansAdd.do")
public class FansServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FansService fansService = new FansServiceImpl();
	WeiboService weiboService = new WeiboServiceImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.endsWith("fansAdd.do")) {
			fansAdd(request, response);
		}
	}

	private void fansAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String widStr = request.getParameter("wid");
		String focuson = request.getParameter("focuson");

		int wid;
		try {
			wid = Integer.valueOf(widStr);
		} catch (Exception e) {
			return;
		}

		Weibo weibo = weiboService.get(wid);
		User user = weibo.getUser();
		User fUser = LoginServlet.login(request, response);
		if (fUser == null) {
			return;
		}

		if ("true".equals(focuson)) {
			int res = fansService.addFans(user, fUser);
			if (res > 0) {
				System.out.println("关注用户成功！");
			} else {
				System.out.println("关注用户失败！");
			}
		} else {
			int res = fansService.deleteFans(user, fUser);
			if (res > 0) {
				System.out.println("取消关注成功！");
			} else {
				System.out.println("取消关注失败！");
			}
		}

	}
}

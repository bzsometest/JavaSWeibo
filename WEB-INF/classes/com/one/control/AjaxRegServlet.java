package com.one.control;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import com.one.entity.User;
import com.one.service.UserService;
import com.one.service.impl.UserServiceImpl;

/**
 * AJAX验证，主要用户登录注册，前端使用
 * 
 * @author QingNan
 *
 */
@WebServlet({ "/ajax.do", "/isLogin.do", "/isHaveAccount.do", "/isHaveUsername.do", "/isHaveEmail.do",
		"/isHavePhone.do", "/login/isLogin.do", "/login/isHaveAccount.do", "/login/isHaveUsername.do",
		"/login/isHaveEmail.do", "/login/isHavePhone.do" })
public class AjaxRegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		if (uri.endsWith("isLogin.do")) {
			// 登陆验证
			isLogin(request, response);

		} else if (uri.endsWith("isHaveAccount.do")) {
			// 判断账号是否已存在，主要用于注册
			isHaveAccount(request, response);

		} else if (uri.endsWith("isHaveUsername.do")) {
			isHaveUsername(request, response);
			// 判断是否存在用户，主要用于注册

		} else if (uri.endsWith("isHaveEmail.do")) {
			// 判断是否存在邮箱，主要用于注册
			isHaveEmail(request, response);

		} else if (uri.endsWith("isHavePhone.do")) {
			// 判断手机是否已存在，主要用于注册
			isHavePhone(request, response);
		}
	}

	private void isHavePhone(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/plain;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");

		String value = request.getParameter("value");
		boolean flag = userService.isHavePhone(value);
		String result = String.valueOf(!flag);
		PrintWriter out = response.getWriter();
		out.println("[{value:" + result + "}]");
	}

	private void isHaveEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/plain;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");

		String value = request.getParameter("value");
		boolean flag = userService.isHaveEmail(value);
		String result = String.valueOf(!flag);
		PrintWriter out = response.getWriter();
		out.println("[{value:" + result + "}]");
	}

	private void isHaveAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");

		String value = request.getParameter("value");
		boolean flag = userService.isHaveAccount(value);
		String result = String.valueOf(!flag);
		PrintWriter out = response.getWriter();
		out.println("[{value:" + result + "}]");
	}

	private void isHaveUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/plain;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");

		String value = request.getParameter("value");
		boolean flag = userService.isHaveUsername(value);
		String result = String.valueOf(!flag);
		PrintWriter out = response.getWriter();
		out.println("[{value:" + result + "}]");
	}

	private void isLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");

		String account = request.getParameter("account");
		String userpass = request.getParameter("userpass");
		User user = userService.login(account, userpass);
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

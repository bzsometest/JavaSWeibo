package com.one.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.one.entity.User;
import com.one.service.UserService;
import com.one.service.impl.UserServiceImpl;
import com.one.tool.MyJump;

/**
 * Servlet implementation class userAlterServlet
 */
@WebServlet({ "/userAlter.do", "/userAlter.action" })
public class UserAlterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserService userService = new UserServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		User user = LoginServlet.login(request, response);
		if (user != null) {
			// 用户信息保存至request中，方便前端读取。
			request.setAttribute("user", user);
			request.getRequestDispatcher("userAlter.jsp").forward(request, response);
		} else {
			MyJump jump = new MyJump();
			jump.set("提示信息", "用户信息已失效，请重新登录!", "正在打开登录界面...", "/login/");
			jump.jump(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 处理提交的修改信息
		response.setCharacterEncoding("UTF-8");
		User user = LoginServlet.login(request, response);

		// 从修改页面中获得信息
		String userpass = request.getParameter("userpass");
		String newPass = request.getParameter("newpass");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String sign = request.getParameter("sign");

		User rUser = userService.login(user.getUsername(), userpass);
		if (rUser == null) {// 原始密码输入错误！
			MyJump jump = new MyJump();
			jump.set("修改信息", "修改信息失败！原始密码输入错误！", "正在返回...", "/userAlter.do");
			jump.jump(request, response);

			/* 此处应该return，防止继续向下执行 */
			return;
		}

		// 判断是否为空，为空则不执行修改
		if (!"".equals(newPass)) {
			user.setUserpass(newPass);
		}
		if (!"".equals(phone)) {
			user.setPhone(phone);
		}
		if (!"".equals(email)) {
			user.setEmail(email);
		}
		if (!"".equals(sign)) {
			user.setSign(sign);
		}

		// 调用修改信息接口
		int rs = userService.alter(user, userpass);
		if (rs > 0) {
			MyJump jump = new MyJump();
			jump.set("修改信息", "修改信息成功！", "正在返回...", "/userCenter.do");
			jump.jump(request, response);
		} else {
			MyJump jump = new MyJump();
			jump.set("修改信息", "原密码验证成功，但修改信息失败！", "正在返回...", "/userCenter.do");
			jump.jump(request, response);
		}
	}
}

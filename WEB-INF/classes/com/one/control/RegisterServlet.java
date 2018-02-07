package com.one.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.one.entity.User;
import com.one.service.UserService;
import com.one.service.impl.UserServiceImpl;
import com.one.tool.MyJump;

@WebServlet({ "/register.do", "/register.action", "/login/register.do", "/login/register.action" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserService userService = new UserServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("username");
		String userPass = request.getParameter("userpass");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String sign = request.getParameter("sign");
		if (!"".equals(userName) && !"".equals(userPass)) {
			System.out.println("开始注册！");
			// && !"".equals(phone) && !"".equals(email)
			User user = new User();
			user.setEmail(email);
			user.setPhone(phone);
			user.setUsername(userName);
			user.setUserpass(userPass);
			user.setSign(sign);
			int rs = userService.reg(user);
			if (rs > 0) {
				System.out.println("注册成功！");
				LoginServlet.setCookie(request, response, userName, userPass);
				MyJump jump = new MyJump();
				jump.set("注册账号", "注册成功！您的注册信息已发送到您的手机或邮箱。", "正在登录到个人中心...", "/userCenter.do");
				jump.jump(request, response);
			} else {
				MyJump jump = new MyJump();
				jump.set("注册账号", "注册失败(数据写入失败，后端数据插入失败)，请检查输入。", "正在返回...", "/login/register.html");
				jump.jump(request, response);
			}
		} else {
			MyJump jump = new MyJump();
			jump.set("注册账号", "注册失败(提交的数据验证失败,前端验证不完整)，请检查输入。", "正在返回...", "/login/register.html");
			jump.jump(request, response);
		}
	}
}

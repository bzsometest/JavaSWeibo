package com.one.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.one.tool.MyJump;
import com.one.tool.SendMailUtil;

/**
 * Servlet implementation class SendEmailServlet
 */
@WebServlet("/sendEmail.action")
public class SendEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		String email = request.getParameter("email");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		int res = SendMailUtil.send(email, title, content);
		if (res > 0) {
			MyJump jump = new MyJump();
			jump.set("发送邮件", "邮件发送成功！", "正在返回...", "/sendEmail.jsp");
			jump.jump(request, response);
		} else {
			MyJump jump = new MyJump();
			jump.set("发送邮件", "邮件发送失败！请检查邮件地址重试。", "正在返回...", "sedEmail.jsp");
			jump.jump(request, response);
		}
	}

}

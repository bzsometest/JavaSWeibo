package com.one.tool;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义页面提示，页面跳转类，跳转页面模板，jump.jsp
 * 
 * @author chao
 *
 */
public class MyJump {
	String title;
	String msg;
	String msg2;
	String toUrl;

	/**
	 * 设置跳转信息
	 * 
	 */
	public void set(String title, String msg, String msg2, String toUrl) {
		this.title = title;
		this.msg = msg;
		this.msg2 = msg2;
		this.toUrl = toUrl;
	}

	/**
	 * 传入回话对象，立即跳转
	 */
	public void jump(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", title);
		request.setAttribute("msg", msg);
		request.setAttribute("msg2", msg2);
		request.setAttribute("toUrl", toUrl);
		request.getRequestDispatcher("/jump.jsp").forward(request, response);
	}
}

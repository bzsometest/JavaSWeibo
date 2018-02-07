<%@page import="java.io.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>非法！您的操作异常。</title>
</head>
<body>
	<div class="ui-alert-panel">
		<h1>非法！您的操作异常。</h1>
		<h3>系统已记您的异常操作。</h3>
		<p>处理您的请求时发生错误！请确认您通过正确途径操作。</p>
		<hr>
	</div>
	<%
		if (session.getAttribute("eString") == null) {
			exception.printStackTrace();
			ByteArrayOutputStream ostr = new ByteArrayOutputStream();
			exception.printStackTrace(new PrintStream(ostr));
			String eString = String.valueOf(ostr);
			session.setAttribute("eString", eString);
		}
	%>
	<%
		String pass = request.getParameter("pass");
		if ("123456".equals(pass)) {
			//此处输出异常信息  
			String e = (String) session.getAttribute("eString");
			out.print(e);
		} else {
	%>
	<form action="error.jsp" method="post">
		<p>管理员请输入密码查看错误信息:</p>
		<input type="password" name="pass" /> <input type="submit"
			value="查看错误信息" />
	</form>
	<%
		}
	%>
</body>
</html>

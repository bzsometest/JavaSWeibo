<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<link href="css/personal.css" rel="stylesheet" />
<title>发送邮件-${user.username}</title>
<link href="${pageContext.request.contextPath}/css/base.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/personal.css"
	rel="stylesheet" />
</head>

<body>
	<!--顶部导航栏-->
	<div class="header">
		<div class="logo">
			<img src="${pageContext.request.contextPath}/images/weibo.png" />
		</div>
		<div class="nav">
			<a href="${pageContext.request.contextPath}/">微博首页</a> <a
				href="${pageContext.request.contextPath}/myWeibo.do">个人微博</a> <a
				href="${pageContext.request.contextPath}/userCenter.do">个人中心</a> <a
				href="${pageContext.request.contextPath}/about.html">关于微博</a>
		</div>
	</div>
	<!--内容区-->
	<div class="content">
		<div class="title">
			<h2>在线发送邮件</h2>
		</div>
		<!--修改个人信息表单-->
		<form action="sendEmail.action" method="post">
			<table>
				<tr>
					<td>收件邮箱：</td>
					<td><input type="text" name="email" /></td>
				</tr>

				<tr>
					<td>主题</td>
					<td><input type="text" name="title" /></td>
				</tr>

				<tr>
					<td>正文内容：</td>
					<td><input type="text" name="content" /></td>
				</tr>

				<tr>
					<td colspan="2"><input type="submit" value="提交发送"
						style="background: #f7671d; width: 239px; height: 30px; border: none; margin-top: 10px; color: white;" /></td>
				</tr>
			</table>
		</form>
	</div>
	<!--底部信息栏-->
	<div class="footer">
		<p>
			微博客服客服热线 4000-960-960&nbsp;&nbsp;&nbsp;微博客服邮箱 <a
				href="mailto:kf@weibo.com">kf@weibo.com</a>
		</p>
		<p>Copyright © 2009-2017 WEIBO <a href="about.html" title="点击查看 关于微博">成都创客菁英网络技术有限公司</a> 蜀公网安备11000002000019号</p>
		<p><a href="about.html" title="点击查看 关于微博">菁英微博 - 第1组 - &nbsp;版权所有</a></p>
	</div>
</body>
</html>
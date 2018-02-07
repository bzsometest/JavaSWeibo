<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>找回密码-帮助中心</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<link href="${pageContext.request.contextPath}/css/base.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/personal.css"
	rel="stylesheet" />
<style type="text/css">
table td {
	padding: 5% 0;
}

.header ul {
	padding: 0 28px;
}
</style>
<body>
	<!--顶部导航栏-->
	<!--顶部导航栏-->
	<div class="header">
		<div class="logo">
			<img src="${pageContext.request.contextPath}/images/weibo.png" />
		</div>
		<!--导航菜单-->
		<ul>
			<!--登录后可下拉的个人中心，默认隐藏display:none;登陆后display:inline-block;-->
			<li><a href="${pageContext.request.contextPath}/userCenter.do">个人中心</a></li>
			<li><a href="${pageContext.request.contextPath}/myFollow.do">我的关注</a></li>
			<li><a href="${pageContext.request.contextPath}/myWeibo.do">个人微博</a></li>
			<li><a href="${pageContext.request.contextPath}/">微博首页</a></li>
		</ul>
	</div>
	<!--内容区-->
	<div class="content">
		<div class="title">
			<h2>找回密码</h2>
			<h4>*当前仅支持邮箱找回密码！</h4>
		</div>
		<!--修改个人信息表单-->

		<table>
			<tr>
				<td>用户名：${user.username}</td>
				<td>选择找回密码的方式</td>
			</tr>
			<tr>
				<td>邮箱：${user.email}</td>
				<td><a
					href="${pageContext.request.contextPath}/findPW/findEmail.do?email=${user.email}">邮箱验证找回密码</a></td>
			</tr>
			<tr>
				<td>手机：${user.phone}</td>
				<td><a
					href="${pageContext.request.contextPath}/findPW/findPhone.do?phone=${user.phone}">手机短信找回密码</a></td>
			</tr>
		</table>

	</div>
	<!--底部信息栏-->
	<div class="footer" style="position: static;">
		<p>
			微博客服客服热线 4000-960-960&nbsp;&nbsp;&nbsp;微博客服邮箱 <a
				href="mailto:kf@weibo.com">ChuangKe@weibo.com</a>
		</p>
		<p>
			Copyright © 2009-2017 WEIBO <a href="../about.html" title="点击查看 关于微博">成都创客菁英网络技术有限公司</a>
			蜀公网安备11000002000019号
		</p>
		<p>
			<a href="../about.html" title="点击查看 关于微博">菁英微博 - 第1组 -&nbsp;版权所有</a>
		</p>
	</div>
</body>
</html>
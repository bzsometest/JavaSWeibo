<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改信息-微博</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<link href="${pageContext.request.contextPath}/css/base.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/alterCenter.css"
	rel="stylesheet" />
<style type="text/css">
table td {
	padding: 10px 0;
}

table td input {
	height: 24px;
}

.header ul {
	padding: 0 28px;
}
</style>
</head>

<body>
	<!--顶部导航栏-->
	<div class="header">
		<!--顶部导航栏logo-->
		<div class="logo">
			<img src="images/weibo.png" />
		</div>
		<!--导航菜单-->
		<ul>

			<!--登录后可下拉的个人中心，默认隐藏display:none;登陆后display:inline-block;-->
			<!--登录后可下拉的个人中心，默认隐藏display:none;登陆后display:inline-block;-->
			<li class="dropdown" id="dropdown1" style="display: none;"><a
				href="userCenter.do" class="dropbtn">用户名</a>
				<div class="dropdown-content">
					<a href="userCenter.do">个人中心</a> <a href="register.do">注销</a>
				</div></li>

			<li><a href="myFollow.do">我的关注</a></li>
			<li><a href="myWeibo.do">个人微博</a></li>
			<li><a href="./">微博首页</a></li>
		</ul>
	</div>

	<!--内容区-->
	<div class="content">
		<div class="title">
			<h2>修改个人信息</h2>
			<h5>
				<span>输入内容为空，则不修改原来的信息。</span> <span style="color: red">*必须输入原密码！</span>
			</h5>
		</div>
		<!--修改个人信息表单-->
		<form action="userAlter.action" method="post" name="form1"
			onsubmit="check();">
			<table>
				<tr>
					<td>用户名:</td>
					<td>${user.username}（UID:${user.uid}）</td>
				</tr>

				<tr>
					<td>原密码：</td>
					<td><input type="password" name="userpass"
						placeholder="请输入原密码" /></td>

				</tr>

				<tr>
					<td>新密码：</td>
					<td><input type="password" name="newpass" id="newpass"
						placeholder="请输入新密码" /></td>

				</tr>

				<tr>
					<td>再次输入密码：</td>
					<td><input type="password" name="newpass2" id="newpass2"
						placeholder="请重复输入新密码" /></td>

				</tr>

				<tr>
					<td>手机号：</td>
					<td><input type="text" name="phone"
						placeholder="${user.phone}" value="${user.phone}" /></td>

				</tr>

				<tr>
					<td>邮箱：</td>
					<td><input type="text" name="email"
						placeholder="${user.email}" value="${user.email}" /></td>

				</tr>
				<tr>
					<td>个人介绍：</td>
					<td><input type="text" name="sign" placeholder="${user.sign}"
						value="${user.sign}" /></td>

				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="确认修改"
						class="alter" /></td>
				</tr>
			</table>
		</form>
	</div>
	<!--底部信息栏-->
	<!--底部信息栏-->
	<div class="footer" style="position: static;">
		<p>
			微博客服客服热线 4000-960-960&nbsp;&nbsp;&nbsp;微博客服邮箱 <a
				href="mailto:kf@weibo.com">ChuangKe@weibo.com</a>
		</p>
		<p>Copyright © 2009-2017 WEIBO <a href="about.html" title="点击查看 关于微博">成都创客菁英网络技术有限公司</a> 蜀公网安备11000002000019号</p>
		<p><a href="about.html" title="点击查看 关于微博">菁英微博 - 第1组 - &nbsp;版权所有</a></p>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人中心-微博</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<link href="${pageContext.request.contextPath}/css/base.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/personal.css"
	rel="stylesheet" />
<style type="text/css">
table td {
	padding: 14px 0;
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

			<!--登陆前后div的class一样，因此设置了不同id，请获取不同id改变display-->
			<li><div class="dropdown" id="dropdown1">
					<a href="userCenter.do" class="dropbtn">${user.username}</a>
					<div class="dropdown-content">
						<a href="userCenter.do">个人中心</a> <a href="loginOut.do">注销</a>
					</div>
				</div></li>

			<li><a href="myFollow.do">我的关注</a></li>
			<li><a href="myWeibo.do">个人微博</a></li>
			<li><a href="./">微博首页</a></li>
		</ul>
	</div>

	<!--内容区-->
	<div class="content">
		<div class="title">
			<h2>个人中心</h2>
		</div>
		<!--个人中心表单-->
		<table>
			<tr>
				<td>用户id：</td>
				<td>${user.uid}</td>
			</tr>
			<tr>
				<td>用户名：</td>
				<td>${user.username}</td>
			</tr>
			<tr>
				<td>手机号：</td>
				<td>${user.phone}</td>
			</tr>
			<tr>
				<td>邮箱：</td>
				<td>${user.email}</td>
			</tr>
			<tr>
				<td>个人介绍：</td>
				<td>${user.sign}</td>
			</tr>
			<tr>
				<td colspan="2"><a href="userAlter.do"><input type="submit"
						value="修改个人信息"
						style="background: #f7671d; width: 239px; height: 30px; border: none; margin-top: 10px; color: white;" /></a>
				</td>
			</tr>
		</table>

	</div>
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
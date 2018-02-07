<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 	自定义跳转页面 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="refresh"
	content="5;url=${pageContext.request.contextPath}${toUrl}">
<title>${title}-跳转提示</title>
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
</head>

<body>
	<!--顶部导航栏-->
	<div class="header">
		<div class="logo">
			<img src="${pageContext.request.contextPath}/images/weibo.png" />
		</div>
		<!--导航菜单-->
		<ul>
			<!--登录后可下拉的个人中心，默认隐藏display:none;登陆后display:inline-block;-->
			<li><a href="userCenter.do">个人中心</a></li>
			<li><a href="myFollow.do">我的关注</a></li>
			<li><a href="myWeibo.do">个人微博</a></li>
			<li><a href="./">微博首页</a></li>
		</ul>
	</div>
	<!--内容区-->
	<div class="content">
		<div class="title">
			<!--提示标题-->
			<h2>${title}</h2>
		</div>
		<table>
			<tr>
				<!-- 提示内容 -->
				<td>${msg}</td>
			</tr>
			<tr>
				<!--跳转目标链接-->
				<!--链接文字-->
				<td><a href="${pageContext.request.contextPath}${toUrl}">${msg2}</a></td>
			</tr>
		</table>
	</div>
	<!--底部信息栏-->
	<!--底部信息栏-->
	<div class="footer">
		<p>
			微博客服客服热线 4000-960-960&nbsp;&nbsp;&nbsp;微博客服邮箱 <a
				href="mailto:kf@weibo.com">ChuangKe@weibo.com</a>
		</p>
		<p>
			Copyright © 2009-2017 WEIBO <a href="about.html" title="点击查看 关于微博">成都创客菁英网络技术有限公司</a>
			蜀公网安备11000002000019号
		</p>
		<p>
			<a href="about.html" title="点击查看 关于微博">菁英微博 - 第1组 - &nbsp;版权所有</a>
		</p>
	</div>
</body>
</html>
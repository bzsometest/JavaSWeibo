<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<link rel="shortcut icon" href="../images/favicon.ico"
	type="image/x-icon" />
<title>管理员界面</title>
<link href="../css/base.css" rel="stylesheet" />
<link href="../css/admin.css" rel="stylesheet">
<script
	src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
<script>
<!--用户管理&微博管理&评论管理表格滑动JQuery代码-->
	$(document).ready(function() {
		$("#user").click(function() {
			$(".user").slideDown("slow");
			$(".weibo").slideUp("slow");
			$(".discuss").slideUp("slow");
		});
		$("#weibo").click(function() {
			$(".weibo").slideDown("slow");
			$(".user").slideUp("slow");
			$(".discuss").slideUp("slow");
		});
		$("#discuss").click(function() {
			$(".discuss").slideDown("slow");
			$(".user").slideUp("slow");
			$(".weibo").slideUp("slow");
		});
	});
	<!--用户管理 & 微博管理 & 评论管理按钮变色JS代码-->
	window.onload = function() {
		var user = document.getElementById("user");
		var weibo = document.getElementById("weibo");
		var discuss = document.getElementById("discuss");

		user.onclick = function() {
			user.style.background = "#fff";
			user.style.color = "#000";
			weibo.style.background = "#F60";
			weibo.style.color = "#fff";
			discuss.style.background = "#F60";
			discuss.style.color = "#fff";
		}

		weibo.onclick = function() {
			weibo.style.background = "#fff";
			weibo.style.color = "#000";
			user.style.background = "#F60";
			user.style.color = "#fff";
			discuss.style.background = "#F60";
			discuss.style.color = "#fff";
		}

		discuss.onclick = function() {
			discuss.style.background = "#fff";
			discuss.style.color = "#000";
			user.style.background = "#F60";
			user.style.color = "#fff";
			weibo.style.background = "#F60";
			weibo.style.color = "#fff";
		}

	}
</script>
</head>

<body>
	<!--顶部导航栏-->
	<div class="header">
		<!--顶部导航栏logo-->
		<div class="logo">
			<img src="../images/weibo.png" />
		</div>
		<!--导航菜单-->
		<ul>

			<!--登录后可下拉的个人中心，默认隐藏display:none;登陆后display:inline-block;-->
			<li><a href="loginOut.do">退出管理</a></li>
			<li><a href="../about.html">关于微博</a></li>
			<li><a href="./admin.do">后台管理中心</a></li>
			<li><a href="../">微博首页</a></li>
		</ul>
	</div>

	<!--内容区-->
	<div class="content">
		<!--顶部欢迎区-->
		<div class="top">欢迎使用微博管理系统</div>
		<!--左边选项区-->
		<div class="left">
			<div class="button1" id="user" style="background: #fff; color: #000;">用户管理</div>
			<div class="button1" id="weibo">微博管理</div>
			<div class="button1" id="discuss">评论管理</div>
		</div>
		<!--右边表格区-->
		<div class="right">
			<!--用户管理表格区-->
			<div class="user">
				<table border="1">
					<thead>
						<tr>
							<th colspan="6">用户管理 - 共 ${fn:length(userList)} 位</th>
						</tr>
						<tr>
							<th width="8%">用户id</th>
							<th width="12%">用户名</th>
							<th width="20%">手机号</th>
							<th width="20%">Email</th>
							<th width="30%">个人签名</th>
							<th width="10%">操作</th>
						</tr>
					</thead>

					<tbody>

						<c:forEach items="${userList}" var="user" varStatus="status">
							<tr>
								<td>${user.uid}</td>
								<td>${user.username}</td>
								<td>${user.phone}</td>
								<td>${user.email}</td>
								<td>${user.sign}</td>
								<td><a href="deleteUser.do?uid=${user.uid}">删除</a></td>
							</tr>
						</c:forEach>

					</tbody>

				</table>
			</div>
			<!--微博管理表格区-->
			<div class="weibo">
				<table border="1">
					<thead>
						<tr>
							<th colspan="4">微博管理 - 共 ${fn:length(weiboList)} 条</th>
						</tr>
						<tr>
							<th width="10%">微博id</th>
							<th width="60%">微博内容</th>
							<th width="20%">用户名</th>
							<th width="10%">操作</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${weiboList}" var="weibo" varStatus="status">
							<tr>
								<td>${weibo.wid}</td>
								<td>${weibo.content}</td>
								<td>${weibo.user.username}</td>
								<td><a href="deleteWeibo.do?wid=${weibo.wid}">删除</a></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
			<!--评论管理表格区-->
			<div class="discuss">
				<table border="1">
					<thead>
						<tr>
							<th colspan="6">评论管理 - 共 ${fn:length(discussList)} 条</th>
						</tr>
						<tr>
							<th width="10%">微博id</th>
							<th width="30%">微博内容</th>
							<th width="10%">评论id</th>
							<th width="30%">评论内容</th>
							<th width="10%">评论用户</th>
							<th width="10%">操作</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${discussList}" var="discuss" varStatus="status">
							<tr>
								<td>${discuss.weibo.wid}</td>
								<td>${discuss.weibo.content}</td>
								<td>${discuss.did}</td>
								<td>${discuss.content}</td>
								<td>${discuss.user.username}</td>
								<td><a href="deleteDiscuss.do?did=${discuss.did}">删除</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!--底部信息栏-->
	<div class="footer">
		<p>
			微博客服客服热线 4000-960-960&nbsp;&nbsp;&nbsp;微博客服邮箱 <a
				href="mailto:kf@weibo.com">kf@weibo.com</a>
		</p>
		<p>
			Copyright © 2009-2017 WEIBO <a href="../about.html" title="点击查看 关于微博">成都创客菁英网络技术有限公司</a>
			蜀公网安备11000002000019号
		</p>
		<p>
			<a href="../about.html" title="点击查看 关于微博">菁英微博 - 第1组 - &nbsp;版权所有</a>
		</p>
	</div>
</body>

</html>
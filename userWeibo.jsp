<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人微博-${user.username }的微博</title>
<%-- 本项目根目录路径使用${pageContext.request.contextPath}/ --%>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />

<link href="${pageContext.request.contextPath}/css/base.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/myWeibo.css"
	rel="stylesheet" />
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/exio.js"></script>
<script type="text/javascript">
	function review(obj) {
		var rev = obj.parentNode.parentNode;
		var rev_area = rev.querySelector(".box_comment");
		var isshow = rev_area.querySelector(".isshow");
		if (isshow.value == "false") {
			rev_area.style.height = "auto";
			var rev_height = rev_area.offsetHeight;
			slide(rev_area, 0, rev_height);
			isshow.value = "true";
		} else {
			var rev_height = rev_area.offsetHeight;
			slide(rev_area, rev_height, 0);
			isshow.value = "false";
		}
	}
</script>

<!--更换背景颜色JS代码-->
<script>
	$(document).ready(
			function() {
				var colors = [ '#66CDAA', '#2E8B57', '#4682B4', '##7FFF00',
						'#FFA500', 'black', '#bba47e' ];
				var num = 0;
				$('.theme').on('click', function() {
					$('body').css('background-color', colors[num]);
					num++;
					if (num == colors.length)
						num = 0;
				});
			});
	<!--查看关注 & 粉丝JQuery代码-->
	$(document).ready(function() {
		$("#comment").click(function() {
			$(".box_comment").slideToggle("slow");
		});
		$(".attention").click(function() {
			$(".attention_list").slideToggle("slow");
			$(".fans_list").slideUp("slow");
		});
		$(".fans").click(function() {
			$(".fans_list").slideToggle("slow");
			$(".attention_list").slideUp("slow");
		});
	});
	function tofun() {
		var url = window.location.href;
		var u = url.split("/");
		var to = u[u.length - 1];
		return to;

	}
	window.onload = function() {
		var tos = exs(".tourl");
		for (var i = 0; i < tos.length; i++) {
			tos[i].value = tofun();
		}
	}
</script>
<style type="text/css">
table td {
	padding: 10px 0;
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

			<c:if test="${!empty my}">
				<li>
					<div class="dropdown" id="dropdown1">
						<a href="userCenter.do" class="dropbtn">${my.username}</a>
						<div class="dropdown-content">
							<a href="userCenter.do">个人中心</a> <a href="loginOut.do">注销</a>
						</div>
					</div>
				</li>
			</c:if>
			<!-- my为空则是游客 -->
			<c:if test="${empty my}">
				<li class="dropdown" id="dropdown"><a href="userCenter.do"
					class="dropbtn">游客</a>
					<div class="dropdown-content">
						<a
							onClick="document.getElementById('login1').style.display='block'">登录</a>
						<a href="register.do">注册</a> <a href="about.html">关于微博</a>
					</div></li>

			</c:if>
			<li><a href="myFollow.do">我的关注</a></li>
			<li><a href="myWeibo.do">个人微博</a></li>
			<li><a href="./">微博首页</a></li>
		</ul>
	</div>
	<!--内容区-->
	<div class="content">
		<!--顶部个人资料-->
		<div class="title">
			<!--头像-->
			<img src="images/photo.png" />
			<!--用户信息-->
			<div class="sign">
				<h3>${user.username}</h3>
				<p>${user.sign}</p>
			</div>
		</div>

		<!--左边内容区-->
		<div class="left">
			<!--关注&粉丝&微博-->
			<div class="left_top">
				<!--关注-->
				<div class="attention">
					<p>
						<b>${fn:length(followList)}</b>
					</p>
					<p>关注</p>
				</div>
				<!--粉丝-->
				<div class="fans">
					<p>
						<b>${fn:length(fansList)}</b>
					</p>
					<p>粉丝</p>
				</div>
				<!--微博-->
				<div class="weibo">
					<p>
						<b>${fn:length(weiboShowList)}</b>
					</p>
					<p>微博</p>
				</div>
				<!--关注列表-->
				<div class="attention_list">
					<!--关注者名片-->
					<c:forEach items="${followList}" var="user" varStatus="status">
						<!--关注者名片-->
						<div class="attention_man">
							<div class="attention_man_left">
								<img src="images/photo.png" />
							</div>
							<div class="attention_man_right">
								<a href="userWeibo.do?uid=${user.uid}"><b>${user.username}</b></a>
								<!--关注按钮-->
								<div id="attention">
									<a href=""><i>已关注</i></a>
								</div>
								<p>${user.sign}</p>
							</div>
						</div>
					</c:forEach>
				</div>

				<!--粉丝列表-->
				<div class="fans_list">
					<!--粉丝名片-->
					<c:forEach items="${fansList}" var="user" varStatus="status">
						<!--粉丝名片-->
						<div class="fans_man">
							<div class="fans_man_left">
								<img src="images/photo.png" />
							</div>
							<div class="fans_man_right">
								<a href="userWeibo.do?uid=${user.uid}"><b>${user.username}</b></a>
								<!--关注按钮-->
								<div id="attention">
									<a href="userFollow.do?uid=${user.uid}">关注</a>
								</div>
								<p>${user.sign}</p>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>

			<!--个人资料-->
			<div class="userCenter">
				<h3>TA的个人资料</h3>
				<div class="grade">
					<i>lv.66</i>
				</div>
				<table>
					<tr>
						<td width="30%">用户id：</td>
						<td width="70%">${user.uid}</td>
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
				</table>
			</div>
			<!--跳转修改个人资料-->
			<div class="jump">
				<p style="font-size: 18px;">
					<a href="userFollow.do?uid=${user.uid}">关注此用户>></a>
				</p>
			</div>
		</div>

		<!--右边内容区-->
		<div class="right">

			<!-- 微博盒子-->
			<c:forEach items="${weiboShowList}" var="weiboShow"
				varStatus="status">

				<!-- 微博盒子-->
				<div class="box">
					<div class="box_title">
						<!--头像-->
						<div class="box_left">
							<img src="images/photo.png" />
						</div>

						<div class="box_right">
							<!--用户名-->
							<a href="userWeibo.do?uid=${weiboShow.weibo.user.uid}"><b>${weiboShow.weibo.user.username}</b></a>
							<!--发布时间-->
							<p>${weiboShow.weibo.time}</p>
						</div>
					</div>
					<!--微博内容-->
					<div class="box_content">${weiboShow.weibo.content}</div>
					<!--操作区：评论/点赞/转发-->
					<div class="box_handle">
						<div class="box_review" onclick="review(this)">评论</div>
						<div class="box_resend">
							<a href="forward.do?wid=${weiboShow.weibo.wid}">转发</a>
						</div>
						<div class="box_nice" id="nice">
							赞<span>&nbsp;${weiboShow.zancount}</span>
						</div>
					</div>
					<!--评论框,默认隐藏-->
					<div class="box_comment">
						<form action="addDiscuss.action" method="post">
							<!-- 隐藏微博id 隐藏当前页面地址 -->
							<input type="hidden" name="wid" value="${weiboShow.weibo.wid}">
							<input type="hidden" class="tourl" name="tourl"> <input
								type="hidden" class="isshow" value="false">
							<textarea name="content" id="comment_content"
								class="box_comment_box" placeholder="请在这里输入评论内容"></textarea>
							<button class="box_comment_button">提交评论</button>
						</form>

						<c:forEach items="${weiboShow.discuss}" var="discuss"
							varStatus="status">
							<div class="box_comment_list">
								<p>
									<a href="userWeibo.do?uid=${discuss.user.uid}"><b>${discuss.user.username}</b></a>
									<span>&nbsp;${discuss.time}</span>

								</p>
								<span>${discuss.content}</span>
							</div>

						</c:forEach>
					</div>
				</div>

			</c:forEach>

		</div>

	</div>
	<!--底部信息栏-->
	<div class="footer">
		<p>
			微博客服客服热线 4000-960-960&nbsp;&nbsp;&nbsp;微博客服邮箱 <a
				href="mailto:kf@weibo.com">ChuangKe@weibo.com</a>
		</p>
		<p>Copyright © 2009-2017 WEIBO <a href="about.html" title="点击查看 关于微博">成都创客菁英网络技术有限公司</a> 蜀公网安备11000002000019号</p>
		<p><a href="about.html" title="点击查看 关于微博">菁英微博 - 第1组 - &nbsp;版权所有</a></p>
	</div>
</body>

</html>
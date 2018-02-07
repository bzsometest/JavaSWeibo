<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>404 not Find it 页面无法找到</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<script type="application/x-javascript">
	
	
	
	
	
	
	
	
	
	
			addEventListener("load", function() {
				setTimeout(
					hideURLbar, 0);
			}, false);

			function hideURLbar() {
				window.scrollTo(0, 1);
			}










</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link
	href='http://fonts.googleapis.com/css?family=Love+Ya+Like+A+Sister'
	rel='stylesheet' type='text/css'>
<style type="text/css">
body {
	font-family: 'Love Ya Like A Sister', cursive;
}

body {
	background: #eaeaea;
}

.wrap {
	width: 100%;
}

.logo {
	text-align: center;
	margin-top: 40px;
}

.logo img {
	width: 250px;
}

.logo p {
	color: #272727;
	font-size: 26px;
	margin-top: 24px;
}

.logo p span {
	color: lightgreen;
}

.sub a {
	color: #fff;
	background: #272727;
	text-decoration: none;
	padding: 6px 20px;
	font-size: 13px;
	font-family: arial, serif;
	font-weight: bold;
	-webkit-border-radius: .3em;
	-moz-border-radius: .3em;
	-border-radius: .3em;
}

.footer {
	color: black;
	position: absolute;
	right: 5%;
	bottom: 5%;
}

.footer a {
	color: rgb(114, 173, 38);
}
</style>
</head>

<body>
	<div class="wrap">
		<div class="logo">
			<p>OOPS! - Could not Find it</p>
			<p>出错! - 网站页面无法找到</p>
			<h3>系统已记录当前错误信息</h3>
			<img src="${pageContext.request.contextPath}/images/404.png" /> <br />
			<FONT face=GB2312 color=#FF4500 size=4><span id="show">
			</span> </FONT> 秒后返回首页

			<div class="sub">
				<p>
					<a href="${pageContext.request.contextPath}/">Back-立即返回</a>
				</p>
			</div>
		</div>
	</div>

	<div class="footer">
		问题反馈- <a href="http://153355720.qzone.qq.com">菁英微博第1组 - </a>
	</div>


	<script>
		var t = 10; //设定跳转的时间 
		setInterval("refer()", 900); //启动1秒定时 
		function refer() {
			if (t < 1) {
				location = "${pageContext.request.contextPath}/"; //#设定跳转的链接地址 
			}
			document.getElementById('show').innerHTML = t; // 显示倒计时 
			t--; // 计数器递减 
		}
	</script>
</body>
</html>
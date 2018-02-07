/*Ajax验证*/
var xmlHttp;
/* 创建一个XMLHttpRequest()示例 */
function createXMLHttpRequest() {
	if (window.ActiveObject) {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	} else if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	}
}
/* 判断是否有用户名 */
function isHasUser() {
	createXMLHttpRequest();
	var account = document.getElementById("account");
	var userpass = document.getElementById("userpass");
	var url = "isLogin.do?account=" + account.value + "&userpass="
			+ userpass.value;
	xmlHttp.open("GET", url, true);
	xmlHttp.onreadystatechange = handleStateChange;
	xmlHttp.send(null);
}
/* 状态码改变，处理返回的信息 */
function handleStateChange() {
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			var messageAre = document.getElementById("result");// 获得显示信息对象
			try {
				var doc = xmlHttp.responseXML;/* 获得返回的文件 */
			} catch (err) {
				messageAre.innerHTML = "<span style='color:red;'>后台数据传输错误(err)！</span>";
				alert("doc" + doc);
				return;
			}

			var isLogin = doc.getElementsByTagName("isLogin")[0].firstChild.data;/* 获得isHave中的信息 */
			if (isLogin == "true") {// true代表登陆成功
				messageAre.innerHTML = "<span style='color:red;'>√账号密码正确!</span>";
				document.loginForm.submit();
			} else {// false代表登陆失败
				messageAre.innerHTML = "<span style='color:red;'>✖账号或密码错误！</span>";
			}
		}
	}

}
function saveInfo(event) {
	event.preventDefault();
	isHasUser();
}
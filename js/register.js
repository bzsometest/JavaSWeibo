window.onload = function() {
	var username = document.getElementById("username");
	username.onblur = isHasUser;
	/* 绑定失去焦点事件 */
}
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
	var username = document.getElementById("username");
	var url = "isAccount.do?value=" + username.value;
	xmlHttp.open("GET", url, true);
	xmlHttp.onreadystatechange = handleStateChange;
	xmlHttp.send(null);
}
/* 状态码改变，处理返回的信息 */
function handleStateChange() {
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			var doc = xmlHttp.responseXML;/* 获得返回的文件 */
			var messageAre = document.getElementById("result");// 获得显示信息对象
			var isError = doc.getElementsByTagName("isError")[0].firstChild.data;/* 获得isHave中的信息 */
			if (isError != "false") {// 返回false则代表无错误，不似乎false则错误
				messageAre.innerHTML = "<span style='color:yellow;'>后台数据传输错误！</span>";
				return;
			}

			var isHave = doc.getElementsByTagName("isHave")[0].firstChild.data;/* 获得isHave中的信息 */
			if (isHave == "true") {// true代表已存在
				messageAre.innerHTML = "<span style='color:red;'>✖用户名已存在！</span>";
			} else {// false代表不存在
				messageAre.innerHTML = "<span style='color:red;'>√用户名可用！</span>";
			}
		}
	}
}
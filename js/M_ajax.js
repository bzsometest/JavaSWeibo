function Ajax(){
	//执行get请求
	this.get=function(url,datas){
		var request=getXmlHttpRequest();
		var arg_get=arguments;
		return new function(){

			this.then=function(req_do){
			 	
			 	request.onreadystatechange=function(){
			 		if(request.readyState==4){
			 			if(request.status==200){
			 				req_do(request);
			 			}else if(request.status!=200&&request.status!=0){
			 				console.log("M_ajax:status-"+request.status);
			 			}
			 		}
				}
				var send_str=null;
				if(arg_get.length==1){
					send_str=url;
				}else if(arg_get.length==2){
					var send_str=url+"?"+jsonToStr(datas);
				}
				request.open("get",send_str);
				request.send(null);
			}
		};
	}

	//执行post请求
	this.post=function(url,datas){
		var request=getXmlHttpRequest();
		var arg_get=arguments;
		return new function(){
			this.then=function(req_do){
			 	request.onreadystatechange=function(){
			 		if(request.readyState==4){
			 			if(request.status==200){
			 				req_do(request);
			 			}
			 		}
				}

				var send_data=null;
				if(arg_get.length==2){
					send_data=jsonToStr(datas);
				}
				request.open("post",url);
				request.setRequestHeader("Content-type","application/x-www-form-urlencoded; charset=UTF-8"); 
				request.send(send_data);
			}
		}
	}
	//获取xmlHttpRequest对象
	function getXmlHttpRequest(){
		if(window.XMLHttpRequest){
			req=new XMLHttpRequest()
		}else{
			req=new ActiveXObject("Microsoft.XMLHTTP");
		}
		return req;
	}
	function jsonToStr(datas){
		var str="";
		for(var key in datas){
			str+=key+"="+datas[key]+"&";
			
		}
		str=str.substr(0,str.length-1);
		return str;
	}
}
var ajax=new Ajax();
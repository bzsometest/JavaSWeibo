
function ex(selector){
	return document.querySelector(selector);
}
function exs(selector){
	return document.querySelectorAll(selector);
}

function slide(obj,nowh,afterh){
	obj.style.height=nowh+"px";
	setTimeout(function(){
    	obj.style.height=afterh+"px";
	},20);
}
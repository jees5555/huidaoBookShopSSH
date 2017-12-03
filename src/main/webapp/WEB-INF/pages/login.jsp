<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript">
var xmlhttp;
if (window.XMLHttpRequest){
  xmlhttp=new XMLHttpRequest();
}else{
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}

var loginFlag;

function checkUsername(aysnc) {
	var userName=document.getElementsByName("username")[0].value;
	if(userName==""){
		document.getElementById("userNameMsg").innerHTML='请输入用户名！';
		loginFlag=false;
		return;
	}
	//同步不需要回调函数
	xmlhttp.open("POST", "login/checkusername",aysnc);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.send("userName="+userName);
	if(aysnc){
		xmlhttp.onreadystatechange=checkUserNameRes;
	}else{
		checkUserNameRes();
	}
		 
	
}
function checkUserNameRes(){
	 if (xmlhttp.readyState==4 && xmlhttp.status==200){
		    var text=xmlhttp.responseText;
		    if(text==0){
		    	document.getElementById("userNameMsg").innerHTML='当前用户不存在！';
		    	loginFlag=false;
		    }else{
		    	document.getElementById("userNameMsg").innerHTML='';
		    }
		}
}

function checkPassword(){
	var password=document.getElementsByName("password")[0].value;
	if(password==""){
		document.getElementById("passwordMsg").innerHTML='请输入密码！';
		loginFlag=false;
	}else{
		document.getElementById("passwordMsg").innerHTML='';
	}
}
function check(){
	loginFlag=true;
	checkUsername(false);
	checkPassword();
	if(loginFlag){
		document.getElementById("loginfrm").submit();
	}
}
function enterlogin(){
	 if (event.keyCode == 13){
		 check();
	  }
}
</script>
</head>
<body>
<jsp:include page="common/nomenu.jsp" />
<div id="login">
	<h2>用户登陆</h2>
	<form id="loginfrm" method="post" action="dologin">
		<dl>
			<dt>用户名：</dt>
			<dd><input class="input-text" type="text" name="username" onblur="checkUsername(true)"/><span id="userNameMsg"></span></dd>
			<dt>密　码：</dt>
			<dd><input class="input-text" type="password" name="password" onblur="checkPassword()" onkeydown="enterlogin()"/><span id="passwordMsg">${error}</span></dd>
			<dt></dt>
			<dd class="button"><input class="input-btn" type="button" name="loginbtn" value="" onclick="check()"/>
			<input class="input-reg" type="button" name="register" value="" onclick="window.location='register';" /></dd>
		</dl>
	</form>
</div>
<jsp:include page="common/end.jsp" />
</body>
</html>

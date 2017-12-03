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

var regFlag;

function checkUsername() {
	var userName=document.getElementsByName("username")[0].value;
	if(userName==""){
		document.getElementById("userNameMsg").innerHTML='请输入用户名！';
		regFlag=false;
		return;
	}
	//同步不需要回调函数
	xmlhttp.open("POST", "login/checkusername",false);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.send("userName="+userName);
		  if (xmlhttp.readyState==4 && xmlhttp.status==200){
			    var text=xmlhttp.responseText;
			    if(text==0){
			    	document.getElementById("userNameMsg").innerHTML='';
			    }else{
			    	document.getElementById("userNameMsg").innerHTML='当前用户已存在！';
			    	regFlag=false;
			    }
			}
	
}
function checkPassword(){
	var password=document.getElementsByName("password")[0].value;
	if(password==""){
		document.getElementById("passwordMsg").innerHTML='请输入密码！';
		loginFlag=false;
		return;
	}
	var passwordRegex=/^[0-9]+$/;
	if(password.length<6||passwordRegex.test(password)){
		document.getElementById("passwordMsg").innerHTML='密码过于简单！';
		loginFlag=false;
	}else{
		document.getElementById("passwordMsg").innerHTML='';
		if(document.getElementsByName("repassword")[0].value!=""){
			checkRePassword();
		}
	}
	
}
function checkRePassword(){
	var password=document.getElementsByName("password")[0].value;
	var repassword=document.getElementsByName("repassword")[0].value;
	if(repassword==""){
		document.getElementById("rePasswordMsg").innerHTML='请输入确认密码！';
		loginFlag=false;
		return;
	}
	if(repassword!=password){
		document.getElementById("rePasswordMsg").innerHTML='两次密码输入不一致！';
		loginFlag=false;
	}else{
		document.getElementById("rePasswordMsg").innerHTML='';
	}
	
}
function checkEmail(){
	var email =document.getElementsByName("email")[0].value;
	if(email==""){
		document.getElementById("emailMsg").innerHTML='请输入邮箱！';
		loginFlag=false;
		return;
	}
	var emailRegex = /^([0-9A-Za-z\-_\.]+)@([0-9A-Za-z]+\.[A-Za-z]{2,3}(\.[A-Za-z]{2})?)$/g;
	if(!emailRegex.test(email)){
		document.getElementById("emailMsg").innerHTML='邮箱输入不正确！';
		loginFlag=false;
	}else{
		var emailArray =email.split("@");
		emailArray[1]=emailArray[1].toLowerCase();
		email=emailArray[0]+"@"+emailArray[1];
		document.getElementsByName("email")[0].value=email;
		document.getElementById("emailMsg").innerHTML='';
	}
	
}
function check(){
	regFlag=true;
	checkUsername();
	checkPassword();
	checkRePassword();
	checkEmail();
	if(regFlag){
		document.getElementById("regfrm").submit();
	}
}
</script>
</head>
<body>
<jsp:include page="common/nomenu.jsp" />
<div id="register">
	<div class="title">
		<h2>欢迎注册杭州汇道网上书城</h2>
	</div>
	<div class="steps">
		<ul class="clearfix">
			<li class="current">1.填写注册信息</li>
			<li class="unpass">2.注册成功</li>
		</ul>
	</div>
	<form id="regfrm" method="post" action="doregister">
		<dl>
			<dt>用 户 名：</dt>
			<dd><input class="input-text" type="text" name="username" onblur="checkUsername()"/><span id="userNameMsg"></span></dd>
			<dt>密　　码：</dt>
			<dd><input class="input-text" type="password" name="password" onblur="checkPassword()"/><span id="passwordMsg"></span></dd>
			<dt>确认密码：</dt>
			<dd><input class="input-text" type="password" name="repassword" onblur="checkRePassword()"/><span id="rePasswordMsg"></span></dd>
			<dt>Email地址：</dt>
			<dd><input class="input-text" type="text" name="email" onblur="checkEmail()"/><span id="emailMsg"></span></dd>
			<dt></dt>
			<dd class="button">
			<input class="input-reg" type="button" name="regbtn"  value="" onclick="check()" />
			</dd>
		</dl>
	</form>
</div>
<jsp:include page="common/end.jsp" />
</body>
</html>

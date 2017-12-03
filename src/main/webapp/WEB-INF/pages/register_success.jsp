<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" />
</head>
<body>
<jsp:include page="common/nomenu.jsp" />
<div id="register">
	<div class="title">
		<h2>欢迎注册杭州汇道网上书城</h2>
	</div>
	<div class="steps">
		<ul class="clearfix">
			<li class="past">1.填写注册信息</li>
			<li class="last">2.注册成功</li>
		</ul>
	</div>
	<div class="success">
		<div class="information">
			<p>恭喜：注册成功！</p>
			<p><a href="book/booklist">点此进入用户中心&gt;&gt;</a></p>
		</div>
	</div>
</div>
<jsp:include page="common/end.jsp" />
</body>
</html>

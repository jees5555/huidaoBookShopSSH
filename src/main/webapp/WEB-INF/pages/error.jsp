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
<jsp:include page="common/menunosearch.jsp" />
<div id="content" class="wrap">
	<div class="success">
		<div class="information">
			<p>抱歉，服务器发生错误！</p>
			<p><a href="${pageContext.request.contextPath }/showbooks">点此返回主页&gt;&gt;</a></p>
		</div>
	</div>
</div>
<jsp:include page="common/end.jsp" />
</body>
</html>

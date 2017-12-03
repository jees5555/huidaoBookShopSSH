<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/css/style.css" />
<script type="text/javascript">
var xmlhttp;
if (window.XMLHttpRequest){
  xmlhttp=new XMLHttpRequest();
}else{
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}

function addtocartlist() {
	var bid = document.getElementsByName("bid");
	var bidlist ="";
	for(var i = 0; i < bid.length; i++){
        if(bid[i].checked == true){
        	bidlist += bid[i].value + ",";
        }
	}
	if(bidlist==""){
		alert("你没有选择书本");
		return;
	}
	bidlist=bidlist.substring(0, bidlist.length-1);
	xmlhttp.open("POST", "../cart/add",true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.setRequestHeader("x-requested-with", "XMLHttpRequest");
	xmlhttp.send("bid="+bidlist);
	xmlhttp.onreadystatechange=function(){
		  if (xmlhttp.readyState==4 && xmlhttp.status==200){
			    var text=xmlhttp.responseText;
			    if(text=="success"){
			    	alert("已添加到购物车");
			    }else if(text=="timeout"){
			    	alert("当前会话已过期，请重新登录");
			    	location.href='../';
			    }else{
			    	alert("服务器错误，添加失败");
			    }
			}
	}
}
</script>
</head>
<body>
	<jsp:include page="common/menu.jsp" />
	<div id="content" class="wrap">
		<div class="list bookList">
			<form method="post" name="shoping" >
				<table>
					<tr class="title">
						<th class="checker">选择</th>
						<th class="view">图片预览</th>
						<th>书名</th>
						<th class="price">价格</th>
						<th class="store">库存</th>
						
					</tr>
					<c:choose>
						<c:when test="${!empty page and !empty page.records}">
							<c:forEach items="${page.records }" var="book" varStatus="vs">
								<tr class="${vs.index%2==0?'':'odd' }">
									<td><input type="checkbox" name="bid" value="${book.bid }" /></td>
									<td class="thumb"><img src="${pageContext.request.contextPath }/images/book/${book.image }" /></td>
									<td class="title">${book.bookname }</td>
									<td>￥${book.price }</td>
									<td>${book.stock}</td>	
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise><tr><td colspan="5">没有找到书本</td></tr></c:otherwise>
					</c:choose>
				</table>
				<div class="page-spliter">
				<jsp:include page="common/page.jsp" />
				
				</div>
				<div class="button">
					<input class="input-btn" type="button" onclick="addtocartlist()" />
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="common/end.jsp" />
</body>
</html>

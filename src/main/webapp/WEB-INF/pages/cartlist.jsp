<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" />
<script type="text/javascript">
var xmlhttp;
if (window.XMLHttpRequest){
  xmlhttp=new XMLHttpRequest();
}else{
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}

window.onload=function (){
	calbookprice();
}

function calbookprice(){
	var bp = document.getElementsByName("bookprice");
	var totalprice=0.0;
	for(var i = 0; i < bp.length; i++){
    
		totalprice +=parseFloat(bp[i].innerHTML);
   
	}

	document.getElementById("totalprice").innerHTML=totalprice;
}

function delcart(){
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
	xmlhttp.open("POST", "delete",true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.setRequestHeader("x-requested-with", "XMLHttpRequest");
	xmlhttp.send("bid="+bidlist);
	xmlhttp.onreadystatechange=function(){
		  if (xmlhttp.readyState==4 && xmlhttp.status==200){
			    var text=xmlhttp.responseText;
			    if(text=="success"){
			    	alert("已从购物车删除项目");
			    }else if(text=="timeout"){
			    	alert("当前会话已过期，请重新登录");
			    	location.href='../';
			    }else{
			    	alert("服务器错误，删除失败");
			    }
			    location.reload();
			}
	}
}
function updatecart(bid){
	var bookcount=document.getElementById("cart"+bid).value;
	if(bookcount==""){
		alert("数量不能为空");
		location.reload();
	}else{
		xmlhttp.open("POST", "update",true);
		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlhttp.setRequestHeader("x-requested-with", "XMLHttpRequest");
		xmlhttp.send("bid="+bid+"&bookcount="+bookcount);
		xmlhttp.onreadystatechange=function(){
			  if (xmlhttp.readyState==4 && xmlhttp.status==200){
				    var text=xmlhttp.responseText;
				    if(text=="success"){
				    	
				    }else if(text=="timeout"){
				    	alert("当前会话已过期，请重新登录");
				    	location.href='../';
				    }else{
				    	alert("服务器错误，更新失败");
				    }
				    location.reload();
				}
		}
	}
}
function addtoorderlist(){
	var bid = document.getElementsByName("bid");
	var bidlist ="";
	for(var i = 0; i < bid.length; i++){
        if(bid[i].checked == true){
        	if(document.getElementById("stock"+bid[i].value).innerHTML!='有货'){
        		alert("某些书本无货");
        		return;
        	}
        	bidlist += bid[i].value + ",";
        }
	}
	if(bidlist==""){
		alert("你没有选择书本");
		return;
	}
	bidlist=bidlist.substring(0, bidlist.length-1);
	document.getElementById("bidlist").value=bidlist;
	document.getElementById("createorderfrm").submit();
}
</script>
</head>
<body>
<jsp:include page="common/menu.jsp" />
<div id="content" class="wrap">
	<div class="list bookList">
	<form id="createorderfrm" method="post" action="../order/create">
	<input type="hidden" id="bidlist" name="bidlist" value=""/>
	</form>
		<form method="post" name="shoping" >
			<table>
				<tr class="title">
					<th class="checker">选择</th>
					<th class="view">图片预览</th>
					<th>书名</th>
					<th class="nums">数量</th>
					<th>状态</th>
					<th class="price">价格</th>
				</tr>
				<c:choose>
						<c:when test="${!empty cartlist }">
							<c:forEach items="${cartlist }" var="cartvo" varStatus="vs">
								<tr class="${vs.index%2==0?'':'odd' }">
									<td><input type="checkbox" name="bid" value="${cartvo.bid }" /></td>
									<td class="thumb"><img src="${pageContext.request.contextPath }/images/book/${cartvo.image }" /></td>
									<td class="title">${cartvo.bookname }</td>
									<td><input id="cart${cartvo.bid }" class="input-text" type="text" name="nums" onblur="javascript:updatecart(${cartvo.bid })" value="${cartvo.count }" 
									onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" /></td>
									<td id="stock${cartvo.bid }">${cartvo.count<=cartvo.stock?"有货":"<font color='red'>无货</font>" }</td>
									<td>￥ <span><a name="bookprice">${cartvo.count*cartvo.price}</a></span></td>	
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise><tr><td colspan="5">购物车为空</td></tr></c:otherwise>
					</c:choose>
			</table>
			<div class="button">
				<h4>总价：￥<span><a id="totalprice"></a></span>元&nbsp;&nbsp;<a href="javascript:delcart()">从购物车删除</a></h4>
				<input class="input-chart" type="button" onclick="addtoorderlist()" />
			</div>
		</form>
	</div>
</div>
<jsp:include page="common/end.jsp" />
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- 分页 -->
<div class="page-spliter">
				<a href="javascript:jump(${page.pagenum-1==0?1:page.pagenum-1})">&lt;</a>
				<a href="javascript:jump(1);">首页</a>
				<c:if test="${page.startPage>1 }">
					<span>...</span>
				</c:if>
				<c:forEach begin="${page.startPage}" end="${page.endPage}" var="num">
					<c:choose>
						<c:when test="${page.pagenum==num}">
							<span class="current" >${num}</span>
						</c:when>
						<c:otherwise>
							<a  href='javascript:jump(${num})'>${num}</a>
						</c:otherwise>
					</c:choose>	
				</c:forEach>
				<c:if test="${page.endPage<page.totalpage}">
					<span>...</span>
				</c:if>
				<a href="javascript:jump(${page.totalpage})">尾页</a>
				<a href="javascript:jump(${page.pagenum+1>page.totalpage?page.totalpage:page.pagenum+1})">&gt;</a>



</div>
<script type="text/javascript">
	function jump(num){
		var keywords=document.getElementById("keywords").value;
		var history=document.getElementById("history").value;
		if(num==null){
			num = document.getElementById("s1").value;
		}
		if(history==""){
			if(keywords==""){
				window.location.href="${pageContext.request.contextPath}/${model}?page="+num;
			}else{
				window.location.href="${pageContext.request.contextPath}/${model}?keywords="+keywords+"&page="+num;
			}
		}else{
			if(keywords==""){
				window.location.href="${pageContext.request.contextPath}/${model}?history="+history+"&page="+num;
			}else{
				window.location.href="${pageContext.request.contextPath}/${model}?keywords="+keywords+"&history="+history+"&page="+num;
			}
		}
	}
</script>
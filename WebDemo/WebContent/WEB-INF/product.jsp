<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/static/taglib.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

 <%
    String contextPath=request.getServletContext().getContextPath();
 %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@
 include file="/static/includetop.jsp"
 %>
	<table>
	<thead>
		<tr>
		<td>书名</td>
        <td>价格</td>
        <td>目录</td>
        <td>操作</td>
        </tr>
		<c:forEach items="${products}" var="pro">
			<tr>
			<td>${pro.proName }</td>
			<td>${pro.price }</td>
			<td>${pro.category }</td>
			<td><a href="<%= contextPath%>/cart?proId=${pro.proId }">添加到购物车</a></td>
			</tr>
		</c:forEach>

 </thead>
	</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/static/taglib.jsp"%>


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

	<div>您好，请选择你想要的商品</div>
	<a href="${context }/product?book=1">小说</a>
	<br>
	<a href="${context }/product?book=2">教科书</a>
	<br>
	<a href="${context }/product?book=3">杂志</a>
	<br>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/static/taglib.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
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
<form action="${context }/item" method="post">
<table>
<thead>
<tr>
    <td>书名</td>
    <td>数量</td>
     <td>总价</td>
     <td>操作</td>
</tr>
<c:forEach items="${cartList}" var="cart" >
<tr>
     <td name="product">${cart.proName }</td>
     <td><input type="text" name="quantity" value="${cart.quantity }"></td>
      <td>${cart.total }</td>
      <td><a href="${context }/item?proName=${cart.proName }">删除</a></td>
</tr>
</c:forEach>

<td><button name="upd" value="clean">清空购物车</button></td>
<td><button name="upd" value="update">更新购物车</button></td>
<td>订单总额:${total }</td>
</table>
</form>

</body>
</html>
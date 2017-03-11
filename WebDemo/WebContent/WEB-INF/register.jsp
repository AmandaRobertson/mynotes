<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/static/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
</head>
<body>

	<form action="${context }/user" method="post">
		<input type="hidden" name="method" value="register"> <input
			type="text" name="username" id="username" value=""> <br>
		<input type="password" name="password"><br>
		<button>注册</button>
	</form>

</body>
</html>
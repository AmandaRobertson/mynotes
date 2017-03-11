<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
    <c:if test="${ susername!= null }">
    <h>欢迎 ${susername }</h>
    <a href="${context }/user?method=logout ">登出</a>
    </c:if>
    </div>

<%@page import="com.google.gson.Gson"%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<c:choose>
	<c:when test="${!empty loginUser}">
		<%= new Gson().toJson(session.getAttribute("loginUSer"))%>
	<c:otherwise>
		{"userId": ""}
	</c:otherwise>
	</c:when>
</c:choose>


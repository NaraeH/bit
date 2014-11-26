
<%@page import="java.util.Date"%>
<%@page import="java63.servlets.test05.domain.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>URL 만들기</h1>
	<p>url을 만들어 준다.</p>
	
	<h2>c:url</h2>
	<c:url var='myUrl' value='http://localhost:8080/web02/test05/product/add'>
		<c:param name="name" value="오호라폰"/>
		<c:param name="qty" value="200"/>
		<c:param name="mkno" value="6"/>
	</c:url>
	
	<a href="${pageScope.myUrl}">등록</a><br>
	
<h2>c:import</h2>
<p>지정된 URL의 콘텐츠를 가져오기</p>
<textarea rows="10" cols="80">
<%-- <c:import url="http://www.daum.net"/> --%>
</textarea>
	
	<h2>c:redirect</h2>
 	<c:set var="age" value="15"/>
		<c:if test="${pageScope.age < 19 }">
			<c:redirect url="http://www.youtube.com/watch?v=Y3tuavZ5-cE" /><!--import의 url과 겹치면 error!  -->
		</c:if>
	
	<h2>fmt:parseDate</h2>
	<p>특정 패턴을 지닌 문자열을 해석하여 java.util.Date객체로 만든다.</p>
 	<fmt:parseDate var="date1" value="2014-11-25" pattern="yyyy-MM-dd"/>
	${date1}<br> 
	
	<h2>fmt:formatDate</h2>
	<p>java.util.Date 객체를 가지고 문자열을 만든다.</p>
	<%
	pageContext.setAttribute("today", new Date());
	%>
	<fmt:formatDate value="${today}" pattern="yyyy-MM-dd"/>
</body>
</html>

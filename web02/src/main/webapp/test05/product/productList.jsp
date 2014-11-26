<%@page import="java63.servlets.test05.domain.Product"%>
<%@page import="java.util.List"%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/common/header" /> 
</head>

<body>
<%
	//위의 태그는 다음과 같은 자바 코드를 생성한다.
	//RequestDispatcher rd = request.getRequestDispatcher("common/header"); 
	//rd.include(request, response);
%>

	<div class='container'>
		<h1>제품목록(JSP v1.1)</h1>
		<p>
		<!-- html에서는 바로 jsp로 링크하지 말고 항상 !!!!!! controller(즉,servlet파일)를 경우해라!!!! => 일관성을 유지하기 위해 (add가 요청될 때 주소로 요청되는 경우도 무조건 listServlet을 경우해서 가므로) -->
			<a href='add' class='btn btn-primary'>새제품</a>
		</p>
		<table class='table table-hover'>
			<tr>
				<th>#</th>
				<th>제품</th>
				<th>수량</th>
				<th>제조사</th>
			</tr>

			<tr>
			</tr>
			<c:forEach items="${products}" var="product" >
				<tr>
					<td>${product.no}</td>
					<td><a href='view?no=${product.no}'>${product.name}</a></td>
					<td>${product.quantity}</td>
					<td>${product.makerNo}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
<script src='../../js/jquery-1.11.1.js'></script>
 <jsp:include page="/common/footer"/> 
</body>
</html>

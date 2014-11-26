<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/common/Header.jsp" /> 
</head>

<body>
	<div class='container'>
		<h1>제품목록(JSP v1.1)</h1>
		<p>
			<a href='add.do' class='btn btn-primary'>새제품</a>
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
				<!-- 만약, product가 맵객체라면 no는 setter/getter가 아닌 key를 의미 -->
					<td>${product.no}</td>
					<td><a href='view.do?no=${product.no}'>${product.name}</a></td>
					<td>${product.quantity}</td>
					<td>${product.maker}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
<script src='../js/jquery-1.11.1.js'></script>
 <jsp:include page="/common/Footer.jsp"/> 
</body>
</html>


<%@page import="java63.servlets.test05.domain.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>Core tag 사용</h1>
	
	<h2>c:out</h2>
	<p>출력문을 만든다.</p>
	
	<c:out value="졸리당 출력!"/><br>
	<c:out value="${null}" default="기본값"/><br> <!--앞의 value가 null일 경우 default로 출력된다.  -->
	<c:out value="졸리당 출력!">기본 값</c:out><br>
	<c:out value="${null}">기본 값</c:out><br>
	
	위의 코드는 아래의 자바코드와 동일하다.<br>
	<%="오호라 출력" %>
	
	<h2>c:set</h2>
	<p>변수를 생성하거나 변수의 값을 설정할 때 사용</p>
	
	<c:set var="name1" value="홍길동" scope="page"/>
	<c:set var="name2" scope="page">이순신</c:set>
	<!--위의 코드는 아래의 자바코드와 동일하다.  -->
	<%
	String name3 = "성춘향";
	pageContext.setAttribute("name3", name3);
	%>
	\${requestScope.name1} = ${requestScope.name1}<br>
	\${pageScope.name1} = ${pageScope.name1}<br>
	\${pageScope.name2} = ${pageScope.name2}<br>
	\${pageScope.name3} = ${pageScope.name3}<br>
	
	\${name1} => ${name1}<br>
	\${name2} => ${name2}<br>
	\${name3} => ${name3}<br>
	
	<h2>객체에 property값 설정하기</h2>
	<%
	Product product = new Product();
	product.setName("아이폰");
	product.setQuantity(100);
	product.setMakerNo(1);
	
	pageContext.setAttribute("product", product);
	%>
	\${product.name} => ${product.name}<br>
	
	<!-- target은 프로퍼티 값을 설정할 대상 객체 => 대상 객체는 자바빈객체나 Map이어야 한다. 반드시 EL을 이용해서 지정해주어야 한다. -->
	<c:set target="${product}" property="name" value="베레기"/>
	\${product.name} => ${product.name}<br>
	
	<h2>c:remove</h2>
	<p>보관소에 저장된 객체 제거하기</p>
	지우기전 => ${pageScope.name1}<br>
	<c:remove var="name1" scope="page"/>
	제거한 후의 name1은? ${pageScope.name1}<br>
	
	지우기전 => ${pageScope.name2}<br>
	<%pageContext.removeAttribute("name2"); %>
	제거한 후의 name2은? ${pageScope.name2}<br>

</body>
</html>

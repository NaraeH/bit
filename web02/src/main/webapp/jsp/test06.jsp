<%@page import="java.util.List"%>
<%@page import="java63.servlets.test05.domain.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>

<body>
	<h1>1. 빈 객체 생성</h1>
	<p>태그를 이용하여 보관소에 저장된 객체를 꺼내거나, 새로 객체를 생성하는 문법</p>
     <jsp:useBean type='java63.servlets.test05.domain.Product' 
     				id="product" 
     				class="java63.servlets.test05.domain.Product" 
     				scope='page'/>
    	
		<!-- 자바코드: 위의 코드는 아래의 자바크드와 같다.
		Product product = (Product)pageContext.getAttribute("product");
		     if(products == null){
		     	products = new Product();
		     	
		     	class => 인스턴스를 생성할때 필요(인터페이스가 와서는 안된다!!!! => new를 할 수 없으므로) 
		     	type  => 변수의 타입지정
		 -->

    	<p>제품명: <%=product.getName()%></p>
    	<p>수량: <%=product.getQuantity()%></p>
    	<p>제조사번호: <%=product.getMakerNo()%></p>
    	
    <hr>
    	
    <h1>2. 빈 객체 생성(type, 속성 생략)</h1>
    <h2>2.1 type생략</h2>
	<p>타입 속성을 생략하면 class 속성을 사용한다.</p>
    <jsp:useBean id="product2" 
     				class="java63.servlets.test05.domain.Product" 
     				scope='page'/>
 

    	<p>제품명: <%=product2.getName()%></p>
    	<p>수량: <%=product2.getQuantity()%></p>
    	<p>제조사번호: <%=product2.getMakerNo()%></p>
    	
    <h2>2.2 class 생략</h2>
	<p>class 속성을 생략하면 보관소에서 객체를 찾을 수 없을 때, 객체를 생성하지 못한다. 오류발생!</p>
	<%-- 
	<!-- 
	Product product3 = (Product)pageContext.getAttribute("product3");
	if(product3 == null){ //객체를 못찾으면 오류
		throw new InstantiationException(......);
	}
	 -->
    <jsp:useBean id="product3" 
     				type="java63.servlets.test05.domain.Product" 
     				scope='page'/>
 

    	<p>제품명: <%=product3.getName()%></p>
    	<p>수량: <%=product3.getQuantity()%></p>
    	<p>제조사번호: <%=product3.getMakerNo()%></p> 
     --%>	
    <h2>2.3 빈 객체 찾기</h2>
	
	<%Product temp = new Product();
	temp.setName("이름이다");
	temp.setQuantity(100);
	temp.setMakerNo(1);
	pageContext.setAttribute("product4", temp);
	%>
	
	<jsp:useBean id="product4" 
     				type="java63.servlets.test05.domain.Product" 
     				scope='page'/>

    	<p>제품명: <%=product4.getName()%></p>
    	<p>수량: <%=product4.getQuantity()%></p>
    	<p>제조사번호: <%=product4.getMakerNo()%></p>
</body>
</html>


<%--  
JSP액션 태그
    => jsp:useBean사용 
    ServletRequest(request), HttpSession(session), ServletContext(application), PageContext(page)로 부터 객체 꺼내기 또는 객체 생성
    
    
    속성
    id => 레퍼런스 변수
    scope => 보관소 지정 page, request, session, application
    class => 보관소에서 꺼낼 객체의 타입. 또는 객체 생성을 위한 타입
    type => 레퍼런스 변수의 타입
     <jsp:useBean type='java.util.List' id="products" class="java.util.ArrayList" scope='request'/>

	=> 위와 코드와 같음(위는 스프링을 이용한 것)     
     java.util.List products = (java.util.List)request.getAttribute("products");
     if(products == null){
     	products = new java.util.ArrayList();
     } 
--%>
    

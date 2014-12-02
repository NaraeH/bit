<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page language="java" contentType="text/plain; charset=UTF-8"
	pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>

<c:set var="count" value="1"/>
{
 "currengPage": ${currentPageNo},
 "maxPageNo": ${maxPageNo},
 "product" : [
 <c:forEach items="${products}" var="product" >
 	<c:if test="${count > 1}">,</c:if>
 	{"no":${product.no},
 	 "name":"${product.name}", 
 	 "quantity":${product.quantity}, 
 	 "maker":"${product.maker}"}
 	 <c:set var="count" value="${count+1}"/>
</c:forEach>
 	 ]
 }
 
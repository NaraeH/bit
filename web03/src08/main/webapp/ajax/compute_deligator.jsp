<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>

<c:url var='myUrl' value='http://192.168.0.144:8080/web03/ajax/compute2.jsp'>
	<c:param name="a" value="${param.a}"/>
	<c:param name="op" value="${param.op}"/>
	<c:param name="b" value="${param.b}"/>
</c:url>
<c:import url="${myUrl}"/> 

<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//쿠키를 보낼 수 있는 url을 지정하기
Cookie cookie1 = new Cookie("name", "aaa");
cookie1.setPath("/web03");

Cookie cookie2 = new Cookie("tel", "010-2222");
cookie2.setPath("/web03");

//쿠키를 보낼 수 있는 url을 지정하지 않으면 오로지 쿠키를 생성한 url과 같은 경로에 대해서만 보낼 수 있다.
Cookie cookie3 = new Cookie("email", "aaa@bit.com");
Cookie cookie4 = new Cookie("addrss", URLEncoder.encode("강남구", "UTF-8"));

response.addCookie(cookie1);
response.addCookie(cookie2);
response.addCookie(cookie3);
response.addCookie(cookie4);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>쿠키 보내기</h1>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="form04.jsp" method="post">
		이    름: <%=session.getAttribute("name") %> 
		전    화: <%=session.getAttribute("tel") %> 
		우편번호: <%=session.getAttribute("postNo") %> 
		주    소: <%=session.getAttribute("address") %> 
		회    사: <%=request.getParameter("company") %> 
		직    위: <%=request.getParameter("position") %> 
	</form>

</body>
</html>
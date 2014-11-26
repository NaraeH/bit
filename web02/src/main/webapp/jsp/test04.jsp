<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<h1>Declaration Element</h1>
<p>클래스멤버(변수/메서드/블럭)를 추가할 때 사용</p>

<%!int i = 20; 

void m(int v){
	i+= v + m2();
}%>
<%!
int m2(){
	return 100;
}%>
<%m(40);%>
m() 호출결과: <%=i %>

<p>태그안의 내용은 그대로 클래스 블록 안으로 복사된다.
따라서, 이 태그는 어디에 선언되든 상관없다.
</p>
</body>
</html>



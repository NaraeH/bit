<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    trimDirectiveWhitespaces="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<h1>Directive Element</h1>
	<p>서블릿 클래스에 특정 자바 코드를 넣는다. 옵션에 따라 다르다.</p>

	<h2>page 지시어</h2>
	<ul>
		<li>import: 자바의 import문 생성</li>
		<li>contentType: response.setConentType() 호출문 생성</li>
		<li>trimDirectiveWhitespaces: 지시어 끝에 붙는 newline 출력문을 제거한다.</li>
		<li>language: JSP페이지에 넣을 수 있는 프로그래밍 언어 지정. 현재는 java만 가능</li>
		<li>pageEncoding: response.setCharacterEncoding()호출문 생성
			contentType속성에서 charset 설정을 모두 처리할 수 있다.</li>
	</ul>
	<p>JSP출력과 관련된 설정을 처리</p>
	<%@ page import="java.util.*"%>
	<%@ page import="java.net.Socket"%>
	<%@ page import="java.lang.reflect.*, java.lang.String"%>

	<h2>include 지시어</h2>
	<p>다른 파일의 내용을 가져와서 붙인다.(import) => 읽어와서 그자리에 붙여라 forward/include의
		동작과는 다르다. 절대 실행하지 않는다. 제어권이 넘어가지 않는다. 따라서 서블릿일 필요가 없다.</p>

	<%@ include file="header.txt"%>
	<p>하하하</p>
	<%@ include file="footer.inc"%>

	<h2>taglib 지시어</h2>
	<p>JSP페이지에서 사용할 태그를 추가한다. 즉, 확장 태그를 사용할 때 필요하다.</p>

	<!-- c란 이름으로 참조하겠다. -->
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<%
		ArrayList<String> nameList = new ArrayList<String>();
		nameList.add("홍길동");
		nameList.add("임꺽정");
		nameList.add("윤봉길");
		nameList.add("안중근");
		pageContext.setAttribute("nameList", nameList);
	%>
	
	<!-- 위에서 참조하겠다고 선언한 c에 대해 for each문 씀 -->
	<ol>
		<c:forEach var="name" items="${nameList}">
			<li>${name}</li>
		</c:forEach>
	</ol>
</body>
</html>



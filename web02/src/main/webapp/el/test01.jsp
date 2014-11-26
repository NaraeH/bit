<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>EL(Expression Language)</h1>

	<h1>JSP기본 객체 (Built-in객체)</h1>
	<p>JSP가 서블릿 클래스를 만들 때  _jspService()메서드에 기본으로 준비하는 객체</p>
	
	<ul>
		<li>jspWriter out = .....;</li>
		<li>ServletContext application = ......;</li>
		<li>HttpSession session = ....;</li>
		<li>HttpServletRequest request 파라미터</li>
		<li>HttpServletResponse response 파라미터</li>
		<li>Object page = this; </li>
		<li>ServletConfig config = ....;</li>
		<li>Exception error = .....;(오류를 처리하는 JSP인 경우에만)</li>
	</ul>
	
	<h1>EL객체 정리</h1>
	<!-- 
	${객체.프로퍼티}
	객체명                   : 설명                       : 사용예
	pageContext      : ....              : ${pageContext.객체} 
	pageScope        :                   : =>  ${pageScope.객체}
	servletContext   : ServletContext    : ${pageContext.servletContext.객체} 
	applicationScope :                   : =>  ${pageContext.servletContext.객체}
	session          : HttpSession       : ${pageContext.session.객체}
	request          : ServletRequest    : ${pageContext.request.객체}
	response         : ServletResponse  
	param            : getParameter(이름) : ${param.이름}
	header           : getHeader(헤더명)   : ${header.헤더명}
	cookie           : getCookies()..    : ${cookie.헤더명}
	initParam        : getInitParameter(): ${initParam.이름}
	-->

</body>
</html>

<%-- 
	${객체면.프로퍼티} 또는 ${객체명["프로퍼티"]} 
	예) ${member.no} 또는 ${member["no"]}
	
	* 위의 코드를 java코드로 바꾸면?
	Member member = (Member)pageContext.findAttribure("member");
	member.getNo();
	
	< findAttribute()가 객체를 찾는 순서 >
	JspContext => ServletRequest => HttpSession => ServletContext => null
	
	< 보관소 지정 >
	pageScope => JspContext
	requestScpre => ServletRequest
	sessionScope => HttpSession
	applicationScope => ServletContext
	
	ex) {pageScope.member.no} => pageContext.getAttribute("member").getNo()
	    {requestScpre.member.no} => request.getAttribute("member").getNo()
	    {sessionScope.member.no} => session.getAttribute("member").getNo()
	    {applicationScope.member.no} => application.getAttribute("member").getNo()
	 --%>
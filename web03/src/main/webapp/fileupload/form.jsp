<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- enctype="application/x-www-form-urlencoded => 파일을 업로드 했을 때 바이너리 값이 서버에 전달되지 않는다. 
	바이너리 데이터를 서버에 보내려면, form의 enctype타입을 multipart/form-data로 설정해야 한다.
	enctype 종류: application/x-www-form-urlencoded, multipart/form-data, text/plain 
	
	1. application/x-www-form-urlencoded
	
	2. multipart/form-data 형식의 요청 프로토콜
	Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
	Accept-Encoding:gzip, deflate
	Accept-Language:ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4
	Cache-Control:max-age=0
	Connection:keep-alive
	Content-Length:26444
	Content-Type:multipart/form-data; boundary=----WebKitFormBoundaryWQMAj712Xu7uyTWt
	------WebKitFormBoundaryWQMAj712Xu7uyTWt
	Content-Disposition: form-data; name="name"
	
	aaa
	------WebKitFormBoundaryWQMAj712Xu7uyTWt
	Content-Disposition: form-data; name="age"
	
	12
	------WebKitFormBoundaryWQMAj712Xu7uyTWt
	Content-Disposition: form-data; name="photo"; filename="s01.jpg"
	Content-Type: image/jpeg
	
	
	------WebKitFormBoundaryWQMAj712Xu7uyTWt--  ==> 끝부분은 "--" 경계의 끝이라는 표시
	
	3. text/plain 
	-->
	<form action="upload.jsp" method="post" enctype="multipart/form-data">
	이름: <input type="text" name="name"><br>
	나이: <input type="text" name="age"><br>
	사진: <input type="file" name="photo"><br>
	<button>등록</button>
	</form>
</body>
</html>
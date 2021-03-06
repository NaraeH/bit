<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<head>
<jsp:include page="/common/Header.jsp" /> 
<title>Insert title here</title>
</head>

<body>
<div class='container'>
<jsp:include page="/common/loginPanel.jsp"/>
<h1>제품 등록</h1>

<form class='form-horizontal' role='form' action='add.do' method='post' enctype="multipart/form-data">
  <div class='form-group'>
    <label for='name' class='col-sm-2 control-label'>제품</label>
    <div class='col-sm-10'>
      <input type='text' class='form-control' id='name' name='name' placeholder='제품명 예) 아이폰6플러스'>
    </div>
 </div>
 
  <div class='form-group'>
    <label for='qty' class='col-sm-2 control-label'>수량</label>
    <div class='col-sm-10'>
      <input type='text' class='form-control' id='qty' name='qty' placeholder='수량 예) 20'>
    </div>
  </div>
  
  <div class='form-group'>
    <label for='mkno' class='col-sm-2 control-label'>제조사</label>
    <div class='col-sm-10'>
    <select name="mkno" class='form-control'>
    	<option value="0">제조사를 선택하세요</option>
    	<c:forEach items="${makers}" var="maker">
    		<option value='${maker.no}'>${maker.name}</option>
    	</c:forEach>
    </select>
    </div>
 </div>
 
   <div class='form-group'>
    <label for='photo' class='col-sm-2 control-label'>사진</label>
    <div class='col-sm-10'>
      <input type='file' class='form-control' id='photo' name='photo'>
    </div>
  </div>
 
<div class='form-group'>
  <button id='btnAdd' type='submit' class='btn btn-primary'>등록</button>
  <button id='btnCancel' type='button' class='btn btn-primary'>취소</button>
</div>
</form>

</div>
 <jsp:include page="/common/Footer.jsp"/> 
<script src='../js/jquery-1.11.1.js'></script>
<script>
$('#btnCancel').click(function(){
	history.back();
});

$('#btnAdd').click(function(){
	if($('#name').val().length == 0){
		alert("이름은 반드시 입력하여야 합니다.");
		return false; 
	}
	
	if($('#qty').val().length == 0){
		alert("수량은 반드시 입력하여야 합니다.");
		return false;  
	}
	
	if($('#mkno').val() == '0'){
		alert("제조사를 선택하세요");
		return false;  
	}
	
});
</script>
</body>
</html>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<head>
<!-- <link rel='stylesheet' href='../../css/bootstrap.min.css'>
<link rel='stylesheet' href='../../css/bootstrap-theme.min.css'>
<link rel='stylesheet' href='../../css/common.css'> -->
<jsp:include page="/common/header" /> 
<title>Insert title here</title>
</head>

<body>
<div class='container'>
<h1>제품 등록</h1>
<!--form태그에서 action은 submit버튼을 눌렀을 때 서버를 통해 이동할 페이지 주소를 의미한다.
때문에 form태그안에 서버에 보내주어야 할 값들은 name을 써서 파라미터로 서버에서 받을 수 있도록 해야한다.(id는 스크립트에서 쓰기위한 라벨이고, name은 서버에서 값을 받기 위한 라벨이다.) 
*주의: 때문에 submit버튼은 반드시 form태그 안에있어야 한다. -->
<form class='form-horizontal' role='form' action='add' method='post'>
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
    <input type='text' class='form-control' id='mkno' name='mkno' placeholder='제조사번호 예) 2'>
    </div>
 </div>
<div class='form-group'>
  <button id='btnAdd' type='submit' class='btn btn-primary'>등록</button>
  <button id='btnCancel' type='button' class='btn btn-primary'>취소</button>
</div>
</form>

</div>
 <jsp:include page="/common/footer"/> 
<script src='../../js/jquery-1.11.1.js'></script>
<script>
$('#btnCancel').click(function(){
	history.back();
});

$('#btnAdd').click(function(){
	if($('#name').val().length == 0){
		alert("제품명은 필수입력 항목입니다.");
		return false;  //서버에 요청하지 않는다.
	}
	
	if($('#qty').val().length == 0){
		alert("수량은 필수입력 항목입니다.");
		return false;  //서버에 요청하지 않는다.
	}
	
	if($('#mkno').val().length == 0){
		alert("제조사 번호는 필수입력 항목입니다.");
		return false;  //서버에 요청하지 않는다.
	}
	
});
</script>
</body>
</html>

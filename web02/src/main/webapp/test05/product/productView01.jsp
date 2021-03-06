<%@page import="java.util.List"%>
<%@page import="java63.servlets.test05.domain.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/common/header" />
</head>

<body>
<%Product product = (Product)request.getAttribute("product"); %>
	<div class='container'>
		<h1>제품정보</h1>
		<form class='form-horizontal' role='form' action='update'
			method='post'>
			<div class='form-group'>
				<label for='no' class='col-sm-2 control-label'>#</label>
				<div class='col-sm-10'>
					<input type='text' class='form-control' readonly id='no' name='no'
						value='<%=product.getNo()%>'>
				</div>
			</div>
			<div class='form-group'>
				<label for='name' class='col-sm-2 control-label'>제품</label>
				<div class='col-sm-10'>
					<input type='text' class='form-control' id='name' name='name'
						value='<%=product.getName()%>'>
				</div>
			</div>
			<div class='form-group'>
				<label for='qty' class='col-sm-2 control-label'>수량</label>
				<div class='col-sm-10'>
					<input type='text' class='form-control' id='qty' name='qty'
						value='<%=product.getQuantity()%>'>
				</div>
			</div>
			<div class='form-group'>
				<label for='mkno' class='col-sm-2 control-label'>제조사</label>
				<div class='col-sm-10'>
					<input type='text' class='form-control' id='mkno' name='mkno'
						value='<%=product.getMakerNo()%>'>
				</div>
			</div>
			<div class='form-group'>
				<button id='btnUpdate' type='submit' class='btn btn-primary'>변경</button>
				<button id='btnDelete' type='button' class='btn btn-primary'>삭제</button>
				<button id='btnCancel' type='button' class='btn btn-primary'>취소</button>
			</div>
	</form>
	</div>
	
	<script src='../../js/jquery-1.11.1.js'></script>
	<script>
		$('#btnCancel').click(function() {
			history.back();
		});
		$('#btnDelete').click(function() {
			if (window.confirm('삭제하시겠습니까?z')) {
				location.href = 'delete?no=3';
			}
		});
		$('#btnUpdate').click(function() {
			if ($('#name').val().length == 0) {
				alert('제품명은 필수입력 항목입니다.');
				return false;
			}
			if ($('#qty').val().length == 0) {
				alert('수량은 필수입력 항목입니다.');
				return false;
			}
			if ($('#mkno').val().length == 0) {
				alert('제조사 번호는 필수입력 항목입니다.');
				return false;
			}
		});
	</script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel='stylesheet' href='../css/bootstrap.min.css'>
<link rel='stylesheet' href='../css/bootstrap-theme.min.css'>
<link rel='stylesheet' href='../css/common.css'>
<link rel='stylesheet'
	href='//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css'>
<script src='../js/jquery-1.11.1.js'></script>
<title>Insert title here</title>
</head>
<body>

	<div class='container'>
		<h1>제품목록</h1>
		<p>
			<a href='product-form.html' class='btn btn-primary'>새제품</a>
		</p>
		<table id='list' class='table table-hover'>
			<tr>
				<td><a href='view?no=${id}'> ${name} </a></td>
			</tr>
			
		</table>

	</div>

	<address class='copyright'>Copyright&copy; Bit</address>
</body>
	<script>
		$(function(){
				console.log(${member});
				<%String test = request.getAttribute("test").toString();
				String[] t = test.replace("/[|]/g", "").split(", ");
				
				for(String s:t){%>
					console.log(<%=s%>);
				<%}
				%>
				
				//console.log("zz");
				//console.log(${id2});

		});
	</script>


</body>
</html>
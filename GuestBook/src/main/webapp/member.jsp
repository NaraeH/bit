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
<title>자바 63기</title>
<style>

.container{
	width:1100px;
	height: 1000px;
	margin: 0 auto;
}

#tableList {
	width: 1020px;
	height: 500px;
	margin: 0 auto;
	overflow-x: auto;
}

#list {
	width: 1000px;
	height: 500px;
	margin: 0 auto;
	overflow-x: auto;
}

#list.dateRow{
	
}

.photoT {
	width:350px;
	margin: 15px;
	margin-left: 100px;
	border-radius: 15px;
}

.photoSS {
	width:220px;
	height:220px;
	margin-top: 128px;
	margin-left: 150px;
	border-radius: 15px;
}

.photoS {
	width:170px;
	height:170px;
	border-radius: 15px;
}
</style>
</head>

<body>
<div>
	<div class='container'>
		<h1>java 63기</h1>
	
		  <div class='form-group'>
		  	<img src='../member/photo/1.jpg' id='photoTeacher' class='photoT'/>
		    <img src='../member/photo/2.jpg' id='photoStudent' class='photoSS'/>
		  	<button type="button">강사님께 글쓰러가기</button>
		    <button type="button">친구에게 글쓰러가기</button>
		 </div>

		<div id=tableList>
			<table id='list' class='table table-hover'>
			</table>
		</div>
	</div>
	<address class='copyright'>Copyright&copy; Bit</address>
</div>

	<script>
		$(function(){
				<%String test = request.getAttribute("test").toString();
				String[] param = test.replace("[", "").replace("]", "").split(", ");
				
				String[] ids = new String[param.length/3];
				String[] names = new String[param.length/3];
				String[] photos = new String[param.length/3];
				
				int indexId = 0, indexName = 0, indexPhoto = 0;
				for(int i=0; i < param.length; i ++){
					if(param[i].startsWith("id")){
						ids[indexId] = param[i].split("=")[1];
						indexId++;
					}else if(param[i].startsWith("name")){
						names[indexName] = param[i].split("=")[1];
						indexName++;
					}else if(param[i].startsWith("photo")){
						photos[indexPhoto] = param[i].split("=")[1];
						indexPhoto++;
					}
				}%>
				

				<%for (int i = 0; i < names.length/5; i++) { 
					 /* j = i*4;  */%>
					console.log(<%=i%>);
 					$("<tr>")
 						.attr("class", "dateRow")
 					<%for(int j = i*5; j < names.length; j++){%>
 						<%if(j <= (i+1)*5-1) {%>
						.append($("<td>")
								.append(($("<a>").attr("href", "#"))
										.append($("<img>").attr('src', '../member/photo/' + <%=photos[j+1]%>).attr('class','photoS').attr('id', <%=photos[j+1]%>))))
 						<%}%>
 					<%}%>
					.appendTo($("#list")); 
				<%}%>
				
				$("#list img").click(function(event){
					event.stopPropagation();
					console.log(this);
					console.log($(this).attr("id"));
					
					$("#photoStudent").attr("src", '../member/photo/' + $(this).attr("id"))
					//$("#list img").attr("border", "3px red");
				})
		});
	</script>
</body>
</html>
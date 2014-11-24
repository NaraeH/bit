<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel='stylesheet' href='../css/bootstrap.min.css'>
<link rel='stylesheet' href='../css/bootstrap-theme.min.css'>
<link rel='stylesheet' href='../css/common.css'>
<link rel='stylesheet' href='//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css'>
<script src='../js/jquery-1.11.1.js'></script>
<title>자바 63기</title>
<style>

.container{
	width:1500px;
	height: 1000px;
	margin: 0 auto;
	margin-top: 50px;
	border: 3px dotted #7ccde4;
	border-radius: 44px;
	background-color:  rgba(218,241,248,0.3);
}

.container h1 {
	height: 65px;
	width: 390px;
	background: white;
	font-size: 50px;
	/* margin: 0 auto; */
	margin-top: -32px;
	text-align: center;
	border-radius:5px;
	padding-top: 6px;
	color: #05639a;
	text-shadow: 3px 3px #7ccde4;
	font-weight: bolder;
	font-family:CURSIVE;
}

.container p{
	margin-left:250px;
	margin-top:35px;
}
}
.mainPhoto{
	margin: 0 auto;
}
#tableList {
	width: 1030px;
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
	margin-top:30px;
	margin-left: 360px;
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

.button1 {
	margin-left: 450px;
}

.button2 {
	margin-left: 302px;
}
</style>
</head>

<body>
<div>
	<div class='container'>
		<!-- <h1>엄진영과 아이들</h1> -->
	
		  <div class='form-group'>
		  	<div class="mainPhoto">
		  		<img src='../member/photo/1.jpg' id='photoTeacher' class='photoT ' title='엄진영 강사님'/>
		    	<img src='../member/photo/2.jpg' id='photoStudent' class='photoSS' title='지용근'/>
		    </div>
		    <div>
		  		<a target="_self" href="../board/list?no=1"  id='writeToTeacher'><button type="button" class='btn btn-info button1'>강사님께 글쓰러가기</button></a>
		    	<a target="_self" href="../board/list?no=2"  id='writeToStudent'><button type="button" class='btn btn-info button2'>친구에게 글쓰러가기</button></a>
		    </div>
		 </div>
		<p></p>
		<p>글을 쓰고 싶은 친구의 사진을 선택해주세요</p>
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
				
				<%for (int i = 0; i < names.length/5; i++) {%>
 					$("<tr>")
 						.attr("class", "dateRow")
 					<%for(int j = i*5; j < names.length; j++){%>
 						<%if(j <= (i+1)*5-1) {%>
						.append($("<td>")
								.append(($("<a>").attr("href", "#"))
										.append($("<img>")
												.attr('src', '../member/photo/' + <%=photos[j+1]%>)
												.attr('class','photoS')
												.attr('id', <%=j+2%>)
												.attr('title', <%=names[j+1]%>))))
 						<%}%>
 					<%}%>
					.appendTo($("#list")); 
				<%}%>
				
				$("#list img").click(function(event){
					event.stopPropagation();
					console.log(this);
					console.log($(this).attr("id"));
					$("#photoStudent").attr("src", '../member/photo/' + $(this).attr("id") + '.jpg')
									  .attr("title", $(this).attr("title"));
					$('#writeToStudent').attr("href", '../board/list?no=' + $(this).attr("id"));
				})
		});
	</script>
</body>
</html>
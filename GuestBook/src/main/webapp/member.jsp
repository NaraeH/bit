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
		<h1>java 63기</h1>
		
		  <div class='form-group'>
		  	<img src='강사님.jpg'/>
		     <img id='photo'/>
		 </div>

		<table id='list' class='table table-hover'>
		</table>

	</div>

	<address class='copyright'>Copyright&copy; Bit</address>
</body>
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
				
				<%-- <%for(int i = 0; i<ids.length; i++){%>
					console.log(<%=ids[i]%>);
				<%}%>
				
				<%for(int i = 0; i<names.length; i++){%>
				console.log(<%=names[i]%>);
				<%}%> --%>
				<%for (int i = 0; i < names.length; i++) {%>
					console.log(<%=names[i]%>);
 					$("<tr>")
					.append($("<td>").html(<%=ids[i]%>))
					.append($("<td>")
							.append($('<a>')
									.attr('name', <%=names[i]%>)
									.attr('href', '#')
									.html(<%=names[i]%>)))
					.appendTo($("#list")); 
				<%}%>
				
				$("#photo").attr("src", <%=photos[1]%>);

		});
	</script>


</body>
</html>
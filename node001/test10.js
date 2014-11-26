/*
GET요청과 POST 요청 구분하기
request.method 프로퍼티의 값을 구분 확인하라
 */
var http = require('http');
var url = require("url");
var qs = require("querystring");
var result = "";

http.createServer(function handler(req, res) {
	res.writeHead(200, {
		'Content-Type' : 'text/html;charset=UTF-8'
	});
	
	if(req.method == "GET"){
		
		var urlInfoMap = url.parse(req.url,true);
		
		diplayReuslt(req, res, parseInt(urlInfoMap.query.v1),
				parseInt(urlInfoMap.query.v2), 
				urlInfoMap.query.op);
		
	}else{
		var messageBody = "";
		//data 이벤트는 클라이언트에서 데이터를 읽을 때마다 data 이벤트가 발생할 때 마다 등록된 함수를 호출한다.
		//일정 시간 또는 일정 크기 단위로 발생한다.
		//따라서 개발자는 클라이언트가 보낸 데이터를 받고 싶으면, data 이벤트에 리스너를 등록하고 리스터가 호출될 때
		//넘어오는 파라미터 값을 보관하면 된다.
		//*point: data 이벤트가 발생할 때 마다 등록된 함수를 호출한다.(호출 횟수는 데이터 양에 따라 달라진다.)
		req.on("data", function(chunk){
			messageBody += chunk;
		});
		
		//모든 데이터 값을 다 읽었을 때는 end라는 이벤트 발생
		req.on("end", function(){
			console.log(messageBody);
			
			var paraMap = qs.parse(messageBody);
			
			diplayReuslt(req, res, parseInt(paraMap.v1), parseInt(paraMap.v2), paraMap.op);
		});
	}
	
}).listen(1337, '127.0.0.1');
console.log('Server running at http://127.0.0.1:1337/');

function diplayReuslt(req, res, v1, v2, op){
	
	res.write("<html><head><title>test10</title></head>");
	res.write("<body>");
	
	if(op == "plus"){
		result = v1 +" + "+ v2 +" = " + (v1+v2);
	}else if(op == "minus"){
		result = v1 +" - "+ v2 +" = " + (v1-v2);
	}else if(op == "multiplue"){
		result = v1 +" * "+ v2 +" = " + (v1*v2);
	}
	else if(op == "divide"){
		result = v1 +" / "+ v2 +" = " + (v1/v2);
	}else{
		result = "연산자가 없습니다.";
	}
	
	res.write("<h1>"+result+"</h1>");
	res.write("</body>");
	res.write("</html>");
	res.end(); //출력의 끝은 end 메세지로
}

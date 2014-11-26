/*
GET에서 값 받아오기
쿼리 객체에서 요청 값 꺼내기
*주의점: 쿼리 객체에서 받은 값은 전부 다 "문자열"이다!!
          숫자로 인지 하고 싶다면 parseInt(문자열, 몇진수) 로 하기!
 */
var http = require('http');
var url = require("url");

http.createServer(function handler(req, res) {

	if(req.method == "GET"){
		console.log("-------------GET 요청------------");
		
		var obj = url.parse(req.url, true);
		
		var v1 = parseInt(obj.query.v1, 10);
		var v2 = parseInt(obj.query.v2, 10);
		var op = obj.query.op;
		
		var result = v1 + v2;
		
		console.log("query", obj.query);
		console.log("op:", op);
		console.log("result:", result);
	}else{
		console.log("POST 요청");
	}
	res.end();
}).listen(1337, '127.0.0.1');
console.log('Server running at http://127.0.0.1:1337/');

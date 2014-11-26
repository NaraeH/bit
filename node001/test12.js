/*
GET에서 값 받아오기
url.parse(req.url, true);
=> true: query를 객체로 받아올 수 있음
 */
var http = require('http');
var url = require("url");

http.createServer(function handler(req, res) {

	if(req.method == "GET"){
		console.log("GET 요청");
		
		//url정보 분석 => 특히 query string(url에서 ? 다음에 오는 데이터)는 분해해서 객체로 만들어라.
		var obj = url.parse(req.url, true);
	
		console.log("URL:", obj.href);
		console.log("pathname:", obj.pathname);
		console.log("search", obj.search);
		console.log("query", obj.query);
	}else{
		console.log("POST 요청");
	}
	res.end();
}).listen(1337, '127.0.0.1');
console.log('Server running at http://127.0.0.1:1337/');

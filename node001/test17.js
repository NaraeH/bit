/*
GET에서 값 받아오기
쿼리 객체에서 요청 값 꺼내기
 *주의점: 쿼리 객체에서 받은 값은 전부 다 "문자열"이다!!
          숫자로 인지 하고 싶다면 parseInt(문자열, 몇진수) 로 하기!
 */
var http = require('http');
var url = require("url");

http.createServer(function handler(req, res) {
	

	if (req.method == "GET") {
		console.log("-------------GET 요청------------");

		var obj = url.parse(req.url, true);

		var v1 = parseInt(obj.query.v1, 10);
		var v2 = parseInt(obj.query.v2, 10);
		var op = obj.query.op;

		var result = compute(v1, op, v2);
		console.log(v1, op, v2, "=", result);
		
		displayResult(res, v1 + op + v2 + "=" + result);

	} else {
		console.log("POST 요청");
	}
	
}).listen(1337, '127.0.0.1');
console.log('Server running at http://127.0.0.1:1337/');

function compute(v1, op, v2){
	switch (op) {
	case "plus":
		return v1 + v2;
		break;
	case "minus":
		return v1 - v2;
		break;
	case "multiple":
		return v1 * v2;
		break;
	case "divide":
		return v1 / v2;
		break;
	default:
		return 0;
	}
}

function displayResult(res, content){
	res.writeHead(200, "OK", {
		"Content-type" : "text/html;charset=UTF-8"
	});
	res.write("<html><head><title>계산결과</title></head>");
	res.write("<body>");
	res.write("<h1>"+ content+ "</h1>");
	//res.write(v1+ op+ v2+ "="+ result);
	res.write("</body></html>");
	res.end();
	
}

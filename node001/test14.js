/*
compute() 함수 만들기
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

	} else {
		console.log("POST 요청");
	}
	res.end();
}).listen(1337, '127.0.0.1');
console.log('Server running at http://127.0.0.1:1337/');

function compute(v1, op, v2) {
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

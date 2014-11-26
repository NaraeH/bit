/*
오직 pathname이 /calc일때만 실행하게 한다.
 */
var http = require('http');
var url = require("url");

http.createServer(function handler(req, res) {
	var obj = url.parse(req.url, true);

	//클라이언트가 요청한 것을 걸러낸다.
	if (obj.pathname != "/calc") {
		res.end();
		return;
	} 

	if (req.method == "GET") {
		console.log("-------------GET 요청------------");

		var v1 = parseInt(obj.query.v1, 10);
		var v2 = parseInt(obj.query.v2, 10);
		var op = obj.query.op;

		var result = compute(v1, op, v2);
		console.log(v1, op, v2, "=", result);

		displayResult(res, v1 + op + v2 + "=" + result);

	} else {
		var messageBody = "";   //클라이언트가 보낼 데이터를 저장할 임시 변수를 준비한다.
		
		console.log("-----------------POST 요청----------------");
		
		//클라이언트가 보낸 데이터를 읽을 때마다 호출될 함수를 등록한다.
		req.on("data", function(chunk){
			messageBody += chunk; 
		});
		//클라이언트로부터 보낸 데이터를 모두 읽었을 때 호출될 함수 등록
		req.on("end", function(){
			
		});
	}

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

function displayResult(res, content) {
	res.writeHead(200, "OK", {
		"Content-type" : "text/html;charset=UTF-8"
	});
	res.write("<html><head><title>계산결과</title></head>");
	res.write("<body>");
	res.write("<h1>" + content + "</h1>");
	//res.write(v1+ op+ v2+ "="+ result);
	res.write("</body></html>");
	res.end();

}

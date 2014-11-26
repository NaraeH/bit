/*
<URL 알아내기> (test05~)
1. URL 정보 추출 기본
ex) GET /status?name=ryan HTTP/1.1\r\n
리턴 값 => status?name=ryan
 */
var http = require('http'); //http 객체 가져오기
var url = require("url"); //url 객체 가져오기

http.createServer(function handler(req, res) {
	console.log(req.url);
	
	//url로 부터 경로 정보와 데이터 정보를 분리하여 추출
	var urlInroMap = url.parse(req.url);
	console.log("전체정보:", urlInroMap.href);
	console.log("데이터:", urlInroMap.search);
	console.log("query:", urlInroMap.query);
	console.log("경로:", urlInroMap.pathname);
	
	res.writeHead(200, {
		'Content-Type' : 'text/html;charset=UTF-8'
	});
	res.write("<html><head><title>test04</title></head>");
	res.write("<body>");
	res.write("<h1> 오호라.. nodejs</h1>");
	res.write("<p> 서버당</p>");
	res.write("</body>");
	res.write("</html>");
	res.end(); //출력의 끝은 end 메세지로
	
	console.log("where?");
	
}).listen(1337, '127.0.0.1');
console.log('Server running at http://127.0.0.1:1337/');

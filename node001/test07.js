/*
요청 파라미터의 값 꺼내기
-클라이언트가 보낸 데이터(요청 파라미터)
 */
var http = require('http');
var url = require("url");
http.createServer(function handler(req, res) {
	res.writeHead(200, {
		'Content-Type' : 'text/html;charset=UTF-8'
	});
	
	var urlInfoMap = url.parse(req.url,true);
	
	var name = urlInfoMap.query.name;
	
	res.write("<html><head><title>test04</title></head>");
	res.write("<body>");
	res.write("<h1> 안녕하세요 "+name+"님</h1>");
	res.write("<p> 서버당</p>");
	res.write("</body>");
	res.write("</html>");
	res.end(); //출력의 끝은 end 메세지로
	
}).listen(1337, '127.0.0.1');
console.log('Server running at http://127.0.0.1:1337/');

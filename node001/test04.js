var http = require('http');
http.createServer(function handler(req, res) {
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
	
}).listen(1337, '127.0.0.1');
console.log('Server running at http://127.0.0.1:1337/');

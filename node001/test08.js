
var http = require('http');
var url = require("url");
http.createServer(function handler(req, res) {
	res.writeHead(200, {
		'Content-Type' : 'text/html;charset=UTF-8'
	});
	
	var urlInfoMap = url.parse(req.url,true);
	
	var v1 = parseInt(urlInfoMap.query.v1);
	var v2 = parseInt(urlInfoMap.query.v2);
	var op = urlInfoMap.query.op;
	var result = "";
	
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
	
	
	res.write("<html><head><title>test04</title></head>");
	res.write("<body>");
	res.write("<h1>"+result+"</h1>");
	res.write("</body>");
	res.write("</html>");
	res.end(); //출력의 끝은 end 메세지로
	
}).listen(1337, '127.0.0.1');
console.log('Server running at http://127.0.0.1:1337/');

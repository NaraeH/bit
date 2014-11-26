/*
GET요청과 POST 요청 구분하기
 */

http.createServer(function handler(req, res) {

	if(req.method == "GET"){
		console.log("GET 요청");
	
	}else{
		console.log("POST 요청");
	}
	res.end();
}).listen(1337, '127.0.0.1');
console.log('Server running at http://127.0.0.1:1337/');

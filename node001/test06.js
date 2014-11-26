/*
 * <URL 알아내기> 
2. URL에서 파라미터 값을 문장열이 아닌 객체로 추출하기
url.parse(URL, true) => 리턴 객체의 query에는 문자열이 아닌 객체가 들어 있다.
ex) 기존query => name=hong&age=20
    true값 준 query => { name: 'hong', age: '20' }
 */
var http = require('http'); //http 객체 가져오기
var url = require("url"); //url 객체 가져오기

http.createServer(function handler(req, res) {
	console.log(req.url);
	
	//url로 부터 경로 정보와 데이터 정보를 분리하여 추출
	var urlInfoMap = url.parse(req.url,true);
	console.log("전체정보:", urlInfoMap.href);
	console.log("데이터:", urlInfoMap.search);
	console.log("query:", urlInfoMap.query);
	console.log("경로:", urlInfoMap.pathname);
	
	//query 문자열이 객체로 준비되어 꺼내볼 수 있다.
	console.log("쿼리 객체 꺼내볼까?: ", urlInfoMap.query.name);
	console.log("쿼리 객체 꺼내볼까?: ", urlInfoMap.query.age);
	
	
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

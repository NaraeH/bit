package groovy01

//자바스크립트의 each() 흉내
//(배열, function(index, item)){};

scores = ["홍길동", 80, 100, 90]
scores.each({println "오호라"})
println "------------------------"

//메소드 호출할 때 괄호 생략가능하다.
scores.each({value ->  println value})
scores.each {value ->  println value} //문제없다. {}내부는 파라미터
println "------------------------"

scores.eachWithIndex ({value, index -> println value + ", " + index})
println "------------------------"

//메소드 정의
//1) 리턴은 있지만 적지 않은 경우
def plus(x, y){
	x + y //return 없는 것도 가능 => 맨 끝의 문장을 실행한 결과가 리턴된다.
}
//2) 리턴을 직접적으로 적어준 경우
int minus (x , y){
	return x - y
}

println(plus(10, 20)) //고유문법: 메소드 호출시 괄호 사용
println plus(10, 20) //groovy기본문법: 메서드 호출시 괄호 생략

result = minus(100, 50)
println result

multiple  = ({x, y -> x * y})
println multiple(30, 20)
println "------------------------"

/*//자바 스크립트로 위의 문장을 표현한다면,
multiple = function(x, y){
	console.log("")
	console.log("")
	return x * y
}*/


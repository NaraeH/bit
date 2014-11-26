package groovy01

//조건문
age = 70

if(age > 18){
	println "성인"
}else {
	println "청소년"
}
println "-----------------------"

//조건연산자
println age >  18? "성인":"청소년"
println "-----------------------"



//switch문
//숫자, 문자열, boolean, 객체도 가능
x = "홍길동"
result = ""

switch(x){
	case "aaa":
		println "aaa이다"
		break
	case "123":
		println "123은이다"
		break
	case [1, 20, "홍길동", true, 3.14]:
		println "대박 이것도 되다닝"
		break
}

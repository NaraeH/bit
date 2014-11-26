package groovy01

//변수선언
//1) dynamic type binding
def a =20 //값을 할당할 때 타입을 결정한다.
a = "오호라"
b = "졸립당"  //def 생략가능

println a
println b
println "-----------------------------"

//2) static type binding
// => 변수를 선언할 때 타입을 지정 변경불가!
int a2;
char b2;

//a2 = "okok"; //error: a2는 int로 선언했으므로 문자열 넣는 것 불가능
//a2 = "20"
a2 = 20
b2 = 'A'

println a2
println b2
println "-----------------------------"

//문자열 자동 형변환
String a3;
a3 = "ohora"
println a3

a3 = 200 //숫자를 문자열로 자동 형변환 한다.
println a3
println "-----------------------------"

//자바객체사용
java.util.Date today = new java.util.Date();
println today

//java.util 패키지는 내부에서 자동으로 import 했기 때문에 굳이 패키지 명을 지정하지 않아도 된다.
Date today2 = new Date();
println today2
println "-----------------------------"


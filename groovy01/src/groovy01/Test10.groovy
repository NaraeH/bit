package groovy01

//문자열 다루기
println "BIT's lecture"
println 'BIT"s lecture'
println 'BIT\'s lecture'
println (/BIT's "ok" lecture/) //괄호를 친다면 쌍따옴표 작은따옴표 관계 없이 다 넣을 수 없음

//multi line 처리
println """ 오호라....
정말이네...
헉...
"""

//formating 문자열 다루기 => Gstring
name = "홍길동"
println "안녕하세요. $name 님 반갑습니다."   //$다음의 문자가 갖고있는 변수값 출력
println "안녕하세요. \$name 님 반갑습니다."
println "2 + 3 = ${println '헐....'; 2 + 3}입니다."       //문자열 중간에 closure 넣기
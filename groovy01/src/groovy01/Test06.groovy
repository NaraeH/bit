package groovy01

//List 다루기
scoreList = ["홍길동", 100, 90, 80]  //ArrayList 객체 생성
println scoreList[2]
println scoreList[0]
println scoreList.get(0)
println scoreList.size()
println "-----------------------"

//new ArrayList(); => 빈 객체 생성
emptyList = []
println emptyList.size()
println "-----------------------"

//Map사용
scoreMap = ["name":"홍길동",
		 "kor":100,
		 "eng": 100,
		 "math":90]

println scoreMap["name"]
println scoreMap.name
println scoreMap.getClass()

scoreMap["kor"] = 50
scoreMap.eng = 60

println scoreMap.name
println scoreMap.kor
println scoreMap.eng
println scoreMap.math

emptyMap = [:]
println emptyList.getClass()
println emptyMap.getClass()
println "-----------------------"





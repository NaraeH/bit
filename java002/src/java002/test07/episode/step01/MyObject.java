package java002.test07.episode.step01;

//오류 발생! 
//default 값이 설정되지 않은 속성은 필수 입력 항목이다!
//두개 이상의 속성을 설정할 때는 value이름을 생략 할 수 없다.
@MyAnnotation(
		value="okok",
		name = "홍길동",
		//값이 여러개 일때는 반드시 {}를 써야한다.
		//language = { "korean", "english" },
		
		//배열에 값을 하나만 넣을 때는 중괄호{}를 생략할 수 있다.
		language = "korean",
		age = 30)
public class MyObject {

}

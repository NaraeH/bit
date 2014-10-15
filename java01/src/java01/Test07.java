/*<변수/메서드(함수)/클래스의 이름>
 -알파벳, 숫자, _, $ , 문자(한, 중, 일 국가 문자 다 가능)
  * 변수의 첫 문자는 반드시 숫자가 올 수 없다.
  * 가능한 $는 변수이름으로 사용하지 말것(보통 $는 자동화 도구에서 값을 대치하는 특수기호로 많이 사용 ex)jQuery)
  * 패키지 멤버 클래스는 영어 외에 문자를 사용하지 말아라.(운영체제에서 인지X)
  * 내부 클래스는 한글 사용가능 but 영어를 쓰는 것이 현명
 -대소문자 구분
 -변수 이름 짓는 규칙: camel표기법 ex) firstName, createDate
 -널리 알려진 약어의 경우는 변수명으로 사용해도 되지만, 가능한 약어 사용을 자제하라 => source code의 가독성을 위해
 -변수 or 메서드: 소문자로 시작 / 클래스: 대문자로 시작
 -메서드명은 동사로 시작, 일을 시키는 식으로 이름을 지어라!
 
  */
package java01;

public class Test07 {

	public static void main(String[] args) {
		byte 나이;
		byte abc;
		byte ABC;
		byte _;
		byte $;
		//byte 20;  //error => 숫자로 변수이름 시작 X
		byte age20;
		

	}

}

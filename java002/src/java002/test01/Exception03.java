/*
<예외 발생 시 던질 수 있는 클래스 : java.lang.Throwable>
1. 서브 클래스: error, Exception
- 시스템 관련 예외는 Error 계열의 객체에 담아서 던진다.
    => 개발자가 처리할 수 없는 예외 상황
- 애플리케이션 관련 예외는 Exception 계열의 객체에 담아서 던진다.
    => 개발자가 처리 할 수 있는 예외 상황
    
2. 왜 복잡하게 예외 정보를 담는 클래스가 많은가?
  => 예외 상황을 좀 더 쉽게 구분하기 위해 
  => 예외 처리를 상황에 따라 조정하기 쉽도록
  
3. 개발 할 때 보통 Exception 계열의 예외를 발생시킨다.
 */
package java002.test01;

public class Exception03 {

	//여러 종류의 에러를 던지기
	public static int compute(int a, int b, String op)
			throws ArithmeticException, Exception {
		if (op == null) {
			throw new Exception("연산자를 지정하세요");
		} else {
			switch (op) {

			case "+":
				return a + b;
			case "-":
				return a - b;
			case "*":
				return a * b;
			case "/":
				if (b == 0)
					throw new ArithmeticException("0으로 나눌 수 없습니다");
				return a / b;
			default:
				throw new Exception("지원하지 않는 연산자 입니다");

			}
		}
	}

	//여러 종류의 에러 처리하기
	//catch 순서에 따라 써줘야 함: ArithmeticException -> Exception
	// => 자식 예외부터 처리하라!
	public static void main(String[] args) {
		try {
			int result = compute(10, 20, ""); 
			System.out.println("결과: " + result);
		} catch (ArithmeticException ex) {
			System.out.println("연산오류");
			System.out.println(ex.getMessage());
		} catch (Exception ex) {
			System.out.println("실행오류");
			System.out.println(ex.getMessage());
		}

	}

}

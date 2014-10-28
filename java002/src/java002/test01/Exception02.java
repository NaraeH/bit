/*
 < 자바의 예외처리 >
 1. 예외처리
 1)예외 상황이 발생하면, 예외 정보를 특별한 객체에 담아서 호출자에게 던진다.
     문법: throw new Throwable("예외 내용");
 2)메서드 선언부에 어떤 예외를 던지는지 지정한다. 
     헤더 문법: void 메서드명 () throws Throwable {...}
     예외를 던질 수 있는 메서드를 호출하는 쪽에서는 try... catch를 사용하여 예외 처리 코드를 준비한다.
       
 2. 효과 
 1) 예외 정보를 좀 더 상세하게 호출자에게 전달 할 수 있다.
 2) 작업 코드와 예외 처리 코드를 분리함으로써 코드 가독성을 높일 수 있다.
 3) 호출자에게 예외 처리를 강제할 수 있다. => 예외 처리 안하면 메서드 호출하지 말아라!
 */
package java002.test01;

public class Exception02 {

	public static float divide(float a, float b) 
			throws Throwable{//선언부는 throws, throws의 다음 부분에서 예외가 발생할 수 있으니 보거라
		if(b == 0){// 어떤지점에 에러가 발생했는지 알 수 있음
			throw new Throwable("0으로 나누면 안돼요");
		}
		return a/b;
	}

	public static void main(String[] args) {
		try{ //에외가 발생하면 실행을 즉시 멈추고 catch로 이동
			//예외를 발생하는 코드들: 위험한 부분들
			float result = divide(10, 0);
			System.out.println("결과는 " + result + " 입니다.");
		}catch(Throwable ex){
			//예외가 발생했을 때 수행하는 코드들
			System.out.println(ex.getMessage()); //getMessage(): Throwable instance의 디테일 메시지 문자열
		}

	}

}

// 역할: 계산기 역할을 수행한다.
package java01.test30;

public class Calculator {
	
	//인스턴스 변수 선언 => static 제거
	//인스턴스 변수는 클래스 로딩할 때 준비되지 않는다.
	//별도의 명령을 내려야만 준비한다.
	//명령 내리는 법 => new Calculator();
	// =>해석: JVM 듣거라 Calculator 클래스의 선언 된 instance 변수를 heap 메모리에 준비하라. 
	//        그리고 그 시작주소를 리턴하라.
	private int result;
	
	//인스턴스 변수를 사용하려면 그 인스턴스의 시작주소를 알아야한다. => 어떤 인스턴스의 result인지 알 수 없음
	//따라서, 함수 메소드의 파라미터에 인스턴스 주소를 넘겨준다.(Calculator that)
	static int getResult(Calculator that){
		return that.result;
	}
	
	static void plus(Calculator that, int value){
		that.result += value;
	}
	
	static void minus(Calculator that, int value){
		that.result -= value;
	}
	
	static void multiple(Calculator that, int value){
		that.result *= value;
	}
	
	static void divide(Calculator that, int value){
		that.result /= value;
	}

}

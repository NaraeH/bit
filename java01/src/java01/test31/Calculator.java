// 역할: 계산기 역할을 수행한다.
package java01.test31;

public class Calculator {

	private int result;

	int getResult() {
		return this.result;
	}

	void plus(int value) {
		/*
		 * Calculator this this 변수는 내부에 숨겨진 변수이다. 메서드를 호출할 때 사용한 인스턴스의 주소를 보관한다.
		 * 먄약, 인스턴스 변수를 사용할 때 this를 붙이지 않으면, 컴파일러가 자동으로 있다고 가정하고 컴파일한다.
		 */
		this.result += value;
	}

	void minus(int value) {
		this.result -= value;
	}

	void multiple(int value) {
		this.result *= value;
	}

	void divide(int value) {
		this.result /= value;
	}

}

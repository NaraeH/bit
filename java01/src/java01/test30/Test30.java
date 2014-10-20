/*<인스턴스 변수>
 - 값을 개별적으로 관리할 필요가 있을 때 사용
  */
package java01.test30;
//단계7: result 변수를 개별적으로 유지 => 인스턴스 변수
public class Test30 {

	public static void main(String[] args) {
		//식 2개를 동시에
		// 10 + 2 * 7 - 4 / 2 = ?
		//20 * 3 + 76 -5 =?
		
		//Calculator 클래스의 명령에 따라 준비된 메모리를  => 인스턴스
		//그 인스턴스 메모리의 주소를 저장하는 변수를 => 레퍼런스
		//method는 절대 heap 영역에 올라가지 않는다.
		Calculator c1 = new Calculator();
		Calculator c2 = new Calculator();
		
		Calculator.plus(c1, 10);
		Calculator.plus(c1, 2);
		Calculator.multiple(c1, 7);
		Calculator.minus(c1, 4);
		Calculator.divide(c1, 2);
		
		Calculator.plus(c2, 20);
		Calculator.multiple(c2, 3);
		Calculator.plus(c2, 76);
		Calculator.minus(c2, 5);

		
		System.out.println("c1 결과: " + Calculator.getResult(c1));
		System.out.println("c2 결과: " + Calculator.getResult(c2));
	}

}

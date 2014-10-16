/*<인스턴스 변수>
 - 값을 개별적으로 관리할 필요가 있을 때 사용
  */
package java01.test30;
//단계7: result 변수를 개별적으로 유지 => 인스턴스 변수
public class Test30 {

	public static void main(String[] args) {
		//식 2개를 동시에
		// 10 + 2 * 7 - 4 / 2 = ?
		//20 * 3 + 76 -5
		
		Calculator cal1 = new Calculator();
		Calculator cal2 = new Calculator();
		
		cal1.plus(, 10);
		cal1.plus(2);
		cal1.multiple(7);
		cal1.minus(4);
		cal1.divide(2);
		
		cal2.plus(20);

		
		System.out.println("결과: " + Calculator.getResult());
	}

}

/*
 <Collection API>
  - 목록 데이터를 다루는 클래스 라이브러리
  - 모든 Collection 관련 클래스들은 Collection 규칙ㄱ에 따라서 클래스를 만든다.
  
  - 종류
  1) List : 데이터 중복 허용
  2) Set : 데이터 중복 불가
  3) Map : key-value 쌍으로 데이터 관리
 */
package java01.test52;

import java.util.ArrayList;
import java.util.Iterator;

/*
 ArrayList test
 */
public class TestCollection01 {

	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		list.add("Hello");
		list.add("Hello");
		list.add(new String("Hello"));
		list.add(10);   //list.add(new Integer(10)) => autoboxting add(값이 아닌 주소원함) => 자동으로 주소를 반환
		
		//값 꺼내기 방법1
		System.out.println("---------값 꺼내기 방법1: for문----------");
		for(int i = 0; i < list.size(); i++){
			System.out.println(list.get(i));
		}
		
		//값 꺼내기 방법2
		// - for (값을 담을 변수 : 배열 또는 Collection객체) { ... }
		System.out.println("------값 꺼내기 방법2: for each문-------");
		for(Object value: list){
			System.out.println(value);
		}
		
		//값 꺼내기 방법3
		//Iterator: 값을 꺼낼 때 일관 된 방법을 사용 => 호출하는 메서드가 같다.
		//Iterator: 값을 꺼내는 방법(알고리즘)을 객체화 시킨 것이다.
		//객체화: 별도의 독립된 명령어 블럭으로 분리함으로써 관리한기 쉽다.
		//      다른 알고리즘으로 대체하기 쉽다.
		System.out.println("------값 꺼내기 방법3: Iterator-------");
		Iterator iterator = list.iterator(); //list.iterator(): list의 element를 iterator형식으로 바꾸기
		
		//hasNext(): element가 있냐 없냐 확인 return - boolean
		while(iterator.hasNext()){
			System.out.println(iterator.next()); //next(): 다음 element
		}
	}

}

/*
 <제네릭(Generic)사용>
 - 클래스에서 다룰 데이터의 타입을 지정하는 방법
 - 지정된 타입의 데이터 외에는 값에 대해서는 오류 발생
 - 사용이유: 다른 타입의 데이터 입력 시 컴파일 오류! => 잘못된 사용을 미연에 방지할 수 있다.
               이미 목록에 저장된 데이터가 어떤 타입인지 컴파일러가 알기 때문에 값을 꺼낼 때 type casting할 필요가 없다.
 */
package java01.test52;

import java.util.ArrayList;
import java.util.Iterator;

/*
 ArrayList test
 */
public class TestCollection02 {

	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		list.add("Hello");
		list.add("Hello");
		list.add(new String("Hello"));
		list.add(10);   //list.add(new Integer(10)) => autoboxting add(값이 아닌 주소원함) => 자동으로 주소를 반환
		
		//String외에는 값을 넣을 수 없다.
		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("Hello");
		list2.add(new String("Hello"));
		//error: The method add(String) in the type ArrayList<String> is not applicable for the arguments (Integer)
		//String만 담을 수 있는데 왜 Integer를 넣었니? => 오류
		//list2.add(new Integer(10)); 
		
		/*<local inner class>
		 특정 메서드에서 사용할 클래스라면 그 메서드에 선언하라.
		 */
		class Score {
			String name;
			int kor;
			int eng;
			int math;

			public Score(String n, int k, int e, int m) {
				this.name = n;
				this.kor = k;
				this.eng = e;
				this.math = m;

			}
		}
		
		ArrayList list3 = new ArrayList();
		list3.add(new Score("홍길동", 100, 100, 100));
		list3.add(new Score("임꺽정", 90, 90, 100));
		
		//질문 : 이 목록에서 Score객체를 꺼내 이름을 출력하라
		
		System.out.println("----------Generic Iterator-----------");
		Iterator<Score> a1 = list3.iterator();
		while(a1.hasNext()){
			Score score = a1.next();
			System.out.println(score.name);
		}
		
		System.out.println("---------for문: type casting 필요-------");
		Score score = null;
		for(int i = 0; i<list3.size(); i++){
			score = (Score)list3.get(i);
			System.out.println(score.name);
		}
		
		System.out.println("----------Generic ArrayList-----------");
		ArrayList<Score> list4 = new ArrayList<Score>();
		list4.add(new Score("홍길동", 100, 100, 100));
		list4.add(new Score("임꺽정", 90, 90, 100));
		//list4.add("Hello");  //error: 다른 타입의 데이터 입력 시 컴파일 오류! => 잘못된 사용을 미연에 방지할 수 있다.
		
		for(int i = 0; i<list4.size(); i++){
			score = (Score)list4.get(i);
			System.out.println(score.name);
		}
		
		

	}

}

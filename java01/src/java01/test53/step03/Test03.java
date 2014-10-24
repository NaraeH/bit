/*
 3단계: 역순으로 데이터를 꺼내주는 Iterator 만들기
 => ReverseIterator
 */
package java01.test53.step03;

public class Test03 {

	public static void main(String[] args) {
		
		System.out.println("-------Iterator--------");
		Iterator iterator1 = new Iterator(args);
		while (iterator1.hasNext()) {
			System.out.println(iterator1.next());
		}

		System.out.println("-------ReverseIterator--------");
		ReverseIterator iterator2 = new ReverseIterator(args);
		/*
		 Iterator가 바뀌더라도 다음 코드는 변경할 필욯가 없다.
		 이것이 Iterator 설계 특징이다.
		 즉, 꺼내는 방식에 상관없이 사용하는 쪽에서는 일관된 메서드를 사용할 수 있다는 점이다.
		 
		 이렇게 꺼내는 방법을 별도의 객체로 분리함으로써 실행할때 유연해 진다.
		  */
		while (iterator2.hasNext()) {
			System.out.println(iterator2.next());
		}
		
		System.out.println("-------EvenIterator--------");
		EvenIterator iterator3 = new EvenIterator(args);
		while (iterator3.hasNext()) {
			System.out.println(iterator3.next());
		}

	}

}

/*<garbage와 garbage collection>
 - Garbage: 참조하는 변수가 없는 메모리
 - Garbage Collector: 가비지의 메모리를 해제하는 객체
 
 <JVM이 메모리를 관리하는 방법>
 운영체제로 부터 메모리를 할당받았을 경우, 
*/
package java01;

public class Test14 {

	public static void main(String[] args) {
		/*int[] p1 = new int[5]; //ex) 배열의 주소가 100번지라고 하자.
		int[] p2 = new int[3]; //ex) 배열의 주소가 200번지라고 하자.
		int[] p3 = p1;
		
		p3[2] = 100;
		System.out.println(p1[2]);
		
		p2 = p1; //p2(200번지)의 주소값에 p1(100번지)이 가지고 있는 주소값을 저장해라
		//p2는 200번지의 주소를 잃어 버렸기 때문에 p2의 주소영역은 garbage가 된다. 
		
		
		//200번지 배열의 주소는 누가 갖고 있는가?
		//갖고 있는 변수가 없다! => 200번지 배열은 쓰레기(garbage)가 되었다.
		//가비지는 언제 청소 하는가? (사용될 수  없는 메모리 해제 시점 )
		//1) idle time (한가한 시간에)
		//2) 메모리가 부족할 때 *주의점: 잠깐 실행하는 경우는 garbage collector가 동작하지 않는다.
		//3) garbage collector가 관리하는 메모리 영역은 heap이다.
		//garbage가 생겼다고 가비지 컬렉터가 즉시 동작하는 것이 아니다!!
		//실무에서는 가비지 생성을 최소화 하도록 프로그래밍을 한다.(메모리 낭비를 줄이기 위해서) => 디자인 패턴에 따라 달라짐
*/
		int i, j, k;
		
		i=10;
		j=20;
		k=30;
		
		char ch1, ch2, ch3;
		ch1 = '가';
		ch2 = '나';
		ch3 = '다';
		
		System.out.println(i);
		System.out.println(j);
		System.out.println(k);
		
		System.out.println(ch1);
		System.out.println(ch2);
		System.out.println(ch3);
	}

}

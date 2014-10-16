/*<import>
  - 다른 패키지에 있는 클래스를 사용하려면 반드시 패키지 이름을 지정해야 한다.
  ex) java.util.ArrayList
  -그러나 import문을 사용하여 미리 클래스의 정보를 컴파일러에게 알려 줄 수 있다.
  ex) import java.util.Array;
  -또는 클래스가 소속되어 있는 패키지 이름을 알려줄 수 있다.
  ex) import java.util.*;
  -가능한 클래스 이름까지 알려 주는게 소스 코드 해석에 도움이 된다, 컴파일 속도도 더 빠르다
 */
package java01;

//import 문은 컴파일러가 참조하는 명령이다. => 컴파일후 제거된다
//따라서, 정확히 참조하는 패키지를 구체적으로 적어주는 것이 좋다. 오히려 *을 쓰면 그안에서 뒤져야 하기때문에 오래 걸린다.
import java.util.ArrayList;

public class Test26 {

	public static void main(String[] args) {

		int[] scores = new int[] { 100, 90, 80 };

		// 값을 순차적으로 꺼낸다.
		for (int value : scores) {
			System.out.println(value);
		}

		ArrayList list = new ArrayList();

		list.add("홍길동");
		list.add(100);
		list.add(90);
		list.add(80);

		// Collection 객체에 저장된 값을 순차적으로 하나씩 꺼낸다.
		for (Object value : list) {
			System.out.println(value);
		}

	}

}

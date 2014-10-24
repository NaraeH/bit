/*
 4단계: Iterator 변경을 자유롭게 하기
       Test04가 사용할 Iterator를 대체하기 쉽게 하자.
- 해결책 
   1) Iterator 클래스 이름을 외부에서 받자
   2) 외부에서 받은 클래스로 Iterator 객체를 생성하자
   3) 그 객체를 사용하여 값을 꺼내자
   
   * 어떤 값을 외부에서 받는방법 (Run버튼 아래아래의 Run Configuration)
   * 1) 프로그램 파라미터(아규먼트)사용
   * 2) JVM 파라미터(아규먼트) ex) java -D파라미터명=값 -D파라미터명=값 -D파라미터명=값 ... 클래스명 파라미터 파라미터 파라미터 ...
   *  => main01참고
 */
package java01.test53.step04;

import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Properties;

public class Test04 {
	
	public static void main(String[] args) throws Exception{
		 //getProperty("iterator")의 iterator는 -D 다음에 오는 값
		String iteratorClassName = System.getProperty("iterator");
		
		//클래스 이름(패키지명 포함)으로 객체 생성하기
		//1) 클래스를 로딩하라.
		Class clazz = Class.forName(iteratorClassName); //class는 예약어 이므로 clazz로!

		// 2) Class 객체를 사용하여 인스턴스 생성
		Iterator iterator = (Iterator)clazz.newInstance(); 
		//4) Iterator로 casting 해주었으므로  -D부분을 수정한다고 해서 바로 코드가 실행될 수 없다. 
		// Iterator class와 ReverseIterator와 EvenIterator는 병렬관계 트럭에 소나타는 넣을 수 없다.
		
		//3) Iterator를 사용하기 전에 필요한 값을 설정한다.
		iterator.setList(args);
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
		// 4) 위를 보아라! => 문제점발생 : 안타깝게도 다른 Iterator는 사용할 수 없다. => step05에서 수정
		//Iterator의 로딩된 메서드명 출력하기
		Method[] methods = clazz.getMethods();
		for(Method method:methods){
			System.out.println(method.getName());
		}
	}

	//JVM property값 꺼내기 예제
	public static void main01(String[] args) {
		//-D 옵션으로 전달된 값을 꺼낼 때,
		// System.getProperty(파라미터명) 사용하라!
		// 파라미터명 다음의 값을 꺼내라
		System.out.println(System.getProperty("iterator"));
		
		System.out.println("--다꺼내보자--");
		Properties props = System.getProperties();
		Enumeration<?> keyList = props.keys();
		String key = null;
		
		while(keyList.hasMoreElements()){
			key = (String)keyList.nextElement();
			System.out.println(key + " =>  "  + props.getProperty(key)); //-D 옵션으로 전달된 값을 담고 있는 객체를 전부 꺼낸다.
			
		}
		
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

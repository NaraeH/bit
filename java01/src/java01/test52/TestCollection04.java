/*
 < HashMap >
 - "key: value"의 쌍으로 데이터를 관리한다.
 - key로 사용할 클래스는 hashCode(), equals()를 재정의 해야 한다. 
   => instance가 다르더라도 값이 같은 같은 hashCode를 리턴하도록 구현해야한다.
   =>  그리고 값이 같으면, equals()가 true를 리턴한다.
 - 기존에 자바에서 제공하는 클래스들 중에서 String과 랩퍼 크래스들은 
   hashCode()와 equals()를 재정의 했기 때문에 key로 사용할 수 있다.
 
 */
package java01.test52;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class TestCollection04 {
/*
 member inner  class
 - 인스턴스 메서드와 같은 member inner class 만이 이클래스를 사용할 수 있다.
 * 참고: class member란? 현재의 public class에서 클래스를 구성하고 있는 원소 ,변수 , method ,inner class
 * 
- 인스턴스 블록: method, member inner class
- 클래스 블록: static으로 선언된 블럭
 */
	
	/*
	 < top level inner class>
	 - member inner 클래스에 static을 붙인 클래스
	 - class method나 static 블럭에서 사용할 수 있다.
	*/
	static class Score {
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
	
	/*
	static block에서는 오로지 클래스 변수 및 클래스 메서드만 사용할 수 있다.
	이유는 무엇일까?
	 class A{ 
	    int value;
	    void print(){
	        system.out.println(this.value);
	    }
	 }
	 
	 static void test(){
	    print();  //호출 가능 하다면 어떤 일이 벌어질까? => error: NullPointerException
	              //A.print()error: A는 static이 아니므로 현재상태로는 메모리에 올라가 있지 않다.
	 }
	  */
	public static void main(String[] args) {
		HashMap<String, Score> map= new HashMap<String, Score>();
		map.put("1111",new Score("홍길동", 100, 100, 100));
		
		// key는 중복될 수 없다. 따라서 임꺽정 데이터는 기존 제이터를 덮어 버린다
		// 즉, 홍길동 데이터 날아감.
		map.put("1111", new Score("임꺽정", 100, 100, 100));
		map.put("2222", new Score("유관순", 100, 100, 100));

		System.out.println(map.get("1111").name);
		System.out.println(map.get("2222").name);
		
		//질문. 키를 모른다고 가정하고 map에 저장된 값을 모두 출력하시오!
		//방법1
		System.out.println("-------entrySet-------");
		Set<Entry<String, Score>> kySet = map.entrySet();
		
		for(Entry<String, Score> e: kySet){
			System.out.println(e.getKey() + ": " + e.getValue().name);
		}
		
		//방법2: 먼저 key 목록을 얻은 다음에, 그 키를 사용하여 값을 꺼내기
		System.out.println("-------keySet-------");
		Set<String> kySet2 = map.keySet();

		for(String key:kySet2){
			System.out.println(key + ": " + map.get(key).name);
		}
		
		//방법3: key 값을 알 피룡가 없다면, 값만 꺼내기
		System.out.println("-------values-------");
		Collection<Score> kySet3 = map.values();
		
		for(Score s:kySet3){
			System.out.println(s.name);
		}

		
	}

}

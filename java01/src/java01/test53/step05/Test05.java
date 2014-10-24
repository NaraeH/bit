/*
 5단계: Iterator 뿐만 아니라 EvenIterator, ReverIterator도 사용할 수 있게 하기
 - 해결책: 
   이들 클래스의 공통점을 뽑아 super class를 정의하라 => Generalization 수행
   Iterator(<= Super class)
      ---------> GeneralIterator (<= sub class)
      ---------> EvenIterator    (<= sub class)
      ---------> ReverseIterator (<= sub class)
 */
package java01.test53.step05;

public class Test05 {
	
	public static void main(String[] args) throws Exception{
		//System.getProperty("환경변수명"): -D 옵션으로 넘어온 값 및 JVM 환경 변수 값을 꺼낸다.
		String iteratorClassName = System.getProperty("iterator");
		
		Class clazz = Class.forName(iteratorClassName); //class는 예약어 이므로 clazz로!
		//return 되는 주소가 명확하지 않기 떄문에 return 주소가 Object Type의 주소변수로 인식
		//따라서, 프로그래머의 형변화(Type Casting)작업이 필요
		Iterator iterator = (Iterator)clazz.newInstance(); 
		iterator.setList(args);
		
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
	}

}

/*
 <에노테이션 사용>
 => 컴파일러나 JVM에게 전달하는 주석
 
 1. 에소테이션 정의
   - MyAnnotation 생성
 2. 에노테이션 적용
   - MyObject 생성 및 에노테이션 적용
 3. 에너테이션 추출
 */
package java002.test07.episode.step01;

public class AnnoTest01 {
	
	public static void main(String[] args) {
		Class clazz = MyObject.class;
		
		MyAnnotation myAnno = (MyAnnotation)clazz.getAnnotation(MyAnnotation.class);
		System.out.println(myAnno.value());
		System.out.println(myAnno.age());
		System.out.println(myAnno.country());
		System.out.println(myAnno.name());
		
		String[] langs = myAnno.language();
		for(String lang:langs){
			System.out.println("language: " + lang);
		}
		
	}
}

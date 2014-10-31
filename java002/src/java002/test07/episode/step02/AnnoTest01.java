/*
 <에노테이션 사용>
 => 컴파일러나 JVM에게 전달하는 주석
 
 1. 에소테이션 정의
   - MyAnnotation 생성
 2. 에노테이션 적용
   - MyObject 생성 및 에놑이션 적용
 3. 에너테이션 추출
 */
package java002.test07.episode.step02;

import java.lang.annotation.Annotation;


public class AnnoTest01 {
	
	//Class 객체를 얻는 또 다른 방법
	//JVM은 클래스를 로딩할 때, "class"라는 이름의 static 변수를 자동으로 추가한다.
	//이 변수에는 "Class"라는 객체가 들어있다.
	//클래스 이름으로 접근 가능하다.
	//클래스 로딩방법1: 클래스에 대한 새로운 인스턴스 생성뒤 얻기
	//클래스 로딩방법2: class.forName("java02.test07.episode.MyObject")
	//클래스 로딩방법3: 클래스의 static 변수를 통해 얻기
	//2번째 방법이 가장좋다!!!!! => 1,3 번은 하드코딩이 된다. => 2번은 forName()안에 String으로 받아서 변경이 쉽다.
	
	//Class관리자(class)를 통해 특정 에노테이션 객체만 추출하기
	public static void main(String[] args) {
		Class clazz = MyObject.class;
		
		//.class는 확장자가 아니다!!!!!!!!!!!!!!!!!!!!! => 변수이다. JVM에서 자동으로 생성된 static 변수(내가 만드는게 아님)
		MyAnnotation myAnno = (MyAnnotation)clazz.getAnnotation(MyAnnotation.class);
		System.out.println(myAnno);
		
	}
	
	//클래스 로딩방법3
	public static void main03(String[] args) {
		Class clazz = MyObject.class;   //class는 static 객체 ctrl+space해서 보면 앞쪽에 s붙은거 확인가능
		
		Annotation[] annos = clazz.getAnnotations();
		
		//에노테이션 모든 목록 보기
		for(Annotation anno:annos){
			System.out.println(anno);
		}
		
		//에노테이션의 값 받기
		MyAnnotation myAnno = (MyAnnotation)annos[0];
		System.out.println(myAnno.value());


	}
	
	//클래스 로딩방법1
	public static void main01(String[] args) {
		MyObject obj = new MyObject();
		Class clazz = obj.getClass();
		
		//모든 에노테이션은 java.lang.annotation.Annotation 인터페이스를 자동으로 구현한다.
		//따라서, 우리가 만든 MyAnnotation도 이 인터페이스를 구현하였다.
		Annotation[] annos = clazz.getAnnotations();
		
		//에노테이션 모든 목록 보기
		for(Annotation anno:annos){
			System.out.println(anno);
		}
		
		//에노테이션의 값 받기
		MyAnnotation myAnno = (MyAnnotation)annos[0];
		System.out.println(myAnno.value());

	}

}

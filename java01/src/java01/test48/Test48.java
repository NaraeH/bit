package java01.test48;

import java01.test48.Student;

public class Test48 {
	
	//hashCode()테스트
	public static void main01(String[] args) {
		Student s1 = new Student("홍길동 jr.", 20);
		Student s2 = new Student("임꺽정 jr.", 10);
		
		//instance 별로 hash 갑이 다른다.
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
	}
	
	public static void main(String[] args) {
		Student s1 = new Student("홍길동", 90);
		Student s2 = new Student("임꺽정", 20);
		
		//Object s3 = new Student("홍길동", 90);
		//Object s4 = new Student("임꺽정", 20);
		
		System.out.println("11:" + s1.toString());  // Override되기전 toString(): 인스턴스 정보 출력, 기본리턴 값: 클래스명@hashCode(4바이트 정 수값)
		System.out.println("22:" + s2.toString());  // Override되기전 toString(): 인스턴스 정보 출력, 기본리턴 값: 클래스명@hashCode(4바이트 정 수값)
	
		//println()은 인스턴스의 toString()을 호출하여 그 리턴 값을 출력한다.
		System.out.println("33:" + s1);
		System.out.println("44:" + s2);
		
		//System.out.println(s3);
		//System.out.println(s4);
	
	}

}

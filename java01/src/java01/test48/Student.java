/*
 < Object 클래스 >
 - 자바 최상위 클래스
 - super class를 지정하지 않으면 자바 컴파일러는 자동으로 Object를 상속받게 한다. => ex) Student extends Object{}
 
 -Object의 주요 메서드
 1) toString(): 인스턴스 정보 출력, 기본리턴 값: 클래스명@hashCode(4바이트 정 수값)
    *hash: 디지털 지문
 2) 
 
 */
package java01.test48;

public class Student {
	String name;
	int age;
	
	public Student(String name, int age){
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}
	

}

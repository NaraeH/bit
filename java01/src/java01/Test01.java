/*
  < 규칙  Test01.java~>
 1. java 코드는 반드시 class 안에 작성해야 한다.
 2. eclipse는 소스파일을 저장하는 즉시 자동으로 컴파일 한다.
 3, 컴파일 된 바이트코드는 프로젝트 폴더/bin 디렉토리에 놓인다.
 4. 바이트코드 파일이름은 class 이름이다. ex) Test01.class
 5. public으로 공개된 클래스는 자바 소스 파일도 반드시 클래스 이름이여야 한다. => 다르면 error!
 6. 파일명이 public이 아니면 파일명이 클래스이름과 달라도 error가 나지 않는다.
 */
package java01;

//public TestOk{ //5
//class TestOk{ //6
public class Test01 {
	public static void main(String[] args) {
		System.out.println("Hello, World!");
	}

}

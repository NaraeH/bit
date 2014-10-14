/*
	< main()함수 >
JVM이 클래스를 실행할 때 홀출 하는 함수
main() => start entry

1. 규칙

2. 실행
java -classpath [클래스파일이 있는 경로] [클래스명]
만약, 클래스가 특정 패키지에 소속된 멤버라면, java -classpath [패키지가 있는 경로] [패키지명.클래스명]
ex) java -classpath ./bin java01.Test01

*-classpath대신 -cp도 가능
*[패키지명.클래스명] : fully Qualified Name(Qname)
*
  */
package java01;

//하나의 소스 파일에는 반드시 public 클래스가 하나만 올 수 있다.
public class Test03 {
	public static void main(String[] args){
		//console창으로 출력
		System.out.print("Hello, World!\n");
	}
}





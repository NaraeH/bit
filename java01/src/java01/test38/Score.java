/*
 <생성자>
 - 인스턴스가 생성된 후 자동으로 호출하는 매서드
 - 인스턴스를 사용하기 전에 유효한 값으로 초기화 하는 일을 한다.
 - 제 역할을 할 수 있도록 유효한 값으로 설정한다.
 - 문법
    1) 리턴 타입이 없어야한다. => 값을 리턴할 수 없다.
    2) 메서드 이름이 클래스 이름과 같아야 한다.
    3) 파라미터가 없는 생성자를 기본 생성자(default)라고 부른다.
    4) 생성자를 만들지 않으면 컴파일러가 자동으로 기본 생성자를 만들어 준다.
 * */
package java01.test38;

public class Score {

	String name; 
	int kor;
	int eng;
	int math;
	int sum;
	float average;
	
	//기본 생성자 : 생성자를 정의하지 않으면 컴파일러가 다음과 같이 자동으로 추가한다.
	//public Score(){}
	
	//생성자 추가
	public Score(String name, int kor, int eng, int math){
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		
		compute();
	}
	public void compute(){
		this.sum = this.kor + this.eng + this.math;
		this.average = (float)this.sum/3;
	}

}

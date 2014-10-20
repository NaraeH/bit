/*
 <다형성 (polymorphism)overloading(오버로딩)>
 - 파라미터 개수나 파라미터의 타입이 다르더라도 결국 동일한 기능을 수행할 경우 메서드의 이름을 같게 하는 것
    메서드의 이름을 갖게 하는 것 => 프로그램의 일관성 유지
  -규칙:
  1) 메서드 이름은 같아야 한다.
  2) 파라미터 개수가 다르거나, 데이터 형이 달라야 한다.
  3) 리턴 타입이 다른 것은 의미없다=> 호출 할 때 메소드를 결정해야 하는데, 
                                          리턴 타입으로는 어떤 메소드를 호출할지 결정 할 수 없기 때문이다.
  */
package java01.test39;

public class Score {

	String name; 
	int kor;
	int eng;
	int math;
	int sum;
	float average;

	public Score(){
		this.name = "홍길동";
	}

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

/*
 * <Data processing class2 - ObjectOutputStream>
 * 인스턴스 출력하기
=> 인스턴스 값을 통채로 출력하기 ObjectOutputStream
=> 용어 정리
  - Serialize(Marshaling) : 바이트 배열로 만드는 것
  - Deserialize(Unmarshaling): 바이트 배열을 인스턴스를 만드는 것
  
 */
package java002.test02;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//인스턴스 값들을 바이트 배열로 만들려면 Serialize를 허용해야 한다.
//문법:java.io.serializable 인터페이스를 구현하면 된다.
//Serializable interface는 method가 없다.
//단지 바이트 배열로 출력할 수 있다는 표시용이다.
//조상 중에 하나라도 Serializale이라면 모든 자식 다 Serializable이다.
class Score implements Serializable {
	String name;
	int kor;
	int eng;
	int math;
	int sum;
	float avg;
	
	public Score(String name, int kor, int eng, int math){
		
		//FileOutputStream out = new FileOutputStream("test14.dat");
		
		this.name =name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.sum = kor + eng + math;
		this.avg = sum / 3.0f;
	}
}


public class Test14 {
	//@SuppressWarnings("serial")

	public static void main(String[] args) throws Exception{
		
/*		private static final long SerialVersion = 1;
		
		ObjectOutputStream out2 = new ObjectOutputStream(out); //도우미 클래스
		
		Score obj =new Score("홍길동", 100, 200, 50);
		
		//instance에 값을 byte 배열로 만들어서 출력한다. 단, 해당 클래스가 허락해야 한다.
		//선언을 해라
		out2.writeObject(obj);

		
		out2.close();
		out.close();*/
	}
}

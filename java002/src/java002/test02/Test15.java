/*

-DataOutoutStream에서 출력한 값을 읽기
Deserialize(Unmarshaling)
 */
package java002.test02;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Test15 {
	/*test14.java 소스에서 생성한 Score사용하기*/
	
	//instance의 값을 출력할 때 클래스 버전도 약간출력된다.
	//읽어 들이는 쪽에서 자신이 갖고잇는 클래스와 같은 버전인지 검사할 때 이값을 사용한다.
	public static void main(String[] args) throws Exception{
		FileInputStream in = new FileInputStream("test14.dat");
		ObjectInputStream in2 = new ObjectInputStream(in); //도우미 클래스
		
		Score obj = (Score)in2.readObject();
		
		System.out.println(obj.name);
		System.out.println(obj.kor);
		System.out.println(obj.eng);
		System.out.println(obj.math);
		System.out.println(obj.avg);

		in2.close();
		in.close();
	}
}

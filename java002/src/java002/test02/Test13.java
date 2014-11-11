/*
  <Test12에서 출력한 값을 읽기 >
-DataOutoutStream에서 출력한 값을 읽기

 */
package java002.test02;

import java.io.DataInputStream;
import java.io.FileInputStream;

public class Test13 {
	
	public static class Score {
		String name;
		int kor;
		int eng;
		int math;
		int sum;
		float avg;
		
		public Score(){}
		
		public Score(String name, int kor, int eng, int math){
			this.name =name;
			this.kor = kor;
			this.eng = eng;
			this.math = math;
			this.sum = kor + eng + math;
			this.avg = sum / 3.0f;
		}
	}
	
	public static void main(String[] args) throws Exception{
		FileInputStream in = new FileInputStream("test11_3.txt");
		DataInputStream in2 = new DataInputStream(in); //도우미 클래스
		
		Score obj1 = new Score();
		
		obj1.name = in2.readUTF();
		obj1.kor = in2.readInt();
		obj1.eng = in2.readInt();
		obj1.math = in2.readInt();
		obj1.avg = in2.readFloat();
		
		System.out.println(obj1.name);
		System.out.println(obj1.kor);
		System.out.println(obj1.eng);
		System.out.println(obj1.math);
		System.out.println(obj1.avg);

		in2.close();
		in.close();
	}
}

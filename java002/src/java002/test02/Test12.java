/*
  < 인스턴스 변수 값 출력하기 >


 */
package java002.test02;

import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class Test12 {
	
	public static class Score {
		String name;
		int kor;
		int eng;
		int math;
		int sum;
		float avg;
		
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
		FileOutputStream out = new FileOutputStream("test11_3.txt");
		DataOutputStream out2 = new DataOutputStream(out); //도우미 클래스
		
		Score obj = new Score("홍길동", 100, 90, 80);
		
		out2.writeUTF(obj.name);
		out2.writeInt(obj.kor);
		out2.writeInt(obj.eng);
		out2.writeInt(obj.math);
		out2.writeInt(obj.sum);
		out2.writeFloat(obj.avg);
		
		//연습: obj의 인스턴스 값 출력
		
		out2.close();
		out.close();
	}
}

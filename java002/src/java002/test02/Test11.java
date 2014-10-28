/*
  < 데이터 프로세싱 스트링 (Data processing stream class) >
  - 중간에서 데이터를 가공하는 역할
  - 자기 스스로 출력할 수 없다.(중간에서 가공하는 역할이므로)
  - 반드시 Data Sink Stream 클래스를 통해 출력한다.

 - DataOutputStream
   => 문자열이나 기본 타입을 데이터로 출력할 때,
         내부에서 바이트 배열로 만들어 다른 출력 스트림에게 넘기낟.

 */
package java002.test02;

import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class Test11 {
	
	public static void main(String[] args) throws Exception{
		FileOutputStream out = new FileOutputStream("test11_2.txt");
		DataOutputStream out2 = new DataOutputStream(out); //도우미 클래스
		
		int kor = 100;
		int math = 90;
		int money = 145630;
		
		out2.writeInt(kor);
		out2.writeInt(math);
		out2.writeInt(money);
		
		//닫을 때 거꾸로 닫는다.
		out2.close();
		out.close();
	}

	//
	public static void main01(String[] args) throws Exception{
		FileOutputStream out = new FileOutputStream("test11.txt");
		int kor = 100;
		int math = 90;
		int money = 145630;
		
		//Quiz: kor, math, money를 출력하라! => 데이터 손실없이
		out.write(kor >> 24);
		out.write(kor >> 16);
		out.write(kor >> 8);
		out.write(kor);
		
		out.write(math >> 24);
		out.write(math >> 16);
		out.write(math >> 8);
		out.write(math);
		
		out.write(money >> 24);
		out.write(money >> 16);
		out.write(money >> 8);
		out.write(money);
		
		
		
		
		char[] str = {'A', 'B', 'C', '가', '각', '간'};
		/*
		  < 바이너리 데이터의 읽기 >
		 - FileInputStream
		 - 텍스트 파일을 바이너리 스트림 객체를 사용하여 읽기
		 - read(): 1바이트씩 읽는다.
		 */
		for(char c:str){
			out.write(c); //무조건 byte로 출력
		}
		out.close();
	}

}

/*
  < 문자데이터 출력 >
 - FileWriter

 */
package java002.test02;

import java.io.FileWriter;

public class Test10 {

	public static void main(String[] args) throws Exception{
		FileWriter out = new FileWriter("test10.dat");
		
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

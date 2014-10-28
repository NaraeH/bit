/*
 <binary data 읽기>
 */
package java002.test02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Test01 {

	public static void main(String[] args){
		
		FileInputStream in = null;
		
		try {
			// 1. 입력 스트링 객체를 준비한다.
			// 현재 파일의 위치는 project directory이다.
			in = new FileInputStream("img1.jpg"); //argument를 불러와라
			int count = 0;
			int b = -1;
			
			//2. read()를 통해 1바이트 읽기
			//=> read()를 해서 받은 것을 b에다가 저장하고 더 이상 바이트 받아올 것이 없으면 -1
			//리턴 타입이 int라 해서 4바이트를 읽는 것은 아니다.
			while ((b = in.read()) != -1) {
				count++;
			}
			
			//3. File이나 Database, Socket 등의 자원은 사용한 다음 명시적으로 해제 해야 한다.
			//그러나 read() 중에 오류가 발생하면 close()를 호출도 못한다. 그래서 자원을 해제하는 명령은 finally block에 넣는다.
			
			System.out.println("파일크기는 " + count + "바이트 입니다.");
			
		} catch (FileNotFoundException e) {
			System.out.println("img1.jpg 파일을 찾을 수 없습니다.");
		} catch (IOException e){
			System.out.println("읽기오류");
		} finally {
			//close()하다가 예외 발생시 아무것도 하지 않는다.
			try { in.close();}catch (IOException e){}
		}
	}
}

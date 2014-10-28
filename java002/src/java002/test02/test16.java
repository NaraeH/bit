/*
 < buffer의 사용 >
 - 한 바이트 씩 데이터를 일는 것보다, 여러 바이트를 한꺼번에 읽는 것이 속도가 더 빠르다.
 - 이유: 데이터 읽기 속도 = Data Seek Time + Data Access Time
         ex) 퀀텀하드(퀀텀에서나온 하드) 
           Data Seek Time(데이터의 위치를 랒는 시간) : 4.2ms => 0.0042초
           Data Transfer Time(데이터 전송 시간): 3Gb / sec => 0.00003초 / 1byte
           
           1byte 읽기 시간 = 0.0042 + 0.00003초 = 0.004203초
           100byte 읽기 시간 =  0.004203 + 100 = 0.4203초
           
           한번 찾았을 때 100바이트 읽기 = 0.0042 + (0.000003 *100)
                            = 0.0045초
        메모리(10ns) = 0.000000001초 
        1억 * 0.000000001초 = 1초
        1억 * 0.004203초(하드에서 읽어들이는데 걸리는 시간) = 420300초 = 약 4.8일
 */
package java002.test02;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class test16 {
	
	//버퍼 사용 후
		public static void main(String[] args) throws Exception {

			FileInputStream in = new FileInputStream("C:/Users/Narae/Desktop/test.pdf");
			BufferedInputStream in2 = new BufferedInputStream(in);
			
			int b = 0;
			
			long start = System.currentTimeMillis();

			while ((b = in2.read()) != -1) {}

			long end = System.currentTimeMillis();

			System.out.println(end - start);

			in2.close();
			in.close();

		}
		
	//버퍼 사용 후
	public static void main02(String[] args) throws Exception {

		FileInputStream in = new FileInputStream("C:/Users/Narae/Desktop/test.pdf");
		
		byte[] buf = new byte[2048];
		int len = 0;
		
		int b = -1;
		long start = System.currentTimeMillis();

		while ((len = in.read(buf)) != -1) {}

		long end = System.currentTimeMillis();

		System.out.println(end - start);

		in.close();

	}
	//버퍼 사용 전 
	public static void main01(String[] args) throws Exception {

		FileInputStream in = new FileInputStream("C:/Users/Narae/Desktop/test.pdf");
		
		int b = -1;
		long start = System.currentTimeMillis();

		while ((b = in.read()) != -1) {
		}

		long end = System.currentTimeMillis();

		System.out.println(end - start);

		in.close();

	}
}

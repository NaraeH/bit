/*
 Q.파일을 복제하는 기능을 구현하시오.
 -$Test02 /home/bit/img1.jpg(엔터)
 => img1.jpg 파일복제하여 img1-01.jpg 
 
 힌트: 출력은 FileOutputStream을 사용하라!
  */
package java002.test02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class test02 {

	public static void main(String[] args) {
		int b = 0;
		int i = 1;
		
		// "\"로 나누고 싶지만 프로그램에서는 \를 escape으로 인식한다. 따라서, \\\\로 써야 한다.
		// "."로 나누고 싶지만 프로그램에서는 \를 escape으로 인식한다. 따라서, \\.로 써야 한다.
		String[] fileName = args[0].split("\\\\");
		String fileName1 = fileName[fileName.length - 1].split("\\.")[0];
		String fileName2 = fileName[fileName.length - 1].split("\\.")[1];
		

		try {
			FileInputStream input = new FileInputStream(args[0]);
			FileOutputStream output = new FileOutputStream(fileName1 + "-0" + i + "." +  fileName2);

			while ((b = input.read()) != -1) {
				output.write(b);
			}
			i++;

		} catch (FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
		} catch (IOException e) {
			System.out.println("파일 입력이 옳지 않습니다");
		}
	}

}

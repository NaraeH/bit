/*
  < 문자 데이터의 읽기 >
 - FileReader
 - 텍스트 파일을 문자 스트림 객체를 사용하여 읽기
 - read(): 한 문자 단위로 읽는다. => 리턴 값은 유니코드이다.
   한문자 => 영어(1바이트), 한글(2바이트(유니코드일 경우) 또는 3바이트).. 
 - 문자 스트림은 읽어들인 데이터를 유니코드로 변환한다.
 
 * 주의점: 파일 데이터를 읽을때는 FileReader를 읽지말아라. 혹시 변환된 유니코드 값이 우연치 않게 글자랑 동일하다면 파일을 제대로 읽어들일 수 없음
             때문에 텍스트 파일이 아닌 다른 그림이나 동영상과 같은 파일을 읽을 때는 FileInputStream을 사용해라
 */
package java002.test02;

import java.io.FileReader;

public class Test08 {

	public static void main(String[] args) throws Exception{
		
		FileReader in = new FileReader("test01.txt");
		
		int b = -1;
		while((b = in.read()) != -1){
			System.out.println(Integer.toHexString(b)); //가장 밑에 한글이 한 글자씩 받아오는 것 알 수 있음 가: ac00, 각: ac01, 간:ac04
		}
		in.close();

	}

}

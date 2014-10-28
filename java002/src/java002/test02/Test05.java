/*
 <File 기본 사용>
 
 */
package java002.test02;

import java.io.File;

public class Test05 {
	public static void main(String[] args) throws Exception{
		
		File f = new File("../..//java63/java01");
		
		//결과: C:\Users\Narae\git\bit\java002\..\..\java63\java01 =>..을 출력해주고 직접 확인해라 
		System.out.println(f.getAbsolutePath()); 
		                                          
		//결과: C:\Users\Narae\git\java63\java01 =>..을 다 계산한 실제 경로
		System.out.println(f.getCanonicalPath()); 
		
		//결과: ..\..\java63\java01 => new File 에서 준 뒷부분만 출력
		System.out.println(f.getPath());
		
		//결과:java01 => 현재 폴더만 출력
		System.out.println(f.getName()); 
		
		//결과:..\..\java63 =>속해있는 것의 앞이름
		System.out.println(f.getParent()); 
 
	}
}

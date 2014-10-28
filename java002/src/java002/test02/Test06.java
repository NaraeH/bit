/*<File을 사용하여 디렉토리 안의 파일 목록을 출력한다>
  
 */
package java002.test02;

import java.io.File;

public class Test06 {

	public static void main(String[] args) {

		displayLsit("."); // .: 현재패키지의 모든 것 , src: src폴더 바로 밑의 하위 폴더
		
/*		File f1 = new File(".");
		
		String[] f1List = f1.list();
		
		for(String name:f1List){
			System.out.println(name);
		}*/

	}

	public static void displayLsit(String filename) {
		System.out.println("filename: " + filename);

		File f = new File(filename);

		if (f.isDirectory()) { //Directory: 파일이나 또 다른 directory를 가지고 있는 파일

			String[] files = f.list();
			
			System.out.println("======>" + f.getPath());

			for (String name : files) {
				displayLsit(f.getPath() + "/" +  name);
			}
		}
	}

}

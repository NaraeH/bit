/*
 Q. 예외 처리 강화
 파일을 읽을 때,
 1) 파일이 존재하지 않으면, 다음 문장 출력
    xxxx.xxx 파일이 존재하지 않습니다.
 2) 디렉토리라면 
    xxxx.xxx 는 파일이 아니라 디렉토리입니다.
 
 파일을 쓸 때,
 1) 파일이 이미 존재한다면,
    xxxx.xxx  파일이 이미 존재합니다. 덮어 쓰시겠습니까?(y/n)y
    xxxx.xxx 파일을 출력하였습니다.
    
 2) 파일이 존재하지 않는다면,
    xxxx.xxx 파일을 출력하였습니다.
 */
package java002.test02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class test03_1 {

	public static void main(String[] args) {

		String[] file = args[0].split("\\\\");
		String fullFileName = file[file.length - 1];
		String fileName = fullFileName.split("\\.")[0];
		String fileExtension = fullFileName.split("\\.")[1];

		File toFile = new File(".");
		Path toPath = Paths.get(fileName + "-01." + fileExtension);
		
		String[] list = toFile.list();
		
		for(String item:list){
			System.out.println(item);
		}

		try {
			
			if(!toFile.exists()){
				Files.copy(Paths.get(args[0]), toPath, StandardCopyOption.REPLACE_EXISTING);
				System.out.println("파일을 출력하였습니다.");
			}else {
				System.out.println( fullFileName + "파일이 이미 존재합니다. 덮어 쓰시겠습니까?(y/n): ");
				Scanner scanner = new Scanner(System.in);
				
				if((scanner.next()).equals("y")){
					Files.copy(Paths.get(args[0]), toPath, StandardCopyOption.REPLACE_EXISTING);
					System.out.println("파일을 출력하였습니다.");
				}
			}
			
		} catch (NoSuchFileException e){
			System.out.println(e.getFile() + "이 존재하지 않습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("끝");
		}

	}

}

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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class test03_0 {

	public static void main(String[] args) {

		String[] fileName = args[0].split("\\\\");
		String fileName1 = fileName[fileName.length - 1].split("\\.")[0];
		String fileName2 = fileName[fileName.length - 1].split("\\.")[1];
		
		Path to = Paths.get(fileName1 + "-01" + "." +  fileName2);
		

		try {
			Files.copy(Paths.get(args[0]), to, StandardCopyOption.REPLACE_EXISTING);
			
		} catch (FileNotFoundException e) {
			System.out.println(fileName[fileName.length - 1] + "이 존재하지 않습니다");
		} catch (FileAlreadyExistsException e) {
			System.out.println(e.getFile() + "존재");
		} catch (DirectoryNotEmptyException e) {
			System.out.println(e.getStackTrace());
		} catch (UnsupportedOperationException e){
			System.out.println(e.getStackTrace());
		}
		catch (IOException e) {
			System.out.println(fileName[fileName.length - 1] + "이 존재하지 않습니다: IOException");
			System.out.println(e);
		} catch (NullPointerException e){
			System.out.println("NullPointerException");
		}
		finally {
			System.out.println("끝");
		}
	}

}

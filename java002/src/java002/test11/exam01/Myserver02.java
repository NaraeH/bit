/*
 <ServerSocket 사용법> 
 */
package java002.test11.exam01;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Myserver02 {
	public static void main(String[] args) throws Exception{
		//클라이언트와 통신을 담당할 객체 생성
		System.out.println("서버 소켓 생성");
		ServerSocket ss = new ServerSocket(8888);
		
		//연결을 대기하고 있는 클라이언트들 중에서  하나 선택하기
		//=> 선택한 클라이언트와 통신을 담당할 소켓 객체를 리턴
		System.out.println("클라이언트 연결을 기다리는 중....");
		Socket socket = ss.accept();
		System.out.println("대기중에 있는 클라이언트와 연결 됨");
		
		//소켓을 통해 읽고 쓰기 위한 /입출력 스트림 얻기
		//Scanner: 바이트 단위가 아닌 한 라인(문자열)로 얻기 위해서 scanner 객체를 이용하여 중간에 데이터 가공하여 받음
		//PrintStream: 출력할 때에 바이트 별이 아닌 한 라인(문자열)로 해서 받기
		Scanner in = new Scanner(socket.getInputStream());
		PrintStream out = new PrintStream(socket.getOutputStream());
		
		//클라이언트가 보낸 문자열읽기
		//클라이언트에서 한 줄의 문자열을 보내기 전까지 리턴하지 않는다.
		//"실행이 완료될 때까지 리턴하지 않는다." => blocking
		String line = in.nextLine();
		
		//클라이언트가 보낸 메시지를 출력
		System.out.println(line);
		
		//사용자에게서 문자열을 한 줄 입력받는다.
		String message = prompt();
		
		//클라인트로 보낸다.
		//클라이언트에서 문자열을 모두 받을 때까지 리턴하지 않는다.
		//입출력은 항상 블로킹으로 다룬다 => 단점해결: Java non-blocking API등장
		out.println(message);
		
		//다 섰으니깐 닫자
		out.close();
		in.close();
		socket.close();
		ss.close();
		
	}

	static private String prompt() {
		System.out.println(">");
		Scanner keyboard = new Scanner(System.in);
		String message = keyboard.nextLine();
		keyboard.close();
		
		return message;
	}

}

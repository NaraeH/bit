/*
 <클라이언트와 여러번 데이터 주고받기> 
 */
package java002.test11.exam01;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Myserver03 {
	static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) throws Exception{
		
		System.out.println("서버 소켓 생성");
		ServerSocket ss = new ServerSocket(8888);
		
		System.out.println("클라이언트 연결을 기다리는 중....");
		Socket socket = ss.accept();
		System.out.println("대기중에 있는 클라이언트와 연결 됨");
		
		Scanner in = new Scanner(socket.getInputStream());
		PrintStream out = new PrintStream(socket.getOutputStream());
		
		String message = null;
		String line = null;
		while (true) {
			line = in.nextLine();
			System.out.println(line);

			if (line.equalsIgnoreCase("quit")) {
				out.println("goodbye");
				break;
			}
			message = prompt();
			out.println(message);
		}

		//다 섰으니깐 닫자
		out.close();
		in.close();
		socket.close();
		ss.close();
		keyboard.close();
		
	}

	static private String prompt() {
		System.out.println(">");
		String message = keyboard.nextLine();
		
		return message;
	}

}

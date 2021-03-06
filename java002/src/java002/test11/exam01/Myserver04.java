/*
 <여러 클라이언트와 대화 나누기> 
 => 동시에 대화 나누는 것이 불가능
 */
package java002.test11.exam01;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Myserver04 {
	//클라이언트와 연결이 종료되면 대기열의 다른 클라이언트와 연결한다.
	//다만, 코딩한 바와 같이 순차적으로 실행한다.
	static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) throws Exception{
		System.out.println("서버 소켓 생성");
		ServerSocket ss = new ServerSocket(8888, 2);
		System.out.println("클라이언트 연결을 기다리는 중....");

		while (true) {
			Socket socket = ss.accept();
			System.out.println("대기중에 있는 클라이언트와 연결 됨");

			Scanner in = new Scanner(socket.getInputStream());
			PrintStream out = new PrintStream(socket.getOutputStream());

			String message = null,line = null;
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
			// 다 섰으니깐 닫자
			in.close();
			out.close();
			socket.close();
		}

//		ss.close();
//		keyboard.close();
	}
		

	static private String prompt() {
		System.out.println(">");
		String message = keyboard.nextLine();
		
		return message;
	}

}

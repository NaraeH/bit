/*
 <protocol에 hello 명령 추가>
 - client는 처음 접속 할 때 "hello 이름" 명령을 보내야 한다.
 - 서버는 이름을 기억해 두었다가 사용한다.
 */
package java002.test11.exam02;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyServer01 {
	static Scanner keyboard = new Scanner(System.in);

	static class ChatSkeleton implements Runnable {
		Socket socket;
		Scanner in;
		PrintStream out;

		public ChatSkeleton(Socket socket) throws IOException {
			this.socket = new Socket();
			in = new Scanner(socket.getInputStream());
			out = new PrintStream(socket.getOutputStream());
		}

		@Override
		public void run() {
			String name = null;
			try {
				String message = null;
				String[] line = null;
				
				while (true) {
					line = in.nextLine().split(" ");
					
					if(line[0].equalsIgnoreCase("hello")){
						name = line[1];
						System.out.println(name + "님이 좁속하였습니다.");
						out.println("반갑습니다^^*");
						continue;
					}else if(line[0].equalsIgnoreCase("quit")){
						System.out.println(name + "님이 연결을 끊었습니다.");
						out.println("goodbye");
						break;
					}else{
						System.out.println(name + ": " + line[0]);

						message = prompt();
						out.println(message);
					}
				}

			} catch (Exception e) {
				System.out.println("\nclient와 통신 중 오류 발생");
				System.out.println(name + "님과의 연결을 종료하였습니다.");
			} finally {

				in.close();
				out.close();
				//socket.close();
			}
		}

		private static String prompt() {
			System.out.print(">");
			String message = keyboard.nextLine();
			return message;
		}

		public static void main(String[] args) throws Exception {
			System.out.println("서버 소켓 생성");
			ServerSocket ss = new ServerSocket(8888, 2);
			System.out.println("클라이언트의 연결을 기다리는 중...");

			while (true) {
				Socket socket = ss.accept();
				System.out.println("\n대기중에 있는 클라이언트와 연결됨");
				
				(new Thread(new ChatSkeleton(socket))).start();
			}
		}
	}
}

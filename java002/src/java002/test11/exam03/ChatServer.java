package java002.test11.exam03;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {
	static Scanner keyboard = null;
	public static void main(String[] args) throws IOException {
		ServerSocket ss = null;
		Socket socket = null;
		Scanner in = null;
		PrintStream out = null;
		
		try {
			keyboard = new Scanner(System.in);
			ss = new ServerSocket(8888);
			
			System.out.println("client 연결을 기다리고 있습니다....");
			socket= ss.accept();
			System.out.println("client와 연결되었습니다.");
			
			in = new Scanner(socket.getInputStream());
			out = new PrintStream(socket.getOutputStream());
			
			ChatReaderThread readerThread = new ChatReaderThread(in);
			readerThread.start();
			
			String message = null;
			String line = null;
			while(true){
				line = prompt();
				out.println(line);
			}
			
		} catch (Exception e) {
			//오류무시
		} finally {
			try { ss.close();} catch (Exception e) {}
			try { socket.close(); } catch (Exception e) {}
			try { in.close(); } catch (Exception e) {}
			try { out.close(); } catch (Exception e) {}
			try { keyboard.close(); } catch (Exception e) {}
		}

	}
	
	static private String prompt() {
		System.out.println(">");
		String message = keyboard.nextLine();

		return message;
	}


}

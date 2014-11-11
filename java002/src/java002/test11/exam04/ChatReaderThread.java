package java002.test11.exam04;

import java.awt.TextArea;
import java.util.Scanner;

public class ChatReaderThread extends Thread{
	Scanner in;
	TextArea taContent;
	
	public ChatReaderThread(Scanner in, TextArea taContent){
		this.in = in;
		this.taContent = taContent;
	}
	
	@Override
	public void run() {
		String message = null;
		try {
			while (true) {
				message = in.nextLine();
				if(message.equalsIgnoreCase("quit")){
					taContent.append("서버와 연결을 끊었습니다 \n");
					break;
				}
				taContent.append(message + "\n");
			}
		} catch (Exception e) {
			System.out.println("데이터 수신 중 오류 발생");
		}
	}
}

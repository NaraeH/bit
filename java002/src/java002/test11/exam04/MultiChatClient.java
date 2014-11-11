/*
<연결기능구현>
   
 */
package java002.test11.exam04;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

@SuppressWarnings("serial")
public class MultiChatClient extends Frame implements ActionListener{
	
	TextField tfServerAddr = new TextField(10);
	TextField tfName = new TextField(8);
	Button btnConnect = new Button("connect");
	TextArea taContent = new TextArea();
	TextField tfInput = new TextField(30);
	Button btnSend = new Button("send");
	
	String username = null;
	String serverAddress = null;
	Socket socket = null;
	Scanner in = null;
	PrintStream out = null;
	
	public class ChatReaderThread extends Thread{

		@Override
		public void run() {
			String message = null;
			try {
				while (true) {
					message = in.nextLine();
					taContent.append(message + "\n");
				}
			} catch (Exception e) {
				System.out.println("데이터 수신 중 오류 발생");
			}
		}
	}

	
	public MultiChatClient(){
		Panel toolbar = new Panel(new FlowLayout(FlowLayout.LEFT));
		toolbar.add(new Label("name: "));
		toolbar.add(tfName);
		toolbar.add(new Label("server: "));
		toolbar.add(tfServerAddr);
		toolbar.add(btnConnect);
		this.add(toolbar, BorderLayout.NORTH);
		this.add(taContent, BorderLayout.CENTER);
		
		Panel bottom = new Panel(new FlowLayout(FlowLayout.LEFT));
		bottom.add(tfInput);
		bottom.add(btnSend);
		this.add(bottom, BorderLayout.SOUTH);
		
		//WindowApapter는 class이므로 상속해야 한다.
		//but, 이미 Frame을 상속받았으므로 상속불가능
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {

				try{
					out.println("quit");
					taContent.append("서버와 연결을 종료하였습니다.\n");
					socket.shutdownInput(); //강제적으로 읽는 스트림을 닫아버림
				}catch(Exception ex){}
				
				try{ in.close(); }catch(Exception ex){}
				try{ out.close(); }catch(Exception ex){}
				try{ socket.close(); }catch(Exception ex){}

				System.exit(0);   //JVM을 종료하겠다.
			}
		});
		
		//actionPerformed가 호출된다.
		btnConnect.addActionListener(this);
		btnSend.addActionListener(this);
		tfInput.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// e.getSource(): 이벤트가 발생한 객체의 주소를 리턴
		if (e.getSource() == btnConnect) { // 연결 버튼을 눌렀다면
			username = tfName.getText();
			serverAddress = tfServerAddr.getText();
			System.out.println("이름: " + username);
			System.out.println("서버주소: " + serverAddress);
			
			try {
				socket = new Socket(serverAddress, 8888);
				in = new Scanner(socket.getInputStream());
				out = new PrintStream(socket.getOutputStream());
				
				ChatReaderThread reader = new ChatReaderThread();
				reader.start();
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		} else if(e.getSource() == btnSend) { // send 버튼을 눌렀다면
			//1) 화면에 보낼 내용을 먼저 출력한다.
			//taContent.append(username+ "> " + tfInput.getText() + "\n");
			
			//2) 서버에 입력 내용을 보낸다.
			out.println(username +">" +tfInput.getText());
			tfInput.setText(""); //입력상자를 초기화한다.
		}
	}
		
	public static void main(String[] args) {
		System.out.println("client");

		MultiChatClient wnd = new MultiChatClient();
		wnd.setSize(400, 600);
		wnd.setBackground(Color.GREEN);
		wnd.setTitle("Chatty!!!");
		wnd.setVisible(true);
	}


}

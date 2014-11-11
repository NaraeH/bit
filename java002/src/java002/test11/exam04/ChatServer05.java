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
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

@SuppressWarnings("serial")
public class ChatServer05 extends Frame implements ActionListener{
	
	TextArea taContent = new TextArea();
	TextField tfInput = new TextField(30);
	Button btnSend = new Button("send");
	
	ServerSocket serverSocket = null;
	Socket socket = null;
	Scanner in = null;
	PrintStream out = null;
	
	public ChatServer05(){
		Panel toolbar = new Panel(new FlowLayout(FlowLayout.LEFT));
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
				taContent.append("클라이언트와 연결을 종료하였습니다.\n");
				socket.shutdownInput(); //강제적으로 읽는 스트림을 닫아버림
				tfInput.setText("");
				
			}catch(Exception e1){}
			
				try{ in.close(); }catch(Exception ex){}
				try{ out.close(); }catch(Exception ex){}
				try{ socket.close(); }catch(Exception ex){}
				try{ serverSocket.close(); }catch(Exception ex){}

				System.exit(0);   //JVM을 종료하겠다.
			}
		});
		btnSend.addActionListener(this);
		tfInput.addActionListener(this);
	}

	public void service() {
		try{
			taContent.append("클라이언트의 연결을 기다리는 중.... \n");
			serverSocket = new ServerSocket(8888);
			socket = serverSocket.accept();
			
			in = new Scanner(socket.getInputStream());
			out = new PrintStream(socket.getOutputStream());
			
			String[] message = in.nextLine().split(" "); 
			taContent.append(message[1] + "님과 연결되었습니다 \n\n");
			
			ChatReaderThread reader = new ChatReaderThread(in, taContent);
			reader.start();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		taContent.append("server> " + tfInput.getText() + "\n");

		out.println("server" + "> " + tfInput.getText());
	}
		
	public static void main(String[] args) {
		System.out.println("server");

		ChatServer05 wnd = new ChatServer05();
		wnd.setSize(400, 600);
		//wnd.setBackground(Color.GREEN);
		wnd.setTitle("Chatty!!!");
		wnd.setVisible(true);
		wnd.service();
	}


}

/*
<implements ActionListener하기>
   
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

public class ChatClient04 extends Frame implements ActionListener{
	TextField tfServerAddr = new TextField(10);
	TextField tfName = new TextField(8);
	Button btnConnect = new Button("connect");
	TextArea taContent = new TextArea();
	TextField ftInput = new TextField(30);
	Button btnSend = new Button("send");
	
	String username = null;
	String serverAddress = null;
	
	public ChatClient04(){
		Panel toolbar = new Panel(new FlowLayout(FlowLayout.LEFT));
		toolbar.add(new Label("name: "));
		toolbar.add(tfName);
		toolbar.add(new Label("server: "));
		toolbar.add(tfServerAddr);
		toolbar.add(btnConnect);
		this.add(toolbar, BorderLayout.NORTH);
		this.add(taContent, BorderLayout.CENTER);
		
		Panel bottom = new Panel(new FlowLayout(FlowLayout.LEFT));
		bottom.add(ftInput);
		bottom.add(btnSend);
		this.add(bottom, BorderLayout.SOUTH);
		
		//WindowApapter는 class이므로 상속해야 한다.
		//but, 이미 Frame을 상속받았으므로 상속불가능
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);   //JVM을 종료하겠다.
			}
		});
		
		//actionPerformed가 호출된다.
		btnConnect.addActionListener(this);
		btnSend.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// e.getSource(): 이벤트가 발생한 객체의 주소를 리턴
		if (e.getSource() == btnConnect) { // 연결 버튼을 눌렀다면
			username = tfName.getText();
			serverAddress = tfServerAddr.getText();
			System.out.println("이름: " + username);
			System.out.println("서버주소: " + serverAddress);
		} else if(e.getSource() == btnSend) { // send 버튼을 눌렀다면
			System.out.println("보내기 버튼 눌렀다!");
		}
	}
		
	public static void main(String[] args) {
		System.out.println("hear");

		ChatClient04 wnd = new ChatClient04();
		wnd.setSize(400, 600);
		wnd.setBackground(Color.GREEN);
		wnd.setTitle("Chatty!!!");
		wnd.setVisible(true);
	}


}

/*
<inner 클래스 처리하기>
   
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

public class ChatClient03 extends Frame{
	TextField tfServerAddr = new TextField(10);
	TextField tfName = new TextField(8);
	Button BtnConnect = new Button("connect");
	TextArea taContent = new TextArea();
	TextField ftInput = new TextField(30);
	Button btnSend = new Button("send");
	
	String username = null;
	String serverAddress = null;
	
	public ChatClient03(){
		//window준비
		Panel toolbar = new Panel(new FlowLayout(FlowLayout.LEFT));
		toolbar.add(new Label("name: "));
		toolbar.add(tfName);
		toolbar.add(new Label("server: "));
		toolbar.add(tfServerAddr);
		toolbar.add(BtnConnect);
		this.add(toolbar, BorderLayout.NORTH);
		this.add(taContent, BorderLayout.CENTER);
		
		Panel bottom = new Panel(new FlowLayout(FlowLayout.LEFT));
		bottom.add(ftInput);
		bottom.add(btnSend);
		this.add(bottom, BorderLayout.SOUTH);
		
		//리스너등록
		//1) window event를 처리할 리스너 객체 등록
		//2) 반드시 WindowListener interface를 구현한 객체여야 한다. => inner class로 만들기
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);   //JVM을 종료하겠다.
			}
		});
		
		//connect버튼 눌렀을 때, ActionEvent는 버튼을 눌렀을 때, 발생하는 이벤트이다.
		//실무에서는 한번빡에 안쓸 객체라면 익명 이너클래스를 정의한다. =>JS와 유사
		BtnConnect.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//this는 나의 field(class)에 있는 것을 말함
				//this를 붙이지 않으면 내 필드부터 상위필드로 올라가면서 그 이름과 맞는 것이 있는지 찾음 => this 생략가능
				//단, 상위 클래스에 inner class와 같은 이름을 가진 변수가 있다면 생략 불가하다.
				// ex) ChatClient03.this.username = tfName.getText();
				username = tfName.getText();
				serverAddress = tfServerAddr.getText();
				System.out.println("이름: " + username);
				System.out.println("서버주소: " + serverAddress);
			}
		});
		
		//send 버튼 눌렀을 때
		//실무에서는 한번빡에 안쓸 객체라면 익명 이너클래스를 정의한다. =>JS와 유사
		btnSend.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("보내기 버튼 눌렀다!");
			}
		});
	}

	public static void main(String[] args) {
		System.out.println("hear");

		ChatClient03 wnd = new ChatClient03();
		wnd.setSize(400, 600);
		wnd.setBackground(Color.GREEN);
		wnd.setTitle("Chatty!!!");
		wnd.setVisible(true);

		// System.out.println(wnd.getTitle());
	}

}

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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Method;

import com.google.common.base.Objects;

public class ChatClient02 extends Frame{
	TextField serverAddr = new TextField(10);
	TextField name = new TextField(8);
	Button connectBtn = new Button("connect");
	TextArea content = new TextArea();
	TextField input = new TextField(30);
	Button sendBtn = new Button("send");
	
	public ChatClient02(){
		//window준비
		Panel toolbar = new Panel(new FlowLayout(FlowLayout.LEFT));
		toolbar.add(new Label("name: "));
		toolbar.add(name);
		toolbar.add(new Label("server: "));
		toolbar.add(serverAddr);
		toolbar.add(connectBtn);
		this.add(toolbar, BorderLayout.NORTH);
		this.add(content, BorderLayout.CENTER);
		
		Panel bottom = new Panel(new FlowLayout(FlowLayout.LEFT));
		bottom.add(input);
		bottom.add(sendBtn);
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
		connectBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("연결 눌렀다.");
			}
		});
		
		//send 버튼 눌렀을 때
		//실무에서는 한번빡에 안쓸 객체라면 익명 이너클래스를 정의한다. =>JS와 유사
		sendBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("보내기 버튼 눌렀다!");
			}
		});
	}
 static void main(String[] args) {

		ChatClient02 wnd = new ChatClient02();
		wnd.setSize(400, 600);
		wnd.setBackground(Color.GREEN);
		wnd.setTitle("Chatty!!!");
		wnd.setVisible(true);
		
		//System.out.println(wnd.getTitle());
	}

}

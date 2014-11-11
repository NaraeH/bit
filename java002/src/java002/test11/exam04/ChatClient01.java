/*
  < >
  Frame Class : 창(window)의 형태를 나타내기 위한 도구를 담고있는 class
                java에서 만드는 것이 아닌 OS에서 만드는 것. 때문에 OS따라 창의 모양이 다름
                => 윈도우는 윈도우 창, 우분투는 우분투 창, 맥은 맥창
                
   1) method 종류
   setSize(width, height) : 윈도우 크기 보이기
   setViserble(true) : 화면에 보여라 
   
   2) 용어
   title bar:"minimize, maximize, close" 버튼이 있는 상단바
   
   3)window의 경우
   GDI API, Windows API 이용
   
 */
package java002.test11.exam04;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChatClient01 extends Frame{
	TextField serverAddr = new TextField(10);
	TextField name = new TextField(8);
	Button connectBtn = new Button("connect");
	TextArea content = new TextArea();
	TextField input = new TextField(30);
	Button sendBtn = new Button("send");
	
	public ChatClient01(){
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
		//2) 반드시 WindowListener interface를 구현한 객체여야 한다.
		this.addWindowListener(new MyWindowListener());
		
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
	
	class MyWindowListener extends WindowAdapter{
		//다음 메서드는 윈도우에서 close 버튼을 눌렀을때 호출된다.
		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);   //JVM을 종료하겠다.
		}
	}
	
	public static void main(String[] args) {
		Menu menu = new Menu("menu");
		MenuBar menubar = new MenuBar();
		menubar.add(menu);

		ChatClient01 wnd = new ChatClient01();
		wnd.setMenuBar(menubar);
		wnd.setSize(400, 600);
		wnd.setBackground(Color.GREEN);
		wnd.setTitle("Chatty!!!");
		wnd.setMenuBar(new MenuBar());
		wnd.setVisible(true);
		
		//System.out.println(wnd.getTitle());
	}

}

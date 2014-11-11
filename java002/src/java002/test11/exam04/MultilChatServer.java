package java002.test11.exam04;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class MultilChatServer {
	//서버쪽에서 연결을 위한 서버소켓 변수 생성 및 초기화
	//ServerSocket은 client에서 요청이 들어오는 것을 감시하는 객체 
	//만약, client에서 요청이 들어온다면 server쪽 socket에 client를 리턴시켜주는 역할
	ServerSocket serverSocket = null;
	
	//
	HashMap<Integer, PrintStream> outputMap = new HashMap<Integer, PrintStream>();  //key: id, value: 출력스트림
	
	
	//여러 명과 동시에 채팅할 수 있도록 Thread 상속하여 구현
	class ChatListener extends Thread{
		int id;
		Scanner in;
		MultilChatServer server;
		
		//생성자
		public ChatListener(int id, Scanner in, MultilChatServer server) throws Exception{
			this.id = id;
			this.in = in;
			this.server = server;
		}
		
		//thread의 instance를 생성하고 start할 때 실행되는 부분
		@Override
		public void run() {
			try {
				//보낼 메시지
				String message = null; 
				
				while (true) {
					message = in.nextLine();  //Scanner에서 값을 라인별로 받아오고 message에 저장한다.
					server.send(message);     //server에 저장한 message를 보낸다.

				}
			} catch (Exception e) {
				server.invalidate(id);

			}
		}
	}
	

	//읽어들이다가 문제가 생겼을 때
	public void invalidate(int id) {
		outputMap.remove(id);
	}

	synchronized public void send(String message) {
		Set<Entry<Integer, PrintStream>> entrySet = outputMap.entrySet();
		
		for(Entry<Integer, PrintStream> entry:entrySet){
			try{
				entry.getValue().println(message);
				
			}catch (Exception e){
				outputMap.remove(entry.getKey());
			}
		}
	}
	
	public void service() throws Exception{
		//serverSocket 인스턴스 생성
		serverSocket = new ServerSocket(8888);

		//client에서 요청이 들어온다면 직접적으로 통신을 하는 socket생성
		Socket socket = null;
		//사용자의 입력을 받아들이는 변수 생성 및 초기화
		Scanner in = null;
		//입력을 출력하기 위한 변수 생성 및 초기화
		PrintStream out = null;
		int count = 0;
		
		while (true) {
			try {
				//serverSocket은 클라이언트가 들어올 때까지 기다리다가
				//클라이언트가 접속하는 순간 해당 클라이언트와 통신할 수 있는 socket리턴
				socket = serverSocket.accept();
				
				//클라이언트와 연결이 되었으므로 server에 연결된 socket으로 부터 입력값을 받아서 "in"에 저장
				//여기서 Scanner는 입력 된 값을 저장할 때에 더 편리하게 저장하기 위해 쓰는 도구일뿐 아무 상관이없다.
				//즉, new InputStreamReader(socket.getInputStream())도 가능(InputStream을 가공할 수 있는 객체라면 모두 다 가능)
				in = new Scanner(socket.getInputStream());
				//server에 연결된 소켓으로 부터 server의 입력값을 받아 출력하기 위해 out에 저장
				//server에서 작성하는 글을 PrintStream이라는 도구를 사용
				out = new PrintStream(socket.getOutputStream());
				//id로 쓰일 값
				count++;
				
				//출력스트림보관
				//미리 생성해논 outputMap에 id와 출력스트림을 넣기
				outputMap.put(count, out); 

				//입력 스트림은 리스너에게 맡긴다.
				//새로운 객체생성하고 아이디로 쓰일 값과 입력받은 값(client로 부터 입력받은 값)과 멀티챗서버 인스턴스를 아규먼트로 보내고 서비스를 시작한다.
				//서비스를 시작할 경우 thred를 상속받은 ChatListener의 run()이 실행되게 된다.
				new ChatListener(count, in, this).start();

			} catch (Exception e) {

			}
		}
	}
	

	public static void main(String[] args) throws Exception{
		//server instance생성
		MultilChatServer server = new MultilChatServer();
		//server instance에 대해 service()실행
		server.service();
	}

}

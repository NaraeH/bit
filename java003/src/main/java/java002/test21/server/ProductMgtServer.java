/*
 mybatis 설정하기
 */
package java002.test21.server;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java002.test21.server.commands.CommandMapping;
import java002.test21.server.commands.CommandMapping.CommandInfo;
import java002.test21.server.dao.ProductDao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

public class ProductMgtServer {
	Scanner scanner;
	ProductDao productDao;
	ApplicationContext appCtx;
	CommandMapping commandMapping;
	
	public void init() throws Exception{
		scanner = new Scanner(System.in);
		
		appCtx = new ClassPathXmlApplicationContext(new String[]{"java002/test21/server/application-context.xml"});

		//objPool에서 @Command 에노테이션이 붙은 메서드를 찾는다.
		//명령어와 메서드 연결 정보를 구축한다.
		commandMapping = new CommandMapping();
		commandMapping.prepare(appCtx.getBeansWithAnnotation(Component.class).values()); //Map에서 values()를 하면 list형태로 변환가능(value만 갖고오므로)
		
	}
	
	class Servicetheread extends Thread {
		Socket socket;
		Scanner in;
		PrintStream out;

		public Servicetheread(Socket socket) throws Exception{
			this.socket = socket;
			in = new Scanner(socket.getInputStream());
			out = new PrintStream(socket.getOutputStream());
		}
		
		private void parseQueryString(String query, HashMap<String, Object> map){
			String[] entryList = query.split("&");

			String[] token= null;
			for(String entry:entryList){
				token = entry.split("=");
				map.put(token[0], token[1]);
			}
		}

		@Override
		public void run(){
			CommandInfo commandInfo = null;

			try{
				String[] token = in.nextLine().split("\\?");
				commandInfo = commandMapping.getCommandInfo(token[0]);
				
				System.out.println(commandInfo);
				
				if (commandInfo == null) {
					System.out.println("해당 명령을 지원하지 않습니다.");
					out.println(); //서버에서 더이상 데이터를 보낼 것이 없다는 표시
					return; 
				}

				HashMap<String, Object> params = new HashMap<String, Object>();
				params.put("out", out);
				
				if(token.length >1){
					parseQueryString(token[1], params);

				}

				commandInfo.method.invoke(commandInfo.instance, params);

			}catch( Exception e){
				e.printStackTrace();
				System.out.println("명령어 처리 중 요류발생. 다시시도해 주세요.");
			} finally{
				try { in.close();} catch(Exception ex){}
				try { out.close();} catch(Exception ex){}
				try { socket.close();} catch(Exception ex){}
			}
		}
	}
	
	public void service() throws Exception{
		ServerSocket serverSocket = new ServerSocket(8888);
		Socket socket = null;

		while(true){
			socket = serverSocket.accept();
			new Servicetheread(socket).start();
		}
	}
	
	public void distroy(){
		scanner.close();
	}
	
	public static void main(String[] args) throws Exception{
		ProductMgtServer app = new ProductMgtServer();
		
		app.init();
		app.service();
		app.distroy();
	}
}

/*
 mybatis 설정하기
 */
package java002.test19.server;

import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java002.test19.server.CommandMapping.CommandInfo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class ProductMgtServer {
	Scanner scanner;
	ProductDao productDao;
	ApplicationContext appCtx;
	CommandMapping commandMapping;
	
	public void init() throws Exception{
		String resource = "java002/test19/server/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//받아온 파일을 SqlSessionFactoryBuilder에 의해 만듬으로써, 해당 파일의 문장들이 실행된다.
		//어떤 것이 실행될까? 해당 파일의 내용인,  DB연결, SQL 파일 맵핑이 실행된다
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		//java0.test19.server 패키지 및 하위 패키지의 모든 클래스를 뒤진다.
		//@Component 애노테이션이 붙은 클래스를 찾는다
		//해당 클래스의 인스턴스를 생성하여 보관한다.
		appCtx = new ApplicationContext("java002.test19.server");
		
		//자동으로 주입 못하는 것은 메서드를 이용해서 주입하자.
		appCtx.addBean("sqlSessionFactory", sqlSessionFactory);
		//의존 객체 주입 => 서로 의존되어 있는 메서드끼리 짝지어서 객체 주입해주기
		appCtx.injectDependency();
		
		//objPool에서 @Command 에노테이션이 붙은 메서드를 찾는다.
		//명령어와 메서드 연결 정보를 구축한다.
		commandMapping = new CommandMapping();
		commandMapping.prepare(appCtx.getBeansAll());
		
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

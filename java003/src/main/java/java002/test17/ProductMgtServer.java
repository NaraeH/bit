package java002.test17;

import static org.reflections.ReflectionUtils.getMethods;
import static org.reflections.ReflectionUtils.withAnnotation;

import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java002.test17.annotation.Command;
import java002.test17.annotation.Component;
import java002.test17.server.util.DBConnectionPool;

import org.reflections.Reflections;

@SuppressWarnings({"unchecked", "rawtypes"})
public class ProductMgtServer {
	
	static class CommandInfo{
		public Object instance;
		public Method method;
	}
	
	Scanner scanner;
	ProductDao productDao;
	HashMap<String, CommandInfo> commandMap;
	DBConnectionPool conPool;
	
	public void init() throws Exception{
		Method method = null;
		Object command = null;
		CommandInfo commandInfo = null;
		Command commandAnno = null;
		conPool = new DBConnectionPool("com.mysql.jdbc.Driver",
				"jdbc:mysql://localhost:3306/studydb",
				"study",
				"study");

		productDao = new ProductDao();
		productDao.setDbConnectionPool(conPool);
		
		scanner = new Scanner(System.in);
		commandMap = new HashMap<String, CommandInfo>();
		
		Reflections reflections = new Reflections("java002.test17");
		Set<Class<?>> clazzList = reflections.getTypesAnnotatedWith(Component.class);
		
		for(Class clazz:clazzList){
			command = clazz.newInstance();
			
			Set<Method> methods = getMethods(clazz, withAnnotation(Command.class));
			
			for(Method m: methods){
				commandAnno = m.getAnnotation(Command.class);
				commandInfo = new CommandInfo();
				commandInfo.instance = command;
				commandInfo.method = m;
				commandMap.put(commandAnno.value(), commandInfo);
			}
			
			try {
				method = clazz.getMethod("setProductDao", ProductDao.class);
				method.invoke(commandInfo.instance, productDao);

			} catch (Exception e) {}

			try {

				method = clazz.getMethod("setScanner", Scanner.class);
				method.invoke(commandInfo.instance, scanner);

			} catch (Exception e) {}

		}
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
			//ex) name=제품&qty=20&mkno=6
			// ==> {name=제품, qty=20, mkno=6}
			String[] entryList = query.split("&");
			//HashMap<String, String> tempMap = new HashMap<String, String>();

			String[] token= null;
			for(String entry:entryList){
				token = entry.split("=");
				map.put(token[0], token[1]);
			}
		}

		@Override
		public void run(){
			CommandInfo commandInfo = null;

			String[] token = in.nextLine().split("\\?");
			//System.out.println(token[0]);
			commandInfo = commandMap.get(token[0]);

			if (commandInfo == null) {
				System.out.println("해당 명령을 지원하지 않습니다.");
				out.println(); //서버에서 더이상 데이터를 보낼 것이 없다는 표시
				return; 
			}

			try{

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

	private String[] promptCommand() {
		System.out.print("명령>");
		String[] token = scanner.nextLine().split("");
		return token;
	}
	
	public static void main(String[] args) throws Exception{
		ProductMgtServer app = new ProductMgtServer();
		
		app.init();
		app.service();
		app.distroy();
	}
}

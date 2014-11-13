package java002.test20.server;

import static org.reflections.ReflectionUtils.getMethods;
import static org.reflections.ReflectionUtils.withAnnotation;

import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import java002.test20.server.annotation.Command;
import java002.test20.server.annotation.Component;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.reflections.Reflections;

@SuppressWarnings({"unchecked", "rawtypes"})
public class MemberMgtServer {
	
	static class CommandInfo{
		public Object instance;
		public Method method;
	}
	
	Scanner scanner;
	MemberDao memberDao;
	HashMap<String, CommandInfo> commandMap;
	
	public void init() throws Exception{
		String resource = "java002/test20/server/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		System.out.println("파일로딩완료");
		Method method = null;
		Object command = null;
		CommandInfo commandInfo = null;
		Command commandAnno = null;

		memberDao = new MemberDao();
		memberDao.setSqlSessionFactory(sqlSessionFactory);
		
		scanner = new Scanner(System.in);
		commandMap = new HashMap<String, CommandInfo>();
		
		Reflections reflections = new Reflections("java002.test20");
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
				method = clazz.getMethod("setMemberDao", MemberDao.class);
				method.invoke(commandInfo.instance, memberDao);

			} catch (Exception e) {}

			try {

				method = clazz.getMethod("setScanner", Scanner.class);
				method.invoke(commandInfo.instance, scanner);

			} catch (Exception e) {}
		}
	}

	public void service() throws Exception{
		ServerSocket serverSocket = new ServerSocket(8888);
		Socket socket = null;
		PrintStream out = null;
		
		CommandInfo commandInfo = null;

		loop: while (true) {
			out = new PrintStream(socket.getOutputStream());
			try {
				serverSocket.accept();
				String[] token = scanner.nextLine().split("?");
				commandInfo = commandMap.get(token[0]);
				
				if (commandInfo == null) {
					System.out.println("해당 명령을 지원하지 않습니다.");
					continue; 
				}

	/*			HashMap<String, Object> params = new HashMap<String, Object>();

				ArrayList<String> options = new ArrayList<String>();
				for (int i = 1; i < token.length; i++) {
					options.add(token[i]);
				}
				params.put("options", options);
				
				commandInfo.method.invoke(commandInfo.instance, params);*/
				
				if(token[0].equals("exit")){
					break loop;
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("명령어 처리 중 오류 발생. 다시 시도해 주세요.");
			}
		}
	}
	
	public void distroy(){
		scanner.close();
	}

	public static void main(String[] args) throws Exception{
		MemberMgtServer app = new MemberMgtServer();
		
		app.init();
		app.service();
		app.distroy();
	}
}

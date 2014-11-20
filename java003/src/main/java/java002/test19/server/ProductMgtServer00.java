/*
 mybatis 설정하기
 */
package java002.test19.server;

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
import java002.test19.server.annotation.Command;
import java002.test19.server.annotation.Component;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.reflections.Reflections;

@SuppressWarnings({"unchecked", "rawtypes"})
public class ProductMgtServer00 {
	
	static class CommandInfo{
		public Object instance;
		public Method method;
	}
	
	Scanner scanner;
	ProductDao productDao;
	HashMap<String, CommandInfo> commandMap;
	
	public void init() throws Exception{
		//mybatis 설정시작-------------------------------------
		// MyBatis 설정 파일 경로
		String resource = "java002/test19/server/mybatis-config.xml";
		
		//설정 파일을 읽어들일 입력스트림 객체를 준비한다.
		//Resources 클래스는 해당 경로에서 리소스을 읽어오는 역할을 한다.(정말 쉽게 메소드 하나로 )
		//Resources 클래스의 getResourceAsStream 메서드를 사용하면,
		//mybatis 설정 파일을 클래스 경로에서 찾는 스트림 객체를 리턴한다.
		//직접 파일경로를 바꿀 수고를 덜 수 있다.
		//FileInputStream in = new FileInputStream("/home/bit/git/java003/bit/" + resource)와 동일하다. 하지만 이걸 사용하면 경로가 바뀌면계속 새롭게 셋팅해주어야 한다.
		InputStream inputStream = Resources.getResourceAsStream(resource);
		
		//SqlSessionFactoryBuilder().build(inputStream): 설계 도면(inputStream)으로 부터 공장을 만들어서 공장에서 찍어서 값을 보내달라.
		//mybatis 설정 파일대로 동장할 SqlSessionFactory를 얻는다.
		//빌더 역할을 수행하는 객체를 통해서 얻는다.
		//SqlSessionFactory class: sql명령을 실행시키기 위한 메서드를 담고있는 class
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		//mybatis 설정끝----------------------------------------------------

		Method method = null;
		Object command = null;
		CommandInfo commandInfo = null;
		Command commandAnno = null;

		productDao.setSqlSessionFactory(sqlSessionFactory);
		
		//java0.test19.server 패키지 및 하위 패키지의 모든 클래스를 뒤진다.
		//@Component 애노테이션이 붙은 클래스를 찾는다
		//해당 ㅡㄹ래스의 인스턴스를 생성하여 보관한다.
		
		scanner = new Scanner(System.in);
		commandMap = new HashMap<String, CommandInfo>();
		
		Reflections reflections = new Reflections("java002.test19");
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
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				method = clazz.getMethod("setScanner", Scanner.class);
				method.invoke(commandInfo.instance, scanner);

			} catch (Exception e) {
				//e.printStackTrace(); //setScanner를 못찾으면 에러 띄우기 => 현재는 사용X
			}

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
	
	public static void main(String[] args) throws Exception{
		ProductMgtServer00 app = new ProductMgtServer00();
		
		app.init();
		app.service();
		app.distroy();
	}
}

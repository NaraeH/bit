/*
<CommandMap에 명령어를 처리하는 객체를 저장할 때, 명령어 처리 객체뿐만 아니라 method 객체도 함께 저장하기>
이전 버전은 commandMap에서 계속 찾아야 한다. method를 그러면 시간이 오래 걸리므로,
이젠 찾은다음에 저장해 놓자.

 해야할 일
 1. 새로운 타입정의 => CommandInfo
 */
package java002.test15;

import static org.reflections.ReflectionUtils.getMethods;
import static org.reflections.ReflectionUtils.withAnnotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java002.test15.annotation.Command;
import java002.test15.annotation.Component;

import org.reflections.Reflections;

@SuppressWarnings({"unchecked", "rawtypes"})
public class ProductMgt {
	
	static class CommandInfo{
		public Object instance;
		public Method method;
	}
	
	Scanner scanner;
	ScoreDao scoreDao;
	HashMap<String, CommandInfo> commandMap;
	
	public void init() throws Exception{
		Method method = null;
		Object command = null;
		CommandInfo commandInfo = null;
		Command commandAnno = null;

		scoreDao = new ScoreDao();
		try {
			scoreDao.load();
		} catch (Exception e) {
			System.out.println("데이터 로딩 중 오류가 발생하였습니다.");
		}
		
		scanner = new Scanner(System.in);
		commandMap = new HashMap<String, CommandInfo>();
		
		Reflections reflections = new Reflections("java002.test10");
		Set<Class<?>> clazzList = reflections.getTypesAnnotatedWith(Component.class);
		
		for(Class clazz:clazzList){
			command = clazz.newInstance();
			
			//@Component 에노테이션이 붙은 클래스에서 @Command가 붙은 메서드를 모두 찾는다.
			//그 메서드와 인스턴스를 CommandInfo에 담은 뒤 CommandMap에 등록한다.
			Set<Method> methods = getMethods(clazz, withAnnotation(Command.class));
			
			for(Method m: methods){
				//System.out.println(command.getClass().getName() + "==>" + m.getName());
				commandAnno = m.getAnnotation(Command.class);
				commandInfo = new CommandInfo();
				commandInfo.instance = command;
				commandInfo.method = m;
				commandMap.put(commandAnno.value(), commandInfo);
			}
			
			try {
				method = clazz.getMethod("setScoreDao", ScoreDao.class);
				//System.out.println(clazz.getName() + "====>" + method.getName());
				method.invoke(commandInfo.instance, scoreDao);

			} catch (Exception e) {}

			try {

				method = clazz.getMethod("setScanner", Scanner.class);
				//System.out.println(clazz.getName() + "====>" + method.getName());
				method.invoke(commandInfo.instance, scanner);

			} catch (Exception e) {}
		}
	}

	public void service(){
		CommandInfo commandInfo = null;

		loop: while (true) {
			try {
				String[] token = promptCommand();
				commandInfo = commandMap.get(token[0]);
				
				if (commandInfo == null) {
					System.out.println("해당 명령을 지원하지 않습니다.");
					continue; 
				}

				HashMap<String, Object> params = new HashMap<String, Object>();

				ArrayList<String> options = new ArrayList<String>();
				for (int i = 1; i < token.length; i++) {
					options.add(token[i]);
				}
				params.put("options", options);
				
				//argument1: commandInfo method를 호출할 때, commandInfo가 속해있는 클래스
				//argument2: commandInfo method의 argument
				commandInfo.method.invoke(commandInfo.instance, params);
				
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

	private String[] promptCommand() {
		System.out.print("명령>");
		String[] token = scanner.nextLine().split(" ");
		return token;
	}
	
	public static void main(String[] args) throws Exception{
		ProductMgt app = new ProductMgt();
		
		app.init();
		app.service();
		app.distroy();
	}
}

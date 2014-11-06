/*
 * interface 종속을 없애기
 * Command interface를 제거하고 @command Annotation을 사용하여
 * 명령처리 클래스를 만든다.
 * 
 * 목표
 * 1) 메소드에 에노테이션을 붙이는 방법
 * 2) 특정 에너테이션이 붙은 메서드를 찾아 호출하는 방법
 */
package java002.test09;

import static org.reflections.ReflectionUtils.getMethods;
import static org.reflections.ReflectionUtils.withAnnotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import java002.test09.annotation.Command;
import java002.test09.annotation.Component;

import org.reflections.Reflections;

@SuppressWarnings({"unchecked", "rawtypes"})
public class Test01 {
	Scanner scanner;
	ScoreDao scoreDao;
	HashMap<String, Object> commandMap;
	
	public void init() throws Exception{
		scoreDao = new ScoreDao();
		try {
			scoreDao.load();
		} catch (Exception e) {
			System.out.println("데이터 로딩 중 오류가 발생하였습니다.");
		}
		
		scanner = new Scanner(System.in);
		commandMap = new HashMap<String, Object>();
		
		Reflections reflections = new Reflections("java002.test09");
		Set<Class<?>> clazzList = reflections.getTypesAnnotatedWith(Component.class);
		
		Component component = null;
		Method method = null;
		Object command = null;
		
		for(Class clazz:clazzList){
			component = (Component) clazz.getAnnotation(Component.class);
			command = clazz.newInstance();
			commandMap.put(component.value(), command);
			
			//class 관리자로 부터 해당 클래스의 method 객체를 얻는다.
			//invoke()를 사용하여 메서드를 호출한다.
			try {

				// 만약, setScoreDao가 있다면 호출하여 ScoreDao객체를 주입한다.
				//parameter1: 메서드 이름, parameter2: ScoreDao.class는 파라미터 타입
				//setScoreDao가 overload되있을 수도 있으니 그 중 파라미터가 ScoreDao.class인 것을 찾아라.
				method = clazz.getMethod("setScoreDao", ScoreDao.class);
				//System.out.println(clazz.getName() + "====>" + method.getName());
				method.invoke(command, scoreDao);

			} catch (Exception e) {}

			//scanner 의존 객체 주입
			try {

				method = clazz.getMethod("setScanner", Scanner.class);
				//System.out.println(clazz.getName() + "====>" + method.getName());
				method.invoke(command, scanner);

			} catch (Exception e) {}
		}
	}

	public void service(){
		Object command = null;

		loop: while (true) {
			try {
				String[] token = promptCommand();
				command = commandMap.get(token[0]);

				if (command == null) {
					System.out.println("해당 명령을 지원하지 않습니다.");
					continue; 
				}

				HashMap<String, Object> params = new HashMap<String, Object>();
				//params.put("scoreDao", scoreDao);
				//params.put("scanner", scanner);

				ArrayList<String> options = new ArrayList<String>();
				for (int i = 1; i < token.length; i++) {
					options.add(token[i]);
				}
				params.put("options", options);
				
				Set<Method> methods = getMethods(command.getClass(), withAnnotation(Command.class));
				
				for(Method m: methods){
					//System.out.println(command.getClass().getName() + "==>" + m.getName());
					m.invoke(command, params);
					break; //한 번만 반복하기 => method는 
				}
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
		Test01 app = new Test01();
		
		app.init();
		app.service();
		app.distroy();
	}


}

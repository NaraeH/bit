/*
 에노테이션이 붙은 클래스를 찾아 자동 로딩하기
 - 클래스를 찾을 때 ClassFinder를 사용한다.
 */
package java002.test07;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java002.test07.annotation.Component;
import java002.test07.util.ClassFinder;

public class Test01 {
	//static변수로 만들기보다는 instance 변수로 만들자!!!
	Scanner scanner;
	ScoreDao scoreDao;
	HashMap<String, Command> commandMap;
	
	public void init() throws Exception{
		commandMap = new HashMap<String, Command>();
		
		//클래스가 들어있는 폴더를 뒤져서 @Component 에노테이션이 붙은 클래스를 로딩한다.
		//그 로딩된 클래스의 instance를 생성하여 commandMap에 저장한다.
		
		//1) 폴더를 뒤져서 클래스정보(패키지명 + class 이름)알아내기
		ClassFinder classFinder = new ClassFinder("java002.test07");
		classFinder.find("./bin");
		List<String> classNames = classFinder.getClassList();
		
		Class clazz = null;
		Command command= null;
		Component component = null;
				
		//2) 클래스 로딩
		for(String className:classNames){
			//forName(): 클래스 로딩후 클래스를 다루는 관리자(class변수)를 리턴
			clazz = Class.forName(className);
			
			//3) 로딩된 클래스 중에서 @component 에노테이션이 붙은 클래스만 인스턴스를 생성한다.
			//class 변수 => new class()라고 하여 새로운 class 객체를 생성하는 것
			//타입정보(크기, 용도, 메서드 등...)를 다루는 객체, 그 타입 객체(Component)를 다루는 도구의 주소가 들어있음
			//항상 타입(클래스, 인터페이스, 에노테이션으로 정의된 타입)에는 도구(Class변수)가 따라다닌다. ex) String.class, Object.class, File.class 등.. 
			//class 변수에는 클래스이름 이름은 뭔지, method는 뭐가 있는지 등의 도구가 들어있다.
			//getAnnotation(annotation 정보를 담고있는 class변수)
			component = (Component) clazz.getAnnotation(Component.class);
			
			if(component != null){
				command = (Command)clazz.newInstance();
				commandMap.put(component.value(), command);
			}
		}
		
		scoreDao = new ScoreDao();
		try {
			scoreDao.load();
		} catch (Exception e) {
			System.out.println("데이터 로딩 중 오류가 발생하였습니다.");
		}
		
		scanner = new Scanner(System.in);
	}
	
	public void service(){
		Command command = null;

		loop: while (true) {
			try {
				String[] token = promptCommand();
				command = commandMap.get(token[0]);

				if (command == null) {
					System.out.println("해당 명령을 지원하지 않습니다.");
					continue; 
				}

				HashMap<String, Object> params = new HashMap<String, Object>();
				params.put("scoreDao", scoreDao);
				params.put("scanner", scanner);

				ArrayList<String> options = new ArrayList<String>();
				for (int i = 1; i < token.length; i++) {
					options.add(token[i]);
				}
				params.put("options", options);
				command.service(params);

				if (token[0].equals("exit")) {
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
		
		app.init();    //service를 하기 위한 자원 준비 commandMap, command 등
		app.service();
		app.distroy(); //service를 종료한 후 닫아야 할 자원을 닫음.
	}


}

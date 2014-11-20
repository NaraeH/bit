/*
 Bean Container
 1) 빈 생성관리
 2) 의존 객체 자동 주입
 
 log4j
 => 로그 레벨에 따라 출력을 제어할 수 있다.
 => 로그 레벨 
   1) TRACE
   2) DEBUG
   3) INFO
   4) WARN
   5) ERROR or FATAL
   => 로그 출력 레벨 설정
   1) TRACE로 설정하면 TRACE이하 모든 로그들을 출력한다.
   2) 만약, WARN 레벨로 설정하면 WARN 이하 즉, ERROR, FATAL 레벨 까지도 출력한다.
   
   => 로그 설정
   log4j.properties 파일을 참조하라.
 */
package java002.test19.server;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import java002.test19.server.annotation.Component;

import org.apache.log4j.Logger;
import org.reflections.Reflections;

public class ApplicationContext {
	static Logger log = Logger.getLogger(ApplicationContext.class);
	
	HashMap<String, Object> objPool = new HashMap<String, Object>();

	public ApplicationContext(String packagaName) throws Exception {
		prepareObjects(packagaName);
	}

	//packagaName => java002.test19.server
	private void prepareObjects(String packagaName) throws Exception{
		Reflections reflections = new Reflections(packagaName);
		Set<Class<?>> clazzList = reflections.getTypesAnnotatedWith(Component.class);
		Object instance = null;
		Component compAnno = null;
		for(Class<?> clazz:clazzList){
			instance = clazz.newInstance();

			//클래스 관리자에게서 Component 에노테이션 정보얻고
			//annotation 이름이 있다면 annotation의 value를 key값으로 갖고
			//annotation 이름이 없다면 클래스의 이름을 key 값으로 갖는다.
			compAnno = (Component)clazz.getAnnotation(Component.class);
			if(compAnno.value().equals("")){
				objPool.put(clazz.getName(), instance);
			}else{
				objPool.put(compAnno.value(), instance);
			}

		}
	}

	//java instance를 bean이라고 부른다. 
	//주입 못하는 객체 직접 넣기
	public void addBean(String key, Object instance){
		objPool.put(key, instance);
	}

	//의존 객체 주입하는 메서드
	public void injectDependency() throws Exception{
		Method[] methods = null;
		Parameter parameter = null;
		Object dependency = null; 
		
		//1) objPool에서 객체를 하나 꺼낸다.
		for(Object obj:objPool.values()){
			//System.out.println("===>" + obj);

			//2) 그 객체에서 setter method들을 꺼낸다.
			methods = obj.getClass().getMethods();

			for(Method m:methods){
				//System.out.println("methods==>" + m);
				if(m.getName().startsWith("set")){
					log.debug("   " + m.getName());
					//3) setter method의 parameter type알아보기
					//parameter = m.getParameters()[0]; //JDK1.8 이상
					
					log.debug(obj.getClass().getName());

					//System.out.println("class===>" + obj.getClass().getName());
					//System.out.println("parameter===>" + parameter.getType().getName());

					//5) setter method를 호출하여 찾은 객체를 주입한다.
					//dependency = findDependency(parameter.getType());
					//파라미터 타입의 첫번째 부분만 중요하므로 배열의 [0]번만 꺼냄
					dependency = findDependency(m.getParameterTypes()[0]);
					//System.out.println("==>" + m + "==>" + m.getParameterTypes()[0]);
					log.debug("      "+ m.getParameterTypes()[0].getName());

					if(dependency != null){
						//setter 메서드를 호출하여 찾은 객체를 주입한다.
						m.invoke(obj, dependency);
					}
				}
			}

		}
	}

	private Object findDependency(Class<?> type) {
		for(Object obj:objPool.values()){
			//System.out.println("type==> " + type);
			//System.out.println("obj===> " + obj);
			if(type.isInstance(obj)){
				//System.out.println("obj=!!!!> " + obj);
				return obj;
			}
		}
		return null;
	}
	
	public Collection<Object> getBeansAll(){
		return objPool.values();
		
	}

}

package java002.test09.commands;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java002.test09.Score;
import java002.test09.ScoreDao;
import java002.test09.annotation.Command;
import java002.test09.annotation.Component;

import org.reflections.ReflectionUtils;

@Component("add")
public class AddCommand {
	// 총괄 관리자로부터 의존 객체를 주입받고 싶으면 setter method를 준비하라.
	ScoreDao scoreDao;

	// 이 메서드는 Test01이 호출한다.
	public void setScoreDao(ScoreDao scoreDao) {
		this.scoreDao = scoreDao;
	}

	//이 메서드는 add 명령을 처리하는 기능을 수행한다. 따라서, Command 에노테이션을 붙인다.
	@Command
	public void addTest(HashMap<String, Object> params) throws Exception {
	//	ScoreDao scoreDao = (ScoreDao)params.get("scoreDao");
		ArrayList<String> options = (ArrayList<String>)params.get("options");
		
	    Score score = new Score(options.get(0), 
	            Integer.parseInt(options.get(1)), 
	            Integer.parseInt(options.get(2)), 
	            Integer.parseInt(options.get(3)));
	        
	        scoreDao.add(score);
	        System.out.println("저장하였습니다.");
	}
	
	public static void main(String[] args) {
		//AddCommand 클래스에서 @Command 에노테이션이 붙은 method를 모두 찾아라
		//첫번째 아규먼트: 찾고 싶은 메서드가 있는 클래스
		//두번째 아규먼트: 조건이다. 두번째 아규먼트 없을 시, 모든 메서드를 다 찾음(상속받은 메서드는제외) => addTest, main, setScoreDao
		//            withAnnotation(): 검색 조건을 담고 있는 객체를 생성하여 리턴
		//getMethods(타입, 조건, 조건, 조건, ....) => 해당 타입에서 그 조건에 맞는 메소드를 리턴(조건은 API에 with붙어있음)
		Set<Method> methods= ReflectionUtils.getMethods(AddCommand.class, ReflectionUtils.withAnnotation(Command.class));
		
		for(Method m: methods){
			System.out.println("===>" + m.getName());
		}
	}

}

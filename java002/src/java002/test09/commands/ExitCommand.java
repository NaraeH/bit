package java002.test09.commands;

import java.util.HashMap;
import java002.test09.ScoreDao;
import java002.test09.annotation.Component;
import java002.test10.annotation.Command;

@Component("exit")
public class ExitCommand {
	
	// 총괄 관리자로부터 의존 객체를 주입받고 싶으면 setter method를 준비하라.
	ScoreDao scoreDao;

	// 이 메서드는 Test01이 호출한다.
	public void setScoreDao(ScoreDao scoreDao) {
		this.scoreDao = scoreDao;
	}

	@Command
	public void exitTest(HashMap<String, Object> params) throws Exception {
		//ScoreDao scoreDao = (ScoreDao)params.get("scoreDao");
	
		try{
			scoreDao.save();
		}catch(Exception e){
			System.out.println("데이터 저장 중 오류 발생");
		}
}

}
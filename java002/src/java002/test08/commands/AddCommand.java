package java002.test08.commands;

import java.util.ArrayList;
import java.util.HashMap;

import java002.test08.Command;
import java002.test08.Score;
import java002.test08.ScoreDao;
import java002.test08.annotation.Component;

@Component("add")
public class AddCommand implements Command{
	// 총괄 관리자로부터 의존 객체를 주입받고 싶으면 setter method를 준비하라.
	ScoreDao scoreDao;

	// 이 메서드는 Test01이 호출한다.
	public void setScoreDao(ScoreDao scoreDao) {
		this.scoreDao = scoreDao;
	}

	@Override
	public String getCommandInfo() {
		return "add";
	}

	@Override
	public void service(HashMap<String, Object> params) throws Exception {
	//	ScoreDao scoreDao = (ScoreDao)params.get("scoreDao");
		ArrayList<String> options = (ArrayList<String>)params.get("options");
		
	    Score score = new Score(options.get(0), 
	            Integer.parseInt(options.get(1)), 
	            Integer.parseInt(options.get(2)), 
	            Integer.parseInt(options.get(3)));
	        
	        scoreDao.add(score);
	        System.out.println("저장하였습니다.");
	}

}

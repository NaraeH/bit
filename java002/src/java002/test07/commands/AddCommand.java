package java002.test07.commands;

import java.util.ArrayList;
import java.util.HashMap;

import java002.test07.Command;
import java002.test07.Score;
import java002.test07.ScoreDao;
import java002.test07.annotation.Component;

@Component("add")
public class AddCommand implements Command{

	@Override
	public String getCommandInfo() {
		return "add";
	}

	@Override
	public void service(HashMap<String, Object> params) throws Exception {
		ScoreDao scoreDao = (ScoreDao)params.get("scoreDao");
		ArrayList<String> options = (ArrayList<String>)params.get("options");
		
	    Score score = new Score(options.get(0), 
	            Integer.parseInt(options.get(1)), 
	            Integer.parseInt(options.get(2)), 
	            Integer.parseInt(options.get(3)));
	        
	        scoreDao.add(score);
	        System.out.println("저장하였습니다.");
	}

}

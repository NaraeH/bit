package java002.test05.commands;

import java.util.HashMap;
import java.util.Map;

import java002.test06.Command;
import java002.test06.Score;
import java002.test06.ScoreDao;

public class ExitCommand implements Command{

	@Override
	public String getCommandInfo() {
		return "exit";
	}

	@Override
	public void service(HashMap<String, Object> params) throws Exception {
		ScoreDao scoreDao = (ScoreDao)params.get("scoreDao");
	
		try{
			scoreDao.save();
		}catch(Exception e){
			System.out.println("데이터 저장 중 오류 발생");
		}
}

}
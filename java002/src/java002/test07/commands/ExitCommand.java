package java002.test07.commands;

import java.util.HashMap;

import java002.test07.Command;
import java002.test07.ScoreDao;
import java002.test07.annotation.Component;

@Component("exit")
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
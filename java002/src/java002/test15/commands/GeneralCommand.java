package java002.test15.commands;

import java.util.HashMap;

import java002.test15.ScoreDao;
import java002.test15.annotation.Command;
import java002.test15.annotation.Component;

@Component
public class GeneralCommand {
	ScoreDao scoreDao;

	public void setScoreDao(ScoreDao scoreDao) {
		this.scoreDao = scoreDao;
	}

	@Command("exit")
	public void exitTest(HashMap<String, Object> params) throws Exception {

		try {
			scoreDao.save();
		} catch (Exception e) {
			System.out.println("데이터 저장 중 오류 발생");
		}
	}

	@Command("help")
	public void helpTest(HashMap<String, Object> params) throws Exception {
		System.out.println("list");
		System.out.println("view 인덱스");
		System.out.println("add 이름 국어 영어 수학");
		System.out.println("delete 인덱스");
		System.out.println("update 인덱스");
		System.out.println("exit");
	}

}

package java002.test09.commands;

import java.util.HashMap;
import java002.test09.Score;
import java002.test09.ScoreDao;
import java002.test09.annotation.Command;
import java002.test09.annotation.Component;

@Component("list")
public class ListCommand {
	//총괄 관리자로부터 의존 객체를 주입받고 싶으면 setter method를 준비하라.
	ScoreDao scoreDao;

	//이 메서드는 Test01이 호출한다.
	public void setScoreDao(ScoreDao scoreDao) {
		this.scoreDao = scoreDao;
	}

	@Command
	public void listTest(HashMap<String, Object> params) throws Exception {
		//이 메서드가 호출되기 전에 ScoreDao 의존 객체가 저장 될 것이기 때문에 다음 코드는 제거한다.
		//ScoreDao scoreDao = (ScoreDao) params.get("scoreDao");

		int index = 0;
		for (Score score : scoreDao.getList()) {
			System.out.printf("%-3d %-10s %3d %3d %3d\n", index,
					score.getName(), score.getKor(), score.getEng(),
					score.getMath());
			index++;
		}
	}
}

package java002.test05.commands;

import java.util.HashMap;

import java002.test06.Command;
import java002.test06.Score;
import java002.test06.ScoreDao;

public class ListCommand implements Command {

	@Override
	public String getCommandInfo() {
		return "list";
	}

	@Override
	public void service(HashMap<String, Object> params) throws Exception {
		ScoreDao scoreDao = (ScoreDao) params.get("scoreDao");

		int index = 0;
		for (Score score : scoreDao.getList()) {
			System.out.printf("%-3d %-10s %3d %3d %3d\n", index,
					score.getName(), score.getKor(), score.getEng(),
					score.getMath());
			index++;
		}
	}
}

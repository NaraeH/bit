package java002.test20.server.command;

import java.util.HashMap;

import java002.test20.server.MemberDao;
import java002.test20.server.annotation.Command;
import java002.test20.server.annotation.Component;

@Component
public class GeneralCommand {
	MemberDao memberDao;

	public void setScoreDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Command("exit")
	public void exitTest(HashMap<String, Object> params) throws Exception {
			System.out.println("안녕히 가세요");
	}

	@Command("help")
	public void helpTest(HashMap<String, Object> params) throws Exception {
		System.out.println("list");
		System.out.println("view 제품번호");
		System.out.println("add");
		System.out.println("delete 제품번호");
		System.out.println("update 제품번호");
		System.out.println("exit");
	}

}

package java002.test06.commands;

import java.util.HashMap;
import java002.test06.Command;

public class TestCommand implements Command{

	@Override
	public String getCommandInfo() {
		return "hello";
	}

	@Override
	public void service(HashMap<String, Object> params) throws Exception {
		System.out.println("안녕하세요");
	}
}

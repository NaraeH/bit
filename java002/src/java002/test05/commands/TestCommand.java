package java002.test05.commands;

import java.util.ArrayList;
import java.util.Map;
import java002.test05.Command;

public class TestCommand implements Command{

	@Override
	public String getCommandInfo() {
		return "test";
	}

	@Override
	public void service(Map<String, Object> params) throws Exception {
		ArrayList<String> option = (ArrayList<String>)params.get("options");
		
		System.out.println(option.get(0) + "님 안녕하세요");
}

}
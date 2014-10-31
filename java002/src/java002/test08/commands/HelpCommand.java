package java002.test08.commands;

import java.util.HashMap;

import java002.test08.Command;
import java002.test08.annotation.Component;
@Component("help")
public class HelpCommand implements Command{


	@Override
	public String getCommandInfo() {
		return "help";
	}

	@Override
	public void service(HashMap<String, Object> params) throws Exception {
		System.out.println("list");
	    System.out.println("view 인덱스");
	    System.out.println("add 이름 국어 영어 수학");
	    System.out.println("delete 인덱스");
	    System.out.println("update 인덱스");
	    System.out.println("exit");
	    }

}

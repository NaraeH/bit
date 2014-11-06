package java002.test09.commands;

import java.util.HashMap;
import java002.test09.annotation.Command;
import java002.test09.annotation.Component;
@Component("help")
public class HelpCommand {
	
	@Command
	public void helpTest(HashMap<String, Object> params) throws Exception {
		System.out.println("list");
	    System.out.println("view 인덱스");
	    System.out.println("add 이름 국어 영어 수학");
	    System.out.println("delete 인덱스");
	    System.out.println("update 인덱스");
	    System.out.println("exit");
	    }

}

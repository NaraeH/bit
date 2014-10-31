package java002.test07;

import java.util.HashMap;

public interface Command {
	String getCommandInfo();
	
	void service(HashMap<String, Object> params) throws Exception;

}

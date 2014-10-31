package java002.test09;

import java.util.HashMap;

public interface Command {
	String getCommandInfo();
	
	void service(HashMap<String, Object> params) throws Exception;

}

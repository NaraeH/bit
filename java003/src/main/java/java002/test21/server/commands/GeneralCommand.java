package java002.test21.server.commands;

import java.io.PrintStream;
import java.util.HashMap;

import java002.test21.server.annotation.Command;
import java002.test21.server.dao.ProductDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeneralCommand {
	@Autowired
	ProductDao productDao;

	@Command("help")
	public void helpTest(HashMap<String, Object> params) throws Exception {
		PrintStream out = (PrintStream)params.get("out");
		
		out.println("list");
		out.println("view 제품번호");
		out.println("add");
		out.println("delete 제품번호");
		out.println("update 제품번호");
		out.println(); //항상 출력의 끝은 빈라인으로 보낸다.
	}

}

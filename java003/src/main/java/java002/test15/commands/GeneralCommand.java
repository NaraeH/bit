package java002.test15.commands;

import java.util.HashMap;

import java002.test15.ProductDao;
import java002.test15.annotation.Command;
import java002.test15.annotation.Component;

@Component
public class GeneralCommand {
	ProductDao productDao;

	public void setScoreDao(ProductDao productDao) {
		this.productDao = productDao;
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

package java002.test19.server.commands;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java002.test19.server.Product;
import java002.test19.server.ProductDao;
import java002.test19.server.annotation.Command;
import java002.test19.server.annotation.Component;

@Component("product")
public class ProductCommand {
	ProductDao productDao;
	Scanner scanner;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
//	public void setScanner(Scanner scanner) {
//		this.scanner = scanner;
//	}
	
	@Command("product/add")
	public void add(Map<String, Object> params){
		PrintStream out = (PrintStream)params.get("out");

		try{
			Product product = new Product();

			product.setName((String)params.get("name"));
			product.setQuantity(Integer.parseInt((String)params.get("qty")));
			product.setMakerNo(Integer.parseInt((String)params.get("mkno")));

			productDao.insert(product);
			out.println("저장하였습니다.");
			out.println();
		}catch(Exception e){
			e.printStackTrace();
			out.println("서버가 바쁩니다. 잠시 후 다시시도하세요.");
			out.println();
		}
	}


	@Command("product/delete")
	public void delete(HashMap<String, Object> params){
		PrintStream out = (PrintStream)params.get("out");
		Product product  = productDao.selectOne(Integer.parseInt((String)params.get("no")));

		if(product == null){
			out.println("해당 인덱스의 성적정보를 찾을 수 없습니다.");
			return;
		}
		
		productDao.delete(Integer.parseInt((String)params.get("no")));
		out.println("삭제되었습니다.");
		out.println();

	}

	@Command("product/list")
	public void list(HashMap<String, Object> params){
		PrintStream out = (PrintStream)params.get("out");

		int pageNo = 0;
		int pageSize = 0;


		if(params.get("pageNo") != null){
			pageNo = Integer.parseInt((String)params.get("pageNo"));
			pageSize = 3;
		}

		if(params.get("pageSize") != null){
			pageSize = Integer.parseInt((String)params.get("pageSize"));
		}

		for (Product product : productDao.selectList(pageNo, pageSize)) {
			out.printf("%-3d %-20s %7d %-3d\n",
					product.getNo(),
					product.getName(),
					product.getQuantity(),
					product.getMakerNo());
		}
		out.println(); //입력의 끝
	}

	@Command("product/update")
	public void update(HashMap<String, Object> params){
		PrintStream out = (PrintStream)params.get("out");
		Product product = productDao.selectOne(Integer.parseInt((String)params.get("no")));
		
		if(product == null){
			System.out.println("해당 인덱스의 데이터를 찾을 수 없습니다.");
			return;
		}

		product.setName((String)params.get("name"));;
		product.setQuantity(Integer.parseInt((String)params.get("qty")));
		product.setMakerNo(Integer.parseInt((String)params.get("mkno")));
		
		productDao.update(product);
		out.println("변경되었습니다.");

		out.println();
	}

	@Command("product/view")
	public void view(HashMap<String, Object> params) {
		PrintStream out = (PrintStream)params.get("out");
		
		int no = Integer.parseInt((String)params.get("no"));

		Product product = productDao.selectOne(no);

		if (product == null) {
			out.println("해당 번호의 성적정보를 찾을 수 없습니다.");
			return;
		}

		out.println("제품번호: " + product.getNo());
		out.println("제품명: " + product.getName());
		out.println("수량: " + product.getQuantity());
		out.println("제조사번호: " + product.getMakerNo());
		
		out.println();
	}
}

package java002.test17.commands;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java002.test17.Product;
import java002.test17.ProductDao;
import java002.test17.annotation.Command;
import java002.test17.annotation.Component;

@Component
public class ProductCommand {
	ProductDao productDao;
	Scanner scanner;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
	
	private HashMap<String, String> parseQueryString(String query){
		//ex) name=제품&qty=20&mkno=6
		// ==> {name=제품, qty=20, mkno=6}
		String[] entryList = query.split("&");
		HashMap<String, String> tempMap = new HashMap<String, String>();

		String[] token= null;
		for(String entry:entryList){
			token = entry.split("=");
			tempMap.put(token[0], token[1]);
		}
		return tempMap;
	}

	@Command("add")
	public void add(Map<String, Object> params){
		PrintStream out = (PrintStream)params.get("out");
		ArrayList<String> options = (ArrayList<String>)params.get("options");

		try{
			HashMap<String,String> valueMap = parseQueryString(options.get(0));
			Product product = new Product();

			product.setName(valueMap.get("name"));
			product.setQuantity(Integer.parseInt(valueMap.get("qty")));
			product.setMakerNo(Integer.parseInt(valueMap.get("mkno")));

			productDao.insert(product);
			out.println("저장하였습니다.");
			out.println();
		}catch(Exception e){
			e.printStackTrace();
			out.println("서버가 바쁩니다. 잠시 후 다시시도하세요.");
			out.println();
		}
	}


	@Command("delete")
	public void delete(HashMap<String, Object> params){
		ArrayList<String> options = (ArrayList<String>)params.get("options");
		PrintStream out = (PrintStream)params.get("out");
		//int no = Integer.parseInt(options.get(0));
		
		HashMap<String, String> valueMap = parseQueryString(options.get(0));

		Product product  = productDao.selectOne(Integer.parseInt(valueMap.get("no")));

		if(product == null){
			out.println("해당 인덱스의 성적정보를 찾을 수 없습니다.");
			return;
		}
		
		productDao.delete(Integer.parseInt(valueMap.get("no")));
		out.println("삭제되었습니다.");
		out.println();

/*		System.out.print(product.getName() + "의 성적을 삭제하시겠습니까?(y/n)");
		if (scanner.nextLine().equalsIgnoreCase("y")) {
			productDao.delete(no);
			System.out.println("삭제하였습니다.");
		} else {
			System.out.println("삭제 취소하였습니다.");
		}*/
	}

	@Command("list")
	public void list(HashMap<String, Object> params){
		ArrayList<String> options = (ArrayList<String>)params.get("options");
		PrintStream out = (PrintStream)params.get("out");
		
		int pageNo = 0;
		int pageSize = 0;
		if(!options.isEmpty()){

			HashMap<String, String> valueMap = parseQueryString(options.get(0));

			if(valueMap.size() > 0){
				pageNo = Integer.parseInt(valueMap.get("pageNo"));
				pageSize = 3;
			}

			if(valueMap.size() > 1){
				pageSize = Integer.parseInt(valueMap.get("pageSize"));
			}
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

	@Command("update")
	public void update(HashMap<String, Object> params){
		PrintStream out = (PrintStream)params.get("out");
		ArrayList<String> options = (ArrayList<String>)params.get("options");
		//System.out.println("===>" + options.get(0));
		
		HashMap<String, String> valueMap = parseQueryString(options.get(0));

		Product product = productDao.selectOne(Integer.parseInt(valueMap.get("no")));
		//System.out.println(Integer.parseInt(valueMap.get("no")));
		
		if(product == null){
			System.out.println("해당 인덱스의 데이터를 찾을 수 없습니다.");
			return;
		}

		Product tempProduct = null;
		try {
			tempProduct = product.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException();
		}

		tempProduct.setName(valueMap.get("name"));;
		tempProduct.setQuantity(Integer.parseInt(valueMap.get("qty")));
		tempProduct.setMakerNo(Integer.parseInt(valueMap.get("mkno")));
		
		productDao.update(tempProduct);
		out.println("변경되었습니다.");

		out.println();
/*		System.out.print("정말 변경하시겠습니까?(y/n)");
		if (scanner.nextLine().equalsIgnoreCase("y")) {
			productDao.update(tempProduct);
			System.out.println("변경하였습니다.");
		} else {
			System.out.println("변경 취소하였습니다.");
		}
*/		

	}

	@Command("view")
	public void view(HashMap<String, Object> params) {
		ArrayList<String> options = (ArrayList<String>) params.get("options");
		PrintStream out = (PrintStream)params.get("out");
		
		int no = Integer.parseInt(options.get(0));

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

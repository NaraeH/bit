package java002.test15.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java002.test15.Product;
import java002.test15.ProductDao;
import java002.test15.annotation.Command;
import java002.test15.annotation.Component;

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

	@Command("add")
	public void add(Map<String, Object> params){
		Product product = new Product();

		System.out.print("제품명:");
		product.setName(scanner.nextLine());

		System.out.print("수량:");
		product.setQuantity((scanner.nextInt()));

		System.out.print("제조사 번호:");
		product.setMakerNo(scanner.nextInt());

		productDao.insert(product);
		System.out.println("저장하였습니다.");
	}


	@Command("delete")
	public void delete(HashMap<String, Object> params){
		ArrayList<String> options = (ArrayList<String>)params.get("options");
		int no = Integer.parseInt(options.get(0));

		Product product  = productDao.selectOne(no);

		if(product == null){
			System.out.println("해당 인덱스의 성적정보를 찾을 수 없습니다.");
			return;
		}

		System.out.print(product.getName() + "의 성적을 삭제하시겠습니까?(y/n)");
		if (scanner.nextLine().equalsIgnoreCase("y")) {
			productDao.delete(no);
			System.out.println("삭제하였습니다.");
		} else {
			System.out.println("삭제 취소하였습니다.");
		}
	}

	@Command("list")
	public void list(HashMap<String, Object> params){
		ArrayList<String> options = (ArrayList<String>)params.get("options");
		//int no = Integer.parseInt(options.get(0));
		
		int pageNo = 0;
		int pageSize = 0;
		
		if(options.size() > 0){
			pageNo = Integer.parseInt(options.get(0));
			pageSize = 3;
		}
		
		if(options.size() > 1){
			pageSize = Integer.parseInt(options.get(1));
		}
		
		System.out.println("no: " + pageNo);
		System.out.println("pageSize: " + pageSize);

		for (Product product : productDao.selectList(pageNo, pageSize)) {
			System.out.printf("%-3d %-20s %7d %-3d\n",
					product.getNo(),
					product.getName(),
					product.getQuantity(),
					product.getMakerNo());
		}
	}

	@Command("update")
	public void update(HashMap<String, Object> params){
		ArrayList<String> options = (ArrayList<String>)params.get("options");
		int no = Integer.parseInt(options.get(0));

		Product product = productDao.selectOne(no);
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

		String text = null;
		System.out.printf("제품명(%s):", product.getName());
		text = scanner.nextLine();
		if (text.length() > 0)
			tempProduct.setName(text);

		System.out.printf("수량(%d):", product.getQuantity());
		text = scanner.nextLine();
		if (text.length() > 0)
			tempProduct.setQuantity(Integer.parseInt(text));

		System.out.printf("제조사번호(%d):", product.getMakerNo());
		text = scanner.nextLine();
		if (text.length() > 0)
			tempProduct.setMakerNo(Integer.parseInt(text)); 

		System.out.print("정말 변경하시겠습니까?(y/n)");
		if (scanner.nextLine().equalsIgnoreCase("y")) {
			productDao.update(tempProduct);
			System.out.println("변경하였습니다.");
		} else {
			System.out.println("변경 취소하였습니다.");
		}

	}

	@Command("view")
	public void view(HashMap<String, Object> params) {
		ArrayList<String> options = (ArrayList<String>) params.get("options");
		int no = Integer.parseInt(options.get(0));

		Product product = productDao.selectOne(no);

		if (product == null) {
			System.out.println("해당 번호의 성적정보를 찾을 수 없습니다.");
			return;
		}

		System.out.println("제품번호: " + product.getNo());
		System.out.println("제품명: " + product.getName());
		System.out.println("수량: " + product.getQuantity());
		System.out.println("제조사번호: " + product.getMakerNo());
	}
}

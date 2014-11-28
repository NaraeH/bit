package java63.web03.control;

import java.util.HashMap;
import java.util.Map;
import java63.web03.dao.ProductDao;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("/product/list.do")
public class ProductListControl{
	static final int PAGE_DEFAULT_SIZE = 3;  //변하지 않는 값이고, 만약 3을 대입한다면 눈에 확연히 보이지 않으므로 final상수로 설정해준다.

	@Autowired
	ProductDao productDao;

	public String excute(HttpServletRequest request) throws Exception {
		int pageNo = 0;
		int pageSize = 0;

		if(request.getParameter("pageNo") != null){
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
			pageSize = PAGE_DEFAULT_SIZE;
		}

		if(request.getParameter("pageSize") != null){
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startNo", (pageNo - 1) * pageSize);
		params.put("pageSize", pageSize);

		request.setAttribute("products", productDao.selectList(params));

		return "/product/productList.jsp";
	}
}
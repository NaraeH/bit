/*
 페이징 처리
 => DBMS마다 처리하는 방법이 다르다.
 
 문법: limit 시작인덱스, 몇개를 가져와야 할지
 */
package java002.test21.server.dao;

import java.util.HashMap;
import java.util.List;
import java002.test21.server.domain.Product;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDao {
	@Autowired
	SqlSessionFactory sqlSessionFactory;

	public ProductDao() {}

	public Product selectOne(int no) {
		//sqlSessionFactory.openSession()에서 argument를 "true"로 주면 auto commit이다.
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try{
			return sqlSession.selectOne(
					"java002.test21.server.dao.ProductDao.selectOne", no);
		}finally {
			sqlSession.close();
		}
	}

	public List<Product> selectList(int pageNo, int pageSize) {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("startNo", (pageNo - 1) * pageSize);
		paramMap.put("pageSize", pageSize);
		
		try{
		//selectList parameter1: namespace+id
		//selectList parameter2: SQL문을 실행할 때 필요한 값 전달 => xml에서 parameterType에 매칭되어야 함
		return sqlSession.selectList(
				"java002.test21.server.dao.ProductDao.selectList",
				paramMap);
		}finally {
			sqlSession.close();
		}
	}

	public void insert(Product product){
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try{
			sqlSession.insert(
					"java002.test21.server.dao.ProductDao.insert", product);
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}

	
	public void update(Product product) {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try{
			sqlSession.update(
					"java002.test21.server.dao.ProductDao.update", product);
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}

	public void delete(int no) {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try{
			sqlSession.delete(
					"java002.test21.server.dao.ProductDao.delete", no);
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}
	
/*public static void main(String[] args) {
	ProductDao dao = new ProductDao();

	Product p = new Product();
	//p.setNo(16);
	p.setName("구글독스");
	p.setQuantity(1);
	p.setMakerNo(1);
	
	dao.insert(p);
	//dao.update(p);
	
	List<Product> list = dao.selectList();
	
	for(Product product:list){
		System.out.println(product);
	}
}*/
}

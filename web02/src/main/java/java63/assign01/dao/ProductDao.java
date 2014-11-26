package java63.assign01.dao;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import java63.assign01.domain.Product;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Component;

@Component
public class ProductDao {
	SqlSessionFactory sqlSessionFactory;
	public ProductDao() {}
	
	public void init() throws Exception{
		String resource = "java63/assign01/dao/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	public Product selectOne(int no) throws Exception{
		init();
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try{
			return sqlSession.selectOne(
					"java63.assign01.dao.ProductDao.selectOne", no);
		}finally {
			sqlSession.close();
		}
	}

	public List<Product> selectList(int pageNo, int pageSize) throws Exception{
		init();
		SqlSession sqlSession = sqlSessionFactory.openSession();

		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("startNo", (pageNo - 1) * pageSize);
		paramMap.put("pageSize", pageSize);
		
		try{
		//selectList parameter1: namespace+id
		//selectList parameter2: SQL문을 실행할 때 필요한 값 전달 => xml에서 parameterType에 매칭되어야 함
		return sqlSession.selectList(
				"java63.assign01.dao.ProductDao.selectList",
				paramMap);
		}finally {
			sqlSession.close();
		}
	}

	public void insert(Product product) throws Exception{
		init();
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try{
			sqlSession.insert(
					"java63.assign01.dao.ProductDao.insert", product);
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}

	
	public void update(Product product) throws Exception{
		init();
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try{
			sqlSession.update(
					"java63.assign01.dao.ProductDao.update", product);
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}

	public void delete(int no) throws Exception{
		init();
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try{
			sqlSession.delete(
					"java63.assign01.dao.ProductDao.delete", no);
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}
}
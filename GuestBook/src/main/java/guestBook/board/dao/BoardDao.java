package guestBook.board.dao;

import guestBook.board.domain.Board;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BoardDao {
	@Autowired
	SqlSessionFactory sqlSessionFactory;

	public BoardDao() {}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public List<Board> selectList(int pageNo, int pageSize) {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("startNo", (pageNo - 1) * pageSize);
		paramMap.put("pageSize", pageSize);
		
		try{
		return sqlSession.selectList(
				"guestBook.board.dao.BoardDao.selectList",
				paramMap);
		}finally {
			sqlSession.close();
		}
	}
	
	public Board selectOne(int no) {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try{
			return sqlSession.selectOne(
					"guestBook.board.dao.BoardDao.selectOne", no);
		}finally {
			sqlSession.close();
		}
	}


	public void insert(Board board){
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try{
			sqlSession.insert(
					"guestBook.board.dao.BoardDao.insert", board);
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}

	
	public void update(Board board) {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try{
			sqlSession.update(
					"guestBook.board.dao.BoardDao.update", board);
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}

	public void delete(int no) {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try{
			sqlSession.delete(
					"guestBook.board.dao.BoardDao.delete", no);
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}

}

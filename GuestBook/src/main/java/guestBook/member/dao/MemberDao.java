package guestBook.member.dao;

import guestBook.member.domain.Member;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberDao {
	
	@Autowired
	SqlSessionFactory sqlSessionFactory;

	public MemberDao() {}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public List<Member> selectList(int pageNo, int pageSize) {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("startNo", (pageNo - 1) * pageSize);
		paramMap.put("pageSize", pageSize);
		
		try{
		return sqlSession.selectList(
				"guestBook.member.dao.MemberDao.selectList", paramMap);
		}finally {
			sqlSession.close();
		}
	}
	
	public Member selectOne(int no) {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try{
			return sqlSession.selectOne(
					"guestBook.member.dao.MemberDao.selectOne", no);
		}finally {
			sqlSession.close();
		}
	}


	/*public void insert(Member member){
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try{
			sqlSession.insert(
					"guestBook.member.domain.Member.insert", member);
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}*/

	
	/*public void update(Member member) {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try{
			sqlSession.update(
					"guestBook.member.domain.Member.update", member);
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}*/

/*	public void delete(int no) {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try{
			sqlSession.delete(
					"guestBook.member.domain.Member.delete", no);
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}*/

}

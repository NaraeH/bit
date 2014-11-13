package java002.test20.server;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class MemberDao {
	SqlSessionFactory sqlSessionFactory;

	public MemberDao() {}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public static Member selectOne(String id) {
		//SqlSession sqlSession = sqlSessionFactory.openSession();
		System.out.println("selectOne");
		return null;
		/*Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studydb", "study", "study");
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT uid, uname, pwd, email, tel, fax, det_addr, phot, ano  FROM MEMBERS"
									+ " WHERE uid=" + "'" + id + "'");
			
			if(rs.next()){
				Member member = new Member();
				member.setId(rs.getString("UID"));
				member.setName(rs.getString("UNAME"));
				member.setEmail(rs.getString("EMAIL"));
				member.setTel(rs.getString("TEL"));
				member.setPax(rs.getString("FAX"));
				member.setDatAddrs(rs.getString("det_addr"));
				member.setPhoto(rs.getString("PHOT"));
				member.setNo(rs.getInt("ANO"));
				return member;
			}else{
				return null;
			}
			
		}catch (Exception ex){
			//RuntimeException을 던지면 받는 곳에서 굳이 try/catch나 throws를 할 필요가 없다.
			throw new RuntimeException(ex);
		}finally {
			try {rs.close();} catch (Exception e){}
			try {stmt.close();} catch (Exception e){}
			try {con.close();} catch (Exception e){}
		}*/
	}

	public static List<Member> selectList(int pageNo, int pageSize) {
		System.out.println("selectList");
		return null;
		/*Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Member> list = new ArrayList<Member>(); 
		Member member = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studydb", "study", "study");
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT uid, uname, pwd, email, tel, fax, det_addr, phot, ano  FROM MEMBERS");
			
			while(rs.next()){
				member = new Member();
				member.setId(rs.getString("UID"));
				member.setName(rs.getString("UNAME"));
				member.setEmail(rs.getString("EMAIL"));
				member.setTel(rs.getString("TEL"));
				member.setPax(rs.getString("FAX"));
				member.setDatAddrs(rs.getString("det_addr"));
				member.setPhoto(rs.getString("PHOT"));
				member.setNo(rs.getInt("ANO"));
				
				list.add(member);
			}
			return list;
			
		}catch (Exception ex){
			//RuntimeException을 던지면 받는 곳에서 굳이 try/catch나 throws를 할 필요가 없다.
			throw new RuntimeException(ex);
		}finally {
			try {rs.close();} catch (Exception e){}
			try {stmt.close();} catch (Exception e){}
			try {con.close();} catch (Exception e){}
		}*/
	}

	public void insert(Member member){
		System.out.println("insert");
		/*Connection con = null;
		Statement stmt = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studydb", "study", "study");
			stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO MEMBERS(uid, uname, pwd, email, tel, fax, det_addr, phot, ano)"
					+ " Values('" + member.getId() + "', '"	
					+ member.getName() + "', '"
					+ member.getPw() +"', '"
					+ member.getEmail() +"', '"
					+ member.getTel() +"', '"
					+ member.getPax() +"', '"
					+ member.getDatAddrs() +"', '"
					+ member.getPhoto() + "', "
					+ member.getNo() + ")" );
			
		}catch (Exception ex){
			ex.printStackTrace();
			throw  new RuntimeException();
		}finally {
			try {stmt.close();} catch (Exception e){}
			try {con.close();} catch (Exception e){}
		}*/
	}
	
	public void update(Member member) {
		System.out.println("update");
		/*
		Connection con = null;
		Statement stmt = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studydb" + "?useUnicode=true&characterEncoding=utf8", "study", "study");
			stmt = con.createStatement();
			stmt.executeUpdate("UPDATE MEMBERS SET"
					+ " PWD= " + "'" + member.getPw() +"'"
					+ ", EMAIL= " + "'" + member.getEmail() +"'"
					+ ", UNAME= " + "'"+ member.getName() +"'"
					+ ", TEL= " + "'" + member.getTel() +"'"
					+ ", FAX= " + "'" + member.getPax()+"'"
					+ ", DET_ADDR= " + "'" + member.datAddrs +"'"
					+ ", PHOT= " + "'" +member.getPhoto() +"'"
					+ " WHERE UID=" + "'" + member.getId() + "'");
			
		}catch (Exception ex){
			ex.printStackTrace();
			throw new RuntimeException();
		}finally {
			try {stmt.close();} catch (Exception e){}
			try {con.close();} catch (Exception e){}
		}*/
	}

	public void delete(String id) {
		System.out.println("delete");
		/*Connection con = null;
		Statement stmt = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studydb", "study", "study");
			stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM MEMBERS WHERE uid='"
					+ id + "'");
			
		}catch (Exception ex){
			ex.getStackTrace();
			throw new RuntimeException();
		}finally {
			try {stmt.close();} catch (Exception e){}
			try {con.close();} catch (Exception e){}
		}*/
	}

}


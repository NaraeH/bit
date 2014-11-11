package java002.test16;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java002.test16.MemberDao;

public class MemberDao {

	public MemberDao() {}

	public static Member selectOne(String id) {
		Connection con = null;
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
		}
	}

	public static List<Member> selectList() {
		Connection con = null;
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
		}
	}

	public void insert(Member member){
		Connection con = null;
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
		}
	}
	
	public void update(Member member) {
		
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
		}
	}

	public void delete(String id) {
		Connection con = null;
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
		}
	}
	
public static void main(String[] args) {
/*	ProductDao dao = new ProductDao();

	Product p = new Product();
	//p.setNo(16);
	p.setName("구글독스");
	p.setQuantity(1);
	p.setMakerNo(1);
	
	dao.insert(p);
	//dao.update(p);
	
	List<Product> list = dao.selectList();
	
	for(Product product:list){
		System.out.println(product);*/
	
	/*MemberDao dao = new MemberDao();
	//List<Member> list = dao.selectList();

	for(Member member:list){
		System.out.println(member);
	}
	Member m = new Member();
	//m.setId("u13");
	m.setName("zzzz");
	m.setPw("1111");
	m.setEmail("1111");
	m.setTel("1111");
	m.setPax("1");
	m.setDatAddrs("1");
	
	dao.update(m);
	
	List<Member> list = dao.selectList();
	
	for(Member member:list){
	System.out.println(member);*/
	MemberDao dao = new MemberDao();
	dao.delete("u12");
}


}


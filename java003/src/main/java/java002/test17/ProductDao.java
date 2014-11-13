/*
 페이징 처리
 => DBMS마다 처리하는 방법이 다르다.
 
 문법: limit 시작인덱스, 몇개를 가져와야 할지
 */
package java002.test17;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java002.test17.server.util.DBConnectionPool;

public class ProductDao {
	DBConnectionPool dbConnectionPool;

	public ProductDao() {}

	public void setDbConnectionPool(DBConnectionPool dbConnectionPool) {
		this.dbConnectionPool = dbConnectionPool;
	}


	public Product selectOne(int no) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			con = dbConnectionPool.getConnection();
			/*Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studydb", "study", "study");*/
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT pno, pname, qty, mkno FROM PRODUCTS"
									+ " WHERE pno=" + no);
			
			if(rs.next()){
				Product product = new Product();
				product.setNo(rs.getInt("PNO"));
				product.setName(rs.getString("PNAME"));
				product.setQuantity(rs.getInt("QTY"));
				product.setMakerNo(rs.getInt("MKNO"));
				return product;
			}else{
				return null;
			}
			
		}catch (Exception ex){
			//RuntimeException을 던지면 받는 곳에서 굳이 try/catch나 throws를 할 필요가 없다.
			throw new RuntimeException(ex);
		}finally {
			try {rs.close();} catch (Exception e){}
			try {stmt.close();} catch (Exception e){}
			dbConnectionPool.restrunConnection(con);
		}
	}

	public List<Product> selectList(int pageNo, int pageSize) {
		//System.out.println("zzzz");

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Product> list = new ArrayList<Product>(); 
		Product product = null;
		
		try{
			con = dbConnectionPool.getConnection();
/*			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studydb", "study", "study");*/

			stmt = con.createStatement();
			
			String sql = "SELECT pno, pname, qty, mkno FROM PRODUCTS";
			if(pageSize > 0){
				sql += " limit " + (pageNo - 1) * pageSize + ", " + pageSize; 
			}
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				product = new Product();
				product.setNo(rs.getInt("PNO"));
				product.setName(rs.getString("PNAME"));
				product.setQuantity(rs.getInt("QTY"));
				product.setMakerNo(rs.getInt("MKNO"));

				list.add(product);
			}
			return list;
			
		}catch (Exception ex){
			//RuntimeException을 던지면 받는 곳에서 굳이 try/catch나 throws를 할 필요가 없다.
			throw new RuntimeException(ex);
		}finally {
			try {rs.close();} catch (Exception e){}
			try {stmt.close();} catch (Exception e){}
			dbConnectionPool.restrunConnection(con);
		}
	}

	public void insert(Product product){
		Connection con = null;
		PreparedStatement stmt = null;
		
		try{
			con = dbConnectionPool.getConnection();
			/*Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studydb", "study", "study");*/
			stmt = con.prepareStatement("INSERT INTO PRODUCTS(pname, qty, mkno) Values(?,?,?)");
			
			//용어정리: ?를 in-parameter라고 부른다.
			//인파라미터의 인덱스는 1부터 시작한다.
			//순서대로 설정할 필요는 없지만, 프로그래밍의 일관성을 위해 순서대로 입력하라!
			stmt.setString(1, product.getName() );
			stmt.setInt(2, product.getQuantity() );
			stmt.setInt(3, product.getMakerNo() );
			
			stmt.executeUpdate();
			
		}catch (Exception ex){
			ex.printStackTrace();
			throw  new RuntimeException();
		}finally {
			try {stmt.close();} catch (Exception e){}
			dbConnectionPool.restrunConnection(con);
		}
	}
	
	public void update(Product product) {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try{
			con = dbConnectionPool.getConnection();
			/*Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studydb" + "?useUnicode=true&characterEncoding=utf8", "study", "study");
			*/
			stmt = con.prepareStatement("UPDATE PRODUCTS SET PNAME=?, QTY=?,MKNO=? WHERE PNO=?");
			stmt.setString(1, product.getName());
			stmt.setInt(2, product.getQuantity());
			stmt.setInt(3, product.getMakerNo());
			stmt.setInt(4, product.getNo());
			stmt.executeUpdate();
			
		}catch (Exception ex){
			ex.printStackTrace();
			throw new RuntimeException();
		}finally {
			try {stmt.close();} catch (Exception e){}
			dbConnectionPool.restrunConnection(con);
		}
	}

	public void delete(int no) {
		Connection con = null;
		Statement stmt = null;
		
		try{
			con = dbConnectionPool.getConnection();
			/*Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studydb" + "?useUnicode=true&characterEncoding=utf8", "study", "study");
			*/stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM products WHERE pno="
					+ no);
			
		}catch (Exception ex){
			ex.getStackTrace();
			throw new RuntimeException();
		}finally {
			try {stmt.close();} catch (Exception e){}
			dbConnectionPool.restrunConnection(con);
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

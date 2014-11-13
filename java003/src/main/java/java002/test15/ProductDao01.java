/*
 PreparedStatment사용하기
 기존의 createStatment와 다른점: SQL문을 미리 준비하여 입력 값을 파라미터로 전달한다.
 장점: 1) 서버에 SQL문을 보내기 전에 한번만 컴파일한다.
             만약, 같은 SQL문을 한번에 여러개 실행하는 경우에는 속도가 빠른다.
      2) 입력값을 파라미터로 전달하기 때문에 바이너리 데이터(ex) 이미지 파일) 입력 가능
      3) 코딩이 간결하다.
 */
package java002.test15;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDao01 {

	public ProductDao01() {}

	public Product selectOne(int no) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studydb", "study", "study");
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
			try {con.close();} catch (Exception e){}
		}
	}

	public List<Product> selectList() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Product> list = new ArrayList<Product>(); 
		Product product = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studydb", "study", "study");
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT pno, pname, qty, mkno FROM PRODUCTS");
			
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
			try {con.close();} catch (Exception e){}
		}
	}

	public void insert(Product product){
		Connection con = null;
		PreparedStatement stmt = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studydb", "study", "study");
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
			try {con.close();} catch (Exception e){}
		}
	}
	
	public void update(Product product) {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studydb" + "?useUnicode=true&characterEncoding=utf8", "study", "study");
			
			stmt = con.prepareStatement("UPDATE PRODUCTS SET PNAME=?, QTY=? WHERE PNO=?");
			stmt.setString(1, product.getName());
			stmt.setInt(2, product.getQuantity());
			stmt.setInt(3, product.getNo());
			stmt.executeUpdate();
			
		}catch (Exception ex){
			ex.printStackTrace();
			throw new RuntimeException();
		}finally {
			try {stmt.close();} catch (Exception e){}
			try {con.close();} catch (Exception e){}
		}
	}

	public void delete(int no) {
		Connection con = null;
		Statement stmt = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studydb" + "?useUnicode=true&characterEncoding=utf8", "study", "study");
			stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM products WHERE pno="
					+ no);
			
		}catch (Exception ex){
			ex.getStackTrace();
			throw new RuntimeException();
		}finally {
			try {stmt.close();} catch (Exception e){}
			try {con.close();} catch (Exception e){}
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

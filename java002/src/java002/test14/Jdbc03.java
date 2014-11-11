/*
 <statement 객체얻기>
  => DBMS에 SQL문을 보내는 역할을 수행한다.
  => java.sql.Connection 구현체를 통해서 얻을 수 있다.
*/
package java002.test14;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Jdbc03 {
	public static void main(String[] args){
		Connection con = null;
		Statement stmt = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBC Driver이 성공하였습니다.");

			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/studydb", "study", "study");
			System.out.println("DBMS에 연결되었습니다.");
			
			//3. Statement객체 얻기
			stmt = (Statement)con.createStatement();
			System.out.println("Statement 객체 준비완료");
			
		}catch (Exception ex){
			ex.getStackTrace();
		}finally {
			try {stmt.close();} catch (Exception e){}
			System.out.println("Stmentment의 자원을 해재하였습니다.");
			
			try {con.close();} catch (Exception e){}
			System.out.println("DBMS에 연결이 끊어졌습니다.");
		}
	}

}

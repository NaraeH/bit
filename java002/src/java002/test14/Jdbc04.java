/*
 <SQL문 실행하기>
 excuteQuery(SELECT문)
 excuteUpdate(INSERT/DELETE/UPDATE문)
 execute(ALL);  => 값 꺼낼 때 불편하므로 위에 두개를 사용하는 것이 편함
*/
package java002.test14;

import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.Statement;

public class Jdbc04 {
	public static void main(String[] args){
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBC Driver이 성공하였습니다.");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studydb", "study", "study");
			System.out.println("DBMS에 연결되었습니다.");
			
			//3. Statement객체 얻기
			stmt = con.createStatement();
			System.out.println("Statement 객체 준비완료");
			
			//4. SQL문 실행하기 - select
			//return: server에서 결과를 하나씩 가져오는 역할자를 리턴 ==> java.sql.ResultSet 구현체를 리턴
			rs = stmt.executeQuery("SELECT * FROM PRODUCTS");
			System.out.println("서버에 질의 완료. ResultSet 준비 완료 되었습니다.");
			
		}catch (Exception ex){
			ex.getStackTrace();
		}finally {
			try {rs.close();} catch (Exception e){}
			System.out.println("ResultSet의 자원을 해제하였습니다.");
			
			try {stmt.close();} catch (Exception e){}
			System.out.println("Stmentment의 자원을 해제하였습니다.");
			
			try {con.close();} catch (Exception e){}
			System.out.println("DBMS에 연결이 끊어졌습니다.");
		}
	}

}

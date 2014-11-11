/*
 <update실행>
 => 리눅스에서는 한글 값이 깨질 수 있다.
   해결책: 연결 정보에 문자집합을 설정해야한다.
    JDBC URL에 설정한다.
     ex) jdbc:mysql://localhost:port/schema?useUnicode=true&characterEncoding=utf8

*/
package java002.test14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Jdbc08 {
	public static void main(String[] args){
		Connection con = null;
		Statement stmt = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBC Driver이 성공하였습니다.");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studydb" + "?useUnicode=true&characterEncoding=utf8", "study", "study");
			System.out.println("DBMS에 연결되었습니다.");
			
			stmt = con.createStatement();
			System.out.println("Statement 객체 준비완료");
			
			stmt.executeUpdate("UPDATE PRODUCTS SET PNAME='넥서스9', QTY=90 WHERE PNO=11");
			System.out.println("데이터 변경 완료");
			
			
		}catch (Exception ex){
			ex.getStackTrace();
		}finally {
			try {stmt.close();} catch (Exception e){}
			System.out.println("Stmentment의 자원을 해제하였습니다.");
			
			try {con.close();} catch (Exception e){}
			System.out.println("DBMS에 연결이 끊어졌습니다.");
		}
	}

}

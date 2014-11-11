/*
 <JDBC Connection 객체얻기>
 => java.sql.Driver 구현체를 통해서 얻을 수 있다.
 => DriverManager에게 부탁하면 DriverManager가 우리를 대신하여 java.sql.Driver객체에게 요구한다.
*/
package java002.test14;

import java.sql.DriverManager;
import com.mysql.jdbc.Connection;

public class Jdbc02 {
	//이 interface를 구현한 어떤 객체
	public static void main(String[] args){
		Connection con = null;
		
		try{
			//1. java.sql.Driver 구현체 로딩한다.
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBC Driver이 성공하였습니다.");

			//2. DriverManaver에게 Connection 객체를 부탁한다.
			//arguments1: jdbc 접속을 위한 url정보
			//arguments2: 사용자 아이디
			//arguments2: 사용자 암호
			//con: mysql에 접속한 객체가 리턴 됨
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/studydb", "study", "study");
			System.out.println("DBMS에 연결되었습니다.");
			
		}catch (Exception ex){
			ex.getStackTrace();
		}finally {
			try {con.close();} catch (Exception e){}
			System.out.println("DBMS에 연결이 끊어졌습니다.");
		}
	}

}

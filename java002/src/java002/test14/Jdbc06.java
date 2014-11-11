/*
 <ResultSet객체를 통해 서버에서 결과를 갖고오기>
 getXXX(컬럼인덱스) 사용
 컬럼인덱스는 1부터 시작한다.
 
 * 문제점: 인덱스는 코드를 해석 하기 어렵게 만든다.
 *       SELECT * from 테이블명 => 컬럼 선택에 와일드카드(*)을 사용하면 실행 속도가 느리다.
 *                               (가능하면 정확하게 컬럼을 지정하여라.)

*/
package java002.test14;

import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.Statement;

public class Jdbc06 {
	public static void main(String[] args){
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBC Driver이 성공하였습니다.");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studydb", "study", "study");
			System.out.println("DBMS에 연결되었습니다.");
			
			stmt = con.createStatement();
			System.out.println("Statement 객체 준비완료");
			
			//4. SQL문 실행하기 - select
			//return: server에서 결과를 하나씩 가져오는 역할자를 리턴 ==> java.sql.ResultSet 구현체를 리턴
			rs = stmt.executeQuery("SELECT * FROM PRODUCTS");
			System.out.println("서버에 질의 완료. ResultSet 준비 완료 되었습니다.");
			
			//5. 결과 가져오기
			// => 현재 결과를 가져온 상태가 아님!
			while(rs.next()){
				//결과를 가져왔다면 데이터는 ResultSet객체에 들어 있다.
				System.out.print(rs.getInt(1) + ",");
				System.out.print(rs.getString(2) + ",");
				System.out.print(rs.getInt(3) + ",");
				System.out.println(rs.getInt(4));
			}
			
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

/*
 <JDBC driver loading>
 JDBC 드라이버 정보를 담은 메인 클래스 로딩
 java.sql.Driver 인터페이스를 구현한 클래스를 로딩해야 한다.
  이 클래스를 Connection 객체를 얻을 수 있다. => 일종의 점조직(쟤를 알기위해서는 쟤2를 알아야한다.) 같은 형태다.
*/
package java002.test14;

import java.sql.DriverManager;

public class Jdbc01 {
	public static void main(String[] args) throws Exception{
		//방법1: Class.forName사용하기
		Class.forName("com.mysql.jdbc.Driver");
		
		//방법2: Driver 클래스의 instance를 만든 후에 명시적으로 등록까지 마치기
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		//방법2의 단점: 다른 DBMS로 접속하려면 코드를 수정하여 한다.
	}

}

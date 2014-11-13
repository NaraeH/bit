package java002.test18.server.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class DBConnectionPool {
	String driver;
	String url;
	String userName;
	String password;
	
	ArrayList<Connection> conList = new ArrayList<>();

	public DBConnectionPool(String driver, String url, String userName,
			String password) throws Exception {
		super();
		this.driver = driver;
		this.url = url;
		this.userName = userName;
		this.password = password;
		
		Class.forName(driver);
	}
	
	public Connection getConnection() throws Exception{
		if(conList.size() > 0){
			return conList.remove(0);
		}else{
			return DriverManager.getConnection(url, userName, password);
		}
	}
	
	public void restrunConnection(Connection con) {
		try{
			if(!con.isClosed()){
				conList.add(con);
			}
		}catch(Exception e){}
	}
	
	public void closeAll() {
		for (Connection con : conList) {
			try { con.close(); } catch (Exception e) {}
		}
	}

}

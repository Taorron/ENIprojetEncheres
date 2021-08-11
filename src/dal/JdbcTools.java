package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTools {

	private static String driverdb;
	private static String urldb;
	private static String userdb;
	private static String passworddb;
	
	static {
		driverdb = Settings.getProperty("driverdb");
		urldb = Settings.getProperty("urldb");
		userdb = Settings.getProperty("userdb");
		passworddb = Settings.getProperty("passworddb");
		
		System.out.println("driverdb="+driverdb);
		System.out.println("urldb="+urldb);
		System.out.println("userdb="+userdb);
		System.out.println("passworddb="+passworddb);
	}

	public static Connection getConnection() {
		// TODO Auto-generated method stub
		
		 Connection connexion = null;
		try {
			Class.forName(driverdb);
			connexion = DriverManager.getConnection(urldb,userdb,passworddb);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return connexion;
	}

}

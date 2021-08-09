package bo;

import java.sql.Connection;

import dal.JdbcTools;

public class ClassTest {

	public String maVar;
	
	Connection connection = JdbcTools.getConnection();
	
}

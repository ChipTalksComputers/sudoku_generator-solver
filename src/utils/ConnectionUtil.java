package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {
	Connection conn = null;
	
	public static Connection conDB() {

		try {
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://sudoku-login.c9tacr6bnl85.us-west-2.rds.amazonaws.com:3306/login_data", "AWSRDS", "Sudoku_login");
			System.out.println("Success!");
			return con;
		} catch (Exception e) {
			System.out.println("Fail");
			e.printStackTrace();
			return null;
		}
	}
}


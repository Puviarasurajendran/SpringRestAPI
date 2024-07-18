package com.zoho.spring.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//@PropertySource("classpath:application.properties")
public class CustomersDBConnectionModel {

	private static String url ="jdbc:mysql://localhost:3306/sys";
	private static String username="root";
	private static String password="password";
	private static String driver="com.mysql.cj.jdbc.Driver";

//	@Value("${db.url}")
//	private static String url ;
//
//	@Value("${db.username}")
//	private static String username;
//
//	@Value("${db.password}")
//	private static String password;
//
//	@Value("${db.driver}")
//	private static String driver;


    static {
        try {
        	System.out.println("url :"+url+" username"+ username);
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load JDBC driver");
        }
    }

    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

	public static void closeConnection(Connection con) {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

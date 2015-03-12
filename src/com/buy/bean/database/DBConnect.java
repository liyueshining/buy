package com.buy.bean.database;

import java.sql.Connection;
import java.sql.DriverManager;

import com.buy.bean.comm.database.Config;

public class DBConnect {
	
	public static Connection getConnection(){
		Connection conn = null;
		 try {
			Class.forName(Config.CONNECTION_DRIVER).newInstance();
			conn = DriverManager.getConnection(Config.CONNECTION_URL,Config.CONNECTION_USER,Config.CONNECTION_PWD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 if (conn == null) {
			System.out.println("no get connection! throws exception");
		}
		 
		 return conn;
	}

}

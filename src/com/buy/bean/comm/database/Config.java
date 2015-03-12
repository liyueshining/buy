package com.buy.bean.comm.database;

import java.io.IOException;
import java.util.Properties;

public class Config {

	private static Properties prop = new Properties();
	
	static{
		try {
			prop.load(Config.class.getResourceAsStream("/config.properties"));
		} catch (IOException e) {
			System.out.println("file:config.properties not found,pls check out");
			e.printStackTrace();
		}		
	}
	
	public static String CONNECTION_TYPE=prop.getProperty("conn_type");
	public static String CONNECTION_URL=prop.getProperty("conn_url");
	public static String CONNECTION_USER=prop.getProperty("conn_user");
	public static String CONNECTION_PWD=prop.getProperty("conn_pwd");
	public static String CONNECTION_DRIVER=prop.getProperty("conn_driver");

}

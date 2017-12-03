package com.github.jees5555.huidaoBookShop.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DaoUtils {
	//6种读取方式 1 静态读取   2  资源绑定读取
	private static Properties p=new Properties();
	public static int pagesize;
	static{
		
		try {
			p.load(DaoUtils.class.getClassLoader().getResourceAsStream("database.properties"));
			pagesize=Integer.parseInt( p.getProperty("jdbc.fenye"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private DaoUtils(){}
		
	public static Connection getContection() throws Exception{
		Class.forName(p.getProperty("jdbc.driver_class"));

		return DriverManager.getConnection(p.getProperty("jdbc.connection.url"),p.getProperty("jdbc.connection.username"),p.getProperty("jdbc.connection.password"));
	}
	
	
	public static void closed(ResultSet rs,PreparedStatement ps,Connection con) throws SQLException{
			if(rs!=null){
				rs.close();
			}
			if(ps!=null){
				ps.close();
			}
			if(con!=null){
				con.close();
			}
	}
	
}

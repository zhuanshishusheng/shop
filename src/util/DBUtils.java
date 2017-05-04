package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {
	public static Connection getConn() {
	/*	Properties p = new Properties();
		try {
			p.load(is);
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/

		String driverclass = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql:///shop";
		String user = "root";
		String password = "123456";
		Connection conn = null;

		try {
			Class.forName(/* "com.mysql.jdbc.Driver" */driverclass);
			conn = DriverManager.getConnection(url, user, password);
		    System.out.println("数据库连接成功");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void closeAll(ResultSet rs,PreparedStatement ps,Connection conn) throws SQLException{
		if(rs!=null)
			rs.close();
		if(ps!=null){
			ps.close();
		if(conn!=null)
			conn.close();
		}
	}

	

}

package dynamic_beat_17.common;

import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DAO {
	protected Connection conn;
	protected Statement stmt;
	protected PreparedStatement pstmt;
	protected ResultSet rs;
	
	public static Connection getConnect() {
		///////////////////
		String user = "";
		String pw = "";
		String url = "";
		///////////////////
		Properties prop = new Properties();
		String path = DAO.class.getResource("../../config/database.properties").getPath();
		try {
			path = URLDecoder.decode(path, "utf-8");
			prop.load(new FileReader(path));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String driver = prop.getProperty("driver");
		url = prop.getProperty("url");
		user = prop.getProperty("user");
		pw = prop.getProperty("passwd");

		//////////////////
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pw);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public static void close(Connection conn) {
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}

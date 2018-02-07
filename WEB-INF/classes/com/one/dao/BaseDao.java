package com.one.dao;

import java.sql.*;

/*加载数据库连接和关闭资源的*/
public class BaseDao {
	// 加载驱动
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 连接数据库
	public static Connection getConnction() {
		try {
			return DriverManager.getConnection("jdbc:mysql:///weibo", "weibo", "123456");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 关闭资源，注意关闭顺序，先打开的后关闭
	public static void close(Connection conn, PreparedStatement pst, ResultSet rs) {

		// 判断数连接不为空，那么关闭
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // 判断数连接不为空，那么关闭
		if (pst != null) {
			try {
				pst.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 判断数据集不为空，那么关闭
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}

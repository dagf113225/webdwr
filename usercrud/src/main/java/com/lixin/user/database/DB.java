package com.lixin.user.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {

	Connection conn;

	// 连接数据库
	public DB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/thzmdb2", "root", "123456");

			System.out.println("连接数据库:" + conn);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 到数据库的表中检查用户是否合法
	public boolean checkLogin(String name, String pwd) {

		// 1.定义sql
		String sql = "SELECT  COUNT(*) FROM  t_emp  WHERE ename=?  AND  epwd=?";

		try {
			// 2. 执行sql句柄对象PreparedStatement conn.conn()
			PreparedStatement stmt = conn.prepareStatement(sql);

			// 3.设置传入参数，下标是从1开始的
			stmt.setString(1, name);
			stmt.setString(2, pwd);

			// 4. 你的sql是select----------->executeQuery()
			// ResultSet是个结果集，表示查询的结果
			ResultSet rs = stmt.executeQuery();

			// 5.这个结果集一定要循环遍历
			// rx.next() 返回true,就代表有结果集，进入循环体
			while (rs.next()) {
				int count = rs.getInt(1);
				System.out.println("--------->" + count);

				if (count > 0) {
					return true;
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public boolean checkName(String name) {
		String sql = "SELECT  COUNT(*) FROM  t_emp  WHERE ename=?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int count = rs.getInt(1);

				if (count > 0) {
					return true;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	// 获取菜单的名称数据
	public String[] getDBMenu() {
		String sql = "SELECT  tname FROM t_menu";

		String[] datas = null;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			int count = 0;

			while (rs.next()) {
				++count;
			}

			datas = new String[count];

			// mysql的结果集rs默认是可以滚动的
			rs.beforeFirst();

			int row = 0;
			while (rs.next()) {
				datas[row++] = rs.getString(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datas;
	}

}

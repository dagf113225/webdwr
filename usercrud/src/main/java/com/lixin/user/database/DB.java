package com.lixin.user.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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

	// 查询班级的名称和id
	public Object[][] getClassNames() {
		String sql = "SELECT  cid,cname  FROM  t_classes";

		Object[][] datas = null;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();

			int column = rsmd.getColumnCount();

			int count = 0;

			while (rs.next()) {
				count++;
			}

			// 完成二维数组创建，实例化
			datas = new Object[count][column];

			// 结果集回到第一行
			rs.beforeFirst();

			int row = 0;

			while (rs.next())// 控制的是行
			{
				for (int i = 1; i <= column; i++)// 控制的是列
				{
					datas[row][i - 1] = rs.getObject(i);
				}
				row++;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datas;

	}

	// 传入的是班级的编号得到学生名称
	public String[] getClassToStuName(String id) {
		String sql = "SELECT  sname  FROM  t_stus  WHERE  scid=?";

		String[] names = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();

			int count = 0;

			while (rs.next()) {
				count++;
			}
			names = new String[count];

			rs.beforeFirst();

			int row = 0;
			while (rs.next()) {
				names[row++] = rs.getString(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return names;

	}

	public Object[][] getGroupSex() {
		String sql = "SELECT ssex,COUNT(*) FROM t_stus  GROUP  BY ssex";

		Object[][] datas = null;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();

			int column = rsmd.getColumnCount();

			int count = 0;

			while (rs.next()) {
				count++;
			}

			// 完成二维数组创建，实例化
			datas = new Object[count][column];

			// 结果集回到第一行
			rs.beforeFirst();

			int row = 0;

			while (rs.next())// 控制的是行
			{
				for (int i = 1; i <= column; i++)// 控制的是列
				{
					datas[row][i - 1] = rs.getObject(i);
				}
				row++;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datas;

	}

	public Object[][] getStuAllInfo() {
		String sql = "SELECT   sname,ssex,sphone,sqq,stuimg,studesc FROM  t_stus";

		Object[][] datas = null;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();

			int column = rsmd.getColumnCount();

			int count = 0;

			while (rs.next()) {
				count++;
			}

			// 完成二维数组创建，实例化
			datas = new Object[count][column];

			// 结果集回到第一行
			rs.beforeFirst();

			int row = 0;

			while (rs.next())// 控制的是行
			{
				for (int i = 0; i < column; i++)// 控制的是列
				{
					datas[row][i] = rs.getObject(i + 1);
				}
				row++;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datas;
	}

}

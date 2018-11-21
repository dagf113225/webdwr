package com.lixin.user.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {

	Connection conn;

	// �������ݿ�
	public DB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/thzmdb2", "root", "123456");

			System.out.println("�������ݿ�:" + conn);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// �����ݿ�ı��м���û��Ƿ�Ϸ�
	public boolean checkLogin(String name, String pwd) {

		// 1.����sql
		String sql = "SELECT  COUNT(*) FROM  t_emp  WHERE ename=?  AND  epwd=?";

		try {
			// 2. ִ��sql�������PreparedStatement conn.conn()
			PreparedStatement stmt = conn.prepareStatement(sql);

			// 3.���ô���������±��Ǵ�1��ʼ��
			stmt.setString(1, name);
			stmt.setString(2, pwd);

			// 4. ���sql��select----------->executeQuery()
			// ResultSet�Ǹ����������ʾ��ѯ�Ľ��
			ResultSet rs = stmt.executeQuery();

			// 5.��������һ��Ҫѭ������
			// rx.next() ����true,�ʹ����н����������ѭ����
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

	// ��ȡ�˵�����������
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

			// mysql�Ľ����rsĬ���ǿ��Թ�����
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

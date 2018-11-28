package com.lixin.user.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.lixin.interfaces.FiexdValue;
import com.lixin.model.CMenu;
import com.lixin.model.FMenu;

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

	// ��ѯ�༶�����ƺ�id
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

			// ��ɶ�ά���鴴����ʵ����
			datas = new Object[count][column];

			// ������ص���һ��
			rs.beforeFirst();

			int row = 0;

			while (rs.next())// ���Ƶ�����
			{
				for (int i = 1; i <= column; i++)// ���Ƶ�����
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

	// ������ǰ༶�ı�ŵõ�ѧ������
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

			// ��ɶ�ά���鴴����ʵ����
			datas = new Object[count][column];

			// ������ص���һ��
			rs.beforeFirst();

			int row = 0;

			while (rs.next())// ���Ƶ�����
			{
				for (int i = 1; i <= column; i++)// ���Ƶ�����
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

			// ��ɶ�ά���鴴����ʵ����
			datas = new Object[count][column];

			// ������ص���һ��
			rs.beforeFirst();

			int row = 0;

			while (rs.next())// ���Ƶ�����
			{
				for (int i = 0; i < column; i++)// ���Ƶ�����
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

	public FMenu[] getMenuDatas() {
		String sql = "SELECT  *  FROM  t_fmenu";

		FMenu[] fmenus = null;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			int count = 0;
			while (rs.next()) {
				count++;
			}

			fmenus = new FMenu[count];

			rs.beforeFirst();

			int row = 0;
			while (rs.next()) {
				FMenu fm = new FMenu();
				fm.setFid(rs.getInt(1));
				fm.setFname(rs.getString(2));
				fm.setCmenus(getCmenuDatas(rs.getInt(1)));
				fmenus[row++] = fm;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fmenus;
	}

	public CMenu[] getCmenuDatas(int id) {
		String sql = "SELECT  cid ,cname,fcid,url   FROM  t_cmenu   WHERE fcid=?";
		CMenu[] cmenus = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			int count = 0;

			while (rs.next()) {
				count++;
			}
			cmenus = new CMenu[count];
			rs.beforeFirst();
			int row = 0;
			while (rs.next()) {
				CMenu cmenu = new CMenu();
				cmenu.setCid(rs.getInt(1));
				cmenu.setCname(rs.getString(2));
				cmenu.setFcid(rs.getShort(3));

				String url = rs.getString(4);

				if ("".equals(url) || null == url) {
					cmenu.setUrl("default.html");
				} else {
					cmenu.setUrl(url);
				}

				cmenus[row++] = cmenu;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cmenus;
	}

	public int getSumCount() {
		String sql = "SELECT  COUNT(*) FROM  t_stus";

		int count = 0;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;

	}

	public Object[][] getTablePageDatas(int PageNum) {
		String sql = "SELECT  sname,ssex,sphone,sqq,stuimg  FROM t_stus LIMIT  ?,?";
		Object[][] datas = null;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, (PageNum - 1) * FiexdValue.PAGE_NUMBER);
			pstmt.setInt(2, FiexdValue.PAGE_NUMBER);

			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();

			int column = rsmd.getColumnCount();

			int count = 0;

			while (rs.next()) {
				count++;
			}

			// ��ɶ�ά���鴴����ʵ����
			datas = new Object[count][column];

			// ������ص���һ��
			rs.beforeFirst();

			int row = 0;

			while (rs.next())// ���Ƶ�����
			{
				for (int i = 0; i < column; i++)// ���Ƶ�����
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

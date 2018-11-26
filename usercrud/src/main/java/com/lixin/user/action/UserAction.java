package com.lixin.user.action;

import com.lixin.user.database.DB;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserAction {

	// ����û���¼
	public String checkUserLogin(String name, String pwd) {
		System.out.println("UserAction  is  checkUserLogin  start... ");

		System.out.println(name + "," + pwd);

		// ����db�����
		DB db = new DB();

		boolean flag = db.checkLogin(name, pwd);
		if (flag) {
			return "�ɹ�";
		}

		return "�û���¼ʧ��";
	}

	// ����û����Ƿ�Ψһ
	public String checkUserName(String name) {
		System.out.println("UserAction  is  checkUserName  start... ");
		System.out.println("------------>" + name);
		DB db = new DB();

		boolean flag = db.checkName(name);
		if (flag) {
			return "fail";
		} else {
			return "success";
		}

	}

	// ���ɶ�̬����˵�
	public String[] getMenuDatas() {
		System.out.println("UserAction  is  getMenuDatas  start... ");

		DB db = new DB();

		String[] menuDatas = db.getDBMenu();

		for (String datas : menuDatas) {
			System.out.println(datas);
		}

		return menuDatas;
	}

	// ��������ѡ��������
	public Object[][] getClassNames() {
		System.out.println("UserAction  is  getClassNames  start... ");

		DB db = new DB();

		Object[][] datas = db.getClassNames();

		return datas;
	}

	// ���ݰ༶�ı�Ż�ȡ����༶��ѧ��������
	public String[] getClassBindStus(String id) {
		System.out.println("UserAction  is  getClassBindStus  start... ");

		DB db = new DB();
		String[] names = db.getClassToStuName(id);

		return names;

	}

	// [[],[]]
	public String getAntvGroupSex() {
		System.out.println("UserAction  is  getAntvGroupSex  start... ");

		DB db = new DB();

		Object[][] datas = db.getGroupSex();

		// [[],[],[]]
		/**
		 * Ů3 
		 * ��7
		 */
		// [{��:ֵ},{��:ֵ},{��:ֵ}]
		// json���ݸ�ʽ[] json����,{}json����{��:ֵ}
		// ������������
		JSONArray array = new JSONArray();

		for (int i = 0; i < datas.length; i++) {

			// �����ﴴ��JSONObject����--->��ֵ
			JSONObject jsonObj = new JSONObject();
			
			jsonObj.put("sex", datas[i][0]);
			jsonObj.put("number", datas[i][1]);
			
			//��ÿһ��json������뵽json������
			array.add(jsonObj);

		}
		System.out.println(array.toString());

		return array.toString();

	}

}

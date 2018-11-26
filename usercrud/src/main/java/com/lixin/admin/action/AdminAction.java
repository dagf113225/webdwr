package com.lixin.admin.action;

import com.lixin.user.database.DB;

public class AdminAction {

	public Object[][] getStuAllInfo() {
		System.out.println("AdminAction  is  getStuAllInfo  start...");

		DB db = new DB();

		Object[][] stuDatas = db.getStuAllInfo();

		return stuDatas;
	}

}

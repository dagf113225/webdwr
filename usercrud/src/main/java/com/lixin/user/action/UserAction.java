package com.lixin.user.action;

import com.lixin.interfaces.FiexdValue;
import com.lixin.model.FMenu;
import com.lixin.user.database.DB;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserAction {

	// 检查用户登录
	public String checkUserLogin(String name, String pwd) {
		System.out.println("UserAction  is  checkUserLogin  start... ");

		System.out.println(name + "," + pwd);

		// 产生db类对象
		DB db = new DB();

		boolean flag = db.checkLogin(name, pwd);
		if (flag) {
			return "成功";
		}

		return "用户登录失败";
	}

	// 检查用户名是否唯一
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

	// 生成动态标题菜单
	public String[] getMenuDatas() {
		System.out.println("UserAction  is  getMenuDatas  start... ");

		DB db = new DB();

		String[] menuDatas = db.getDBMenu();

		for (String datas : menuDatas) {
			System.out.println(datas);
		}

		return menuDatas;
	}

	// 生成下拉选择框的数据
	public Object[][] getClassNames() {
		System.out.println("UserAction  is  getClassNames  start... ");

		DB db = new DB();

		Object[][] datas = db.getClassNames();

		return datas;
	}

	// 根据班级的编号获取这个班级的学生的姓名
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
		 * 女3 男7
		 */
		// [{键:值},{键:值},{键:值}]
		// json数据格式[] json数组,{}json对象{键:值}
		// 数据容器倒换
		JSONArray array = new JSONArray();

		for (int i = 0; i < datas.length; i++) {

			// 在这里创建JSONObject对象--->键值
			JSONObject jsonObj = new JSONObject();

			jsonObj.put("sex", datas[i][0]);
			jsonObj.put("number", datas[i][1]);

			// 把每一个json对象放入到json数组中
			array.add(jsonObj);

		}
		System.out.println(array.toString());

		return array.toString();

	}

	public FMenu[] getMainMenuDatas() {
		System.out.println("UserAction  is  getMenuDatas  start... ");

		DB db = new DB();
		FMenu[] fmenus = db.getMenuDatas();

		return fmenus;
	}
	public Object[][]  getPageTableData(int  pageNum)
	{
		System.out.println("UserAction  is  getPageTableData  start... ");
		DB db = new DB();
		
		Object[][]  datas=db.getTablePageDatas(pageNum);
		return datas;
	}
	
	public  int[]   getTableInfo()
	{
		System.out.println("UserAction  is  getPageTableData  start... ");
		
		DB  db = new DB();
		//总共多少条
		int  count=db.getSumCount();
		
		//三元运算符 
		int  pageSize=count%FiexdValue.PAGE_NUMBER==0?count/FiexdValue.PAGE_NUMBER:count/FiexdValue.PAGE_NUMBER+1;
		
		int[]  datas = {count,pageSize};
		
		return datas;
	
	}

}

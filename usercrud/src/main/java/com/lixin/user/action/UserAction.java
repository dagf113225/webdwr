package com.lixin.user.action;

import com.lixin.user.database.DB;

public class UserAction {

	// 检查用户登录
	public String checkUserLogin(String name, String pwd) {
		System.out.println("UserAction  is  checkUserLogin  start... ");

		System.out.println(name + "," + pwd);
		
		//产生db类对象
		DB db  = new DB();
		boolean  flag=db.checkLogin(name, pwd);
		if(flag)
		{
			return "成功";
		}
		
		return "用户登录失败";
	}
	
	//检查用户名是否唯一
	public String  checkUserName(String name)
	{
		System.out.println("UserAction  is  checkUserName  start... ");
		System.out.println("------------>"+name);
		DB  db = new DB();
		
		boolean flag=db.checkName(name);
		if(flag)
		{
			return "fail";
		}
		else
		{
			return "success";
		}
		
		
	}

}

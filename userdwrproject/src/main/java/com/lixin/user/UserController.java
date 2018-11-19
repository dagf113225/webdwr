package com.lixin.user;

import com.lixin.user.codeImpls.UserChinaCode;
import com.lixin.user.codeImpls.UserNumAndCharCode;
import com.lixin.user.codeImpls.UserNumberCode;
import com.lixin.user.interfaces.UserUtils;

public class UserController {

	//为什么要声明接口对象?
	private UserUtils uc;

	public String getUserCode(String name) {

		if (name.equals("汉字")) {
			
			uc = new UserChinaCode();

		} else if (name.equals("数字")) {
			uc = new UserNumberCode();
		}
		else  if(name.equals("字母"))
		{
			
			uc = new UserNumAndCharCode();
			
		}
		

		return uc.createCode();
	}

}

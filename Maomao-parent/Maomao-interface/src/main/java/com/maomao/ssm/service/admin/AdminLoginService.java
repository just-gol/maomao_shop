package com.maomao.ssm.service.admin;

import com.maomao.ssm.pojo.JsonData;

/** 
* @author:wzy
* @descrption:
* @version:
* @date:2018年2月27日
*/

public interface AdminLoginService {
	JsonData login(String mobile,String password,Byte rember);
	JsonData getSmsCode(String mobile);
	JsonData checkSmsCode(String mobile ,String code);
	JsonData updatePassword (String mobile,String password,String token);
	JsonData logout(Integer userId);
	JsonData modifiedPassword(Integer userId,String oldPassword,String newPassword);
	JsonData getAccountInfo(Integer userId);
	JsonData getHomePage(String userId, String result);
}






























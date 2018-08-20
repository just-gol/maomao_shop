package com.maomao.ssm.constant;

/**
 * @author:wzy
 * @descrption:
 * @version:
 * @date:2018年2月27日
 */

public class AdminConst {
	public static final String AMDIN_LOGIN_SUCCESS = "admin:login:";// 登录成功
	public static final Integer ADMIN_LOGIN_COOKIE_TIME = 2592000;// 记住密码,保存cookie时间
	public static final Byte ADMIN_LOGIN_REMBER = 1;// 记住密码
	public static final String ADMIN_LOGIN_LIMIT = "admin:limit:user:";// 记住密码
	public static final String ADMIN_LOGIN_ERROR = "admin:login:error:user:";// 记住密码
	public static final Integer ADMIN_LOGIN_ERROR_TIME = 300;// 5分钟内密码连续错误6次
	public static final Integer ADMIN_ERROR_COUNT = 6;// 用户最大允许密码错误次数
	public static final Integer ADMIN_LOGIN_LIMIT_TIME = 1800;// 密码超过最大错误次数限制登录时间(秒)

	// 管理员状态 -1删除 0可用
	public static final Byte ADMIN_STATUS_DEL = -1;
	public static final Byte ADMIN_STATUS_NORMAL = 0;

}

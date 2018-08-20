package com.maomao.ssm.controller.intercept;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.maomao.ssm.constant.AdminConst;
import com.maomao.ssm.constant.StatusConst;
import com.maomao.ssm.pojo.JedisClientPool;
import com.maomao.ssm.service.admin.AccountService;
import com.maomao.ssm.service.admin.LogService;
import com.maomao.ssm.utils.CookieUtils;
/**
 * 管理员操作日志拦截器
 * @author Administrator
 *
 */
public class UserLogInterceptor implements HandlerInterceptor {
	public static final Logger logger = Logger.getLogger(UserLogInterceptor.class);
	@Autowired
	private JedisClientPool jedisClientPool;
	@Autowired
	private LogService logService;
	@Autowired
	private AccountService accountService;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handlerMethod)
			throws Exception {
		String methodName  = null;
		String adminId = null;
		try {
			String method = request.getMethod();
			if (method.equalsIgnoreCase("get")) {
				return true;
			}
			
			String cookie = CookieUtils.getCookieValue(request, "token", true);
			 if (StringUtils.isNotBlank(cookie)) {
				 adminId = jedisClientPool.hget(AdminConst.AMDIN_LOGIN_SUCCESS, cookie);//用户id
			 }
			 
		//	String url = request.getRequestURI();
			 
			HandlerMethod handler = (HandlerMethod) handlerMethod;
			 methodName = handler.getMethod().getName(); // 获取方法名
		//	 System.out.println("方法:"+methodName);
			 //方法名含get直接结束
			 if (methodName.contains("get")) {
				return true;
			}
			Byte type = getMethodType(methodName);
			StringBuilder builder = new StringBuilder();
			Map<String, String[]> paraMap = request.getParameterMap();
			Iterator<Entry<String, String[]>> it = paraMap.entrySet().iterator();
			while (it.hasNext()) {
				@SuppressWarnings("rawtypes")
				Map.Entry entry = (Entry) it.next();
				String mapKey = (String) entry.getKey();
				String mapValue = StringUtils.EMPTY;
				if (mapKey.equalsIgnoreCase("password")) {
					Object obj = entry.getValue();
					if (obj instanceof String[]) {
						String[] str = (String[]) obj;
					//	mapValue = Arrays.toString(str);
						mapValue = DigestUtils.md5DigestAsHex(Arrays.toString(str).getBytes());
					}
				}else if(mapKey.equalsIgnoreCase("mobile") && adminId == null){
					Object obj = entry.getValue();
						if (obj instanceof String[]) {
							String[] str = (String[]) obj;
							mapValue = str[0];
							Integer id = accountService.getAdminId(mapValue);
							adminId = id+"";
						}
				}else{
					Object obj = entry.getValue();
					if (obj instanceof String[]) {
						String[] str = (String[]) obj;
						mapValue = Arrays.toString(str);
					}
				}
				builder.append(mapKey).append("=").append(mapValue); //请求参数
			}
			
			Date date = new Date(); // 操作时间
			String ip = request.getRemoteAddr();// 获取用户的IP地址
			logService.addLog(Integer.parseInt(adminId), methodName, builder.toString(), ip, date, type);
		} catch (Exception e) {
			System.out.println("===log时间===:"+new Date()+"===方法===:"+methodName+"===出现异常==="+e.toString());
		}
			return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handlerMethod,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handlerMethod, Exception ex)
			throws Exception {
	}

	private Byte getMethodType(String methodName) {
		Byte type = 0;
		if (methodName.contains("del")) {
			type = StatusConst.LOG_DEL;
		}
		if (methodName.contains("add")) {
			type = StatusConst.LOG_ADD;
		}

		if (methodName.contains("update")) {
			type = StatusConst.LOG_UPDATE;
		}
		if (methodName.contains("login")) {//登陆
			type = StatusConst.LOG_LOGIN;
		}
		if (methodName.contains("goodsShelvest")) { // 申请下架
			type = StatusConst.LOG_UPDATE;
		}

		if (methodName.contains("goodsCancel")) {// 驳回下架
			type = StatusConst.LOG_UPDATE;
		}

		if (methodName.contains("saveItem")) {
			type = StatusConst.LOG_ADD;
		}

		if (methodName.contains("setItemOnSale")) {// 商品下架
			type = StatusConst.LOG_UPDATE;
		}
		
		if (methodName.contains("set")) {
			type = StatusConst.LOG_UPDATE;
		}

		if (methodName.contains("getShopApply")) {// 开店审核
			type = StatusConst.LOG_UPDATE;
		}
		if (methodName.contains("shopApproved")) {// 审核通过
			type = StatusConst.LOG_UPDATE;
		}
		if (methodName.contains("shopRefused")) {// 拒绝受理
			type = StatusConst.LOG_UPDATE;
		}
		if (methodName.contains("saveSubItem")) {// 添加认筹商品
			type = StatusConst.LOG_ADD;
		}
		if (methodName.contains("saveSubItemStatus")) {//处理认筹审核
			type = StatusConst.LOG_UPDATE;
		}
		if (methodName.contains("setCategoryStopById")) {//停用分类
			type = StatusConst.LOG_UPDATE;
		}
		if (methodName.contains("setCategoryStartById")) {//启动分类
			type = StatusConst.LOG_UPDATE;
		}
		if (methodName.contains("editCategoryById")) {//修改分类
			type = StatusConst.LOG_UPDATE;
		}
		if (methodName.contains("upSubpplyDetail")) {//上架商品
			type = StatusConst.LOG_UPDATE;
		}
		if (methodName.contains("getServicManagementList")) {//新增服务
			type = StatusConst.LOG_ADD;
		}
		if (methodName.contains("startServicManagement")) {//启动服务
			type = StatusConst.LOG_UPDATE;
		}
		if (methodName.contains("stopServicManagement")) {//禁用服务
			type = StatusConst.LOG_UPDATE;
		}
		if (methodName.contains("saveRecommend")) {//修改推荐位
			type = StatusConst.LOG_UPDATE;
		}
		if (methodName.contains("ResetUserPassword")) {//用户密码重置
			type = StatusConst.LOG_UPDATE;
		}
		if (methodName.contains("ResetAdminUserPassword")) {//管理员密码重置
			type = StatusConst.LOG_UPDATE;
		}
		if (methodName.contains("updatePassword")) {//重置密码
			type = StatusConst.LOG_UPDATE;
		}
		if (methodName.contains("logout")) {//用户退出
			type = StatusConst.LOG_OUT;
		}
		if (methodName.contains("modifiedPassword")) {//修改密码
			type = StatusConst.LOG_UPDATE;
		}
		if (methodName.contains("deleteRole")) {//删除角色
			type = StatusConst.LOG_UPDATE;
		}
		
		if (methodName.contains("deleteDataRole")) {//删除数据角色
			type = StatusConst.LOG_UPDATE;
		}
		return type;
	}
}

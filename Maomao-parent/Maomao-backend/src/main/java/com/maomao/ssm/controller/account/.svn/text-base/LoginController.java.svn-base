package com.maomao.ssm.controller.account;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maomao.ssm.bean.AdminInfo;
import com.maomao.ssm.bean.AdminInfoExample;
import com.maomao.ssm.constant.AdminConst;
import com.maomao.ssm.dao.AdminInfoMapper;
import com.maomao.ssm.pojo.JedisClientPool;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.AdminLoginService;
import com.maomao.ssm.utils.CookieUtils;
import com.maomao.ssm.utils.JsonUtils;

/**
 * @author:wzy
 * @descrption:
 * @version:
 * @date:2018年2月27日
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	private AdminLoginService adminLoginService;
	@Autowired
	private JedisClientPool jedisClientPool;

	
	/**
	 * 用户登录
	 */
	@RequestMapping("/login.action")
	@ResponseBody
	public JsonData login(String mobile, String password, Byte rember, HttpServletRequest request,
			HttpServletResponse response) {
		JsonData jsonData = adminLoginService.login(mobile, password, rember);
		if (jsonData.getCode() == 200) {
			@SuppressWarnings("unchecked")
			List<Object> returnList = (List<Object>) jsonData.getData();
			if (rember != null && rember == AdminConst.ADMIN_LOGIN_REMBER) {
				CookieUtils.setCookie(request, response, "token", returnList.get(0).toString(),
						AdminConst.ADMIN_LOGIN_COOKIE_TIME);
			} else {
				CookieUtils.setCookie(request, response, "token", returnList.get(0).toString(), 0);
			}

			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) returnList.get(1);
			return JsonData.setSuccessMessage(map);
		}

		return jsonData;
	}

	/**
	 * 获取图片验证码
	 */
	@RequestMapping("/randomIMG.action")
	public void randomIMG(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		int width = 100;
		int height = 40;
		Random random = new Random();
		// 设置response头信息
		// 禁止缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		// 生成缓冲区image类
		BufferedImage image = new BufferedImage(width, height, 1);
		// 产生image类的Graphics用于绘制操作
		Graphics g = image.getGraphics();
		// Graphics类的样式
		g.setColor(this.getRandColor(200, 250));
		g.setFont(new Font("Times New Roman", 0, 28));
		g.fillRect(0, 0, width, height);
		// 绘制干扰线
		for (int i = 0; i < 40; i++) {
			g.setColor(this.getRandColor(130, 200));
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int x1 = random.nextInt(12);
			int y1 = random.nextInt(12);
			g.drawLine(x, y, x + x1, y + y1);
		}

		// 绘制字符
		String strCode = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			strCode = strCode + rand;
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(rand, 13 * i + 6, 28);
		}
		// 将字符保存到session中用于前端的验证
		session.setAttribute("checkCode", strCode);
		g.dispose();

		ImageIO.write(image, "JPEG", response.getOutputStream());
		response.getOutputStream().flush();
	}

	// 创建颜色
	Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * 校验图片验证码
	 */
	@RequestMapping("/chekcIMG.action")
	@ResponseBody
	public JsonData checkIMG(String code, HttpSession session) {
		String checkCode = (String) session.getAttribute("checkCode");
		if (StringUtils.isBlank(code) || StringUtils.isBlank(checkCode) || !code.equalsIgnoreCase(checkCode)) {
			return JsonData.setErrorMessage("验证码错误");
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 发送短信验证码
	 */
	@RequestMapping("/getSmsCode.action")
	@ResponseBody
	public JsonData getSmsCode(String mobile) {
		return adminLoginService.getSmsCode(mobile);
	}

	/**
	 * 校验短信验证码
	 */
	@RequestMapping("/checkSmsCode.action")
	@ResponseBody
	public JsonData checkSmsCode(String mobile, String code) {
		return adminLoginService.checkSmsCode(mobile, code);
	}

	/**
	 * 忘记密码
	 */
	@RequestMapping("/updatePassword.action")
	@ResponseBody
	public JsonData updatePassword(String mobile, String password, String token) {
		return adminLoginService.updatePassword(mobile, password, token);
	}
	
	/**
	 * 首页信息
	 * author:wzy
	 */
	@RequestMapping("/getHomePage.action")
	@ResponseBody
	public JsonData getHomePage(HttpServletRequest request) {
		String cookie = CookieUtils.getCookieValue(request, "token", true);
		String userId = jedisClientPool.hget(AdminConst.AMDIN_LOGIN_SUCCESS, cookie);
		String userInfo = jedisClientPool.hget(AdminConst.AMDIN_LOGIN_SUCCESS + userId, "userInfo");
	//	Map map = JsonUtils.jsonToPojo(userInfo, Map.class);
	//	return JsonData.setSuccessMessage(map);
	//	System.out.println("userId:"+userId);
	//	System.out.println("userInfo:"+userInfo);
		
	//	JsonData result = JsonData.setSuccessMessage(JsonUtils.jsonToPojo(userInfo, Map.class));
	//	String userInfoResult = JsonUtils.objectToJson(result);
		
		return adminLoginService.getHomePage(userId,userInfo);
	}

	@RequestMapping("/logout.action")
	@ResponseBody
	public JsonData logout(Integer userId) {
		return adminLoginService.logout(userId);
	}

	@RequestMapping("/modifiedPassword.action")
	@ResponseBody
	public JsonData modifiedPassword(Integer userId, String oldPassword, String newPassword) {
		return adminLoginService.modifiedPassword(userId, oldPassword, newPassword);
	}

	/**
	 * 管理员基本信息 author:wzy
	 */
	@RequestMapping("/getAccountInfo.action")
	@ResponseBody
	public JsonData getAccountInfo(Integer userId) {
		return adminLoginService.getAccountInfo(userId);
	}
}

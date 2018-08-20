package com.maomao.ssm.constant;

/**
 * @author : wzy
 * @descrpition:redis相关常量配置
 */
public final class RedisConst {
	private RedisConst() {
	}

	public static final Integer CACHE_EXPIRE_TIME = 172800;// 缓存默认失效时间,2天

	public static final String LOGIN_SUCCESS = "login_success:";// 保存用户相关信息key
	public static final String LIMIT_USER = "limit_user:";// 保存用户相关信息key
	public static final String LOGIN_ERROR = "login_error:";// 保存用户登录错误信息field
	public static final Integer ERROR_COUNT = 6;// 用户最大允许密码错误次数
	public static final Integer LIMIT_TIME = 1800;// 密码超过最大错误次数限制登录时间(秒)
	public static final Integer ERROR_TIME = 300;// 5分钟内密码连续错误6次
	public static final Integer COOKIE_EXPIRE_TIME = 2592000;// cookie的过期时间(30天)

	public static final String SMS_CODE = "sms_code:";// 短信验证码key
	public static final Integer SMS_CODE_EXPIRE_TIME = 900;// 短信验证过期时间
	public static final String SMS_CODE_LIMIT = "sms_code_limit:";// 短信验证码发送间隔时间key
	public static final Integer SMS_CODE_LIMIT_TIME = 120;// 短信验证码发送间隔时间
	public static final String SMS_CODE_ERROR = "sms_code_error:";// 短信验证码错误key

	public static final String SMS_RANDOM_CODE = "sms_random_code:";// 随机码key
	public static final Integer SMS_RANDOM_CODE_LIMIT_TIME = 300;// 随机码有效时间
	public static final String HOME_DETAIL = "home_detail:";// 首页详情
	public static final String HOME_BANNER = "home_banner:";// 首页banner
	public static final String CATEGORY_DETAIL = "category_detail:";// 分类
	public static final String CATEGORY_DETAIL_V2 = "category_detail_v2:";// 分类
	public static final String CATEGORY_GOODS = "category_goods:";// 根据分类查询商品缓存
	
	
	
	
	

	public static final String GOODS_BRANDS = "goods_brand:";// 商品品牌
	public static final String AD_BANNER = "ad:";// 商品品牌

	public static final String ORDER_COMFIRM = "order_comfirm:";// 确认订单页面
	public static final Integer ORDER_COMFIRM_EXPIRE_TIME = 10;// 确认订单重复提交失效时间
	
	public static final String ORDER_PAY_INFO = "order_pay_info:";// 订单支付信息
	public static final Integer ORDER_PAY_INFO_EXPIRE_TIME = 1800;// 订单支付信息失效时间

	public static final String BANK_LIST = "bank_list:";// 银行列表

	// 商品详情缓存
	public static final String GOODS_NORMAL_DETAIL = "goods:detail:normal:";// 普通商品
	public static final String GOODS_SUBSCRIPTION_DETAIL = "goods:detail:subscription:";// 认筹商品详情

	// redis的key分隔符
	public static final String REDIS_KEY_SPLIT_STRING = ":";

	// 提现密码错误
	public static final String PASSWORD_WITHDRAWALS_ERROR = "password_withdrawals_error:";

	// 修改密码校验token前缀
	public static final String MODIFIED_PASSWORD_TOKEN = "modified_password_token:";

	// 全部权限列表
	public static final String PERMISSION_TREE_LIST = "permission_tree";

	// 消息通知
	public static final String NOTICE_GET_ALL = "notice_get_all";
	public static final Integer NOTICE_EXPIRE_TIME = 3300;

	// 优惠券发送
	public static final String COUPON_KEY = "coupon_key";
	public static final Integer COUPON_EXPIRE_TIME = 250;
}

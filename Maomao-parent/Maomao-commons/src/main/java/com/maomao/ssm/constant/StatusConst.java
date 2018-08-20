package com.maomao.ssm.constant;

/**
 * @author : wzy
 * @descrpition:相关状态常量配置
 */
public final class StatusConst {
	private StatusConst() {
	}
	public static final Byte BANNER_STATUS_HOME = 0;//banner在首页显示(广告位)
	public static final Byte BANNER_STATUS_CATEGORY = 1;//banner分类页
	public static final Byte BANNER_STATUS_START = 2;//banner在启动页
	public static final Byte BANNER_STATUS_ON=1;//banner启用
	public static final Byte BANNER_STATUS_OFF=0;//banner禁用
	public static final Byte BANNER_STATUS_DEL=-1;//banner删除
	
	
	public static final Byte CATEGORY_STATUS_ON=1;//商品分类启用
	public static final Byte CATEGORY_STATUS_OFF=0;//商品分类禁用
	public static final Byte CATEGORY_STATUS_DEL=-1;//商品分类删除
	public static final Byte CATEGORY_RECOMMEND_ON=1;//分类在首页推荐
	public static final Byte CATEGORY_RECOMMEND_OFF=0;//分类不在首页显示
	
	//首页推荐位内容状态
	public static final Byte HOME_CONTENT_STATUS_OFF=0;//首页内容禁用
	public static final Byte HOME_CONTENT_STATUS_ON=1;//首页内容启用
	public static final Byte HOME_CONTENT_STATUS_DEL=-1;//首页内容删除
	
	//首页推荐位分类状态
	public static final Byte HOME_CATEGORY_STATUS_OFF=0;//首页内容禁用
	public static final Byte HOME_CATEGORY_STATUS_ON=1;//首页内容启用
	public static final Byte HOME_CATEGORY_STATUS_DEL=-1;//首页内容删除
	
	//推荐位分类类型 0小图 1中图 2大图  3精选 4推荐 5店铺分享 6店铺压货
	public static final Byte HOMECATEGORY_IMG_TYPE_MIN=0;
	public static final Byte HOMECATEGORY_IMG_TYPE_MIDDLE=1;
	public static final Byte HOMECATEGORY_IMG_TYPE_MAX=2;
	public static final Byte HOMECATEGORY_IMG_TYPE_QUALITY=3;
	public static final Byte HOMECATEGORY_IMG_TYPE_RECOMMENDED=4;
	public static final Byte HOMECATEGORY_IMG_TYPE_SHARE=5;
	public static final Byte HOMECATEGORY_IMG_TYPE_PRESSUREGOODS=6;
	
	public static final Byte USER_TYPE_PERSONAL=0;//个人用户
	public static final Byte USER_TYPE_SHOP=1;//商家用户
	public static final Byte USER_STATSU_NOT_DEL=0;//用户未删除
	public static final Byte USER_STATSU_DEL=-1;//用户删除
	
	public static final Byte ADMIN_TYPE_SUPPLIER=1;//供应商
	public static final Byte ADMIN__TYPE_INTERNAL=0;//内部
	public static final Byte ADMIN_STATSU_NOT_DEL=0;//管理员未删除
	public static final Byte ADMIN_STATSU_DEL=-1;//管理员删除
	
	public static final Byte SEX_MEN=1;//男
	public static final Byte SEX_WOMEN=2;//女
	
	public static final Byte USER_APPLY_STATUS_IN=0;//申请调额审核中
	public static final Byte USER_APPLY_STATUS_REFUSE=1;//申请调额未通过
	public static final Byte USER_APPLY_STATUS_SUCCESS=2;//申请调额通过
	
	public static final String IMG_SPLIT_STRING=";";//图片路径分隔符
	
	public static final Byte NOTICE_RECORD_TYPE_SYSTEM_INFO=0;//系统公告
	public static final Byte NOTICE_RECORD_TYPE_SYSTEM_WULIU=1;//物流通知
	public static final Byte NOTICE_RECORD_TYPE_TRADE_INFO=0;//系统公告
	public static final Byte NOTICE_RECORD_TYPE_CONSUME=0;//系统公告
	public static final Byte NOTICE_RECORD_STATUS_READ_NO=0;//未读
	public static final Byte NOTICE_RECORD_STATUS_READ_YES=1;//已读
	public static final Byte NOTICE_RECORD_STATUS_DEL=-1;//删除
	public static final Byte NOTICE_RECORD_DEL_ALL=1;//删除全部已读
	
	public static final String GOODS_SERVICE_ID_SPLIT_STRING="|";//商品服务项分隔符
	
	public static final Byte USER_WITHDRAWALS_STATUS_IN = 0;//提现申请中
	public static final Byte USER_WITHDRAWALS_STATUS_ON = 1;//提现成功
	public static final Byte USER_WITHDRAWALS_STATUS_FAIL = 2;//提现失败
	public static final Byte USER_WITHDRAWALS_TYPE_ZHIFUBAO=0;//提现类型:支付宝
	public static final Byte USER_WITHDRAWALS_TYPE_WEIXIN=1;//提现类型:微信
	public static final Byte USER_WITHDRAWALS_TYPE_BANK=2;//提现类型:银行卡
	
	public static final Byte GOODS_USER_STATUS_OFF=2;//客户商品上架状态,下架
	public static final Byte GOODS_USER_STATUS_ON=1;//客户商品上架状态,上架
	public static final Byte GOODS_USER_STATUS_DEL=-1;//客户商品上架状态,删除
	
	public static final Byte IS_FENYE_YES=1;//是否分页 :是
	public static final Byte IS_FENYE_NO=0;//是否分页:否
	
	public static final Byte GOODS_SERVICE_STATUS_FORBIDEN=0;//商品服务项禁止
	public static final Byte GOODS_SERVICE_STATUS_ON=1;//商品服务项启动
	public static final Byte GOODS_SERVICE_STATUS_DELETE=-1;//商品服务项删除
	
	//下架申请:0申请中 1已通过 2未通过
	public static final Byte GOODS_APPLY_STATUS_APPLY=0;
	public static final Byte GOODS_APPLY_STATUS_SUC=1;
	public static final Byte GOODS_APPLY_STATUS_FAIL=2;
	
	//日志 增0 删1 改2 登陆3 退出4
	public static final Byte LOG_ADD = 0;
	public static final Byte LOG_DEL = 1;
	public static final Byte LOG_UPDATE = 2;
	public static final Byte LOG_LOGIN=3;
	public static final Byte LOG_OUT=4;
	
	//分类首页推文 状态 1可用 -1删除
	public static final Byte CATEGORY_HOME_ARTICLE_STATUS_DEL=-1;
	public static final Byte CATEGORY_HOME_ARTICLE_STATUS_NORMAL=1;
	
}







































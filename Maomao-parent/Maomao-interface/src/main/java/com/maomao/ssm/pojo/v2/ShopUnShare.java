package com.maomao.ssm.pojo.v2;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/** 
* @author:wzy
* @descrption:购买未分享
* @version:
* @date:2018年7月11日
*/

public class ShopUnShare implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<Map<String,Object>> unShareList;
	private List<Map<String,Object>> unShareRecommend;
	
	public ShopUnShare(List<Map<String, Object>> unShareList, List<Map<String, Object>> unShareRecommend) {
		this.unShareList = unShareList;
		this.unShareRecommend = unShareRecommend;
	}
	public ShopUnShare() {
	}
	public List<Map<String, Object>> getUnShareList() {
		return unShareList;
	}
	public void setUnShareList(List<Map<String, Object>> unShareList) {
		this.unShareList = unShareList;
	}
	public List<Map<String, Object>> getUnShareRecommend() {
		return unShareRecommend;
	}
	public void setUnShareRecommend(List<Map<String, Object>> unShareRecommend) {
		this.unShareRecommend = unShareRecommend;
	}
	
}

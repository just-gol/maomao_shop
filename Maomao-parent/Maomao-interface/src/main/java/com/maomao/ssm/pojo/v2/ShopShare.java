package com.maomao.ssm.pojo.v2;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/** 
* @author:wzy
* @descrption:已经分享
* @version:
* @date:2018年7月11日
*/

public class ShopShare implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<Map<String,Object>> share;
	private List<Map<String,Object>> shareRecommend;
	public List<Map<String, Object>> getShare() {
		return share;
	}
	public void setShare(List<Map<String, Object>> share) {
		this.share = share;
	}
	public List<Map<String, Object>> getShareRecommend() {
		return shareRecommend;
	}
	public void setShareRecommend(List<Map<String, Object>> shareRecommend) {
		this.shareRecommend = shareRecommend;
	}
	public ShopShare(List<Map<String, Object>> share, List<Map<String, Object>> shareRecommend) {
		super();
		this.share = share;
		this.shareRecommend = shareRecommend;
	}
	
}










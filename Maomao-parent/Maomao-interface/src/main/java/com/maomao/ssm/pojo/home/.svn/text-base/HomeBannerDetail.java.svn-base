package com.maomao.ssm.pojo.home;

import java.io.Serializable;
import java.util.Date;

import com.maomao.ssm.bean.Banner;
import com.maomao.ssm.constant.StatusConst;

/**
 * @author:wzy
 * @descrption:
 * @version:
 */

public class HomeBannerDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String img;// 图片
	private String href;// 链接
	private  String url;
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private Date createTime;// 创建时间

	public HomeBannerDetail(Banner banner, String ua) {
		this.id = banner.getId();
		if ("ios".equals(ua)) {
			this.img = banner.getImg().split(StatusConst.IMG_SPLIT_STRING)[1];
		} else {
			this.img = banner.getImg().split(StatusConst.IMG_SPLIT_STRING)[0];
		}
		this.href = banner.getHref();
		this.url=banner.getHref();
		this.createTime = banner.getCreateTime();
	}

	public HomeBannerDetail() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String toString() {
		return "HomeBannerDetail [id=" + id + ", img=" + img + ", href=" + href + ", createTime=" + createTime + "]";
	}

}

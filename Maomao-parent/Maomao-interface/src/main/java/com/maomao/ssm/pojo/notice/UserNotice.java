package com.maomao.ssm.pojo.notice;

import java.io.Serializable;
import java.util.Date;

import com.maomao.ssm.bean.NoticeRecordWithBLOBs;

/** 
* @author:wzy
* @descrption:
* @version:
* @date:2018年1月26日
*/
public class UserNotice implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Byte type;
	private Date createTime;
	private Byte status;
	private String content;
	public UserNotice() {
		
	}
	
	public UserNotice(NoticeRecordWithBLOBs noticeRecordWithBLOBs) {
		this.setId(noticeRecordWithBLOBs.getId());
		this.setType(noticeRecordWithBLOBs.getType());
		this.createTime = noticeRecordWithBLOBs.getCreateTime();
		this.setStatus(noticeRecordWithBLOBs.getStatus());
		this.setContent(noticeRecordWithBLOBs.getContent());
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}
	
	
	
	
}

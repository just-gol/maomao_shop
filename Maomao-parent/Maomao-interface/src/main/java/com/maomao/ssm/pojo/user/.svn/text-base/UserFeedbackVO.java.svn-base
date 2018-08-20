package com.maomao.ssm.pojo.user;

import java.io.Serializable;

import com.maomao.ssm.bean.UserFeedback;

public class UserFeedbackVO implements Serializable {
	private Integer feedbackId;
	private String content;
	private Long createTime;
	private String reply;
	private Long replyTime;

	public UserFeedbackVO() {
		super();
	}

	public UserFeedbackVO(UserFeedback userfeedback) {
		super();
		this.feedbackId = userfeedback.getId();
		this.content = userfeedback.getContent();
		this.createTime = userfeedback.getCreateTime().getTime();
		this.reply = userfeedback.getReply();
		if (userfeedback.getReplyTime() != null) {
			this.replyTime = userfeedback.getReplyTime().getTime();
		}
	}

	public UserFeedbackVO(Integer feedbackId, String content, Long createTime, String reply, Long replyTime) {
		super();
		this.feedbackId = feedbackId;
		this.content = content;
		this.createTime = createTime;
		this.reply = reply;
		this.replyTime = replyTime;
	}

	public Integer getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public Long getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Long replyTime) {
		this.replyTime = replyTime;
	}

}

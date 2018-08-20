package com.maomao.ssm.service.admin;

import com.maomao.ssm.pojo.JsonData;

/** 
* @author:wzy
* @descrption:
* @version:
* @date:2018年5月18日
*/

public interface UserFeedBackService {
    JsonData getFeebackListUnSolved(Integer pages,Integer rows,Long startTime ,Long endTime,String keywords,Integer adminId,Byte type);
    JsonData getFeedbackAdminList();
    JsonData getFeedbackDetail(Integer id,String reason,Byte type,Integer adminId);
}






































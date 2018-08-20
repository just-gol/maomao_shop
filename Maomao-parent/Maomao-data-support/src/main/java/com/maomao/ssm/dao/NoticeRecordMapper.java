package com.maomao.ssm.dao;

import com.maomao.ssm.bean.NoticeRecord;
import com.maomao.ssm.bean.NoticeRecordExample;
import com.maomao.ssm.bean.NoticeRecordWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NoticeRecordMapper {
    int countByExample(NoticeRecordExample example);

    int deleteByExample(NoticeRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NoticeRecordWithBLOBs record);

    int insertSelective(NoticeRecordWithBLOBs record);

    List<NoticeRecordWithBLOBs> selectByExampleWithBLOBs(NoticeRecordExample example);

    List<NoticeRecord> selectByExample(NoticeRecordExample example);

    NoticeRecordWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NoticeRecordWithBLOBs record, @Param("example") NoticeRecordExample example);

    int updateByExampleWithBLOBs(@Param("record") NoticeRecordWithBLOBs record, @Param("example") NoticeRecordExample example);

    int updateByExample(@Param("record") NoticeRecord record, @Param("example") NoticeRecordExample example);

    int updateByPrimaryKeySelective(NoticeRecordWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(NoticeRecordWithBLOBs record);

    int updateByPrimaryKey(NoticeRecord record);
}
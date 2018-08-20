package com.maomao.ssm.service.impl.admin;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maomao.ssm.bean.Log;
import com.maomao.ssm.bean.LogExample;
import com.maomao.ssm.dao.LogMapper;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.PageBean;
import com.maomao.ssm.service.admin.LogService;

@Service
public class LogServiceImpl implements LogService {
	@Autowired
	private LogMapper logMapper;

	@Override
	public void addLog(Integer adminId, String methodName, String params, String ip, Date createTime, Byte type) {
		Log log = new Log();
		log.setAdminId(adminId);
		log.setCreateTime(createTime);
		log.setMethodName(methodName);
		log.setParams(params);
		log.setIp(ip);
		log.setType(type);
		logMapper.insert(log);
	}

	/**
	 * 查看日志列表 方法名,或者ip进行查找
	 * 
	 * @Override
	 */

	public JsonData getLogList(Integer pages, Integer rows,String keywords) {
		// 分页
		PageHelper.startPage(pages, rows);
		LogExample example = new LogExample();
		if (StringUtils.isNotBlank(keywords)) {
			example.or().andMethodNameLike(("%" + keywords + "%"));
			example.or().andIpLike(("%" + keywords + "%"));
		}
		List<Log> logList = logMapper.selectByExample(example);
		if (logList == null || logList.size() <= 0) {
				return JsonData.setErrorMessage("未查询到数据");
		}
		PageInfo<Log> pageInfo = new PageInfo<>(logList);

		PageBean pagebean = new PageBean();
		// 获取总条数
		pagebean.setTotal(pageInfo.getTotal());
		// 获取当前页数据
		pagebean.setRows(logList);
		
		return JsonData.setSuccessMessage(pagebean);
	}
}

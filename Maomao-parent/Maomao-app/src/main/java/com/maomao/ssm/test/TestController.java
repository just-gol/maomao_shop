package com.maomao.ssm.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maomao.ssm.exception.DataException;
import com.maomao.ssm.exception.ServerException;
import com.maomao.ssm.pojo.JsonData;

@Controller
@RequestMapping(value = "/test")
public class TestController {

	@RequestMapping(value = "/test1.action")
	@ResponseBody
	public JsonData test1() throws Exception {
		throw new DataException("test1");
	}

	@RequestMapping(value = "/test2.action")
	@ResponseBody
	public JsonData test2() throws Exception {
		throw new ServerException("test1");
	}
}

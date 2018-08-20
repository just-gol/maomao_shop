package com.maomao.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maomao.ssm.bean.Goods;
import com.maomao.ssm.bean.GoodsExample;
import com.maomao.ssm.dao.GoodsMapper;

/** 
* @author:wzy
* @descrption:
* @version:
*/

public class PageHelperTest {
	
	public void fn1() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		GoodsMapper goodsMapper = context.getBean(GoodsMapper.class);
		
		//执行sql之前设置分页信息,使用PageHelper的startPage的静态方法
		PageHelper.startPage(1, 1);
		//执行查询
		GoodsExample example = new GoodsExample();
		List<Goods> list = goodsMapper.selectByExample(example);
		for (Goods goods : list) {
			System.out.println(goods.getName());
		}
		//取分页信息,PageInfo,总记录数,总页数,当前页码,每页显示记录数
		PageInfo<Goods> pageInfo = new PageInfo<Goods>(list);
		System.out.println(pageInfo.getTotal());
	}
}




































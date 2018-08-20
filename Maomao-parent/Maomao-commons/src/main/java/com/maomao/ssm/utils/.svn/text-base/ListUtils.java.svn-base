package com.maomao.ssm.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

	/**
	 * 将String转成integer集合
	 * 
	 * @param list
	 * @param regex
	 * @return
	 */
	public static List<Integer> getIntegerListFromString(String list, String regex) {
		String[] lists = list.split(regex);
		List<Integer> integerList = new ArrayList<Integer>();
		for (String s : lists) {
			integerList.add(Integer.parseInt(s));
		}
		return integerList;
	}
}

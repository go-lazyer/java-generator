package com.shadowh.lazy.util;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtil {
	/**
	 * 把List<String> 转换为 List<Integer>
	 * @param list
	 * @return
	 * @author hanchanghong
	 * @date 2014年11月20日 下午4:24:02
	 */
	public static List<Integer> convertToIntList(List<String> list){
		List<Integer> intList= new ArrayList<Integer>();
		for(String str:list){
			intList.add(Integer.parseInt(str));
		}
		return intList;
	}
	
}

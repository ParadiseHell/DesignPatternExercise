package com.ct.simple.factoty.main;

import com.ct.simple.factoty.impl.RecommendImpl;
import com.ct.simple.factoty.utils.RecommendUtils;
import com.ct.simple.factoty.utils.StringUtils;

/**
 * 推荐主体类
 * @author ChengTao
 *
 */
public class RecommenfMain {
	//姓名
	private static String name = "小明";
	//推荐对象
	private static RecommendImpl recommendObejct;
	
	public static void main(String[] args) {
		StringUtils.println(name + "登陆了...");
		recommendObejct = RecommendUtils.getRecommendObjectFromName(name);
		try {
			recommendObejct.recommend();
		} catch (NullPointerException e) {
			StringUtils.println("没有找到"+name+"诶......");
		}
	}

}

package com.ct.simple.factoty.people;

import java.util.Arrays;
import java.util.List;

import com.ct.simple.factoty.impl.RecommendImpl;
import com.ct.simple.factoty.utils.StringUtils;

/**
 * 女生推荐类
 * 
 * @author ChengTao
 *
 */
public class GirlRecommend implements RecommendImpl {
	private final List<String> recommendInfo = Arrays.asList("1.淘宝", "2.1号店", "3.聚美优品");

	@Override
	public void recommend() {
		for (String string : recommendInfo) {
			StringUtils.println(string);
		}
	}
}

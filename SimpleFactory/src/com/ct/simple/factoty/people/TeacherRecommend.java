package com.ct.simple.factoty.people;

import java.util.Arrays;
import java.util.List;

import com.ct.simple.factoty.impl.RecommendImpl;
import com.ct.simple.factoty.utils.StringUtils;

/**
 * 老师推荐类
 * 
 * @author ChengTao
 *
 */
public class TeacherRecommend implements RecommendImpl {
	private final List<String> recommendInfo = Arrays.asList("1.网球王子", "2.英雄联盟", "3.滑板");

	@Override
	public void recommend() {
		for (String string : recommendInfo) {
			StringUtils.println(string);
		}
	}
}

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
	private final List<String> recommendInfo = Arrays.asList("1.图书", "2.旅游", "3.学生动态");

	@Override
	public void recommend() {
		for (String string : recommendInfo) {
			StringUtils.println(string);
		}
	}
}

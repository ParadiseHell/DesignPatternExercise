package com.ct.simple.factoty.people;

import java.util.Arrays;
import java.util.List;

import com.ct.simple.factoty.impl.RecommendImpl;
import com.ct.simple.factoty.utils.StringUtils;

/**
 * �����Ƽ���
 * 
 * @author ChengTao
 *
 */
public class BoyRecommend implements RecommendImpl {
	private final List<String> recommendInfo = Arrays.asList("1.��������", "2.Ӣ������", "3.����");

	@Override
	public void recommend() {
		for (String string : recommendInfo) {
			StringUtils.println(string);
		}
	}

}

package com.ct.factory.product;

import com.ct.factory.base.AbstractTVProduct;
import com.ct.factory.utils.StringUtils;

/**
 * ��������
 * @author ChengTao
 *
 */
public class HairTV extends AbstractTVProduct{
	//-------------����
	private final static String name = "Hair_TV";
	
	public HairTV() {
		super(name);
	}
	
	@Override
	public void play() {
		StringUtils.printlnString("I'm a " + TvName);
	}

}

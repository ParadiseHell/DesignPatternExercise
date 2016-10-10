package com.ct.factory.product;

import com.ct.factory.base.AbstractTVProduct;
import com.ct.factory.utils.StringUtils;

/**
 * 海尔电视
 * @author ChengTao
 *
 */
public class HairTV extends AbstractTVProduct{
	//-------------常量
	private final static String name = "Hair_TV";
	
	public HairTV() {
		super(name);
	}
	
	@Override
	public void play() {
		StringUtils.printlnString("I'm a " + TvName);
	}

}

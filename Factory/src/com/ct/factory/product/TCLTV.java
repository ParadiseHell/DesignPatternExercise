package com.ct.factory.product;

import com.ct.factory.base.AbstractTVProduct;
import com.ct.factory.utils.StringUtils;

/**
 * TCL电视
 * 
 * @author ChengTao
 *
 */
public class TCLTV extends AbstractTVProduct {

	// -------------常量
	private final static String name = "TCL_TV";

	public TCLTV() {
		super(name);
	}

	@Override
	public void play() {
		StringUtils.printlnString("I'm a " + TvName);
	}

}

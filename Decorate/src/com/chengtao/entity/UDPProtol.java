package com.chengtao.entity;

import com.chengtao.impl.UDPReceiverProtocol;

/**
 * UDP协议类<br>
 * 规定了换行符和结束符
 * @author ChengTao
 *
 */
public class UDPProtol implements UDPReceiverProtocol{

	@Override
	public char getLineChar() {
		return '#';
	}

	@Override
	public char getEndChar() {
		return '@';
	}

}

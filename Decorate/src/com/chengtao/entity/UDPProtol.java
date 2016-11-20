package com.chengtao.entity;

import com.chengtao.impl.UDPReceiverProtocol;

/**
 * UDPЭ����<br>
 * �涨�˻��з��ͽ�����
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

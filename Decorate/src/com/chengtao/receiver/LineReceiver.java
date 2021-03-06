package com.chengtao.receiver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.chengtao.impl.UDPReceiverProtocol;

/**
 * UDP字符串接受类
 * @author ChengTao
 *
 */
public class LineReceiver {
	private CharReceiver cr = null;
	private char[] charBuf = new char[10];
	private UDPReceiverProtocol protocol = null;

	public LineReceiver(CharReceiver cr) {
		this.cr = cr;
	}

	public String receiveLine() {
		if (protocol == null) {
			throw new NullPointerException("没有设置UDP接受的协议");
		}
		resetBuffer();
		char ch;
		int i = 0;
		String receiveStr = "";
		while (true) {
			ch = cr.receive();
			if (ch != Character.MIN_VALUE) {
				System.out.println(ch);
				if (ch == protocol.getLineChar() || ch == protocol.getEndChar()) {
					break;
				}
				charBuf[i] = ch;
				i++;
			} else {
				break;
			}
		}
		receiveStr = new String(charBuf);
		receiveStr = receiveStr.replace(Character.MIN_VALUE, ' ').trim();
		if (receiveStr.equals("")) {
			receiveStr = null;
		} else {
			System.out.println(receiveStr);
		}
		return receiveStr;
	}

	private void resetBuffer() {
		for (int i = 0; i < charBuf.length; i++) {
			charBuf[i] = Character.MIN_VALUE;
		}
	}

	public int receivePattern(String pattern) {
		int partternNum = 0;
		String receiveString = null;
		while (true) {
			receiveString = receiveLine();
			if (receiveString == null) {
				break;
			} else {
				partternNum += matchPattern(receiveString, pattern);
			}
		}
		return partternNum;
	}

	public static int matchPattern(String matrix, String pattern) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(matrix);
		int n = 0;
		while(m.find()){
			n++;
		}
		return n;
	}

	public void setUDPReceiverProtocol(UDPReceiverProtocol protocol) {
		this.protocol = protocol;
	}
}

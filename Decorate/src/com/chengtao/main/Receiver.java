package com.chengtao.main;

import java.util.Scanner;

import com.chengtao.entity.UDPProtol;
import com.chengtao.receiver.CharReceiver;
import com.chengtao.receiver.LineReceiver;

/**
 * ������
 * @author ChengTao
 *
 */
public class Receiver {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		UDPProtol protocol = new UDPProtol();
		LineReceiver lr = new LineReceiver(new CharReceiver(6102));
		lr.setUDPReceiverProtocol(protocol);
		System.out.println("������Ҫƥ����ַ���:");
		String pattern = scanner.next();
		int count = lr.receivePattern(pattern);
		System.out.println("���յ�ģʽ�� " + pattern + " " + count + " ��");
		scanner.close();
	}
}
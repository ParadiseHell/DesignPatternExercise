package com.chengtao.receiver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * UDP�ַ�������
 * @author ChengTao
 *
 */
public class CharReceiver {
	private DatagramSocket socket = null;
	
	public CharReceiver(int port) {
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public char receive() {
		byte buffer[] = new byte[255];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		try {
			socket.receive(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (packet.getData() != null) {
			return (char) packet.getData()[0];
		}
		return Character.MIN_VALUE;
	}
}

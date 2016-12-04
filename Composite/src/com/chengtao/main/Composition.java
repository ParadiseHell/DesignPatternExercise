package com.chengtao.main;

import java.util.Scanner;

import com.chengtao.commad.Command;

/**
 * the main class to test Composition Design
 * @author ChengTao
 *
 */
public class Composition {
	private final static String TIP_FONT = "�ڽڵ�����ǰ����ָ��( �ÿո���� ) : ";
	private final static String TIP_BACK = "q for quit.";
	public static void main(String[] args) {
		Command command = Command.getInstance();
		String tip = TIP_FONT + command.getCommands() + TIP_BACK;
		Scanner in = new Scanner(System.in);
		String cmd = "";
		String arg = "";
		while(true){
			System.out.println(tip);
			cmd = in.next();
			if (cmd.equals("q")) {
				System.out.println("bye");
				break;
			}
			arg = in.next();
			command.execute(cmd, arg);
		}
		in.close();
	}
}
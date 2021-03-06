package com.chengtao.commad;

import java.util.HashMap;

/**
 * Command class which to define all commands here
 * @author ChengTao
 *
 */
public class Command {
	private static Command instance = null;
	/**
	 * the map to store command and it's class name
	 */
	private HashMap<String, String> cmds = new HashMap<>();
	
	private Command(){
		initCommands();
	}
	
	/**
	 * initial all commands here
	 */
	private void initCommands() {
		cmds.put("layer", LayerCommand.class.getName());
		cmds.put("depth", DepthCommand.class.getName());
		cmds.put("children", ChildrenCommand.class.getName());
		cmds.put("ancestors", AncestorsCommand.class.getName());
		cmds.put("subtree", SubtreeCommand.class.getName());
	}

	public static Command getInstance() {
		if (instance == null) {
			synchronized (Command.class) {
				if (instance == null) {
					instance = new Command();
				}
			}
		}
		return instance;
	}
	
	/**
	 * To get all command can be executed
	 * @return
	 */
	public String getCommands() {
		String cmdStr = "";
		for(String cmd : cmds.keySet()){
			cmdStr += (cmd + "; ");
		}
		return cmdStr;
	}
	
	/**
	 * execute command from client
	 * @param cmdName
	 * @param arg
	 */
	public void execute(String cmdName,String arg) {
		if (!cmds.containsKey(cmdName)) {
			System.out.println("不能解析所给的指令和参数.");
			return;
		}
		try {
			try {
				AbsCommand command = (AbsCommand) Class.forName(cmds.get(cmdName)).newInstance();
				command.execute(arg);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

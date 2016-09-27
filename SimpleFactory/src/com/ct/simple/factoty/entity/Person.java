package com.ct.simple.factoty.entity;

import java.io.Serializable;

/**
 * 人物实体
 * @author ChengTao
 *
 */
public class Person implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8292469100214968426L;
	
	public final static int BOY = 1;
	public final static int  GIRL = 2;
	public final static int TEACHER = 3;
	
	//姓名
	private String name;
	//类别
	private int type;
	
	public Person() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}

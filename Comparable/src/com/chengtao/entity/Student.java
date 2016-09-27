package com.chengtao.entity;

/**
 * 
 * description:学生类实体
 * @author ChengTao <br/>排序规则：<br/>
 *         1：女学生优先<br/>
 *         2：年龄小优先<br/>
 *         3：学号小的优先<br/>
 *
 */
public class Student implements Comparable<Object> {
	/**
	 * true为女生，false为男生
	 */
	private boolean sex;
	/**
	 * 年龄
	 */
	private int age;
	/**
	 * 学号
	 */
	private String number;

	public Student(boolean sex, int age, String number) {
		super();
		this.sex = sex;
		this.age = age;
		this.number = number;
	}

	@Override
	public String toString() {
		return this.isSex() ? 
				  "女生--年龄:" + this.getAge() + "--学号:" + this.getNumber()
				: "男生--年龄:" + this.getAge() + "--学号:" + this.getNumber();
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * 前者小返回-1 <br>
	 * 后者小返回1 <br>
	 * 两者相等返回0<br>
	 */
	@Override
	public int compareTo(Object o) {
		Student temp = (Student) o;
		if ((this.isSex() && temp.isSex()) || (!this.isSex() && !temp.isSex())) {// 二者性别相同
			if (this.getAge() < temp.getAge()) {// 前者年龄小
				return -1;
			} else if (this.getAge() > temp.getAge()) {// 后者年龄小
				return 1;
			} else {// 二者年龄相等
				int result = this.getNumber().compareTo(temp.getNumber());
				if (result < 0) {// 前者小
					return -1;
				} else if (result > 0) {// 后者小
					return 1;
				}
			}
		} else if (this.isSex() && !temp.isSex()) {// 前者是女生，后者不是
			return -1;
		} else if (!this.isSex() && temp.isSex()) {// 前者不是女生，后者是女生
			return 1;
		}
		return 0;
	}

}

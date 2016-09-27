package com.chengtao.entity;

/**
 * 
 * description:ѧ����ʵ��
 * @author ChengTao <br/>�������<br/>
 *         1��Ůѧ������<br/>
 *         2������С����<br/>
 *         3��ѧ��С������<br/>
 *
 */
public class Student implements Comparable<Object> {
	/**
	 * trueΪŮ����falseΪ����
	 */
	private boolean sex;
	/**
	 * ����
	 */
	private int age;
	/**
	 * ѧ��
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
				  "Ů��--����:" + this.getAge() + "--ѧ��:" + this.getNumber()
				: "����--����:" + this.getAge() + "--ѧ��:" + this.getNumber();
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
	 * ǰ��С����-1 <br>
	 * ����С����1 <br>
	 * ������ȷ���0<br>
	 */
	@Override
	public int compareTo(Object o) {
		Student temp = (Student) o;
		if ((this.isSex() && temp.isSex()) || (!this.isSex() && !temp.isSex())) {// �����Ա���ͬ
			if (this.getAge() < temp.getAge()) {// ǰ������С
				return -1;
			} else if (this.getAge() > temp.getAge()) {// ��������С
				return 1;
			} else {// �����������
				int result = this.getNumber().compareTo(temp.getNumber());
				if (result < 0) {// ǰ��С
					return -1;
				} else if (result > 0) {// ����С
					return 1;
				}
			}
		} else if (this.isSex() && !temp.isSex()) {// ǰ����Ů�������߲���
			return -1;
		} else if (!this.isSex() && temp.isSex()) {// ǰ�߲���Ů����������Ů��
			return 1;
		}
		return 0;
	}

}

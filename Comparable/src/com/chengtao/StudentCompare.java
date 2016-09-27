package com.chengtao;

import com.chengtao.entity.Student;

/**
 * 
 * description:学生信息比较类
 * @author ChengTao
 *
 */
public class StudentCompare{

	public static void main(String[] args) {
		Student[] students = new Student[6];
		boolean[] sexArr = {true,true,false,true,false,true};
		int[] ageArr = {12,19,14,13,17,12};
		String[] numberArr = {"123","124","100","121","099","120"};
		for (int i = 0; i < students.length; i++) {
			students[i] = new Student(sexArr[i], ageArr[i], numberArr[i]);
		}
		compareStudents(students);
		for (int i = 0; i < students.length; i++) {
			System.out.println(students[i].toString());
		}
	}
	
	/**
	 * 学生信息比较
	 * @param stus 学生数组
	 */
	private static void compareStudents(Comparable<Object>[] stus) {
		for (int i = stus.length - 1; i >= 0; i--) {
			Student maxStu = (Student) stus[i];
			int maxPos = i;
			for (int j = 0; j < i; j++) {
				int result = stus[j].compareTo(maxStu);
				if (result > 0) {
					maxPos = j;
					maxStu = (Student) stus[j];
				}
			}
			if (maxPos != i) {
				Student temp = (Student) stus[i];
				stus[i] = maxStu;
				stus[maxPos] = temp; 
			}
		}
	}
}

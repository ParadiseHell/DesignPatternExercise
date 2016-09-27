package com.ct.simple.factoty.utils;

import java.util.ArrayList;

import com.ct.simple.factoty.db.RecommendDB;
import com.ct.simple.factoty.entity.Person;
import com.ct.simple.factoty.impl.RecommendImpl;
import com.ct.simple.factoty.people.BoyRecommend;
import com.ct.simple.factoty.people.GirlRecommend;
import com.ct.simple.factoty.people.TeacherRecommend;

/**
 * �Ƽ�������
 * 
 * @author ChengTao
 *
 */
public class RecommendUtils {
	/**
	 * ͨ�����ֻ�ȡ�Ƽ�����
	 * 
	 * @param name
	 *            ����
	 * @return
	 */
	private static RecommendDB db;

	public static RecommendImpl getRecommendObjectFromName(String name){
		db = RecommendDB.getInstance();
		ArrayList<Person> peopleList = db.getPeopleList();
		for (Person person : peopleList) {
			if (person.getName().equalsIgnoreCase(name)) {
				switch (person.getType()) {
				case Person.BOY:
					return new BoyRecommend();
				case Person.GIRL:
					return new GirlRecommend();
				case Person.TEACHER:
					return new TeacherRecommend();
				default:
					break;
				}
			}
		}
		return null;
	}
}

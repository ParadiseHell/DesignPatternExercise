package com.ct.simple.factoty.db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ct.simple.factoty.entity.Person;

/**
 * 推荐假数据库
 * 
 * @author ChengTao
 *
 */
public class RecommendDB {
	// 数据库实例
	private static RecommendDB instance = null;
	// 姓名列表
	private final static List<String> nameList = Arrays.asList("小明", "小李", "小红", "小花", "老李", "老王");
	// 类别列表
	private final static List<Integer> typeList = Arrays.asList(Person.BOY, Person.BOY, Person.GIRL, Person.GIRL,
			Person.TEACHER, Person.TEACHER);
	// 人物类别
	private static ArrayList<Person> peopleList = null;

	// 私有构造函数
	private RecommendDB() {
		peopleList = new ArrayList<>();
		CreatePeople();
	}

	/**
	 * 获取数据库实体
	 * 
	 * @return
	 */
	public static RecommendDB getInstance() {
		if (instance == null) {
			synchronized (RecommendDB.class) {
				instance = new RecommendDB();
			}
		}
		return instance;
	}

	/**
	 * 创建人物
	 */
	private void CreatePeople() {
		if (peopleList.size() == 0) {
			for (int i = 0; i < nameList.size(); i++) {
				Person person = new Person();
				person.setName(nameList.get(i));
				person.setType(typeList.get(i));
				peopleList.add(person);
			}
		}
	}
	
	/**
	 * 获取人物列表
	 * 
	 * @return
	 */
	public ArrayList<Person> getPeopleList() {
		return peopleList;
	}

}

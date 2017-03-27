package cn.nickid.biz.res;

import java.io.Serializable;

public class UserInfoRes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8264536452200692937L;
	
	private int id;
	private String name;
	private int age;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

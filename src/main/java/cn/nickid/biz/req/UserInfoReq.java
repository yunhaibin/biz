package cn.nickid.biz.req;

import java.io.Serializable;

public class UserInfoReq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5134938726745121898L;
	
	private int id;
	private String name;
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

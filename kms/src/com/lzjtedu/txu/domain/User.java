package com.lzjtedu.txu.domain;

import java.io.Serializable;

public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int uid;
	private String name;
	private String realName;
	private String address;
	private String password;
	
	public User() {
	}
	
	
	
	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}


	public int getId() {
		return uid;
	}
	public void setId(int id) {
		this.uid = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}


	public String getAddress() {
		return address;
	}
	
	
	
	public void setAddress(String address) {
		this.address = address;
	}



	@Override
	public String toString() {
		return "User [uid=" + uid + ", name=" + name + ", realName=" + realName + ", address=" + address + ", password="
				+ password + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + uid;
		return result;
	}





	
	
	
	
	
	

}

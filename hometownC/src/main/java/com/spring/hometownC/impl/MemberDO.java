package com.spring.hometownC.impl;

public class MemberDO {
	private String id;
	private String username;
	private String password;
	private String phone;
	private String address;
	
	@Override
	public String toString() {
		return "MemberDO [id=" + id + ", username=" + username + ", password=" + password + ", phone=" + phone
				+ ", address=" + address + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	

}

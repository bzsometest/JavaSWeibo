package com.one.entity;

/**
 * 用户实体
 * 
 * @author Administrator
 *
 */
public class User {
	private int uid;// 用户id
	private String username;// 用户名
	private String userpass;// 用户密码
	private String phone;// 用户电话
	private String email;// 用户邮箱
	private String sign;// 用户介绍
    private String type;//用户类型
    
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User() {

	}

	public User(int uid, String username, String userpass, String phone, String email, String sign) {
		super();
		this.uid = uid;
		this.username = username;
		this.userpass = userpass;
		this.phone = phone;
		this.email = email;
		this.sign = sign;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", userpass=" + userpass + ", phone=" + phone
				+ ", email=" + email + ", sign=" + sign + "]";
	}

}

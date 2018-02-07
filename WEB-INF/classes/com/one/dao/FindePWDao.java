package com.one.dao;

/**
 * 找回密码接口
 * 
 * @author chao
 *
 */
public interface FindePWDao {
	/**
	 * 发送Email验证链接
	 * 
	 * @param email
	 * @param webUrl
	 */
	public int sendEmail(String email, String emailCode);

	/**
	 * 发送短语验证码
	 * 
	 * @param phone
	 * @param webUrl
	 */
	public int sendPhone(String phone, String phonCode);

	/**
	 * 验证手机短信
	 * 
	 * @param phone
	 * @param phoneCode
	 */
	public int phoneCode(int uid, String phoneCode);

	/**
	 * 验证邮箱链接
	 */
	public int emailCode(int uid, String emailCode);

	/**
	 * 修改用户密码
	 * 
	 * @param user
	 * @return
	 */
	public int alterPass(int id, String userpass);
}

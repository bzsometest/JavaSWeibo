package com.one.service;

/**
 * 找回业务密码接口
 * 
 * @author chao
 *
 */
public interface FindPWService {
	/**
	 * 发送Email链接找回密码
	 * 
	 */
	public int sendEmail(String email, String webUrl);

	/**
	 * 发送手机短信找回密码
	 * 
	 */
	public int sendPhone(String phone, String webUrl);

	/**
	 * 验证邮箱链接修改密码
	 * 
	 */
	public int emailCode(String email, String emailCode);

	/**
	 * 验证手机短信修改密码
	 * 
	 */
	public int phoneCode(String phone, String phoneCode);

	/**
	 * 通过邮箱修改密码，执行方法则成功修改密码！
	 * 
	 * @param account
	 * @param emailCode
	 * @param newPass
	 * @return
	 */
	public int alterEmailpass(String account, String emailCode, String newPass);

	/**
	 * 通过手机修改密码，执行方法则成功修改密码！
	 * 
	 * @param phone
	 * @param phoneCode
	 */
	public int phoneCode(String phone, String phoneCode, String newPass);
}

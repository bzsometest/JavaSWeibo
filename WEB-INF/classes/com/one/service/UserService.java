package com.one.service;

import java.util.List;

import com.one.entity.User;

/**
 * 用户接口
 * 
 * @author Chao
 */
public interface UserService {
	/**
	 * 用户注册
	 * 
	 * @author Chao
	 */
	public int reg(User user);

	/**
	 * 用户登录
	 * 
	 * @author Chao
	 */
	public User login(String account, String userPass);
	/**
	 * 管理员用户登录
	 * 
	 * @author Chao
	 */
	public User adminLogin(String account, String userPass);

	/**
	 * 根据账号获得用户，主要用于找回密码
	 * 
	 * @author Chao
	 */
	public User getUser(String account);

	/**
	 * 修改用户信息,需要验证原始密码
	 * 
	 * @param user
	 * @return
	 */
	public int alter(User user, String userpass);

	/**
	 * 获得所有用户
	 * 
	 * @author Chao
	 */
	public List<User> getAll(User user);

	/**
	 * 是否存在此用户名
	 * 
	 * @param username
	 * @return
	 */
	public boolean isHaveUsername(String username);

	/**
	 * 是否存在此手机号
	 * 
	 */
	public boolean isHavePhone(String phone);

	/**
	 * 是否存在此邮箱
	 * 
	 */
	public boolean isHaveEmail(String email);

	/**
	 * 是否存在此账号
	 * 
	 */
	public boolean isHaveAccount(String account);
   /**
    * 管理员删除用户
    * @param user
    * @param admin
    * @return
    */
	public int adminDelete(User user, User admin);
	 /**
	  * 根据uid查询User
	  * @param user
	  * @return
	  */
	public User getByUid(int did);
    /**
     * 删除用户
     * @param admin
     * @param user
     * @return
     */
	public int deleteUser(User admin, User user);
}

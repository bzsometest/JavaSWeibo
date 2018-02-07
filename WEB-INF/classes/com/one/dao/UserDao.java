package com.one.dao;

import java.util.List;


import com.one.entity.User;

/**
 * 用户接口
 * 
 * @author Chao
 */
public interface UserDao {
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
	 * 根据账号获得用户，主要用于找回密码
	 * 
	 * @author Chao
	 */
	public User getUser(String account);

	/**
	 * 修改用户信息
	 * 
	 * @param user
	 * @return
	 */
	public int alter(User user);

	/**
	 * 获得所有用户
	 * 
	 * @author Chao
	 */
	public List<User> getAll();

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
	 * 根据用户id查询用户信息
	 * 
	 * @param uid
	 * @return
	 */
	public User findByID(int uid);
    /**
     * 管理员登录验证
     * @param account
     * @param userPass
     * @return
     */
	public User adminLogin(String account, String userPass);
   
	 /**
	  * 管理员删除用户
	  * @param uid
	  * @return
	  */
	public int adminDelete(int uid);
    /**
     * 根据uid查询用户
     * @param uid
     * @return
     */
	public User getByUid(int uid);

	
}

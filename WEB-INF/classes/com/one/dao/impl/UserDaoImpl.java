package com.one.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.one.dao.BaseDao;
import com.one.dao.UserDao;
import com.one.entity.User;

/**
 * 用户实现类
 * 
 * @author Chao
 */
public class UserDaoImpl implements UserDao {
	/**
	 * 注册用户
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public int reg(User user) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "insert into user(username,userpass_encode,phone,email,sign)values(?,?,?,?,?)";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getUserpass());
			pst.setString(3, user.getPhone());
			pst.setString(4, user.getEmail());
			pst.setString(5, user.getSign());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
		}
		return result;
	}

	/**
	 * 根据密码和登录名查询用户
	 * 
	 * @param account
	 * @param userPass
	 * @return
	 */
	@Override
	public User login(String account, String userPass) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		User user = null;
		String sql = "SELECT * FROM user where (username=? OR phone=? OR email=?) AND userpass_encode=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, account);
			pst.setString(2, account);
			pst.setString(3, account);
			pst.setString(4, userPass);
			rs = pst.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUid(rs.getInt("uid"));
				user.setUsername(rs.getString("username"));
				user.setUserpass(rs.getString("userpass_encode"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setSign(rs.getString("sign"));
				user.setType(rs.getString("type"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
		}
		return user;
	}
	@Override
	public User adminLogin(String account, String userPass) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		User user = null;
		String sql = "SELECT * FROM user where (username=? OR phone=? OR email=?) AND userpass_encode=? and type='admin'";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, account);
			pst.setString(2, account);
			pst.setString(3, account);
			pst.setString(4, userPass);
			rs = pst.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUid(rs.getInt("uid"));
				user.setUsername(rs.getString("username"));
				user.setUserpass(rs.getString("userpass_encode"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setSign(rs.getString("sign"));
				user.setType(rs.getString("type"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
		}
		return user;
	}
	@Override
	public List<User> getAll() {
		List<User> list = new ArrayList<>();
		Connection conn = BaseDao.getConnction();
		if (conn != null) {
			PreparedStatement pst = null;
			ResultSet rs = null;
			User user;
			String sql = "SELECT * FROM user WHERE username!='admin'";
			try {
				pst = conn.prepareStatement(sql);
				rs = pst.executeQuery();
				while (rs.next()) {
					user = new User();
					user.setUid(rs.getInt("uid"));
					user.setUsername(rs.getString("username"));
					user.setUserpass(rs.getString("userpass_encode"));
					user.setPhone(rs.getString("phone"));
					user.setEmail(rs.getString("email"));
					user.setSign(rs.getString("sign"));
					user.setType(rs.getString("type"));
					// 未写
					list.add(user);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				BaseDao.close(conn, pst, rs);
			}
		}
		return list;
	}

	@Override
	public boolean isHaveUsername(String username) {
		Connection conn = BaseDao.getConnction();
		if (conn != null) {
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = "select * from user where username=?";
			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, username);
				rs = pst.executeQuery();
				if (rs.next()) {
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				BaseDao.close(conn, pst, rs);
			}
		}
		return false;
	}

	@Override
	public boolean isHavePhone(String phone) {
		Connection conn = BaseDao.getConnction();
		if (conn != null) {
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = "select * from user where phone=? ";
			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, phone);
				rs = pst.executeQuery();
				if (rs.next()) {
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				BaseDao.close(conn, pst, rs);
			}
		}
		return false;
	}

	@Override
	public boolean isHaveEmail(String email) {
		Connection conn = BaseDao.getConnction();
		if (conn != null) {
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = "select * from user where  email=?";
			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, email);
				rs = pst.executeQuery();
				if (rs.next()) {
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				BaseDao.close(conn, pst, rs);
			}
		}
		return false;
	}

	@Override
	public boolean isHaveAccount(String account) {
		Connection conn = BaseDao.getConnction();
		if (conn != null) {
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = "select * from user where username=? or phone=? or email=?";
			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, account);
				pst.setString(2, account);
				pst.setString(3, account);
				rs = pst.executeQuery();
				if (rs.next()) {
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				BaseDao.close(conn, pst, rs);
			}
		}
		return false;
	}

	@Override
	public User getUser(String account) {
		Connection conn = BaseDao.getConnction();
		User user = null;
		if (conn != null) {
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = "select * from user where username=? or phone=? or email=?";
			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, account);
				pst.setString(2, account);
				pst.setString(3, account);
				rs = pst.executeQuery();
				if (rs.next()) {
					user = new User();
					user.setUid(rs.getInt("uid"));
					user.setUsername(rs.getString("Username"));
					user.setPhone(rs.getString("Phone"));
					user.setEmail(rs.getString("Email"));
					user.setSign(rs.getString("Sign"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				BaseDao.close(conn, pst, rs);
			}
		}
		return user;
	}

	@Override
	public int alter(User user) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		int res = 0;
		String sql = "update user set userpass_encode=?,email=?,phone=?,sign=? where uid=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUserpass());
			pst.setString(2, user.getEmail());
			pst.setString(3, user.getPhone());
			pst.setString(4, user.getSign());
			pst.setInt(5, user.getUid());
			res = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, null);
		}
		return res;
	}

	@Override
	public User findByID(int uid) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		User user = null;
		String sql = "SELECT * FROM user where uid=?";

		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, uid);

			rs = pst.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUid(rs.getInt("uid"));
				user.setUsername(rs.getString("username"));
					
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setSign(rs.getString("sign"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
		}
		return user;
	}

	@Override
	public int adminDelete(int uid) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "delete from user where uid=?";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1,uid);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
		}
		return result;
	}

	@Override
	public User getByUid(int uid) {
		Connection conn = BaseDao.getConnction();
		User user = null;
		if (conn != null) {
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = "select * from user where uid=?";
			try {
				pst = conn.prepareStatement(sql);
				pst.setInt(1, uid);
				
				rs = pst.executeQuery();
				if (rs.next()) {
					user = new User();
					user.setUid(rs.getInt("uid"));
					user.setUsername(rs.getString("Username"));
					user.setPhone(rs.getString("Phone"));
					user.setEmail(rs.getString("Email"));
					user.setSign(rs.getString("Sign"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				BaseDao.close(conn, pst, rs);
			}
		}
		return user;
	}
}

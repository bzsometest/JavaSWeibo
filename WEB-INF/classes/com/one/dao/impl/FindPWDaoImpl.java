package com.one.dao.impl;

import java.sql.*;

import com.one.dao.BaseDao;
import com.one.dao.FindePWDao;
import com.one.entity.User;

/**
 * 找回密码实现类
 * 
 * @author chao
 *
 */
public class FindPWDaoImpl implements FindePWDao {

	@Override
	public int sendEmail(String email, String emailCode) {
		Connection conn = BaseDao.getConnction();
		User user = new UserDaoImpl().getUser(email);
		int res = 0;
		if (conn != null) {
			PreparedStatement pst = null;
			String sql = "insert into find_userpass (uid,email_code) values(?,?)";
			try {
				pst = conn.prepareStatement(sql);
				pst.setInt(1, user.getUid());
				pst.setString(2, emailCode);
				res = pst.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				BaseDao.close(conn, pst, null);
			}
		}
		return res;
	}

	@Override
	public int sendPhone(String phone, String phonCode) {
		Connection conn = BaseDao.getConnction();
		User user = new UserDaoImpl().getUser(phone);
		int res = 0;
		if (conn != null) {
			PreparedStatement pst = null;
			String sql = "insert into find_userpass (uid,phone_code) values(?,?)";
			try {
				pst = conn.prepareStatement(sql);
				pst.setInt(1, user.getUid());
				pst.setString(2, phonCode);
				res = pst.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				BaseDao.close(conn, pst, null);
			}
		}
		return res;
	}

	@Override
	public int phoneCode(int uid, String phonCode) {
		Connection conn = BaseDao.getConnction();
		ResultSet rs;
		if (conn != null) {
			PreparedStatement pst = null;
			String sql = "select * find_userpass where uid=? and phone_code=?";
			try {
				pst = conn.prepareStatement(sql);
				pst.setInt(1, uid);
				pst.setString(2, phonCode);
				rs = pst.executeQuery();
				if (rs.next()) {
					return rs.getInt("uid");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				BaseDao.close(conn, pst, null);
			}
		}
		return 0;
	}

	@Override
	public int emailCode(int uid, String emailCode) {
		Connection conn = BaseDao.getConnction();
		ResultSet rs = null;
		if (conn != null) {
			PreparedStatement pst = null;
			String sql = "select * from find_userpass where uid=? and email_code=?";
			try {
				pst = conn.prepareStatement(sql);
				pst.setInt(1, uid);
				pst.setString(2, emailCode);
				rs = pst.executeQuery();
				if (rs.next()) {
					return rs.getInt("uid");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				BaseDao.close(conn, pst, null);
			}
		}
		return 0;
	}

	@Override
	public int alterPass(int uid, String userpass) {
		Connection conn = BaseDao.getConnction();
		int res = 0;
		// 删除此用户关于找回密码的列表
		deleteCode(uid);
		if (conn != null) {
			PreparedStatement pst = null;
			String sql = "update user set userpass_encode=? where uid=?";
			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, userpass);
				pst.setInt(2, uid);
				res = pst.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				BaseDao.close(conn, pst, null);
			}
		}
		return res;
	}

	// 删除此用户关于找回密码的列表
	public void deleteCode(int uid) {
		Connection conn = BaseDao.getConnction();
		if (conn != null) {
			PreparedStatement pst = null;
			String sql = "delete from find_userpass where uid=?";
			try {
				pst = conn.prepareStatement(sql);
				pst.setInt(1, uid);
				pst.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				BaseDao.close(conn, pst, null);
			}
		}
	}

}

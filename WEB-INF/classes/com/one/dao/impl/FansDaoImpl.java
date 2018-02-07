package com.one.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.one.dao.BaseDao;
import com.one.dao.FansDao;
import com.one.entity.User;

public class FansDaoImpl implements FansDao {
	/**
	 * 获取所有的粉丝
	 * 
	 * @param uid
	 * @return
	 */
	@Override
	public List<User> getFans(int uid) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<User>();
		String sql = "select DISTINCT uid,mid from fans where uid=?" ;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, uid);
			rs = pst.executeQuery();
			while (rs.next()) {
				User muser = new User();
				muser.setUid(rs.getInt("mid"));
				list.add(muser);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
		}
		return list;
	}

	/**
	 * 获取所有的关注用户
	 * 
	 * @param uid
	 * @return
	 */
	@Override
	public List<User> getFollow(int uid) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<User>();
		String sql = "select DISTINCT uid,mid from fans where mid=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, uid);
			rs = pst.executeQuery();
			while (rs.next()) {
				User muser = new User();
				muser.setUid(rs.getInt("uid"));
				list.add(muser);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
		}
		return list;
	}

	/**
	 * 添加关注
	 */
	@Override
	public int addFans(int uid, int mid) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "insert into fans(uid,mid)values(?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, uid);
			pst.setInt(2, mid);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
		}
		return result;
	}

	/**
	 * 取消关注
	 */
	@Override
	public int deleteFans(int uid, int fid) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "delete from fans where uid=? and mid=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, uid);
			pst.setInt(2, fid);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
		}
		return result;
	}

	/**
	 * 查看用户的粉丝数
	 */
	@Override
	public int getCountByUid(int uid) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "SELECT COUNT(*) counts FROM fans where uid=? ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, uid);
			rs = pst.executeQuery();
			if (rs.next()) {
				rs.getInt("counts");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
		}
		return result;
	}

	/**
	 * 查看用户关注的博主数
	 */
	@Override
	public int getCountByMid(int mid) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "SELECT COUNT(uid) counts FROM fans where mid=? distinct";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, mid);
			// ---------------有错------ 已改---------
			rs = pst.executeQuery();
			if (rs.next()) {
				result = rs.getInt("counts");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
		}
		return result;
	}

	@Override
	public boolean isFans(int uid, int fid) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean b = false;
		String sql = "SELECT * FROM fans where uid=? and mid=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, uid);
			pst.setInt(2, fid);
			rs = pst.executeQuery();
			if (rs.next()) {
				b = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
		}

		return b;
	}

	@Override
	public int deleteByUid(int uid) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "delete from fans where uid=? or mid=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, uid);
			pst.setInt(2, uid);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
		}
		return result;
	}

}

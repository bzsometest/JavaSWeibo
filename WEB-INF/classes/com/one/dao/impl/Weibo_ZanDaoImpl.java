package com.one.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.one.dao.BaseDao;
import com.one.dao.Weibo_ZanDao;

public class Weibo_ZanDaoImpl implements Weibo_ZanDao {

	@Override
	public int addZan(int wid, int uid) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "insert into weibo_zan(wid,uid)values(?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, wid);
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

	@Override
	public int DeleteZan(int wid, int uid) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "delete from weibo_zan where wid=? and uid=?";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, wid);
			pst.setInt(2, uid);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
		}
		return result;
	}

	@Override
	public int deleteZanBywid(int wid) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "delete from weibo_zan where wid=?";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, wid);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
		}
		return result;
	}

	@Override
	public boolean isZan(int wid, int uid) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean b = false;
		String sql = "select  * from weibo_zan where wid=? and uid=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, wid);
			pst.setInt(2, uid);
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
	public int countZan(int wid) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "SELECT COUNT(*) counts FROM weibo_zan where wid=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, wid);
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
	public int deleteByWid(int wid) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "delete from weibo_zan where wid=?";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, wid);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
		}
		return result;
	}

	@Override
	public int deleteByUid(int uid) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "delete from weibo_zan where uid=?";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, uid);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
		}
		return result;
	}

}

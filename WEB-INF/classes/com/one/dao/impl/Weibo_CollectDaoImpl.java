package com.one.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.one.dao.BaseDao;
import com.one.dao.Weibo_CollectDao;
import com.one.entity.Weibo;
import com.one.entity.Weibo_Collect;

public class Weibo_CollectDaoImpl implements Weibo_CollectDao {

	@Override
	public int addCollect(int wid, int uid) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "insert into weibo_collect(wid,uid)values(?,?)";
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
	public int DeleteCollect(int wid, int uid) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "delete from weibo_collect where wid=? and uid=?";
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
	public int deleteCollectBywid(int wid) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "delete from weibo_collect where wid=?";
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
	public boolean isCollect(int wid, int uid) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean b = false;
		String sql = "select  * from weibo_collect where wid=? and uid=?";
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
	public int countCollect(int wid) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "SELECT COUNT(*) counts FROM weibo_collect where wid=?";
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
		String sql = "delete from weibo_collect where wid=?";
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
		String sql = "delete from weibo_collect where uid=?";
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

	@Override
	public List<Weibo_Collect> getByCollect(int uid) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Weibo_Collect> list = new ArrayList<Weibo_Collect>();
		String sql = "select DISTINCT wid from weibo_collect where uid=? ORDER by wid DESC";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, uid);
			rs = pst.executeQuery();
			while (rs.next()) {
				Weibo_Collect wc = new Weibo_Collect();
				Weibo weibo = new Weibo();
				weibo.setWid(rs.getInt("wid"));
				wc.setWeibo(weibo);
				list.add(wc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
		}
		return list;
	}

}

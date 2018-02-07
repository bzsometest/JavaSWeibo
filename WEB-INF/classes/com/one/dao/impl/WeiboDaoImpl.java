package com.one.dao.impl;

import java.sql.*;
import java.util.*;

import com.one.dao.BaseDao;
import com.one.dao.WeiboDao;
import com.one.entity.User;
import com.one.entity.Weibo;

/**
 * 微博dao实现类
 * 
 * @author Zhao TianXin
 *
 */
public class WeiboDaoImpl implements WeiboDao {

	@Override
	public Weibo get(int wid) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Weibo weibo = null;
		String sql = "select * from weibo where wid=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, wid);
			rs = pst.executeQuery();
			if (rs.next()) {
				weibo = new Weibo();
				weibo.setWid(rs.getInt("wid"));
				User user = new User();
				user.setUid(rs.getInt("uid"));
				weibo.setUser(user);
				weibo.setContent(rs.getString("content"));
				weibo.setTime(rs.getDate("time"));
				weibo.setImg(rs.getString("img"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
		}
		return weibo;
	}

	@Override
	public int add(Weibo weibo) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "insert into weibo(uid,content,img) values(?,?,?)";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, weibo.getUser().getUid());
			pst.setString(2, weibo.getContent());
			pst.setString(3, weibo.getImg());
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
	public int delete(int wid) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "delete from weibo where wid=?";
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
	public int alter(int wid, Weibo weibo) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "update set weibo content=? where wid=?";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);

			pst.setString(1, weibo.getContent());
			pst.setInt(2, wid);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
		}
		return result;
	}

	@Override
	public int getCount() {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM weibo ";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			result = rs.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
		}
		return result;
	}

	@Override
	public List<Weibo> get(User user) {
		List<Weibo> list = new ArrayList<Weibo>();
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Weibo weibo = null;
		String sql = "select * from weibo where uid=?  ORDER by wid DESC";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, user.getUid());
			rs = pst.executeQuery();
			while (rs.next()) {
				weibo = new Weibo();
				weibo.setWid(rs.getInt("wid"));
				User users = new User();
				users.setUid(rs.getInt("uid"));
				weibo.setUser(users);
				weibo.setContent(rs.getString("content"));
				weibo.setTime(rs.getTimestamp("time"));
				weibo.setImg(rs.getString("img"));
				list.add(weibo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
		}
		return list;
	}

	@Override
	public List<Weibo> getAll() {
		List<Weibo> list = new ArrayList<Weibo>();
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Weibo weibo = null;
		String sql = "select * from weibo ORDER by wid DESC";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				weibo = new Weibo();
				weibo.setWid(rs.getInt("wid"));
				int uid = rs.getInt("uid");
				User user = new UserDaoImpl().findByID(uid);
				weibo.setUser(user);
				weibo.setContent(rs.getString("content"));
				weibo.setTime(rs.getTimestamp("time"));
				weibo.setImg(rs.getString("img"));
				list.add(weibo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
		}
		return list;
	}

	@Override
	public List<Weibo> get(int start, int count) {
		List<Weibo> list = new ArrayList<Weibo>();
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Weibo weibo = null;
		String sql = "select * from weibo order by time limit ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, start - 1);
			pst.setInt(2, count);
			rs = pst.executeQuery();
			while (rs.next()) {
				weibo = new Weibo();
				weibo.setWid(rs.getInt("wid"));
				User user = new User();
				user.setUid(rs.getInt("uid"));
				weibo.setUser(user);
				weibo.setContent(rs.getString("content"));
				weibo.setTime(rs.getTimestamp("time"));
				weibo.setImg(rs.getString("img"));
				list.add(weibo);
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
	 * 查询用户的微博数
	 */
	@Override
	public int getCount(int uid) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;

		String sql = "SELECT COUNT(*) counts FROM weibo where uid=?";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, uid);
			rs = pst.executeQuery();
			if (rs.next()) {
				result = rs.getInt("counts");
			}
			result = rs.getRow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
		}
		return result;
	}

	/**
	 * 显示用户关注的博主的微博
	 */
	@Override
	public List<Weibo> getByMid(int uid) {
		List<Weibo> list = new ArrayList<Weibo>();
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Weibo weibo = null;
		String sql = "SELECT * FROM  weibo,fans WHERE weibo.uid=fans.uid AND fans.mid=? ORDER by wid DESC";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, uid);
			rs = pst.executeQuery();
			while (rs.next()) {
				weibo = new Weibo();
				weibo.setWid(rs.getInt("wid"));
				User users = new User();
				users.setUid(rs.getInt("uid"));
				weibo.setUser(users);
				weibo.setContent(rs.getString("content"));
				weibo.setTime(rs.getTimestamp("time"));
				weibo.setImg(rs.getString("img"));
				list.add(weibo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
		}
		return list;
	}

	@Override
	public int deleteByUid(int uid) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "delete from weibo where wid=?";
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

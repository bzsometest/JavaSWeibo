package com.one.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.one.dao.BaseDao;
import com.one.dao.DiscussDao;
import com.one.entity.Discuss;
import com.one.entity.User;
import com.one.entity.Weibo;

public class DiscussDaoImpl implements DiscussDao {

	@Override
	public Discuss get(int did) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Discuss discuss = null;
		String sql = "select * from discuss where did=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, did);
			rs = pst.executeQuery();
			if (rs.next()) {
				discuss = new Discuss();
				discuss.setDid(rs.getInt("did"));
				// 获取微博的id并传值
				Weibo weibo = new Weibo();
				weibo.setWid(rs.getInt("wid"));
				discuss.setWeibo(weibo);
				// 获取用户的id并传值
				User user = new User();
				user.setUid(rs.getInt("uid"));
				discuss.setUser(user);
				discuss.setContent(rs.getString("content"));
				discuss.setTime(rs.getTimestamp("time"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.close(conn, pst, rs);
		}
		return discuss;
	}

	/**
	 * 增加评论
	 */
	@Override
	public int add(Discuss discuss) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "insert into discuss(wid,uid,content) values(?,?,?)";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, discuss.getWeibo().getWid());
			pst.setInt(2, discuss.getUser().getUid());
			pst.setString(3, discuss.getContent());
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
	 * 删除评论
	 */
	@Override
	public int delete(int did) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "delete from discuss where did=?";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, did);
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
	 * 根据微博号删除评论
	 * 
	 * @param did
	 * @return
	 */
	@Override
	public int deleteByWid(int wid) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "delete from discuss where wid=?";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, wid);
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
	public List<Discuss> getWid(int wid) {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Discuss discuss = null;
		List<Discuss> list = new ArrayList<Discuss>();
		String sql = "select * from discuss where wid=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, wid);
			rs = pst.executeQuery();
			while (rs.next()) {
				discuss = new Discuss();
				discuss.setDid(rs.getInt("did"));
				// 获取微博的id并传值
				Weibo weibo = new Weibo();
				weibo.setWid(rs.getInt("wid"));
				discuss.setWeibo(weibo);
				// 获取用户的id并传值
				User user = new User();
				user.setUid(rs.getInt("uid"));
				discuss.setUser(user);
				discuss.setContent(rs.getString("content"));
				discuss.setTime(rs.getTimestamp("time"));
				list.add(discuss);
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
	public List<Discuss> getAll() {
		Connection conn = BaseDao.getConnction();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Discuss discuss = null;
		List<Discuss> list = new ArrayList<Discuss>();
		String sql = "select * from discuss";
		try {
			pst = conn.prepareStatement(sql);

			rs = pst.executeQuery();
			while (rs.next()) {
				discuss = new Discuss();
				discuss.setDid(rs.getInt("did"));
				// 获取微博的id并传值
				Weibo weibo = new Weibo();
				weibo.setWid(rs.getInt("wid"));
				discuss.setWeibo(weibo);
				// 获取用户的id并传值
				User user = new User();
				user.setUid(rs.getInt("uid"));
				discuss.setUser(user);
				discuss.setContent(rs.getString("content"));
				discuss.setTime(rs.getTimestamp("time"));
				list.add(discuss);
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

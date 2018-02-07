package com.one.dao;

import java.util.List;

import com.one.entity.Weibo_Collect;

public interface Weibo_CollectDao {
	/**
	 * 添加收藏
	 * @param weibo_Collect
	 * @return
	 */
   public int addCollect (int wid,int uid);
   /**
	 * 取消收藏
	 * @param weibo_Collect
	 * @return
	 */
   public int DeleteCollect (int wid,int uid);
   /**
    * 是否收藏
    * @param uid
    * @param wid
    * @return
    */
   public boolean isCollect (int uid,int wid);
   /**
    * 根据wid删除收藏
    * @param wid
    * @return
    */
   public  int deleteCollectBywid(int wid);
   /**
    * 被收藏的次数
    * @param wid
    * @param uid
    * @return
    */
    public int countCollect(int wid);
    /**
     * 根据wid删除收藏
     * @param wid
     * @return
     */
    public int deleteByWid(int wid);
    /**
     * 根据uid收藏
     * @param uid
     * @return
     */
	public int deleteByUid(int uid);
	/**
	 * 获取用户的收藏
	 * @param uid
	 * @return
	 */
	public List<Weibo_Collect> getByCollect(int uid);
}

package com.one.dao;


public interface Weibo_ZanDao {
	/**
	 * 增加点赞
	 * @param weibo_Zan
	 * @return
	 */
   public int addZan(int wid,int uid);
   /**
    * 取消点赞
    * @param weibo_Zan
    * @return
    */
   public int DeleteZan(int wid,int uid);
   /**
    * 是否点赞
    * @param uid
    * @param wid
    * @return
    */
   public boolean isZan(int uid,int wid);
   /**
    * 删除点赞
    * @param wid
    * @return
    */
   public  int deleteZanBywid(int wid);
   /**
    * 获取被点赞的次数
    * @param wid
    * @param uid
    * @return
    */
   public int countZan(int wid);
   /**
    * 根据wid删除点赞
    * @param wid
    * @return
    */
   public int deleteByWid(int wid);
   /**
    * 根据uid删除点赞
    * @param uid
    * @return
    */
    public int deleteByUid(int uid);
}

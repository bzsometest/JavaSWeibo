package com.one.service;

import java.util.List;

import com.one.entity.User;
import com.one.entity.Weibo;
public interface Weibo_CollectService {
	/**
	 * 收藏微博
	 * @param weibo_Collect
	 * @return
	 */
         public int addCollect(Weibo weibo,User user);
         /**
          * 取消收藏
          * @param weibo_Collect
          * @return
          */
         public int cancleCollect(Weibo weibo,User user);
          /**
          * 是否收藏
          * @param weibo_Collect
          * @return
          */
         public boolean isCollect(Weibo weibo,User user);
         /**
          * 此微博被收藏的次数
          * @param weibo_Collect
          * @return
          */
         public int countCollect(Weibo weibo);
           /**
          * 获取用户收藏的微博
          * @param user
          * @return
          */
          public  List<Weibo> getByCollect(User user);
}

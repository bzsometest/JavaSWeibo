package com.one.service;

import com.one.entity.User;
import com.one.entity.Weibo;

public interface Weibo_ZanService {
	        /**
	         * 点赞
	         * @param weibo_Zan
	         * @return
	         */
            public int addZan(Weibo weibo,User user);
            /**
             * 取消点赞
             * @param weibo_Zan
             * @return
             */
            public int cancelZan(Weibo weibo,User user);
            /**
             * 是否点赞
             * @param weibo_Zan
             * @return
             */
            public boolean isZan(Weibo weibo,User user);
            /**
             *此微博被点赞次数
             * @param weibo_Zan
             * @return
             */
            public int countZan(Weibo weibo);
}

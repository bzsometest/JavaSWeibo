# Host: www.bzchao.com  (Version: 5.5.58)
# Date: 2018-01-08 21:56:42
# Generator: MySQL-Front 5.3  (Build 4.234)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `userpass_encode` varchar(100) NOT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `sign` varchar(255) DEFAULT '暂无个人介绍',
  `type` varchar(255) DEFAULT 'common',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

#
# Data for table "user"
#

INSERT INTO `user` VALUES (1,'chao','E10ADC3949BA59ABBE56E057F20F883E','13981681231','153355720@qq.com','愿漂泊的人都有酒喝 愿孤独的人都会唱歌','common'),(6,'csx','75D1F0ED88D3D6C477B482FC159BF842','18215539469','568984795@qq.com','f','common'),(9,'ttt','96E79218965EB72C92A549DD5A330112','18384585959','1058846562@qq.com',NULL,'common'),(11,'exio','E10ADC3949BA59ABBE56E057F20F883E',NULL,'1751736289@qq.com',NULL,'common'),(13,'wak666','5B1B68A9ABF4D2CD155C81A9225FD158','18782292112','5955403577@qq.com','666666','common'),(14,'admin','E10ADC3949BA59ABBE56E057F20F883E',NULL,NULL,'我是管理员，我最大！','admin');

#
# Structure for table "find_userpass"
#

DROP TABLE IF EXISTS `find_userpass`;
CREATE TABLE `find_userpass` (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `phone_code` varchar(20) DEFAULT NULL,
  `email_code` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`fid`),
  KEY `uid` (`uid`),
  CONSTRAINT `fpass_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=249 DEFAULT CHARSET=utf8;

#
# Data for table "find_userpass"
#

INSERT INTO `find_userpass` VALUES (248,1,NULL,'OD8DUFAI');

#
# Structure for table "fans"
#

DROP TABLE IF EXISTS `fans`;
CREATE TABLE `fans` (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `mid` int(11) DEFAULT NULL,
  PRIMARY KEY (`fid`),
  KEY `uid` (`uid`),
  KEY `fans_ibfk_2` (`mid`),
  CONSTRAINT `fans_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`),
  CONSTRAINT `fans_ibfk_2` FOREIGN KEY (`mid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

#
# Data for table "fans"
#

INSERT INTO `fans` VALUES (5,1,11);

#
# Structure for table "weibo"
#

DROP TABLE IF EXISTS `weibo`;
CREATE TABLE `weibo` (
  `wid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `content` varchar(1024) NOT NULL DEFAULT '',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`wid`),
  KEY `uid` (`uid`),
  CONSTRAINT `weibo_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

#
# Data for table "weibo"
#

INSERT INTO `weibo` VALUES (35,1,'1982年以来，在天安门广场升降国旗的任务一直由武警国旗护卫队担负着。','2018-01-03 12:42:41',NULL),(36,1,'今天终于完成了项目的基本功能，感觉收获颇多。ヽ(ˋ▽ˊ)ノ ','2018-01-05 17:40:03',NULL),(38,1,'这是菁英微博第二版V2.1。界面优化，提升数据库安全。','2018-01-05 20:10:33',NULL),(39,1,'修复游客查看评论，增加管理员后台登录。','2018-01-07 01:08:58',NULL),(40,1,'新增Html标签显示，解决内容中有<div>,<ifrom>等标签，对显示效果的影响。','2018-01-08 00:14:14',NULL),(43,1,'转发微博：新增Html标签显示，解决内容中有<div>,<ifrom>等标签，对显示效果的影响。','2018-01-08 20:48:50',NULL);

#
# Structure for table "discuss"
#

DROP TABLE IF EXISTS `discuss`;
CREATE TABLE `discuss` (
  `did` int(11) NOT NULL AUTO_INCREMENT,
  `wid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`did`),
  KEY `wid` (`wid`),
  KEY `uid` (`uid`),
  CONSTRAINT `discuss_ibfk_1` FOREIGN KEY (`wid`) REFERENCES `weibo` (`wid`),
  CONSTRAINT `discuss_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

#
# Data for table "discuss"
#

INSERT INTO `discuss` VALUES (1,38,1,'修复游客评论，增加管理员登录。','2018-01-07 01:05:34'),(2,39,11,'666','2018-01-07 01:12:54'),(3,39,1,'发布的项目，有映射地址发生改变，还得重启Tomcat┑(~。。~)┍','2018-01-07 01:17:49'),(10,40,1,'对微博和评论内容进行了编码，使用了工具类','2018-01-08 00:31:11'),(11,40,1,'测试标签</a><br></div><div>','2018-01-08 00:31:42'),(12,40,1,'同时解决了Json传输内容中有引号，逗号等问题','2018-01-08 00:33:06'),(13,40,1,'爸春节快乐','2018-01-08 01:00:33');

#
# Structure for table "weibo_collect"
#

DROP TABLE IF EXISTS `weibo_collect`;
CREATE TABLE `weibo_collect` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `wid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`cid`),
  KEY `uid` (`uid`),
  KEY `wid` (`wid`),
  UNIQUE KEY `weibo_collect_uw_1` (`uid`,`wid`),
  CONSTRAINT `weibo_collect_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`),
  CONSTRAINT `weibo_collect_ibfk_2` FOREIGN KEY (`wid`) REFERENCES `weibo` (`wid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Data for table "weibo_collect"
#


#
# Structure for table "weibo_zan"
#

DROP TABLE IF EXISTS `weibo_zan`;
CREATE TABLE `weibo_zan` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `wid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`zid`),
  KEY `uid` (`uid`),
  KEY `wid` (`wid`),
  UNIQUE KEY `weibo_zan_uw_1` (`uid`,`wid`),
  CONSTRAINT `weibo_zan_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`),
  CONSTRAINT `weibo_zan_ibfk_2` FOREIGN KEY (`wid`) REFERENCES `weibo` (`wid`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

#
# Data for table "weibo_zan"
#


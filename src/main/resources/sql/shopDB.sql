DROP DATABASE IF EXISTS shopDB;
CREATE DATABASE shopDB DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE shopDB;
DROP TABLE IF EXISTS `T_user`;
CREATE TABLE `T_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `name` varchar(25) NOT NULL COMMENT '用户昵称',
  `trueName` varchar(25) NOT NULL COMMENT '真实姓名',
  `cardId` varchar(18) NOT NULL COMMENT '身份证号码',
  `email` varchar(50) NOT NULL COMMENT '电子邮箱',
  `telephone` varchar(25) NOT NULL COMMENT '电话号码',
  `qqId` varchar(25) DEFAULT NULL COMMENT 'QQ号码',
  `pwd` varchar(25) NOT NULL DEFAULT '123456' COMMENT '密码',
  `regTime` datetime NOT NULL,
  `question` varchar(60) DEFAULT NULL COMMENT '密码提问',
  `answer` varchar(100) DEFAULT NULL COMMENT '密码答案',
  `state` int(11) NOT NULL DEFAULT '1' COMMENT '是否冻结',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基本信息表';
insert into T_user ( `id`, `state`, `name`, `pwd`, `regTime`, `answer`, `cardId`, `question`, `telephone`, `qqId`, `email`, `trueName`) values ( '1', '1', 'lx', '123456', '2017-11-29', '不告诉你', '220103198203152514', '您的生日', '13609764565', '212319800', 'lx0315@163.com', '张丽');
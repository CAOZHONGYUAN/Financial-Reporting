/*
Navicat MySQL Data Transfer

Source Server         : dad
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : financial

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-03-30 15:09:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill` (
  `billId` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '账单id',
  `reportUserId` int(11) DEFAULT NULL COMMENT '报账人id(外键)',
  `submitDatetime` datetime DEFAULT NULL COMMENT '报账发起时间',
  `description` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '报账说明',
  `remarks` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注(由审核人填写:未通过理由)',
  `statusId` int(255) DEFAULT '0' COMMENT '状态码',
  `typeId` int(11) DEFAULT NULL COMMENT '报账类型',
  `countCost` decimal(10,0) DEFAULT NULL COMMENT '账单金额总计',
  PRIMARY KEY (`billId`) USING BTREE,
  KEY `Bill_ReportUserId_FK` (`reportUserId`) USING BTREE,
  KEY `Bill_Status_FK` (`statusId`) USING BTREE,
  KEY `Bill_Type_FK` (`typeId`),
  CONSTRAINT `Bill_ReportUserId_FK` FOREIGN KEY (`reportUserId`) REFERENCES `user` (`userId`),
  CONSTRAINT `Bill_Status_FK` FOREIGN KEY (`statusId`) REFERENCES `status` (`statusId`),
  CONSTRAINT `Bill_Type_FK` FOREIGN KEY (`typeId`) REFERENCES `type` (`typeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of bill
-- ----------------------------
INSERT INTO `bill` VALUES ('017e4249-5f38-46e3-a99a-d128c431193a', '1', '2019-03-29 20:34:50', '差旅费用', null, '1', '1', '1100');
INSERT INTO `bill` VALUES ('09436d0d-c1c8-4cb5-8968-4f3cff17c9b9', '12', '2019-03-29 20:45:46', '信息审核出差费', '', '4', '1', '1400');
INSERT INTO `bill` VALUES ('09617d86-2e0c-45a6-a3d4-ecd767f6b2ec', '16', '2019-03-30 09:54:03', 'ert', null, '4', '2', '888');
INSERT INTO `bill` VALUES ('1439343a-a2ea-4a3e-b5ad-4a32e3008d92', '16', '2019-03-30 08:31:51', 'sasa', '', '4', '1', '900');
INSERT INTO `bill` VALUES ('19c8c162-9e3b-4fd8-975f-4d0ffd956514', '16', '2019-03-30 09:11:00', 'add', null, '1', '4', '200');
INSERT INTO `bill` VALUES ('223c0b8b-bbcf-4bf5-9334-59a84939dc99', '16', '2019-03-30 09:10:01', 'asdd', null, '1', '2', '600');
INSERT INTO `bill` VALUES ('24371256-7f1c-41ec-84db-a76f4d487759', '16', '2019-01-29 21:08:00', '6666', null, '4', '3', '6666');
INSERT INTO `bill` VALUES ('2d353e57-bf3a-4a09-a5cb-a545e44afcee', '1', '2019-03-29 20:34:22', '空调费', null, '1', '4', '200');
INSERT INTO `bill` VALUES ('314e824c-f305-40a3-9787-2aad5358b570', '16', '2019-03-30 09:21:33', 'asas', '', '3', '1', '423');
INSERT INTO `bill` VALUES ('31c9ed30-724a-4744-9d42-dd26d8dfd5b2', '16', '2019-03-30 10:32:13', 'asas', null, '0', '2', '500');
INSERT INTO `bill` VALUES ('36e976e2-b30a-411d-80c6-56254e41ec18', '16', '2019-03-30 09:10:48', 'asdfg', null, '1', '4', '200');
INSERT INTO `bill` VALUES ('37086803-9ec4-4de7-993f-539f5ab390cd', '16', '2019-03-30 09:27:01', 'as', null, '0', '3', '999');
INSERT INTO `bill` VALUES ('41fd5d8c-04f5-4cc7-9f85-5f1e5da78ac8', '16', '2019-03-30 08:55:37', 'adfg', null, '1', '2', '300');
INSERT INTO `bill` VALUES ('4aae4e49-239a-4e13-bc88-7d063a3ceb2c', '16', '2019-03-30 08:37:30', 'sdf', '', '4', '3', '200');
INSERT INTO `bill` VALUES ('53ef1cd6-1a9e-4e17-9b98-d76408605fe7', '16', '2019-03-30 09:49:17', 'adfg', '', '4', '2', '333');
INSERT INTO `bill` VALUES ('5580cc14-62e2-409d-a428-848870941ea0', '1', '2019-03-29 20:39:54', '搬运费', '', '4', '4', '400');
INSERT INTO `bill` VALUES ('5771162c-dcc0-4883-a270-69dae171017e', '1', '2019-03-29 20:27:17', '成都到昆明差旅', null, '1', '1', '1400');
INSERT INTO `bill` VALUES ('594e7a13-4d57-4414-a77f-ac97086f09ff', '1', '2019-03-29 20:35:41', '高温补贴', null, '1', '2', '200');
INSERT INTO `bill` VALUES ('5b419f20-7b86-4079-9e5f-03ab57a9a904', '16', '2019-03-30 08:35:56', 'asdf', null, '0', '1', '1900');
INSERT INTO `bill` VALUES ('603e78b6-a09c-4c0b-bb16-438b54c3dcd1', '16', '2019-03-30 08:43:38', 'asdf', null, '1', '1', '720');
INSERT INTO `bill` VALUES ('67d0e027-f8e4-4437-8954-9fc7102d8e8a', '16', '2019-03-30 09:51:54', 'asdf', null, '4', '2', '111');
INSERT INTO `bill` VALUES ('698c8ba9-be54-423c-b445-c3c02c50863b', '16', '2019-03-30 08:55:54', 'asdsf', null, '1', '3', '300');
INSERT INTO `bill` VALUES ('6aa7b66f-0cde-48f4-8978-72cc4f449ac3', '16', '2019-03-30 08:28:59', '飞机票', null, '4', '1', '1000');
INSERT INTO `bill` VALUES ('6bce9b43-d368-447f-973c-514d37fbe0d2', '16', '2019-03-30 08:56:13', '1231', null, '1', '4', '300');
INSERT INTO `bill` VALUES ('6d6e2e83-237b-43fe-bb66-e420ab0724bf', '16', '2019-03-30 09:41:48', '汇报', null, '0', '2', '2000');
INSERT INTO `bill` VALUES ('6e4731a4-8e6b-4a18-8a76-322920fe38db', '16', '2019-03-30 08:29:32', '火车票', null, '4', '1', '1600');
INSERT INTO `bill` VALUES ('70095b97-06e4-4631-a38c-4152a2bae419', '1', '2019-03-29 20:36:44', '交通费用', null, '4', '3', '666');
INSERT INTO `bill` VALUES ('7146e142-84f7-4e25-811e-816971f87a79', '16', '2019-03-30 08:34:57', 'asdf', null, '0', '1', '1500');
INSERT INTO `bill` VALUES ('746d8a5d-2b56-4cad-9850-b92d5753597b', '1', '2019-03-29 20:30:56', '取暖费', null, '1', '4', '500');
INSERT INTO `bill` VALUES ('76649231-c83b-44c2-81b3-8058531c66ab', '16', '2019-07-30 08:43:51', 'asdf', null, '4', '3', '300');
INSERT INTO `bill` VALUES ('79a3fbd7-1d79-44be-ba10-068888a8482d', '1', '2019-03-29 20:39:19', '宣传费用', 'qweasddsa', '-1', '4', '500');
INSERT INTO `bill` VALUES ('82990369-6af1-4acf-a54c-62069baf1677', '1', '2019-03-29 20:37:52', '项目和奖金', '', '2', '2', '555');
INSERT INTO `bill` VALUES ('840b5c74-bce1-45e5-8dc6-3ab4ca4888c2', '16', '2019-03-30 08:30:18', '汽车', null, '1', '1', '1000');
INSERT INTO `bill` VALUES ('8853e6b7-fab8-4c73-b907-06a3d6dfddeb', '1', '2019-03-29 20:22:40', '成都到云南出差费用', null, '1', '1', '2600');
INSERT INTO `bill` VALUES ('898d73e4-fd8e-436a-9243-a338d8a71a9b', '16', '2019-03-30 08:37:18', 'asdfg', null, '1', '2', '600');
INSERT INTO `bill` VALUES ('8c202223-d88b-44e5-9c0b-1a356940e481', '1', '2019-03-29 20:31:57', '出差费', null, '4', '1', '1200');
INSERT INTO `bill` VALUES ('8ec18c65-ce04-4bcb-b896-4d8a20126b0e', '1', '2019-03-29 20:33:43', '外出办公', null, '4', '3', '600');
INSERT INTO `bill` VALUES ('8fdd0831-b37f-4429-a8bc-4f87e2edb6b9', '16', '2019-03-30 09:10:32', 'asdfg', null, '1', '3', '200');
INSERT INTO `bill` VALUES ('9076b6f4-f56d-45e1-beb3-3b06eafb69f9', '16', '2019-03-30 09:53:11', '121', null, '4', '2', '777');
INSERT INTO `bill` VALUES ('934d105e-a222-4604-bd12-a3c4789f0484', '16', '2019-03-30 09:19:43', '6666', '', '2', '1', '2664');
INSERT INTO `bill` VALUES ('9c6a6ebd-8b07-475f-b461-88c36ac762ea', '16', '2019-03-30 08:31:23', 'asassa', null, '0', '1', '400');
INSERT INTO `bill` VALUES ('9cc01f1c-f53b-42f0-be09-ab6e024c652d', '16', '2019-03-30 08:33:37', 'kjhg', null, '0', '1', '1800');
INSERT INTO `bill` VALUES ('b64683c0-529e-4341-80db-b38dc82d547c', '16', '2019-09-30 08:37:50', 'asdf', null, '0', '4', '200');
INSERT INTO `bill` VALUES ('b7334e4d-c42c-42d1-ae1e-6d581dad3297', '16', '2019-03-30 08:56:30', 'jhgf', null, '1', '4', '200');
INSERT INTO `bill` VALUES ('b8af4c6d-6cc0-4ad2-b052-00c154c47f13', '1', '2019-03-29 20:33:00', '加班费', null, '1', '2', '300');
INSERT INTO `bill` VALUES ('bd5339ef-e6d0-4881-b182-85f50b27900a', '16', '2019-03-30 08:34:35', 'dfgh', null, '0', '1', '800');
INSERT INTO `bill` VALUES ('c4742218-3b2c-482e-841f-65a91dde6617', '1', '2019-03-29 20:38:43', '饮水费', '', '4', '4', '200');
INSERT INTO `bill` VALUES ('ca7de7cd-795a-4540-a867-2282af4715b9', '16', '2019-03-30 08:33:08', 'sfg', null, '0', '1', '1800');
INSERT INTO `bill` VALUES ('cfeede9c-cdcb-4ca2-ac03-ebef981e28c1', '1', '2019-03-29 20:36:19', '交通费用', null, '1', '3', '600');
INSERT INTO `bill` VALUES ('d0a3d2ab-b9c2-4dc8-994d-c61c08b99547', '16', '2019-03-30 09:50:55', '1231', null, '4', '2', '333');
INSERT INTO `bill` VALUES ('d1d33208-8d82-4cc2-8482-73b0c55a4016', '16', '2019-03-29 21:08:42', '9999', '', '4', '2', '9999');
INSERT INTO `bill` VALUES ('e229ed24-da2c-4598-9870-63f7b4512582', '16', '2019-03-30 08:55:07', 'af', null, '1', '1', '2422');
INSERT INTO `bill` VALUES ('e62428ce-edf4-43a6-b3e7-18971814ff7c', '16', '2019-03-30 10:31:26', 'asa', '', '2', '1', '800');
INSERT INTO `bill` VALUES ('ed45bd30-0393-402a-8351-84eb0c6124ee', '16', '2019-03-30 09:09:49', 'klkl', null, '1', '2', '200');
INSERT INTO `bill` VALUES ('eecb91cd-2a39-4dc9-8f7e-602d8354f2d1', '1', '2019-03-29 20:37:13', '加班费用补贴', '', '2', '2', '200');
INSERT INTO `bill` VALUES ('f1c7c9f8-7cdc-4846-8d03-61dd6d6f964c', '16', '2019-03-30 09:24:32', '123', null, '0', '2', '300');
INSERT INTO `bill` VALUES ('f7449e4f-f666-4a38-b4c1-25865dcb2590', '16', '2019-03-30 09:23:42', 'asfhj', '', '4', '1', '4123');
INSERT INTO `bill` VALUES ('f75d0ec9-006c-4964-969c-12c6d61b6b1d', '16', '2019-03-30 08:38:04', 'asasa', null, '0', '4', '120');
INSERT INTO `bill` VALUES ('f7f0c1ff-5ed7-4c20-94c8-3eb9522ca8d5', '1', '2019-03-29 20:28:53', '项目奖', null, '1', '2', '500');
INSERT INTO `bill` VALUES ('f989946f-bf69-4377-897a-462e5edc96f2', '16', '2019-03-30 09:10:13', 'aadd', null, '1', '3', '600');
INSERT INTO `bill` VALUES ('f99892de-781a-4224-b1d6-5091d782dd74', '1', '2019-03-29 20:29:35', '车费', null, '1', '3', '300');

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `deptId` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门号',
  `deptName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '部门名',
  `userId` int(11) DEFAULT NULL COMMENT '部门主管id',
  PRIMARY KEY (`deptId`) USING BTREE,
  KEY `User_Dept_FK` (`userId`) USING BTREE,
  CONSTRAINT `User_Dept_FK` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('10', '人事部', '6');
INSERT INTO `dept` VALUES ('20', '财务部', null);
INSERT INTO `dept` VALUES ('30', '产品策划部', '8');
INSERT INTO `dept` VALUES ('40', '软件开发部', '9');
INSERT INTO `dept` VALUES ('50', '销售部', '10');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `permId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8 NOT NULL COMMENT '资源名称',
  `type` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '资源类型：menu,button,',
  `url` varchar(128) CHARACTER SET utf8 NOT NULL COMMENT '访问url地址',
  `percode` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '权限代码字符串',
  `parentid` bigint(20) DEFAULT NULL COMMENT '父结点id',
  `parentids` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '父结点id列表串',
  `sortstring` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '排序号',
  `available` char(1) CHARACTER SET utf8 DEFAULT NULL COMMENT '是否可用,1：可用，0不可用',
  PRIMARY KEY (`permId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '用户列表', 'menu', '/admin/adminlist.do', 'admin:query', '1', null, null, '1');

-- ----------------------------
-- Table structure for perm_role
-- ----------------------------
DROP TABLE IF EXISTS `perm_role`;
CREATE TABLE `perm_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限_角色 id',
  `roleId` int(11) NOT NULL COMMENT '角色外键',
  `permId` int(11) NOT NULL COMMENT '权限外键',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `Perm_Role_FK` (`permId`) USING BTREE,
  KEY `Role_Perm_FK` (`roleId`) USING BTREE,
  CONSTRAINT `Perm_Role_FK` FOREIGN KEY (`permId`) REFERENCES `permission` (`permId`),
  CONSTRAINT `Role_Perm_FK` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of perm_role
-- ----------------------------
INSERT INTO `perm_role` VALUES ('1', '4', '1');

-- ----------------------------
-- Table structure for property
-- ----------------------------
DROP TABLE IF EXISTS `property`;
CREATE TABLE `property` (
  `propertyId` int(11) NOT NULL AUTO_INCREMENT COMMENT '物业管理明细id',
  `useFor` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '报账用途',
  `cost` decimal(10,2) DEFAULT NULL COMMENT '报账金额',
  `depict` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '详细描述',
  `billId` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '对应账单id(外键)',
  `payeeUserId` int(11) DEFAULT NULL COMMENT '收款人id(外键)',
  `invoice` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '发票附件',
  PRIMARY KEY (`propertyId`) USING BTREE,
  KEY `Property_Bill_FK` (`billId`) USING BTREE,
  KEY `Property_User_FK` (`payeeUserId`) USING BTREE,
  CONSTRAINT `Property_User_FK` FOREIGN KEY (`payeeUserId`) REFERENCES `user` (`userId`),
  CONSTRAINT `Property_bill_FK` FOREIGN KEY (`billId`) REFERENCES `bill` (`billId`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of property
-- ----------------------------
INSERT INTO `property` VALUES ('18', '暖气', '500.00', '冬天产热', '746d8a5d-2b56-4cad-9850-b92d5753597b', '4', '');
INSERT INTO `property` VALUES ('19', '冷气', '200.00', '夏天太热', '2d353e57-bf3a-4a09-a5cb-a545e44afcee', '15', '');
INSERT INTO `property` VALUES ('20', '喝水', '200.00', '喝水', 'c4742218-3b2c-482e-841f-65a91dde6617', '4', '');
INSERT INTO `property` VALUES ('21', '奖金', '500.00', '加班人员', '79a3fbd7-1d79-44be-ba10-068888a8482d', '13', '');
INSERT INTO `property` VALUES ('22', '水桶', '400.00', '水桶搬运', '5580cc14-62e2-409d-a428-848870941ea0', '14', '');
INSERT INTO `property` VALUES ('23', 'gfd', '200.00', 'fg', 'b64683c0-529e-4341-80db-b38dc82d547c', '4', 'C:/upload/invoice/x.jpg');
INSERT INTO `property` VALUES ('24', 'wdd', '120.00', 'aadd', 'f75d0ec9-006c-4964-969c-12c6d61b6b1d', '2', '');
INSERT INTO `property` VALUES ('25', 'asas', '300.00', '123132', '6bce9b43-d368-447f-973c-514d37fbe0d2', '5', '');
INSERT INTO `property` VALUES ('26', 'asdsa', '200.00', 'xzx', 'b7334e4d-c42c-42d1-ae1e-6d581dad3297', '4', '');
INSERT INTO `property` VALUES ('27', 'ad', '200.00', '暗帝', '36e976e2-b30a-411d-80c6-56254e41ec18', '16', '');
INSERT INTO `property` VALUES ('28', 'sad', '200.00', 'ass', '19c8c162-9e3b-4fd8-975f-4d0ffd956514', '16', '');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `partName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色名(职位)',
  `content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`roleId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '普通用户', '报账、管理自己账单');
INSERT INTO `role` VALUES ('2', '信息审核员', '报账、用户信息首次审核');
INSERT INTO `role` VALUES ('3', '部门主管', '报账、用户信息的二次审核');
INSERT INTO `role` VALUES ('4', '财务经办人', '报账、审批发放物资');
INSERT INTO `role` VALUES ('5', '超级管理员', '全部权限');

-- ----------------------------
-- Table structure for status
-- ----------------------------
DROP TABLE IF EXISTS `status`;
CREATE TABLE `status` (
  `statusId` int(11) NOT NULL COMMENT '账单状态码',
  `statusName` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '账单状态',
  PRIMARY KEY (`statusId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of status
-- ----------------------------
INSERT INTO `status` VALUES ('-1', '未通过');
INSERT INTO `status` VALUES ('0', '待提交');
INSERT INTO `status` VALUES ('1', '第一次审核');
INSERT INTO `status` VALUES ('2', '第二次审核');
INSERT INTO `status` VALUES ('3', '第三次审核');
INSERT INTO `status` VALUES ('4', '已通过');

-- ----------------------------
-- Table structure for traffic
-- ----------------------------
DROP TABLE IF EXISTS `traffic`;
CREATE TABLE `traffic` (
  `trafficId` int(11) NOT NULL AUTO_INCREMENT COMMENT '公务交通明细id',
  `useFor` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '交通方式',
  `cost` decimal(10,2) DEFAULT NULL COMMENT '报账金额',
  `depict` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '详细描述',
  `billId` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '对应账单id(外键)',
  `payeeUserId` int(11) DEFAULT NULL COMMENT '收款人id(外键)',
  `invoice` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '发票附件',
  PRIMARY KEY (`trafficId`) USING BTREE,
  KEY `Traffic_bill_FK` (`billId`) USING BTREE,
  KEY `Traffic_User_FK` (`payeeUserId`) USING BTREE,
  CONSTRAINT `Traffic_User_FK` FOREIGN KEY (`payeeUserId`) REFERENCES `user` (`userId`),
  CONSTRAINT `Traffic_bill_FK` FOREIGN KEY (`billId`) REFERENCES `bill` (`billId`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of traffic
-- ----------------------------
INSERT INTO `traffic` VALUES ('16', '汽车', '300.00', '出差3天', 'f99892de-781a-4224-b1d6-5091d782dd74', '2', '');
INSERT INTO `traffic` VALUES ('17', '飞机', '600.00', '来回路程', '8ec18c65-ce04-4bcb-b896-4d8a20126b0e', '13', '');
INSERT INTO `traffic` VALUES ('18', '汽车', '600.00', '汽车费用', 'cfeede9c-cdcb-4ca2-ac03-ebef981e28c1', '4', '');
INSERT INTO `traffic` VALUES ('19', '汽车', '666.00', '出行办公', '70095b97-06e4-4631-a38c-4152a2bae419', '2', '');
INSERT INTO `traffic` VALUES ('20', '6666', '6666.00', '6666', '24371256-7f1c-41ec-84db-a76f4d487759', '16', '');
INSERT INTO `traffic` VALUES ('21', 'sdfg', '200.00', 'kjhgf', '4aae4e49-239a-4e13-bc88-7d063a3ceb2c', '4', '');
INSERT INTO `traffic` VALUES ('22', 'asd', '300.00', 'asds', '76649231-c83b-44c2-81b3-8058531c66ab', '16', '');
INSERT INTO `traffic` VALUES ('23', 'ad', '300.00', 'adf', '698c8ba9-be54-423c-b445-c3c02c50863b', '4', '');
INSERT INTO `traffic` VALUES ('24', 'sasa', '600.00', 'daff', 'f989946f-bf69-4377-897a-462e5edc96f2', '16', '');
INSERT INTO `traffic` VALUES ('25', 'as', '200.00', 'AS', '8fdd0831-b37f-4429-a8bc-4f87e2edb6b9', '16', '');
INSERT INTO `traffic` VALUES ('26', 'as', '888.00', 'as', '37086803-9ec4-4de7-993f-539f5ab390cd', '16', '');

-- ----------------------------
-- Table structure for travel
-- ----------------------------
DROP TABLE IF EXISTS `travel`;
CREATE TABLE `travel` (
  `travelId` int(11) NOT NULL AUTO_INCREMENT COMMENT '差旅明细id',
  `startDatetime` datetime DEFAULT NULL COMMENT '出差开始时间点',
  `endDatetime` datetime DEFAULT NULL COMMENT '出差结束时间点',
  `startlocation` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '出差起点',
  `endlocation` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '出差终点',
  `transportation` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '交通工具',
  `countUser` int(11) DEFAULT '1' COMMENT '出差人数(默认1)',
  `foodCost` decimal(10,2) DEFAULT NULL COMMENT '伙食费(元)',
  `trafficCost` decimal(10,2) DEFAULT NULL COMMENT '交通费用(元)',
  `accomCost` decimal(10,2) DEFAULT NULL COMMENT '住宿费(元)',
  `otherCost` decimal(10,2) DEFAULT NULL COMMENT '其它费用(元)',
  `billId` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '对应账单号(外键)',
  `payeeUserId` int(11) DEFAULT NULL COMMENT '收款人id(外键)',
  `invoice` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '发票附件',
  PRIMARY KEY (`travelId`) USING BTREE,
  KEY `Travel_Bill_FK` (`billId`) USING BTREE,
  KEY `Travel_User_FK` (`payeeUserId`) USING BTREE,
  CONSTRAINT `Travel_Bill_FK` FOREIGN KEY (`billId`) REFERENCES `bill` (`billId`),
  CONSTRAINT `Travel_User_FK` FOREIGN KEY (`payeeUserId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of travel
-- ----------------------------
INSERT INTO `travel` VALUES ('53', '2019-01-01 00:03:00', '2019-01-03 00:03:00', '四川省成都市', '云南省昆明市', '飞机', '1', '600.00', '1200.00', '500.00', '300.00', '8853e6b7-fab8-4c73-b907-06a3d6dfddeb', '16', 'C:/upload/invoice/x.jpg');
INSERT INTO `travel` VALUES ('54', '2019-01-01 00:02:00', '2019-01-02 00:02:00', '四川省成都市', '云南省昆明市', '火车', '1', '300.00', '500.00', '200.00', '400.00', '5771162c-dcc0-4883-a270-69dae171017e', '16', '');
INSERT INTO `travel` VALUES ('55', '2019-01-08 00:03:00', '2019-01-24 00:03:00', '四川省成都市', '云南省昆明市', '高铁', '1', '300.00', '500.00', '300.00', '100.00', '8c202223-d88b-44e5-9c0b-1a356940e481', '16', '');
INSERT INTO `travel` VALUES ('56', '2019-01-03 00:03:00', '2019-01-01 00:03:00', '四川省成都市', '云南省昆明市', '火车', '1', '200.00', '300.00', '500.00', '100.00', '017e4249-5f38-46e3-a99a-d128c431193a', '16', '');
INSERT INTO `travel` VALUES ('57', '2019-01-01 00:03:00', '2019-01-02 00:03:00', '四川省成都市', '云南省昆明市', '飞机', '1', '200.00', '300.00', '400.00', '500.00', '09436d0d-c1c8-4cb5-8968-4f3cff17c9b9', '16', '');
INSERT INTO `travel` VALUES ('58', '2019-01-01 00:03:00', '2019-01-02 00:03:00', '四川省成都市', '湖南省长沙市', '飞机', '1', '200.00', '300.00', '400.00', '100.00', '6aa7b66f-0cde-48f4-8978-72cc4f449ac3', '16', 'C:/upload/invoice/timg.jpg');
INSERT INTO `travel` VALUES ('59', '2019-01-04 00:03:00', '2019-01-24 00:03:00', '四川省成都市', '云南省昆明市', '火车', '1', '600.00', '300.00', '400.00', '300.00', '6e4731a4-8e6b-4a18-8a76-322920fe38db', '16', '');
INSERT INTO `travel` VALUES ('60', '2019-01-01 00:03:00', '2019-01-03 00:03:00', '四川省成都市', '云南省昆明市', '汽车', '1', '100.00', '200.00', '300.00', '400.00', '840b5c74-bce1-45e5-8dc6-3ab4ca4888c2', '16', '');
INSERT INTO `travel` VALUES ('62', '2019-01-27 00:02:00', '2019-01-02 00:03:00', '四川省成都市', '云南省昆明市', '火车', '1', '100.00', '100.00', '100.00', '100.00', '9c6a6ebd-8b07-475f-b461-88c36ac762ea', '16', '');
INSERT INTO `travel` VALUES ('63', '2019-01-01 00:03:00', '2019-01-02 00:03:00', '四川省成都市', '云南省昆明市', '飞机', '1', '300.00', '300.00', '200.00', '100.00', '1439343a-a2ea-4a3e-b5ad-4a32e3008d92', '16', '');
INSERT INTO `travel` VALUES ('65', '2019-01-01 00:02:00', '2019-01-10 00:02:00', '四川省成都市', '云南省昆明市', '飞机', '1', '500.00', '600.00', '400.00', '300.00', 'ca7de7cd-795a-4540-a867-2282af4715b9', '16', '');
INSERT INTO `travel` VALUES ('66', '2019-01-01 00:03:00', '2019-01-03 00:03:00', '四川省成都市', '广东省广州市', '汽车', '1', '500.00', '600.00', '300.00', '400.00', '9cc01f1c-f53b-42f0-be09-ab6e024c652d', '16', '');
INSERT INTO `travel` VALUES ('67', '2019-01-02 00:03:00', '2019-01-03 00:03:00', '四川省成都市', '云南省昆明市', '汽车', '1', '200.00', '200.00', '200.00', '200.00', 'bd5339ef-e6d0-4881-b182-85f50b27900a', '16', '');
INSERT INTO `travel` VALUES ('68', '2019-01-01 00:03:00', '2019-01-03 00:03:00', '四川省成都市', '云南省昆明市', '飞机', '1', '200.00', '500.00', '500.00', '300.00', '7146e142-84f7-4e25-811e-816971f87a79', '16', '');
INSERT INTO `travel` VALUES ('70', '2019-01-03 00:03:00', '2019-01-02 00:03:00', '四川省成都市', '云南省昆明市', '汽车', '1', '600.00', '800.00', '300.00', '200.00', '5b419f20-7b86-4079-9e5f-03ab57a9a904', '16', '');
INSERT INTO `travel` VALUES ('71', '2019-01-01 00:03:00', '2019-01-03 00:03:00', '四川省成都市', '云南省昆明市', 'asdf', '1', '120.00', '200.00', '300.00', '100.00', '603e78b6-a09c-4c0b-bb16-438b54c3dcd1', '16', '');
INSERT INTO `travel` VALUES ('72', '2019-01-02 00:03:00', '2019-01-03 00:03:00', '四川省成都市', '云南省昆明市', 'ad', '1', '20.00', '200.00', '2002.00', '200.00', 'e229ed24-da2c-4598-9870-63f7b4512582', '16', '');
INSERT INTO `travel` VALUES ('73', '2019-01-01 00:03:00', '2019-01-02 00:03:00', '四川省成都市', '云南省昆明市', '灰机', '1', '666.00', '666.00', '666.00', '666.00', '934d105e-a222-4604-bd12-a3c4789f0484', null, 'C:/upload/invoice/x.jpg');
INSERT INTO `travel` VALUES ('74', '2019-01-01 00:03:00', '2019-01-02 00:03:00', '四川省成都市', '云南省昆明市', 'asas', '1', '100.00', '100.00', '100.00', '123.00', '314e824c-f305-40a3-9787-2aad5358b570', null, '');
INSERT INTO `travel` VALUES ('75', '2019-01-01 00:03:00', '2019-01-03 00:03:00', '四川省成都市', '云南省昆明市', '户籍及', '1', '1000.00', '1000.00', '1000.00', '1123.00', 'f7449e4f-f666-4a38-b4c1-25865dcb2590', null, '');
INSERT INTO `travel` VALUES ('76', '2019-01-01 00:03:00', '2019-01-03 00:03:00', '四川省成都市', '云南省昆明市', '飞机', '1', '200.00', '200.00', '200.00', '200.00', 'e62428ce-edf4-43a6-b3e7-18971814ff7c', null, 'C:/upload/invoice/x.jpg');

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `typeId` int(11) NOT NULL COMMENT '报账类型id',
  `typeName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`typeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('1', '国内差旅');
INSERT INTO `type` VALUES ('2', '员工福利');
INSERT INTO `type` VALUES ('3', '公务交通');
INSERT INTO `type` VALUES ('4', '物业管理');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `userName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名',
  `userPass` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '密码',
  `trueName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '员工真实姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `sex` varchar(5) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '性别',
  `deptId` int(11) DEFAULT NULL COMMENT '部门号',
  `roleId` int(11) DEFAULT NULL COMMENT '对应角色(职位)',
  `phoneNum` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '员工邮箱',
  `idCard` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '身份证号',
  `bankCode` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '银行卡号',
  `regTime` datetime DEFAULT NULL,
  PRIMARY KEY (`userId`) USING BTREE,
  KEY `U_D_FK` (`deptId`) USING BTREE,
  KEY `User_Role_FK` (`roleId`) USING BTREE,
  CONSTRAINT `Dept_User_FK` FOREIGN KEY (`deptId`) REFERENCES `dept` (`deptId`),
  CONSTRAINT `Role_User_FK` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('1', 'admin', '456', '管理员', '20', '男', '10', '5', '13160877650', '123@qq.com', '123', '123', '2019-02-28 20:07:30');
INSERT INTO `user` VALUES ('2', 'liyang', '123', '李杨', '20', '男', '10', '1', '88364701', '110@qq.com', '123', '123', '2019-03-04 20:08:51');
INSERT INTO `user` VALUES ('3', 'Tom', '123', '汤姆', '20', '男', '40', '1', '18784962098', '123@qq.com', '123', '123', '2019-03-14 20:10:02');
INSERT INTO `user` VALUES ('4', 'leiting', '123', '雷霆', '21', '男', '20', '1', '13434231426', '123@qq.com', '123', '123', '2019-02-28 20:11:29');
INSERT INTO `user` VALUES ('5', 'caozhongyuan', '123', '曹钟元', '23', '男', '30', '1', '18784962098', '666@qq.com', '123', '123', '2019-02-28 20:11:29');
INSERT INTO `user` VALUES ('6', 'zhuguan1', '123', '人事主管', '25', '女', '10', '3', '18784962098', '666@qq.com', '123', '123', '2019-02-28 20:11:29');
INSERT INTO `user` VALUES ('7', 'zhuguan2', '123', '财务主管', '18', '女', '20', '4', '18784962098', '666@qq.com', '123', '123', '2019-02-28 20:11:29');
INSERT INTO `user` VALUES ('8', 'zhuguan3', '123', '产品主管', '26', '男', '30', '3', '18784962098', '666@qq.com', '123', '123', '2019-02-28 20:11:29');
INSERT INTO `user` VALUES ('9', 'zhuguan4', '123', '软件主管', '24', '男', '40', '3', '18784962098', '666@qq.com', '123', '123', '2019-02-28 20:11:29');
INSERT INTO `user` VALUES ('10', 'zhuguan5', '123', '销售主管', '28', '女', '50', '3', '18784962098', '666@qq.com', '123', '123', '2019-02-28 20:11:29');
INSERT INTO `user` VALUES ('11', 'liuquanhui', '123', '刘权辉', '15', '男', '50', '1', '18784962098', '666@qq.com', '123', '123', '2019-02-28 20:11:29');
INSERT INTO `user` VALUES ('12', 'infoadmin', '123', '信息审核员', '30', '男', '40', '2', '18784962098', '666@qq.com', '123', '123', '2019-02-28 20:11:29');
INSERT INTO `user` VALUES ('13', 'lily', '123', '莉莉', '20', '女', '20', '1', '18784962098', '666@qq.com', '123', '123', '2019-02-28 20:11:29');
INSERT INTO `user` VALUES ('14', 'jack', '123', '杰克', '34', '男', '10', '1', '18784962098', '666@qq.com', '123', '123', '2019-02-28 20:11:29');
INSERT INTO `user` VALUES ('15', 'rose', '123', '玫瑰', '19', '女', '30', '1', '18784962098', '666@qq.com', '123', '123', '2019-02-28 20:11:29');
INSERT INTO `user` VALUES ('16', 'ly', '123', '刘颖', '20', '男', '10', '1', '18784962098', '000@qq.com', '123', '123', '2019-01-22 20:56:14');
INSERT INTO `user` VALUES ('17', 'pop', '123', 'pop', '19', '男', '20', '1', '18784962098', '110@qq.com', '123', '123', '2019-03-08 21:13:36');

-- ----------------------------
-- Table structure for userimg
-- ----------------------------
DROP TABLE IF EXISTS `userimg`;
CREATE TABLE `userimg` (
  `userId` int(11) DEFAULT NULL,
  `src` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userimg
-- ----------------------------
INSERT INTO `userimg` VALUES ('1', '03998aef-3748-4e7f-9b33-fca6e1dfd9d3.jpg');

-- ----------------------------
-- Table structure for welfare
-- ----------------------------
DROP TABLE IF EXISTS `welfare`;
CREATE TABLE `welfare` (
  `welfareId` int(11) NOT NULL AUTO_INCREMENT COMMENT '福利明细id',
  `useFor` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用途',
  `cost` decimal(10,2) DEFAULT NULL COMMENT '所需金额',
  `depict` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '详细描述',
  `billId` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '对应账单id(外键)',
  `payeeUserId` int(11) DEFAULT NULL COMMENT '收款人id(外键)',
  `invoice` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '发票附件',
  PRIMARY KEY (`welfareId`) USING BTREE,
  KEY `Welfare_Bill_FK` (`billId`) USING BTREE,
  KEY `Welfare_User_FK` (`payeeUserId`) USING BTREE,
  CONSTRAINT `Welfare_User_FK` FOREIGN KEY (`payeeUserId`) REFERENCES `user` (`userId`),
  CONSTRAINT `Welfare_bill_FK` FOREIGN KEY (`billId`) REFERENCES `bill` (`billId`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of welfare
-- ----------------------------
INSERT INTO `welfare` VALUES ('16', '项目开发奖励', '500.00', '财务管理系统开发成功', 'f7f0c1ff-5ed7-4c20-94c8-3eb9522ca8d5', '2', '');
INSERT INTO `welfare` VALUES ('17', '加班费', '300.00', '夜班时间长', 'b8af4c6d-6cc0-4ad2-b052-00c154c47f13', '14', '');
INSERT INTO `welfare` VALUES ('18', '奖金发放', '200.00', '温度高，补贴', '594e7a13-4d57-4414-a77f-ac97086f09ff', '3', '');
INSERT INTO `welfare` VALUES ('19', '奖金', '200.00', '加班费', 'eecb91cd-2a39-4dc9-8f7e-602d8354f2d1', '2', '');
INSERT INTO `welfare` VALUES ('20', '补贴', '555.00', '项目奖励', '82990369-6af1-4acf-a54c-62069baf1677', '5', '');
INSERT INTO `welfare` VALUES ('21', '9999', '9999.00', '9999', 'd1d33208-8d82-4cc2-8482-73b0c55a4016', '16', '');
INSERT INTO `welfare` VALUES ('22', '水电费', '600.00', 'asdf', '898d73e4-fd8e-436a-9243-a338d8a71a9b', '2', '');
INSERT INTO `welfare` VALUES ('23', 'asdf', '300.00', 'asdf', '41fd5d8c-04f5-4cc7-9f85-5f1e5da78ac8', '2', '');
INSERT INTO `welfare` VALUES ('24', 'asa', '200.00', 'asd', 'ed45bd30-0393-402a-8351-84eb0c6124ee', '16', '');
INSERT INTO `welfare` VALUES ('25', '123', '600.00', '1231', '223c0b8b-bbcf-4bf5-9334-59a84939dc99', '16', '');
INSERT INTO `welfare` VALUES ('26', '123', '300.00', '123', 'f1c7c9f8-7cdc-4846-8d03-61dd6d6f964c', '16', '');
INSERT INTO `welfare` VALUES ('27', 'qwe', '2000.00', 'weq', '6d6e2e83-237b-43fe-bb66-e420ab0724bf', '2', '');
INSERT INTO `welfare` VALUES ('28', 'af', '333.00', 'asd', '53ef1cd6-1a9e-4e17-9b98-d76408605fe7', '2', '');
INSERT INTO `welfare` VALUES ('29', '1232', '333.00', '123132', 'd0a3d2ab-b9c2-4dc8-994d-c61c08b99547', '4', '');
INSERT INTO `welfare` VALUES ('30', '12312', '111.00', '13123', '67d0e027-f8e4-4437-8954-9fc7102d8e8a', '5', '');
INSERT INTO `welfare` VALUES ('31', '1212', '777.00', '2121', '9076b6f4-f56d-45e1-beb3-3b06eafb69f9', '11', '');
INSERT INTO `welfare` VALUES ('32', 'adfg', '888.00', 'asdfg', '09617d86-2e0c-45a6-a3d4-ecd767f6b2ec', '2', '');
INSERT INTO `welfare` VALUES ('33', 'asa', '666.00', 'sas', '31c9ed30-724a-4744-9d42-dd26d8dfd5b2', '2', 'C:/upload/invoice/timg.jpg');

/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80033 (8.0.33)
 Source Host           : localhost:3306
 Source Schema         : xiafish

 Target Server Type    : MySQL
 Target Server Version : 80033 (8.0.33)
 File Encoding         : 65001

 Date: 23/07/2023 23:48:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `goods_id` int NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ori_price` float NULL DEFAULT NULL,
  `cur_price` float NOT NULL,
  `goods_category_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `seller_id` int NULL DEFAULT NULL,
  `release_time` datetime NULL DEFAULT NULL,
  `inventory` int NULL DEFAULT NULL,
  `goods_profile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goods_photos` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`goods_id`) USING BTREE,
  UNIQUE INDEX `goods_id_UNIQUE`(`goods_id` ASC) USING BTREE,
  INDEX `fk_goods_user_idx`(`seller_id` ASC) USING BTREE,
  INDEX `fk_goods_category_idx`(`goods_category_name` ASC) USING BTREE,
  CONSTRAINT `goods_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `user` (`user_id`) ON DELETE SET NULL ON UPDATE CASCADE
  CONSTRAINT `goods_inventory_constraint` CHECK (`inventory` >= 0)
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '《计算机组成原理》', 40, 20, '书本', NULL, '2023-07-16 20:02:30', 1, '九成新', '[\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/487d5c5a-5f81-4fa4-ac2d-6dd4777a8855.png\\\",\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/aec1de9c-4f22-486d-892d-bda5177ea663.png\\\"]');
INSERT INTO `goods` VALUES (2, '钢笔', 3, 1, '文具', NULL, '2023-07-18 17:08:15', 1, '九成新', '[\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/487d5c5a-5f81-4fa4-ac2d-6dd4777a8855.png\\\",\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/aec1de9c-4f22-486d-892d-bda5177ea663.png\\\"]');
INSERT INTO `goods` VALUES (3, '《计算机网络》', 40, 20, '书本', NULL, '2023-07-16 20:02:30', 1, '九成新', '[\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/487d5c5a-5f81-4fa4-ac2d-6dd4777a8855.png\\\",\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/aec1de9c-4f22-486d-892d-bda5177ea663.png\\\"]');
INSERT INTO `goods` VALUES (4, '荧光笔', 3, 1, '文具', NULL, '2023-07-18 17:08:15', 1, '九成新', '[\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/487d5c5a-5f81-4fa4-ac2d-6dd4777a8855.png\\\",\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/aec1de9c-4f22-486d-892d-bda5177ea663.png\\\"]');
INSERT INTO `goods` VALUES (5, 'iPhone 12', 1000, 800, '数码产品', NULL, '2023-07-18 17:08:15', 10, '九成新', '[\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/487d5c5a-5f81-4fa4-ac2d-6dd4777a8855.png\\\",\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/aec1de9c-4f22-486d-892d-bda5177ea663.png\\\"]');
INSERT INTO `goods` VALUES (6, 'Samsung Galaxy S21', 1500, 1200, '数码产品', NULL, '2023-07-18 17:08:15', 5, '九成新', '[\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/487d5c5a-5f81-4fa4-ac2d-6dd4777a8855.png\\\",\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/aec1de9c-4f22-486d-892d-bda5177ea663.png\\\"]');
INSERT INTO `goods` VALUES (7, 'Dell XPS 15', 3000, 2500, '数码产品', NULL, '2023-07-18 17:08:15', 8, '九成新', '[\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/487d5c5a-5f81-4fa4-ac2d-6dd4777a8855.png\\\",\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/aec1de9c-4f22-486d-892d-bda5177ea663.png\\\"]');
INSERT INTO `goods` VALUES (8, 'MacBook Air', 4000, 3500, '数码产品', NULL, '2023-07-18 17:08:15', 6, '九成新', '[\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/487d5c5a-5f81-4fa4-ac2d-6dd4777a8855.png\\\",\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/aec1de9c-4f22-486d-892d-bda5177ea663.png\\\"]');
INSERT INTO `goods` VALUES (11, 'Python编程入门', 50, 30, '书本', NULL, '2023-07-18 17:08:15', 3, '九成新', '[\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/487d5c5a-5f81-4fa4-ac2d-6dd4777a8855.png\\\",\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/aec1de9c-4f22-486d-892d-bda5177ea663.png\\\"]');
INSERT INTO `goods` VALUES (12, '红楼梦', 60, 40, '书本', NULL, '2023-07-18 17:08:15', 2, '九成新', '[\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/487d5c5a-5f81-4fa4-ac2d-6dd4777a8855.png\\\",\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/aec1de9c-4f22-486d-892d-bda5177ea663.png\\\"]');
INSERT INTO `goods` VALUES (13, 'Nike T恤', 200, 150, '服装', NULL, '2023-07-18 17:08:15', 8, '九成新', '[\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/487d5c5a-5f81-4fa4-ac2d-6dd4777a8855.png\\\",\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/aec1de9c-4f22-486d-892d-bda5177ea663.png\\\"]');
INSERT INTO `goods` VALUES (14, 'Levi\'s 牛仔裤', 300, 250, '服装', NULL, '2023-07-18 17:08:15', 5, '九成新', '[\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/487d5c5a-5f81-4fa4-ac2d-6dd4777a8855.png\\\",\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/aec1de9c-4f22-486d-892d-bda5177ea663.png\\\"]');
INSERT INTO `goods` VALUES (15, '兰蔻面霜', 100, 80, '美妆', NULL, '2023-07-18 17:08:15', 10, '九成新', '[\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/487d5c5a-5f81-4fa4-ac2d-6dd4777a8855.png\\\",\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/aec1de9c-4f22-486d-892d-bda5177ea663.png\\\"]');
INSERT INTO `goods` VALUES (16, 'MAC口红', 200, 150, '美妆', NULL, '2023-07-18 17:08:15', 6, '九成新', '[\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/487d5c5a-5f81-4fa4-ac2d-6dd4777a8855.png\\\",\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/aec1de9c-4f22-486d-892d-bda5177ea663.png\\\"]');
INSERT INTO `goods` VALUES (17, 'Lay\'s薯片', 20, 15, '食品', NULL, '2023-07-18 17:08:15', 20, '九成新', '[\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/487d5c5a-5f81-4fa4-ac2d-6dd4777a8855.png\\\",\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/aec1de9c-4f22-486d-892d-bda5177ea663.png\\\"]');
INSERT INTO `goods` VALUES (18, 'Dove巧克力', 30, 25, '食品', NULL, '2023-07-18 17:08:15', 15, '九成新', '[\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/487d5c5a-5f81-4fa4-ac2d-6dd4777a8855.png\\\",\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/aec1de9c-4f22-486d-892d-bda5177ea663.png\\\"]');
INSERT INTO `goods` VALUES (19, '宜家椅子', 150, 120, '生活用品', NULL, '2023-07-18 17:08:15', 5, '九成新', '[\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/487d5c5a-5f81-4fa4-ac2d-6dd4777a8855.png\\\",\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/aec1de9c-4f22-486d-892d-bda5177ea663.png\\\"]');
INSERT INTO `goods` VALUES (20, 'IKEA地毯', 250, 200, '生活用品', NULL, '2023-07-18 17:08:15', 3, '九成新', '[\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/487d5c5a-5f81-4fa4-ac2d-6dd4777a8855.png\\\",\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/aec1de9c-4f22-486d-892d-bda5177ea663.png\\\"]');
INSERT INTO `goods` VALUES (21, 'Sony电视', 2000, 1800, '生活用品', NULL, '2023-07-18 17:08:15', 4, '九成新', '[\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/487d5c5a-5f81-4fa4-ac2d-6dd4777a8855.png\\\",\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/aec1de9c-4f22-486d-892d-bda5177ea663.png\\\"]');
INSERT INTO `goods` VALUES (22, 'Haier冰箱', 3000, 2800, '生活用品', NULL, '2023-07-18 17:08:15', 2, '九成新', '[\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/487d5c5a-5f81-4fa4-ac2d-6dd4777a8855.png\\\",\\\"https://xiafish.oss-cn-hangzhou.aliyuncs.com/aec1de9c-4f22-486d-892d-bda5177ea663.png\\\"]');

-- ----------------------------
-- Table structure for goods_comment
-- ----------------------------
DROP TABLE IF EXISTS `goods_comment`;
CREATE TABLE `goods_comment`  (
  `goods_comment_id` int NOT NULL AUTO_INCREMENT,
  `goods_id` int NOT NULL,
  `buyer_id` int NOT NULL,
  `goods_comment_content` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`goods_comment_id`) USING BTREE,
  UNIQUE INDEX `goods_comment_id_UNIQUE`(`goods_comment_id` ASC) USING BTREE,
  INDEX `fk_goods_comment_goods_idx`(`goods_id` ASC) USING BTREE,
  INDEX `fk_goods_comment_buyer_idx`(`buyer_id` ASC) USING BTREE,
  CONSTRAINT `fk_goods_comment_buyer` FOREIGN KEY (`buyer_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_goods_comment_goods` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 117 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods_comment
-- ----------------------------
INSERT INTO `goods_comment` VALUES (1, 1, 4, '可以的');
INSERT INTO `goods_comment` VALUES (2, 4, 2, '不错');
INSERT INTO `goods_comment` VALUES (17, 14, 6, 'dPxvqpxS84');
INSERT INTO `goods_comment` VALUES (18, 21, 5, 'ZviPYqE0M8');
INSERT INTO `goods_comment` VALUES (19, 16, 5, 'BuYqVW3UJ9');
INSERT INTO `goods_comment` VALUES (20, 14, 6, 'st7cFegIVx');
INSERT INTO `goods_comment` VALUES (21, 18, 5, 'bS1tZwqyQZ');
INSERT INTO `goods_comment` VALUES (22, 1, 1, 'BfdMlBrpHU');
INSERT INTO `goods_comment` VALUES (23, 18, 5, 'uGz4tYGe2P');
INSERT INTO `goods_comment` VALUES (24, 18, 8, '0YLXEld7Tz');
INSERT INTO `goods_comment` VALUES (25, 18, 5, 'OHPxaQraCk');
INSERT INTO `goods_comment` VALUES (26, 15, 7, 'lXiO7w6eNZ');
INSERT INTO `goods_comment` VALUES (27, 7, 4, '0AmWs56LfB');
INSERT INTO `goods_comment` VALUES (28, 19, 6, 'wtfxNIWd2e');
INSERT INTO `goods_comment` VALUES (29, 2, 5, 'aD0oBAWlYx');
INSERT INTO `goods_comment` VALUES (30, 5, 8, 'osIeK2Fi46');
INSERT INTO `goods_comment` VALUES (31, 22, 6, 'qlGq0ZgvsH');
INSERT INTO `goods_comment` VALUES (32, 18, 5, 'HBwGIAbSvf');
INSERT INTO `goods_comment` VALUES (33, 20, 5, 'XkDc5KDEHY');
INSERT INTO `goods_comment` VALUES (34, 11, 2, 'xx0MHeCnc2');
INSERT INTO `goods_comment` VALUES (35, 15, 1, 'e7K08awz16');
INSERT INTO `goods_comment` VALUES (36, 4, 5, 'mW3SxpqDlD');
INSERT INTO `goods_comment` VALUES (37, 6, 6, 'f2ZOF5fPoT');
INSERT INTO `goods_comment` VALUES (38, 18, 4, 'lncyE8k65x');
INSERT INTO `goods_comment` VALUES (39, 16, 5, 'gglnX5I1Kg');
INSERT INTO `goods_comment` VALUES (40, 17, 8, 'YbeY5k98WE');
INSERT INTO `goods_comment` VALUES (41, 4, 2, 'cPU2b4W4nq');
INSERT INTO `goods_comment` VALUES (42, 22, 2, 'PcrUrFGywe');
INSERT INTO `goods_comment` VALUES (43, 17, 1, 'FOOpzwYSBW');
INSERT INTO `goods_comment` VALUES (44, 16, 8, 'Q7znDVIn1G');
INSERT INTO `goods_comment` VALUES (45, 17, 4, 'mHTCYyikzh');
INSERT INTO `goods_comment` VALUES (46, 17, 4, 'OOdja0XkZp');
INSERT INTO `goods_comment` VALUES (47, 21, 6, 'Ze1VN5wzsI');
INSERT INTO `goods_comment` VALUES (48, 6, 4, 'pEwZT7nyAw');
INSERT INTO `goods_comment` VALUES (49, 14, 1, 'ZZNhQmWLcx');
INSERT INTO `goods_comment` VALUES (50, 21, 8, 'dm4uRThzZh');
INSERT INTO `goods_comment` VALUES (51, 14, 7, '1ezpItKh5I');
INSERT INTO `goods_comment` VALUES (52, 5, 3, 'Uhuv61PSL3');
INSERT INTO `goods_comment` VALUES (53, 14, 8, '8BD6akAF9t');
INSERT INTO `goods_comment` VALUES (54, 3, 8, 'QdzTe2icnt');
INSERT INTO `goods_comment` VALUES (55, 4, 7, 'dcpzyzLNCF');
INSERT INTO `goods_comment` VALUES (56, 19, 2, '93fK1ekFWb');
INSERT INTO `goods_comment` VALUES (57, 12, 5, 'bt6HjvjLaz');
INSERT INTO `goods_comment` VALUES (58, 8, 4, 'Zpa53wXnYb');
INSERT INTO `goods_comment` VALUES (59, 18, 3, 'Qsf8ww0dkx');
INSERT INTO `goods_comment` VALUES (60, 21, 3, '5llxpswzwt');
INSERT INTO `goods_comment` VALUES (61, 18, 3, 'UyExS8l75t');
INSERT INTO `goods_comment` VALUES (62, 8, 6, 'NRCP8Tsh3e');
INSERT INTO `goods_comment` VALUES (63, 20, 7, '4USvLGR3C8');
INSERT INTO `goods_comment` VALUES (64, 17, 1, 'X3kF0iSN48');
INSERT INTO `goods_comment` VALUES (65, 16, 2, 'QhZSGGL0p7');
INSERT INTO `goods_comment` VALUES (66, 3, 6, 'FITx24frWl');
INSERT INTO `goods_comment` VALUES (67, 4, 5, 'V3TBKJEk2V');
INSERT INTO `goods_comment` VALUES (68, 8, 4, 'Em2JPwYLVy');
INSERT INTO `goods_comment` VALUES (69, 2, 5, 'hLeMGgaXV6');
INSERT INTO `goods_comment` VALUES (70, 6, 8, 'goI6kXqFE9');
INSERT INTO `goods_comment` VALUES (71, 18, 3, 'DMRc66KGL8');
INSERT INTO `goods_comment` VALUES (72, 17, 7, '3of8YWRv6b');
INSERT INTO `goods_comment` VALUES (73, 18, 6, 'Ig8RWVQh4j');
INSERT INTO `goods_comment` VALUES (74, 17, 5, 'qNyUeraR3Y');
INSERT INTO `goods_comment` VALUES (75, 4, 4, 'DaFdTWz7zi');
INSERT INTO `goods_comment` VALUES (76, 22, 4, 'NsZVM6xIT0');
INSERT INTO `goods_comment` VALUES (77, 1, 1, 'mTjkitbyEq');
INSERT INTO `goods_comment` VALUES (78, 3, 5, 'fIHxv6Ms5h');
INSERT INTO `goods_comment` VALUES (79, 3, 6, 'lTMhmufHSw');
INSERT INTO `goods_comment` VALUES (80, 16, 3, 'T9GDpWoycb');
INSERT INTO `goods_comment` VALUES (81, 16, 3, 'MhAZPc16qd');
INSERT INTO `goods_comment` VALUES (82, 13, 3, 'xMqPeoEdpx');
INSERT INTO `goods_comment` VALUES (83, 22, 8, 'UDZLalrpHp');
INSERT INTO `goods_comment` VALUES (84, 17, 5, 'hx83V7gghL');
INSERT INTO `goods_comment` VALUES (85, 17, 2, '8haiJTjIIy');
INSERT INTO `goods_comment` VALUES (86, 13, 1, 't99npOq10o');
INSERT INTO `goods_comment` VALUES (87, 8, 1, 'iITPKOh07M');
INSERT INTO `goods_comment` VALUES (88, 14, 5, 'kN5AXHBiDf');
INSERT INTO `goods_comment` VALUES (89, 7, 2, '30YkUzltFM');
INSERT INTO `goods_comment` VALUES (90, 14, 7, 'WKBEzWt4GW');
INSERT INTO `goods_comment` VALUES (91, 8, 5, 'mFVs5FEIzz');
INSERT INTO `goods_comment` VALUES (92, 2, 3, '1SYvr7SYTM');
INSERT INTO `goods_comment` VALUES (93, 13, 6, 'PXagTvRW26');
INSERT INTO `goods_comment` VALUES (94, 21, 3, 'wmkyWTwdPJ');
INSERT INTO `goods_comment` VALUES (95, 20, 7, 'L5ySQXpTVx');
INSERT INTO `goods_comment` VALUES (96, 3, 4, 'qz82VdPZij');
INSERT INTO `goods_comment` VALUES (97, 11, 4, 'rl8KXY0Ex7');
INSERT INTO `goods_comment` VALUES (98, 1, 6, 'fopcPbgQJq');
INSERT INTO `goods_comment` VALUES (99, 11, 5, 'Tgme40lKQM');
INSERT INTO `goods_comment` VALUES (100, 15, 2, 'xx64aDTXcs');
INSERT INTO `goods_comment` VALUES (101, 7, 1, 'ogtbb8qUbo');
INSERT INTO `goods_comment` VALUES (102, 13, 1, 'AFX9oJGTOw');
INSERT INTO `goods_comment` VALUES (103, 14, 1, 'CP959kwPOS');
INSERT INTO `goods_comment` VALUES (104, 5, 7, 'DUlxbxl9mK');
INSERT INTO `goods_comment` VALUES (105, 13, 6, 'imR7Pf6Buo');
INSERT INTO `goods_comment` VALUES (106, 18, 5, 'Vjk5AHeHZP');
INSERT INTO `goods_comment` VALUES (107, 13, 8, '61dpwvxvaB');
INSERT INTO `goods_comment` VALUES (108, 6, 4, 'dTkajNfu8z');
INSERT INTO `goods_comment` VALUES (109, 17, 7, 'jQev2yzwtq');
INSERT INTO `goods_comment` VALUES (110, 7, 8, 'XvOPzlMSD6');
INSERT INTO `goods_comment` VALUES (111, 5, 3, 'UwfNwtZlUN');
INSERT INTO `goods_comment` VALUES (112, 7, 7, 'GN5FH3dMO4');
INSERT INTO `goods_comment` VALUES (113, 4, 8, 'FRSZuTunXy');
INSERT INTO `goods_comment` VALUES (114, 5, 4, '12AY20P6EE');
INSERT INTO `goods_comment` VALUES (115, 20, 1, 'RsAkxllcW3');
INSERT INTO `goods_comment` VALUES (116, 17, 2, 'WdicDuW0Gw');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `buyer_id` int NULL DEFAULT NULL,
  `seller_id` int NULL DEFAULT NULL,
  `goods_id` int NULL DEFAULT NULL,
  `order_num` int NOT NULL,
  `order_sum_price` float NOT NULL,
  `order_status` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `order_date_time` datetime NOT NULL,
  PRIMARY KEY (`order_id`) USING BTREE,
  UNIQUE INDEX `order_id_UNIQUE`(`order_id` ASC) USING BTREE,
  INDEX `fk_order_goods_idx`(`goods_id` ASC) USING BTREE,
  INDEX `buyer_id`(`buyer_id` ASC) USING BTREE,
  INDEX `seller_id`(`seller_id` ASC) USING BTREE,
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`buyer_id`) REFERENCES `user` (`user_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`seller_id`) REFERENCES `user` (`user_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `order_ibfk_3` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 73 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (1, 2, 1, 1, 1, 20, '已下单', '2023-07-16 21:52:26');
INSERT INTO `order` VALUES (2, 1, 2, 3, 1, 10, '已下单', '2023-07-23 23:35:31');
INSERT INTO `order` VALUES (23, 8, 8, 15, 249, 26.07, 'llShvtUSsa', '2011-09-10 08:18:49');
INSERT INTO `order` VALUES (24, 5, 5, 22, 58, 361.49, '7D0XBAgq58', '2006-02-08 05:45:28');
INSERT INTO `order` VALUES (25, 7, 7, 5, 498, 681.29, 'fY90owJYt6', '2020-10-16 02:13:11');
INSERT INTO `order` VALUES (26, 1, 1, 15, 595, 427.78, '4QaaMS97d6', '2021-03-19 20:58:35');
INSERT INTO `order` VALUES (27, 7, 7, 16, 987, 424.26, 'o1Lp079QFN', '2022-03-11 23:20:48');
INSERT INTO `order` VALUES (28, 3, 3, 11, 382, 412.03, '6Ki1sfRjpM', '2018-04-21 22:21:26');
INSERT INTO `order` VALUES (29, 4, 4, 21, 947, 584.58, 'J3QH08T0SB', '2014-06-12 07:42:29');
INSERT INTO `order` VALUES (30, 6, 6, 7, 392, 356.12, 'u7yx6EA5tA', '2019-04-07 07:30:13');
INSERT INTO `order` VALUES (31, 4, 4, 17, 648, 960.32, 'y01TRW5zA2', '2007-07-18 12:37:24');
INSERT INTO `order` VALUES (32, 8, 8, 6, 737, 324.32, 'mWzWgWEc4W', '2000-05-13 01:02:14');
INSERT INTO `order` VALUES (33, 1, 1, 3, 279, 483.52, '0nKqCplltX', '2007-06-11 12:37:09');
INSERT INTO `order` VALUES (34, 5, 5, 4, 461, 876.04, 'lgVDpeWQix', '2020-12-21 03:19:52');
INSERT INTO `order` VALUES (35, 4, 4, 20, 63, 39.6, 'XKWezWCKb7', '2017-11-21 22:38:42');
INSERT INTO `order` VALUES (36, 7, 7, 12, 550, 18.98, 'C98EWUGcHJ', '2013-12-28 12:53:51');
INSERT INTO `order` VALUES (37, 8, 8, 3, 787, 494.65, 'jlqhlzoPck', '2022-08-24 15:53:43');
INSERT INTO `order` VALUES (38, 2, 2, 20, 32, 182.22, 'E5oy7MzuAG', '2018-12-18 01:32:08');
INSERT INTO `order` VALUES (39, 3, 3, 6, 109, 908.14, 'q3rXBiMZig', '2013-04-06 08:09:44');
INSERT INTO `order` VALUES (40, 5, 5, 12, 528, 80.8, 'aVHOwfkisi', '2007-11-23 06:41:31');
INSERT INTO `order` VALUES (41, 5, 5, 21, 894, 152.74, 'FVvkqtfyAu', '2022-04-12 10:25:48');
INSERT INTO `order` VALUES (42, 7, 7, 1, 212, 592.44, 'cXbWkqEzqo', '2005-10-16 22:31:23');
INSERT INTO `order` VALUES (43, 5, 5, 15, 784, 461.79, 'SwScbgbRtI', '2010-04-07 08:38:01');
INSERT INTO `order` VALUES (44, 5, 5, 4, 492, 428.38, 'DKJ2qwzpEK', '2016-02-29 16:32:40');
INSERT INTO `order` VALUES (45, 6, 6, 13, 70, 568.86, 'sp4KLioCgp', '2004-08-28 15:48:50');
INSERT INTO `order` VALUES (46, 7, 7, 8, 92, 17.87, 'nXO5E7jvSG', '2020-06-28 03:14:59');
INSERT INTO `order` VALUES (47, 8, 8, 13, 960, 696.8, '1C8XQKpd4J', '2004-08-11 20:10:55');
INSERT INTO `order` VALUES (48, 1, 1, 20, 562, 954.12, '33LgMa2HFd', '2013-03-10 23:00:13');
INSERT INTO `order` VALUES (49, 4, 4, 2, 822, 267.24, 'zACBlOXNcd', '2008-05-07 09:17:15');
INSERT INTO `order` VALUES (50, 3, 3, 19, 231, 271.71, 'K0xLk7nx5H', '2002-11-20 21:24:04');
INSERT INTO `order` VALUES (51, 3, 3, 20, 784, 736.49, 'K53Ow0AA3c', '2000-12-11 18:05:16');
INSERT INTO `order` VALUES (52, 7, 7, 14, 412, 505.47, '1knxedOwdC', '2009-02-27 20:58:04');
INSERT INTO `order` VALUES (53, 6, 6, 19, 708, 567.87, '1Pf9WAhW5W', '2017-08-08 07:44:35');
INSERT INTO `order` VALUES (54, 3, 3, 16, 767, 594.39, 'k7uqKX2Xad', '2007-03-14 02:37:56');
INSERT INTO `order` VALUES (55, 4, 4, 11, 462, 425, 'EhAisMgATq', '2021-07-17 03:48:39');
INSERT INTO `order` VALUES (56, 1, 1, 15, 155, 586.5, 'BWTYt22EEF', '2019-04-01 15:42:44');
INSERT INTO `order` VALUES (57, 4, 4, 7, 900, 429.23, 'lArtg3Yqhv', '2003-09-02 21:27:11');
INSERT INTO `order` VALUES (58, 3, 3, 3, 148, 868.16, 'E5p4qQB9hN', '2004-08-22 21:26:43');
INSERT INTO `order` VALUES (59, 6, 6, 19, 723, 485.79, 'xmE5gKbXsp', '2003-09-14 21:43:20');
INSERT INTO `order` VALUES (60, 6, 6, 14, 727, 984.15, '3TxRwmaMev', '2018-09-28 16:00:39');
INSERT INTO `order` VALUES (61, 2, 2, 16, 103, 266.91, 'DyKM6ZdkZl', '2005-06-11 00:50:24');
INSERT INTO `order` VALUES (62, 1, 1, 20, 621, 854.08, 'gXFGoOoby8', '2009-01-31 05:46:05');
INSERT INTO `order` VALUES (63, 8, 8, 21, 289, 333.5, 'BKSipkGh67', '2023-05-26 03:42:19');
INSERT INTO `order` VALUES (64, 8, 8, 22, 615, 112.92, 'I7zS3izAKK', '2016-09-23 22:06:35');
INSERT INTO `order` VALUES (65, 4, 4, 4, 595, 805.56, 'U0GZorKFPN', '2021-02-02 05:33:08');
INSERT INTO `order` VALUES (66, 5, 5, 22, 490, 672.81, 'er3Vl1XmaN', '2009-08-31 14:31:53');
INSERT INTO `order` VALUES (67, 6, 6, 13, 647, 768.9, 'iVkfz9trhh', '2004-11-11 02:22:00');
INSERT INTO `order` VALUES (68, 8, 8, 7, 224, 651.73, 'fPzyvOYGYk', '2015-02-26 20:52:18');
INSERT INTO `order` VALUES (69, 5, 5, 16, 765, 323.79, 'UcnPW7LViT', '2013-01-20 03:46:46');
INSERT INTO `order` VALUES (70, 7, 7, 7, 577, 170.49, 'LanRU0lKLj', '2018-04-30 05:27:07');
INSERT INTO `order` VALUES (71, 5, 5, 20, 817, 297.82, 'xGgYH5jXBb', '2002-08-17 07:12:52');
INSERT INTO `order` VALUES (72, 6, 6, 11, 41, 740.62, 'csfm18AG5d', '2023-04-23 08:07:59');

-- ----------------------------
-- Table structure for shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart`  (
  `shopping_cart_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `goods_id` int NULL DEFAULT NULL,
  `collect_time` datetime NOT NULL,
  `collect_num` int NOT NULL,
  PRIMARY KEY (`shopping_cart_id`) USING BTREE,
  UNIQUE INDEX `user_goods_unique`(`user_id` ASC, `goods_id` ASC) USING BTREE,
  INDEX `fk_collect_goods_idx`(`goods_id` ASC) USING BTREE,
  CONSTRAINT `fk_shoppingcart_goods` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE SET NULL ON UPDATE RESTRICT,
) ENGINE = InnoDB AUTO_INCREMENT = 119 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of shopping_cart
-- ----------------------------
INSERT INTO `shopping_cart` VALUES (118, 1, 2, '2023-07-23 23:46:40', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_phone_num` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_passwd` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_status` int NOT NULL DEFAULT 1,
  `user_photo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_campus` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_nickname` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_profile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_gender` int NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_id_UNIQUE`(`user_id` ASC) USING BTREE,
  UNIQUE INDEX `user_name_UNIQUE`(`user_name` ASC) USING BTREE,
  UNIQUE INDEX `user_phone_num_UNIQUE`(`user_phone_num` ASC) USING BTREE,
  UNIQUE INDEX `user_email_UNIQUE`(`user_email` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三', '15912345435', '1234@qq.com', '$2a$10$y6OcJaiKNbAZd/rUJSllwOKoPMUWznO1sFtW4IkpS40ESR8tW5kV.', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (2, '李四', '15923214245', '4321@qq.com', '$2a$10$7ct.yypVHgKwQrYilCBUXubJmFPpgHRRmxN2zL3T4GQsXBN/vO.nS', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (3, '赵五', '15923214231', '1234@163.com', '$2a$10$ty0hnooI5.86QUBqWQxPUOzneCrAOvQO.zNViI./9PCpUOjou832a', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (4, '黄子安', '18157646970', '2640115971@qq.com', '$2a$10$ZkwH8Oqe5.vTsoI7FkdJMeSY2wY3rzKEMHSv99Pl/IKdnzu/p.RJu', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (5, '徐森彬', '13123313136', '1209653508@qq.com', '$2a$10$3XYocrjSEg3UFmGTD.vbC.9vyog8eUnH2JjP3D7DAZ3aLdzXR83Pu', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (6, '侯妤欣', '18064501719', '1175314118@qq.com', '$2a$10$wejpqDnD397qJuo0Iink7OpQWkZ2OiX0KJbnfyuflVc8zd2lzc0ga', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (7, '范周喆', '15768408775', '2902566341@qq.com', '$2a$10$VE7LctbeirfYQkMnnjJBXeSheMrfg1U6rfAK9IUIZID38nuGtCb1S', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (8, '崔方博', '15518441162', '1451986367@qq.com', '$2a$10$kxdHTAJylmjuLehkm9pYxOk8vhnhdFiQvjVpOmKuIq6c0kmOHzzAW', 1, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for user_comment
-- ----------------------------
DROP TABLE IF EXISTS `user_comment`;
CREATE TABLE `user_comment`  (
  `user_comment_id` int NOT NULL AUTO_INCREMENT,
  `buyer_id` int NULL DEFAULT NULL,
  `seller_id` int NULL DEFAULT NULL,
  `user_comment_content` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`user_comment_id`) USING BTREE,
  UNIQUE INDEX `user_comment_id_UNIQUE`(`user_comment_id` ASC) USING BTREE,
  INDEX `fk_user_comment_buyer_idx`(`buyer_id` ASC) USING BTREE,
  INDEX `fk_user_comment_seller_idx`(`seller_id` ASC) USING BTREE,
  CONSTRAINT `fk_user_comment_buyer` FOREIGN KEY (`buyer_id`) REFERENCES `user` (`user_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_user_comment_seller` FOREIGN KEY (`seller_id`) REFERENCES `user` (`user_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_comment
-- ----------------------------
INSERT INTO `user_comment` VALUES (1, 2, 1, '回消息快，好评');
INSERT INTO `user_comment` VALUES (2, 3, 1, '可以的');
INSERT INTO `user_comment` VALUES (3, 4, 1, '不错');
INSERT INTO `user_comment` VALUES (4, 5, 2, '可以可以');
INSERT INTO `user_comment` VALUES (5, 6, 3, '真不错');
INSERT INTO `user_comment` VALUES (6, 6, 3, '好');
INSERT INTO `user_comment` VALUES (7, 2, 2, '1EqQuFDfqR');
INSERT INTO `user_comment` VALUES (8, 5, 5, 'iu4h1Fb0AB');
INSERT INTO `user_comment` VALUES (9, 8, 8, '7ozY7gfnZl');
INSERT INTO `user_comment` VALUES (10, 7, 7, 'QjJkOxXcSZ');
INSERT INTO `user_comment` VALUES (11, 2, 2, 'PCrCBjWrHL');
INSERT INTO `user_comment` VALUES (12, 6, 6, 'YfjTgKzFl7');
INSERT INTO `user_comment` VALUES (13, 1, 1, 'LYY7Trnomi');
INSERT INTO `user_comment` VALUES (14, 2, 2, '4owpqakLaz');
INSERT INTO `user_comment` VALUES (15, 8, 8, '2dlb0ru9hS');
INSERT INTO `user_comment` VALUES (16, 8, 8, '8edZ0FQlso');
INSERT INTO `user_comment` VALUES (17, 2, 2, 'No1J6M3T4h');
INSERT INTO `user_comment` VALUES (18, 3, 3, 'jOBuZ7vnqr');
INSERT INTO `user_comment` VALUES (19, 6, 6, 'FCQf1NjFdy');
INSERT INTO `user_comment` VALUES (20, 1, 1, 'f5WgVfVjvY');
INSERT INTO `user_comment` VALUES (21, 3, 3, 'JkrpbP7uMl');
INSERT INTO `user_comment` VALUES (22, 8, 8, '4gAw3hU2s9');
INSERT INTO `user_comment` VALUES (23, 1, 1, 'kj34Le73xl');
INSERT INTO `user_comment` VALUES (24, 2, 2, 'qxGh0bSRd7');
INSERT INTO `user_comment` VALUES (25, 3, 3, 'pPvZxE3mCJ');
INSERT INTO `user_comment` VALUES (26, 7, 7, 'cZLOTWAFCz');

SET FOREIGN_KEY_CHECKS = 1;

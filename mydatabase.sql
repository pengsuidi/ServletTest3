/*
 Navicat Premium Data Transfer

 Source Server         : 毕设腾讯云
 Source Server Type    : MySQL
 Source Server Version : 50651
 Source Host           : localhost:3306
 Source Schema         : mydatabase

 Target Server Type    : MySQL
 Target Server Version : 50651
 File Encoding         : 65001

 Date: 19/04/2022 14:57:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for FK_payment_food
-- ----------------------------
DROP TABLE IF EXISTS `FK_payment_food`;
CREATE TABLE `FK_payment_food`  (
  `oid` int(11) NOT NULL,
  `food_id` int(11) NOT NULL,
  PRIMARY KEY (`oid`, `food_id`) USING BTREE,
  INDEX `FK_FK_payment_food2`(`food_id`) USING BTREE,
  CONSTRAINT `FK_FK_payment_food` FOREIGN KEY (`oid`) REFERENCES `payment` (`oid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_FK_payment_food2` FOREIGN KEY (`food_id`) REFERENCES `food` (`food_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of FK_payment_food
-- ----------------------------
INSERT INTO `FK_payment_food` VALUES (1, 1);
INSERT INTO `FK_payment_food` VALUES (43, 1);
INSERT INTO `FK_payment_food` VALUES (44, 1);
INSERT INTO `FK_payment_food` VALUES (45, 1);
INSERT INTO `FK_payment_food` VALUES (46, 1);
INSERT INTO `FK_payment_food` VALUES (48, 1);
INSERT INTO `FK_payment_food` VALUES (43, 2);
INSERT INTO `FK_payment_food` VALUES (44, 2);
INSERT INTO `FK_payment_food` VALUES (45, 2);
INSERT INTO `FK_payment_food` VALUES (46, 2);
INSERT INTO `FK_payment_food` VALUES (48, 2);
INSERT INTO `FK_payment_food` VALUES (47, 4);

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection`  (
  `shop_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`shop_id`, `user_id`) USING BTREE,
  INDEX `FK_collection`(`user_id`) USING BTREE,
  CONSTRAINT `FK_collection` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_collection2` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`shop_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of collection
-- ----------------------------
INSERT INTO `collection` VALUES (1, 17);
INSERT INTO `collection` VALUES (2, 17);
INSERT INTO `collection` VALUES (3, 17);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `comment` varchar(100) CHARACTER SET utf32 COLLATE utf32_general_ci NULL DEFAULT NULL,
  `comment_grade` float NULL DEFAULT NULL,
  `comment_img_addr` varchar(100) CHARACTER SET utf32 COLLATE utf32_general_ci NULL DEFAULT NULL,
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`comment_id`) USING BTREE,
  INDEX `FK_comment_shop`(`shop_id`) USING BTREE,
  INDEX `FK_comment_user`(`user_id`) USING BTREE,
  CONSTRAINT `FK_comment_shop` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`shop_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_comment_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('good', 5, '/root/CommentImg/default.jpg', 2, 2, 17);
INSERT INTO `comment` VALUES ('good', 5, '/root/CommentImg/default.jpg', 3, 3, 17);
INSERT INTO `comment` VALUES ('6', 5, '/root/CommentImg/_1_17_1649560862172.jpg', 8, 1, 17);

-- ----------------------------
-- Table structure for food
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food`  (
  `food_type` varchar(100) CHARACTER SET utf32 COLLATE utf32_general_ci NULL DEFAULT NULL,
  `food_txt` varchar(100) CHARACTER SET utf32 COLLATE utf32_general_ci NULL DEFAULT NULL,
  `food_img_addr` varchar(100) CHARACTER SET utf32 COLLATE utf32_general_ci NULL DEFAULT NULL,
  `food_price` float NULL DEFAULT NULL,
  `food_name` varchar(100) CHARACTER SET utf32 COLLATE utf32_general_ci NULL DEFAULT NULL,
  `food_id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`food_id`) USING BTREE,
  INDEX `FK_FK_food_shop`(`shop_id`) USING BTREE,
  CONSTRAINT `FK_FK_food_shop` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`shop_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of food
-- ----------------------------
INSERT INTO `food` VALUES ('类型', '文本', '/root/FoodImg/food.jpg', 10, '名字', 1, 1);
INSERT INTO `food` VALUES ('aa', 'aa', '/root/FoodImg/food.jpg', 5, 'aa', 2, 1);
INSERT INTO `food` VALUES ('aa', 'aa', '/root/FoodImg/food.jpg', 5, 'aa', 3, 2);
INSERT INTO `food` VALUES ('aa', 'aa', '/root/FoodImg/food.jpg', 5, 'aa', 4, 3);
INSERT INTO `food` VALUES ('动物', '黑色', '/root/FoodImg/food:_1_1649641600206.jpg', 5, '乌鸦', 5, 1);

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment`  (
  `total_price` float NULL DEFAULT NULL,
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`oid`) USING BTREE,
  INDEX `FK_Relationship_6`(`user_id`) USING BTREE,
  CONSTRAINT `FK_Relationship_6` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of payment
-- ----------------------------
INSERT INTO `payment` VALUES (10, 1, 17);
INSERT INTO `payment` VALUES (15, 43, 17);
INSERT INTO `payment` VALUES (15, 44, 17);
INSERT INTO `payment` VALUES (15, 45, 17);
INSERT INTO `payment` VALUES (15, 46, 17);
INSERT INTO `payment` VALUES (15, 47, 17);
INSERT INTO `payment` VALUES (25, 48, 17);

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop`  (
  `shop_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `shop_name` varchar(100) CHARACTER SET utf32 COLLATE utf32_general_ci NULL DEFAULT NULL,
  `shop_img_addr` varchar(100) CHARACTER SET utf32 COLLATE utf32_general_ci NULL DEFAULT NULL,
  `shop_type` varchar(100) CHARACTER SET utf32 COLLATE utf32_general_ci NULL DEFAULT NULL,
  `shop_grade` float NULL DEFAULT NULL,
  PRIMARY KEY (`shop_id`) USING BTREE,
  INDEX `FK_possess`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO `shop` VALUES (1, 17, '店名1', '/root/ShopImg/tudouwang.png', '类型', 4);
INSERT INTO `shop` VALUES (2, 17, '店名2', '/root/ShopImg/123.png', '类型', 5);
INSERT INTO `shop` VALUES (3, 17, '店名3', '/root/ShopImg/123.png', '类型', 5);
INSERT INTO `shop` VALUES (4, 17, '名', '/root/img/_17_名_1649641317748.jpg', '25', 0);
INSERT INTO `shop` VALUES (5, 17, '3', '/root/img/_17_3_1649641403335.jpg', '啊6', 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `pay_password` varchar(100) CHARACTER SET utf32 COLLATE utf32_general_ci NULL DEFAULT NULL,
  `user_img_addr` varchar(100) CHARACTER SET utf32 COLLATE utf32_general_ci NULL DEFAULT NULL,
  `uname` varchar(100) CHARACTER SET utf32 COLLATE utf32_general_ci NULL DEFAULT NULL,
  `upassword` varchar(100) CHARACTER SET utf32 COLLATE utf32_general_ci NULL DEFAULT NULL,
  `usex` varchar(10) CHARACTER SET utf32 COLLATE utf32_general_ci NULL DEFAULT NULL,
  `unickname` varchar(100) CHARACTER SET utf32 COLLATE utf32_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (17, '123', '/root/UserImg/default.jpg', '123', '123', 'male', 'lqb1234124124');
INSERT INTO `user` VALUES (18, '1', '/root/UserImg/default.jpg', NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;

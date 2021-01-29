/*
 Navicat Premium Data Transfer

 Source Server         : 10.43.1.52
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : 10.43.1.52:3306
 Source Schema         : mall_sms

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 10/07/2020 22:21:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sms_coupon
-- ----------------------------
DROP TABLE IF EXISTS `sms_coupon`;
CREATE TABLE `sms_coupon`
(
    `id`                bigint(20)                                                     NOT NULL AUTO_INCREMENT COMMENT 'id',
    `coupon_type`       tinyint(1)                                                     NULL DEFAULT NULL COMMENT '优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]',
    `coupon_img`        varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '优惠券图片',
    `coupon_name`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL COMMENT '优惠卷名字',
    `num`               int(11)                                                        NULL DEFAULT NULL COMMENT '数量',
    `amount`            decimal(18, 4)                                                 NULL DEFAULT NULL COMMENT '金额',
    `per_limit`         int(11)                                                        NULL DEFAULT NULL COMMENT '每人限领张数',
    `min_point`         decimal(18, 4)                                                 NULL DEFAULT NULL COMMENT '使用门槛',
    `start_time`        datetime(0)                                                    NULL DEFAULT NULL COMMENT '开始时间',
    `end_time`          datetime(0)                                                    NULL DEFAULT NULL COMMENT '结束时间',
    `use_type`          tinyint(1)                                                     NULL DEFAULT NULL COMMENT '使用类型[0->全场通用；1->指定分类；2->指定商品]',
    `note`              varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL COMMENT '备注',
    `publish_count`     int(11)                                                        NULL DEFAULT NULL COMMENT '发行数量',
    `use_count`         int(11)                                                        NULL DEFAULT NULL COMMENT '已使用数量',
    `receive_count`     int(11)                                                        NULL DEFAULT NULL COMMENT '领取数量',
    `enable_start_time` datetime(0)                                                    NULL DEFAULT NULL COMMENT '可以领取的开始日期',
    `enable_end_time`   datetime(0)                                                    NULL DEFAULT NULL COMMENT '可以领取的结束日期',
    `code`              varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci   NULL DEFAULT NULL COMMENT '优惠码',
    `member_level`      tinyint(1)                                                     NULL DEFAULT NULL COMMENT '可以领取的会员等级[0->不限等级，其他-对应等级]',
    `publish`           tinyint(1)                                                     NULL DEFAULT NULL COMMENT '发布状态[0-未发布，1-已发布]',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '优惠券信息'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sms_coupon_history
-- ----------------------------
DROP TABLE IF EXISTS `sms_coupon_history`;
CREATE TABLE `sms_coupon_history`
(
    `id`               bigint(20)                                                   NOT NULL AUTO_INCREMENT COMMENT 'id',
    `coupon_id`        bigint(20)                                                   NULL DEFAULT NULL COMMENT '优惠券id',
    `member_id`        bigint(20)                                                   NULL DEFAULT NULL COMMENT '会员id',
    `member_nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '会员名字',
    `get_type`         tinyint(1)                                                   NULL DEFAULT NULL COMMENT '获取方式[0->后台赠送；1->主动领取]',
    `create_time`      datetime(0)                                                  NULL DEFAULT NULL COMMENT '创建时间',
    `use_type`         tinyint(1)                                                   NULL DEFAULT NULL COMMENT '使用状态[0->未使用；1->已使用；2->已过期]',
    `use_time`         datetime(0)                                                  NULL DEFAULT NULL COMMENT '使用时间',
    `order_id`         bigint(20)                                                   NULL DEFAULT NULL COMMENT '订单id',
    `order_sn`         bigint(20)                                                   NULL DEFAULT NULL COMMENT '订单号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '优惠券领取历史记录'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sms_coupon_spu_category_relation
-- ----------------------------
DROP TABLE IF EXISTS `sms_coupon_spu_category_relation`;
CREATE TABLE `sms_coupon_spu_category_relation`
(
    `id`            bigint(20)                                                   NOT NULL AUTO_INCREMENT COMMENT 'id',
    `coupon_id`     bigint(20)                                                   NULL DEFAULT NULL COMMENT '优惠券id',
    `category_id`   bigint(20)                                                   NULL DEFAULT NULL COMMENT '产品分类id',
    `category_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '产品分类名称',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '优惠券分类关联'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sms_coupon_spu_relation
-- ----------------------------
DROP TABLE IF EXISTS `sms_coupon_spu_relation`;
CREATE TABLE `sms_coupon_spu_relation`
(
    `id`        bigint(20)                                                    NOT NULL AUTO_INCREMENT COMMENT 'id',
    `coupon_id` bigint(20)                                                    NULL DEFAULT NULL COMMENT '优惠券id',
    `spu_id`    bigint(20)                                                    NULL DEFAULT NULL COMMENT 'spu_id',
    `spu_name`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'spu_name',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '优惠券与产品关联'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sms_home_adv
-- ----------------------------
DROP TABLE IF EXISTS `sms_home_adv`;
CREATE TABLE `sms_home_adv`
(
    `id`           bigint(20)                                                    NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`         varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名字',
    `pic`          varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图片地址',
    `start_time`   datetime(0)                                                   NULL DEFAULT NULL COMMENT '开始时间',
    `end_time`     datetime(0)                                                   NULL DEFAULT NULL COMMENT '结束时间',
    `status`       tinyint(1)                                                    NULL DEFAULT NULL COMMENT '状态',
    `click_count`  int(11)                                                       NULL DEFAULT NULL COMMENT '点击数',
    `url`          varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '广告详情连接地址',
    `note`         varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
    `sort`         int(11)                                                       NULL DEFAULT NULL COMMENT '排序',
    `publisher_id` bigint(20)                                                    NULL DEFAULT NULL COMMENT '发布者',
    `auth_id`      bigint(20)                                                    NULL DEFAULT NULL COMMENT '审核者',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '首页轮播广告'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sms_home_subject
-- ----------------------------
DROP TABLE IF EXISTS `sms_home_subject`;
CREATE TABLE `sms_home_subject`
(
    `id`        bigint(20)                                                    NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`      varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '专题名字',
    `title`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '专题标题',
    `sub_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '专题副标题',
    `status`    tinyint(1)                                                    NULL DEFAULT NULL COMMENT '显示状态',
    `url`       varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '详情连接',
    `sort`      int(11)                                                       NULL DEFAULT NULL COMMENT '排序',
    `img`       varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '专题图片地址',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sms_home_subject_spu
-- ----------------------------
DROP TABLE IF EXISTS `sms_home_subject_spu`;
CREATE TABLE `sms_home_subject_spu`
(
    `id`         bigint(20)                                                    NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`       varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '专题名字',
    `subject_id` bigint(20)                                                    NULL DEFAULT NULL COMMENT '专题id',
    `spu_id`     bigint(20)                                                    NULL DEFAULT NULL COMMENT 'spu_id',
    `sort`       int(11)                                                       NULL DEFAULT NULL COMMENT '排序',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '专题商品'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sms_member_price
-- ----------------------------
DROP TABLE IF EXISTS `sms_member_price`;
CREATE TABLE `sms_member_price`
(
    `id`                bigint(20)                                                    NOT NULL AUTO_INCREMENT COMMENT 'id',
    `sku_id`            bigint(20)                                                    NULL DEFAULT NULL COMMENT 'sku_id',
    `member_level_id`   bigint(20)                                                    NULL DEFAULT NULL COMMENT '会员等级id',
    `member_level_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '会员等级名',
    `member_price`      decimal(18, 4)                                                NULL DEFAULT NULL COMMENT '会员对应价格',
    `add_other`         tinyint(1)                                                    NULL DEFAULT NULL COMMENT '可否叠加其他优惠[0-不可叠加优惠，1-可叠加]',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 37
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '商品会员价格'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sms_member_price
-- ----------------------------
INSERT INTO `sms_member_price`
VALUES (1, 1, 2, '铜牌会员', 8788.0000, 1);
INSERT INTO `sms_member_price`
VALUES (2, 1, 3, '银牌会员', 8688.0000, 1);
INSERT INTO `sms_member_price`
VALUES (3, 1, 4, '金牌会员', 8588.0000, 1);
INSERT INTO `sms_member_price`
VALUES (4, 1, 5, '钻石会员', 8288.0000, 1);
INSERT INTO `sms_member_price`
VALUES (5, 2, 2, '铜牌会员', 8766.0000, 1);
INSERT INTO `sms_member_price`
VALUES (6, 2, 3, '银牌会员', 8666.0000, 1);
INSERT INTO `sms_member_price`
VALUES (7, 2, 4, '金牌会员', 8566.0000, 1);
INSERT INTO `sms_member_price`
VALUES (8, 2, 5, '钻石会员', 8266.0000, 1);
INSERT INTO `sms_member_price`
VALUES (9, 3, 2, '铜牌会员', 5888.0000, 1);
INSERT INTO `sms_member_price`
VALUES (10, 3, 3, '银牌会员', 5788.0000, 1);
INSERT INTO `sms_member_price`
VALUES (11, 3, 4, '金牌会员', 5688.0000, 1);
INSERT INTO `sms_member_price`
VALUES (12, 3, 5, '钻石会员', 5588.0000, 1);
INSERT INTO `sms_member_price`
VALUES (13, 4, 2, '铜牌会员', 5888.0000, 1);
INSERT INTO `sms_member_price`
VALUES (14, 4, 3, '银牌会员', 5788.0000, 1);
INSERT INTO `sms_member_price`
VALUES (15, 4, 4, '金牌会员', 5688.0000, 1);
INSERT INTO `sms_member_price`
VALUES (16, 4, 5, '钻石会员', 5588.0000, 1);
INSERT INTO `sms_member_price`
VALUES (17, 5, 2, '铜牌会员', 5888.0000, 1);
INSERT INTO `sms_member_price`
VALUES (18, 5, 3, '银牌会员', 5788.0000, 1);
INSERT INTO `sms_member_price`
VALUES (19, 5, 4, '金牌会员', 5688.0000, 1);
INSERT INTO `sms_member_price`
VALUES (20, 5, 5, '钻石会员', 5588.0000, 1);
INSERT INTO `sms_member_price`
VALUES (21, 6, 2, '铜牌会员', 5888.0000, 1);
INSERT INTO `sms_member_price`
VALUES (22, 6, 3, '银牌会员', 5788.0000, 1);
INSERT INTO `sms_member_price`
VALUES (23, 6, 4, '金牌会员', 5688.0000, 1);
INSERT INTO `sms_member_price`
VALUES (24, 6, 5, '钻石会员', 5588.0000, 1);
INSERT INTO `sms_member_price`
VALUES (25, 7, 2, '铜牌会员', 5888.0000, 1);
INSERT INTO `sms_member_price`
VALUES (26, 7, 3, '银牌会员', 5788.0000, 1);
INSERT INTO `sms_member_price`
VALUES (27, 7, 4, '金牌会员', 5688.0000, 1);
INSERT INTO `sms_member_price`
VALUES (28, 7, 5, '钻石会员', 5588.0000, 1);
INSERT INTO `sms_member_price`
VALUES (29, 8, 2, '铜牌会员', 5888.0000, 1);
INSERT INTO `sms_member_price`
VALUES (30, 8, 3, '银牌会员', 5788.0000, 1);
INSERT INTO `sms_member_price`
VALUES (31, 8, 4, '金牌会员', 5688.0000, 1);
INSERT INTO `sms_member_price`
VALUES (32, 8, 5, '钻石会员', 5588.0000, 1);
INSERT INTO `sms_member_price`
VALUES (33, 9, 2, '铜牌会员', 4088.0000, 1);
INSERT INTO `sms_member_price`
VALUES (34, 9, 3, '银牌会员', 3988.0000, 1);
INSERT INTO `sms_member_price`
VALUES (35, 9, 4, '金牌会员', 3888.0000, 1);
INSERT INTO `sms_member_price`
VALUES (36, 9, 5, '钻石会员', 3288.0000, 1);

-- ----------------------------
-- Table structure for sms_seckill_promotion
-- ----------------------------
DROP TABLE IF EXISTS `sms_seckill_promotion`;
CREATE TABLE `sms_seckill_promotion`
(
    `id`          bigint(20)                                                    NOT NULL AUTO_INCREMENT COMMENT 'id',
    `title`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '活动标题',
    `start_time`  datetime(0)                                                   NULL DEFAULT NULL COMMENT '开始日期',
    `end_time`    datetime(0)                                                   NULL DEFAULT NULL COMMENT '结束日期',
    `status`      tinyint(4)                                                    NULL DEFAULT NULL COMMENT '上下线状态',
    `create_time` datetime(0)                                                   NULL DEFAULT NULL COMMENT '创建时间',
    `user_id`     bigint(20)                                                    NULL DEFAULT NULL COMMENT '创建人',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '秒杀活动'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sms_seckill_session
-- ----------------------------
DROP TABLE IF EXISTS `sms_seckill_session`;
CREATE TABLE `sms_seckill_session`
(
    `id`          bigint(20)                                                    NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`        varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '场次名称',
    `start_time`  datetime(0)                                                   NULL DEFAULT NULL COMMENT '每日开始时间',
    `end_time`    datetime(0)                                                   NULL DEFAULT NULL COMMENT '每日结束时间',
    `status`      tinyint(1)                                                    NULL DEFAULT NULL COMMENT '启用状态',
    `create_time` datetime(0)                                                   NULL DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '秒杀活动场次'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sms_seckill_session
-- ----------------------------
INSERT INTO `sms_seckill_session`
VALUES (1, '谷粒秒杀', '2020-07-10 00:15:00', '2020-07-10 15:59:59', 1, '2020-07-09 15:14:21');
INSERT INTO `sms_seckill_session`
VALUES (2, '谷粒秒杀', '2020-07-09 05:15:00', '2020-07-09 15:59:59', 1, '2020-07-09 05:14:47');

-- ----------------------------
-- Table structure for sms_seckill_sku_notice
-- ----------------------------
DROP TABLE IF EXISTS `sms_seckill_sku_notice`;
CREATE TABLE `sms_seckill_sku_notice`
(
    `id`            bigint(20)  NOT NULL AUTO_INCREMENT COMMENT 'id',
    `member_id`     bigint(20)  NULL DEFAULT NULL COMMENT 'member_id',
    `sku_id`        bigint(20)  NULL DEFAULT NULL COMMENT 'sku_id',
    `session_id`    bigint(20)  NULL DEFAULT NULL COMMENT '活动场次id',
    `subcribe_time` datetime(0) NULL DEFAULT NULL COMMENT '订阅时间',
    `send_time`     datetime(0) NULL DEFAULT NULL COMMENT '发送时间',
    `notice_type`   tinyint(1)  NULL DEFAULT NULL COMMENT '通知方式[0-短信，1-邮件]',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '秒杀商品通知订阅'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sms_seckill_sku_relation
-- ----------------------------
DROP TABLE IF EXISTS `sms_seckill_sku_relation`;
CREATE TABLE `sms_seckill_sku_relation`
(
    `id`                   bigint(20)     NOT NULL AUTO_INCREMENT COMMENT 'id',
    `promotion_id`         bigint(20)     NULL DEFAULT NULL COMMENT '活动id',
    `promotion_session_id` bigint(20)     NULL DEFAULT NULL COMMENT '活动场次id',
    `sku_id`               bigint(20)     NULL DEFAULT NULL COMMENT '商品id',
    `seckill_price`        decimal(10, 0) NULL DEFAULT NULL COMMENT '秒杀价格',
    `seckill_count`        decimal(10, 0) NULL DEFAULT NULL COMMENT '秒杀总量',
    `seckill_limit`        decimal(10, 0) NULL DEFAULT NULL COMMENT '每人限购数量',
    `seckill_sort`         int(11)        NULL DEFAULT NULL COMMENT '排序',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '秒杀活动商品关联'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sms_seckill_sku_relation
-- ----------------------------
INSERT INTO `sms_seckill_sku_relation`
VALUES (1, NULL, 1, 1, 4888, 100, 1, 0);
INSERT INTO `sms_seckill_sku_relation`
VALUES (2, NULL, 2, 1, 6666, 200, 1, 0);

-- ----------------------------
-- Table structure for sms_sku_full_reduction
-- ----------------------------
DROP TABLE IF EXISTS `sms_sku_full_reduction`;
CREATE TABLE `sms_sku_full_reduction`
(
    `id`           bigint(20)     NOT NULL AUTO_INCREMENT COMMENT 'id',
    `sku_id`       bigint(20)     NULL DEFAULT NULL COMMENT 'spu_id',
    `full_price`   decimal(18, 4) NULL DEFAULT NULL COMMENT '满多少',
    `reduce_price` decimal(18, 4) NULL DEFAULT NULL COMMENT '减多少',
    `add_other`    tinyint(1)     NULL DEFAULT NULL COMMENT '是否参与其他优惠',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '商品满减信息'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sms_sku_full_reduction
-- ----------------------------
INSERT INTO `sms_sku_full_reduction`
VALUES (1, 1, 10000.0000, 80.0000, NULL);
INSERT INTO `sms_sku_full_reduction`
VALUES (2, 2, 10000.0000, 80.0000, NULL);
INSERT INTO `sms_sku_full_reduction`
VALUES (3, 3, 10000.0000, 80.0000, NULL);
INSERT INTO `sms_sku_full_reduction`
VALUES (4, 4, 10000.0000, 80.0000, NULL);
INSERT INTO `sms_sku_full_reduction`
VALUES (5, 5, 10000.0000, 80.0000, NULL);
INSERT INTO `sms_sku_full_reduction`
VALUES (6, 6, 10000.0000, 80.0000, NULL);
INSERT INTO `sms_sku_full_reduction`
VALUES (7, 7, 10000.0000, 80.0000, NULL);
INSERT INTO `sms_sku_full_reduction`
VALUES (8, 8, 10000.0000, 80.0000, NULL);
INSERT INTO `sms_sku_full_reduction`
VALUES (9, 9, 6666.0000, 200.0000, NULL);

-- ----------------------------
-- Table structure for sms_sku_ladder
-- ----------------------------
DROP TABLE IF EXISTS `sms_sku_ladder`;
CREATE TABLE `sms_sku_ladder`
(
    `id`         bigint(20)     NOT NULL AUTO_INCREMENT COMMENT 'id',
    `sku_id`     bigint(20)     NULL DEFAULT NULL COMMENT 'spu_id',
    `full_count` int(11)        NULL DEFAULT NULL COMMENT '满几件',
    `discount`   decimal(4, 2)  NULL DEFAULT NULL COMMENT '打几折',
    `price`      decimal(18, 4) NULL DEFAULT NULL COMMENT '折后价',
    `add_other`  tinyint(1)     NULL DEFAULT NULL COMMENT '是否叠加其他优惠[0-不可叠加，1-可叠加]',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 19
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '商品阶梯价格'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sms_sku_ladder
-- ----------------------------
INSERT INTO `sms_sku_ladder`
VALUES (1, 1, 3, 0.92, NULL, 1);
INSERT INTO `sms_sku_ladder`
VALUES (2, 1, 3, 0.92, NULL, 1);
INSERT INTO `sms_sku_ladder`
VALUES (3, 2, 5, 0.92, NULL, 1);
INSERT INTO `sms_sku_ladder`
VALUES (4, 2, 5, 0.92, NULL, 1);
INSERT INTO `sms_sku_ladder`
VALUES (5, 3, 3, 0.92, NULL, 0);
INSERT INTO `sms_sku_ladder`
VALUES (6, 3, 3, 0.92, NULL, 0);
INSERT INTO `sms_sku_ladder`
VALUES (7, 4, 3, 0.92, NULL, 0);
INSERT INTO `sms_sku_ladder`
VALUES (8, 4, 3, 0.92, NULL, 0);
INSERT INTO `sms_sku_ladder`
VALUES (9, 5, 3, 0.92, NULL, 0);
INSERT INTO `sms_sku_ladder`
VALUES (10, 5, 3, 0.92, NULL, 0);
INSERT INTO `sms_sku_ladder`
VALUES (11, 6, 3, 0.92, NULL, 0);
INSERT INTO `sms_sku_ladder`
VALUES (12, 6, 3, 0.92, NULL, 0);
INSERT INTO `sms_sku_ladder`
VALUES (13, 7, 3, 0.92, NULL, 1);
INSERT INTO `sms_sku_ladder`
VALUES (14, 7, 3, 0.92, NULL, 1);
INSERT INTO `sms_sku_ladder`
VALUES (15, 8, 3, 0.92, NULL, 1);
INSERT INTO `sms_sku_ladder`
VALUES (16, 8, 3, 0.92, NULL, 1);
INSERT INTO `sms_sku_ladder`
VALUES (17, 9, 4, 0.80, NULL, 0);
INSERT INTO `sms_sku_ladder`
VALUES (18, 9, 4, 0.80, NULL, 0);

-- ----------------------------
-- Table structure for sms_spu_bounds
-- ----------------------------
DROP TABLE IF EXISTS `sms_spu_bounds`;
CREATE TABLE `sms_spu_bounds`
(
    `id`          bigint(20)     NOT NULL AUTO_INCREMENT COMMENT 'id',
    `spu_id`      bigint(20)     NULL DEFAULT NULL,
    `grow_bounds` decimal(18, 4) NULL DEFAULT NULL COMMENT '成长积分',
    `buy_bounds`  decimal(18, 4) NULL DEFAULT NULL COMMENT '购物积分',
    `work`        tinyint(1)     NULL DEFAULT NULL COMMENT '优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '商品spu积分设置'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sms_spu_bounds
-- ----------------------------
INSERT INTO `sms_spu_bounds`
VALUES (1, 1, 500.0000, 2000.0000, NULL);
INSERT INTO `sms_spu_bounds`
VALUES (2, 2, 500.0000, 2000.0000, NULL);
INSERT INTO `sms_spu_bounds`
VALUES (3, 3, 500.0000, 2000.0000, NULL);

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`
(
    `id`            bigint(20)                                              NOT NULL AUTO_INCREMENT,
    `branch_id`     bigint(20)                                              NOT NULL,
    `xid`           varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `context`       varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `rollback_info` longblob                                                NOT NULL,
    `log_status`    int(11)                                                 NOT NULL,
    `log_created`   datetime(0)                                             NOT NULL,
    `log_modified`  datetime(0)                                             NOT NULL,
    `ext`           varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `ux_undo_log` (`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;


/*
 Navicat Premium Data Transfer

 Source Server         : 10.43.1.52
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : 10.43.1.52:3306
 Source Schema         : mall_ums

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 10/07/2020 22:21:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ums_growth_change_history
-- ----------------------------
DROP TABLE IF EXISTS `ums_growth_change_history`;
CREATE TABLE `ums_growth_change_history`
(
    `id`           bigint(20)                                                  NOT NULL AUTO_INCREMENT COMMENT 'id',
    `member_id`    bigint(20)                                                  NULL DEFAULT NULL COMMENT 'member_id',
    `create_time`  datetime(0)                                                 NULL DEFAULT NULL COMMENT 'create_time',
    `change_count` int(11)                                                     NULL DEFAULT NULL COMMENT '改变的值（正负计数）',
    `note`         varchar(0) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
    `source_type`  tinyint(4)                                                  NULL DEFAULT NULL COMMENT '积分来源[0-购物，1-管理员修改]',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '成长值变化历史记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ums_integration_change_history
-- ----------------------------
DROP TABLE IF EXISTS `ums_integration_change_history`;
CREATE TABLE `ums_integration_change_history`
(
    `id`           bigint(20)                                                    NOT NULL AUTO_INCREMENT COMMENT 'id',
    `member_id`    bigint(20)                                                    NULL DEFAULT NULL COMMENT 'member_id',
    `create_time`  datetime(0)                                                   NULL DEFAULT NULL COMMENT 'create_time',
    `change_count` int(11)                                                       NULL DEFAULT NULL COMMENT '变化的值',
    `note`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
    `source_tyoe`  tinyint(4)                                                    NULL DEFAULT NULL COMMENT '来源[0->购物；1->管理员修改;2->活动]',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '积分变化历史记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ums_member
-- ----------------------------
DROP TABLE IF EXISTS `ums_member`;
CREATE TABLE `ums_member`
(
    `id`           bigint(20)                                                    NOT NULL AUTO_INCREMENT COMMENT 'id',
    `level_id`     bigint(20)                                                    NULL DEFAULT NULL COMMENT '会员等级id',
    `username`     char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci     NULL DEFAULT NULL COMMENT '用户名',
    `password`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL COMMENT '密码',
    `nickname`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL COMMENT '昵称',
    `mobile`       varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL COMMENT '手机号码',
    `email`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL COMMENT '邮箱',
    `header`       varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
    `gender`       tinyint(4)                                                    NULL DEFAULT NULL COMMENT '性别',
    `birth`        date                                                          NULL DEFAULT NULL COMMENT '生日',
    `city`         varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '所在城市',
    `job`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '职业',
    `sign`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '个性签名',
    `source_type`  tinyint(4)                                                    NULL DEFAULT NULL COMMENT '用户来源',
    `integration`  int(11)                                                       NULL DEFAULT NULL COMMENT '积分',
    `growth`       int(11)                                                       NULL DEFAULT NULL COMMENT '成长值',
    `status`       tinyint(4)                                                    NULL DEFAULT NULL COMMENT '启用状态',
    `create_time`  datetime(0)                                                   NULL DEFAULT NULL COMMENT '注册时间',
    `social_uid`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '社交账号ID',
    `access_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '社交账号Token',
    `expires_in`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '社交账号Token过期时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '会员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_member
-- ----------------------------
INSERT INTO `ums_member` VALUES (1, 1, 'firenay', '$2a$10$uDXc05.IsjGI//f7HC8/lOGys0oRiFZX59olzqVqTl8IKxR8iBVGy', 'firenay', '18173516208', 'xxx@gmail.com', NULL, 0, '2020-06-25', '湖南 长沙', 'JAVA', NULL, NULL, NULL, NULL, 0, '2020-06-25 13:09:14', NULL, NULL, NULL);
INSERT INTO `ums_member` VALUES (2, 1, 'sentinel', '$2a$10$j5XRpUeGq7AYIFk7pqdvyebK.Bo5MvasCxk.8RuBWsHFcq5RzXKEC', 'sentinel', '18173516102', 'xxx@gmail.com', NULL, 1, '2020-06-25', '湖南 长沙', 'JAVA', NULL, NULL, NULL, NULL, 0, '2020-06-25 13:15:33', NULL, NULL, NULL);
INSERT INTO `ums_member` VALUES (3, 1, 'firenayfly', '$2a$10$UvvfpBagTqbalI6UTnq5nOiPheEdbKLO64fozWMx1lUeK9p2tM366', 'firenayfly', '18467894965', 'xxx@gmail.com', NULL, 1, '2020-06-25', '湖南 长沙', 'JAVA', NULL, NULL, NULL, NULL, 0, '2020-06-25 13:18:32', NULL, NULL, NULL);
INSERT INTO `ums_member` VALUES (4, 1, '汀西氟的我是你', '$2a$10$uDXc05.IsjGI//f7HC8/lOGys0oRiFZX59olzqVqTl8IKxR8iBVGy', '汀西氟的我是你', '18467894965', 'xxx@gmail.com', NULL, 1, '2020-06-26', '湖南 长沙', '自媒体', NULL, NULL, NULL, NULL, 0, '2020-06-26 09:36:00', '5605937365', '2.00b5w4HGMwxc6B0e3d62c666DlN1DD', '157679999');

-- ----------------------------
-- Table structure for ums_member_collect_spu
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_collect_spu`;
CREATE TABLE `ums_member_collect_spu`
(
    `id`          bigint(20)                                                    NOT NULL COMMENT 'id',
    `member_id`   bigint(20)                                                    NULL DEFAULT NULL COMMENT '会员id',
    `spu_id`      bigint(20)                                                    NULL DEFAULT NULL COMMENT 'spu_id',
    `spu_name`    varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'spu_name',
    `spu_img`     varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'spu_img',
    `create_time` datetime(0)                                                   NULL DEFAULT NULL COMMENT 'create_time',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '会员收藏的商品' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ums_member_collect_subject
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_collect_subject`;
CREATE TABLE `ums_member_collect_subject`
(
    `id`           bigint(20)                                                    NOT NULL AUTO_INCREMENT COMMENT 'id',
    `subject_id`   bigint(20)                                                    NULL DEFAULT NULL COMMENT 'subject_id',
    `subject_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'subject_name',
    `subject_img`  varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'subject_img',
    `subject_urll` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '活动url',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '会员收藏的专题活动' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ums_member_level
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_level`;
CREATE TABLE `ums_member_level`
(
    `id`                      bigint(20)                                                    NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`                    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '等级名称',
    `growth_point`            int(11)                                                       NULL DEFAULT NULL COMMENT '等级需要的成长值',
    `default_status`          tinyint(4)                                                    NULL DEFAULT NULL COMMENT '是否为默认等级[0->不是；1->是]',
    `free_freight_point`      decimal(18, 4)                                                NULL DEFAULT NULL COMMENT '免运费标准',
    `comment_growth_point`    int(11)                                                       NULL DEFAULT NULL COMMENT '每次评价获取的成长值',
    `priviledge_free_freight` tinyint(4)                                                    NULL DEFAULT NULL COMMENT '是否有免邮特权',
    `priviledge_member_price` tinyint(4)                                                    NULL DEFAULT NULL COMMENT '是否有会员价格特权',
    `priviledge_birthday`     tinyint(4)                                                    NULL DEFAULT NULL COMMENT '是否有生日特权',
    `note`                    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '会员等级' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_member_level
-- ----------------------------
INSERT INTO `ums_member_level` VALUES (1, '普通会员', 0, 1, 188.0000, 10, 0, 0, 1, '初级会员');
INSERT INTO `ums_member_level` VALUES (2, '铜牌会员', 2000, 0, 159.0000, 20, 0, 1, 1, '铜牌会员');
INSERT INTO `ums_member_level` VALUES (3, '银牌会员', 5000, 0, 129.0000, 50, 0, 1, 1, '银牌会员');
INSERT INTO `ums_member_level` VALUES (4, '金牌会员', 8000, 0, 88.0000, 0, 1, 1, 1, '金牌会员');
INSERT INTO `ums_member_level` VALUES (5, '钻石会员', 12000, 0, 48.0000, 80, 1, 1, 1, '钻石会员');

-- ----------------------------
-- Table structure for ums_member_login_log
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_login_log`;
CREATE TABLE `ums_member_login_log`
(
    `id`          bigint(20)                                                   NOT NULL AUTO_INCREMENT COMMENT 'id',
    `member_id`   bigint(20)                                                   NULL DEFAULT NULL COMMENT 'member_id',
    `create_time` datetime(0)                                                  NULL DEFAULT NULL COMMENT '创建时间',
    `ip`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'ip',
    `city`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'city',
    `login_type`  tinyint(1)                                                   NULL DEFAULT NULL COMMENT '登录类型[1-web，2-app]',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '会员登录记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ums_member_receive_address
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_receive_address`;
CREATE TABLE `ums_member_receive_address`
(
    `id`             bigint(20)                                                    NOT NULL AUTO_INCREMENT COMMENT 'id',
    `member_id`      bigint(20)                                                    NULL DEFAULT NULL COMMENT 'member_id',
    `name`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '收货人姓名',
    `phone`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL COMMENT '电话',
    `post_code`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL COMMENT '邮政编码',
    `province`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '省份/直辖市',
    `city`           varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '城市',
    `region`         varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '区',
    `detail_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '详细地址(街道)',
    `areacode`       varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL COMMENT '省市区代码',
    `default_status` tinyint(1)                                                    NULL DEFAULT NULL COMMENT '是否默认',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '会员收货地址' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_member_receive_address
-- ----------------------------
INSERT INTO `ums_member_receive_address` VALUES (1, 1, 'firenay', '18173516208', NULL, '湖南', '长沙', NULL, '望城区', NULL, 1);
INSERT INTO `ums_member_receive_address` VALUES (2, 2, 'sentinel', '18173516102', NULL, '湖南', '长沙', NULL, '雨花区', NULL, 1);
INSERT INTO `ums_member_receive_address` VALUES (3, 3, 'firenayfly', '15421564125', NULL, '陕西', '西安', NULL, '新城区', NULL, 1);

-- ----------------------------
-- Table structure for ums_member_statistics_info
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_statistics_info`;
CREATE TABLE `ums_member_statistics_info`
(
    `id`                    bigint(20)     NOT NULL AUTO_INCREMENT COMMENT 'id',
    `member_id`             bigint(20)     NULL DEFAULT NULL COMMENT '会员id',
    `consume_amount`        decimal(18, 4) NULL DEFAULT NULL COMMENT '累计消费金额',
    `coupon_amount`         decimal(18, 4) NULL DEFAULT NULL COMMENT '累计优惠金额',
    `order_count`           int(11)        NULL DEFAULT NULL COMMENT '订单数量',
    `coupon_count`          int(11)        NULL DEFAULT NULL COMMENT '优惠券数量',
    `comment_count`         int(11)        NULL DEFAULT NULL COMMENT '评价数',
    `return_order_count`    int(11)        NULL DEFAULT NULL COMMENT '退货数量',
    `login_count`           int(11)        NULL DEFAULT NULL COMMENT '登录次数',
    `attend_count`          int(11)        NULL DEFAULT NULL COMMENT '关注数量',
    `fans_count`            int(11)        NULL DEFAULT NULL COMMENT '粉丝数量',
    `collect_product_count` int(11)        NULL DEFAULT NULL COMMENT '收藏的商品数量',
    `collect_subject_count` int(11)        NULL DEFAULT NULL COMMENT '收藏的专题活动数量',
    `collect_comment_count` int(11)        NULL DEFAULT NULL COMMENT '收藏的评论数量',
    `invite_friend_count`   int(11)        NULL DEFAULT NULL COMMENT '邀请的朋友数量',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '会员统计信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`
(
    `id`            bigint(20)                                              NOT NULL AUTO_INCREMENT,
    `branch_id`     bigint(20)                                              NOT NULL,
    `xid`           varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `context`       varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `rollback_info` longblob                                                NOT NULL,
    `log_status`    int(11)                                                 NOT NULL,
    `log_created`   datetime(0)                                             NOT NULL,
    `log_modified`  datetime(0)                                             NOT NULL,
    `ext`           varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `ux_undo_log` (`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

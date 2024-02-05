/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80100
 Source Host           : localhost:3306
 Source Schema         : yx_tpl

 Target Server Type    : MySQL
 Target Server Version : 80100
 File Encoding         : 65001

 Date: 05/02/2024 14:38:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for yx_sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `yx_sys_dict`;
CREATE TABLE `yx_sys_dict`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典代码',
  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'lable',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'value',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_sys_dict
-- ----------------------------
INSERT INTO `yx_sys_dict` VALUES (1, 'y_n', '是', '1');
INSERT INTO `yx_sys_dict` VALUES (2, 'y_n', '否', '0');
INSERT INTO `yx_sys_dict` VALUES (3, 'menu_type', '目录', '0');
INSERT INTO `yx_sys_dict` VALUES (4, 'menu_type', '菜单', '1');
INSERT INTO `yx_sys_dict` VALUES (5, 'menu_type', '按钮', '2');

-- ----------------------------
-- Table structure for yx_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `yx_sys_menu`;
CREATE TABLE `yx_sys_menu`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID，自增',
  `parent_id` int NULL DEFAULT NULL COMMENT '父菜单ID，顶级菜单为NULL',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单标题',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单路由路径',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由name',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '前端Vue组件路径',
  `order_num` int NULL DEFAULT 0 COMMENT '菜单排序顺序',
  `visible` tinyint(1) NULL DEFAULT 1 COMMENT '菜单是否可见, 1为可见，0为不可见',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限标识，用于权限控制',
  `is_keep_alive` tinyint(1) NULL DEFAULT NULL COMMENT '当前路由是否缓存 0：否 1：是',
  `redirect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由重定向地址',
  `is_full` tinyint(1) NULL DEFAULT NULL COMMENT '是否全屏',
  PRIMARY KEY (`id`) USING BTREE COMMENT '主键'
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '后台管理系统动态菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_sys_menu
-- ----------------------------
INSERT INTO `yx_sys_menu` VALUES (1, NULL, '首页', 'HomeFilled', '/home/index', 'home', '0', '/home/index', 1, 1, 'dashboard:view', NULL, '', 0);
INSERT INTO `yx_sys_menu` VALUES (3, NULL, '用户管理', 'User', '/users', 'user', '0', '/home/index', 2, 1, 'users:manage', NULL, '/users/list', 0);
INSERT INTO `yx_sys_menu` VALUES (4, 3, '用户列表', 'list', '/users/list', 'userlist', '1', '/home/index', 1, 1, 'users:list:view', NULL, NULL, 0);
INSERT INTO `yx_sys_menu` VALUES (5, 3, '创建用户', 'add', '/users/create', 'usercreate', '1', '/home/index', 2, 1, 'users:create', NULL, NULL, 0);
INSERT INTO `yx_sys_menu` VALUES (6, 3, '删除用户', NULL, '/settings/personal', 'settingspersonal', '2', '/settings/personal', NULL, 0, 'users:delete', NULL, NULL, 0);
INSERT INTO `yx_sys_menu` VALUES (7, 11, '组织架构', 'FolderOpened', '/system/permission', 'permission', '0', '/system/permission', 1, 1, '', 0, '', 0);
INSERT INTO `yx_sys_menu` VALUES (8, 11, '应用设置', 'Avatar', '/settings/application', 'application', '1', '/home/index', 1, 1, 'settings:app:view', NULL, NULL, 0);
INSERT INTO `yx_sys_menu` VALUES (9, 11, '个人设置', 'Apple', '/settings/personal', 'personal', '1', '/PersonalSettings', 2, 1, 'settings:personal:view', NULL, NULL, 0);
INSERT INTO `yx_sys_menu` VALUES (10, 11, '修改密码', NULL, '/setings/updatepwd', 'updatepwd', '1', '/setings/updatepwd', NULL, 0, 'settings:password:change', NULL, NULL, 0);
INSERT INTO `yx_sys_menu` VALUES (11, NULL, '系统', '', '/system/setting', '/setting', '0', '/system/setting', 1, 1, '', 0, '', 0);
INSERT INTO `yx_sys_menu` VALUES (12, 11, '菜单管理', NULL, '/system/menu', NULL, '1', '/system/menu/index', 0, 1, NULL, NULL, NULL, 0);
INSERT INTO `yx_sys_menu` VALUES (51, NULL, '我是测试', '', '222', '222', '0', '222', 1, 1, '', 0, '', 0);
INSERT INTO `yx_sys_menu` VALUES (52, NULL, '我是测试哦222', '', '/tes', 'tes', '1', '/tes', 1, 1, '', 0, '', 0);

-- ----------------------------
-- Table structure for yx_sys_role_menu_permissions
-- ----------------------------
DROP TABLE IF EXISTS `yx_sys_role_menu_permissions`;
CREATE TABLE `yx_sys_role_menu_permissions`  (
  `role_id` int NOT NULL COMMENT '角色ID',
  `menu_id` int NOT NULL COMMENT '菜单ID',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '具体的权限标识',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE COMMENT '复合主键',
  INDEX `fk_role_menu_menu_id`(`menu_id` ASC) USING BTREE,
  CONSTRAINT `fk_role_menu_menu_id` FOREIGN KEY (`menu_id`) REFERENCES `yx_sys_menu` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_role_menu_role_id` FOREIGN KEY (`role_id`) REFERENCES `yx_sys_roles` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色与菜单权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_sys_role_menu_permissions
-- ----------------------------
INSERT INTO `yx_sys_role_menu_permissions` VALUES (1, 1, NULL);
INSERT INTO `yx_sys_role_menu_permissions` VALUES (1, 4, NULL);
INSERT INTO `yx_sys_role_menu_permissions` VALUES (1, 5, NULL);
INSERT INTO `yx_sys_role_menu_permissions` VALUES (1, 8, NULL);
INSERT INTO `yx_sys_role_menu_permissions` VALUES (1, 9, NULL);
INSERT INTO `yx_sys_role_menu_permissions` VALUES (1, 10, NULL);

-- ----------------------------
-- Table structure for yx_sys_roles
-- ----------------------------
DROP TABLE IF EXISTS `yx_sys_roles`;
CREATE TABLE `yx_sys_roles`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '角色ID，主键自增',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '角色描述',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '角色创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE COMMENT '主键'
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_sys_roles
-- ----------------------------
INSERT INTO `yx_sys_roles` VALUES (1, 'Admin', '系统管理员拥有所有权限', '2024-01-26 09:59:56', '2024-01-26 09:59:56');
INSERT INTO `yx_sys_roles` VALUES (2, 'Editor', '编辑可以发布和编辑文章', '2024-01-26 09:59:56', '2024-01-26 09:59:56');
INSERT INTO `yx_sys_roles` VALUES (3, 'Viewer', '查看者只能查看数据', '2024-01-26 09:59:56', '2024-01-26 09:59:56');

-- ----------------------------
-- Table structure for yx_sys_user_roles
-- ----------------------------
DROP TABLE IF EXISTS `yx_sys_user_roles`;
CREATE TABLE `yx_sys_user_roles`  (
  `user_id` int NOT NULL COMMENT '用户ID',
  `role_id` int NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE COMMENT '复合主键',
  INDEX `fk_user_role_role_id`(`role_id` ASC) USING BTREE,
  CONSTRAINT `fk_user_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `yx_sys_roles` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_role_user_id` FOREIGN KEY (`user_id`) REFERENCES `yx_sys_users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户与角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_sys_user_roles
-- ----------------------------
INSERT INTO `yx_sys_user_roles` VALUES (1, 1);
INSERT INTO `yx_sys_user_roles` VALUES (2, 2);
INSERT INTO `yx_sys_user_roles` VALUES (3, 3);

-- ----------------------------
-- Table structure for yx_sys_users
-- ----------------------------
DROP TABLE IF EXISTS `yx_sys_users`;
CREATE TABLE `yx_sys_users`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID，主键自增',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户密码',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户手机号码',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '用户状态，1为启用，0为禁用',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '用户创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE COMMENT '主键'
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_sys_users
-- ----------------------------
INSERT INTO `yx_sys_users` VALUES (1, 'admin', 'e3274be5c857fb42ab72d786e281b4b8', 'admin@example.com', '1234567890', 1, '2024-01-26 09:59:50', '2024-01-26 09:59:50');
INSERT INTO `yx_sys_users` VALUES (2, 'editor', '4c11b65be3278bcd80064fe03e76b5b8', 'editor@example.com', '2345678901', 1, '2024-01-26 09:59:50', '2024-01-26 09:59:50');
INSERT INTO `yx_sys_users` VALUES (3, 'viewer', '884d0eebbf6b4967ada0d4dc0ca7eb66', 'viewer@example.com', '3456789012', 1, '2024-01-26 09:59:50', '2024-01-26 09:59:50');

SET FOREIGN_KEY_CHECKS = 1;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 1. 投票活动表
-- ----------------------------
DROP TABLE IF EXISTS vote_activity;
CREATE TABLE vote_activity (
  activity_id      bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '活动ID',
  title            varchar(100) NOT NULL                COMMENT '活动标题',
  description      varchar(500) DEFAULT ''              COMMENT '活动说明',
  cover            varchar(255) DEFAULT ''              COMMENT '封面图',
  votes_per_person int(11)      DEFAULT 8               COMMENT '每人可投票数',
  multi_per_pair   char(1)      DEFAULT '0'             COMMENT '同一候选人同一维度可重复投(0否 1是)',
  require_name     char(1)      DEFAULT '1'             COMMENT '投票是否需填写姓名(0否 1是)',
  status           char(1)      DEFAULT '0'             COMMENT '状态(0未开始 1进行中 2已结束)',
  start_time       datetime     DEFAULT NULL            COMMENT '开始时间',
  end_time         datetime     DEFAULT NULL            COMMENT '结束时间',
  del_flag         char(1)      DEFAULT '0'             COMMENT '删除标志(0存在 2删除)',
  create_by        varchar(64)  DEFAULT ''              COMMENT '创建者',
  create_time      datetime     DEFAULT NULL            COMMENT '创建时间',
  update_by        varchar(64)  DEFAULT ''              COMMENT '更新者',
  update_time      datetime     DEFAULT NULL            COMMENT '更新时间',
  remark           varchar(500) DEFAULT NULL            COMMENT '备注',
  PRIMARY KEY (activity_id)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='投票活动表';

-- ----------------------------
-- 2. 候选人表
-- ----------------------------
DROP TABLE IF EXISTS vote_candidate;
CREATE TABLE vote_candidate (
  candidate_id bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '候选人ID',
  activity_id  bigint(20)   NOT NULL                COMMENT '所属活动ID',
  name         varchar(60)  NOT NULL                COMMENT '候选人姓名',
  no           varchar(20)  DEFAULT ''              COMMENT '编号',
  avatar       varchar(255) DEFAULT ''              COMMENT '头像',
  description  varchar(255) DEFAULT ''              COMMENT '简介/宣言',
  sort         int(11)      DEFAULT 0               COMMENT '排序',
  status       char(1)      DEFAULT '0'             COMMENT '状态(0正常 1停用)',
  create_by    varchar(64)  DEFAULT ''              COMMENT '创建者',
  create_time  datetime     DEFAULT NULL            COMMENT '创建时间',
  update_by    varchar(64)  DEFAULT ''              COMMENT '更新者',
  update_time  datetime     DEFAULT NULL            COMMENT '更新时间',
  remark       varchar(500) DEFAULT NULL            COMMENT '备注',
  PRIMARY KEY (candidate_id),
  KEY idx_cand_activity (activity_id)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT='投票候选人表';

-- ----------------------------
-- 3. 评选维度表(选项)
-- ----------------------------
DROP TABLE IF EXISTS vote_option;
CREATE TABLE vote_option (
  option_id    bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '维度ID',
  activity_id  bigint(20)   NOT NULL                COMMENT '所属活动ID',
  name         varchar(60)  NOT NULL                COMMENT '维度名称(帅/美/幽默...)',
  icon         varchar(60)  DEFAULT ''              COMMENT '图标(emoji)',
  color        varchar(20)  DEFAULT ''              COMMENT '主题色',
  sort         int(11)      DEFAULT 0               COMMENT '排序',
  status       char(1)      DEFAULT '0'             COMMENT '状态(0正常 1停用)',
  create_by    varchar(64)  DEFAULT ''              COMMENT '创建者',
  create_time  datetime     DEFAULT NULL            COMMENT '创建时间',
  update_by    varchar(64)  DEFAULT ''              COMMENT '更新者',
  update_time  datetime     DEFAULT NULL            COMMENT '更新时间',
  remark       varchar(500) DEFAULT NULL            COMMENT '备注',
  PRIMARY KEY (option_id),
  KEY idx_opt_activity (activity_id)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='投票评选维度表';

-- ----------------------------
-- 4. 投票人表
-- ----------------------------
DROP TABLE IF EXISTS vote_voter;
CREATE TABLE vote_voter (
  voter_id    bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '投票人ID',
  activity_id bigint(20)  NOT NULL                COMMENT '活动ID',
  voter_name  varchar(60) DEFAULT ''              COMMENT '投票人姓名',
  client_id   varchar(64) DEFAULT ''              COMMENT '客户端标识(防重复)',
  ip          varchar(64) DEFAULT ''              COMMENT 'IP',
  votes_used  int(11)     DEFAULT 0               COMMENT '已使用票数',
  create_time datetime    DEFAULT NULL            COMMENT '投票时间',
  PRIMARY KEY (voter_id),
  KEY idx_voter_activity (activity_id),
  KEY idx_voter_client (activity_id, client_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='投票人表';

-- ----------------------------
-- 5. 投票记录表(每一票)
-- ----------------------------
DROP TABLE IF EXISTS vote_record;
CREATE TABLE vote_record (
  record_id    bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  activity_id  bigint(20) NOT NULL                COMMENT '活动ID',
  voter_id     bigint(20) NOT NULL                COMMENT '投票人ID',
  candidate_id bigint(20) NOT NULL                COMMENT '候选人ID',
  option_id    bigint(20) NOT NULL                COMMENT '维度ID',
  create_time  datetime   DEFAULT NULL            COMMENT '投票时间',
  PRIMARY KEY (record_id),
  KEY idx_rec_activity (activity_id),
  KEY idx_rec_candidate (candidate_id),
  KEY idx_rec_option (option_id),
  KEY idx_rec_voter (voter_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='投票记录表';

-- ----------------------------
-- 演示数据：活动
-- ----------------------------
INSERT INTO vote_activity (activity_id, title, description, votes_per_person, multi_per_pair, require_name, status, start_time, end_time, create_by, create_time, remark)
VALUES (100, '以“情”连线，点赞身边人', '每人 8 张点赞票，可投给身边值得点赞的同学，最终汇聚温暖、有爱、闪光的身边人！', 8, '0', '1', '1', '2026-01-01 00:00:00', '2026-12-31 23:59:59', 'admin', NOW(), '系统内置演示活动');

-- ----------------------------
-- 演示数据：评选维度
-- ----------------------------
INSERT INTO vote_option (activity_id, name, icon, color, sort) VALUES
(100, '最帅',   '😎', '#3b82f6', 1),
(100, '最美',   '🌸', '#ec4899', 2),
(100, '幽默',   '😄', '#f59e0b', 3),
(100, '有趣',   '🎈', '#22c55e', 4),
(100, '可爱',   '🐱', '#a855f7', 5),
(100, '气质',   '🤍', '#06b6d4', 6),
(100, '人气',   '🔥', '#ef4444', 7),
(100, '有才',   '🎓', '#6366f1', 8);

-- ----------------------------
-- 演示数据：30 位候选人（王一 ~ 王三十）
-- ----------------------------
INSERT INTO vote_candidate (activity_id, name, no, description, sort, status, create_time) VALUES
(100, '王一',   '01', '阳光开朗，热爱运动',     1,  '0', NOW()),
(100, '王二',   '02', '温柔可人，笑容治愈',     2,  '0', NOW()),
(100, '王三',   '03', '段子手本手，全场最稳',   3,  '0', NOW()),
(100, '王四',   '04', '才华横溢，多才多艺',     4,  '0', NOW()),
(100, '王五',   '05', '人见人爱，团宠担当',     5,  '0', NOW()),
(100, '王六',   '06', '气质出众，安静美好',     6,  '0', NOW()),
(100, '王七',   '07', '幽默风趣，气氛组组长',   7,  '0', NOW()),
(100, '王八',   '08', '低调有实力',             8,  '0', NOW()),
(100, '王九',   '09', '颜值与才华并存',         9,  '0', NOW()),
(100, '王十',   '10', '元气满满的小太阳',       10, '0', NOW()),
(100, '王十一', '11', '冷面笑匠',               11, '0', NOW()),
(100, '王十二', '12', '舞台型选手',             12, '0', NOW()),
(100, '王十三', '13', '邻家男孩',               13, '0', NOW()),
(100, '王十四', '14', '甜美系少女',             14, '0', NOW()),
(100, '王十五', '15', '运动健将',               15, '0', NOW()),
(100, '王十六', '16', '文艺青年',               16, '0', NOW()),
(100, '王十七', '17', '搞笑担当',               17, '0', NOW()),
(100, '王十八', '18', '学霸人设',               18, '0', NOW()),
(100, '王十九', '19', '温暖治愈系',             19, '0', NOW()),
(100, '王二十', '20', '高冷男神',               20, '0', NOW()),
(100, '王二十一', '21', '元气少女',             21, '0', NOW()),
(100, '王二十二', '22', '幽默达人',             22, '0', NOW()),
(100, '王二十三', '23', '艺术细胞爆棚',         23, '0', NOW()),
(100, '王二十四', '24', '可爱担当',             24, '0', NOW()),
(100, '王二十五', '25', '气场两米八',           25, '0', NOW()),
(100, '王二十六', '26', '宝藏男孩',             26, '0', NOW()),
(100, '王二十七', '27', '人气王',               27, '0', NOW()),
(100, '王二十八', '28', '才艺双全',             28, '0', NOW()),
(100, '王二十九', '29', '低调实力派',           29, '0', NOW()),
(100, '王三十',   '30', '压轴登场',             30, '0', NOW());

-- ----------------------------
-- 后台管理菜单
-- ----------------------------
DELETE FROM sys_menu WHERE menu_id BETWEEN 6000 AND 6099;

-- 一级目录
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, remark)
VALUES (6000, '投票管理', 0, 6, 'vote', NULL, '', '', 1, 0, 'M', '0', '0', '', 'star', 'admin', NOW(), '投票系统目录');

-- 二级菜单
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, remark) VALUES
(6001, '投票活动', 6000, 1, 'activity',   'vote/activity/index',   '', '', 1, 0, 'C', '0', '0', 'vote:activity:list',  'date',      'admin', NOW(), ''),
(6002, '候选人管理', 6000, 2, 'candidate', 'vote/candidate/index',  '', '', 1, 0, 'C', '0', '0', 'vote:candidate:list', 'peoples',   'admin', NOW(), ''),
(6003, '评选维度', 6000, 3, 'option',     'vote/option/index',     '', '', 1, 0, 'C', '0', '0', 'vote:option:list',    'list',      'admin', NOW(), ''),
(6004, '投票记录', 6000, 4, 'voterecord', 'vote/record/index',     '', 'VoteRecord', 1, 0, 'C', '0', '0', 'vote:record:list', 'documentation', 'admin', NOW(), ''),
(6005, '统计看板', 6000, 5, 'statistics', 'vote/statistics/index', '', '', 1, 0, 'C', '0', '0', 'vote:stat:list',      'chart',     'admin', NOW(), '');

-- 三级按钮权限
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, remark) VALUES
(6011, '活动查询', 6001, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'vote:activity:query',  '#', 'admin', NOW(), ''),
(6012, '活动新增', 6001, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'vote:activity:add',    '#', 'admin', NOW(), ''),
(6013, '活动修改', 6001, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'vote:activity:edit',   '#', 'admin', NOW(), ''),
(6014, '活动删除', 6001, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'vote:activity:remove', '#', 'admin', NOW(), ''),
(6021, '候选人查询', 6002, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'vote:candidate:query',  '#', 'admin', NOW(), ''),
(6022, '候选人新增', 6002, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'vote:candidate:add',    '#', 'admin', NOW(), ''),
(6023, '候选人修改', 6002, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'vote:candidate:edit',   '#', 'admin', NOW(), ''),
(6024, '候选人删除', 6002, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'vote:candidate:remove', '#', 'admin', NOW(), ''),
(6031, '维度查询', 6003, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'vote:option:query',  '#', 'admin', NOW(), ''),
(6032, '维度新增', 6003, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'vote:option:add',    '#', 'admin', NOW(), ''),
(6033, '维度修改', 6003, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'vote:option:edit',   '#', 'admin', NOW(), ''),
(6034, '维度删除', 6003, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'vote:option:remove', '#', 'admin', NOW(), ''),
(6041, '记录删除', 6004, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'vote:record:remove', '#', 'admin', NOW(), ''),
(6042, '清空记录', 6004, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'vote:record:clear',  '#', 'admin', NOW(), '');

SET FOREIGN_KEY_CHECKS = 1;

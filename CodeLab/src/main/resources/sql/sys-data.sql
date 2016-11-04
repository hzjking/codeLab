
DELIMITER ;;

delete from `sys_user` where id>=1 and id<=1000;;
insert into `sys_user`
(`id`, `username`, `email`, `mobile_phone_number`, `password`, `salt`, `create_date`, `status`, `admin`, `deleted`)
  values
  (1, 'admin', 'admin@sishuok.com', '13412345671', 'ec21fa1738f39d5312c6df46002d403d', 'yDd1956wn1', sysdate(), 'normal', 1, 0);;

insert into `sys_user`
(`id`, `username`, `email`, `mobile_phone_number`, `password`, `salt`, `create_date`, `status`, `admin`, `deleted`)
  values
  (2, 'showcase', 'showcase@sishuok.com', '13412345672', '5f915c55c6d43da136a42e3ebabbecfc', 'hSSixwNQwt', sysdate(), 'normal', 1, 0);;

insert into `sys_user`
(`id`, `username`, `email`, `mobile_phone_number`, `password`, `salt`, `create_date`, `status`, `admin`, `deleted`)
  values
  (3, 'sys', 'sys@sishuok.com', '13412345673', 'a10b3c7af051a81fe2506318f982ce28', 'MANHOoCpnb', sysdate(), 'normal', 1, 0);;

insert into `sys_user`
(`id`, `username`, `email`, `mobile_phone_number`, `password`, `salt`, `create_date`, `status`, `admin`, `deleted`)
  values
  (4, 'maintain', 'maintain@sishuok.com', '13412345674', '594813c5eb02b210dacc1a36c2482fc1', 'iY71e4dtoa', sysdate(), 'normal', 1, 0);;


insert into `sys_user`
(`id`, `username`, `email`, `mobile_phone_number`, `password`, `salt`, `create_date`, `status`, `admin`, `deleted`)
  values
  (5, 'create', 'create@sishuok.com', '13412345675', 'a6d5988a698dec63c6eea71994dd7be0', 'iruPxupgfb', sysdate(), 'normal', 1, 0);;

insert into `sys_user`
(`id`, `username`, `email`, `mobile_phone_number`, `password`, `salt`, `create_date`, `status`, `admin`, `deleted`)
  values
  (6, 'update', 'update@sishuok.com', '13412345676', 'fffa26ac5c47ec1bf9a37d9823816074', '2WQx5LmvlV', sysdate(), 'normal', 1, 0);;

insert into `sys_user`
(`id`, `username`, `email`, `mobile_phone_number`, `password`, `salt`, `create_date`, `status`, `admin`, `deleted`)
  values
  (7, 'delete', 'delete@sishuok.com', '13412345677', '4c472bf1d56f440d2953803ab4eea8d4', 'E8KSvr1C7d', sysdate(), 'normal', 1, 0);;

insert into `sys_user`
(`id`, `username`, `email`, `mobile_phone_number`, `password`, `salt`, `create_date`, `status`, `admin`, `deleted`)
  values
  (8, 'view', 'view@sishuok.com', '13412345678', 'c919215efcef4064858bf02f8776c00d', 'XFJZQOXWZW', sysdate(), 'normal', 1, 0);;

insert into `sys_user`
(`id`, `username`, `email`, `mobile_phone_number`, `password`, `salt`, `create_date`, `status`, `admin`, `deleted`)
  values
  (9, 'audit', 'audit@sishuok.com', '13412345679', '15d8f7b8da8045d24c71a92a142ffad7', 'BI2XbXMUr7', sysdate(), 'normal', 1, 0);;

insert into `sys_user`
(`id`, `username`, `email`, `mobile_phone_number`, `password`, `salt`, `create_date`, `status`, `admin`, `deleted`)
  values
  (10, 'monitor', 'monitor@sishuok.com', '1341234580', 'e1549e68ad21fe888ae36ec4965116cd', 'iY71e4d123', sysdate(), 'normal', 1, 0);;



delete from `sys_permission` where id>=1 and id<=1000;;
insert into `sys_permission` (`id`, `name`, `permission`, `description`, `is_show`) values (1, '所有', '*', '所有数据操作的权限', 1);;
insert into `sys_permission` (`id`, `name`, `permission`, `description`, `is_show`) values (2, '新增', 'create', '新增数据操作的权限', 1);;
insert into `sys_permission` (`id`, `name`, `permission`, `description`, `is_show`) values (3,  '修改', 'update', '修改数据操作的权限', 1);;
insert into `sys_permission` (`id`, `name`, `permission`, `description`, `is_show`) values (4,  '删除', 'delete', '删除数据操作的权限', 1);;
insert into `sys_permission` (`id`, `name`, `permission`, `description`, `is_show`) values (5,  '查看', 'view', '查看数据操作的权限', 1);;
insert into `sys_permission` (`id`, `name`, `permission`, `description`, `is_show`) values (6,  '审核', 'audit', '审核数据操作的权限', 1);;

delete from `sys_role` where id>=1 and id<=1000;;
insert into `sys_role` (`id`, `name`, `role`, `description`, `is_show`) values (1,  '超级管理员', 'admin', '拥有所有权限', 1);;
insert into `sys_role` (`id`, `name`, `role`, `description`, `is_show`) values (2,  '示例管理员', 'example_admin', '拥有示例管理的所有权限', 1);;
insert into `sys_role` (`id`, `name`, `role`, `description`, `is_show`) values (3,  '系统管理员', 'sys_admin', '拥有系统管理的所有权限', 1);;
insert into `sys_role` (`id`, `name`, `role`, `description`, `is_show`) values (4,  '维护管理员', 'conf_admin', '拥有维护管理的所有权限', 1);;
insert into `sys_role` (`id`, `name`, `role`, `description`, `is_show`) values (5,  '新增管理员', 'create_admin', '拥有新增/查看管理的所有权限', 1);;
insert into `sys_role` (`id`, `name`, `role`, `description`, `is_show`) values (6,  '修改管理员', 'update_admin', '拥有修改/查看管理的所有权限', 1);;
insert into `sys_role` (`id`, `name`, `role`, `description`, `is_show`) values (7,  '删除管理员', 'delete_admin', '拥有删除/查看管理的所有权限', 1);;
insert into `sys_role` (`id`, `name`, `role`, `description`, `is_show`) values (8,  '查看管理员', 'view_admin', '拥有查看管理的所有权限', 1);;
insert into `sys_role` (`id`, `name`, `role`, `description`, `is_show`) values (9,  '审核管理员', 'audit_admin', '拥有审核管理的所有权限', 1);;
insert into `sys_role` (`id`, `name`, `role`, `description`, `is_show`) values (10,  '监控管理员', 'audit_admin', '拥有审核管理的所有权限', 1);;

delete from `sys_role_resource_permission` where id>=1 and id<=1000;;
INSERT INTO `sys_role_resource_permission` (`id`, `role_id`, `resource_id`, `permission_ids`) VALUES
	(1, 1, 21, '1'),
	(2, 1, 31, '1'),
	(3, 1, 41, '1'),
	(5, 2, 21, '1'),
	(6, 3, 31, '1'),
	(7, 4, 41, '1'),
	(8, 5, 21, '2,5'),
	(9, 5, 31, '2,5'),
	(10, 5, 41, '2,5'),
	(12, 6, 21, '3,5'),
	(13, 6, 31, '3,5'),
	(14, 6, 41, '3,5'),
	(16, 7, 21, '4,5'),
	(17, 7, 31, '4,5'),
	(18, 7, 41, '4,5'),
	(20, 8, 21, '5'),
	(21, 8, 31, '5'),
	(22, 8, 41, '5'),
	(24, 9, 21, '5,6'),
	(25, 9, 31, '5,6'),
	(26, 9, 41, '5,6'),
	(27, 10, 21, '1');;


delete from `sys_user_role`;;
insert into `sys_user_role` (`id`, `user_id`, `role_id`, `is_show`) values (1,  1, 1, 1);;
insert into `sys_user_role` (`id`, `user_id`, `role_id`, `is_show`) values (2,  2, 2, 1);;
insert into `sys_user_role` (`id`, `user_id`, `role_id`, `is_show`) values (3,  3, 3, 1);;
insert into `sys_user_role` (`id`, `user_id`, `role_id`, `is_show`) values (4,  4, 4, 1);;
insert into `sys_user_role` (`id`, `user_id`, `role_id`, `is_show`) values (5,  5, 5, 1);;
insert into `sys_user_role` (`id`, `user_id`, `role_id`, `is_show`) values (6,  6, 6, 1);;
insert into `sys_user_role` (`id`, `user_id`, `role_id`, `is_show`) values (7,  7, 7, 1);;
insert into `sys_user_role` (`id`, `user_id`, `role_id`, `is_show`) values (8,  8, 8, 1);;
insert into `sys_user_role` (`id`, `user_id`, `role_id`, `is_show`) values (9,  9, 9, 1);;
insert into `sys_user_role` (`id`, `user_id`, `role_id`, `is_show`) values (10, 10, 10, 1);;


INSERT INTO `sys_resource` (`id`, `name`, `type`, `identity`, `url`, `parent_id`, `parent_ids`, `icon`, `available`) VALUES
	(1, '资源', 'menu', ' ', '', 0, '0/', '', '', 1),
	(21, '系统管理', 'menu', 'sys', '', 1, '0/1/', 'fa fa-cog', 1),
	(22, '用户管理', 'button', 'user', '', 21, '0/1/21/', '', 1),
	(23, '角色管理', 'button', 'role', '', 21, '0/1/21/', '', 1),
	(24, '日志管理', 'button', 'log', '', 21, '0/1/21/', '', 1),
	(31, '贷款管理', 'menu', 'loan', '', 1, '0/1/', 'fa fa-money', 1),
	(32, '合同管理', 'button', 'contract', '', 31, '0/1/31/',  '', 1),
	(33, '放款审核', 'button', 'green', '', 31, '0/1/31/', '', 1),
	(34, '额度管理', 'button', 'limit', '', 31, '0/1/31/', '', 1),
	(41, '产品管理', 'menu', 'product', '', 1, '0/1/','fa fa-briefcase', 1),
	(42, '产品作废', 'button', 'cancel', '', 41, '0/1/41/','', 1),
	(43, '产品发布管理', 'button', 'release', '/product/show_product_add', 41, '0/1/41/','', 1);;

#11:15--11:30 创建机构管理表
set names utf8;
CREATE DATABASE oa DEFAULT CHARACTER SET utf8;
use oa;

CREATE TABLE t_org(
 id    int primary key auto_increment,
 name  varchar(25) not null default '' COMMENT '机构名称',
 sn    varchar(50) not null default '' COMMENT '机构编号',
 descr varchar(25) not null default '' COMMENT '机构描述',
 pid   int  not null default 0  COMMENT '父机构编号',
 pname varchar(25) not null default '' COMMENT '父机构名称'
)ENGINE=INNODB;

insert into t_org values(1,'北京公司','1','顶级公司',0,'');
insert into t_org values(2,'上海公司','2','顶级公司',0,'');
insert into t_org values(3,'天津公司','3','顶级公司',0,'');
insert into t_org values(4,'办公室4','1_4','部门公司',1,'');
insert into t_org values(5,'办公室5','1_5','部门公司',1,'');
insert into t_org values(6,'办公室6','1_6','部门公司',1,'');
insert into t_org values(7,'办公室7','1_7','部门公司',1,'');
insert into t_org values(8,'办公室8','1_8','部门公司',1,'');
insert into t_org values(9,'办公室9','1_9','部门公司',1,'');
insert into t_org values(10,'办公室10','1_10','部门公司',1,'');
insert into t_org values(11,'办公室11','1_11','部门公司',1,'');
insert into t_org values(12,'办公室12','1_12','部门公司',1,'');
insert into t_org values(13,'办公室13','1_13','部门公司',1,'');

CREATE TABLE t_person(
 id    int primary key auto_increment,
 name  varchar(25) not null default '' COMMENT '人员名称',
 gender enum('男','女') not null default '男' COMMENT '性别',
 job   varchar(25) not null default '' COMMENT '职务',
 tel   varchar(25) not null default '' COMMENT '电话',
 descr varchar(25) not null default '' COMMENT '描述',
 addr  varchar(25) not null default '' COMMENT '地址',
 age   tinyint not null default 0 COMMENT '年龄',
 oid   int not null default 0 COMMENT '机构id'
)ENGINE=INNODB;

ALTER TABLE t_person ADD FOREIGN KEY(oid)
 REFERENCES t_org(id);
 
ALTER TABLE t_person modify age datetime 
not null default '0000-00-00' COMMENT '出生日期';

#用户表
CREATE TABLE t_user(
 id         int primary key auto_increment,
 name       varchar(25) not null default '' COMMENT '姓名',
 pwd        char(32) not null default '' COMMENT '密码',
 lastlogin  datetime not null default '0000-00-00' COMMENT '上次登录时间',
 isdelete   enum('0','1')  not null default '0'  COMMENT '是否删除',
 pid        int  not null default 0  COMMENT '人员id'
)ENGINE=INNODB;

ALTER TABLE t_user ADD FOREIGN KEY(pid)
 REFERENCES t_person(id);


#创建角色表
CREATE TABLE t_role(
 id         int primary key auto_increment,
 name       varchar(25) not null default '' COMMENT '角色名称'
)ENGINE=INNODB;

#创建用户和角色表关系表
CREATE TABLE t_user_role(
 id          int primary key auto_increment,
 uid         int not null default 0 COMMENT '用户id',
 rid         int not null default 0 COMMENT '角色id'
)ENGINE=INNODB;

ALTER TABLE t_user_role ADD FOREIGN KEY(uid)
 REFERENCES t_user(id);

ALTER TABLE t_user_role ADD FOREIGN KEY(rid)
 REFERENCES t_role(id);

#创建模块表
CREATE TABLE t_module(
 id          int primary key auto_increment,
 name        varchar(25) not null default '' COMMENT '模块名称',
 url         varchar(50) not null default '' COMMENT '访问模块url'
)ENGINE=INNODB;

#创建访问控制表
CREATE TABLE t_acl(
 id          int primary key auto_increment,
 rid		 int not null default 0 COMMENT '角色id',
 mid         int not null default 0 COMMENT '模块id',
 c           enum('1','0') not null default '0' COMMENT '创建',
 r           enum('1','0') not null default '0' COMMENT '读取',
 u           enum('1','0') not null default '0' COMMENT '更新',
 d           enum('1','0') not null default '0' COMMENT '删除'
)ENGINE=INNODB;

ALTER TABLE t_acl ADD FOREIGN KEY(rid)
 REFERENCES t_role(id);
ALTER TABLE t_acl ADD FOREIGN KEY(mid)
 REFERENCES t_module(id);

 
 
 

 

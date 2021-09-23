create table if not exists person
(
    id   int(11)                          not null auto_increment comment '主键id',
    name varchar(8) character set utf8mb4 not null comment '姓名',
    age  int(4)                           not null comment '年龄',
    sex  tinyint(1)                       not null comment '性别',
    primary key (`id`) using btree,
    index `name_idex` (`name`) using btree comment '姓名索引'
) engine = InnoDB
  auto_increment = 1
  character set utf8mb4
  collate utf8mb4_general_ci comment '人物表'
  row_format = dynamic

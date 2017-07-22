--订单表
create table tb_order(
       o_id int primary key,--订单编号
       o_status int ,--订单状态 0 新建  1 已签单  2 已结账  3 预订单 
       did int references tb_desk(did),--桌号
       o_time  date --下单时间
)
insert into tb_order values (3011,1,8001,sysdate)
select * from tb_order for update
select to_char(o_time,'hh24:mi:ss') from tb_order 
update tb_order set did = 8001 where o_id = 3078
drop table tb_order
delete from tb_order where o_id = 3075
select o_status ,t.o_id,mid,mname,unit,od_amount,od_price, od_amount*od_price price  from  tb_order t inner join orderdetail o on t.o_id = o.o_id 
inner join tb_menu m on o.mid = m.mid where t.o_id = 1
--订单座位表
--create table tb_orderdesk(
--      o_id int references tb_order(o_id),
--       did int references tb_desk(did)
--)


--
--订单明细表
create table orderdetail(
      od_id int primary key,--id
      o_id int  references tb_order(o_id),--订单编号
      mid int references tb_menu(mid),--菜品id
      od_amount int ,--数量
      od_price number(5) --单价
)
select * from orderdetail
select nvl(o_id,0) o_id ,t.did,seating,quantity,nvl(o_time,to_date('2000-01-01','yyyy-mm-dd')) o_time ,nvl(b.state,-1) state from 
(select * from tb_order where o_status = 0 and o_status = 1) o 
right join  tb_desk t on t.did=o.did 
left join obligate b on t.did = b.did 
--where o_id is null or o_id = 0
order by did
delete orderdetail where o_id = 3075


--按日查询
select o_id,d.did,sm_money from tb_order t inner join tb_desk d on t.did=d.did inner join settlement s on s.o_id = t.o_id where to_char(s.sm_datetime,'yyyy-mm-dd') = '2016-05-28'
-- 按日详情 订单号 折扣 菜品id  菜品名称 单位  数量  单价 金额 
select o_id,nvl(vip_discount,0),mid,mname,unit,od_amount,od_price,od_amount*od_price amount from tb_order t inner join orderdetail r on t.o_id=r.o_id inner join settlement son s.o_id = t.o_id inner join vip von v.vip_id = s.vip_id inner join tb_menu m on m.mid =r.mid where o_id = 3062
--查询日总金额
select nvl(sum(od_amount*od_price),0) amount from orderdetail o inner join settlement s on o.o_id = s.o_id where to_char(s.sm_datetime,'yyyy-mm-dd') = '2016-05-29'
--按月查询
select extract(year from s.sm_datetime)year,extract(month from s.sm_datetime)as month,nvl(sum(od_amount*od_price),0) amount from orderdetail o 
inner join settlement s on o.o_id = s.o_id 
group by extract(year from s.sm_datetime),extract(month from s.sm_datetime)
having extract(year from s.sm_datetime) = 2016 and extract(month from s.sm_datetime) = 5
order by month
------------------------------
select a.year,a.month,support.day,nvl(a.amount,0)amount from (select extract(year from s.sm_datetime)year,extract(month from s.sm_datetime)as month,extract(day from s.sm_datetime)day,nvl(sum(od_amount*od_price),0) amount from orderdetail o 
inner join settlement s on o.o_id = s.o_id 
group by extract(year from s.sm_datetime),extract(month from s.sm_datetime),extract(day from s.sm_datetime)
having extract(year from s.sm_datetime) = 2016 and extract(month from s.sm_datetime) = 5
order by day) a right join support on a.day = support.day
order by day
--
select extract(year from s.sm_datetime)year,nvl(sum(od_amount*od_price),0) amount from orderdetail o 
inner join settlement s on o.o_id = s.o_id 
group by extract(year from s.sm_datetime) 
having extract(year from s.sm_datetime) = 2016 
order by year
--
--创建一个表辅助查询
create table support(
       year int ,
       month int ,
       day int,
       amount number(10,1)
)
select * from support for update
create table support2(
       year int ,
       month int ,
       amount number(10,1)
)
select * from support2 for update
--按年查询
select a.year,support2.month,nvl(a.amount,0)amount from (select extract(year from s.sm_datetime)year,extract(month from s.sm_datetime)as month,nvl(sum(od_amount*od_price),0) amount 
from orderdetail o 
inner join settlement s on o.o_id = s.o_id 
group by extract(year from s.sm_datetime),extract(month from s.sm_datetime)
having extract(year from s.sm_datetime) = 2016 ) a 
right join support2 on a.month = support2.month 
order by month 



--------

drop table orderdetail
create sequence seq_odid start  with 1 increment by 1;
drop sequence seq_oid
select seq_oid.currval from dual
delete orderdetail
select *　from orderdetail
--结算表
create table  settlement(
       sm_id int primary key,--编号 
       o_id int  references tb_order(o_id),--订单编号
       --did int references tb_desk(did),--座位号
       sm_datetime date,--结算时间
       sm_money number(10),--应收金额
       sm_realmoney   number(10),  --实收金额
       userid int references tb_user(userid),--用户id
       vip_id int references vip(vip_id)   --会员id   
) 
insert into settlement values(seq_smid.nextval,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?,0) --非会员结算

insert into settlement values(seq_smid.nextval,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?,?) -- 用户结算
delete settlement where sm_id = 10
select * from settlement
delete from settlement where sm_id = 21
drop table settlement
create sequence seq_smid start with 1 increment by 1;

seq_oid.


--用户表
create table tb_user(
       userid int primary key,--用户id
       name varchar2(20) not null unique,--用户姓名
       sex varchar2(2)check(sex in('男','女')),--用户性别
       birthday date,--用户生日
       id_card varchar2(20) unique,--用户身份证号码
       password varchar2(20) not null,--用户密码
       type int,--用户类型 0为管理员 1为收银员
       freeze int--冻结用户(可用可不用)1为冻结 0为正常使用
)
create sequence seq_userid start  with 1 increment by 1;
alter table tb_user modify password varchar2(20)
select * from tb_user where name = '李四' and password = '123456'
select * from tb_user for update
drop table tb_user


--会员表
create table vip(
       vip_id int primary key,--会员id
       vip_name varchar2(20), -- 会员姓名
       vip_mailbox  varchar2(20),--会员邮箱
       vip_tel number(20),--电话号码
       vip_discount number(3,3)--折扣
)
select * from vip for update
drop table vip

--菜单表
create table tb_menu(
       mid int primary key,--菜品id
       sid int references tb_sort(sid),--菜系号
       mname varchar2(10),--菜名
       code varchar2(10),--助记码
       unit char(4),--单位
       unit_price number(10),--单价
       status int--0meiyou  1 you 
       
)
select *　from tb_menu for update
insert into tb_menu values(1001,10,'虾兵蟹将','xbxj','盘',128,1);
insert into tb_menu values(1002,3,'红酒','hj','只',128,1);
insert into tb_menu values(1003,4,'茶品','cp','盘',8,1);
insert into tb_menu values(1004,1,'宫保鸡丁','gbjd','碟',128,1);
insert into tb_menu values(1005,5,'糖醋排骨','tcpg','盘',78,1);
insert into tb_menu values(1006,5,'可乐鸡翅','kljc','碟',65,1);
create sequence seq_mid start  with 1010 increment by 1;
alter table tb_menu rename column name to mname
alter table tb_menu modify unit_price number(10,1)
delete tb_menu

--座位表
create table tb_desk(
       did int primary key,--台号
       seating int,--是否已经预定（状态）  0空闲 1 使用中 2 预定中 
       quantity number(10)--每桌的座位数
)
insert into tb_desk values (8001,0,8);
insert into tb_desk values (8002,0,8);
insert into tb_desk values (8003,0,8);
insert into tb_desk values (8004,0,8);
insert into tb_desk values (8005,0,8);
insert into tb_desk values (8006,0,8);
insert into tb_desk values (8007,0,8);
insert into tb_desk values (8008,0,8);
drop table tb_desk
select *　from tb_desk for update
create sequence seq_did start  with 1 increment by 1;
alter table tb_desk modify seating default 0
update tb_desk set seating = 0 where 1=1


--预留座位表
create table obligate(
       oid int primary key,--编号id
       o_id int references tb_order(o_id),--订单表
       ordertime date,--预定时间
       effective date,--保留时间 
       people number(10),--预定人数
       state int,--状态   1 已经预定 
       did int references tb_desk(did),--台号
       vip_id int references vip(vip_id)--会员id
       cname varchar2(20),--顾客姓名
       ctel number(20),--联系方式
       message varchar2(100)--顾客留言
       
)
select * from obligate for update
drop table obligate
create sequence seq_oid start  with 1 increment by 1;


--菜系表
create table tb_sort(
       sid int primary key,--菜系号
       sname varchar2(10)--菜系名
)
alter table  tb_sort modify sname unique
alter table  tb_sort rename column name to sname
create sequence seq_sid start  with 1 increment by 1;
select * from tb_sort for update

commit;

select seq_did.nextval,seating,quantity from  tb_desk

select * from tb_order;
select * from orderdetail;
select * from settlement;
select * from tb_user;
select * from vip;
select * from tb_menu;
select * from tb_desk;
select * from obligate;
select * from tb_sort;


--结算表   订单表   订单明细表   菜单表
select * from tb_desk t left join obligate o on t.did=o.oid;

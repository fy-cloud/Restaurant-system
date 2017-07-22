--������
create table tb_order(
       o_id int primary key,--�������
       o_status int ,--����״̬ 0 �½�  1 ��ǩ��  2 �ѽ���  3 Ԥ���� 
       did int references tb_desk(did),--����
       o_time  date --�µ�ʱ��
)
insert into tb_order values (3011,1,8001,sysdate)
select * from tb_order for update
select to_char(o_time,'hh24:mi:ss') from tb_order 
update tb_order set did = 8001 where o_id = 3078
drop table tb_order
delete from tb_order where o_id = 3075
select o_status ,t.o_id,mid,mname,unit,od_amount,od_price, od_amount*od_price price  from  tb_order t inner join orderdetail o on t.o_id = o.o_id 
inner join tb_menu m on o.mid = m.mid where t.o_id = 1
--������λ��
--create table tb_orderdesk(
--      o_id int references tb_order(o_id),
--       did int references tb_desk(did)
--)


--
--������ϸ��
create table orderdetail(
      od_id int primary key,--id
      o_id int  references tb_order(o_id),--�������
      mid int references tb_menu(mid),--��Ʒid
      od_amount int ,--����
      od_price number(5) --����
)
select * from orderdetail
select nvl(o_id,0) o_id ,t.did,seating,quantity,nvl(o_time,to_date('2000-01-01','yyyy-mm-dd')) o_time ,nvl(b.state,-1) state from 
(select * from tb_order where o_status = 0 and o_status = 1) o 
right join  tb_desk t on t.did=o.did 
left join obligate b on t.did = b.did 
--where o_id is null or o_id = 0
order by did
delete orderdetail where o_id = 3075


--���ղ�ѯ
select o_id,d.did,sm_money from tb_order t inner join tb_desk d on t.did=d.did inner join settlement s on s.o_id = t.o_id where to_char(s.sm_datetime,'yyyy-mm-dd') = '2016-05-28'
-- �������� ������ �ۿ� ��Ʒid  ��Ʒ���� ��λ  ����  ���� ��� 
select o_id,nvl(vip_discount,0),mid,mname,unit,od_amount,od_price,od_amount*od_price amount from tb_order t inner join orderdetail r on t.o_id=r.o_id inner join settlement son s.o_id = t.o_id inner join vip von v.vip_id = s.vip_id inner join tb_menu m on m.mid =r.mid where o_id = 3062
--��ѯ���ܽ��
select nvl(sum(od_amount*od_price),0) amount from orderdetail o inner join settlement s on o.o_id = s.o_id where to_char(s.sm_datetime,'yyyy-mm-dd') = '2016-05-29'
--���²�ѯ
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
--����һ��������ѯ
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
--�����ѯ
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
select *��from orderdetail
--�����
create table  settlement(
       sm_id int primary key,--��� 
       o_id int  references tb_order(o_id),--�������
       --did int references tb_desk(did),--��λ��
       sm_datetime date,--����ʱ��
       sm_money number(10),--Ӧ�ս��
       sm_realmoney   number(10),  --ʵ�ս��
       userid int references tb_user(userid),--�û�id
       vip_id int references vip(vip_id)   --��Աid   
) 
insert into settlement values(seq_smid.nextval,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?,0) --�ǻ�Ա����

insert into settlement values(seq_smid.nextval,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?,?) -- �û�����
delete settlement where sm_id = 10
select * from settlement
delete from settlement where sm_id = 21
drop table settlement
create sequence seq_smid start with 1 increment by 1;

seq_oid.


--�û���
create table tb_user(
       userid int primary key,--�û�id
       name varchar2(20) not null unique,--�û�����
       sex varchar2(2)check(sex in('��','Ů')),--�û��Ա�
       birthday date,--�û�����
       id_card varchar2(20) unique,--�û����֤����
       password varchar2(20) not null,--�û�����
       type int,--�û����� 0Ϊ����Ա 1Ϊ����Ա
       freeze int--�����û�(���ÿɲ���)1Ϊ���� 0Ϊ����ʹ��
)
create sequence seq_userid start  with 1 increment by 1;
alter table tb_user modify password varchar2(20)
select * from tb_user where name = '����' and password = '123456'
select * from tb_user for update
drop table tb_user


--��Ա��
create table vip(
       vip_id int primary key,--��Աid
       vip_name varchar2(20), -- ��Ա����
       vip_mailbox  varchar2(20),--��Ա����
       vip_tel number(20),--�绰����
       vip_discount number(3,3)--�ۿ�
)
select * from vip for update
drop table vip

--�˵���
create table tb_menu(
       mid int primary key,--��Ʒid
       sid int references tb_sort(sid),--��ϵ��
       mname varchar2(10),--����
       code varchar2(10),--������
       unit char(4),--��λ
       unit_price number(10),--����
       status int--0meiyou  1 you 
       
)
select *��from tb_menu for update
insert into tb_menu values(1001,10,'Ϻ��з��','xbxj','��',128,1);
insert into tb_menu values(1002,3,'���','hj','ֻ',128,1);
insert into tb_menu values(1003,4,'��Ʒ','cp','��',8,1);
insert into tb_menu values(1004,1,'��������','gbjd','��',128,1);
insert into tb_menu values(1005,5,'�Ǵ��Ź�','tcpg','��',78,1);
insert into tb_menu values(1006,5,'���ּ���','kljc','��',65,1);
create sequence seq_mid start  with 1010 increment by 1;
alter table tb_menu rename column name to mname
alter table tb_menu modify unit_price number(10,1)
delete tb_menu

--��λ��
create table tb_desk(
       did int primary key,--̨��
       seating int,--�Ƿ��Ѿ�Ԥ����״̬��  0���� 1 ʹ���� 2 Ԥ���� 
       quantity number(10)--ÿ������λ��
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
select *��from tb_desk for update
create sequence seq_did start  with 1 increment by 1;
alter table tb_desk modify seating default 0
update tb_desk set seating = 0 where 1=1


--Ԥ����λ��
create table obligate(
       oid int primary key,--���id
       o_id int references tb_order(o_id),--������
       ordertime date,--Ԥ��ʱ��
       effective date,--����ʱ�� 
       people number(10),--Ԥ������
       state int,--״̬   1 �Ѿ�Ԥ�� 
       did int references tb_desk(did),--̨��
       vip_id int references vip(vip_id)--��Աid
       cname varchar2(20),--�˿�����
       ctel number(20),--��ϵ��ʽ
       message varchar2(100)--�˿�����
       
)
select * from obligate for update
drop table obligate
create sequence seq_oid start  with 1 increment by 1;


--��ϵ��
create table tb_sort(
       sid int primary key,--��ϵ��
       sname varchar2(10)--��ϵ��
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


--�����   ������   ������ϸ��   �˵���
select * from tb_desk t left join obligate o on t.did=o.oid;

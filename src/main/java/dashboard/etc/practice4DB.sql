#[1] 데이터베이스 생성 
drop database if exists practice4DB;
create database practice4DB;
use practice4DB;
#[2] 테이블 생성 
create table department(
    dcode int auto_increment,
    constraint primary key( dcode ),
	dname varchar(10) not null 
);

create table staff(
    scode int auto_increment , 
    constraint primary key( scode ),
    sname varchar(10) not null , 
    srank varchar(30) not null ,
    dcode int,
   constraint foreign key( dcode ) references department( dcode ) on update cascade on delete cascade
);

create table vacation(
    vcode int auto_increment,
    constraint primary key( vcode ),
    vstart varchar(10) ,
    vend varchar(10),
    vreason varchar(10),
    scode int,
     constraint foreign key( scode ) references staff( scode ) on update cascade on delete cascade
     );

#[3] 테이블 샘플 데이터 10개 ( AI 활용 ) 

#[3] 테이블 샘플 데이터 10개 ( AI 활용 ) 
INSERT INTO department(dcode , dname ) VALUES (1, '개발팀');
INSERT INTO department(dcode , dname )VALUES  (2, '디자인팀');
INSERT INTO department(dcode , dname )VALUES (3, '기획팀');
INSERT INTO staff(scode , sname, srank , dcode ) VALUES (1, '김민준', '선임 개발자', 1);
INSERT INTO staff(scode , sname, srank , dcode ) VALUES (2, '이서연', '수석 디자이너', 2);
INSERT INTO staff(scode , sname, srank , dcode ) VALUES (3, '박도윤', '팀장', 3);
INSERT INTO staff(scode , sname, srank , dcode ) VALUES (4, '유재석', '대리', 1);
INSERT INTO vacation(vcode , vstart, vend , vreason , scode  ) VALUES (1, '2025-08-04', '2025-08-04', '병원진료' , 1);
INSERT INTO vacation(vcode , vstart, vend , vreason , scode  ) VALUES (2, '2025-08-21', '2025-08-25', '여름 휴가' , 2);

select * from department;
select * from staff;
select * from vacation;
/*
< INSERT >
INSERT INTO 테이블명 (컬럼명, 컴럼명, .....) 
VALUES (값, 값, 값, ....)

<SELECT - 조회>
SELECT * FROM 테이블명

<SQL>
1)DDL(Data Definitin Language)
create table, drop table, other tables 등

2) DML (Data Manipulation Language)
insert, update, delete 데이터를 다루는 문법

3)DQL(Data Query Language)
select
*/

insert into ADDRS(postno, bas_addr) values('100-100', '서울 강남구');
insert into ADDRS(postno, bas_addr) values('100-101', '서울 서초구');
insert into ADDRS(postno, bas_addr) values('100-102', '서울 마포구');
insert into ADDRS(postno, bas_addr) values('100-103', '서울 영등포구');
insert into ADDRS(postno, bas_addr) values('100-104', '서울 관악구');
insert into ADDRS(postno, bas_addr) values('100-105', '서울 동작구');
insert into ADDRS(postno, bas_addr) values('100-106', '서울 강동구');
insert into ADDRS(postno, bas_addr) values('100-107', '서울 중구');
insert into ADDRS(postno, bas_addr) values('100-108', '서울 은평구');



/*회원정보입력*/
INSERT into MEMBERS(uid, pwd, email, uname, tel) 
values('u01', '1111', 'u01@test.com', '홍길동', '111-111');

INSERT into MEMBERS(uid, pwd, email, uname, tel) 
values('u02', '1111', 'u02@test.com', '임꺽정', '111-111');

INSERT into MEMBERS(uid, pwd, email, uname, tel) 
values('u03', '1111', 'u03@test.com', '유관순', '111-111');

INSERT into MEMBERS(uid, pwd, email, uname, tel) 
values('u04', '1111', 'u04@test.com', '안중근', '111-111');

INSERT into MEMBERS(uid, pwd, email, uname, tel) 
values('u05', '1111', 'u05@test.com', '윤봉길', '111-111');

INSERT into MEMBERS(uid, pwd, email, uname, tel) 
values('u06', '1111', 'u06@test.com', '이순신', '111-111');

INSERT into MEMBERS(uid, pwd, email, uname, tel) 
values('u07', '1111', 'u07@test.com', '김구', '111-111');

INSERT into MEMBERS(uid, pwd, email, uname, tel) 
values('u08', '1111', 'u08@test.com', '김좌진', '111-111');

INSERT into MEMBERS(uid, pwd, email, uname, tel) 
values('u09', '1111', 'u09@test.com', '안창호', '111-111');

INSERT into MEMBERS(uid, pwd, email, uname, tel) 
values('u10', '1111', 'u10@test.com', '이봉창', '111-111');

/*문제: 회원의 아이디와 이름, 이메일을 출력하라*/
SELECT UID, UNAME, EMAIL from members;


/*문제: 위의 결과를 출력할 때 아이디와 이름의 라벨명을 id, name으로 바꾸어서(alias) 출력해라
 'as' 생략가능
 */
SELECT UID as ID, UNAME as NAME, EMAIL from members;


/*문제: 회원정보를 출력할 때 다음과 같이 출력하시오.
 * 회원명(email)
 */
SELECT concat(UNAME, '(', email, ')') as username from members;


/*제조사 입력*/
INSERT into makers(mkname, home, tel) values('애플', 'www.apple.com', '100-1001');
INSERT into makers(mkname, home, tel) values('삼성', 'www.samsung.com', '100-1002');
INSERT into makers(mkname, home, tel) values('LG', 'www.lg.com', '100-1003');
INSERT into makers(mkname, home, tel) values('IBM', 'www.ibm.com', '100-1004');
INSERT into makers(mkname, home, tel) values('Intel', 'www.intel.com', '100-1005');
INSERT into makers(mkname, home, tel) values('Google', 'www.google.com', '100-1006');


/*제품입력*/
INSERT into products(pname, qty, mkno) values('아이폰6', 100, 1);
INSERT into products(pname, qty, mkno) values('아이폰5', 100, 1);
INSERT into products(pname, qty, mkno) values('아이패드', 50, 1);
INSERT into products(pname, qty, mkno) values('갤럭시s5', 2000, 2);
INSERT into products(pname, qty, mkno) values('갤럭시s4', 1000, 2);
INSERT into products(pname, qty, mkno) values('갤럭시노트', 500, 2);
INSERT into products(pname, qty, mkno) values('구글글라스', 10000, 6);
INSERT into products(pname, qty, mkno) values('넥서스7', 100, 6);



/* 사진 정보 입력 */
insert into PROD_PHOTS(PNO, URL)
values(1, 'a01.gif');

insert into PROD_PHOTS(PNO, URL)
values(2, 'a02.gif');

insert into PROD_PHOTS(PNO, URL)
values(4, 'a04.gif');

insert into PROD_PHOTS(PNO, URL)
values(5, 'a05.gif');

insert into PROD_PHOTS(PNO, URL)
values(7, 'a07.gif');


/* 주문 정보 입력*/
insert into ODERS(PNO, UID, QTY, QDATE)
values (1, 'u01', 1, '2014-05-07');

insert into ODERS(PNO, UID, QTY, QDATE)
values (1, 'u02', 3, '2014-06-07');

insert into ODERS(PNO, UID, QTY, QDATE)
values (2, 'u01', 3, '2014-07-07');

insert into ODERS(PNO, UID, QTY, QDATE)
values (2, 'u05', 1, '2014-08-07');

insert into ODERS(PNO, UID, QTY, QDATE)
values (3, 'u07', 6, '2014-09-07');

insert into ODERS(PNO, UID, QTY, QDATE)
values (7, 'u10', 1, '2014-10-07');

insert into ORDERS(PNO, UID, QTY, QDATE)
values (7, 'u13', 5, '2014-11-10');

insert into ORDERS(PNO, UID, QTY, ODATE)
values (7, 'u10', 1, '2014-10-07');


/*UPDATE 명령*/
update ORDERS set 
QTY = 3,
ODATE = '2014-11-09'
WHERE ONO = 6;

/*
 Delete 명령
 문법:
 delete form 테이블명 where 조건1 조건2 ... 
 */

DELETE FROM ORDERS
where ONO = 6;





















/*
 DISTINCT
 중복 데이터 제거
 => DISTINCT 를 붙이지 않으면 기본적으로 모든 데이터가 출력된다.
 */

/* 1) 모든 제품 번호를 출력하라*/
SELECT PNO FROM products;

/*2) 모든 주문 제품의 번호를 출력하라*/
SELECT PNO FROM ORDERS;

/*3) 주문한 제품이 무엇이 있는지 목록을 출력하라
     =>중복제거 (중복되지 않다면 동일)
 */
SELECT DISTINCT PNO FROM ORDERS;
SELECT DISTINCT PNO, UID FROM ORDERS;


/*4) ORDER BY
 출력결과를 정렬
 ORDER BY 컬럼명/*ASC*/, 컬럼명, .....
 => 나열된 컬럼 순서대로 정렬한다.(기본은 오름차순)
   ASC: 오름차순
   DESC: 내림차순
 */

/*1) 기본출력*/
SELECT UID, UNAME, EMAIL FROM MEMBERS;

/*2) 이름을 오름 차순으로 정렬하라*/
SELECT UID, UNAME, EMAIL FROM MEMBERS ORDER BY UNAME;

/*3) 이름을 내림 차순으로 정렬하라*/
SELECT UID, UNAME, EMAIL FROM MEMBERS ORDER BY UNAME DESC;

/*4) 주문정보를 제품번호의 오름차순으로 정렬하라*/
SELECT * FROM ORDERS ORDER BY PNO ASC;

/*5) 주문정보를 제품번호의 오름차순으로 정렬하고, 사용자아이디로 오름차순으로 정렬하라*/
SELECT * FROM ORDERS ORDER BY PNO ASC, UID ASC;

/*6) 주문정보를 제품번호의 오름차순으로 정렬하고, 사용자아이디로 내림차순으로 정렬하라
 
  주의할점: SELECT문장이 먼저 실행되고 ORDER BY에 대해 실행되는 것이 아니라, 
         ORDER BY에 먼저 정렬되고 마지막에 SELECT문장이 실행되는 것이다.
 */
SELECT * FROM ORDERS ORDER BY PNO ASC, UID DESC;

/*7) ORDER BY 주의할점
 */
SELECT ONO, ODATE FROM ORDERS ORDER BY PNO ASC, UID DESC;

/*ALIAS : 새로운 이름(별명) 붙이기 => AS는 생략가능
 문법 SELECT 컬럼명 AS 별명 FROM 테이블명
 별명 중간에 빈 공간에 들어갈 경우 '' 사용해야 함.
 */
SELECT ONO AS NO, ODATE AS 'Order Date', PNO 'Product NO' FROM ORDERS; 

/* WHERE 절
  문법: 
  WHERE 조건1 AND 조건2 ....
  WHERE 조건1 OR 조건2 ....
  
 */

SELECT ONO AS NO, ODATE AS 'Order Date', PNO 'Product NO' FROM ORDERS 
WHERE PNO =1  AND ONO=2; 

/*
 연산자 사용
 **/

/*1) 더하기 연산자*/
SELECT ONO, QTY, QTY * 500000 AS TOTAL  FROM  ORDERS;

/*2) 비교 연산자*/
SELECT ONO, QTY FROM ORDERS WHERE  QTY >2;

SELECT ONO, QTY FROM ORDERS WHERE  QTY > 1 AND QTY <= 5;


/*3) 문자열 비교*/
SELECT UID, UNAME, EMAIL FROM MEMBERS WHERE  UNAME = "홍길동";


/*4) 성이 김씨인 사람만 나열해라
 % : 0개 이상의 글자
 _ : 앞 글자 다음에 1개의 글자다음이 온다음에 뒷글자 올 수 있다.
 **/
SELECT UID, UNAME, EMAIL FROM MEMBERS WHERE UNAME LIKE '김%';

SELECT UID, UNAME, EMAIL FROM MEMBERS WHERE UNAME LIKE '김_진';

/*5) 제품명에 '럭시'라는 글자를 포함한 모든 제품 선택하기
 주의사항: 양쪽모두 검색해야하기 때문에 검색속도가 매우 느리다.
 **/
SELECT PNAME FROM PRODUCTS WHERE PNAME LIKE '%럭시%';

/* 6) 삼성과 애플을 출력하시오
 IN연산자
  표현식 IN(값, 값, 값, ...)
  => 표현식이 IN에 들어있는 값과 일치하면 TRUE 
  
  위 아래의 결과 값은 같다.
 **/

SELECT PNO, PNAME, MKNO FROM PRODUCTS WHERE MKNO = 1 OR MKNO =2; 
SELECT PNO, PNAME, MKNO FROM PRODUCTS WHERE MKNO IN(1, 2); 

/*7) NULL 여부검사하기
 참고: 웹에서 NULL로 입력하였더라도 DB에 넘어갈 때는 STRING객체로 넘어가면서 빈문자열이 들어감
        따라서, IS NOT NULL 로 Check하면 안됨!!!! => CONTENT = ''로 검사해야한다. 
 **/

insert into PROD_PHOTS(URL)
values('xa01.gif');

insert into PROD_PHOTS(URL)
values('xa02.gif');

insert into PROD_PHOTS(URL)
values('xa03.gif');

SELECT * FROM PROD_PHOTS WHERE PNO IS NULL;
SELECT * FROM PROD_PHOTS WHERE PNO IS NOT NULL;

/*8) BETWEEN (이상과 이하)
 아래의 두 문장은 동일한 실행결과를 갖는다.
 **/
SELECT * FROM ORDERS WHERE QTY >= 1 AND QTY <= 3;

SELECT * FROM ORDERS WHERE QTY BETWEEN 1 AND 3 ;

/*9) UNION( 결과의 결합 => 합집합 )
 두 개의 결과를 합쳐서 하나로 다루고 싶을 때
 UNION : 두 결과를 합칠 때 중복 데이터 제거
 UNION ALL: 두 결과를 합칠 때 중복 데이터 제거하지 않음
 **/
/*제품이름과 제조사 이름을 알고 싶을 때는?*/
SELECT PNAME FROM PRODUCTS
UNION
SELECT MKNAME FROM MAKERS;

/*2014년 7월 이후의 주문 정보와 애플제품의 주문 정보를 출력하시오.*/
SELECT * FROM ORDERS WHERE ODATE >= '2014-07-01'
UNION
SELECT * FROM ORDERS WHERE PNO IN(1, 2, 3);

SELECT * FROM ORDERS WHERE ODATE >= '2014-07-01'
UNION ALL
SELECT * FROM ORDERS WHERE PNO IN(1, 2, 3);

/*2014년 7월 이후 주문정보 중에서 애플 제품을 제외한(NOT IN) 모든 주문 정보 출력*/
SELECT * FROM ORDERS WHERE ODATE >= '2014-07-01' AND PNO NOT IN(1, 2, 3);


/*< 서브 쿼리 >
 Usb Query로 부터 가상테이블을 생성하여 조건에 맞게 다시 검색한다.
*/

/*1) 컬럼명에 Sub Query넣기
       주문 제품의 주문번호와 제품명을 출력하라
        실행횟수: 바깥쪽의 실행된 개수만큼 안의 서브 쿼리가 실행된다.
 */
SELECT 
ONO, 
PNO, 
(SELECT PNAME FROM PRODUCTS WHERE PNO = T1.PNO) AS NAME,
QTY 
FROM ORDERS T1;

/*2) WHERE절에 Sub Query넣기
       검색어(애플)와 일치하는 회사 제품의 주문 정보를 출력하시오.*/
SELECT * FROM ORDERS WHERE PNO IN(SELECT PNO FROM PRODUCTS WHERE MKNO=1);

/*3) FROM절에 Sub Query 넣기
     2014-7-1 이후에 주문한 정보 중에서 u01, u05가 주문한 것 */
SELECT *
FROM (SELECT * FROM ORDERS WHERE ODATE >= '2014-07-01' ) AS T1
WHERE UID IN('u01', 'u05');




























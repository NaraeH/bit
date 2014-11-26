/*<CROSS 조인>
 - 두 개의 테이블 데이터를 m * n 으로 조인한다.
 */

SELECT ONO, PNO, QTY FROM ORDERS;
SELECT PNO, PNAME FROM PRODUCTS;

SELECT ONO, T1.PNO, T1.QTY, PNAME FROM ORDERS T1, PRODUCTS T2;

/*<NATURAL 조인>
 => 두개의 테이블의 공통 컬럼을 기준으로 조인한다
 => 외부키를 기준으로 조인을 수행한다.
 */
SELECT ONO, T1.PNO, T1.QTY, PNAME 
FROM ORDERS T1, PRODUCTS T2 
WHERE T1.PNO = T2.PNO;

/*NATURAL 조인 => T1 JOIN T2 USING(컬럼명, 컬럼명, ...)
 단, 조인할 때 기준이 되는 컬럼명이 일치해야 한다.
 **/
SELECT ONO, T1.PNO, T1.QTY, PNAME 
FROM ORDERS T1 JOIN PRODUCTS T2 
USING (PNO);

/*NATURAL 조인 => T1 JOIN T2 ON 조인조건1 ..
  => 조인의 기준이 되는 컬럼명이 다를 때 사용
 */
SELECT ONO, T1.PNO, T1.QTY, PNAME 
FROM ORDERS T1 JOIN PRODUCTS T2 
ON T1.PNO = T2.PNO;


/*OUTER JOIN
 */
/*모든 제품의 정보를 출력하되, 사진정보도 함께 출력하라
 문제점: 만약, PNO가 NULL이라면 그 데이터는 조인되지 않고 사라진다.(조인가능한 결과만 출력)
       => 난 조인할 데이터가 상대 테이블에 없더라도 조인을 해서 값을 얻고 싶은데?
 */
SELECT T1.PNO, T1.PNAME, T2.URL
FROM PRODUCTS T1 JOIN PROD_PHOTS T2 ON T1.PNO = T2.PNO;

/*문제점 해결
=> OUTER JOIN
문법: T1 LEFT OUTER JOIN T2 ON 조인조건...  =>왼쪽 테이블 기준으로 조인
문법: T1 RIGHT OUTER JOIN T2 ON 조인조건...  =>오른쪽 테이블 기준으로 조인
**/
SELECT T1.PNO, T1.PNAME, T2.URL
FROM PRODUCTS T1 LEFT OUTER JOIN PROD_PHOTS T2 ON T1.PNO = T2.PNO;

SELECT T1.PNO, T1.PNAME, T2.URL
FROM PRODUCTS T1 RIGHT OUTER JOIN PROD_PHOTS T2 ON T1.PNO = T2.PNO;

/*문제: 다음결과를 출력하시오
 주문번호, 제품명, 제조사명, 주문수량, 잔여수량, 고객명, 고객이메일
 ORDERS, PRODUCTS, MAKERS, MEMBERS
 주문 정보를 출력하는데 */

SELECT  o.ono as '주문번호',
		p.pname as '제품명', 
		ma.mkname as '제조사명',
		o.qty as '주문수량' , 
		p.qty as '잔여수량',
		m.uname as '고객명',
		m.email as '고객이메일' 
FROM  MEMBERS m JOIN ORDERS o ON m.uid = o.uid 
	JOIN PRODUCTS p ON o.pno = p.pno
	JOIN MAKERS ma ON ma.mkno = p.mkno
















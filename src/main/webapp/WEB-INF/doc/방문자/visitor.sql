- 테이블 구조
-- member 삭제전에 FK가 선언된 blog 테이블 먼저 삭제합니다.
DROP TABLE qna;
DROP TABLE reply;
DROP TABLE member;
-- 제약 조건과 함께 삭제(제약 조건이 있어도 삭제됨, 권장하지 않음.)
DROP TABLE visitor CASCADE CONSTRAINTS; 
DROP TABLE visitor;
DROP SEQUENCE visitor_seq;

CREATE TABLE visitor (
  visitorno NUMBER(10) NOT NULL, -- 회원 번호, 레코드를 구분하는 컬럼 
  id         VARCHAR(30)   NOT NULL UNIQUE, -- 이메일(아이디), 중복 안됨, 레코드를 구분 
  passwd     VARCHAR(60)   NOT NULL, -- 패스워드, 영숫자 조합
  age        NUMBER(10)   NOT NULL, -- 나이
  sex        VARCHAR(60)   NOT NULL, -- 성별
  mname      VARCHAR(30)   NOT NULL, -- 성명, 한글 10자 저장 가능
  tel            VARCHAR(14)   NOT NULL, -- 전화번호
  zipcode     VARCHAR(5)        NULL, -- 우편번호, 12345
  address1    VARCHAR(80)       NULL, -- 주소 1
  address2    VARCHAR(50)       NULL, -- 주소 2
  mdate       DATE             NOT NULL, -- 가입일    
  grade        NUMBER(2)     NOT NULL, -- 등급(1~10: 관리자, 11~20: 회원, 30~39: 비회원, 40~49: 정지 회원, 99: 탈퇴 회원)
  PRIMARY KEY (visitorno)               -- 한번 등록된 값은 중복 안됨
);


COMMENT ON TABLE visitor is '방문자';
COMMENT ON COLUMN visitor.visitorno is '방문자 번호';
COMMENT ON COLUMN visitor.id is '아이디';
COMMENT ON COLUMN visitor.passwd is '비밀 번호';
COMMENT ON COLUMN visitor.age is '나이';
COMMENT ON COLUMN visitor.sex is '성별';
COMMENT ON COLUMN visitor.mname is '방문자 이름';
COMMENT ON COLUMN visitor.tel is '전화 번호';
COMMENT ON COLUMN visitor.zipcode is '우편 번호';
COMMENT ON COLUMN visitor.address1 is '주소1';
COMMENT ON COLUMN visitor.address2 is '주소2';
COMMENT ON COLUMN visitor.mdate is '가입일';
COMMENT ON COLUMN visitor.grade is '등급';

DROP SEQUENCE visitor_seq;
CREATE SEQUENCE visitor_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지

commit;
-- id 중복 확인
SELECT COUNT(id) as cnt
FROM visitor
WHERE id='user1';

-- id 등록
INSERT INTO visitor(visitorno, id, passwd, age, sex, mname, tel, zipcode, address1, address2, mdate, grade)
VALUES (visitor_seq.nextval, 'user1@gmail.com', '1234', 14, '남자', '왕눈이', '000-0000-0000', '12345', '서울시 종로구', '관철동', sysdate, 15);

INSERT INTO visitor(visitorno, id, passwd, age, sex, mname, tel, zipcode, address1, address2, mdate, grade)
VALUES (visitor_seq.nextval, 'manager1', 'manager1', 20, '남자', '게시판관리자', '000-0000-0000', '12345', '서울시 종로구', '관철동', sysdate, 1);

INSERT INTO visitor(visitorno, id, passwd, age, sex, mname, tel, zipcode, address1, address2, mdate, grade)
VALUES (visitor_seq.nextval, 'manager2', 'manager2', 20, '남자', '댓글관리자', '000-0000-0000', '12345', '서울시 종로구', '관철동', sysdate, 2);

SELECT * FROM visitor;

-- 목록
SELECT visitorno, id, passwd, age, sex, mname, tel, zipcode, address1, address2, mdate, grade
FROM visitor
ORDER BY grade ASC, id ASC;

-- 조회
SELECT visitorno, id, passwd, age, sex, mname, tel, zipcode, address1, address2, mdate, grade
FROM visitor
WHERE visitorno = 1;

SELECT visitorno, id, passwd, age, sex, mname, tel, zipcode, address1, address2, mdate, grade
FROM visitor
WHERE id = 'user1@gmail.com';

-- 수정
UPDATE visitor
SET age=15, sex='여자', mname='김두환', tel='010-3030-3033', zipcode='12312',
    address1='강원도', address2='원주시', grade=15
WHERE visitorno = 1;
      
-- 삭제
DELETE FROM visitor
WHERE visitorno=1;

-- 로그인
SELECT COUNT(visitorno) as cnt
FROM visitor
WHERE id='user1@gmail.com' AND passwd='1234';

-- 패스워드 검사
SELECT COUNT(visitorno) as cnt
FROM visitor
WHERE visitorno=1 AND passwd='1234';

-- 패스워드 수정
UPDATE visitor
SET passwd='0000'
WHERE visitorno=4;

COMMIT;

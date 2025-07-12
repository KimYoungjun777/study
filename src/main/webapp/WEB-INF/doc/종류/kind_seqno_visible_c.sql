/**********************************/
/* Table Name: 종류 */
/**********************************/
DROP TABLE KIND;
DROP SEQUENCE KIND_SEQ;

CREATE TABLE KIND(
		kindno                        		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		title                         		VARCHAR2(30)		NOT NULL ,
		cnt                           		NUMBER(10)		 NOT NULL ,
		rdate                         		DATE		 NOT NULL, 
        SEQNO                               NUMBER(5)   DEFAULT 1 NOT NULL,
        VISIBLE                             CHAR(1)         DEFAULT 'N' NOT NULL
);

COMMENT ON TABLE KIND is '종류';
COMMENT ON COLUMN KIND.kindno is '종류 번호';
COMMENT ON COLUMN KIND.title is '종류 제목';
COMMENT ON COLUMN KIND.cnt is '자료 수';
COMMENT ON COLUMN KIND.rdate is '등록일';
COMMENT ON COLUMN KIND.SEQNO is '우선순위';
COMMENT ON COLUMN KIND.VISIBLE is '공개여부';

CREATE SEQUENCE KIND_SEQ
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 999999999
    CACHE 2
    NOCYCLE;

commit;
-- Created
INSERT INTO KIND(kindno, title, cnt, rdate) VALUES (KIND_SEQ.nextval, '한식', 0, sysdate);
INSERT INTO KIND(kindno, title, cnt, rdate) VALUES (KIND_SEQ.nextval, '중식', 0, sysdate);
INSERT INTO KIND(kindno, title, cnt, rdate) VALUES (KIND_SEQ.nextval, '일식', 0, sysdate);
SELECT kindno, title, cnt, rdate, seqno FROM KIND ORDER BY kindno ASC;

-- Read : List
SELECT * FROM KIND;
SELECT kindno, title, cnt, rdate, seqno FROM KIND ORDER BY kindno ASC;
-- Update
UPDATE KIND SET title='분식', cnt=1 WHERE kindno=1;
SELECT kindno, title, cnt, rdate FROM KIND ORDER BY kindno ASC;
-- Delete
DELETE FROM KIND WHERE kindno=4;
SELECT kindno, title, cnt, rdate FROM KIND ORDER BY kindno ASC;

-- 우선 순위 높임, 10등 -> 1등
UPDATE kind SET seqno= seqno - 1 WHERE kindno=1;
SELECT kindno, title, cnt, rdate, seqno FROM kind ORDER BY kindno ASC;

-- 우선 순위 낮춤, 1등 -> 10등
UPDATE kind SET seqno= seqno + 1 WHERE kindno=1;
SELECT kindno, title, cnt, rdate, seqno FROM kind ORDER BY kindno ASC;

-- 카테고리 공개 설정
UPDATE kind SET visible = 'Y' WHERE kindno=1;
SELECT kindno, title, cnt, rdate, seqno, visible FROM kind ORDER BY kindno ASC;

-- 카테고리 비공개 설정
UPDATE kind SET visible = 'N' WHERE kindno=1;
SELECT kindno, title, cnt, rdate, seqno, visible FROM kind ORDER BY kindno ASC;

-- 비회원 회원 SELECT LIST, id: list_all_y
SELECT kindno, title, cnt, rdate, seqno, visible FROM kind WHERE visible='Y' ORDER BY kindno ASC;

DROP SEQUENCE KIND_SEQ;
DROP TABLE KIND;
COMMIT;
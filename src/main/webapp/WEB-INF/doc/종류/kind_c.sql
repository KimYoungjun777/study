/**********************************/
/* Table Name: 종류 */
/**********************************/
CREATE TABLE KIND(
		kindno                        		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		title                         		VARCHAR2(30)		NOT NULL ,
		cnt                           		NUMBER(10)		 NOT NULL ,
		rdate                         		DATE		 NOT NULL 
);

COMMENT ON TABLE KIND is '종류';
COMMENT ON COLUMN KIND.kindno is '종류 번호';
COMMENT ON COLUMN KIND.title is '종류 제목';
COMMENT ON COLUMN KIND.cnt is '자료 수';
COMMENT ON COLUMN KIND.rdate is '등록일';

CREATE SEQUENCE KIND_SEQ
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 999999999
    CACHE 2
    NOCYCLE;

-- Created
INSERT INTO KIND(kindno, title, cnt, rdate) VALUES (KIND_SEQ.nextval, '한식', 0, sysdate);
INSERT INTO KIND(kindno, title, cnt, rdate) VALUES (KIND_SEQ.nextval, '중식', 0, sysdate);
INSERT INTO KIND(kindno, title, cnt, rdate) VALUES (KIND_SEQ.nextval, '일식', 0, sysdate);
SELECT kindno, title, cnt, rdate FROM KIND ORDER BY kindno ASC;

-- Read : List
SELECT * FROM KIND;
SELECT kindno, title, cnt, rdate FROM KIND ORDER BY kindno ASC;
-- Update
UPDATE KIND SET title='분식', cnt=1 WHERE kindno=1;
SELECT kindno, title, cnt, rdate FROM KIND ORDER BY kindno ASC;
-- Delete
DELETE FROM KIND WHERE kindno=1;
SELECT kindno, title, cnt, rdate FROM KIND ORDER BY kindno ASC;

DROP SEQUENCE KIND_SEQ;
DROP TABLE KIND;
COMMIT;
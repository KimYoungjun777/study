DROP TABLE GRAMMER;
DROP SEQUENCE GRAMMER_SEQ

CREATE TABLE GRAMMER(
    GRAMMERNO   NUMBER(5)    NOT NULL,
    ID         VARCHAR(20)   NOT NULL UNIQUE, -- 아이디, 중복 안됨, 레코드를 구분 
    PASSWD     VARCHAR(15)   NOT NULL, -- 패스워드, 영숫자 조합
    GNAME      VARCHAR(20)   NOT NULL, -- 성명, 한글 10자 저장 가능
    SDATE      DATE          NOT NULL, -- 가입일    
    GRADE      NUMBER(2)     NOT NULL, -- 등급(1~10: 관리자, 정지 관리자:20, 탈퇴 관리자: 99)    
    PRIMARY KEY (GRAMMERNO)
    );
    
COMMENT ON TABLE GRAMMER is '관리자';
COMMENT ON COLUMN GRAMMER.GRAMMERNO is '관리자 번호';
COMMENT ON COLUMN GRAMMER.ID is '아이디';
COMMENT ON COLUMN GRAMMER.PASSWD is '패스워드';
COMMENT ON COLUMN GRAMMER.GNAME is '성명';
COMMENT ON COLUMN GRAMMER.SDATE is '가입일';
COMMENT ON COLUMN GRAMMER.GRADE is '등급';

CREATE SEQUENCE GRAMMER_SEQ
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 99999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;       

commit;

INSERT INTO grammer(grammerno, id, passwd, gname, sdate, grade)
VALUES(grammer_seq.nextval, 'dudwns724', 'tmxkrhtn1', '김영준', sysdate, 1);

INSERT INTO grammer(grammerno, id, passwd, gname, sdate, grade)
VALUES(grammer_seq.nextval, 'shh0076', '20221113', '태니', sysdate, 1);  

INSERT INTO grammer(grammerno, id, passwd, gname, sdate, grade)
VALUES(grammer_seq.nextval, 'grammer3', '1234', '개발자3', sysdate, 1);

COMMIT;

SELECT grammerno, id, passwd, gname, sdate, grade FROM grammer ORDER BY grammerno ASC;

SELECT grammerno, id, passwd, gname, sdate, grade FROM grammer WHERE grammerno=1;

SELECT grammerno, id, passwd, gname, sdate, grade FROM grammer WHERE id='grammer1';

UPDATE grammer
SET id= 'dudwns724', passwd='tmxkrhtn1', gname='김영준', sdate=sysdate, grade=1
WHERE grammerno=1;

COMMIT;

SELECT COUNT(*) as cnt
FROM grammer
WHERE id='grammer1' AND passwd='1234';

DROP TABLE GRAMMER;
DROP SEQUENCE GRAMMER_SEQ;
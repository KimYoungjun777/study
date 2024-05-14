DROP TABLE chatting;

CREATE TABLE chatting (
  chattingno NUMBER(10) NOT NULL, 
  visitorno        NUMBER(10)   NOT NULL,
  vname         VARCHAR2(50) NULL,
  reciverno        NUMBER(10)   NOT NULL,  
  rname         VARCHAR2(50) NULL,
  msg     VARCHAR(300)   NOT NULL, 
  rdate        DATE   NOT NULL, 
  PRIMARY KEY (chattingno),
  FOREIGN KEY (visitorno) REFERENCES visitor (visitorno)
);


COMMENT ON TABLE chatting is '채팅';
COMMENT ON COLUMN chatting.chattingno is '채팅 번호';
COMMENT ON COLUMN chatting.visitorno is '회원 번호';
COMMENT ON COLUMN chatting.vname is '회원 번호';
COMMENT ON COLUMN chatting.reciverno is '받는 사람';
COMMENT ON COLUMN chatting.rname is '회원 번호';
COMMENT ON COLUMN chatting.msg is '내용';
COMMENT ON COLUMN chatting.rdate is '날짜';


DROP SEQUENCE chatting_seq;
CREATE SEQUENCE chatting_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지

commit;

INSERT INTO chatting(chattingno, visitorno, reciverno, msg, rdate) VALUES (chatting_seq.nextval, 2, 3, '사이다 ㄱ?', sysdate);


SELECT * FROM chatting;

-- 회원 별 보낸 채팅 목록
SELECT rname, msg, rdate
FROM chatting
WHERE (reciverno, rdate) IN (
    SELECT reciverno, MAX(rdate) AS rdate
    FROM chatting
    WHERE visitorno = 2
    GROUP BY reciverno
);

-- 회원 별 받은 채팅 목록
SELECT vname, msg, rdate
FROM chatting
WHERE (visitorno, rdate) IN (
    SELECT visitorno, MAX(rdate) AS rdate
    FROM chatting
    WHERE reciverno = 2
    GROUP BY visitorno
);
-- 보낸 채팅 목록(보낸 사람 기준으로 다 보이게)
SELECT rname, msg, rdate
FROM chatting
WHERE visitorno = 2 and reciverno = 1
ORDER BY rdate DESC;

-- 받은 채팅 목록(보낸 사람 기준으로 다 보이게)
SELECT vname, msg, rdate
FROM chatting
WHERE visitorno = 1 and reciverno = 2
ORDER BY rdate DESC;



-- 삭제요
DELETE FROM chatting;
WHERE reciverno = 2;











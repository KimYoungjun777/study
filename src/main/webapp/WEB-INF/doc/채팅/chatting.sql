DROP TABLE chatting;

CREATE TABLE chatting (
  chattingno NUMBER(10) NOT NULL, 
  visitorno        NUMBER(10)   NOT NULL,  
  reciverno        NUMBER(10)   NOT NULL,  
  msg     VARCHAR(300)   NOT NULL, 
  rdate        DATE   NOT NULL, 
  PRIMARY KEY (chattingno),
  FOREIGN KEY (visitorno) REFERENCES visitor (visitorno)
);


COMMENT ON TABLE chatting is '채팅';
COMMENT ON COLUMN chatting.chattingno is '채팅 번호';
COMMENT ON COLUMN chatting.visitorno is '회원 번호';
COMMENT ON COLUMN chatting.reciverno is '받는 사람';
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

INSERT INTO chatting(chattingno, visitorno, reciverno, msg, rdate) VALUES (chatting_seq.nextval, 1, 2, '콜라줘', sysdate);


SELECT * FROM chatting;

-- 회원 별 채팅 목록
SELECT reciverno, msg, rdate
FROM chatting
WHERE (reciverno, rdate) IN (
    SELECT reciverno, MAX(rdate) AS rdate
    FROM chatting
    WHERE visitorno = 2
    GROUP BY reciverno
);

-- 채팅 상세
SELECT *
FROM chatting
WHERE visitorno = 1 and reciverno = 2
ORDER BY rdate ASC;



DELETE FROM chatting
WHERE reciverno = 2;











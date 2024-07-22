DROP TABLE bookmark;
DROP SEQUENCE bookmark_seq;

CREATE TABLE bookmark(
		bookmarkno                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		substancesno                       		NUMBER(10)		 NOT NULL ,
		visitorno                         		NUMBER(10)		 NOT NULL ,
		rdate                         	DATE		 NOT NULL,
  FOREIGN KEY (substancesno) REFERENCES substances (substancesno),
  FOREIGN KEY (visitorno) REFERENCES visitor (visitorno)
);

COMMENT ON TABLE bookmark is '북마크';
COMMENT ON COLUMN bookmark.bookmarkno is '북마크 번호';
COMMENT ON COLUMN bookmark.substancesno is '본문 번호';
COMMENT ON COLUMN bookmark.visitorno is '방문자 번호';
COMMENT ON COLUMN bookmark.rdate is '북마크 날짜';

CREATE SEQUENCE bookmark_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 999999999
    CACHE 2
    NOCYCLE;
    
COMMIT;    

-- 등록
INSERT INTO bookmark(bookmarkno, substancesno, visitorno, rdate) VALUES (bookmark_seq.nextval, 3, 3, sysdate);

-- Read : List
SELECT * FROM bookmark;
SELECT bookmarkno, substancesno, visitorno, rdate FROM bookmark ORDER BY bookmarkno ASC;

-- 본문 글의 총 조회수
SELECT COUNT(*) as cnt FROM bookmark
WHERE substancesno=1;

-- 1번 회원이 2번 상품을 좋아요 클릭했는지 카운트, 1: 좋아요했음, 0은 자료 없음
SELECT COUNT(*) as cnt 
FROM bookmark
WHERE substancesno=3 AND visitorno=10;

-- 2번 회원의 북마크 한 게시글
SELECT substancesno
FROM bookmark
WHERE visitorno = 2;

-- Delete
DELETE FROM bookmark WHERE substancesno=2 and visitorno=3;
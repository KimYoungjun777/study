DROP TABLE substances;
DROP SEQUENCE substances_seq;

CREATE TABLE substances(
        substancesno                            NUMBER(10)         NOT NULL         PRIMARY KEY,
        grammerno                              NUMBER(10)     NOT NULL , -- FK
        kindno                                NUMBER(10)         NOT NULL , -- FK
        title                                 VARCHAR2(200)         NOT NULL,
        substance                               CLOB                  NOT NULL,
        recom                                 NUMBER(7)         DEFAULT 0         NOT NULL,
        cnt                                   NUMBER(7)         DEFAULT 0         NOT NULL,
        replycnt                              NUMBER(7)         DEFAULT 0         NOT NULL,
        passwd                                VARCHAR2(15)         NOT NULL,
        word                                  VARCHAR2(100)         NULL ,
        name                                  VARCHAR2(50)          NULL,
        review                                NUMBER(7)         DEFAULT 0         NOT NULL,
        rdate                                 DATE               NOT NULL,
        file1                                   VARCHAR(100)          NULL,  -- 원본 파일명 image
        file1saved                            VARCHAR(100)          NULL,  -- 저장된 파일명, image
        thumb1                              VARCHAR(100)          NULL,   -- preview image
        size1                                 NUMBER(10)      DEFAULT 0 NULL,  -- 파일 사이즈
        price                                 NUMBER(10)      DEFAULT 0 NULL,  
        dc                                    NUMBER(10)      DEFAULT 0 NULL,  
        saleprice                            NUMBER(10)      DEFAULT 0 NULL,  
        point                                 NUMBER(10)      DEFAULT 0 NULL,  
        salecnt                               NUMBER(10)      DEFAULT 0 NULL,
        map                                   VARCHAR2(1000)            NULL,
        youtube                               VARCHAR2(1000)            NULL,
        FOREIGN KEY (grammerno) REFERENCES grammer (grammerno),
        FOREIGN KEY (kindno) REFERENCES kind (kindno)
);

COMMENT ON TABLE substances is '본문';
COMMENT ON COLUMN substances.substancesno is '본문 번호';
COMMENT ON COLUMN substances.grammerno is '개발자 번호';
COMMENT ON COLUMN substances.kindno is '종류 번호';
COMMENT ON COLUMN substances.title is '제목';
COMMENT ON COLUMN substances.substance is '내용';
COMMENT ON COLUMN substances.recom is '추천수';
COMMENT ON COLUMN substances.cnt is '조회수';
COMMENT ON COLUMN substances.replycnt is '댓글수';
COMMENT ON COLUMN substances.passwd is '패스워드';
COMMENT ON COLUMN substances.word is '검색어';
COMMENT ON COLUMN substances.review is '검색어';
COMMENT ON COLUMN substances.rdate is '등록일';
COMMENT ON COLUMN substances.file1 is '메인 이미지';
COMMENT ON COLUMN substances.file1saved is '실제 저장된 메인 이미지';
COMMENT ON COLUMN substances.thumb1 is '메인 이미지 Preview';
COMMENT ON COLUMN substances.size1 is '메인 이미지 크기';
COMMENT ON COLUMN substances.price is '정가';
COMMENT ON COLUMN substances.dc is '할인률';
COMMENT ON COLUMN substances.saleprice is '판매가';
COMMENT ON COLUMN substances.point is '포인트';
COMMENT ON COLUMN substances.salecnt is '수량';
COMMENT ON COLUMN substances.map is '지도';
COMMENT ON COLUMN substances.youtube is 'Youtube 영상';

CREATE SEQUENCE SUBSTANCES_SEQ
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE; 
  
commit;
-- 등록 화면 유형 1: 커뮤니티(공지사항, 게시판, 자료실, 갤러리,  Q/A...)글 등록
INSERT INTO substances(substancesno, grammerno, kindno, title, substance, recom, cnt, replycnt, passwd, 
                     word, review, rdate, file1, file1saved, thumb1, size1)
VALUES(substances_seq.nextval, 1, 1, '엽기떡볶이', '매운 맛이 일품', 0, 0, 0, '123',
       '산책', 5, sysdate, 'space.jpg', 'space_1.jpg', 'space_t.jpg', 1000);

SELECT substancesno, grammerno, kindno, title, substance, recom, cnt, replycnt, passwd, word, review, rdate, file1, file1saved, thumb1, size1
FROM substances
ORDER BY substancesno ASC;

INSERT INTO substances(substancesno, grammerno, kindno, title, substance, recom, cnt, replycnt, passwd, 
                     word, review, rdate, file1, file1saved, thumb1, size1)
VALUES(substances_seq.nextval, 1, 1, '교촌치킨', '한입에 쏘옥', 0, 0, 0, '123',
       '배달', 5, sysdate, 'space.jpg', 'space_1.jpg', 'space_t.jpg', 1000);

INSERT INTO substances(substancesno, grammerno, kindno, title, substance, recom, cnt, replycnt, passwd, 
                     word, review, rdate, file1, file1saved, thumb1, size1)
VALUES(substances_seq.nextval, 1, 1, '홍콩반점', '짜장면이 일품', 0, 0, 0, '123',
       '음식', 5, sysdate, 'space.jpg', 'space_1.jpg', 'space_t.jpg', 1000);

COMMIT;

-- 1번 kindno만 출력
SELECT substancesno, grammerno, kindno, title, substance, recom, cnt, replycnt, passwd, word, review, rdate, file1, file1saved, thumb1, size1
FROM substances
WHERE kindno=1
ORDER BY substancesno ASC;

-- 2번 kindno만 출력
SELECT substancesno, grammerno, kindno, title, substance, recom, cnt, replycnt, passwd, word, review, rdate, file1, file1saved, thumb1, size1
FROM substances
WHERE kindno=2
ORDER BY substancesno ASC;

-- 3번 kindno만 출력
SELECT substancesno, grammerno, kindno, title, substance, recom, cnt, replycnt, passwd, word, review, rdate, file1, file1saved, thumb1, size1
FROM substances
WHERE kindno=1
ORDER BY substancesno ASC;

-- 모든 레코드 삭제
DELETE FROM substances;
COMMIT;

-- 삭제
DELETE FROM substances
WHERE substancesno = 10;

COMMIT;

-- 카테고리별 목록
SELECT substancesno, grammerno, kindno, title, substance, recom, cnt, replycnt, passwd, word, review, rdate, file1, file1saved, thumb1, size1
FROM substances
WHERE kindno=2
ORDER BY substancesno ASC;

-- 검색
SELECT substancesno, grammerno, kindno, title, substance, recom, cnt, replycnt, passwd, word, review, rdate, file1, file1saved, thumb1, size1
FROM substances
WHERE kindno=1 AND (title LIKE '%떡볶이%' OR substance LIKE '%떡볶이%' OR word LIKE '%떡볶이%')
ORDER BY substancesno ASC;

-- 레코드 갯수
SELECT COUNT(*) as cnt
FROM substances
WHERE kindno=1 AND (title LIKE '%떡볶이%' OR substance LIKE '%떡볶이%' OR word LIKE '%떡볶이%');

-- 부분 문자열
SELECT substancesno, SUBSTR (title, 1, 4) as title
FROM substances
WHERE kindno=1 AND (title LIKE '%떡%');

-- MAP등록/수정
UPDATE substances SET map ='엽기 떡볶이' WHERE substancesno=1;

COMMIT;

-- MAP등록/수정
UPDATE substances SET youtube ='youtube 스크립트' WHERE substancesno=1;

-- 검색
SELECT substancesno, grammerno, kindno, title, substance, recom, cnt, replycnt, passwd, word, review, rdate, file1, file1saved, thumb1, size1
FROM substances
WHERE kindno=2 AND (word LIKE '%찌개%' OR title LIKE '%찌개%' OR substance LIKE '%찌개%')
ORDER BY substancesno ASC;

-- 검색 레코드 갯수
SELECT COUNT(*)
FROM substances
WHERE kindno=2;

SELECT COUNT(*) as cnt
FROM substances
WHERE kindno=2 AND (word LIKE '%찌개%' OR title LIKE '%찌개%' OR substance LIKE '%찌개%');

-- SUBSTR(컬럼명, 시작 index(1부터 시작), 길이), 부분 문자열 추출
SELECT substancesno, SUBSTR(title, 1, 4) as title
FROM substances
WHERE kindno=1 AND (title LIKE '%찌개%');

-- 검색
SELECT substancesno, title, word
FROM substances
WHERE kindno=1 AND (UPPER(word) LIKE '%' || UPPER('FOOD') || '%');

-- ----------------------------------------------------------------------------------------------------
-- 검색 + 페이징 + 메인 이미지
-- ----------------------------------------------------------------------------------------------------
-- step 1
SELECT substancesno, grammerno, kindno, title, substance, recom, cnt, replycnt, review, rdate,
           file1, file1saved, thumb1, size1, map, youtube
FROM substances
WHERE kindno=1 AND (title LIKE '%오지%' OR substance LIKE '%오지%' OR word LIKE '%오지%')
ORDER BY substancesno DESC;

-- step 2
SELECT substancesno, grammerno, kindno, title, substance, recom, cnt, replycnt, review, rdate,
           file1, file1saved, thumb1, size1, map, youtube, rownum as r
FROM (
          SELECT substancesno, grammerno, kindno, title, substance, recom, cnt, replycnt, review, rdate,
                     file1, file1saved, thumb1, size1, map, youtube
          FROM substances
          WHERE kindno=1 AND (title LIKE '%오지%' OR substance LIKE '%오지%' OR word LIKE '%오지%')
          ORDER BY substancesno DESC
);

-- step 3, 1 page
SELECT substancesno, grammerno, kindno, title, substance, recom, cnt, replycnt, review, rdate,
           file1, file1saved, thumb1, size1, map, youtube, r
FROM (
           SELECT substancesno, grammerno, kindno, title, substance, recom, cnt, replycnt, review, rdate,
                      file1, file1saved, thumb1, size1, map, youtube, rownum as r
           FROM (
                     SELECT substancesno, grammerno, kindno, title, substance, recom, cnt, replycnt, review, rdate,
                                file1, file1saved, thumb1, size1, map, youtube
                     FROM substances
                     WHERE kindno=1 AND (UPPER(title) LIKE '%' || UPPER('오지') || '%' 
                                         OR UPPER(substance) LIKE '%' || UPPER('오지') || '%' 
                                         OR UPPER(word) LIKE '%' || UPPER('오지') || '%')
                     ORDER BY substancesno DESC
           )          
)
WHERE r >= 1 AND r <= 3;


-- 대소문자를 처리하는 페이징 쿼리
SELECT substancesno, grammerno, kindno, title, substance, recom, cnt, replycnt, review, rdate,
           file1, file1saved, thumb1, size1, map, youtube, r
FROM (
           SELECT substancesno, grammerno, kindno, title, substance, recom, cnt, replycnt, review, rdate,
                      file1, file1saved, thumb1, size1, map, youtube, rownum as r
           FROM (
                     SELECT substancesno, grammerno, kindno, title, substance, recom, cnt, replycnt, review, rdate,
                                file1, file1saved, thumb1, size1, map, youtube
                     FROM substances
                     WHERE kindno=1 AND (UPPER(title) LIKE '%' || UPPER('오지') || '%' 
                                         OR UPPER(substance) LIKE '%' || UPPER('오지') || '%' 
                                         OR UPPER(word) LIKE '%' || UPPER('오지') || '%')
                     ORDER BY substancesno DESC
           )          
)
WHERE r >= 1 AND r <= 3;

-- 패스워드 검사, id="password_check"
SELECT COUNT(*) as cnt 
FROM substances
WHERE substancesno=1 AND passwd='123';

-- 텍스트 수정: 예외 컬럼: 추천수, 조회수, 댓글 수
UPDATE substances
SET title='기차를 타고', substance='계획없이 여행 출발',  word='나,기차,생각' 
WHERE substancesno = 35;

commit;

-- 파일 수정
UPDATE substances
SET file1='train.jpg', file1saved='train.jpg', thumb1='train_t.jpg', size1=5000
WHERE substancesno = 35;

-- substances 삭제
DELETE FROM substances
WHERE substancesno = 42;

commit;

-- 특정 그룹에 속한 레코드 갯수 산출
SELECT COUNT(*) as cnt 
FROM substances 
WHERE kindno=1;

-- 특정 그룹에 속한 게시글 삭제
DELETE FROM kind WHERE kindno=1;



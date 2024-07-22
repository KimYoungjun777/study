package dev.mvc.substances;

import org.springframework.web.multipart.MultipartFile;

public class SubstancesVO {
	
//    substancesno                            NUMBER(10)         NOT NULL         PRIMARY KEY,
//    grammerno                              NUMBER(10)     NOT NULL , -- FK
//    kindno                                NUMBER(10)         NOT NULL , -- FK
//    title                                 VARCHAR2(200)         NOT NULL,
//    substance                               CLOB                  NOT NULL,
//    recom                                 NUMBER(7)         DEFAULT 0         NOT NULL,
//    cnt                                   NUMBER(7)         DEFAULT 0         NOT NULL,
//    replycnt                              NUMBER(7)         DEFAULT 0         NOT NULL,
//    passwd                                VARCHAR2(15)         NOT NULL,
//    word                                  VARCHAR2(100)         NULL ,
//    rdate                                 DATE               NOT NULL,
//    file1                                   VARCHAR(100)          NULL,  -- 원본 파일명 image
//    file1saved                            VARCHAR(100)          NULL,  -- 저장된 파일명, image
//    thumb1                              VARCHAR(100)          NULL,   -- preview image
//    size1                                 NUMBER(10)      DEFAULT 0 NULL,  -- 파일 사이즈
//    price                                 NUMBER(10)      DEFAULT 0 NULL,  
//    dc                                    NUMBER(10)      DEFAULT 0 NULL,  
//    saleprice                            NUMBER(10)      DEFAULT 0 NULL,  
//    point                                 NUMBER(10)      DEFAULT 0 NULL,  
//    salecnt                               NUMBER(10)      DEFAULT 0 NULL,
//    map                                   VARCHAR2(1000)            NULL,
//    youtube                               VARCHAR2(1000)            NULL,
//    FOREIGN KEY (grammerno) REFERENCES grammer (grammerno),
//    FOREIGN KEY (kindno) REFERENCES kind (kindno)
	
	 /** 본문 번호 */
    private int substancesno;
    /** 방문자 번호 */
    private int visitorno;
    /** 종류 번호 */
    private int kindno;
    /** 제목 */
    private String title = "";
    /** 내용 */
    private String substance = "";
    /** 추천수 */
    private int recom;
    /** 조회수 */
    private int cnt = 0;
    /** 댓글수 */
    private int replycnt = 0;
    /** 패스워드 */
    private String passwd = "";
    /** 검색어 */
    private String word = "";
    /** 주소 */
    private String address = "";
    /** 작성자 */
    private String name = "";
    /** 등록 날짜 */
    private String rdate = "";
    /** 지도 */
    private String map;
    /** Youtube */
    private String youtube;
    /** 상태 */
    private String state;

    // 파일 업로드 관련
    // -----------------------------------------------------------------------------------
    /**
    이미지 파일
    <input type='file' class="form-control" name='file1MF' id='file1MF' 
               value='' placeholder="파일 선택">
    */
    private MultipartFile file1MF;
    /** 메인 이미지 크기 단위, 파일 크기 */
    private String size1_label = "";
    /** 메인 이미지 */
    private String file1 = "";
    /** 실제 저장된 메인 이미지 */
    private String file1saved = "";
    /** 메인 이미지 preview */
    private String thumb1 = "";
    /** 메인 이미지 크기 */
    private long size1;

    // 쇼핑몰 상품 관련
    // -----------------------------------------------------------------------------------
    /** 정가 */
    private int price;
    /** 할인률 */
    private int dc;
    /** 판매가 */
    private int saleprice;
    /** 포인트 */
    private int point;
    /** 재고 수량 */
    private int salecnt;
    
    // 페이징 관련
    // -----------------------------------------------------------------------------------
    /** 시작 rownum */
    private int start_num;    
    /** 종료 rownum */
    private int end_num;    
    /** 현재 페이지 */
    private int now_page = 1;
	public int getSubstancesno() {
		return substancesno;
	}
	public void setSubstancesno(int substancesno) {
		this.substancesno = substancesno;
	}

	public int getKindno() {
		return kindno;
	}
	public void setKindno(int kindno) {
		this.kindno = kindno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubstance() {
		return substance;
	}
	public void setSubstance(String substance) {
		this.substance = substance;
	}
	public int getRecom() {
		return recom;
	}
	public void setRecom(int recom) {
		this.recom = recom;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getReplycnt() {
		return replycnt;
	}
	public void setReplycnt(int replycnt) {
		this.replycnt = replycnt;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	
  public int getVisitorno() {
    return visitorno;
  }
  public void setVisitorno(int visitorno) {
    this.visitorno = visitorno;
  }
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	public String getState() {
    return state;
  }
  public void setState(String state) {
    this.state = state;
  }
  public String getMap() {
		return map;
	}
	public void setMap(String map) {
		this.map = map;
	}
	public String getYoutube() {
		return youtube;
	}
	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}
	public MultipartFile getFile1MF() {
		return file1MF;
	}
	public void setFile1MF(MultipartFile file1mf) {
		file1MF = file1mf;
	}
	public String getSize1_label() {
		return size1_label;
	}
	public void setSize1_label(String size1_label) {
		this.size1_label = size1_label;
	}
	public String getFile1() {
		return file1;
	}
	public void setFile1(String file1) {
		this.file1 = file1;
	}
	public String getFile1saved() {
		return file1saved;
	}
	public void setFile1saved(String file1saved) {
		this.file1saved = file1saved;
	}
	public String getThumb1() {
		return thumb1;
	}
	public void setThumb1(String thumb1) {
		this.thumb1 = thumb1;
	}
	public long getSize1() {
		return size1;
	}
	public void setSize1(long size1) {
		this.size1 = size1;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDc() {
		return dc;
	}
	public void setDc(int dc) {
		this.dc = dc;
	}
	public int getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(int saleprice) {
		this.saleprice = saleprice;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getSalecnt() {
		return salecnt;
	}
	public void setSalecnt(int salecnt) {
		this.salecnt = salecnt;
	}
	public int getStart_num() {
		return start_num;
	}
	public void setStart_num(int start_num) {
		this.start_num = start_num;
	}
	public int getEnd_num() {
		return end_num;
	}
	public void setEnd_num(int end_num) {
		this.end_num = end_num;
	}
	public int getNow_page() {
		return now_page;
	}
	public void setNow_page(int now_page) {
		this.now_page = now_page;
	}
	
	@Override
	public String toString() {
		return "SubstancesVO [substancesno=" + substancesno + ", visitorno=" + visitorno + ", kindno=" + kindno
				+ ", title=" + title + ", substance=" + substance + ", recom=" + recom + ", cnt=" + cnt + ", replycnt="
				+ replycnt + ", passwd=" + passwd + ", word=" + word + ", rdate=" + rdate + ", map=" + map
				+ ", youtube=" + youtube + ", file1MF=" + file1MF + ", size1_label=" + size1_label + ", file1=" + file1
				+ ", file1saved=" + file1saved + ", thumb1=" + thumb1 + ", size1=" + size1 + ", price=" + price
				+ ", dc=" + dc + ", saleprice=" + saleprice + ", point=" + point + ", salecnt=" + salecnt
				+ ", start_num=" + start_num + ", end_num=" + end_num + ", now_page=" + now_page + "]";
	}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

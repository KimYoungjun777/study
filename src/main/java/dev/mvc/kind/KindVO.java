package dev.mvc.kind;

public class KindVO {
//	CREATE TABLE KIND(
//			kindno                        		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
//			title                         		VARCHAR2(30)		 NULL ,
//			cnt                           		NUMBER(10)		 NULL ,
//			rdate                         		DATE		 NULL 
//	);
	private int kindno;
	private String title;
	private int cnt;
	private String rdate;
	private int seqno;
	private String visible;
	
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
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	
	public int getSeqno() {
		return seqno;
	}
	public void setSeqno(int seqno) {
		this.seqno = seqno;
	}
	
	public String getVisible() {
		return visible;
	}
	public void setVisible(String visible) {
		this.visible = visible;
	}
	@Override
	public String toString() {
		return "KindVO [kindno=" + kindno + ", title=" + title + ", cnt=" + cnt + ", rdate=" + rdate + ", seqno="
				+ seqno + ", visible=" + visible + "]";
	}

	
	
	
}

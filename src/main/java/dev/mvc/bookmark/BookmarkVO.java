package dev.mvc.bookmark;

public class BookmarkVO {
  private int bookmarkno;
  private int substancesno;
  private int visitorno;
  private String rdate = "";
  public int getBookmarkno() {
    return bookmarkno;
  }
  public void setBookmarkno(int bookmarkno) {
    this.bookmarkno = bookmarkno;
  }
  public int getSubstancesno() {
    return substancesno;
  }
  public void setSubstancesno(int substancesno) {
    this.substancesno = substancesno;
  }
  public int getVisitorno() {
    return visitorno;
  }
  public void setVisitorno(int visitorno) {
    this.visitorno = visitorno;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  
  
}

package dev.mvc.chatting;

public class ChattingVO {
  private int chattingno;
  private int visitorno;
  private String vname = "";
  private int reciverno;
  private String rname = "";
  private String msg;
  private String rdate;
  
  public int getChattingno() {
    return chattingno;
  }
  public void setChattingno(int chattingno) {
    this.chattingno = chattingno;
  }
  public int getVisitorno() {
    return visitorno;
  }
  public void setVisitorno(int visitorno) {
    this.visitorno = visitorno;
  }
  public String getVname() {
    return vname;
  }
  public void setVname(String vname) {
    this.vname = vname;
  }
  public int getReciverno() {
    return reciverno;
  }
  public void setReciverno(int reciverno) {
    this.reciverno = reciverno;
  }
  public String getRname() {
    return rname;
  }
  public void setRname(String rname) {
    this.rname = rname;
  }
  public String getMsg() {
    return msg;
  }
  public void setMsg(String msg) {
    this.msg = msg;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  
  
}

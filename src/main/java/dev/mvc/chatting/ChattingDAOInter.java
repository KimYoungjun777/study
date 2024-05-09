package dev.mvc.chatting;

import java.util.ArrayList;

public interface ChattingDAOInter {

  public int create(ChattingVO chattingVO);
  
  public ArrayList<ChattingVO> list_all();
  
  public ArrayList<ChattingVO> list_by_visitor(int visitorno);
}

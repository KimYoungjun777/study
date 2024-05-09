package dev.mvc.chatting;

import java.util.ArrayList;

public interface ChattingProcInter {
  
  // 메시지 보내기
  public int create(ChattingVO chattingVO);

  public ArrayList<ChattingVO> list_all();
 
  public ArrayList<ChattingVO> list_by_visitor(int visitorno);
}

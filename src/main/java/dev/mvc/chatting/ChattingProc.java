package dev.mvc.chatting;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("dev.mvc.chatting.ChattingProc")
public class ChattingProc implements ChattingProcInter {
  @Autowired
  private ChattingDAOInter chattingDAO;
  
  @Override
  public int create(ChattingVO chattingVO) {
    int cnt = this.chattingDAO.create(chattingVO);
    return cnt;
  }

  @Override
  public ArrayList<ChattingVO> list_all() {
    ArrayList<ChattingVO> list_all = this.chattingDAO.list_all();
    return list_all;
  }

  @Override
  public ArrayList<ChattingVO> list_by_visitor(int visitorno) {
    ArrayList<ChattingVO> list_by_visitor = this.chattingDAO.list_by_visitor(visitorno);
    return list_by_visitor;
  }

  
}

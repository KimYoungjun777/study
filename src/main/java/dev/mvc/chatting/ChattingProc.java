package dev.mvc.chatting;

import java.util.ArrayList;
import java.util.HashMap;

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
  public ArrayList<ChattingVO> list_by_visitor(ChattingVO chattingVO) {
    ArrayList<ChattingVO> list_by_visitor = this.chattingDAO.list_by_visitor(chattingVO);
    return list_by_visitor;
  }
  
  @Override
  public ArrayList<ChattingVO> list_by_reciver(ChattingVO chattingVO) {
    ArrayList<ChattingVO> list_by_reciver = this.chattingDAO.list_by_reciver(chattingVO);
    return list_by_reciver;
  }

  @Override
  public ArrayList<ChattingVO> chatting_list_by_visitor(ChattingVO chattingVO) {
    ArrayList<ChattingVO> chatting_list_by_visitor = this.chattingDAO.chatting_list_by_visitor(chattingVO);
    return chatting_list_by_visitor;
  }

  @Override
  public ArrayList<ChattingVO> chatting_list_by_reciver(ChattingVO chattingVO) {
    ArrayList<ChattingVO> chatting_list_by_reciver = this.chattingDAO.chatting_list_by_reciver(chattingVO);
    return chatting_list_by_reciver;
  }
  
  @Override
  public ChattingVO read(int chattingno) {
    ChattingVO chattingVO = this.chattingDAO.read(chattingno);
    return chattingVO;
  }

  @Override
  public int rname_edit(HashMap<String, Object> map) {
    int cnt = this.chattingDAO.rname_edit(map);
    return cnt;
  }



  
}

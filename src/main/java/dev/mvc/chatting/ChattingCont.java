package dev.mvc.chatting;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.grammer.GrammerProcInter;
import dev.mvc.kind.KindProcInter;
import dev.mvc.kind.KindVO;
import dev.mvc.substances.SubstancesProcInter;
import dev.mvc.visitor.VisitorProcInter;
import dev.mvc.visitor.VisitorVO;

@Controller
public class ChattingCont {
  @Autowired
  @Qualifier("dev.mvc.kind.KindProc")
  private KindProcInter kindProc;
  
  @Autowired
  @Qualifier("dev.mvc.grammer.GrammerProc")
  private GrammerProcInter grammerProc; 
  
  @Autowired
  @Qualifier("dev.mvc.substances.SubstancesProc")
  private SubstancesProcInter substancesProc;
  
  @Autowired
  @Qualifier("dev.mvc.visitor.VisitorProc")
  private VisitorProcInter visitorProc;
  
  @Autowired
  @Qualifier("dev.mvc.chatting.ChattingProc")
  private ChattingProcInter chattingProc;
  /**
   * 등록 폼
   * @return
   */
  @RequestMapping(value="/chatting/create.do", method=RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/chatting/create");
          
    return mav;
  }
  
  /**
   * 등록 처리
   * @param session
   * @param chattingVO
   * @return
   */
  @RequestMapping(value="/chatting/create.do", method=RequestMethod.POST)
  public ModelAndView create(HttpSession session, ChattingVO chattingVO) {
    ModelAndView mav = new ModelAndView();
    
    if(this.visitorProc.isVisitor(session) == true) {       
      
      int cnt = this.chattingProc.create(chattingVO);
      
      if (cnt == 1) {
        mav.setViewName("redirect:/chatting/list_by_visitor.do");
      } else {
        mav.addObject("code", "create_fail");
        mav.setViewName("/kind/msg");
      }
      
//    int memno = (int)session.getAttribute("memno"); // managerno FK
//    reviewVO.setMemno(memno);
//    MemVO memVO = this.memProc.read(memno);
//    reviewVO.setRname(memVO.getMname());
    }
    
    return mav;
  }
  /**
   * 전체 목록
   * @param session
   * @return
   */
  @RequestMapping(value="/chatting/list_all.do", method = RequestMethod.GET)
  public ModelAndView list_all(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    if(this.grammerProc.isGrammer(session) == true) {
      mav.setViewName("/chatting/list_all"); // /WEB-INF/views/cate/list_all.jsp
      
      ArrayList<ChattingVO> list_all = this.chattingProc.list_all();
      mav.addObject("list_all", list_all);
    } else {
      mav.setViewName("/grammer/login_need");
    }
    return mav;
  }
  
  /**
   * 회원별 보낸 메시지 목록
   * @param session
   * @return
   */
  @RequestMapping(value="/chatting/list_by_visitor.do", method = RequestMethod.GET)
  public ModelAndView list_by_visitor(HttpSession session, ChattingVO chattingVO) {
    ModelAndView mav = new ModelAndView();
    
    
    if(this.visitorProc.isVisitor(session) == true) {
      mav.setViewName("/chatting/list_by_visitor"); // /WEB-INF/views/cate/list_all.jsp
      
      int visitorno = (int)session.getAttribute("visitorno");
      chattingVO.setVisitorno(visitorno);
      
      ArrayList<ChattingVO> list_by_visitor = this.chattingProc.list_by_visitor(chattingVO);
      mav.addObject("list_by_visitor", list_by_visitor);
    } else {
      mav.setViewName("/visitor/login_need");
    }
    return mav;
  }
  
  /**
   * 회원별 받은 메시지 목록
   * @param session
   * @return
   */
  @RequestMapping(value="/chatting/list_by_reciver.do", method = RequestMethod.GET)
  public ModelAndView list_by_reciver(HttpSession session, ChattingVO chattingVO) {
    ModelAndView mav = new ModelAndView();
    
    
    if(this.visitorProc.isVisitor(session) == true) {
      mav.setViewName("/chatting/list_by_reciver"); // /WEB-INF/views/cate/list_all.jsp
      
      int reciverno = (int)session.getAttribute("visitorno");
      chattingVO.setReciverno(reciverno);
      
      ArrayList<ChattingVO> list_by_reciver = this.chattingProc.list_by_reciver(chattingVO);
      mav.addObject("list_by_reciver", list_by_reciver);
    } else {
      mav.setViewName("/visitor/login_need");
    }
    return mav;
  }
  
  /**
   * 보낸 메시지 목록(받은 사람 기준으로 다보이게)
   * @param session
   * @return
   */
  @RequestMapping(value="/chatting/chatting_list_by_visitor.do", method = RequestMethod.GET)
  public ModelAndView chatting_list_by_visitor(HttpSession session, ChattingVO chattingVO) {
    ModelAndView mav = new ModelAndView();
    
    
    if(this.visitorProc.isVisitor(session) == true) {
      mav.setViewName("/chatting/chatting_list_by_visitor"); // /WEB-INF/views/cate/list_all.jsp
      
      int visitorno = (int)session.getAttribute("visitorno");
      chattingVO.setVisitorno(visitorno);
      
      VisitorVO visitorVO = this.visitorProc.read(chattingVO.getReciverno());
      chattingVO.setRname(visitorVO.getMname());
      
      ArrayList<ChattingVO> chatting_list_by_visitor = this.chattingProc.chatting_list_by_visitor(chattingVO);
      mav.addObject("chatting_list_by_visitor", chatting_list_by_visitor);
    } else {
      mav.setViewName("/visitor/login_need");
    }
    return mav;
  }
  
  /**
   * 받은 메시지 목록(보낸 사람 기준으로 다보이게)
   * @param session
   * @return
   */
  @RequestMapping(value="/chatting/chatting_list_by_reciver.do", method = RequestMethod.GET)
  public ModelAndView chatting_list_by_reciver(HttpSession session, ChattingVO chattingVO) {
    ModelAndView mav = new ModelAndView();
    
    
    if(this.visitorProc.isVisitor(session) == true) {
      mav.setViewName("/chatting/chatting_list_by_reciver"); // /WEB-INF/views/cate/list_all.jsp
      
      int visitorno = (int)session.getAttribute("visitorno");
      chattingVO.setReciverno(visitorno);
      
      VisitorVO visitorVO = this.visitorProc.read(chattingVO.getVisitorno());
      chattingVO.setVname(visitorVO.getMname());
      
      ArrayList<ChattingVO> chatting_list_by_reciver = this.chattingProc.chatting_list_by_reciver(chattingVO);
      mav.addObject("chatting_list_by_reciver", chatting_list_by_reciver);
    } else {
      mav.setViewName("/visitor/login_need");
    }
    return mav;
  }
  
  /**
   * 채팅내역 하나만 보기
   * @param chattingno
   * @return
   */
  @RequestMapping(value="/chatting/read.do", method = RequestMethod.GET)
  public ModelAndView read(int chattingno) { // int cateno = (int)request.getParameter("cateno");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/chatting/read"); // /WEB-INF/views/cate/read.jsp
    
    ChattingVO chattingVO = this.chattingProc.read(chattingno);
    mav.addObject("chattingVO", chattingVO);
    
    return mav;
  }
  
  
  
  
  
  
  
  
  
  
  
}

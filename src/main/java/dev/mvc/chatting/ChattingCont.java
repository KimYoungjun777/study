package dev.mvc.chatting;

import java.util.ArrayList;

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
  
  @RequestMapping(value="/chatting/create.do", method=RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/chatting/create");
          
    return mav;
  }
  
  @RequestMapping(value="/chatting/create.do", method=RequestMethod.POST)
  public ModelAndView create(ChattingVO chattingVO) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.chattingProc.create(chattingVO);
    
    if (cnt == 1) {
      mav.setViewName("./list_all.do");
    } else {
      mav.addObject("code", "create_fail");
      mav.setViewName("/kind/msg");
    }
    mav.addObject("cnt", cnt);
    
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
   * 회원별 메시지 목록
   * @param session
   * @return
   */
  @RequestMapping(value="/chatting/list_by_visitor.do", method = RequestMethod.GET)
  public ModelAndView list_by_visitor(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    int visitorno = (int)session.getAttribute("visitorno");
    
    if(this.visitorProc.isVisitor(session) == true) {
      mav.setViewName("/chatting/list_by_visitor"); // /WEB-INF/views/cate/list_all.jsp
      
      ArrayList<ChattingVO> list_by_visitor = this.chattingProc.list_by_visitor(visitorno);
      mav.addObject("list_by_visitor", list_by_visitor);
    } else {
      mav.setViewName("/visitor/login_need");
    }
    return mav;
  }
  
  
  
  
  
  
  
  
  
  
  
  
}

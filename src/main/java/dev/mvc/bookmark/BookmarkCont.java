package dev.mvc.bookmark;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.chatting.ChattingProcInter;
import dev.mvc.chatting.ChattingVO;
import dev.mvc.grammer.GrammerProcInter;
import dev.mvc.kind.KindProcInter;
import dev.mvc.substances.SubstancesProcInter;
import dev.mvc.substances.SubstancesVO;
import dev.mvc.visitor.VisitorProcInter;

@Controller
public class BookmarkCont {
  
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
  
  @Autowired
  @Qualifier("dev.mvc.bookmark.BookmarkProc")
  private BookmarkProcInter bookmarkProc;
  
  /**
   * 북마크 등록 폼
   * @return
   */
  @RequestMapping(value="/bookmark/create.do", method=RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/bookmark/create");
          
    return mav;
  }
  
  @ResponseBody
  @RequestMapping(value="/bookmark/create.do", method = RequestMethod.POST)
  public Map<String, Object> create(BookmarkVO bookmarkVO) {   
   Map<String, Object> resultMap = new HashMap<>();
  
   try {
     int visitor_cnt = this.bookmarkProc.bookmark_by_visitor_cnt(bookmarkVO);
     if (visitor_cnt == 0) {
       // 좋아요 등록 로직을 호출하고 좋아요 수를 가져옴
       int cnt = bookmarkProc.create(bookmarkVO);
       resultMap.put("cnt", cnt);
       int bookmark_cnt = this.bookmarkProc.bookmark_cnt(bookmarkVO.getSubstancesno()); 
       resultMap.put("bookmark_cnt", bookmark_cnt);
       resultMap.put("visitor_cnt", 1);       
     } else {
       resultMap.put("cnt", 0);
       resultMap.put("visitor_cnt", 0);
     }
   } catch (Exception e) {
     resultMap.put("error", "좋아요 등록 실패");
   }
  
   return resultMap;
  }
  
  /**
   *  게시글 북마크 여부 확인
   * @return
   */
  @ResponseBody
  @RequestMapping(value="/bookmark/bookmark_by_visitor_cnt.do", method=RequestMethod.GET,
          produces = "text/plain;charset=UTF-8")
  public String bookmark_by_visitor_cnt(BookmarkVO bookmarkVO) {
    int cnt = this.bookmarkProc.bookmark_by_visitor_cnt(bookmarkVO);

    // JSON 객체 생성
    JSONObject json = new JSONObject();
    json.put("cnt", cnt);
    System.out.println(json);
    // JSON 문자열 반환
    return json.toString();
  }
  
  /**
   * 회원의 북마크 한 게시글
   * @param session
   * @return
   */
  @RequestMapping(value="/bookmark/substances_by_visitor.do", method = RequestMethod.GET)
  public ModelAndView substances_by_visitor(HttpSession session, int visitorno) {
    ModelAndView mav = new ModelAndView();
    
    if(this.visitorProc.isVisitor(session) == true) {
      mav.setViewName("/bookmark/substances_by_visitor"); // /WEB-INF/views/cate/list_all.jsp
      visitorno = (int)session.getAttribute("visitorno");
      String name = (String)session.getAttribute("name");
      mav.addObject("name", name);
      BookmarkVO bookmarkVO = new BookmarkVO();
      bookmarkVO.setVisitorno(visitorno);
      ArrayList<Integer> substancesno = this.bookmarkProc.substances_by_visitor(visitorno);
      mav.addObject("substancesno", substancesno);
      ArrayList <SubstancesVO> list = new ArrayList<SubstancesVO>();
      SubstancesVO substancesVO = new SubstancesVO();
      for(int i=0; i<substancesno.size(); i++) {
        substancesVO = this.substancesProc.read(substancesno.get(i));
        list.add(substancesVO);
      }
      mav.addObject("list", list);
      
    } else {
      mav.setViewName("/visitor/login_need");
    }
    return mav;
  }
  
  /**
   * 북마크 삭제, Ajax 처리
   * @return
   */
  @RequestMapping(value="/bookmark/delete.do", method=RequestMethod.GET )
  public ModelAndView delete() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/bookmark/delete");
    
    return mav;
  }
  
  @ResponseBody
  @RequestMapping(value="/bookmark/delete.do", method = RequestMethod.POST)
  public Map<String, Object> delete(BookmarkVO bookmarkVO) {   
   Map<String, Object> resultMap = new HashMap<>();
  
   try {
     // 좋아요 등록 로직을 호출하고 좋아요 수를 가져옴
     int cnt = bookmarkProc.delete(bookmarkVO);
     int bookmark_cnt = this.bookmarkProc.bookmark_cnt(bookmarkVO.getSubstancesno());
     resultMap.put("cnt", cnt);     
     resultMap.put("bookmark_cnt", bookmark_cnt);
   } catch (Exception e) {
     resultMap.put("error", "좋아요 등록 실패");
   }
  
   return resultMap;
  }
}

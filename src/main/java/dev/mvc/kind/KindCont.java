package dev.mvc.kind;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.grammer.GrammerDAOInter;
import dev.mvc.grammer.GrammerProcInter;
import dev.mvc.substances.Substances;
import dev.mvc.substances.SubstancesProcInter;
import dev.mvc.substances.SubstancesVO;
import dev.mvc.tool.Tool;



@Controller
public class KindCont {
	@Autowired
	@Qualifier("dev.mvc.kind.KindProc")
	  private KindProcInter kindProc;
	@Autowired
	@Qualifier("dev.mvc.grammer.GrammerProc")
	private GrammerProcInter grammerProc;	
	@Autowired
	@Qualifier("dev.mvc.substances.SubstancesProc")
	private SubstancesProcInter substancesProc;
	
	@RequestMapping(value="/kind/create.do", method=RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/kind/create");
		
		ArrayList<KindVO> list = this.kindProc.list_all();
	    mav.addObject("list", list);
	    
		return mav;
	}
	
	@RequestMapping(value="/kind/create.do", method=RequestMethod.POST)
	public ModelAndView create(KindVO kindVO) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/kind/msg");
		
		int cnt = this.kindProc.create(kindVO);
		if (cnt == 1) {
//	      mav.addObject("code", "create_success"); // 키, 값
//	      mav.addObject("name", cateVO.getName()); // 카테고리 이름 jsp로 전송
	    	mav.setViewName("redirect:/kind/list_all.do");
	    } else {
		  mav.addObject("code", "create_fail");
		  mav.setViewName("/kind/msg");
	    }
		mav.addObject("cnt", cnt);
		
		return mav;
	}
	  /**
	   * 전체 목록
	   * http://localhost:9092/kind/list_all.do
	   * @return
	   */
	  @RequestMapping(value="/kind/list_all.do", method = RequestMethod.GET)
	  public ModelAndView list_all(HttpSession session) {
	    ModelAndView mav = new ModelAndView();
	    
	    if(this.grammerProc.isGrammer(session) == true) {
		    mav.setViewName("/kind/list_all"); // /WEB-INF/views/cate/list_all.jsp
		    
		    ArrayList<KindVO> list = this.kindProc.list_all();
		    mav.addObject("list", list);
	    } else {
	    	mav.setViewName("/grammer/login_need");
	    }
	    return mav;
	  }
	  
	  /**
	   * 조회
	   * http://localhost:9092/kind/read.do?kindno=1
	   * @return
	   */
	  @RequestMapping(value="/kind/read.do", method = RequestMethod.GET)
	  public ModelAndView read(int kindno) { // int cateno = (int)request.getParameter("cateno");
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("/kind/read"); // /WEB-INF/views/cate/read.jsp
	    
	    KindVO kindVO = this.kindProc.read(kindno);
	    mav.addObject("kindVO", kindVO);
	    
	    return mav;
	  }
	  /**
	   * 수정폼
	   * http://localhost:9092/kind/update.do?kindno=1
	   * @return
	   */
	  @RequestMapping(value="/kind/update.do", method = RequestMethod.GET)
	  public ModelAndView update(HttpSession session, int kindno) { // int cateno = (int)request.getParameter("cateno");
	    ModelAndView mav = new ModelAndView();
	    if(this.grammerProc.isGrammer(session) == true) {
		    mav.setViewName("/kind/list_all_update"); // /WEB-INF/views/cate/update.jsp
		    
		    KindVO kindVO = this.kindProc.read(kindno);
		    mav.addObject("kindVO", kindVO);
		    
		    ArrayList<KindVO> list= (ArrayList<KindVO>)this.kindProc.list_all();
		    mav.addObject("list", list);
	    } else {
	    	mav.setViewName("/grammer/login_need");
	    }
	    return mav;
	  }
	  
	  /**
	   * 수정 처리, http://localhost:9092/kind/update.do
	   * @param kindVO 수정할 내용
	   * @return
	   */
	  
	  @RequestMapping(value="/kind/update.do", method = RequestMethod.POST)
	  public ModelAndView update(KindVO kindVO) { // 자동으로 cateVO 객체가 생성되고 폼의 값이 할당됨
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("/kind/msg"); // /WEB-INF/views/cate/msg.jsp
	    
	    int cnt = this.kindProc.update(kindVO); // 수정 처리
	    System.out.println("-> cnt: " + cnt);
	    
	    if (cnt == 1) {
//	      mav.addObject("code", "update_success"); // 키, 값
//	      mav.addObject("title", kindVO.getTitle()); // 카테고리 이름 jsp로 전송
	    	mav.setViewName("redirect:/kind/list_all.do");
	    } else {
	    	mav.setViewName("/kind/msg");
	    	mav.addObject("code", "update_fail");
	    }
	    
	    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt);
//	    mav.addObject("cnt", 0); // request.setAttribute("cnt", cnt);
	    
	    return mav;
	  }
	  
	  /**
	   * 삭제폼
	   * http://localhost:9092/kind/delete.do?kindno=1
	   * @return
	   */
	  @RequestMapping(value="/kind/delete.do", method = RequestMethod.GET)
	  public ModelAndView delete(HttpSession session, int kindno) { // int cateno = (int)request.getParameter("cateno");
	    ModelAndView mav = new ModelAndView();
	    if(this.grammerProc.isGrammer(session) == true) {
		    mav.setViewName("/kind/list_all_delete"); // /WEB-INF/views/cate/update.jsp
		    
		    KindVO kindVO = this.kindProc.read(kindno);
		    mav.addObject("kindVO", kindVO);
		    
		    ArrayList<KindVO> list= this.kindProc.list_all();
		    mav.addObject("list", list);
		    
		    int count_by_kindno = this.substancesProc.count_by_kindno(kindno);
		    mav.addObject("count_by_kindno", count_by_kindno);
	    } else {
	    	mav.setViewName("/grammer/login_need");
	    }
	    
	    return mav;
	  }
	  
	  /**
	   * 삭제 처리, http://localhost:9092/kind/delete.do
	   * @param kindVO 수정할 내용
	   * @return
	   */
	  
	  @RequestMapping(value="/kind/delete.do", method = RequestMethod.POST)
	  public ModelAndView delete_proc(HttpSession session, int kindno) { // 자동으로 cateVO 객체가 생성되고 폼의 값이 할당됨
	    ModelAndView mav = new ModelAndView();
	    if(this.grammerProc.isGrammer(session) == true) {
		    ArrayList<SubstancesVO> list= this.substancesProc.list_by_kindno(kindno);
		    for(SubstancesVO substancesVO : list) {
		    	String file1saved = substancesVO.getFile1saved();  
			    String thumb1 = substancesVO.getThumb1();      
			         
			    String upDir =  Substances.getUploadDir(); // C:/kd/deploy/resort_v2sbm3c/contents/storage/
			      
			    Tool.deleteFile(upDir, file1saved);  // 실제 저장된 파일삭제
			    Tool.deleteFile(upDir, thumb1); 
		    }

		    this.substancesProc.delete_by_kindno(kindno);
		    int cnt = this.kindProc.delete(kindno);
		    
		     if (cnt == 1) {
		         mav.setViewName("redirect:/kind/list_all.do");       // 자동 주소 이동, Spring 재호출
		         
		       } else {
		         mav.addObject("code", "delete_fail");
		         mav.setViewName("/kind/msg"); // /WEB-INF/views/cate/msg.jsp
		       }
		       
		       mav.addObject("cnt", cnt);
		       
		     } else {
		       mav.setViewName("/grammer/login_need"); // /WEB-INF/views/admin/login_need.jsp
		     }
		     
		     return mav;
	}
	  
	  /**
	   * 우선 순위 높임 http://localhost:9092/kind/update_seqno_up.do?kindno=1
	   * @param kindno 수정할 레코드 PK번호
	   * @return
	   */
	  @RequestMapping(value="/kind/update_seqno_up.do", method = RequestMethod.GET)
	  public ModelAndView update_seqno_up(int kindno) { // 자동으로 cateVO 객체가 생성되고 폼의 값이 할당됨
	    ModelAndView mav = new ModelAndView();
	   
	    int cnt = this.kindProc.update_seqno_up(kindno); // 수정 처리
	    System.out.println("-> cnt: " + cnt);
	    
	    if (cnt == 1) {
	    	mav.setViewName("redirect:/kind/list_all.do");
	    } else {
	    	mav.setViewName("/kind/msg"); 
	    	mav.addObject("code", "update_fail");
	    }
	    
	    mav.addObject("cnt", cnt); 
	    
	    return mav;
	  }
	  
	  /**
	   * 우선 순위 낮춤 http://localhost:9092/kind/update_seqno_down.do?kindno=1
	   * @param kindno 수정할 레코드 PK번호
	   * @return
	   */
	  @RequestMapping(value="/kind/update_seqno_down.do", method = RequestMethod.GET)
	  public ModelAndView update_seqno_down(int kindno) { // 자동으로 cateVO 객체가 생성되고 폼의 값이 할당됨
	    ModelAndView mav = new ModelAndView();
	   
	    int cnt = this.kindProc.update_seqno_down(kindno); // 수정 처리
	    System.out.println("-> cnt: " + cnt);
	    
	    if (cnt == 1) {
	    	mav.setViewName("redirect:/kind/list_all.do");
	    } else {
	    	mav.setViewName("/kind/msg"); 
	    	mav.addObject("code", "update_fail");
	    }
	    
	    mav.addObject("cnt", cnt); 
	    
	    return mav;
	  }
	  
	  /**
	   * 공개 설정 http://localhost:9092/kind/update_visible_y.do?kindno=1
	   * @param kindno 수정할 레코드 PK번호
	   * @return
	   */
	  @RequestMapping(value="/kind/update_visible_y.do", method = RequestMethod.GET)
	  public ModelAndView update_visible_y(int kindno) { // 자동으로 cateVO 객체가 생성되고 폼의 값이 할당됨
	    ModelAndView mav = new ModelAndView();
	   
	    int cnt = this.kindProc.update_visible_y(kindno); // 수정 처리
	    System.out.println("-> cnt: " + cnt);
	    
	    if (cnt == 1) {
	    	mav.setViewName("redirect:/kind/list_all.do");
	    } else {
	    	mav.setViewName("/kind/msg"); 
	    	mav.addObject("code", "update_fail");
	    }
	    
	    mav.addObject("cnt", cnt); 
	    
	    return mav;
	  }
	  
	  /**
	   * 비공개 설정 http://localhost:9092/kind/update_visible_n.do?kindno=1
	   * @param kindno 수정할 레코드 PK번호
	   * @return
	   */
	  @RequestMapping(value="/kind/update_visible_n.do", method = RequestMethod.GET)
	  public ModelAndView update_visible_n(int kindno) { // 자동으로 cateVO 객체가 생성되고 폼의 값이 할당됨
	    ModelAndView mav = new ModelAndView();
	   
	    int cnt = this.kindProc.update_visible_n(kindno); // 수정 처리
	    System.out.println("-> cnt: " + cnt);
	    
	    if (cnt == 1) {
	    	mav.setViewName("redirect:/kind/list_all.do");
	    } else {
	    	mav.setViewName("/kind/msg"); 
	    	mav.addObject("code", "update_fail");
	    }
	    
	    mav.addObject("cnt", cnt); 
	    
	    return mav;
	  }
}
		
	

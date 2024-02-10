package dev.mvc.grammer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.grammer.GrammerVO;
import dev.mvc.tool.Tool;
@Controller
public class GrammerCont {
	@Autowired
	@Qualifier("dev.mvc.grammer.GrammerProc")
	private GrammerProcInter grammerProc;
	
	public GrammerCont() {
		System.out.println("-> GrammerCont Created");
	}
	
	  /**
	   * POST 요청시 JSP 페이지에서 JSTL 호출 기능 지원, 새로고침 방지, EL에서 param으로 접근
	   * POST -> url -> GET -> 데이터 전송
	   */
	  @RequestMapping(value="/grammer/msg.do", method=RequestMethod.GET)
	  public ModelAndView msg(String url){
	    ModelAndView mav = new ModelAndView();

	    mav.setViewName(url); // forward
	    
	    return mav; // forward
	  }
	  
//	  /**
//	   * 로그인 폼
//	   * http://localhost:9092/grammer/login.do
//	   * @return
//	   */
//	  @RequestMapping(value="/grammer/login.do", method=RequestMethod.GET)
//	  public ModelAndView login() {
//	    ModelAndView mav = new ModelAndView();
    
//	    mav.setViewName("/grammer/login_form"); // /WEB-INF/views/admin/login_form.jsp
	    
//	    return mav;
//	  }
	  
//	  /**
//	   * 로그인 처리
//	   * http://localhost:9092/grammer/login.do
//	   * @return
//	   */
	//  @RequestMapping(value="/grammer/login.do", method=RequestMethod.POST)
//	  public ModelAndView login(HttpSession session, GrammerVO grammerVO) {
//	    ModelAndView mav = new ModelAndView();
//    
//	    int cnt = this.grammerProc.login(grammerVO);
   
//	    if (cnt == 1) { // 로그인 성공
//	      GrammerVO grammerVO_read = this.grammerProc.read_by_id(grammerVO.getId()); // 관리자 정보 읽기
//	      
//	      session.setAttribute("grammerno", grammerVO_read.getGrammerno()); // 서버의 메모리에 기록
//	      session.setAttribute("grammer_id", grammerVO_read.getId());
//	      session.setAttribute("grammer_gname", grammerVO_read.getGname());
//	      session.setAttribute("grammer_grade", grammerVO_read.getGrade());

//	      mav.setViewName("redirect:/index.do"); // 시작 페이지
//	    } else {  // 로그인 실패
//	      // /WEB-INF/views/admin/login_fail_msg.jsp
//	      // POST 방식에서는 jsp에서 <c:import 태그가 실행이 안됨.
//	      // mav.setViewName("/admin/login_fail_msg");   
	      
//	      mav.addObject("url", "/grammer/login_fail_msg"); // /WEB-INF/views/admin/login_fail_msg.jsp
//	      mav.setViewName("redirect:/grammer/msg.do"); 
//	    }
	        
//	    return mav;
//	  }	  
	  
	  /**
	   * 로그아웃 처리
	   * @param session
	   * @return
	   */
	  @RequestMapping(value="/grammer/logout.do", method=RequestMethod.GET)
	  public ModelAndView logout(HttpSession session){
	    ModelAndView mav = new ModelAndView();
	    session.invalidate(); // 모든 session 변수 삭제
	    
	    mav.setViewName("redirect:/index.do"); 
	    
	    return mav;
	  }
	  
	  /**
	   * Cookie 로그인 폼
	   * @return
	   */
	  // http://localhost:9092/grammer/login.do 
	  @RequestMapping(value = "/grammer/login.do", method = RequestMethod.GET)
	  public ModelAndView login() {
	    ModelAndView mav = new ModelAndView();
	  
	    mav.setViewName("/grammer/login_form_ck"); 
	    
	    return mav;
	  }
	  
	  /**
	   * Cookie 기반 로그인 처리
	   * @param request Cookie를 읽기위해 필요
	   * @param response Cookie를 쓰기위해 필요
	   * @param session 로그인 정보를 메모리에 기록
	   * @param id  회원 아이디
	   * @param passwd 회원 패스워드
	   * @param id_save 회원 아이디 Cookie에 저장 여부
	   * @param passwd_save 패스워드 Cookie에 저장 여부
	   * @param id_save id저장여부
	   * @param passwd_save 폼에 입력된 password 저장 여부
	   * @return
	   */
	   // http://localhost:9092/grammer/login.do 
	   @RequestMapping(value = "/grammer/login.do", 
	                             method = RequestMethod.POST)
	   public ModelAndView login_cookie_proc(
	                             HttpServletRequest request,
	                             HttpServletResponse response,
	                             HttpSession session,
	                             GrammerVO grammerVO, String id_save, String passwd_save) {
	     ModelAndView mav = new ModelAndView();
	    
	     int cnt = grammerProc.login(grammerVO);
	     if (cnt == 1) { // 로그인 성공
	    	 GrammerVO grammerVO_read = grammerProc.read_by_id(grammerVO.getId()); // DBMS에서 id를 이용한 회원 조회
	       session.setAttribute("grammerno", grammerVO_read.getGrammerno()); // 서버의 메모리에 기록
	       session.setAttribute("grammer_id", grammerVO_read.getId());
	       session.setAttribute("grammer_gname", grammerVO_read.getGname());
	       session.setAttribute("grammer_grade", grammerVO_read.getGrade());
	    
	       String id = grammerVO.getId();              // 폼에 입력된 id
	       String passwd = grammerVO.getPasswd();              // 폼에 입력된 passwd 
	       
	       // -------------------------------------------------------------------
	       // id 관련 쿠기 저장
	       // -------------------------------------------------------------------
	       if (Tool.checkNull(id_save).equals("Y")) { // id를 저장할 경우, Checkbox를 체크한 경우
	         Cookie ck_grammer_id = new Cookie("ck_grammer_id", id);
	         ck_grammer_id.setPath("/");  // root 폴더에 쿠키를 기록함으로 모든 경로에서 쿠기 접근 가능
	         ck_grammer_id.setMaxAge(60 * 60 * 24 * 30); // 30 day, 초단위
	         response.addCookie(ck_grammer_id); // id 저장
	       } else { // N, id를 저장하지 않는 경우, Checkbox를 체크 해제한 경우
	         Cookie ck_grammer_id = new Cookie("ck_grammer_id", "");
	         ck_grammer_id.setPath("/");
	         ck_grammer_id.setMaxAge(0);
	         response.addCookie(ck_grammer_id); // id 저장
	       }
	       
	       // id를 저장할지 선택하는 CheckBox 체크 여부
	       Cookie ck_grammer_id_save = new Cookie("ck_grammer_id_save", id_save);
	       ck_grammer_id_save.setPath("/");
	       ck_grammer_id_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
	       response.addCookie(ck_grammer_id_save);
	       // -------------------------------------------------------------------
	   
	       // -------------------------------------------------------------------
	       // Password 관련 쿠기 저장
	       // -------------------------------------------------------------------
	       if (Tool.checkNull(passwd_save).equals("Y")) { // 패스워드 저장할 경우
	         Cookie ck_grammer_passwd = new Cookie("ck_grammer_passwd", passwd);
	         ck_grammer_passwd.setPath("/");
	         ck_grammer_passwd.setMaxAge(60 * 60 * 24 * 30); // 30 day
	         response.addCookie(ck_grammer_passwd);
	       } else { // N, 패스워드를 저장하지 않을 경우
	         Cookie ck_grammer_passwd = new Cookie("ck_grammer_passwd", "");
	         ck_grammer_passwd.setPath("/");
	         ck_grammer_passwd.setMaxAge(0);
	         response.addCookie(ck_grammer_passwd);
	       }
	       
	       // passwd를 저장할지 선택하는  CheckBox 체크 여부
	       Cookie ck_grammer_passwd_save = new Cookie("ck_grammer_passwd_save", passwd_save);
	       ck_grammer_passwd_save.setPath("/");
	       ck_grammer_passwd_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
	       response.addCookie(ck_grammer_passwd_save);
	       // -------------------------------------------------------------------
	    
	       mav.setViewName("redirect:/index.do");  
	     } else {
	       mav.addObject("url", "/grammer/login_fail_msg");
	       mav.setViewName("redirect:/grammer/msg.do"); 
	     }
	        
	     return mav;
	   }
	  
	  
	  
	  
	  
	  
	  
	  
	  
}

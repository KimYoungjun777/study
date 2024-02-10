package dev.mvc.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.grammer.GrammerProcInter;
import dev.mvc.kind.KindProcInter;
import dev.mvc.substances.SubstancesProcInter;
 
@Controller
public class VisitorCont {
  @Autowired
  @Qualifier("dev.mvc.visitor.VisitorProc")
  private VisitorProcInter visitorProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.kind.KindProc")
    private KindProcInter kindProc;
  
  @Autowired
  @Qualifier("dev.mvc.grammer.GrammerProc")
  private GrammerProcInter grammerProc;
  
  @Autowired
  @Qualifier("dev.mvc.substances.SubstancesProc")
  private SubstancesProcInter substancesProc;
  public VisitorCont(){
    
    System.out.println("-> VisitorCont created.");
  }
  
  // http://localhost:9092/visitor/checkID.do?id=user1
  /**
  * ID 중복 체크, JSON 출력
  * @return
  */
  @ResponseBody
  @RequestMapping(value="/visitor/checkID.do", method=RequestMethod.GET ,
                         produces = "text/plain;charset=UTF-8" )
  public String checkID(String id) {
    
    try {
      Thread.sleep(3000); // 3 초 지연
    }catch(Exception e) {
      
    }
    int cnt = this.visitorProc.checkID(id);
   
    JSONObject json = new JSONObject();
    json.put("cnt", cnt);
   
    return json.toString(); // {"cnt":1} 
  }

  // http://localhost:9092/visitor/create.do
  /**
  * 등록 폼
  * @return
  */
  @RequestMapping(value="/visitor/create.do", method=RequestMethod.GET )
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/visitor/create"); // /WEB-INF/views/member/create.jsp
   
    return mav; // forward
  }
  
  /**
   * 등록 처리
   * @param memberVO
   * @return
   */
  @RequestMapping(value="/visitor/create.do", method=RequestMethod.POST)
  public ModelAndView create(VisitorVO visitorVO){
    ModelAndView mav = new ModelAndView();
    
    // 중복 ID 검사
    int checkID_cnt = this.visitorProc.checkID(visitorVO.getId());
    if (checkID_cnt == 0) {
      // System.out.println("id: " + memberVO.getId());
      
      visitorVO.setGrade(15); // 기본 회원 가입 등록 15 지정
      
      int cnt= visitorProc.create(visitorVO); // SQL insert
      
      if (cnt == 1) { // insert 레코드 개수, 회원 가입 성공
        mav.addObject("code", "create_success");
        mav.addObject("mname", visitorVO.getMname());  // 홍길동님(user4) 회원 가입을 축하합니다.
        mav.addObject("id", visitorVO.getId());
      } else { // 회원 가입 실패
        mav.addObject("code", "create_fail");
        // mav.addObject("cnt", 0);  // 추가된 레코드 없음.  
      }
      
      mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
      
    } else {
      mav.addObject("code", "duplicate_fail"); // 이미 사용중인 id임으로 가입 실패
      mav.addObject("cnt", 0);                       // 추가된 레코드 없음.      
    }

    mav.addObject("url", "/visitor/msg");  // /member/msg -> /member/msg.jsp
    
    mav.setViewName("redirect:/visitor/msg.do"); // POST -> GET -> /member/msg.jsp
    
    return mav;
  }
  
  /**
   * 새로고침 방지, EL에서 param으로 접근, POST -> GET -> /member/msg.jsp
   * @return
   */
  @RequestMapping(value="/visitor/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url); // forward
    
    return mav; // forward
  }
  
  /**
  * 목록 출력 가능
  * @param session
  * @return
  */
  @RequestMapping(value="/visitor/list.do", method=RequestMethod.GET)
  public ModelAndView list(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    if (this.grammerProc.isGrammer(session) == true) {
      ArrayList<VisitorVO> list = this.visitorProc.list();
      mav.addObject("list", list);

      mav.setViewName("/visitor/list"); // /webapp/WEB-INF/views/member/list.jsp

    } else {
      mav.setViewName("/grammer/login_need"); // /WEB-INF/views/admin/login_need.jsp
    }
        
    return mav;
  } 
  
  /**
   * 회원 조회
   * 관리자, 회원 본인만 가능
   * @param memberno
   * @return
   */
  @RequestMapping(value="/visitor/read.do", method=RequestMethod.GET)
  public ModelAndView read(HttpSession session, HttpServletRequest request){
    ModelAndView mav = new ModelAndView();
    
    int visitorno = 0;
    if (this.visitorProc.isVisitor(session) || this.grammerProc.isGrammer(session)) { 
      // 로그인한 경우

      if (this.visitorProc.isVisitor(session)) { // 회원으로 로그인
        visitorno = (int)session.getAttribute("visitorno");
        
      } else if (this.grammerProc.isGrammer(session)) { // 관리자로 로그인
        visitorno = Integer.parseInt(request.getParameter("visitorno"));
        
      }

      VisitorVO visitorVO = this.visitorProc.read(visitorno);
      mav.addObject("visitorVO", visitorVO);
      mav.setViewName("/visitor/read"); // /member/read.jsp
      
    } else {
      // 로그인을 하지 않은 경우
      mav.setViewName("/visitor/login_need"); // /webapp/WEB-INF/views/member/login_need.jsp
    }
    
    return mav; // forward
  }
  
  @RequestMapping(value="/visitor/update.do", method=RequestMethod.POST)
  public ModelAndView update(VisitorVO visitorVO){
    ModelAndView mav = new ModelAndView();

    int cnt= this.visitorProc.update(visitorVO); // SQL insert
      
    if (cnt == 1) {
      mav.addObject("code", "update_success");
      mav.addObject("mname", visitorVO.getMname());  
      mav.addObject("id", visitorVO.getId());
    } else { 
      mav.addObject("code", "update_fail");
    }
      
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
    mav.addObject("url", "/visitor/msg");  // /member/msg -> /member/msg.jsp    
    mav.setViewName("redirect:/visitor/msg.do"); // POST -> GET -> /member/msg.jsp
    
    return mav;
  }
  
  /**
   * 회원 삭제
   * @param memberno
   * @return
   */
  @RequestMapping(value="/visitor/delete.do", method=RequestMethod.GET)
  public ModelAndView delete(int visitorno){
    ModelAndView mav = new ModelAndView();
    
    VisitorVO visitorVO = this.visitorProc.read(visitorno); // 삭제할 레코드를 사용자에게 출력하기위해 읽음.
    mav.addObject("visitorVO", visitorVO);
    
    mav.setViewName("/visitor/delete"); // /member/delete.jsp
    
    return mav; // forward
  }
  
  /**
   * 회원 삭제 처리
   * @param memberVO
   * @return
   */
  @RequestMapping(value="/visitor/delete.do", method=RequestMethod.POST)
  public ModelAndView delete_proc(int visitorno){
    ModelAndView mav = new ModelAndView();
    
    // System.out.println("id: " + memberVO.getId());
    // 삭제된 정보를 msg.jsp에 출력하기 위해, 삭제전에 회원 정보를 읽음.
    VisitorVO visitorVO = this.visitorProc.read(visitorno); 
    
    int cnt= this.visitorProc.delete(visitorno);

    if (cnt == 1) {
      mav.addObject("code", "delete_success");
      mav.addObject("mname", visitorVO.getMname());  // 홍길동님(user4) 회원 정보를 삭제했습니다.
      mav.addObject("id", visitorVO.getId());
    } else {
      mav.addObject("code", "delete_fail");
    }

    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
    mav.addObject("url", "/visitor/msg");  // /member/msg -> /member/msg.jsp
    
    mav.setViewName("redirect:/visitor/msg.do");
    
    return mav;
  }
  
//  /**
//   * 로그인 폼
//   * @return
//   */
//  // http://localhost:9092/visitor/login.do 
//  @RequestMapping(value = "/visitor/login.do", 
//                             method = RequestMethod.GET)
//  public ModelAndView login() {
//    ModelAndView mav = new ModelAndView();
//  
//    mav.setViewName("/visitor/login_form");
//    return mav;
//  }
//
//  /**
//   * 로그인 처리
//   * @return
//   */
//  // http://localhost:9092/member/login.do 
//  @RequestMapping(value = "/visitorr/login.do", 
//                             method = RequestMethod.POST)
//  public ModelAndView login_proc(HttpSession session,
//                                                   String id, 
//                                                   String passwd) {
//    ModelAndView mav = new ModelAndView();
//    HashMap<String, Object> map = new HashMap<String, Object>();
//    map.put("id", id);
//    map.put("passwd", passwd);
//    
//    int count = this.visitorProc.login(map); // id, passwd 일치 여부 확인
//    if (count == 1) { // 로그인 성공
//      // System.out.println(id + " 로그인 성공");
//      VisitorVO visitorVO = visitorProc.readbyId(id); // 로그인한 회원의 정보 조회
//      session.setAttribute("visitorno", visitorVO.getVisitorno());
//      session.setAttribute("id", id);
//      session.setAttribute("mname", visitorVO.getMname());
//      session.setAttribute("age", visitorVO.getAge());
//      session.setAttribute("sex", visitorVO.getSex());
//      session.setAttribute("grade", visitorVO.getGrade());
//      
//      mav.setViewName("redirect:/index.do"); // 시작 페이지로 이동  
//    } else {
//      mav.addObject("url", "/visitor/login_fail_msg"); // login_fail_msg.jsp, redirect parameter 적용
//     
//      mav.setViewName("redirect:/visitor/msg.do"); // 새로고침 방지
//    }
//        
//    return mav;
//  }
  
  /**
   * 로그아웃 처리
   * @param session
   * @return
   */
  @RequestMapping(value="/visitor/logout.do", 
                             method=RequestMethod.GET)
  public ModelAndView logout(HttpSession session){
    ModelAndView mav = new ModelAndView();
    session.invalidate(); // 모든 session 변수 삭제
    
    mav.setViewName("redirect:/index.do"); 
    
    return mav;
  }
  
  /**
   * 로그인 폼
   * @return
   */
  // http://localhost:9091/member/login.do 
  @RequestMapping(value = "/visitor/login.do", 
                             method = RequestMethod.GET)
  public ModelAndView login_cookie(HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    
    Cookie[] cookies = request.getCookies();
    Cookie cookie = null;
  
    String ck_id = ""; // id 저장
    String ck_id_save = ""; // id 저장 여부를 체크
    String ck_passwd = ""; // passwd 저장
    String ck_passwd_save = ""; // passwd 저장 여부를 체크
  
    if (cookies != null) { // 쿠키가 존재한다면
      for (int i=0; i < cookies.length; i++){
        cookie = cookies[i]; // 쿠키 객체 추출
      
        if (cookie.getName().equals("ck_id")){
          ck_id = cookie.getValue(); 
        }else if(cookie.getName().equals("ck_id_save")){
          ck_id_save = cookie.getValue();  // Y, N
        }else if (cookie.getName().equals("ck_passwd")){
          ck_passwd = cookie.getValue();         // 1234
        }else if(cookie.getName().equals("ck_passwd_save")){
          ck_passwd_save = cookie.getValue();  // Y, N
        }
      }
    }
  
    //    <input type='text' class="form-control" name='id' id='id' 
    //            value='${ck_id }' required="required" 
    //            style='width: 30%;' placeholder="아이디" autofocus="autofocus">
    mav.addObject("ck_id", ck_id);
  
    //    <input type='checkbox' name='id_save' value='Y' 
    //            ${ck_id_save == 'Y' ? "checked='checked'" : "" }> 저장
    mav.addObject("ck_id_save", ck_id_save);
  
    mav.addObject("ck_passwd", ck_passwd);
    mav.addObject("ck_passwd_save", ck_passwd_save);
  
    mav.setViewName("/visitor/login_form_ck"); // /member/login_form_ck.jsp
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
  * @return
  */
  // http://localhost:9091/member/login.do 
  @RequestMapping(value = "/visitor/login.do", 
                            method = RequestMethod.POST)
  public ModelAndView login_cookie_proc(
                            HttpServletRequest request,
                            HttpServletResponse response,
                            HttpSession session,
                            String id,
                            String passwd,
                            @RequestParam(value="id_save", defaultValue="") String id_save,
                            @RequestParam(value="passwd_save", defaultValue="") String passwd_save) {
    ModelAndView mav = new ModelAndView();
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("id", id);
    map.put("passwd", passwd);
   
    int cnt = visitorProc.login(map);
    if (cnt == 1) { // 로그인 성공
      // System.out.println(id + " 로그인 성공");
      VisitorVO visitorVO = visitorProc.readbyId(id);
      session.setAttribute("visitorno", visitorVO.getVisitorno()); // 서버의 메모리에 기록
      session.setAttribute("id", id);
      session.setAttribute("mname", visitorVO.getMname());
      session.setAttribute("grade", visitorVO.getGrade());
   
      // -------------------------------------------------------------------
      // id 관련 쿠기 저장
      // -------------------------------------------------------------------
      if (id_save.equals("Y")) { // id를 저장할 경우, Checkbox를 체크한 경우
        Cookie ck_id = new Cookie("ck_id", id);
        ck_id.setPath("/");  // root 폴더에 쿠키를 기록함으로 모든 경로에서 쿠기 접근 가능
        ck_id.setMaxAge(60 * 60 * 24 * 30); // 30 day, 초단위
        response.addCookie(ck_id); // id 저장
      } else { // N, id를 저장하지 않는 경우, Checkbox를 체크 해제한 경우
        Cookie ck_id = new Cookie("ck_id", "");
        ck_id.setPath("/");
        ck_id.setMaxAge(0);
        response.addCookie(ck_id); // id 저장
      }
      
      // id를 저장할지 선택하는  CheckBox 체크 여부
      Cookie ck_id_save = new Cookie("ck_id_save", id_save);
      ck_id_save.setPath("/");
      ck_id_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
      response.addCookie(ck_id_save);
      // -------------------------------------------------------------------
  
      // -------------------------------------------------------------------
      // Password 관련 쿠기 저장
      // -------------------------------------------------------------------
      if (passwd_save.equals("Y")) { // 패스워드 저장할 경우
        Cookie ck_passwd = new Cookie("ck_passwd", passwd);
        ck_passwd.setPath("/");
        ck_passwd.setMaxAge(60 * 60 * 24 * 30); // 30 day
        response.addCookie(ck_passwd);
      } else { // N, 패스워드를 저장하지 않을 경우
        Cookie ck_passwd = new Cookie("ck_passwd", "");
        ck_passwd.setPath("/");
        ck_passwd.setMaxAge(0);
        response.addCookie(ck_passwd);
      }
      // passwd를 저장할지 선택하는  CheckBox 체크 여부
      Cookie ck_passwd_save = new Cookie("ck_passwd_save", passwd_save);
      ck_passwd_save.setPath("/");
      ck_passwd_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
      response.addCookie(ck_passwd_save);
      // -------------------------------------------------------------------
   
      mav.setViewName("redirect:/index.do");  
    } else {
      mav.addObject("url", "/visitor/login_fail_msg");
      mav.setViewName("redirect:/visitor/msg.do"); 
    }
       
    return mav;
  }
  
  /**
  * 패스워드를 변경합니다.
  * http://localhost:9092/visitor/passwd_update.do
  * @param visitorno
  * @return
  */
 @RequestMapping(value="/visitor/passwd_update.do", method=RequestMethod.GET)
 public ModelAndView passwd_update(){
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/visitor/passwd_update"); // passwd_update.jsp
   
   return mav;
 }
 
 /**
  * 패스워드 검사 
  * 로그인 실행 -> http://localhost:9092/visitor/passwd_check.do?current_passwd=1234
  * @param session
  * @param current_passwd 현재 패스워드
  * @return 1: 일치, 0: 불일치
  */
 @ResponseBody
 @RequestMapping(value="/visitor/passwd_check.do", method=RequestMethod.GET)
 public String passwd_check(HttpSession session, String current_passwd) {
   try {
     Thread.sleep(3000); // 3초
   } catch(Exception e) {

   }
   
   int visitorno = (int)session.getAttribute("visitorno");
   HashMap<String, Object> map = new HashMap<String, Object>();
   map.put("visitorno", visitorno);
   map.put("passwd", current_passwd);
   int cnt = this.visitorProc.passwd_check(map);
   
   JSONObject json = new JSONObject();
   json.put("cnt", cnt);
   
   return json.toString();
 }
 
 /**
  * 패스워드 변경 처리
  * @param visitorno 회원 번호
  * @param current_passwd 현재 패스워드
  * @param new_passwd 새로운 패스워드
  * @return
  */
 @RequestMapping(value="/visitor/passwd_update.do", method=RequestMethod.POST)
 public ModelAndView passwd_update(HttpSession session, String current_passwd, String new_passwd){
   ModelAndView mav = new ModelAndView();

   // int memberno = 3; // 테스트
   int visitorno = (int)session.getAttribute("visitorno"); // 현재 로그인한 회원의 정보만 패스워드 변경 가능
   
   VisitorVO visitorVO = this.visitorProc.read(visitorno); // 패스워드를 변경하려는 회원 정보를 읽음
   mav.addObject("mname", visitorVO.getMname());  
   mav.addObject("id", visitorVO.getId());
   
   // 현재 패스워드 검사용 데이터
   HashMap<String, Object> map = new HashMap<String, Object>();
   map.put("visitorno", visitorno);
   map.put("passwd", current_passwd);
   
   int cnt = visitorProc.passwd_check(map); // 현재 패스워드 검사
   int update_cnt = 0; // 변경된 패스워드 수
   
   if (cnt == 1) { // 현재 패스워드가 일치하는 경우
     map.put("passwd", new_passwd); // 새로운 패스워드를 저장
     update_cnt = this.visitorProc.passwd_update(map); // 패스워드 변경 처리
     
     if (update_cnt == 1) {
       mav.addObject("code", "passwd_update_success"); // 패스워드 변경 성공
     } else {
       cnt = 0;  // 패스워드는 일치했으나 변경하지는 못함.
       mav.addObject("code", "passwd_update_fail");       // 패스워드 변경 실패
     }
     
     mav.addObject("update_cnt", update_cnt);  // 변경된 패스워드의 갯수    
   } else {
     mav.addObject("code", "passwd_fail"); // 패스워드가 일치하지 않는 경우
   }

   mav.addObject("cnt", cnt); // 패스워드 일치 여부
   mav.addObject("url", "/visitor/msg");  // /member/msg -> /member/msg.jsp
   
   mav.setViewName("redirect:/visitor/msg.do");
   
   return mav;
 }
 
  
  
  
  
  
  
  
  
  
}

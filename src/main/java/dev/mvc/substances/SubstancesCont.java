package dev.mvc.substances;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.kind.KindVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;
import dev.mvc.visitor.VisitorProcInter;
import dev.mvc.grammer.GrammerProcInter;
import dev.mvc.kind.KindProcInter;

@Controller
public class SubstancesCont {
	@Autowired
	@Qualifier("dev.mvc.kind.KindProc")
	  private KindProcInter kindProc;
	
	@Autowired
	@Qualifier("dev.mvc.grammer.GrammerProc")
	private GrammerProcInter grammerProc;
	
	 @Autowired
	  @Qualifier("dev.mvc.visitor.VisitorProc")
	  private VisitorProcInter visitorProc;
	 
	@Autowired
	@Qualifier("dev.mvc.substances.SubstancesProc")
	private SubstancesProcInter substancesProc;
	
	// 등록 폼, contents 테이블은 FK로 cateno를 사용함.
	  // http://localhost:9091/contents/create.do  X
	  // http://localhost:9091/contents/create.do?cateno=1 // cateno 변수값을 보내는 목적
	  // http://localhost:9091/contents/create.do?cateno=2
	  // http://localhost:9091/contents/create.do?cateno=3
	  @RequestMapping(value="/substances/create.do", method = RequestMethod.GET)
	  public ModelAndView create(int kindno) {
	//  public ModelAndView create(HttpServletRequest request,  int cateno) {
	    ModelAndView mav = new ModelAndView();

	    KindVO kindVO = this.kindProc.read(kindno); // create.jsp에 카테고리 정보를 출력하기위한 목적
	    mav.addObject("kindVO", kindVO);
	    
	    mav.setViewName("/substances/create"); // /webapp/WEB-INF/views/contents/create.jsp
	    
	    return mav;
	  }
	  
	  /**
	   * 등록 처리 http://localhost:9092/substances/create.do
	   * 
	   * @return
	   */
	  @RequestMapping(value = "/substances/create.do", method = RequestMethod.POST)
	  public ModelAndView create(HttpServletRequest request, HttpSession session, SubstancesVO substancesVO) {
	    ModelAndView mav = new ModelAndView();
	    
	    if (visitorProc.isVisitor(session)) { // 관리자로 로그인한경우
	      // ------------------------------------------------------------------------------
	      // 파일 전송 코드 시작
	      // ------------------------------------------------------------------------------
	      String file1 = "";          // 원본 파일명 image
	      String file1saved = "";   // 저장된 파일명, image
	      String thumb1 = "";     // preview image

	      String upDir =  Substances.getUploadDir(); // 파일을 업로드할 폴더 준비
	      System.out.println("-> upDir: " + upDir);
	      
	      // 전송 파일이 없어도 file1MF 객체가 생성됨.
	      // <input type='file' class="form-control" name='file1MF' id='file1MF' 
	      //           value='' placeholder="파일 선택">
	      MultipartFile mf = substancesVO.getFile1MF();
	      
	      file1 = Tool.getFname(mf.getOriginalFilename()); // 원본 순수 파일명 산출
	      System.out.println("-> file1: " + file1);
	      
	      long size1 = mf.getSize();  // 파일 크기
	      
	      if (size1 > 0) { // 파일 크기 체크
	        // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
	        file1saved = Upload.saveFileSpring(mf, upDir); 
	        
	        if (Tool.isImage(file1saved)) { // 이미지인지 검사
	          // thumb 이미지 생성후 파일명 리턴됨, width: 200, height: 150
	          thumb1 = Tool.preview(upDir, file1saved, 200, 150); 
	        }
	        
	      }    
	      
	      substancesVO.setFile1(file1);   // 순수 원본 파일명
	      substancesVO.setFile1saved(file1saved); // 저장된 파일명(파일명 중복 처리)
	      substancesVO.setThumb1(thumb1);      // 원본이미지 축소판
	      substancesVO.setSize1(size1);  // 파일 크기
	      // ------------------------------------------------------------------------------
	      // 파일 전송 코드 종료
	      // ------------------------------------------------------------------------------
	      
	      // Call By Reference: 메모리 공유, Hashcode 전달
	      int visitorno = (int)session.getAttribute("visitorno"); // adminno FK
	      substancesVO.setVisitorno(visitorno);
	      int cnt = this.substancesProc.create(substancesVO); 
	      
	      // ------------------------------------------------------------------------------
	      // PK의 return
	      // ------------------------------------------------------------------------------
	      // System.out.println("--> contentsno: " + contentsVO.getContentsno());
	      // mav.addObject("contentsno", contentsVO.getContentsno()); // redirect parameter 적용
	      // ------------------------------------------------------------------------------
	      
	      if (cnt == 1) {
	          mav.addObject("code", "create_success");
	          // cateProc.increaseCnt(contentsVO.getCateno()); // 글수 증가
	      } else {
	          mav.addObject("code", "create_fail");
	      }
	      mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
	      
	      // System.out.println("--> cateno: " + contentsVO.getCateno());
	      // redirect시에 hidden tag로 보낸것들이 전달이 안됨으로 request에 다시 저장
	      mav.addObject("kindno", substancesVO.getKindno()); // redirect parameter 적용
	      
	      mav.addObject("url", "/substances/msg"); // msg.jsp, redirect parameter 적용
	      mav.setViewName("redirect:/substances/msg.do"); // POST방식 GET방식으로 바꾸는 작업.

	    } else {
	      mav.addObject("url", "/visitor/login_need"); // /WEB-INF/views/admin/login_need.jsp
	      mav.setViewName("redirect:/substances/msg.do"); 
	    }
	    
	    return mav; // forward
	  }
	  
	  /**
	   * POST 요청시 JSP 페이지에서 JSTL 호출 기능 지원, 새로고침 방지, EL에서 param으로 접근
	   * POST -> url -> GET -> 데이터 전송
	   */
	  @RequestMapping(value="/substances/msg.do", method=RequestMethod.GET)
	  public ModelAndView msg(String url){
	    ModelAndView mav = new ModelAndView();

	    mav.setViewName(url); // forward
	    
	    return mav; // forward
	  }
	  
	  /**
	   * 전체 목록
	   * http://localhost:9092/substances/list_all.do
	   * @return
	   */
	  @RequestMapping(value="/substances/list_all.do", method = RequestMethod.GET)
	  public ModelAndView list_all(HttpSession session) {
	    ModelAndView mav = new ModelAndView();
	    ArrayList<SubstancesVO> list = this.substancesProc.list_all();
	    
	    
		  mav.setViewName("/substances/list_all"); // /WEB-INF/views/cate/list_all.jsp
		   
		  for(SubstancesVO substancesVO:list) {
		   String title = substancesVO.getTitle();
		   String substance = substancesVO.getSubstance();
		   int kindno = substancesVO.getKindno();
		    	
		   title = Tool.convertChar(title);
		   substance = Tool.convertChar(substance);
		    	
		   substancesVO.setTitle(title);
		   substancesVO.setSubstance(substance);
       substancesVO.setKindno(kindno);
		  }
		  mav.addObject("list", list);
		    
	    
	    return mav;
	  }
	  
	  /**
	   * 종류 별 목록
	   * http://localhost:9092/substances/list_by_kindno.do?kindno=1
	   * @return
	   */
//	  @RequestMapping(value="/substances/list_by_kindno.do", method = RequestMethod.GET)
//	  public ModelAndView list_by_kindno(int kindno) {
//	    ModelAndView mav = new ModelAndView();
//	    
//	    KindVO kindVO = this.kindProc.read(kindno);
//	    mav.addObject("kindVO", kindVO);
//	    
//		mav.setViewName("/substances/list_by_kindno"); // /WEB-INF/views/cate/list_all.jsp
//		    
//		ArrayList<SubstancesVO> list = this.substancesProc.list_by_kindno(kindno);
//		
//		for(SubstancesVO substancesVO:list) {
//			
//	    	String title = substancesVO.getTitle();
//	    	String substance = substancesVO.getSubstance();
//	    	
//	    	title = Tool.convertChar(title);
//	    	substance = Tool.convertChar(substance);
//	    	
//	    	substancesVO.setTitle(title);
//	    	substancesVO.setSubstance(substance);
//	    }
//	    mav.addObject("list", list);
//
//	    return mav;
//	  }
	  
//	  /**
//	   *  글 목록
//	   * @param kindno
//	   * @return
//	   */
//	  @RequestMapping(value="/substances/list_by_kindno.do", method = RequestMethod.GET)
//	  public ModelAndView list_by_kindno(int kindno, String word) {
//	    ModelAndView mav = new ModelAndView();
//	    
//	    KindVO kindVO = this.kindProc.read(kindno);
//	    mav.addObject("kindVO", kindVO);
//	    
//		mav.setViewName("/substances/list_by_kindno"); // /WEB-INF/views/cate/list_all.jsp
//		
//		HashMap<String, Object> hashMap = new HashMap<String, Object>();
//		hashMap.put("kindno", kindno);
//		hashMap.put("word", word);
//		
//		ArrayList<SubstancesVO> list = this.substancesProc.list_by_kindno_search(hashMap);
//		
//		for(SubstancesVO substancesVO:list) {
//			
//	    	String title = substancesVO.getTitle();
//	    	String substance = substancesVO.getSubstance();
//	    	
//	    	title = Tool.convertChar(title);
//	    	substance = Tool.convertChar(substance);
//	    	
//	    	substancesVO.setTitle(title);
//	    	substancesVO.setSubstance(substance);
//	    }
//	    mav.addObject("list", list);
//
//	    return mav;
//	  }
	  
//	/**
//	* 특정 카테고리의 검색된 글목록
//	* http://localhost:9091/substances/list_by_kindno.do?kindno=8&word=부대찌게
//	* @return
//	*/
//	@RequestMapping(value="/substances/list_by_kindno.do", method=RequestMethod.GET)
//	public ModelAndView list_by_kindno_search(SubstancesVO substancesVO) {
//	 ModelAndView mav = new ModelAndView();
//	 
//	 KindVO kindVO = this.kindProc.read(substancesVO.getKindno());
//	 mav.addObject("kindVO", kindVO);
//	     
//	 ArrayList<SubstancesVO> list = this.substancesProc.list_by_kindno_search(substancesVO);
//	 mav.addObject("list", list);
//	 
//	 mav.setViewName("/substances/list_by_kindno_search"); // /webapp/WEB-INF/views/contents/list_by_cateno_search.jsp
//	 
//	 return mav;
//	}
	
	  @RequestMapping(value="/substances/list_by_kindno.do", method = RequestMethod.GET)
	  public ModelAndView list_by_kindno(SubstancesVO substancesVO) {
	    ModelAndView mav = new ModelAndView();
	    
	    ArrayList<SubstancesVO> list = this.substancesProc.list_by_kindno_search_paging(substancesVO);

		for(SubstancesVO vo:list) {
			
	    	String title = vo.getTitle();
	    	String substance = vo.getSubstance();
	    	
	    	title = Tool.convertChar(title);
	    	substance = Tool.convertChar(substance);
	    	
	    	vo.setTitle(title);
	    	vo.setSubstance(substance);
	   }
		mav.addObject("list", list);
		
		KindVO kindVO = kindProc.read(substancesVO.getKindno());
	  mav.addObject("kindVO", kindVO);
	    
	  HashMap<String, Object> hashMap = new HashMap<String, Object>();
	  hashMap.put("kindno", substancesVO.getKindno());
	  hashMap.put("word", substancesVO.getWord());
	    
	  int search_count = this.substancesProc.search_count(hashMap);
	  mav.addObject("search_count", search_count);
	    
	    
	  String paging = substancesProc.pagingBox(substancesVO.getKindno(), substancesVO.getNow_page(), substancesVO.getWord(), "list_by_kindno.do");
		mav.addObject("paging", paging);
		
		mav.setViewName("/substances/list_by_kindno");
		
	    return mav;
	  
	  
	  }
	  /**
	   * 조회
	   * http://localhost:9092/substances/read.do?substancesno=1
	   * @return
	   */
	  @RequestMapping(value="/substances/read.do", method = RequestMethod.GET)
	  public ModelAndView read(int substancesno) {
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("/substances/read");
	    
	    SubstancesVO substancesVO = this.substancesProc.read(substancesno);
	    KindVO kindVO = this.kindProc.read(substancesVO.getKindno());
	    mav.addObject("kindVO", kindVO);
	    
    	String title = substancesVO.getTitle();
    	String substance = substancesVO.getSubstance();
    	long size1 = substancesVO.getSize1();
    	
    	String size1_label = Tool.unit(size1);
    	title = Tool.convertChar(title);
    	substance = Tool.convertChar(substance);
    	
    	
    	substancesVO.setTitle(title);
    	substancesVO.setSubstance(substance);	   
    	substancesVO.setSize1_label(size1_label);	     
	    
	    
	    mav.addObject("substancesVO", substancesVO);
	    
	    return mav;
	  }
	  
	  /**
	   * 맵 등록/수정/삭제 폼
	   * http://localhost:9092/substances/map.do?substancesno=1
	   * @return
	   */
	  @RequestMapping(value="/substances/map.do", method=RequestMethod.GET )
	  public ModelAndView map(int substancesno) {
	    ModelAndView mav = new ModelAndView();

	    SubstancesVO substancesVO = this.substancesProc.read(substancesno); // map 정보 읽어 오기
	    mav.addObject("substancesVO", substancesVO); // request.setAttribute("contentsVO", contentsVO);

	    KindVO kindVO = this.kindProc.read(substancesVO.getKindno()); // 그룹 정보 읽기
	    mav.addObject("kindVO", kindVO); 

	    mav.setViewName("/substances/map"); // /WEB-INF/views/contents/map.jsp
	        
	    return mav;
	  }
	  
	  /**
	   * MAP 등록/수정/삭제 처리
	   * http://localhost:9092/substances/map.do
	   * @param substancesVO
	   * @return
	   */
	  @RequestMapping(value="/substances/map.do", method = RequestMethod.POST)
	  public ModelAndView map(int substancesno, String map) {
		  ModelAndView mav = new ModelAndView();
		  
		  HashMap<String, Object> hashMap=new HashMap<String, Object>();
		  hashMap.put("substancesno", substancesno);
		  hashMap.put("map", map);
		  
		  this.substancesProc.map(hashMap);
		  
		  mav.setViewName("redirect:/substances/read.do?substancesno=" + substancesno);
		  
		  return mav;
	  }
	  
	  /**
	   * 유튜브 등록/수정/삭제 폼
	   * http://localhost:9092/substances/youtube.do?substancesno=1
	   * @return
	   */
	  @RequestMapping(value="/substances/youtube.do", method=RequestMethod.GET )
	  public ModelAndView youtube(int substancesno) {
	    ModelAndView mav = new ModelAndView();

	    SubstancesVO substancesVO = this.substancesProc.read(substancesno); // map 정보 읽어 오기
	    mav.addObject("substancesVO", substancesVO); // request.setAttribute("contentsVO", contentsVO);

	    KindVO kindVO = this.kindProc.read(substancesVO.getKindno()); // 그룹 정보 읽기
	    mav.addObject("kindVO", kindVO); 

	    mav.setViewName("/substances/youtube"); // /WEB-INF/views/contents/map.jsp
	        
	    return mav;
	  }
	  
	  /**
	   * 유튜브 등록/수정/삭제 처리
	   * http://localhost:9092/substances/youtube.do
	   * @param substancesVO
	   * @return
	   */
	  @RequestMapping(value="/substances/youtube.do", method = RequestMethod.POST)
	  public ModelAndView youtube(int substancesno, String youtube) {
		  ModelAndView mav = new ModelAndView();
		  
		  if (youtube.trim().length() > 0) {  // 삭제 중인지 확인, 삭제가 아니면 youtube 크기 변경
		      youtube = Tool.youtubeResize(youtube, 640);  // youtube 영상의 크기를 width 기준 640 px로 변경
		  }
		  
		  HashMap<String, Object> hashMap=new HashMap<String, Object>();
		  hashMap.put("substancesno", substancesno);
		  hashMap.put("youtube", youtube);
		  
		  this.substancesProc.youtube(hashMap);
		  
		  mav.setViewName("redirect:/substances/read.do?substancesno=" + substancesno);
		  
		  return mav;
	  }
	  
	  /**
	   * 갤러리형 목록 만들기
	   * @param substancesVO
	   * @return
	   */
	  @RequestMapping(value="/substances/list_by_kindno_grid.do", method = RequestMethod.GET)
	  public ModelAndView list_by_kindno_grid(SubstancesVO substancesVO) {
	    ModelAndView mav = new ModelAndView();
	    
	    ArrayList<SubstancesVO> list = this.substancesProc.list_by_kindno_search_paging(substancesVO);

		for(SubstancesVO vo:list) {
			
	    	String title = vo.getTitle();
	    	String substance = vo.getSubstance();
	    	
	    	title = Tool.convertChar(title);
	    	substance = Tool.convertChar(substance);
	    	
	    	vo.setTitle(title);
	    	vo.setSubstance(substance);
	    }
		mav.addObject("list", list);
		
		KindVO kindVO = kindProc.read(substancesVO.getKindno());
	    mav.addObject("kindVO", kindVO);
	    
	    HashMap<String, Object> hashMap = new HashMap<String, Object>();
	    hashMap.put("kindno", substancesVO.getKindno());
	    hashMap.put("word", substancesVO.getWord());
	    
	    int search_count = this.substancesProc.search_count(hashMap);
	    mav.addObject("search_count", search_count);
	    
	    
	    String paging = substancesProc.pagingBox(substancesVO.getKindno(), substancesVO.getNow_page(), substancesVO.getWord(), "list_by_kindno_grid.do");
		mav.addObject("paging", paging);
		
		mav.setViewName("/substances/list_by_kindno_grid");
		
	    return mav;
	  }    
	  /**
	   * 수정폼
	   * @param kindno
	   * @return
	   */
	  @RequestMapping(value="/substances/update_text.do", method = RequestMethod.GET)
	  public ModelAndView update_text(HttpSession session, int substancesno) {
	    ModelAndView mav = new ModelAndView();
	    
	    if(visitorProc.isVisitor(session)) {
	    	SubstancesVO substancesVO = this.substancesProc.read(substancesno);
	    	mav.addObject("substancesVO", substancesVO);
	    	
	    	KindVO kindVO = this.kindProc.read(substancesVO.getKindno()); 
		   	mav.addObject("kindVO", kindVO);
		   	
		   	mav.setViewName("/substances/update_text"); 
		   	
	    } else {
	    	 mav.addObject("url", "/visitor/login_need"); // /WEB-INF/views/admin/login_need.jsp
	         mav.setViewName("redirect:/substances/msg.do");     	
	    }   
	    return mav;
	  }
	  
	  /**
	   * 수정 처리
	   * http://localhost:9091/contents/update_text.do?contentsno=1
	   * 
	   * @return
	   */
	  @RequestMapping(value = "/substances/update_text.do", method = RequestMethod.POST)
	  public ModelAndView update_text(HttpSession session, SubstancesVO substancesVO) {
	    ModelAndView mav = new ModelAndView();
	    
	    // System.out.println("-> word: " + contentsVO.getWord());
	    
	    if (this.visitorProc.isVisitor(session)) { // 관리자 로그인 확인
	      HashMap<String, Object> hashMap = new HashMap<String, Object>();
	      hashMap.put("substancesno", substancesVO.getSubstancesno());
	      hashMap.put("passwd", substancesVO.getPasswd());
	      
	      if (this.substancesProc.password_check(hashMap) == 1) { // 패스워드 일치
	        this.substancesProc.update_text(substancesVO); // 글수정  
	         
	        // mav 객체 이용
	        mav.addObject("substancesno", substancesVO.getSubstancesno());
	        mav.addObject("kindno", substancesVO.getKindno());
	        mav.setViewName("redirect:/substances/read.do"); // 페이지 자동 이동
	        
	      } else { // 패스워드 불일치
	        mav.addObject("code", "passwd_fail");
	        mav.addObject("cnt", 0);
	        mav.addObject("kindno", substancesVO.getKindno());
	        mav.addObject("url", "/substances/msg"); // msg.jsp, redirect parameter 적용
	        mav.setViewName("redirect:/substances/msg.do");  // POST -> GET -> JSP 출력
	      }
	    } else { // 정상적인 로그인이 아닌 경우 로그인 유도
	      mav.addObject("url", "/visitor/login_need"); // /WEB-INF/views/admin/login_need.jsp
	      mav.setViewName("redirect:/substances/msg.do"); 
	    }
	    
	    mav.addObject("now_page", substancesVO.getNow_page()); // POST -> GET: 데이터 분실이 발생함으로 다시하번 데이터 저장 ★
	    
	    // URL에 파라미터의 전송
	    // mav.setViewName("redirect:/contents/read.do?contentsno=" + contentsVO.getContentsno() + "&cateno=" + contentsVO.getCateno());             
	    
	    return mav; // forward
	  }
	  
	  /**
	   * 파일 수정폼
	   * @param kindno
	   * @return
	   */
	  @RequestMapping(value="/substances/update_file.do", method = RequestMethod.GET)
	  public ModelAndView update_file(HttpSession session, int substancesno) {
	    ModelAndView mav = new ModelAndView();
	    
	    if(visitorProc.isVisitor(session)) {
	    	SubstancesVO substancesVO = this.substancesProc.read(substancesno);
	    	mav.addObject("substancesVO", substancesVO);
	    	
	    	KindVO kindVO = this.kindProc.read(substancesVO.getKindno()); 
		   	mav.addObject("kindVO", kindVO);
		   	
		   	mav.setViewName("/substances/update_file"); 
		   	
	    } else {
	    	 mav.addObject("url", "/visitor/login_need"); // /WEB-INF/views/admin/login_need.jsp
	         mav.setViewName("redirect:/substances/msg.do");     	
	    }   
	    return mav;
	  }
	  
	  /**
	   * 파일 수정 처리 
	   * 
	   * @return
	   */
	  @RequestMapping(value = "/substances/update_file.do", method = RequestMethod.POST)
	  public ModelAndView update_file(HttpSession session, SubstancesVO substancesVO) {
	    ModelAndView mav = new ModelAndView();
	    
	    if (this.visitorProc.isVisitor(session)) {
	      // 삭제할 파일 정보를 읽어옴, 기존에 등록된 레코드 저장용
	    	SubstancesVO substancesVO_old = substancesProc.read(substancesVO.getSubstancesno());
	      
	      // -------------------------------------------------------------------
	      // 파일 삭제 시작
	      // -------------------------------------------------------------------
	      String file1saved = substancesVO_old.getFile1saved();  
	      String thumb1 = substancesVO_old.getThumb1();      
	      long size1 = 0;
	         
	      String upDir =  Substances.getUploadDir(); // C:/kd/deploy/resort_v2sbm3c/contents/storage/
	      
	      Tool.deleteFile(upDir, file1saved);  // 실제 저장된 파일삭제
	      Tool.deleteFile(upDir, thumb1);     // preview 이미지 삭제
	      // -------------------------------------------------------------------
	      // 파일 삭제 종료
	      // -------------------------------------------------------------------
	          
	      // -------------------------------------------------------------------
	      // 파일 전송 시작
	      // -------------------------------------------------------------------
	      String file1 = "";          // 원본 파일명 image

	      // 전송 파일이 없어도 file1MF 객체가 생성됨.
	      // <input type='file' class="form-control" name='file1MF' id='file1MF' 
	      //           value='' placeholder="파일 선택">
	      MultipartFile mf = substancesVO.getFile1MF();
	          
	      file1 = mf.getOriginalFilename(); // 원본 파일명
	      size1 = mf.getSize();  // 파일 크기
	          
	      if (size1 > 0) { // 폼에서 새롭게 올리는 파일이 있는지 파일 크기로 체크 ★
	        // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
	        file1saved = Upload.saveFileSpring(mf, upDir); 
	        
	        if (Tool.isImage(file1saved)) { // 이미지인지 검사
	          // thumb 이미지 생성후 파일명 리턴됨, wwth: 250, height: 200
	          thumb1 = Tool.preview(upDir, file1saved, 250, 200); 
	        }
	        
	      } else { // 파일이 삭제만 되고 새로 올리지 않는 경우
	        file1="";
	        file1saved="";
	        thumb1="";
	        size1=0;
	      }
	          
	      substancesVO.setFile1(file1);
	      substancesVO.setFile1saved(file1saved);
	      substancesVO.setThumb1(thumb1);
	      substancesVO.setSize1(size1);
	      // -------------------------------------------------------------------
	      // 파일 전송 코드 종료
	      // -------------------------------------------------------------------
	          
	      this.substancesProc.update_file(substancesVO); // Oracle 처리

	      mav.addObject("substancesno", substancesVO.getSubstancesno());
	      mav.addObject("kindno", substancesVO.getKindno());
	      mav.setViewName("redirect:/substances/read.do"); // request -> param으로 접근 전환
	                
	    } else {
	      mav.addObject("url", "/visitor/login_need"); // login_need.jsp, redirect parameter 적용
	      mav.setViewName("redirect:/substances/msg.do"); // GET
	    }

	    // redirect하게되면 전부 데이터가 삭제됨으로 mav 객체에 다시 저장
	    mav.addObject("now_page", substancesVO.getNow_page());
	    
	    return mav; // forward
	  }   	  
	  
	  /**
	   * 글 삭제폼
	   * @param kindno
	   * @return
	   */
	  @RequestMapping(value="/substances/delete.do", method = RequestMethod.GET)
	  public ModelAndView delete(HttpSession session, int substancesno) {
	    ModelAndView mav = new ModelAndView();
	    
	    if(visitorProc.isVisitor(session)) {
	    	SubstancesVO substancesVO = this.substancesProc.read(substancesno);
	    	mav.addObject("substancesVO", substancesVO);
	    	
	    	KindVO kindVO = this.kindProc.read(substancesVO.getKindno()); 
		   	mav.addObject("kindVO", kindVO);
		   	
		   	mav.setViewName("/substances/delete"); 
		   	
	    } else {
	    	 mav.addObject("url", "/visitor/login_need"); // /WEB-INF/views/admin/login_need.jsp
	         mav.setViewName("redirect:/substances/msg.do");     	
	    }   
	    return mav;
	  }
	  
	  /**
	   * 글 삭제 처리 
	   * 
	   * @return
	   */
	  @RequestMapping(value = "/substances/delete.do", method = RequestMethod.POST)
	  public ModelAndView delete(SubstancesVO substancesVO) {
	    ModelAndView mav = new ModelAndView();
	    
	    
	      // 삭제할 파일 정보를 읽어옴, 기존에 등록된 레코드 저장용
	    SubstancesVO substancesVO_old = substancesProc.read(substancesVO.getSubstancesno());
	      
	      // -------------------------------------------------------------------
	      // 파일 삭제 시작
	      // -------------------------------------------------------------------
	     String file1saved = substancesVO_old.getFile1saved();  
	     String thumb1 = substancesVO_old.getThumb1();      
	         
	     String upDir =  Substances.getUploadDir();
	      
	     Tool.deleteFile(upDir, file1saved);  // 실제 저장된 파일삭제
	     Tool.deleteFile(upDir, thumb1);     // preview 이미지 삭제
	      // -------------------------------------------------------------------
	      // 파일 삭제 종료
	      // -------------------------------------------------------------------
	     this.substancesProc.delete(substancesVO.getSubstancesno());
	      
	     int now_page = substancesVO.getNow_page();
	      
		 HashMap<String, Object> hashMap = new HashMap<String, Object>();
		 hashMap.put("kindno", substancesVO.getKindno());
		 hashMap.put("word", substancesVO.getWord());
	  
		 if(substancesProc.search_count(hashMap)%Substances.RECORD_PER_PAGE == 0) {
			 now_page = now_page - 1;
			 if(now_page<1) {
				 now_page=1;
			 }
		 }
		 mav.addObject("kindno", substancesVO.getKindno());
		 mav.addObject("now_page", now_page);
		 mav.setViewName("redirect:/substances/list_by_kindno.do"); 
	  
		 return mav;
	  
	  
	  }
	  
	  /**
	   * Gallery 전체 이미지 출력
	   * http://localhost:9091/contents/list_all_gallery.do
	   * @return
	   */
	  @RequestMapping(value="/substances/list_all_gallery.do", method = RequestMethod.GET)
	  public ModelAndView list_all_gallery(HttpSession session) {
	    ModelAndView mav = new ModelAndView();
	    
	    if (this.grammerProc.isGrammer(session) == true) {
	      mav.setViewName("/substances/list_all_gallery"); // /WEB-INF/views/contents/list_all_gallery.jsp
	      
	      ArrayList<SubstancesVO> list = this.substancesProc.list_all();
	      mav.addObject("list", list);
	      
	    } else {
	      mav.setViewName("/grammer/login_need"); // /WEB-INF/views/admin/login_need.jsp
	      
	    }
	    
	    return mav;
	  }
	  
	  
	  
}

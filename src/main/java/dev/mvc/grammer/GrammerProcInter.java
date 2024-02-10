package dev.mvc.grammer;

import javax.servlet.http.HttpSession;

public interface GrammerProcInter {
	  /**
	   * 로그인
	   * @param AdminVO
	   * @return
	   */
	  public int login(GrammerVO grammerVO);
	  
	  /**
	   * 회원 정보
	   * @param String
	   * @return
	   */
	  public GrammerVO read_by_id(String id);
	  
	  
	  public GrammerVO read(int grammerno);
	  
	  /**
	   * 관리자 로그인 체크
	   * @param session
	   * @return
	   */
	  public boolean isGrammer(HttpSession session);
}

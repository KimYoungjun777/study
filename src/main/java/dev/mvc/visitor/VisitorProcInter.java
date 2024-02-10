package dev.mvc.visitor;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

public interface VisitorProcInter {
	/**
	 * 아이디 중복 체크
	 * @param id
	 * @return
	 */
	public int checkID(String id);
	
	 /**
   * id등록
   * @param visitorVO
   * @return
   */
  public int create(VisitorVO visitorVO);
  
  /**
   * 전체목록
   * @return
   */
  public ArrayList<VisitorVO> list();
  
  /**
   * visitorno로 조회
   * @param visitorno
   * @return
   */
  public VisitorVO read(int visitorno);
  
  /**
   * id로 조회
   * @param id
   * @return
   */
  public VisitorVO readbyId(String id);
  
  /**
   * 로그인된 회원 계정인지 검사합니다.
   * @param session
   * @return true: 사용자
   */
  public boolean isVisitor(HttpSession session);

  /**
   * 로그인된 회원 관리자 계정인지 검사합니다.
   * @param session
   * @return true: 사용자
   */
  public boolean isVisitorGrammer(HttpSession session);
  
  /**
   * 수정
   * @param visitorVO
   * @return
   */
  public int update(VisitorVO visitorVO);
  
  /**
   * 삭제
   * @param visitorno
   * @return
   */
  public int delete(int visitorno);
  
  /**
   * 로그인 처리
   * @param map
   * @return
   */
  public int login(HashMap<String, Object>map);
  
  /**
   * 현재 패스워드 검사
   * @param map
   * @return 0: 일치하지 않음, 1: 일치함
   */
  public int passwd_check(HashMap<String, Object> map);
  
  /**
   * 패스워드 변경
   * @param map
   * @return 변경된 패스워드 갯수
   */
  public int passwd_update(HashMap<String, Object> map);
  
}

package dev.mvc.kind;

import java.util.ArrayList;

public interface KindProcInter {
	/**
	 * 등록, 추상 메소드
	 */
	
	public int create(KindVO kindVO);
	
	/**
	 * 전체목록
	 * @return
	 */
	public ArrayList<KindVO> list_all(); 
	
	/**
	 * 조회
	 * @param
	 * @return
	 */
	
	public KindVO read(int kindno);
	
	/**
	   * 수정   
	   * @param cateVO
	   * @return 수정된 레코드 갯수
	   */
	public int update(KindVO kindVO);
	
	/**
	 * 삭제
	 * @param kindno
	 * @return
	 */
	public int delete(int kindno);
	
	  /**
	   * 수정 우선 순위 높임
	   * @param cateVO
	   * @return 수정된 레코드 갯수
	   */
	  public int update_seqno_up(int kindno);
	  
	  /**
	   * 수정 우선 순위 낮춤
	   * @param cateVO
	   * @return 수정된 레코드 갯수
	   */
	  public int update_seqno_down(int kindno);
	  
	  /**
	   * 수정 종류 공개 설정
	   * @param kindno
	   * @return
	   */
	  public int update_visible_y(int kindno);
	  
	  /**
	   * 수정 종류 비공개 설정
	   * @param kindno
	   * @return
	   */
	  public int update_visible_n(int kindno);	
	  
	  /**
	   * 공개 비공개 리스트
	   * @param 
	   * @return
	   */
	  public ArrayList<KindVO> list_all_y(); 	  
	  

	  
	  
}

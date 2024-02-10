package dev.mvc.kind;

import java.util.ArrayList;

public interface KindDAOInter {
	
	public int create(KindVO kindVO);
	
	public ArrayList<KindVO> list_all(); 
	
	public KindVO read(int kindno);
	
	public int update(KindVO kindVO);
	
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

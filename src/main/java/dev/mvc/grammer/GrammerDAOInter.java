package dev.mvc.grammer;


public interface GrammerDAOInter {
	
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
}

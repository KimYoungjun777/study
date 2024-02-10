package dev.mvc.substances;

import java.util.ArrayList;
import java.util.HashMap;

public interface SubstancesProcInter {
	/**
	 * 등록, 추상
	 * @param substancesVO
	 * @return
	 */
	public int create(SubstancesVO substancesVO);
	
	/**
	 * 목록
	 * @return
	 */
	public ArrayList<SubstancesVO> list_all();
	
	/**
	 * 종류별 목록
	 * @param kinddo
	 * @return
	 */
	public ArrayList<SubstancesVO> list_by_kindno(int kindno);
	
	/**
	 * 조회
	 * @param substancesno
	 * @return
	 */
	public SubstancesVO read(int substancesno);	
	
	/**
	 * 지도 등록 및 수정 및 삭제
	 * @param map
	 * @return
	 */
	public int map(HashMap<String, Object> map);
	
	/**
	 * 유튜브 등록 및 수정 및 삭제
	 * @param map
	 * @return
	 */
	public int youtube(HashMap<String, Object> youtube);	
	
	/**
	 * 검색 기능
	 * @param hashMap
	 * @return
	 */
	public ArrayList<SubstancesVO> list_by_kindno_search(HashMap hashMap);
	
	/**
	 * 검색 카운트
	 * @param hashMap
	 * @return
	 */
	public int search_count(HashMap hashMap);
	
	public ArrayList<SubstancesVO> list_by_kindno_search_paging(SubstancesVO substancesVO);
	
	public String pagingBox(int kindno, int now_page, String word, String list_file);
	
	/**
	 * 비밀번호 체킹
	 * @param hashMap
	 * @return
	 */
	public int password_check(HashMap hashMap);
	
	/**
	 * 글 수정
	 * @param substancesVO
	 * @return
	 */
	public int update_text(SubstancesVO substancesVO);
	
	/**
	 * 파일 수정 및 삭제
	 * @param substancesVO
	 * @return
	 */
	public int update_file(SubstancesVO substancesVO); 
	
	/**
	 * 글 삭제
	 * @param substancesno
	 * @return
	 */
	public int delete(int substancesno);
	
	/**
	 * 종류에 해당하는 글의 갯수
	 * @param kindno
	 * @return
	 */
	public int count_by_kindno(int kindno);
	
	/**
	 * 종류에 해당하는 글 삭제
	 * @param kindno
	 * @return
	 */
	public int delete_by_kindno(int kindno);
}

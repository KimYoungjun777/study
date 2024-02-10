package dev.mvc.substances;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.substances.SubstancesProc")
public class SubstancesProc implements SubstancesProcInter {
	@Autowired
	private SubstancesDAOInter substancesDAO;
	@Override
	public int create(SubstancesVO substancesVO) {
		int cnt =  this.substancesDAO.create(substancesVO);
		return cnt;
	}
	@Override
	public ArrayList<SubstancesVO> list_all() {
		ArrayList<SubstancesVO> list = this.substancesDAO.list_all();
		return list;
	}
	@Override
	public ArrayList<SubstancesVO> list_by_kindno(int kindno) {
		ArrayList<SubstancesVO> list = this.substancesDAO.list_by_kindno(kindno);
		return list;
	}
	@Override
	public SubstancesVO read(int substancesno) {
		SubstancesVO substancesVO = this.substancesDAO.read(substancesno);
		return substancesVO;
	}
	@Override
	public int map(HashMap<String, Object> map) {
		int cnt = this.substancesDAO.map(map);
		return cnt;
	}
	@Override
	public int youtube(HashMap<String, Object> youtube) {
		int cnt = this.substancesDAO.youtube(youtube);
		return cnt;
	}
	@Override
	public ArrayList<SubstancesVO> list_by_kindno_search(HashMap hashMap) {
		ArrayList<SubstancesVO> list = this.substancesDAO.list_by_kindno_search(hashMap);
		return list;
	}
	@Override
	public int search_count(HashMap hashMap) {
		int cnt = this.substancesDAO.search_count(hashMap);
		return cnt;
	}
	
	@Override
	public ArrayList<SubstancesVO> list_by_kindno_search_paging(SubstancesVO substancesVO){
		
		int begin_of_page = (substancesVO.getNow_page() - 1) * Substances.RECORD_PER_PAGE;
		int start_num = begin_of_page + 1;
		int end_num = begin_of_page + Substances.RECORD_PER_PAGE;
		
		substancesVO.setStart_num(start_num);
		substancesVO.setEnd_num(end_num);
		
		ArrayList<SubstancesVO> list = this.substancesDAO.list_by_kindno_search_paging(substancesVO);
		return list;
	}
	
	@Override
	public String pagingBox(int kindno, int now_page, String word, String list_file) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("kindno", kindno);
		hashMap.put("word", word);
		
		int search_count = this.substancesDAO.search_count(hashMap);
		
	    // 전체 페이지 수: (double)1/10 -> 0.1 -> 1 페이지, (double)12/10 -> 1.2 페이지 -> 2 페이지
	    int total_page = (int)(Math.ceil((double)search_count / Substances.RECORD_PER_PAGE));
	    // 전체 그룹  수: (double)1/10 -> 0.1 -> 1 그룹, (double)12/10 -> 1.2 그룹-> 2 그룹
        int total_grp = (int)(Math.ceil((double)total_page / Substances.PAGE_PER_BLOCK)); 
        // 현재 그룹 번호: (double)13/10 -> 1.3 -> 2 그룹
        int now_grp = (int)(Math.ceil((double)now_page / Substances.PAGE_PER_BLOCK)); 		
		
		int start_page = ((now_grp - 1) * Substances.PAGE_PER_BLOCK) + 1;
		int end_page = (now_grp * Substances.PAGE_PER_BLOCK);
		
		StringBuffer str = new StringBuffer();
	    str.append("<style type='text/css'>"); 
	    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}"); 
	    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}"); 
	    str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color:black; font-size: 1em;}"); 
	    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}"); 
	    str.append("  .span_box_1{"); 
	    str.append("    text-align: center;");    
	    str.append("    font-size: 1em;"); 
	    str.append("    border: 1px;"); 
	    str.append("    border-style: solid;"); 
	    str.append("    border-color: #cccccc;"); 
	    str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
	    str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
	    str.append("  }"); 
	    str.append("  .span_box_2{"); 
	    str.append("    text-align: center;");    
	    str.append("    background-color: #668db4;"); 
	    str.append("    color: #FFFFFF;"); 
	    str.append("    font-size: 1em;"); 
	    str.append("    border: 1px;"); 
	    str.append("    border-style: solid;"); 
	    str.append("    border-color: #cccccc;"); 
	    str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
	    str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
	    str.append("  }"); 
	    str.append("</style>"); 
	    str.append("<DIV id='paging'>"); 		
	    
	    int _now_page = (now_grp - 1) * Substances.PAGE_PER_BLOCK; 
	    if (now_grp >= 2){ // 현재 그룹번호가 2이상이면 페이지수가 11페이지 이상임으로 이전 그룹으로 갈수 있는 링크 생성 
	        str.append("<span class='span_box_1'><A href='"+list_file+"?&word="+word+"&now_page="+_now_page+"&kindno="+kindno+"'>이전</A></span>"); 
	    } 
	    
	    for(int i=start_page; i<=end_page; i++){ 
	        if (i > total_page){ // 마지막 페이지를 넘어갔다면 페이 출력 종료
	          break; 
	        } 
	      
	        if (now_page == i){ // 목록에 출력하는 페이지가 현재페이지와 같다면 CSS 강조(차별을 둠)
	          str.append("<span class='span_box_2'>"+i+"</span>"); // 현재 페이지, 강조 
	        }else{
	          // 현재 페이지가 아닌 페이지는 이동이 가능하도록 링크를 설정
	          str.append("<span class='span_box_1'><A href='"+list_file+"?word="+word+"&now_page="+i+"&kindno="+kindno+"'>"+i+"</A></span>");   
	        } 
	     } 	    
	     _now_page = (now_grp * Substances.PAGE_PER_BLOCK)+1; //  최대 페이지수 + 1 
	     if (now_grp < total_grp){ 
	       str.append("<span class='span_box_1'><A href='"+list_file+"?&word="+word+"&now_page="+_now_page+"&kindno="+kindno+"'>다음</A></span>"); 
	     } 
	     str.append("</DIV>"); 
	      
      return str.toString(); 
	  }
	@Override
	public int password_check(HashMap hashMap) {
		int cnt = this.substancesDAO.password_check(hashMap);
		return cnt;
	}
	@Override
	public int update_text(SubstancesVO substancesVO) {
		int cnt = this.substancesDAO.update_text(substancesVO);
		return cnt;
	}
	@Override
	public int update_file(SubstancesVO substancesVO) {
		int cnt = this.substancesDAO.update_file(substancesVO);
		return cnt;
	}
	@Override
	public int delete(int substancesno) {
		int cnt = this.substancesDAO.delete(substancesno);
		return cnt;
	}
	@Override
	public int count_by_kindno(int kindno) {
		int cnt = this.substancesDAO.count_by_kindno(kindno);
		return cnt;
	}
	@Override
	public int delete_by_kindno(int kindno) {
		int cnt = this.substancesDAO.delete_by_kindno(kindno);
		return cnt;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

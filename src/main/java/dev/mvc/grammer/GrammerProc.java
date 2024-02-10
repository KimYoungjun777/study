package dev.mvc.grammer;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.grammer.GrammerProc")
public class GrammerProc implements GrammerProcInter {
	@Autowired
	private GrammerDAOInter grammerDAO;
	
	@Override
	public int login(GrammerVO grammerVO) {
		int cnt = this.grammerDAO.login(grammerVO);
		return cnt;
	}

	@Override
	public GrammerVO read_by_id(String id) {
		GrammerVO grammerVO = this.grammerDAO.read_by_id(id);
		return grammerVO;
	}

	@Override
	public GrammerVO read(int grammerno) {
		GrammerVO grammerVO = this.grammerDAO.read(grammerno);
		return grammerVO;
	}
	
	  @Override
	  public boolean isGrammer(HttpSession session) {
	    boolean grammer = false;
	    
	    if (session != null) {
	      String grammer_id = (String)session.getAttribute("grammer_id");
	      
	      if (grammer_id != null) {
	        grammer = true;
	      }
	    }
	    
	    return grammer;
	  }

}

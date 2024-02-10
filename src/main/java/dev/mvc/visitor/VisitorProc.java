package dev.mvc.visitor;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.grammer.GrammerProcInter;
import dev.mvc.kind.KindProcInter;
import dev.mvc.substances.SubstancesProcInter;

@Component("dev.mvc.visitor.VisitorProc")
public class VisitorProc implements VisitorProcInter {
	@Autowired
	private VisitorDAOInter visitorDAO;

	@Override
	public int checkID(String id) {
		int cnt = this.visitorDAO.checkID(id);
		return cnt;
	}

  @Override
  public int create(VisitorVO visitorVO) {
    int cnt = this.visitorDAO.create(visitorVO);
    return cnt;
  }

  @Override
  public ArrayList<VisitorVO> list() {
    ArrayList<VisitorVO> list = this.visitorDAO.list();
    return list;
  }

  @Override
  public VisitorVO read(int visitorno) {
    VisitorVO visitorVO = this.visitorDAO.read(visitorno);
    return visitorVO;
  }

  @Override
  public VisitorVO readbyId(String id) {
    VisitorVO visitorVO = this.visitorDAO.readbyId(id);
    return visitorVO;
  }

  @Override
  public boolean isVisitor(HttpSession session) {
    boolean sw = false; // 로그인하지 않은 것으로 초기화
    int grade = 99;
    
    // System.out.println("-> grade: " + session.getAttribute("grade"));
    if (session != null) {
      String id = (String)session.getAttribute("id");
      if (session.getAttribute("grade") != null) {
        grade = (int)session.getAttribute("grade");
      }
      
      if (id != null && grade <= 20){ // 관리자 + 회원
        sw = true;  // 로그인 한 경우
      }
    }
    
    return sw;
  }

  @Override
  public boolean isVisitorGrammer(HttpSession session) {
    boolean sw = false; // 로그인하지 않은 것으로 초기화
    int grade = 99;
    
    // System.out.println("-> grade: " + session.getAttribute("grade"));
    if (session != null) {
      String id = (String)session.getAttribute("id");
      if (session.getAttribute("grade") != null) {
        grade = (int)session.getAttribute("grade");
      }
      
      if (id != null && grade <= 10){ // 관리자 
        sw = true;  // 로그인 한 경우
      }
    }
    
    return sw;
  }

  @Override
  public int update(VisitorVO visitorVO) {
    int cnt = this.visitorDAO.update(visitorVO);
    return cnt;
  }

  @Override
  public int delete(int visitorno) {
    int cnt = this.visitorDAO.delete(visitorno);
    return cnt;
  }

  @Override
  public int login(HashMap<String, Object> map) {
    int cnt = this.visitorDAO.login(map);
    return cnt;
  }

  @Override
  public int passwd_check(HashMap<String, Object> map) {
    int cnt = this.visitorDAO.passwd_check(map);
    return cnt;
  }

  @Override
  public int passwd_update(HashMap<String, Object> map) {
    int cnt = this.visitorDAO.passwd_update(map);
    return cnt;
  }
  
  
  
	
	

}

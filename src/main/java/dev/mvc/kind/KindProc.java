package dev.mvc.kind;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.kind.KindProc")
public class KindProc implements KindProcInter {
	@Autowired
	private KindDAOInter kindDAO;
	
	@Override
	public int create(KindVO kindVO) {
		int cnt = this.kindDAO.create(kindVO);
		return cnt;
	}

	@Override
	public ArrayList<KindVO> list_all() {
		ArrayList<KindVO> list = this.kindDAO.list_all();
		return list;
	}

	@Override
	public KindVO read(int kindno) {
		KindVO kindVO = this.kindDAO.read(kindno);
		return kindVO;
	}

	@Override
	public int update(KindVO kindVO) {
		int cnt = this.kindDAO.update(kindVO);
		return cnt;
	}

	@Override
	public int delete(int kindno) {
		int cnt = this.kindDAO.delete(kindno);
		return cnt;
	}

	@Override
	public int update_seqno_up(int kindno) {
		int cnt = this.kindDAO.update_seqno_up(kindno);
		return cnt;
	}

	@Override
	public int update_seqno_down(int kindno) {
		int cnt = this.kindDAO.update_seqno_down(kindno);
		return cnt;
	}

	@Override
	public int update_visible_y(int kindno) {
		int cnt = this.kindDAO.update_visible_y(kindno);
		return cnt;
	}

	@Override
	public int update_visible_n(int kindno) {
		int cnt = this.kindDAO.update_visible_n(kindno);
		return cnt;
	}

	@Override
	public ArrayList<KindVO> list_all_y() {
		ArrayList<KindVO> list = this.kindDAO.list_all_y();
		return list;
	}


	
	
	
	
	
}

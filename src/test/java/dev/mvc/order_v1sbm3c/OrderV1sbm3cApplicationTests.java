package dev.mvc.order_v1sbm3c;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import dev.mvc.kind.*;
@SpringBootTest
class OrderV1sbm3cApplicationTests {
	@Autowired
	private KindDAOInter kindDAO;
	
	@Autowired
	@Qualifier("dev.mvc.kind.KindProc")
	private KindProcInter kindProc;
	@Test
	void contextLoads() {
	}
	
	
	@Test
	public void testCreate() {
		KindVO kindVO = new KindVO();
		kindVO.setTitle("분식");
		
	int cnt = this.kindDAO.create(kindVO);
	System.out.println("-> cnt: " + cnt);
	 
//	int cnt = this.kindProc.create(kindVO);
//	System.out.println("-> cnt: " + cnt);
	}
}

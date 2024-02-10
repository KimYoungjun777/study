package dev.mvc.order_v1sbm3c;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.kind.KindProc;
import dev.mvc.kind.KindVO;

@Controller
public class HomeCont {
	@Autowired
	@Qualifier("dev.mvc.kind.KindProc")
	private KindProc kindProc;
	public HomeCont() {
		System.out.println("-> HomeCont Created.");
	}
	
	
	// http://localhost:9092/
	@RequestMapping(value= {"/","","index.do"}, method = RequestMethod.GET)
	public ModelAndView home() {
		System.out.println("-> home()");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/index"); // WEB-INF/views/index.jsp
		
		return mav;
	}
	// http://localhost:9092/menu/top.do
		  @RequestMapping(value= {"/menu/top.do"}, method=RequestMethod.GET)
		  public ModelAndView top() {
		    ModelAndView mav = new ModelAndView();	
		    
		    ArrayList<KindVO>list_top = this.kindProc.list_all_y();
		    mav.addObject("list_top", list_top);
		    
		    mav.setViewName("/menu/top"); // /WEB-INF/views/menu/top.jsp
		    
		    return mav;
		  }
}

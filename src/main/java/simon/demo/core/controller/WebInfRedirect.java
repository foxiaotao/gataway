package simon.demo.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 专门视图转发的控制器
 * @author 小涛
 */
@Controller
@RequestMapping(value="/gene")
public class WebInfRedirect {
	
	@RequestMapping("/{viewName}.do")
	public String redict(@PathVariable("viewName") String viewName){
		System.out.println(viewName);
		return viewName;
	}
	
}

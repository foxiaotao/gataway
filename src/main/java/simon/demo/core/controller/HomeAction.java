package simon.demo.core.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import simon.demo.core.bean.MenuPrivalige;
import simon.demo.core.bean.MenuTree;
import simon.demo.core.service.MenuPrivaligeService;
import simon.demo.core.shiro.ShiroUser;
import simon.demo.core.util.TreeUtil;

@Controller
@RequestMapping(value="/Home")
public class HomeAction {
	@Autowired
	private MenuPrivaligeService menuPrivaligeServiceImpl;
    
    @RequestMapping(value="/index.do")
    public ModelAndView index(HttpServletRequest request,String id) throws Exception {
    	ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    	List<MenuPrivalige> allMenu = menuPrivaligeServiceImpl.selectAllMenu(user);
    	List<MenuTree> menuTree = TreeUtil.parseMenuTree(allMenu);
    	ModelAndView model = new ModelAndView();
		model.addObject("menuTree", menuTree);
		model.setViewName("home");
		return model;
    }
}
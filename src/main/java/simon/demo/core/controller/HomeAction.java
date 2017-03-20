package simon.demo.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;

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
		model.setViewName("base/main");
		return model;
    }
    @RequestMapping(value="/welcome.do")
    public ModelAndView welcome(HttpServletRequest request) throws Exception {
    	ModelAndView model = new ModelAndView();
    	model.setViewName("base/welcome");
    	return model;
    }
    
    
    @RequestMapping(value="/menuList.do")
    public ResponseEntity menuList(@RequestParam(value="node",required = false)String node){
    	ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    	List<MenuPrivalige> allMenu = menuPrivaligeServiceImpl.selectAllMenu(user);
    	List<MenuTree> menuTree = TreeUtil.parseMenuTree(allMenu);
    	return new ResponseEntity<>(menuTree,HttpStatus.OK);
    }
    @RequestMapping(value="/menuList2.do",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String menuList2(@RequestParam(value="node",required = false)String node){
    	ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    	List<MenuPrivalige> allMenu = menuPrivaligeServiceImpl.selectAllMenu(user);
    	List<MenuTree> menuTree = TreeUtil.parseMenuTree(allMenu);
    	return JSONArray.toJSON(menuTree).toString();
    }
}
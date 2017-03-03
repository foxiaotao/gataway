/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package simon.demo.core.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import simon.demo.core.bean.MenuPrivalige;
//import simon.demo.core.service.MenuPrivaligeService;
//import simon.demo.core.service.impl.LoginServiceImpl;
import simon.demo.core.util.IpUtil;


/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，
 * 
 * 真正登录的POST请求由Filter完成,
 * 
 * @author calvin
 */
@Controller
public class LoginAction {
	
	private final static Log logger = LogFactory.getLog(LoginAction.class);

	/**
	 * 2015-4-16 11:11:56
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/login_tologin.do", method = RequestMethod.GET)
	public ModelAndView loginNew(HttpServletRequest req) {
		ModelAndView model = new ModelAndView();
		String ip = IpUtil.getIpAddr(req);
		model.addObject("ip", ip);
		model.setViewName("login");
		return model;
	}
	
	//刷新主页
	@RequestMapping(value = "/login_login.do", method = RequestMethod.GET)
	public ModelAndView loginRefresh(HttpServletRequest req) {
		ModelAndView model = new ModelAndView();
		String ip = IpUtil.getIpAddr(req);
		model.addObject("ip", ip);
		Subject currentUser = SecurityUtils.getSubject();
		try {
			if (!currentUser.isAuthenticated()){
				//已登录
				model.setViewName("login");
			}else{
				model.setViewName("home");
			}
		}catch (Exception e) {
			logger.error(e.getMessage());
			req.setAttribute("error", e.getClass().getSimpleName());
			model.setViewName("error");
			return model;
		}
		return model;
	}
	/**
	 * 2015-4-16 11:11:56
	 * 
	 * @param req
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/login_login.do", method = RequestMethod.POST)
	public void login(HttpServletRequest req,HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView();
		String ip = IpUtil.getIpAddr(req);

		String username = req.getParameter("username");
		//MD5加密
//		String password = CipherUtil.generatePassword(req.getParameter("password"));
		String password = req.getParameter("password");
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		
		Subject currentUser = SecurityUtils.getSubject();
		try {
			if (!currentUser.isAuthenticated()){
				token.setRememberMe(true);
				currentUser.login(token);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			req.setAttribute("error", e.getClass().getSimpleName());
			model.setViewName("login");
//			return model;
		}
		
		model.addObject("ip", ip);
		model.setViewName("home");
		
		response.sendRedirect("Home/index.do");
		
//		return model;
	}



	@RequestMapping(value = "login_loginout.do", method = RequestMethod.GET)
	public String loginout(HttpSession session) {
		session.invalidate();
		return "login";
	}

	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public ModelAndView index() throws IOException {
		ModelAndView model = new ModelAndView();
		
		//在cpan建立文件流
		
//		File f = new File("/usr/suntao/nginx_test.txt");
//		File f = new File("c://nginx_test.txt");
//		OutputStream out = new FileOutputStream(f,true); 
//		
//		String name = "你的名字 \r\n";
//		  byte b[] = name.getBytes();  
//		  out.write(b);
//		  byte b2[] = name.getBytes();
//		  out.write(b2);  
//		  out.close();
//		
//		System.out.println("a");
//		if(loginServiceImpl.login() !=null){
//			int a = 1;
			model.setViewName("index");
//		}
//		else{
//			model.setViewName("error");
//		}
		return model;
	}
}

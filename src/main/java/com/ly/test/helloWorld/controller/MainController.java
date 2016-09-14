package com.ly.test.helloWorld.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ly.test.helloWorld.util.StringUtil;

@Controller
public class MainController {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/")
	public ModelAndView index1() {
		String username = (String) request.getSession().getAttribute("username");
		if (StringUtil.isBlank(username)) {
			log.info("redirected to login");
			return new ModelAndView("redirect:/login");
		} else {
			log.info("redirected to main");
			return new ModelAndView("redirect:/main");
		}
	}
	
	@RequestMapping("/login")
	public ModelAndView index2() {
		return new ModelAndView("login");
	}
	
	@ResponseBody
	@RequestMapping("/signIn")
	public String signIn() {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if ("1234".equals(username) && "4321".equals(password)) {
			request.getSession().setAttribute("username", username);
			return "success";
		}
		return "error";
	}
	
	@ResponseBody
	@RequestMapping("signOut")
	public String signOut() {
		request.getSession().removeAttribute("username");
		return "success";
	}
	
	@RequestMapping("/main")
	public ModelAndView main() {
		String username = (String) request.getSession().getAttribute("username");
		if (StringUtil.isBlank(username)) {
			log.info("redirected to login");
			return new ModelAndView("redirect:/login");
		} else {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("username", username);
			return new ModelAndView("main", model);
		}
	}
}
package com.ly.test.helloWorld.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ly.test.helloWorld.util.StringUtil;

@Controller
public class MainController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/")
	public void index1(HttpServletResponse response) {
		log.info("redirected");
		try {
			response.sendRedirect("login");
		} catch(Exception e) {
			log.error("send redirect error:", e);
		}
	}
	
	@RequestMapping("/login")
	public ModelAndView index2() {
		return new ModelAndView("login");
	}
	
	@ResponseBody
	@RequestMapping("/signIn")
	public String signIn(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if ("1234".equals(username) && "4321".equals(password)) {
			request.getSession().setAttribute("username", username);
			return "success";
		}
		return "error";
	}
	
	@RequestMapping("/main")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		String username = (String) request.getSession().getAttribute("username");
		if (StringUtil.isBlank(username)) {
			try {
				response.sendRedirect("login");
			} catch(Exception e) {
				log.error("send redirect error:", e);
			}
		} else {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("username", username);
			return new ModelAndView("main", model);
		}
		return null;
	}
}
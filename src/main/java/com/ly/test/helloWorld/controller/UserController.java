package com.ly.test.helloWorld.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	@ResponseBody
	@RequestMapping("/queryUser")
	public Map<String, Object> queryUser() {
		int page = Integer.parseInt(request.getParameter("page"));
		int size = Integer.parseInt(request.getParameter("size"));
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i=0; i < size; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("username", "user" + (page * size + i + 1));
			map.put("password", "123456");
			list.add(map);
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("size", size);
		result.put("count", 10);
		result.put("page", page);
		result.put("data", list);
		return result;
	}
}

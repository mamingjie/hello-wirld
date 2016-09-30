package com.ly.test.helloWorld.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ly.test.helloWorld.util.JSONUtils;

import net.sf.json.JSONObject;

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
		Map<String, Object> result = null;
		try {
			String pageStr = null;
			String sizeStr = null;
			String jsonStr = JSONUtils.parseRequest(request);
			if (StringUtils.hasLength(jsonStr)) {
				JSONObject jsonObject = JSONObject.fromObject(jsonStr);
				pageStr = jsonObject.getString("page");
				sizeStr = jsonObject.getString("size");
			} else {
				pageStr = request.getParameter("page");
				sizeStr = request.getParameter("size");
			}
			int page = Integer.parseInt(pageStr);
			int size = Integer.parseInt(sizeStr);
			
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for (int i=0; i < size; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("username", "user" + (page * size + i + 1));
				map.put("password", "123456");
				list.add(map);
			}
			
			result = new HashMap<String, Object>();
			result.put("size", size);
			result.put("count", 10);
			result.put("page", page);
			result.put("data", list);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}

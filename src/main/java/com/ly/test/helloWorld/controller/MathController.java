package com.ly.test.helloWorld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MathController {

	@RequestMapping("/math")
	public ModelAndView index2() {
		return new ModelAndView("math");
	}
}

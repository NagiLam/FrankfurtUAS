package com.insulin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
/*
 * author: Crunchify.com
 * 
 */
 
@Controller
public class Insulin {
 
	@RequestMapping("/welcome")
	public ModelAndView helloWorld() {
 
		return new ModelAndView("welcome");
	}
}
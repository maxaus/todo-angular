package com.baev.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * UI controller to manage  main page.
 *
 * @author Maxim Baev
 */
@Controller
@RequestMapping("/*")
public class IndexController {

	@RequestMapping(method = RequestMethod.GET)
	public String getIndexPage() {
		return "view/index.html";
	}
}

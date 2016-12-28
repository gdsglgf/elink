package com.elink.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.elink.persistence.mapper.PageMapper;
import com.elink.persistence.model.Page;

@Controller
@RequestMapping(value = "/")
public class DefaultController {
	private static Logger log = Logger.getLogger(DefaultController.class);
	
	/**
	 * 显示应用程序的首页.
	 * @param request - HttpRequest对象
	 * @param response - HttpResponse对象
	 * @return 一个包含首页内容的ModelAndView对象
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView indexView(
			HttpServletRequest request, HttpServletResponse response) {
		log.debug(String.format("%s get index page.", request.getRemoteAddr()));
		ModelAndView view = new ModelAndView("index");
		return view;
	}
	
	@RequestMapping(value = "/page/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Page getPage(@PathVariable("id") long id) {
		return pageMapper.getById(id);
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@ResponseBody
	public Page getPage2(@RequestParam("id") long id) {
		return pageMapper.getById(id);
	}
	
	@Autowired
	private PageMapper pageMapper;
}

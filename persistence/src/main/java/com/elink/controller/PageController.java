package com.elink.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elink.persistence.mapper.PageMapper;

@Controller
@RequestMapping(value = "/page")
public class PageController {
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, String>> queryData(Map<String, String> params) {
		log.debug(params);
		System.out.println(params.getClass().getName());
		return pageMapper.findAll(params);
	}
	
	@RequestMapping(value = "/query2")
	@ResponseBody
	public Map<String, Object> queryData2(@RequestParam(value="offset", required=false, defaultValue="0")int offset,
			@RequestParam(value="limit", required=false, defaultValue="10")int limit) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", pageMapper.countAll());
		result.put("rows", pageMapper.findAll2(offset, limit));
		return result;
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String pageView() {
		return "data";
	}
	
	private static Logger log = Logger.getLogger(PageController.class);

	@Autowired
	private PageMapper pageMapper;
}

package com.huemedia.cms.web.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

	@RequestMapping(value = "/document/home", method = RequestMethod.GET)
	public ModelAndView home(Locale locale) {
		final ModelAndView mav = new ModelAndView("test/home");
		
		return mav;
	}
	
	@RequestMapping(value = "/document/add", method = RequestMethod.GET)
	public ModelAndView mou(Locale locale) {
		final ModelAndView mav = new ModelAndView("test/mou");
		
		return mav;
	}
	@RequestMapping(value = "/document/edit", method = RequestMethod.GET)
	public ModelAndView mouEdit(Locale locale) {
		final ModelAndView mav = new ModelAndView("test/edit");
		
		return mav;
	}
	@RequestMapping(value = "/document/search", method = RequestMethod.GET)
	public ModelAndView homex(Locale locale) {
		final ModelAndView mav = new ModelAndView("test/search");
		
		return mav;
	}
	@RequestMapping(value = "/document/report", method = RequestMethod.GET)
	public ModelAndView report(Locale locale) {
		final ModelAndView mav = new ModelAndView("test/report");
		
		return mav;
	}
	
	
	
	
	
	@RequestMapping(value = "/governance/home", method = RequestMethod.GET)
	public ModelAndView governance(Locale locale) {
		final ModelAndView mav = new ModelAndView("test/governance");
		
		return mav;
	}
	
	@RequestMapping(value = "/governance/add", method = RequestMethod.GET)
	public ModelAndView governanceAdd(Locale locale) {
		final ModelAndView mav = new ModelAndView("test/governanceAdd");
		
		return mav;
	}
	@RequestMapping(value = "/governance/edit", method = RequestMethod.GET)
	public ModelAndView governanceEdit(Locale locale) {
		final ModelAndView mav = new ModelAndView("test/governanceEdit");
		
		return mav;
	}
	@RequestMapping(value = "/governance/search", method = RequestMethod.GET)
	public ModelAndView governanceSearch(Locale locale) {
		final ModelAndView mav = new ModelAndView("test/governanceSearch");
		
		return mav;
	}
	
	@RequestMapping(value = "/governance/print", method = RequestMethod.GET)
	public ModelAndView print(Locale locale) {
		final ModelAndView mav = new ModelAndView("test/print");
		
		return mav;
	}
	
	
	
}

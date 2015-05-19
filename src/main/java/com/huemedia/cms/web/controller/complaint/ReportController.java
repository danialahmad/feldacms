package com.huemedia.cms.web.controller.complaint;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huemedia.cms.service.ReportService;

@Controller
public class ReportController {
	
	@Autowired
	ReportService reportService;
	
	@RequestMapping(value = "/report/caseStat", method = RequestMethod.GET)
	public ModelAndView home(Locale locale) {
		final ModelAndView mav = new ModelAndView("report/caseStat");
		
		return mav;
	}
	
	@RequestMapping(value = "/report/bulanan", method = RequestMethod.GET)
	public ModelAndView bulan(Locale locale) {
		final ModelAndView mav = new ModelAndView("report/report");
		
		return mav;
	}
	
	
	 @RequestMapping(value = "/report/kumulatif/{reportName}", method = RequestMethod.GET)
	  @ResponseBody  
	 public String genKumulatifReport(ModelAndView modelView,@PathVariable("reportName") final String reportName, HttpServletRequest request, HttpServletResponse response) throws MalformedURLException, IOException {
	     
		 Map<String, String> map= new HashMap<String, String>();
		 map.put("bulan_dari", request.getParameter("bulan_dari"));
		 map.put("bulan_hingga", request.getParameter("bulan_hingga"));
		 map.put("tahun", request.getParameter("tahun"));
		
		 
		 reportService.generateReport(response,reportName, map);
	        String viewName = "report";
	        modelView.setViewName(viewName);
	        return viewName;//new ModelAndView(new RedirectView(null));
	    }
	
	
	 @RequestMapping(value = "/report/bulanan/{reportName}", method = RequestMethod.GET)
	  @ResponseBody  
	 public String genStatusReport(ModelAndView modelView,@PathVariable("reportName") final String reportName, HttpServletRequest request, HttpServletResponse response) throws MalformedURLException, IOException {
	     
		 Map<String, String> map= new HashMap<String, String>();
		 map.put("bulan", request.getParameter("month"));
		 map.put("tahun", request.getParameter("year"));
		
		 
		 reportService.generateReport(response,reportName, map);
	        String viewName = "report";
	        modelView.setViewName(viewName);
	        return viewName;//new ModelAndView(new RedirectView(null));
	    }
}

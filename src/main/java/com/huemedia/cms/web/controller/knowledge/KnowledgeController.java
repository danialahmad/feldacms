package com.huemedia.cms.web.controller.knowledge;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huemedia.cms.service.CodeService;
import com.huemedia.cms.service.KnowledgeService;
import com.huemedia.cms.web.controller.datatables.DataTablesRequest;
import com.huemedia.cms.web.controller.datatables.DataTablesResponse;
import com.huemedia.cms.web.controller.knowledge.datatables.KnowledgeCatResult;
import com.huemedia.cms.web.controller.knowledge.datatables.KnowledgeResult;
import com.huemedia.cms.web.form.KnowledgeForm;

@Controller
public class KnowledgeController {
	
	@Autowired
	KnowledgeService knowledgeService;
	@Autowired
	CodeService codeService;
	
	@RequestMapping(value = "/knowledge/administrations", method = RequestMethod.GET)
	public ModelAndView knowledgeAdmin(Locale locale) {
		final ModelAndView mav = new ModelAndView("knowledge/admin");
		mav.addObject("knowledgeCategoryList", codeService.getKnowledgeCategoryList());
	    mav.addObject("knowledgeForm", new KnowledgeForm());
	    
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		List<GrantedAuthority> authorities =(List<GrantedAuthority>) authentication.getAuthorities();
	    boolean approver=false;
		if(authorities.contains(new GrantedAuthorityImpl("ROLE_SUPERVISOR")) || authorities.contains(new GrantedAuthorityImpl("ROLE_ADMIN"))  || authorities.contains(new GrantedAuthorityImpl("ROLE_MANAGER"))  ){
			approver=true;
		}
		
	    mav.addObject("approver",approver);
		return mav;
	}
	
	@RequestMapping(value = "/knowledge/search", method = RequestMethod.GET)
	public ModelAndView search(Locale locale) {
		final ModelAndView mav = new ModelAndView("knowledge/search");
		//PageWrapper<KnowledgeCategory> page = new PageWrapper<KnowledgeCategory>(knowledgeService.getAll(false, true,pageable), "/knowledge/search");
		//mav.addObject("page",page);
	    mav.addObject("knowledgeForm", new KnowledgeForm());
		return mav;
	}
	
	@RequestMapping(value = "/knowledge/add", method = RequestMethod.GET)
	public ModelAndView add(Locale locale) {
		final ModelAndView mav = new ModelAndView("knowledge/form");
		mav.addObject("knowledgeCategoryList", codeService.getKnowledgeCategoryList());
	    mav.addObject("knowledgeForm", new KnowledgeForm());
		return mav;
	}
	
	@RequestMapping(value = "/knowledge/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(Locale locale,@PathVariable("id") Integer id) {
		final ModelAndView mav = new ModelAndView("knowledge/form");
		KnowledgeForm form= new KnowledgeForm();
		form=knowledgeService.findByID(id);
		mav.addObject("knowledgeCategoryList", codeService.getKnowledgeCategoryList());
		mav.addObject("knowledgeForm", form);
		return mav;
	}
	@RequestMapping(value = "/knowledge/view/{id}", method = RequestMethod.GET)
	public ModelAndView view(Locale locale,@PathVariable("id") Integer id) {
		final ModelAndView mav = new ModelAndView("knowledge/view");
		KnowledgeForm form= new KnowledgeForm();
		form=knowledgeService.findByID(id);
		mav.addObject("knowledgeCategoryList", codeService.getKnowledgeCategoryList());
		mav.addObject("knowledgeForm", form);
		return mav;
	}
	
	@RequestMapping(value ="/knowledge/save",method = RequestMethod.POST)
	public @ResponseBody String save(final Locale locale, @ModelAttribute("knowledgeForm") KnowledgeForm form,BindingResult result) throws ParseException{
		knowledgeService.save(form);
		return "success";
	}
	
	@RequestMapping(value ="/knowledge/delete/{id}",method = RequestMethod.GET)
	public @ResponseBody String delete(@PathVariable("id") Integer id) throws ParseException{
		knowledgeService.delete(id);
		return "success";
	}
	
	@RequestMapping(value ="/knowledge/approve/{id}",method = RequestMethod.GET)
	public @ResponseBody String save(final Locale locale,@PathVariable("id") Integer id) throws ParseException{
		knowledgeService.approve(id);
		return "success";
	}
	@RequestMapping("/knowledge/list")
	public @ResponseBody DataTablesResponse<KnowledgeResult> getUserList(HttpServletRequest request, final DataTablesRequest dtRequest,@ModelAttribute("knowledgeForm") final KnowledgeForm form){
		DataTablesResponse<KnowledgeResult> dtResponse = new DataTablesResponse<KnowledgeResult>();
		final String echo = dtRequest.getsEcho();
		dtResponse.setsEcho(echo);
		final Long countAll = knowledgeService.countAll();
		final Long countSearch = knowledgeService.countSearch(form);
		try {
			dtResponse.setAaData(knowledgeService.getKnowledges(form, dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setiTotalDisplayRecords(countSearch.intValue());
			dtResponse.setiTotalRecords(countAll.intValue());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
	
	@RequestMapping("/knowledge/keyword/results")
	public @ResponseBody DataTablesResponse<KnowledgeResult> getKnowledgeList(HttpServletRequest request){
		DataTablesResponse<KnowledgeResult> dtResponse = new DataTablesResponse<KnowledgeResult>();
		final Long countAll = knowledgeService.countAll();
		
		Integer num=Integer.parseInt(request.getParameter("num"));
		Integer maxVisible =Integer.parseInt(request.getParameter("maxVisible"));
		String search = request.getParameter("search");
		try {
			dtResponse.setAaData(knowledgeService.getByKeyword(search, num, maxVisible));
			dtResponse.setiTotalDisplayRecords(knowledgeService.countSearchByKeyword(search).intValue());
			dtResponse.setiTotalRecords(countAll.intValue());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
	
	@RequestMapping("/knowledgeCategory/list")
	public @ResponseBody DataTablesResponse<KnowledgeCatResult> getKnowledgeCategoryList(HttpServletRequest request){
		DataTablesResponse<KnowledgeCatResult> dtResponse = new DataTablesResponse<KnowledgeCatResult>();
	//	final Long countAll = knowledgeService.countKnowledgeCategories();
		System.out.println("Call center");
		Integer num=Integer.parseInt(request.getParameter("num"));
		Integer maxVisible =Integer.parseInt(request.getParameter("maxVisible"));
		//String search = request.getParameter("search");
		try {
			dtResponse.setAaData(knowledgeService.getKnowledgeCategories(num, maxVisible));
			dtResponse.setiTotalDisplayRecords(knowledgeService.getKnowledgeCategories(num, maxVisible).size());
			dtResponse.setiTotalRecords(knowledgeService.getKnowledgeCategories(num, maxVisible).size());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
	
	
}

package com.huemedia.cms.web.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huemedia.cms.component.FileUtil;
import com.huemedia.cms.component.ImageUtil;
import com.huemedia.cms.model.entity.Chat;
import com.huemedia.cms.model.entity.EventLog;
import com.huemedia.cms.model.entity.StaffGroup;
import com.huemedia.cms.model.entity.User;
import com.huemedia.cms.security.service.UserDetail;
import com.huemedia.cms.service.ChatService;
import com.huemedia.cms.service.DashboardService;
import com.huemedia.cms.service.TicketService;
import com.huemedia.cms.service.UserService;
import com.huemedia.cms.service.log.EventLogService;
import com.huemedia.cms.web.config.Greeting;
import com.huemedia.cms.web.config.HelloMessage;
import com.huemedia.cms.web.form.ChatForm;

/**
 * Handles requests for the application home page.
 */

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	

	@Autowired
	DashboardService dashboardService;
	@Autowired
	EventLogService eventLogService;
	@Autowired
	ChatService chatService;
	@Autowired
	UserService userService;
	@Value("#{globalProperties['contextPath']}")
	String contextPath;
	@Autowired
	TicketService ticketService;
	
	@Autowired
    MessageSource messageSource;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<StaffGroup> sgList= userService.getStaffGroups(userDetail.getProfile());
		String[] groups=new String[sgList.size()];
		int i=0;
		Integer reg=null;
		for(StaffGroup sg:sgList){
			System.out.println("GROUP NAME :"+sg.getGroup().getId());
			groups[i]=sg.getGroup().getId();
			if(sg.getGroup().getId().equals("024")){
				reg=userDetail.getProfile().getRegion().getId();
			}
			i++;
		}
		Long cp=new Long(0);
		Long countU=new Long(0); 
		String page="index";
		
		List<GrantedAuthority> authorities =(List<GrantedAuthority>) authentication.getAuthorities();
		System.out.println("GRANDE");
		if(authorities.contains(new GrantedAuthorityImpl("ROLE_ADMIN"))){
		  page="index";	
		}
		if(authorities.contains(new GrantedAuthorityImpl("ROLE_SUPERVISOR"))){
			
			 cp=dashboardService.countPriorityByGroup(groups[0], "H");
			 final String[] status = {"ASSIGNED","RE-ASSIGNED","RETURNED","REJECTED"};
	         countU = ticketService.countAllUnassigned(groups,0,status);
			page="dashboard/supervisor";	
		}
		if(authorities.contains(new GrantedAuthorityImpl("ROLE_SUPPORTER"))){
			page="dashboard/supporter";	
		}
		if(authorities.contains(new GrantedAuthorityImpl("ROLE_MANAGER"))){
			page="dashboard/manager";	
		}
		if(authorities.contains(new GrantedAuthorityImpl("ROLE_HELPDESK"))){
			page="dashboard/helpdesk";	
		}
		
		final ModelAndView mav = new ModelAndView(page);
		mav.addObject("cp",cp.intValue());
		mav.addObject("cu",countU.intValue());
		return mav;
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(Locale locale) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<StaffGroup> sgList= userService.getStaffGroups(userDetail.getProfile());
		String[] groups=new String[sgList.size()];
		int i=0;
		Integer reg=null;
		for(StaffGroup sg:sgList){
			System.out.println("GROUP NAME :"+sg.getGroup().getId());
			groups[i]=sg.getGroup().getId();
			if(sg.getGroup().getId().equals("024")){
				reg=userDetail.getProfile().getRegion().getId();
			}
			i++;
		}
		Long cp=new Long(0);
		Long countU=new Long(0); 
		String page="index";
		List<GrantedAuthority> authorities =(List<GrantedAuthority>) authentication.getAuthorities();
		
		if(authorities.contains(new GrantedAuthorityImpl("ROLE_ADMIN"))){
		  page="index";	
		}
		if(authorities.contains(new GrantedAuthorityImpl("ROLE_SUPERVISOR"))){
			 cp=dashboardService.countPriorityByGroup(groups[0], "H");
			 final String[] status = {"ASSIGNED","RE-ASSIGNED","RETURNED","REJECTED"};
	         countU = ticketService.countAllUnassigned(groups,reg,status);
			page="dashboard/supervisor";	
		}
		if(authorities.contains(new GrantedAuthorityImpl("ROLE_SUPPORTER"))){
			page="dashboard/supporter";	
		}
		if(authorities.contains(new GrantedAuthorityImpl("ROLE_MANAGER"))){
			page="dashboard/manager";	
		}
		
		
		final ModelAndView mav = new ModelAndView(page);
		mav.addObject("cp",cp.intValue());
		mav.addObject("cu",countU.intValue());
		return mav;
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public ModelAndView indexPost(Locale locale) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<StaffGroup> sgList= userService.getStaffGroups(userDetail.getProfile());
		String[] groups=new String[sgList.size()];
		int i=0;
		Integer reg=null;
		for(StaffGroup sg:sgList){
			System.out.println("GROUP NAME :"+sg.getGroup().getId());
			groups[i]=sg.getGroup().getId();
			if(sg.getGroup().getId().equals("024")){
				reg=userDetail.getProfile().getRegion().getId();
			}
			i++;
		}
		Long cp=new Long(0);
		Long countU=new Long(0); 
		String page="index";
		List<GrantedAuthority> authorities =(List<GrantedAuthority>) authentication.getAuthorities();
		
		if(authorities.contains(new GrantedAuthorityImpl("ROLE_ADMIN"))){
		  page="index";	
		}
		if(authorities.contains(new GrantedAuthorityImpl("ROLE_SUPERVISOR"))){
			 cp=dashboardService.countPriorityByGroup(groups[0], "H");
			 final String[] status = {"ASSIGNED","RE-ASSIGNED","RETURNED","REJECTED"};
	         countU = ticketService.countAllUnassigned(groups,reg,status);
			page="dashboard/supervisor";	
		}
		if(authorities.contains(new GrantedAuthorityImpl("ROLE_SUPPORTER"))){
			page="dashboard/supporter";	
		}
		if(authorities.contains(new GrantedAuthorityImpl("ROLE_MANAGER"))){
			page="dashboard/manager";	
		}
		
		
		final ModelAndView mav = new ModelAndView(page);
		mav.addObject("cp",cp.intValue());
		mav.addObject("cu",countU.intValue());
		return mav;
	}
	
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public ModelAndView success(Locale locale,HttpServletRequest req) {
		final ModelAndView mav = new ModelAndView("success");
		String s=messageSource.getMessage(req.getParameter("alert"), req.getParameterValues("param"), locale);
		mav.addObject("alert", s);
		
		return mav;
	}
	@RequestMapping(value = "/eventLog/{ticketId}", method = RequestMethod.GET)
	public @ResponseBody List latestCases(Locale locale,@PathVariable("ticketId")String ticketId) {
		List<Map> list = new ArrayList<Map>();
		
		List<EventLog> events= eventLogService.findEventsByTicketID(ticketId);
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		for(EventLog t:events){
			User user=userService.getByUsername(t.getCreateBy());
			Map map = new HashMap();
			map.put("date", sdfFull.format(t.getCreateDate()));
			map.put("id", t.getTicketId());
			map.put("action",t.getAction());
			map.put("createBy", user.getProfile().getName());
			list.add(map);
		}
		
		return list;
	}
	
	@RequestMapping(value = "/eventLog/assignment/{ticketId}/{id}", method = RequestMethod.GET)
	public @ResponseBody List eventAss(Locale locale,@PathVariable("ticketId")String ticketId,@PathVariable("id")Integer id) {
		List<Map> list = new ArrayList<Map>();
		
		List<EventLog> events= eventLogService.findEventsByAssignmentID(id);
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		for(EventLog t:events){
			User user=userService.getByUsername(t.getCreateBy());
			Map map = new HashMap();
			map.put("date", sdfFull.format(t.getCreateDate()));
			map.put("id", t.getTicketId());
			map.put("action",t.getAction());
			map.put("createBy", user.getProfile().getName());
			list.add(map);
		}
		
		return list;
	}
	@RequestMapping(value = "/chat/{ticketId}", method = RequestMethod.GET)
	public @ResponseBody List chat(Locale locale,@PathVariable("ticketId")String ticketId) {
		List<Map> list = new ArrayList<Map>();
		
		List<Chat> chats= chatService.findAllMessagesByTicketID(ticketId);
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		for(Chat t:chats){
			Map map = new HashMap();
			
			User user=userService.getByUsername(t.getCreateBy());
			if(user.getProfile().getPhoto()!=null){
			byte[] img=user.getProfile().getPhoto();
			InputStream myInputStream = new ByteArrayInputStream(img); 
			map.put("img","data:image/jpg;base64,"+Base64.encodeBase64String(ImageUtil.resize(myInputStream, 26, 26)));
			}else{
				map.put("img",contextPath+"resources/img/examples/users/no-photo_s.jpg");
			}
			
			
			map.put("date", sdfFull.format(t.getCreateDate()));
			map.put("id", t.getTicketId());
			map.put("msg",t.getMessage());
			map.put("createBy", user.getProfile().getName());
			list.add(map);
		}
		
		return list;
	}
	@RequestMapping(value ="/chat/save",method = RequestMethod.POST)
	public @ResponseBody String saveChat(final Locale locale, @ModelAttribute("chatForm") ChatForm chatForm,BindingResult result) throws ParseException{
		chatService.save(chatForm);
		return "success";
	}
	
	@RequestMapping(value = "/chat/storeImage", method = RequestMethod.POST)
	public @ResponseBody Map<String,String> storeImage(Locale locale,@RequestBody byte[] img,HttpServletRequest req) throws IOException {
		Map<String,String> map=new HashMap<String,String>();
		String name =req.getParameter("name");
		Date date=new Date(); 
		String path=FileUtil.storeImage("cms-messages/"+date.getTime(), img, name);
		//String path="cms-messages/"+date.getTime()+"/"+name;
		map.put("name", name);
		map.put("path", path);
		return map;
	}
	
	@RequestMapping(value = "/chat/file", method = RequestMethod.GET)
	public ResponseEntity<byte[]> file(Model model,HttpServletRequest req,
			HttpSession session) throws Exception{
		String path =req.getParameter("path");
		Map<String,String> map=FileUtil.getInfo(path);
		byte[] imageData =FileUtil.readFullPath(path,map.get(FileUtil.EXTENSION));
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(map.get(FileUtil.MIME)));
        headers.add("Content-Disposition", "inline; filename=\"" + map.get(FileUtil.FILE_NAME) + '"');

        
        
        return new ResponseEntity<byte[]>(imageData, headers, HttpStatus.CREATED);
	}
	

	@MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(3000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }
	
	
	
	
}

package com.huemedia.cms.web.controller.administration;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huemedia.cms.model.entity.Notification;
import com.huemedia.cms.model.entity.RefVelocityMailTemplate;
import com.huemedia.cms.service.NotificationService;
import com.huemedia.cms.service.VelocityMailTemplateService;
import com.huemedia.cms.web.form.MasterForm;

@Controller
public class NotificationController {

	@Autowired
	NotificationService notificationService;
	@Autowired
	 VelocityMailTemplateService velocityMailTemplateService;
	
	@RequestMapping(value = "/administration/notification/complaint", method = RequestMethod.GET)
	public ModelAndView complaint(Locale locale) {
		final ModelAndView mav = new ModelAndView("administration/notification/complaint");
		List<Notification> list= notificationService.getNotifications();
		System.out.println("SIZE NOTI : "+list.size());
		MasterForm form=new MasterForm();
		form.setListNoti(list);
		mav.addObject("templateList", velocityMailTemplateService.listAll());
	    mav.addObject("masterForm", form);
		return mav;
	}
	@RequestMapping(value ="/administration/notification/complaint/save",method = RequestMethod.POST)
	public @ResponseBody String save(final Locale locale, @ModelAttribute("masterForm") MasterForm form,BindingResult result) throws ParseException{
		notificationService.save(form);
		return "success";
	}
	
}

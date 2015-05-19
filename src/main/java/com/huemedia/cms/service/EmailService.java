package com.huemedia.cms.service;

import com.huemedia.cms.model.entity.Profile;
import com.huemedia.cms.model.entity.Ticket;
import com.huemedia.cms.model.entity.TicketActivity;
import com.huemedia.cms.model.entity.TicketAssignment;
import com.huemedia.cms.model.entity.User;

public interface EmailService {
  
   
   void sendEmailForActivity(TicketActivity ticketActivity);
   void sendEmailForActivation(User user,String url);
   boolean sendEmailForReset(User user,String password);
   
   void sendChatMessage(Ticket t,Profile sender,String msg);
   
   void sendComplaintEmail(Ticket t);
   void sendComplaintEmail(TicketAssignment a);
   void sendEmailForAssignment(Profile p,TicketAssignment a);
   
}

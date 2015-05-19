package com.huemedia.cms.component.mail;

public interface EmailSender {
	   public void sendEmail(String[] to,String subject,String text);
	   public void sendEmail(String to,String subject,String text);
	   public void sendEmail(String from,String[] to,String subject,String text);
	   public void sendEmail(String from,String to,String subject,String text);
	   public void sendEmail(String from,String[] to,String[] cc,String[] bcc,String subject,String text);
	   public void sendEmail(String from,String to,String[] cc,String[] bcc,String subject,String text);
	   public void sendEmail(String from,String[] to,String[] bcc,String subject,String text);
	   public void sendEmail(String from,String to,String[] bcc,String subject,String text);
}

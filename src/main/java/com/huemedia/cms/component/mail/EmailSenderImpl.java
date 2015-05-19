package com.huemedia.cms.component.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class EmailSenderImpl implements EmailSender{

	private JavaMailSender javaMailSender;
	
	@Override
	public void sendEmail(final String[] to, final String subject, final String text) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
	         public void prepare(MimeMessage mimeMessage) throws Exception {
	            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
	            message.setTo(to);
	            message.setSubject(subject);
	         //   message.setFrom("support@abyres.net"); // could be parameterized...
	            message.setText(text, true);
	         }
	      };
	      this.javaMailSender.send(preparator);
	}
	
	
	@Override
	public void sendEmail(final String to, final String subject, final String text) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
	         public void prepare(MimeMessage mimeMessage) throws Exception {
	            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
	            message.setTo(to);
	            message.setSubject(subject);
	       //     message.setFrom("support@abyres.net"); // could be parameterized...
	            message.setText(text, true);
	         }
	      };
	      this.javaMailSender.send(preparator);
	}

	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}


	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}


	@Override
	public void sendEmail(final String from, final String[] to, final String subject, final String text) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
	         public void prepare(MimeMessage mimeMessage) throws Exception {
	            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
	            message.setTo(to);
	            message.setSubject(subject);
	            message.setFrom(from); // could be parameterized...
	            message.setText(text, true);
	         }
	      };
	      this.javaMailSender.send(preparator);
	}


	@Override
	public void sendEmail(final String from, final String to, final String subject, final String text) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
	         public void prepare(MimeMessage mimeMessage) throws Exception {
	         System.out.println("SEND FROM"+from);
	        	 MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
	            message.setTo(to);
	            message.setSubject(subject);
	            message.setFrom(from); // could be parameterized...
	            message.setText(text, true);
	         }
	      };
	      this.javaMailSender.send(preparator);
	}


	@Override
	public void sendEmail(final String from, final String[] to, final String[] cc, final String[] bcc,
			final String subject, final String text) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
	         public void prepare(MimeMessage mimeMessage) throws Exception {
	            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
	            message.setTo(to);
	            message.setSubject(subject);
	            message.setFrom(from); // could be parameterized...
	            message.setText(text, true);
	            message.setCc(cc);
	            message.setBcc(bcc);
	         }
	      };
	      this.javaMailSender.send(preparator);
	}


	@Override
	public void sendEmail(final String from,final String to,final String[] cc,final String[] bcc,
			final String subject,final String text) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
	         public void prepare(MimeMessage mimeMessage) throws Exception {
	            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
	            message.setTo(to);
	            message.setSubject(subject);
	            message.setFrom(from); // could be parameterized...
	            message.setText(text, true);
	            message.setCc(cc);
	            message.setBcc(bcc);
	         }
	      };
	      this.javaMailSender.send(preparator);
	}


	@Override
	public void sendEmail(final String from,final String[] to,final String[] bcc,
			final String subject,final String text) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
	         public void prepare(MimeMessage mimeMessage) throws Exception {
	            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
	            message.setTo(to);
	            message.setSubject(subject);
	            message.setFrom(from); // could be parameterized...
	            message.setText(text, true);
	            message.setBcc(bcc);
	         }
	      };
	      this.javaMailSender.send(preparator);
	}


	@Override
	public void sendEmail(final String from,final String to,final String[] bcc,final String subject,
			final String text) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
	         public void prepare(MimeMessage mimeMessage) throws Exception {
	            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
	            message.setTo(to);
	            message.setSubject(subject);
	            message.setFrom(from); // could be parameterized...
	            message.setText(text, true);
	            message.setBcc(bcc);
	         }
	      };
	      this.javaMailSender.send(preparator);
	}

}

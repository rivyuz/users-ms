/*
 * Author: Siva 
 * Date  : 22-Jan-2015
 * Version: 1.0
 */

package com.riviewz.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class EmailFromFooter {

	public String footerMail(String fromEmailId, String toEmailId, String subjectId, String contentId) {

		String flag = "success";
		
		String host="smtp.office365.com";
		String port="587";
		String auth="true";
		String starttls="true";

		String mailer="contact@riviewz.com";
		String password="trep6ub#Est!";

		Properties props = new Properties();

		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", auth);
		props.put("mail.smtp.starttls.enable", starttls);

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailer, password);
			}
		});

		try {

			Message message = new MimeMessage(session);

			// Set From address
			message.setFrom(new InternetAddress(mailer));

			// Set To address
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmailId));

			// Set Subject field
			message.setSubject(subjectId);

			// Set email content
			message.setContent("<html>" + 
							   "<body>" + 
							   "<p>" + 
							   "Dear Team " + 
							   "<br><br>" + contentId.replaceAll("\n\n", "<br><br>") + "</b>" + "<br><br>" + 
							   "Regards" + 
							   "<br>" + 
							   fromEmailId + 
							   "</p>" + 
							   "<br>" + 
							   "\n" + 
							   "</body>" + 
							   "</html>", 
							   "text/html");

			// Send message
			Transport.send(message);

			System.out.println("Footer email sent successfully....");

		} catch (MessagingException e) {
			flag = "unsuccessful";
			e.printStackTrace();
		}

		return flag;
	}
}
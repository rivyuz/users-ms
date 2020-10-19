package com.riviewz.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.riviewz.configuration.ReadProperties;
import com.riviewz.model.User;

@Component
public class EmailActivationCode {
	
	@Autowired
	ReadProperties rp;	

	public String sendActivationCode(User user) {

		String flag = "success";
		
		String host="smtp.office365.com";
		String port="587";
		String auth="true";
		String starttls="true";

		String mailer="contact@riviewz.com";
		String password="trep6ub#Est!";

		String confirmSubject="From riviewz.com - Please confirm your registration";

		String server = rp.getServer();
		
		String confirmLink = null;
		String siteLink = null;

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
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmailId()));// Recipient's email ID

			// Set Subject field
			message.setSubject(confirmSubject);

			String[] splitEmail = user.getEmailId().split("@");
			String replyEmail = splitEmail[0] + "XXXXXxxxxXXXX" + splitEmail[1];

			String queryString = "job=confirm" + "&code=" + user.getActivationCode() + "&emailId=" + replyEmail;
			String encQueryString = null;

			AesUtil aesUtil = new AesUtil();
			encQueryString = aesUtil.encrypt(queryString);
			
			confirmLink = "<b>" + "<a href=\"" + server + "?" + encQueryString + "\">\n" + "Confirm Registration</a>\n" + "</b>";
			siteLink = "<br>" + "<a href=\"" + server + "/" + "\">\n" + "www.riviewz.com</a>\n" + "</p>" + "<br>";
			
			// Set email content
			message.setContent("<html>" + 
							   "<body>" + 
							   "<p>" + 
							   "Dear " + user.getUserName() + 
							   "<br><br>" + "Thank you for registering at riviewz.com." + "<br>" + 
							   "Please click the below link to confirm your registration:" + 
							   "<br><br>" + 
							   confirmLink + 
							   "<br><br>" + 
							   "Regards" + 
							   siteLink + 
							   "</body>" + 
							   "</html>", 
							   "text/html");

			// Send message
			Transport.send(message);

			System.out.println("Confirmation link emailed successfully....");

		} catch (MessagingException e) {
			flag = "unsuccessful";
			e.printStackTrace();
		}

		return flag;
	}

}
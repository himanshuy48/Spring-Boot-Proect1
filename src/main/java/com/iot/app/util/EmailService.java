package com.iot.app.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import com.iot.app.model.Response;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

@Component
public class EmailService {

	@Value("${spring.mail.host}")
	String host;

	@Value("${spring.mail.username}")
	String user;

	@Value("${spring.mail.password}")
	String password;

	@Autowired
	private JavaMailSender javaMailSender;

	public Response<String> sendEmail(String receiversEmail, String verificationCode) {

		SimpleMailMessage msg = new SimpleMailMessage();

		msg.setTo(receiversEmail);
		msg.setSubject("Email varification for Connextion-iot application");
		msg.setText("Hello your verification code is:-" + verificationCode + "\nEnter this in your app to verify");

		try {
			javaMailSender.send(msg);
		} catch (MailException e) {

			e.printStackTrace();
		}
		return new Response(200, "mail status", msg);

	}
	
	//for future use if we have to seprate the verify email and forgot password
	public Response<String> sendEmailForForgetPassword(String receiversEmail, String verificationCode) {

		SimpleMailMessage msg = new SimpleMailMessage();

		msg.setTo(receiversEmail);
		msg.setSubject("Forget password varification for Connextion-iot application");
		msg.setText("Hello your verification code is:-" + verificationCode + "\nEnter this in your app to change your password");

		try {
			javaMailSender.send(msg);
		} catch (MailException e) {

			e.printStackTrace();
		}
		return new Response(200, "mail status", msg);

	}
//
//	public Response<String> sendJavaMail(String receiversEmail, String verificationCode) {
//
//		// Get the session object
//		Properties props = new Properties();
//		props.put("mail.smtp.host", host);
//		props.put("mail.smtp.auth", "true");
//
//		
//		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
//			@Override
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(user, password);
//			}
//		});
//
//		// Compose the message
//		try {
//			MimeMessage message = new MimeMessage(session);
//			message.setFrom(new InternetAddress(user));
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiversEmail));
//			message.setSubject("Email varification for Connextion-iot application");
//			message.setText("Hello your verification code is:-" + verificationCode + "\nEnter this in your app to verify");
//
//			// send the message
//			Transport.send(message);
//
//			System.out.println("message sent successfully...");
//			return new Response(200, "mail status", message);
//		} catch (MessagingException e) {
//			e.printStackTrace();
//			return new Response(205, "EmailId not valid");
//		}
//
//	}
//
}

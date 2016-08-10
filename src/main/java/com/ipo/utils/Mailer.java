package com.ipo.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//import javax.mail.Message;

public class Mailer {

	final String from = "IPO_Portal_Admin@noreply.com";
	final String host = "172.16.4.64";
	final String port = "25";
	final String socketFactoryClass = "javax.net.ssl.ExchangeSSLSocketFactory";
	String to;
	String subject;
	String content;

	public Mailer(String to, String subject, String content) {
		this.to = to;
		this.subject = subject;
		this.content = content;
	}

	public void send() {

		try {
			// Get System Properties
			Properties properties = System.getProperties();
			
			//Setup Mail server
			properties.setProperty("mail.smtp.host",host);
			properties.setProperty("mail.smtp.socketFactory.port",port);
			properties.setProperty("mail.smtp.socketFactory.class",socketFactoryClass);
			properties.setProperty("mail.smtp.port",port);
			properties.setProperty("mail.smtp.starttls.enable", "false");

			// Get Default Session Object
			Session session = Session.getDefaultInstance(properties);

			// Create a default MimeMessage Object
			Message message = new MimeMessage(session);

			// Set From
			message.setFrom(new InternetAddress(from));

			// Set To
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject
			message.setSubject(subject);

			// Set Content
			message.setContent(content, "text/html");

			// Send Message
			Transport.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

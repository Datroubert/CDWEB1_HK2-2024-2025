package com.ecom.finalproj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	@Autowired
	private JavaMailSender mailSender;

	public void sendEmail(String to, String subject, String text) {
	    SimpleMailMessage message = new SimpleMailMessage();
	    message.setTo(to); // Địa chỉ email hợp lệ!
	    message.setSubject(subject); // Không null
	    message.setText(text); // Không null
	    mailSender.send(message);
	}
}

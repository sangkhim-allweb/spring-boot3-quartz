package com.sangkhim.spring_boot3_quartz.service;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {

  private final JavaMailSender sender;

  public void send(String from, String recipient, String subject, String body) {
    try {
      log.info("Sending mail to {}", recipient);
      MimeMessage message = sender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message);
      helper.setFrom(new InternetAddress(from));
      helper.setTo(recipient);
      helper.setSubject(subject);
      helper.setText(body);
      sender.send(message);
    } catch (Exception e) {
      log.info(e.toString());
    }
  }
}

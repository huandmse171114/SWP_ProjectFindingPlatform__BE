package com.findhub.finhubbackend.service.email;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.findhub.finhubbackend.entity.mail.MailRequest;
import com.findhub.finhubbackend.entity.mail.MailResponse;
import com.findhub.finhubbackend.entity.mail.MailType;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender sender;

	@Autowired
	private Configuration config;

	public MailResponse sendEmail(MailRequest request, Map<String, Object> model, MailType type) {
		MailResponse response = new MailResponse();
		MimeMessage message = sender.createMimeMessage();
		String file = type.getValue();
		try {
			// set mediaType
			MimeMessageHelper helper = new MimeMessageHelper(
					message,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			// add attachment
			// helper.addAttachment("logo.png", new ClassPathResource("logo.png"));

			Template t = config.getTemplate(file);
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

			helper.setTo(request.getTo());
			helper.setText(html, true);
			helper.setSubject(request.getSubject());
			helper.setFrom("lethucthanhtu@gmail.com");
			sender.send(message);

			response.setMessage("mail send to : " + request.getTo());
			response.setStatus(Boolean.TRUE);

		} catch (MessagingException | IOException | TemplateException e) {
			response.setMessage("Mail Sending failure : " + e.getMessage());
			response.setStatus(Boolean.FALSE);
		}

		return response;
	}

}
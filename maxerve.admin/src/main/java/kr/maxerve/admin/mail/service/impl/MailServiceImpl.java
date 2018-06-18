package kr.maxerve.admin.mail.service.impl;

import java.util.Date;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.DateTool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import kr.maxerve.admin.mail.service.MailService;
import kr.maxerve.admin.mail.vo.MailVO;

@Service("mailService")
public class MailServiceImpl implements MailService {
	@Resource(name="javaMailSender")
	private JavaMailSender javaMailSender;

	@Resource(name="velocityEngine")
	private VelocityEngine velocityEngine;

	@Value("#{propertiesService['user.server.http_host']}")
	private String userServerHttpHost;

	@Override
	public void sendMail(MailVO vo) throws Exception {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@SuppressWarnings("unchecked")
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				vo.getModel().put("dateTool", new DateTool());
				vo.getModel().put("now", new Date(System.currentTimeMillis()));
				vo.getModel().put("stringUtils", new StringUtils());
				vo.getModel().put("userServerHttpHost", userServerHttpHost);

				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setFrom(vo.getFrom());
				message.setTo(vo.getTo());
				message.setSubject(vo.getSubject());
				String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, vo.getTemplate(), "UTF-8", vo.getModel());

				message.setText(text, true);
			}
		};

		javaMailSender.send(preparator);
	}

}

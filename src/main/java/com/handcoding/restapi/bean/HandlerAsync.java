package com.handcoding.restapi.bean;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import com.handcoding.restapi.domain.in.InEmailConfirmSendVO;

/**
 * 비동기 처리
 * @author 이승환
 * @version 2018.01.23 v1.0
 */
@Configuration
@EnableAsync
public class HandlerAsync {
	
	@Autowired
	private JavaMailSender mailSender;
	
	/**
	 * 회원가입 이메일 인증메일 발송
	 * @param inEmailConfirmSendVO
	 * @throws MessagingException 
	 */
	@Async
	public void signUpEmailConfirm(InEmailConfirmSendVO inEmailConfirmSendVO) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
		
		if(inEmailConfirmSendVO.getFrom() != null) {
			messageHelper.setFrom(inEmailConfirmSendVO.getFrom());
		}
		messageHelper.setTo(inEmailConfirmSendVO.getEmail());
		messageHelper.setSubject("회원가입");
        StringBuffer sb = new StringBuffer();
        sb.append("<a href='")
        	.append(inEmailConfirmSendVO.getEmailConfirmUrl())
        	.append("?emailKey=")
        	.append(inEmailConfirmSendVO.getEmailKey())
        	.append("'>이메일인증</a>");
        messageHelper.setText(sb.toString(), true);
        
        mailSender.send(message);
	}
	
}


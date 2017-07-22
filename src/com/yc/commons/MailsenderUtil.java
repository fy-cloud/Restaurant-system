package com.yc.commons;

import com.yc.bean.MailSenderInfo;
import com.yc.bean.MyAuthenticator;
import com.yc.service.MailSender;

public class MailsenderUtil {
	MailSenderInfo mailSenderInfo = MailSenderInfo.getInstance();
	MyAuthenticator myAuthenticator = new MyAuthenticator();
	MailSender mailSender = new MailSender();
	
	public MailsenderUtil(){
		mailSenderInfo.setMailServerHost("smtp.qq.com");
		mailSenderInfo.setUserName("773458019@qq.com");
		mailSenderInfo.setPassword("odcngzrvtdrsbbgf");
		mailSenderInfo.setFromAddress("773458019@qq.com");
	}
	
	public void send (String toAddress ,String subject,String content){
		mailSender.doSendEmail(subject, content, toAddress, null);
		
	}
}

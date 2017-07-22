package com.yc.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.yc.bean.MailSenderInfo;
import com.yc.bean.MyAuthenticator;
/**
 * 
 * @author chastlove
 * @usage  用于发送邮件
 */
public class MailSender {
	/**
	 * 
	 * @param mInfo
	 * @param subject
	 * @param content
	 * @param receiveUser
	 * @param attachment
	 */
	public void doSendEmail(String subject, String content, String receiveUser, File attachment){
		MailSenderInfo mInfo = MailSenderInfo.getInstance();
		mInfo.setToAddress(receiveUser);
		MyAuthenticator myAuthenticator = new MyAuthenticator(mInfo.getUserName(), mInfo.getPassword());
		Properties properties = mInfo.getProperties();
		Session session = Session.getDefaultInstance(properties, myAuthenticator);
		Transport transport = null;
		
		try {
			Message message = new MimeMessage(session);
			//设置发件人
			InternetAddress from = new InternetAddress(mInfo.getFromAddress());
			message.setFrom(from);
			//设置收件人
			InternetAddress to = new InternetAddress(mInfo.getToAddress());
			message.setRecipient(Message.RecipientType.TO, to);
			//设置邮件主题
			message.setSubject(subject);
			//向邮件中添加内容,包括附件
			Multipart multipart = new MimeMultipart();
			
			//添加正文
			BodyPart contentPart = new MimeBodyPart();
			contentPart.setContent(content, "text/html;charset=UTF-8");
			multipart.addBodyPart(contentPart);
			
			//添加附件
			
			if(attachment!=null){
				BodyPart attachmentBodyPart = new MimeBodyPart();
				DataSource dataSource = new FileDataSource(attachment);
				attachmentBodyPart.setDataHandler(new DataHandler(dataSource));
				attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
				multipart.addBodyPart(attachmentBodyPart);
			}	
			
			//将multipart放入message中
			message.setContent(multipart);
			
			//保存邮件
			message.saveChanges();
			//验证smtp连接
			transport = session.getTransport("smtp");
			transport.connect(mInfo.getMailServerHost(), mInfo.getUserName(), mInfo.getPassword());
			//发送
			transport.sendMessage(message, message.getAllRecipients());
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(transport!=null){
				try {
					transport.close();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}

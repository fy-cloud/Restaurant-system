package com.yc.bean;

import java.util.Properties;
/**
 * 
 * @author chastlove
 * @usage  存放发送邮件的基本信息
 */
public class MailSenderInfo {
	 // 发送邮件的服务器的IP(或主机地址)
	 private static String mailServerHost;
	 // 发送邮件的服务器的端口
	 private static String mailServerPort = "587";
	 // 发件人邮箱地址
	 private static String fromAddress;
	 // 收件人邮箱地址
	 private static String toAddress;
	 // 登陆邮件发送服务器的用户名
	 private static String userName;
	 // 登陆邮件发送服务器的密码
	 private static String password;
	 // 是否需要身份验证
	 private static boolean validate = true;
	 // 邮件主题
	 private static String subject;
	 // 邮件的文本内容
	 private static String content;
	 // 邮件附件的文件名
	 private static String[] attachFileNames;
	 
	 private static MailSenderInfo mailSenderInfo;
	 
	 private MailSenderInfo(){
		 
	 }
	 
	 public static MailSenderInfo getInstance (){
		 if(mailSenderInfo==null)
			 return new MailSenderInfo();
		 return mailSenderInfo;
	 }
	 
	 
	 public Properties getProperties() {
	  Properties p = new Properties();
	  p.put("mail.smtp.host", mailServerHost);
	  p.put("mail.smtp.port", mailServerPort);
	  p.put("mail.smtp.auth", validate ? "true" : "false");
	  return p;
	 }
	 public String getMailServerHost() {
	  return mailServerHost;
	 }
	 public void setMailServerHost(String mailServerHost) {
	  MailSenderInfo.mailServerHost = mailServerHost;
	 }
	 public String getMailServerPort() {
	  return mailServerPort;
	 }
	 public void setMailServerPort(String mailServerPort) {
	  MailSenderInfo.mailServerPort = mailServerPort;
	 }
	 public boolean isValidate() {
	  return validate;
	 }
	 public void setValidate(boolean validate) {
	  MailSenderInfo.validate = validate;
	 }
	 public String[] getAttachFileNames() {
	  return attachFileNames;
	 }
	 public void setAttachFileNames(String[] fileNames) {
	  MailSenderInfo.attachFileNames = fileNames;
	 }
	 public String getFromAddress() {
	  return fromAddress;
	 }
	 public void setFromAddress(String fromAddress) {
	  MailSenderInfo.fromAddress = fromAddress;
	 }
	 public String getPassword() {
	  return password;
	 }
	 public void setPassword(String password) {
	  MailSenderInfo.password = password;
	 }
	 public String getToAddress() {
	  return toAddress;
	 }
	 public void setToAddress(String toAddress) {
	  MailSenderInfo.toAddress = toAddress;
	 }
	 public String getUserName() {
	  return userName;
	 }
	 public void setUserName(String userName) {
	  MailSenderInfo.userName = userName;
	 }
	 public String getSubject() {
	  return subject;
	 }
	 public void setSubject(String subject) {
	  MailSenderInfo.subject = subject;
	 }
	 public String getContent() {
	  return content;
	 }
	 public void setContent(String textContent) {
	  MailSenderInfo.content = textContent;
	 }
}

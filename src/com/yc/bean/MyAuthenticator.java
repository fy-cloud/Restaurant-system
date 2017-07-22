package com.yc.bean;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
/**
 * 
 * @author chastlove
 * @usage  验证类,用来返回PasswordAuthentication的对象
 *
 */
public class MyAuthenticator extends Authenticator{
	private String username = null;
	private String password = null;
	
	public MyAuthenticator () {
		// TODO Auto-generated method stub

	}

	public MyAuthenticator(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}
}

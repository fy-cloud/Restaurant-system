package com.yc.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

import com.yc.commons.LoginUserInfo;
import com.yc.commons.MessageUtil;
import com.yc.dao.OrderDao;
import com.yc.dao.PwdDao;

public class Mainpwd {

	Shell shell;
	Display display;
	Text old_pwd;
	Label lblNewLabel_2;
	Text new_pwd1;
	Text new_pwd2;
	Button btnNewButton;
	Label label_newpwd1;
	Label label_newpwd2;
	OrderDao orderDao = new OrderDao();
	PwdDao pwdDao = new PwdDao();
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Mainpwd window = new Mainpwd();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		Display display = null;
		shell = new Shell(display,SWT.CLOSE | SWT.ON_TOP);shell.setImage(SWTResourceManager.getImage(Mainpwd.class, "/image/密码修改.png"));
		shell.setSize(500, 280);
		shell.setText("修改密码");
		// 得到屏幕的宽度和高度 
        int  screenHeight  =  Toolkit.getDefaultToolkit().getScreenSize().height;
        int  screenWidth  =  Toolkit.getDefaultToolkit().getScreenSize().width;
        // 得到Shell窗口的宽度和高度 
        int  shellHeight  =  shell.getBounds().height;
        int  shellWidth  =  shell.getBounds().width;
        // 让窗口在屏幕中间显示 
        shell.setLocation(((screenWidth - shellWidth)/2),((screenHeight - shellHeight)/2));
		
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(120, 40, 74, 17);
		lblNewLabel.setText("      原密码:");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(120, 90, 74, 17);
		lblNewLabel_1.setText("      新密码:");
		
		old_pwd = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		old_pwd.setBounds(200, 34, 140, 23);
		
		lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(120, 140, 74, 17);
		lblNewLabel_2.setText("确认新密码:");
		
		new_pwd1 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		new_pwd1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				label_newpwd1.setText("*密码应为6-10位数字");
			}
			@Override
			public void focusLost(FocusEvent e) {
				String newpwd1 = new_pwd1.getText();
				if (newpwd1.matches("\\d{6,10}")) {
					label_newpwd1.setText("");
				}else {
					label_newpwd1.setText("密码输入不合法!!!");
				}
			}
		});
		new_pwd1.setEchoChar('*');
		new_pwd1.setBounds(200, 84, 140, 23);
		
		new_pwd2 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		new_pwd2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
			}
			@Override
			public void focusLost(FocusEvent e) {
				String newpwd1 = new_pwd1.getText();
				String newpwd2 = new_pwd2.getText();
				if (newpwd1.equals(newpwd2)) {
					label_newpwd2.setText("");
				}else{
					label_newpwd2.setText("*密码输入不一致!!!");
				}
			}
		});
		new_pwd2.setEchoChar('*');
		new_pwd2.setBounds(200, 134, 140, 23);
		
		btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		btnNewButton.setFont(SWTResourceManager.getFont("System", 10, SWT.BOLD));
		btnNewButton.setText("确   定");
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String oldpwd = old_pwd.getText();
				String newpwd1 = new_pwd1.getText();
				String newpwd2 = new_pwd2.getText();
				if(oldpwd.trim().equals("") ||newpwd1.trim().equals("") ||newpwd2.trim().equals("")){
					MessageUtil.promt(shell, "温馨提示", "请完善信息!");
					return;
				}
				if(!pwdDao.isPwd(oldpwd)){
					MessageUtil.promt(shell, "温馨提示", "原密码不正确!");
					return;
				}
				if (new_pwd1.getText().equals(new_pwd2.getText())) {
					if(oldpwd.equals(newpwd1)){
						MessageUtil.promt(shell, "温馨提示", "新旧密码不能一样!");
						return;
					}
					
					List<Object> params = new ArrayList<Object>();
					params.add(newpwd2);
					params.add(orderDao.findUserId(LoginUserInfo.username));
					try {
						boolean i = pwdDao.changePwd(params);
						if(i){
							MessageUtil.promt(shell, "温馨提示", "密码修改成功!");
							old_pwd.setText("");
							new_pwd1.setText("");
							new_pwd2.setText("");
						}else {
							MessageUtil.promt(shell, "温馨提示", "密码修改失败!");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}else {
					MessageUtil.promt(shell, "温馨提示", "密码输入不一致!");
				}

			}
		});
		btnNewButton.setBounds(142, 190, 90, 30);
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnNewButton_1.setFont(SWTResourceManager.getFont("System", 10, SWT.BOLD));
		btnNewButton_1.setBounds(260, 190, 90, 30);
		btnNewButton_1.setText("退   出");
		
		label_newpwd1 = new Label(shell, SWT.NONE);
		label_newpwd1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 8, SWT.NORMAL));
		label_newpwd1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_newpwd1.setBounds(200, 113, 140, 15);
		
		label_newpwd2 = new Label(shell, SWT.NONE);
		label_newpwd2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_newpwd2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 8, SWT.NORMAL));
		label_newpwd2.setBounds(200, 163, 140, 15);

	}
}

package com.yc.ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.yc.bean.MailSenderInfo;
import com.yc.commons.MessageUtil;

public class MailLogin {

	protected Shell shell;
	private Text txtSmtpqqcom;
	private Text txtqqcom;
	private Text txtSxj;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MailLogin window = new MailLogin();
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
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("参数设置");
		Dimension dem = Toolkit.getDefaultToolkit().getScreenSize();
		// 窗体居中显示
		shell.setLocation((dem.width - shell.getSize().x) / 2,
				(dem.height - shell.getSize().y) / 2);
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(60, 58, 92, 17);
		lblNewLabel.setText("smtp服务器:");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(60, 107, 92, 17);
		lblNewLabel_1.setText("用户名:");
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(60, 159, 92, 17);
		lblNewLabel_2.setText("密码");
		
		txtSmtpqqcom = new Text(shell, SWT.BORDER);
		txtSmtpqqcom.setText("smtp.qq.com");
		txtSmtpqqcom.setBounds(159, 58, 167, 23);
		
		txtqqcom = new Text(shell, SWT.BORDER);
		txtqqcom.setText("773458019@qq.com");
		txtqqcom.setBounds(159, 107, 167, 23);
		
		txtSxj = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		txtSxj.setText("odcngzrvtdrsbbgf");
		txtSxj.setBounds(158, 159, 167, 23);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(txtSmtpqqcom.getText().equals("")||txtqqcom.getText().equals("")||txtSxj.getText().equals("")){
					MessageUtil.promt(shell, "Alert", "请填写完整信息!");
					return;
				}
				
				MailSenderInfo mailSenderInfo = MailSenderInfo.getInstance();
				mailSenderInfo.setMailServerHost(txtSmtpqqcom.getText());
				mailSenderInfo.setUserName(txtqqcom.getText());
				mailSenderInfo.setPassword(txtSxj.getText());
				shell.close();
				MailSendUI mailSendUI = new MailSendUI();
				mailSendUI.open();
			}
		});
		btnNewButton.setBounds(198, 213, 80, 27);
		btnNewButton.setText("确定");

	}
}

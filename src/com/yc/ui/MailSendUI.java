package com.yc.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.yc.bean.MailSenderInfo;
import com.yc.commons.MessageUtil;
import com.yc.service.MailSender;

public class MailSendUI {

	protected Shell shell;
	private Text txtqqcom;
	private Text text__subject;
	private Text text_content;
	private Text text_3;
	private Button button;
	private Button button_1;
	private Button button_2;
	private Button button_3;
	File file = null;
	boolean isSelectedFile = false;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MailSendUI window = new MailSendUI();
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
		shell.setSize(784, 483);
		shell.setText("邮件发送");
		Dimension dem = Toolkit.getDefaultToolkit().getScreenSize();
		// 窗体居中显示
		shell.setLocation((dem.width - shell.getSize().x) / 2,
				(dem.height - shell.getSize().y) / 2);
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(44, 32, 61, 17);
		label.setText("收件人:");
		
		txtqqcom = new Text(shell, SWT.BORDER);
		txtqqcom.setText("840734781@qq.com");
		txtqqcom.setBounds(120, 32, 381, 23);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(44, 80, 61, 17);
		label_1.setText("邮件主题:");
		
		text__subject = new Text(shell, SWT.BORDER);
		text__subject.setBounds(120, 74, 381, 23);
		
		text_content = new Text(shell, SWT.BORDER);
		text_content.setBounds(44, 143, 687, 224);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(44, 125, 61, 17);
		lblNewLabel.setText("正文:");
		
		text_3 = new Text(shell, SWT.BORDER);
		text_3.setBounds(44, 397, 170, 23);
		
		button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(shell, SWT.OPEN|SWT.SINGLE);
				String path = fd.open();
				if(path!=null){
					text_3.setText(path);
					isSelectedFile = true;
				}
			}
		});
		button.setBounds(235, 393, 80, 27);
		button.setText("添加附件");
		
		button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text_3.setText("");
				isSelectedFile = false;
			}
		});
		button_1.setText("取消附件");
		button_1.setBounds(330, 393, 80, 27);
		
		button_2 = new Button(shell, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MailSenderInfo mailSenderInfo = MailSenderInfo.getInstance();
				mailSenderInfo.setFromAddress(mailSenderInfo.getUserName());
				MailSender mailSender = new MailSender();
				file = new File(text_3.getText());
				if(!file.exists()&&isSelectedFile){
					MessageUtil.promt(shell, "Alert", "您选择的文件不存在");
					return;
				}
				mailSender.doSendEmail( text__subject.getText(), text_content.getText(), txtqqcom.getText(), file.exists()?file:null);
				MessageUtil.promt(shell, "Alert", "发送成功!");
			}
		});
		button_2.setBounds(541, 395, 80, 27);
		button_2.setText("发送");
		
		button_3 = new Button(shell, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
		button_3.setText("关闭");
		button_3.setBounds(643, 395, 80, 27);

	}

}

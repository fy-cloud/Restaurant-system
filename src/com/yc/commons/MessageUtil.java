package com.yc.commons;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class MessageUtil {
	public static void promt(Shell shell,String title,String message){
		MessageBox messageBox=new MessageBox(shell,SWT.NONE);
		messageBox.setText(title);
		messageBox.setMessage(message);
		messageBox.open();
	}
}

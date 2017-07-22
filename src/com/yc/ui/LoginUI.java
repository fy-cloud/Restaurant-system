package com.yc.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.Map;
import java.util.List;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;

import com.yc.commons.LoginUserInfo;
import com.yc.commons.MessageUtil;
import com.yc.dao.LoginDao;

public class LoginUI {

	protected Shell shell;
	private Text text;
	LoginDao dao = new LoginDao();
	List<Map<String,Object>> list;
	Combo combo_1 ;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LoginUI window = new LoginUI();
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
		shell = new Shell(SWT.MIN);
		shell.setBackgroundImage(SWTResourceManager.getImage(LoginUI.class, "/image/land_background.jpg"));
		shell.setImage(SWTResourceManager.getImage(LoginUI.class, "/image/ca31062b6693f580-1bb360fd7cf46844-26de80ae18859a6a8d708d698a02de94.jpg"));
		shell.setSize(424, 327);
		shell.setText("酒店管理系统");
		Dimension dem = Toolkit.getDefaultToolkit().getScreenSize();
		// 窗体居中显示
		shell.setLocation((dem.width - shell.getSize().x) / 2,
				(dem.height - shell.getSize().y) / 2);

		final Combo combo = new Combo(shell, SWT.NONE);
		combo.setBackgroundImage(null);
		combo.setBounds(246, 150, 88, 25);

		text = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text.setBounds(246, 185, 126, 23);

		final Button button = new Button(shell, SWT.NONE);
		button.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseExit(MouseEvent e) {
				button.setImage(SWTResourceManager.getImage(LoginUI.class,
						"/image/land_submit.png"));
			}

			@Override
			public void mouseEnter(MouseEvent e) {
				button.setImage(SWTResourceManager.getImage(LoginUI.class,
						"/image/land_submit_over.png"));
			}
		});
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				button.setImage(SWTResourceManager.getImage(LoginUI.class,
						"/image/land_submit_pressed.png"));
			}

			@Override
			public void mouseUp(MouseEvent e) {
				button.setImage(SWTResourceManager.getImage(LoginUI.class,
						"/image/land_submit.png"));
			}
		});
		button.setImage(SWTResourceManager.getImage(LoginUI.class,
				"/image/land_submit.png"));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(combo.getText().equals("")||text.getText().equals("")){
					MessageUtil.promt(shell, "温馨提示", "请填写完整信息");
					return;
				}
				try {
					boolean login = dao.login(combo.getText(), text.getText());
					if(login){
						MessageUtil.promt(shell, "温馨提示", "登陆成功");
						LoginUserInfo.username = combo.getText();
						LoginUserInfo.type = combo_1.getText().equals("收银员")?1:0;
						MainUI mainUI = new MainUI();
						shell.close();
						mainUI.open();
					}else {
						MessageUtil.promt(shell, "温馨提示", "用户名或密码错误");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(136, 267, 49, 19);

		final Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				combo.setText("");
				text.setText("");
			}
		});
		button_1.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent e) {
				button_1.setImage(SWTResourceManager.getImage(LoginUI.class,
						"/image/land_reset_over.png"));
			}

			@Override
			public void mouseExit(MouseEvent e) {
				button_1.setImage(SWTResourceManager.getImage(LoginUI.class,
						"/image/land_reset.png"));
			}
		});
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				button_1.setImage(SWTResourceManager.getImage(LoginUI.class,
						"/image/land_reset_pressed.png"));
			}

			@Override
			public void mouseUp(MouseEvent e) {
				button_1.setImage(SWTResourceManager.getImage(LoginUI.class,
						"/image/land_reset.png"));
			}
		});
		button_1.setImage(SWTResourceManager.getImage(LoginUI.class,
				"/image/land_reset.png"));
		button_1.setBounds(210, 267, 49, 19);

		final Button button_2 = new Button(shell, SWT.NONE);
		button_2.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent e) {
				button_2.setImage(SWTResourceManager.getImage(LoginUI.class,
						"/image/land_exit_over.png"));
			}

			@Override
			public void mouseExit(MouseEvent e) {
				button_2.setImage(SWTResourceManager.getImage(LoginUI.class,
						"/image/land_exit.png"));
			}
		});
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				button_2.setImage(SWTResourceManager.getImage(LoginUI.class,
						"/image/land_exit_pressed.png"));
			}

			@Override
			public void mouseUp(MouseEvent e) {
				button_2.setImage(SWTResourceManager.getImage(LoginUI.class,
						"/image/land_exit.png"));
			}
		});
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
		button_2.setImage(SWTResourceManager.getImage(LoginUI.class,
				"/image/land_exit.png"));
		button_2.setBounds(285, 267, 49, 19);
		
		combo_1 = new Combo(shell, SWT.NONE);
		combo_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				combo.removeAll();
				try {
					if(combo_1.getText().equals("收银员"))
						list = dao.findAllusers(1);
					if(combo_1.getText().equals("管理员"))
						list = dao.findAllusers(0);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for (Map<String, Object> map : list) {
					combo.add(map.get("NAME").toString());
				}
			}
		});
		combo_1.setBounds(246, 230, 61, 25);
		combo_1.setText("收银员");
		combo_1.add("收银员");
		combo_1.add("管理员");
		
		Label label = new Label(shell, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setBounds(179, 158, 61, 17);
		label.setText("登录用户:");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setText("用户密码:");
		label_1.setBounds(179, 191, 61, 17);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_2.setText("用户类型:");
		label_2.setBounds(179, 233, 61, 17);
		try {
			list = dao.findAllusers(1);
			for (Map<String, Object> map : list) {
				combo.add(map.get("NAME").toString());
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}

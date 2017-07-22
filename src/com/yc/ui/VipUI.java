package com.yc.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


//import org.apache.log4j.chainsaw.Main;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;


//import com.yc.commons.LogUtil;
import com.yc.commons.MessageUtil;
import com.yc.dao.SeatDao;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class VipUI {

	protected Shell shell;
	SeatDao seatDao = new SeatDao();
	Composite composite;
	Composite composite_1;
	StackLayout stackLayout;
	protected Composite com_info;
	Composite com_find;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	Label label_name;
	Label label_name1;
	Label label_name2;
	Label label_name3;
	private Table table;
	private Text text_4;
	String image;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			VipUI window = new VipUI();
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
		shell.setImage(SWTResourceManager.getImage(VipUI.class, "/image/ca31062b6693f580-1bb360fd7cf46844-26de80ae18859a6a8d708d698a02de94.jpg"));
		shell.setSize(450, 300);
		shell.setText("会员管理");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		Dimension dem = Toolkit.getDefaultToolkit().getScreenSize();
		// 窗体居中显示
		shell.setLocation((dem.width - shell.getSize().x) / 2,
				(dem.height - shell.getSize().y) / 2);
		
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem mntmNewSubmenu = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu.setText("会员管理");
		
		Menu menu_1 = new Menu(mntmNewSubmenu);
		mntmNewSubmenu.setMenu(menu_1);
		
		MenuItem mntmNewItem = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl=composite;
				com_info.layout();
			}
		});
		
		mntmNewItem.setText("会员注册");
		
		MenuItem mntmNewItem_1 = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl=composite_1;
				com_info.layout();
			}
		});
	
		mntmNewItem_1.setText("信息查询");
		
		com_info = new Composite(shell, SWT.NONE);
		
		 stackLayout=new StackLayout();
		com_info.setLayout(stackLayout);
		 Composite com_welcome = new Composite(com_info, SWT.NONE);
		 com_welcome.setBackgroundMode(SWT.INHERIT_DEFAULT);
		 com_welcome.setBackgroundImage(SWTResourceManager.getImage(VipUI.class, "/image/EWNXS4S~[([OY(7N7B[~20M.jpg"));
		 com_welcome.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		 com_welcome.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
	
		 stackLayout.topControl = com_welcome;//设置默认栈顶面板
		 
		 Label label_1 = new Label(com_welcome, SWT.NONE);
		 label_1.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_ARROW));
		 label_1.setAlignment(SWT.CENTER);
		// label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		 label_1.setText("会员信息管理系统");
		 label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		 label_1.setFont(SWTResourceManager.getFont("华文行楷", 30, SWT.BOLD));
		 label_1.setBounds(23, 98, 401, 52);
		
		composite = new Composite(com_info, SWT.NONE);
		composite.setBackgroundImage(SWTResourceManager.getImage(VipUI.class, "/image/EWNXS4S~[([OY(7N7B[~20M.jpg"));
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		
		label_name = new Label(composite, SWT.NONE);
		label_name.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		//label_name.setBackgroundImage(SWTResourceManager.getImage(VipUI.class, "/img/EWNXS4S~[([OY(7N7B[~20M.jpg"));
	//	label_name.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		label_name.setBounds(10, 24, 61, 17);
		label_name.setText("会员姓名");
		text = new Text(composite, SWT.BORDER);
		text.addFocusListener(new FocusAdapter() {
			//文本框失去焦点时，触发事件，判断文本框中的数据格式是否正确，此文本框只允许输入汉字
			@Override
			public void focusLost(FocusEvent e) {
				//获取文本框中的名字
				String username=text.getText();
				if(username.matches("[\u4E00-\u9FA5]{2,10}")){
					label_name1.setText("");
				}else{
					label_name1.setText("必须输入汉字，长度在2-10之间");
				}
			}
		});
		text.setBounds(94, 21, 117, 23);
		
		label_name1 = new Label(composite, SWT.NONE);
		//label_name1.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		label_name1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		label_name1.setBounds(233, 24, 201, 17);
		
		Label label_adress = new Label(composite, SWT.NONE);
		label_adress.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		//label_adress.setBackgroundImage(SWTResourceManager.getImage(VipUI.class, "/img/EWNXS4S~[([OY(7N7B[~20M.jpg"));
		//label_adress.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		label_adress.setText("邮箱");
		label_adress.setBounds(10, 76, 61, 17);
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String address = text_1.getText();
				if(address.matches("\\w+@\\w{2,6}(\\.\\w{2,3})+")){
					label_name2.setText("");
				}else{
					label_name2.setText("您输入的格式有误");
				}
			}
		});
		text_1.setBounds(94, 73, 117, 23);
		
		label_name2 = new Label(composite, SWT.NONE);
		//label_name2.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		label_name2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		label_name2.setBounds(233, 76, 201, 17);
		
		Label label_number = new Label(composite, SWT.NONE);
		label_number.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
	//	label_number.setBackgroundImage(SWTResourceManager.getImage(VipUI.class, "/img/EWNXS4S~[([OY(7N7B[~20M.jpg"));
		//label_number.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		label_number.setText("电话号码");
		label_number.setBounds(10, 128, 61, 17);
		
		Label label_discount = new Label(composite, SWT.NONE);
		label_discount.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		//label_discount.setBackgroundImage(SWTResourceManager.getImage(VipUI.class, "/img/EWNXS4S~[([OY(7N7B[~20M.jpg"));
		//label_discount.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		label_discount.setText("折扣");
		label_discount.setBounds(10, 180, 61, 17);
		
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String  telphone= text_2.getText();
				if(telphone.matches("1[34578]\\d{9}")){
					label_name3.setText("");
				}else{
					label_name3.setText("首字为1第二个为3或8的11个数字组合");
				}
			}
		});
		text_2.setBounds(94, 125, 117, 23);
		
		label_name3 = new Label(composite, SWT.NONE);
		//label_name3.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		label_name3.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		label_name3.setBounds(233, 128, 201, 17);
		
		text_3 = new Text(composite, SWT.BORDER);
		text_3.setBounds(94, 177, 117, 23);
		
		Button button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String username=text.getText();//获取会员姓名
				String address=text_1.getText();//获取邮箱
				String telephone = text_2.getText();//获取电话号码
				String discount = text_3.getText();//获取折扣
			

				List<Object> params=new ArrayList<Object>();
			
				params.add(username);
				params.add(address);
				params.add(telephone);
				params.add(discount);
				
			if (getSelection()){
			try {
				boolean flag = true;
				try {
					flag = seatDao.registerVip(params);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(flag){
					MessageUtil.promt(shell, "温馨提示","会员注册成功!");
					//清除页面上的数据
					text.setText("");
					text_1.setText("");
					text_2.setText("");
					text_3.setText("");
					
				}else{
					MessageUtil.promt(shell, "错误提示","学籍注册失败!");
				}
				//MessageUtil.promt(shell, "温馨提示","注册成功");
				
			} catch (IOException e1) {
				MessageUtil.promt(shell, "出错了", e1.getMessage());
			}
			}else {
				MessageUtil.promt(shell, "温馨提示", "请完善会员信息");
			}
			}

			private boolean getSelection() {
				if(text.getText().equals("")||text_1.getText().equals("")||text_2.getText().equals("")||text_3.getText().equals("")){
					return false;
				} else {
					return true;
				}
			}
		});
		button.setBounds(153, 205, 80, 27);
		button.setText("注册");
		
		composite_1 = new Composite(com_info, SWT.NONE);
		composite_1.setBackgroundImage(SWTResourceManager.getImage(VipUI.class, "/image/EWNXS4S~[([OY(7N7B[~20M.jpg"));
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		
		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 76, 414, 156);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(68);
		tblclmnNewColumn.setText("会员编号");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(78);
		tblclmnNewColumn_1.setText("会员姓名");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(103);
		tblclmnNewColumn_2.setText("邮箱");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(108);
		tblclmnNewColumn_3.setText("电话号码");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_4.setWidth(55);
		tblclmnNewColumn_4.setText("折扣");
		
		Label label_family = new Label(composite_1, SWT.NONE);
		label_family.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		label_family.setBounds(10, 33, 61, 17);
		label_family.setText("会员编号");
		
		text_4 = new Text(composite_1, SWT.BORDER);
		text_4.setBounds(94, 27, 97, 23);
		
		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			//查询所有会员信息
			@Override
			public void widgetSelected(SelectionEvent e) {
				String info = text_4.getText().trim();
				//表示文本框没有输入查询条件
				if(null == info || "".equals(info)){
					//查看所有信息
					try{
						List <Map<String,Object>> list = seatDao.findAllVip();
						table.removeAll();//清除表格中的数据
						if(null != list && list.size()>0){
							for(Map<String,Object> map:list){
								//System.out.println(map.toString());
								TableItem tableItem2 = new TableItem(table,SWT.NONE);
								tableItem2.setText(new String[]{		
										map.get("VIP_ID").toString(),
										map.get("VIP_NAME").toString(),
										map.get("VIP_MAILBOX").toString(),
										map.get("VIP_TEL").toString(),
										map.get("VIP_DISCOUNT").toString()});
							}
						}else{
							MessageUtil.promt(shell, "温馨提示","无会员信息");
						}
					}catch(SQLException e1){
						e1.printStackTrace();
					}catch(IOException e2){
						e2.printStackTrace();
					}
					return;
				}		
				
				String type = text_4.getText();
				if(type.matches("[\\d]+")){
					int did = Integer.parseInt(type);
					//根据姓名查询
					try{
						List <Map<String,Object>> list = seatDao.findVipByName(did);
						table.removeAll();//清除表格中的数据
						if(null != list && list.size()>0){
							for(Map<String,Object> map:list){
								TableItem tableItem2 = new TableItem(table,SWT.NONE);
								tableItem2.setText(new String[]{
										map.get("VIP_ID").toString(),
										map.get("VIP_NAME").toString(),
										map.get("VIP_MAILBOX").toString(),
										map.get("VIP_TEL").toString(),
										map.get("VIP_DISCOUNT").toString()});
							}
						}else{
							MessageUtil.promt(shell, "温馨提示","无会员信息");
						}
					}catch(SQLException e1){
//						LogUtil.logger.info(e1.getMessage()+new Date());
					}catch(IOException e2){
//						LogUtil.logger.info(e2.getMessage()+new Date());
					}
				}else{ 
					MessageUtil.promt(shell, "温馨提示","请输入正确的会员号");
					text_4.setText("");
				}
			
			}
		});
		btnNewButton.setBounds(307, 27, 80, 27);
		btnNewButton.setText("查询");
		
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);

	}

	
}

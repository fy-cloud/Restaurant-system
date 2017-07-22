package com.yc.ui;

import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import com.yc.commons.MessageUtil;
import com.yc.commons.TableColumnSorter;
import com.yc.dao.AdminDao;

public class AdminMain {

	Shell shell;
	Display display;
	Text name_text;
	Text pwd_text;
	Text idcard_text;
	Label lblNewLabel_5;
	Combo combo_year;
	Combo combo_month;
	Combo combo_day;
	Button boy;
	Button girl;
	Table table;
	TableItem tableItem;
	String userid = "";
	Table table_1;
	TableItem[] allname;

	AdminDao adminDao = new AdminDao();
	
	//awt获取屏幕高宽
		private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
		private Table table_2;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AdminMain window = new AdminMain();
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
		shell = new Shell(display, SWT.CLOSE | SWT.ON_TOP);
		shell.setSize(700, 380);
		// 得到屏幕的宽度和高度
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		// 得到Shell窗口的宽度和高度
		int shellHeight = shell.getBounds().height;
		int shellWidth = shell.getBounds().width;
		// 让窗口在屏幕中间显示
		shell.setLocation(((screenWidth - shellWidth) / 2), ((screenHeight - shellHeight) / 2));

		Label label_name = new Label(shell, SWT.NONE);
		label_name.setBounds(98, 10, 32, 17);
		label_name.setText("姓名:");

		name_text = new Text(shell, SWT.BORDER);
		name_text.setBounds(136, 7, 99, 23);

		Label label_sex = new Label(shell, SWT.NONE);
		label_sex.setBounds(267, 10, 32, 17);
		label_sex.setText("性别:");

		boy = new Button(shell, SWT.RADIO);
		boy.setBounds(305, 10, 33, 17);
		boy.setText("男");

		girl = new Button(shell, SWT.RADIO);
		girl.setBounds(344, 10, 33, 17);
		girl.setText("女");

		Label label_bir = new Label(shell, SWT.NONE);
		label_bir.setBounds(405, 10, 57, 17);
		label_bir.setText("出生日期:");

		combo_year = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
		for (int i = 1970; i < 2016; i++) {
			combo_year.add(i + "");
		}
		combo_year.setBounds(468, 7, 52, 25);

		combo_month = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
		for (int i = 1; i < 13; i++) {
			combo_month.add(i + "");
		}
		combo_month.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (combo_year.getText().equals(""))
					MessageUtil.promt(shell, "温馨提示", "请选择年份！");
				else {
					int b = Integer.parseInt(combo_year.getText());
					boolean years = adminDao.isRun(b);
					if (combo_month.getText().equals("1") || combo_month.getText().equals("3")
							|| combo_month.getText().equals("5") || combo_month.getText().equals("7")
							|| combo_month.getText().equals("8") || combo_month.getText().equals("10")
							|| combo_month.getText().equals("12")) {
						combo_day.removeAll();
						for (int i = 1; i <= 31; i++) {
							combo_day.add(i + "");
						}

					} else if (combo_month.getText().equals("2") && years) {
						combo_day.removeAll();
						for (int i = 1; i <= 29; i++) {
							combo_day.add(i + "");
						}
					} else if (combo_month.getText().equals("4") || combo_month.getText().equals("6")
							|| combo_month.getText().equals("9") || combo_month.getText().equals("11")) {
						combo_day.removeAll();
						for (int i = 1; i <= 30; i++) {
							combo_day.add(i + "");
						}
					} else {
						combo_day.removeAll();
						for (int i = 1; i <= 28; i++) {
							combo_day.add(i + "");
						}
					}
				}
			}
		});
		combo_month.setBounds(526, 7, 45, 25);

		combo_day = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
		combo_day.setBounds(577, 7, 44, 25);

		Label label_pwd = new Label(shell, SWT.NONE);
		label_pwd.setBounds(71, 42, 59, 17);
		label_pwd.setText("  登录密码:");

		pwd_text = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		pwd_text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				lblNewLabel_5.setText("*密码应为6-10位数字或字母");
			}

			@Override
			public void focusLost(FocusEvent e) {
				String pwd = pwd_text.getText();
				if (pwd.matches("[a-zA-Z0-9]{6,10}")) {
					lblNewLabel_5.setText("");
				} else {
					lblNewLabel_5.setText("*密码输入不合法!!!");
				}
			}
		});
		pwd_text.setBounds(136, 39, 241, 23);

		lblNewLabel_5 = new Label(shell, SWT.NONE);
		lblNewLabel_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_5.setBounds(136, 65, 202, 15);

		Label label_idcard = new Label(shell, SWT.NONE);
		label_idcard.setBounds(393, 42, 69, 17);
		label_idcard.setText("   身份证号:");

		idcard_text = new Text(shell, SWT.BORDER);
		idcard_text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String id_card = idcard_text.getText();
				if (id_card.matches("[0-9]{15}|[0-9]{18}|[0-9]{17}(x|X)")) {
				} else {
					idcard_text.setText("");
					MessageUtil.promt(shell, "温馨提示", "请输入有效的身份证号码");
				}
			}
		});
		idcard_text.setBounds(468, 39, 153, 23);

		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setBounds(0, 101, 694, 250);

		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("用户");

		table = new Table(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
		tabItem.setControl(table);
		table.addListener(SWT.MouseDown, new Listener() {
			@Override
			public void handleEvent(Event event) {
				tableItem = table.getItem(new Point(event.x, event.y));
				if (tableItem == null) {
					return;
				} else {
					userid = tableItem.getText(0);
				}
			}
		});
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.setWidth(94);
		tblclmnNewColumn.setText("       编号");
		table.getColumn(0).addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 调用排序文件，处理排序
				new TableColumnSorter().addNumberSorter(table, table.getColumn(0));
			}
		});		

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("姓名");
		table.getColumn(1).addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 调用排序文件，处理排序
				new TableColumnSorter().addStringSorter(table, table.getColumn(1));
			}
		});		

		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("性别");
		table.getColumn(2).addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 调用排序文件，处理排序
				new TableColumnSorter().addStringSorter(table, table.getColumn(2));
			}
		});		

		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_3.setWidth(129);
		tblclmnNewColumn_3.setText("出生日期");
		table.getColumn(3).addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 调用排序文件，处理排序
				new TableColumnSorter().addStringSorter(table, table.getColumn(3));
			}
		});		

		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_4.setWidth(257);
		tblclmnNewColumn_4.setText("身份证号码");
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
		mntmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!userid.equals("")) {
					List<Object> params = new ArrayList<Object>();
					params.add(1);
					params.add(userid);
					try {
						boolean falg = adminDao.deleteAdmin(params);

						if (falg) {
							MessageUtil.promt(shell, "温馨提示", "用户删除成功！");
							table.removeAll();
							List<Map<String, Object>> list = adminDao.findAllAdmin();
							for (Map<String, Object> map : list) {
								tableItem = new TableItem(table, SWT.NONE);
								tableItem.setText(new String[] { map.get("USERID").toString(),
										map.get("NAME").toString(), map.get("SEX").toString(),
										map.get("BIRTHDAY").toString(), map.get("ID_CARD").toString() });
								try {
									table_2.removeAll();
									List<Map<String, Object>> list1 = adminDao.findDeleteAdmin();
									for (Map<String, Object> map1 : list1) {
										tableItem = new TableItem(table_2, SWT.NONE);
										tableItem.setText(new String[] { map1.get("USERID").toString(), map1.get("NAME").toString(),
												map1.get("SEX").toString(), map1.get("BIRTHDAY").toString(), map1.get("ID_CARD").toString() });
									}
								} catch (SQLException e1) {
									e1.printStackTrace();
								} catch (IOException e1) {
									e1.printStackTrace();
								}

							}
						} else {
							MessageUtil.promt(shell, "出错了", "用户删除失败");
						}
					} catch (SQLException e1) {
						MessageUtil.promt(shell, "出错了", e1.getMessage());
					} catch (IOException e1) {
						MessageUtil.promt(shell, "出错了", e1.getMessage());
					}
					userid = "";
				} else {
					MessageUtil.promt(shell, "温馨提示", "请选择要删除的用户!");
				}
			}
		});
		mntmNewItem.setText("删除");
		
		table.getColumn(4).addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 调用排序文件，处理排序
				new TableColumnSorter().addNumberSorter(table, table.getColumn(4));
			}
		});		

		TabItem tabItem_1 = new TabItem(tabFolder, SWT.NONE);
		tabItem_1.setText("管理员");

		table_1 = new Table(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
		tabItem_1.setControl(table_1);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);

		TableColumn tblclmnNewColumn_5 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_5.setWidth(94);
		tblclmnNewColumn_5.setText("       编号");
		table_1.getColumn(0).addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 调用排序文件，处理排序
				new TableColumnSorter().addNumberSorter(table_1, table_1.getColumn(0));
			}
		});

		TableColumn tblclmnNewColumn_6 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_6.setWidth(100);
		tblclmnNewColumn_6.setText("姓名");
		table_1.getColumn(1).addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 调用排序文件，处理排序
				new TableColumnSorter().addStringSorter(table_1, table_1.getColumn(1));
			}
		});

		TableColumn tblclmnNewColumn_7 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_7.setWidth(100);
		tblclmnNewColumn_7.setText("性别");
		table_1.getColumn(2).addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 调用排序文件，处理排序
				new TableColumnSorter().addStringSorter(table_1, table_1.getColumn(2));
			}
		});

		TableColumn tblclmnNewColumn_8 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_8.setWidth(129);
		tblclmnNewColumn_8.setText("出生日期");
		table_1.getColumn(3).addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 调用排序文件，处理排序
				new TableColumnSorter().addStringSorter(table_1, table_1.getColumn(3));
			}
		});

		TableColumn tblclmnNewColumn_9 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_9.setWidth(257);
		tblclmnNewColumn_9.setText("身份证号码");
		
		TabItem tabItem_2 = new TabItem(tabFolder, SWT.NONE);
		tabItem_2.setText("已删除用户");
		
		table_2 = new Table(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
		tabItem_2.setControl(table_2);
		table_2.addListener(SWT.MouseDown, new Listener() {
			@Override
			public void handleEvent(Event event) {
				tableItem = table_2.getItem(new Point(event.x, event.y));
				if (tableItem == null) {
					return;
				} else {
					userid = tableItem.getText(0);
				}
			}
		});
		table_2.setHeaderVisible(true);
		table_2.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn_10 = new TableColumn(table_2, SWT.CENTER);
		tblclmnNewColumn_10.setWidth(94);
		tblclmnNewColumn_10.setText("       编号");
		table_2.getColumn(0).addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 调用排序文件，处理排序
				new TableColumnSorter().addNumberSorter(table_2, table_2.getColumn(0));
			}
		});	
		
		TableColumn tblclmnNewColumn_11 = new TableColumn(table_2, SWT.CENTER);
		tblclmnNewColumn_11.setWidth(100);
		tblclmnNewColumn_11.setText("姓名");
		table_2.getColumn(1).addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 调用排序文件，处理排序
				new TableColumnSorter().addStringSorter(table_2, table_2.getColumn(1));
			}
		});	
		
		TableColumn tblclmnNewColumn_12 = new TableColumn(table_2, SWT.CENTER);
		tblclmnNewColumn_12.setWidth(100);
		tblclmnNewColumn_12.setText("性别");
		table_2.getColumn(2).addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 调用排序文件，处理排序
				new TableColumnSorter().addStringSorter(table_2, table_2.getColumn(2));
			}
		});	
		
		TableColumn tblclmnNewColumn_13 = new TableColumn(table_2, SWT.CENTER);
		tblclmnNewColumn_13.setWidth(129);
		tblclmnNewColumn_13.setText("出生日期");
		table_2.getColumn(3).addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 调用排序文件，处理排序
				new TableColumnSorter().addStringSorter(table_2, table_2.getColumn(3));
			}
		});	
		
		TableColumn tblclmnNewColumn_14 = new TableColumn(table_2, SWT.CENTER);
		tblclmnNewColumn_14.setWidth(257);
		tblclmnNewColumn_14.setText("身份证号码");
		
		Menu menu_1 = new Menu(table_2);
		table_2.setMenu(menu_1);
		
		MenuItem mntmNewItem_1 = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!userid.equals("")) {
					List<Object> params = new ArrayList<Object>();
					params.add(0);
					params.add(userid);
					try {
						boolean falg = adminDao.deleteAdmin(params);

						if (falg) {
							MessageUtil.promt(shell, "温馨提示", "用户恢复成功！");
							table_2.removeAll();
							List<Map<String, Object>> list = adminDao.findDeleteAdmin();
							for (Map<String, Object> map : list) {
								tableItem = new TableItem(table_2, SWT.NONE);
								tableItem.setText(new String[] { map.get("USERID").toString(),
										map.get("NAME").toString(), map.get("SEX").toString(),
										map.get("BIRTHDAY").toString(), map.get("ID_CARD").toString() });
							}
							try {
								table.removeAll();
								List<Map<String, Object>> list1 = adminDao.findAllAdmin();
								for (Map<String, Object> map1 : list1) {
									tableItem = new TableItem(table, SWT.NONE);
									tableItem.setText(new String[] { map1.get("USERID").toString(), map1.get("NAME").toString(),
											map1.get("SEX").toString(), map1.get("BIRTHDAY").toString(), map1.get("ID_CARD").toString() });
								}
							} catch (SQLException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							
						} else {
							MessageUtil.promt(shell, "出错了", "用户恢复失败");
						}
					} catch (SQLException e1) {
						MessageUtil.promt(shell, "出错了", e1.getMessage());
					} catch (IOException e1) {
						MessageUtil.promt(shell, "出错了", e1.getMessage());
					}
					userid = "";
				} else {
					MessageUtil.promt(shell, "温馨提示", "请选择要恢复的用户!");
				}
			}
		});
		mntmNewItem_1.setText("恢复");
		
		table_2.getColumn(4).addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 调用排序文件，处理排序
				new TableColumnSorter().addNumberSorter(table_2, table_2.getColumn(4));
			}
		});	
		
		table_1.getColumn(4).addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 调用排序文件，处理排序
				new TableColumnSorter().addNumberSorter(table_1, table_1.getColumn(4));
			}
		});
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String name = name_text.getText();
				String password = pwd_text.getText();
				String id_card = idcard_text.getText();
				String sex = "";
				if (boy.getSelection()) {// 男选中
					sex = boy.getText();
				} else if (girl.getSelection()) {// 女选中
					sex = girl.getText();
				}
				String birthday = combo_year.getText() + "-" + combo_month.getText() + "-" + combo_day.getText();
				TableItem[] allname= table_1.getItems();
				for (TableItem tableItem : allname) {
					if (name.equals(tableItem.getText(1))) {
						MessageUtil.promt(shell, "温馨提示", "该管理员已存在！");
						// 清除页面上的数据
						name_text.setText("");
						pwd_text.setText("");
						idcard_text.setText("");
						return;
					}
				}
				List<Object> params = new ArrayList<Object>();
				params.add(name);
				params.add(sex);
				params.add(birthday);
				params.add(id_card);
				params.add(password);

				if (getSelection() && password.matches("[a-zA-Z0-9]{6,10}") && id_card.matches("[0-9]{15}|[0-9]{18}|[0-9]{17}(x|X)") ) {
					try {
						if(adminDao.isIDCardExist(id_card)){
							MessageUtil.promt(shell, "温馨提示", "该身份证号已存在,请重新输入!");
							return;
						}
						boolean flag = adminDao.registerManager(params);

						if (flag) {
							MessageUtil.promt(shell, "温馨提示", "管理员添加成功！");
							// 清除页面上的数据
							name_text.setText("");
							pwd_text.setText("");
							idcard_text.setText("");
							table_1.removeAll();
							List<Map<String, Object>> list = adminDao.findAllManager();
							for (Map<String, Object> map : list) {
								tableItem = new TableItem(table_1, SWT.NONE);
								tableItem.setText(new String[] { map.get("USERID").toString(),
										map.get("NAME").toString(), map.get("SEX").toString(),
										map.get("BIRTHDAY").toString(), map.get("ID_CARD").toString() });

							}
						} else {
							MessageUtil.promt(shell, "出错了", "管理员添加失败");
						}

					} catch (SQLException e1) {
						MessageUtil.promt(shell, "出错了", e1.getMessage());
					} catch (IOException e1) {
						MessageUtil.promt(shell, "出错了", e1.getMessage());
					}
				} else {
					MessageUtil.promt(shell, "温馨提示", "请完善管理员信息");
				}
				
			}
		});
		btnNewButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnNewButton.setBounds(393, 68, 80, 27);
		btnNewButton.setText("添加管理员");
		
		Button button_add = new Button(shell, SWT.NONE);
		button_add.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String name = name_text.getText();
				String password = pwd_text.getText();
				String id_card = idcard_text.getText();
				String sex = "";
				if (boy.getSelection()) {// 男选中
					sex = boy.getText();
				} else if (girl.getSelection()) {// 女选中
					sex = girl.getText();
				}
				String birthday = combo_year.getText() + "-" + combo_month.getText() + "-" + combo_day.getText();
				TableItem[] allname= table.getItems();
				for (TableItem tableItem : allname) {
					if (name.equals(tableItem.getText(1))) {
						MessageUtil.promt(shell, "温馨提示", "该用户已存在！");
						// 清除页面上的数据
						name_text.setText("");
						pwd_text.setText("");
						idcard_text.setText("");
						return;
					}
				}
				List<Object> params = new ArrayList<Object>();
				params.add(name);
				params.add(sex);
				params.add(birthday);
				params.add(id_card);
				params.add(password);

				if (getSelection() && password.matches("[a-zA-Z0-9]{6,10}") && id_card.matches("[0-9]{15}|[0-9]{18}|[0-9]{17}(x|X)") ) {
					try {
						if(adminDao.isIDCardExist(id_card)){
							MessageUtil.promt(shell, "温馨提示", "该身份证号已存在,请重新输入!");
							return;
						}
						boolean flag = adminDao.registerAdmin(params);

						if (flag) {
							MessageUtil.promt(shell, "温馨提示", "用户添加成功！");
							// 清除页面上的数据
							name_text.setText("");
							pwd_text.setText("");
							idcard_text.setText("");
							table.removeAll();
							List<Map<String, Object>> list = adminDao.findAllAdmin();
							for (Map<String, Object> map : list) {
								tableItem = new TableItem(table, SWT.NONE);
								tableItem.setText(new String[] { map.get("USERID").toString(),
										map.get("NAME").toString(), map.get("SEX").toString(),
										map.get("BIRTHDAY").toString(), map.get("ID_CARD").toString() });

							}
						} else {
							MessageUtil.promt(shell, "出错了", "用户添加失败");
						}

					} catch (SQLException e1) {
						MessageUtil.promt(shell, "出错了", e1.getMessage());
					} catch (IOException e1) {
						MessageUtil.promt(shell, "出错了", e1.getMessage());
					}
				} else {
					MessageUtil.promt(shell, "温馨提示", "请完善用户信息");
				}
			}
		});
		button_add.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		button_add.setBounds(495, 68, 59, 27);
		button_add.setText("添加用户");
		
		Button button_exit = new Button(shell, SWT.NONE);
		button_exit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		button_exit.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		button_exit.setText("退出");
		button_exit.setBounds(574, 68, 59, 27);

		// 加载页面时执行查看所有用户信息
		try {
			List<Map<String, Object>> list = adminDao.findAllAdmin();
			for (Map<String, Object> map : list) {
				tableItem = new TableItem(table, SWT.NONE);
				tableItem.setText(new String[] { map.get("USERID").toString(), map.get("NAME").toString(),
						map.get("SEX").toString(), map.get("BIRTHDAY").toString(), map.get("ID_CARD").toString() });
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// 加载页面时执行查看所有管理员信息
		try {
			List<Map<String, Object>> list = adminDao.findAllManager();
			for (Map<String, Object> map : list) {
				tableItem = new TableItem(table_1, SWT.NONE);
				tableItem.setText(new String[] { map.get("USERID").toString(), map.get("NAME").toString(),
						map.get("SEX").toString(), map.get("BIRTHDAY").toString(), map.get("ID_CARD").toString() });
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// 加载页面时执行查看所有已删除用户信息
		try {
			List<Map<String, Object>> list = adminDao.findDeleteAdmin();
			for (Map<String, Object> map : list) {
				tableItem = new TableItem(table_2, SWT.NONE);
				tableItem.setText(new String[] { map.get("USERID").toString(), map.get("NAME").toString(),
						map.get("SEX").toString(), map.get("BIRTHDAY").toString(), map.get("ID_CARD").toString() });
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}


	}
	
	public boolean getSelection() {
		if (name_text.getText().equals("") || (boy.getSelection() == false && girl.getSelection() == false)
				|| combo_day.getText().equals("") || idcard_text.getText().equals("")) {
			return false;
		} else {
			return true;
		}
	}
}

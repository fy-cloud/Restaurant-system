package com.yc.ui;

import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.wb.swt.SWTResourceManager;


import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import com.yc.commons.LoginUserInfo;
import com.yc.commons.MessageUtil;
import com.yc.commons.TableColumnSorter;
import com.yc.dao.ObligateDao;


public class ObligateMain {

	Shell shell;
	Display display;
	Text text_4;
	Label label_date;
	Date date;
	DateFormat format;
	Text text_3;
	Table table;
	Table table_1;
	Text text_1;
	Text text_2;
	TableItem tableItem;
	Combo combo_2;
	Spinner spinner_2;
	TableItem[] allmname;
	String did;
	Combo combo;
	Combo combo_1;
	Combo combo_3;
	DateTime dateTime;
	DateTime dateTime_1;
	DateTime dateTime_2;
	
	int row ;
	
	ObligateDao obligateDao = new ObligateDao();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ObligateMain window = new ObligateMain();
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
		shell.setImage(SWTResourceManager.getImage(ObligateMain.class, "/image/预定.jpg"));
		shell.setSize(480, 560);
		shell.setText("新增预定");
		// 得到屏幕的宽度和高度
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		// 得到Shell窗口的宽度和高度
		int shellHeight = shell.getBounds().height;
		int shellWidth = shell.getBounds().width;
		// 让窗口在屏幕中间显示
		shell.setLocation(((screenWidth - shellWidth) / 2), ((screenHeight - shellHeight) / 2));
		
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13, SWT.NORMAL));
		lblNewLabel.setBounds(10, 10, 74, 25);
		lblNewLabel.setText("新增预定");
		
		Label label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 41, 454, 2);
		
		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setBounds(20, 54, 51, 17);
		lblNewLabel_3.setText("预定会员:");
		
		combo = new Combo(shell, SWT.READ_ONLY);
		combo.add("");
		//下拉列表插入所有VIP
		try {
			List<Map<String, Object>> list = obligateDao.findAllVip();
			for (Map<String, Object> map : list) {
				combo.add(map.get("VIP_ID").toString()+"  "+map.get("VIP_NAME").toString());
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		combo.setBounds(80, 49, 311, 17);
		combo.setToolTipText("非会员也可预订");
		
		Label label_1 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(10, 80, 454, 2);
		
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setBounds(10, 148, 454, 232);
		
		TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("预订信息");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_1.setControl(composite_1);
		
		Label lblNewLabel_4 = new Label(composite_1, SWT.NONE);
		lblNewLabel_4.setBounds(59, 24, 51, 17);
		lblNewLabel_4.setText("预订日期:");
		
		dateTime = new DateTime(composite_1, SWT.BORDER);
		dateTime.setBounds(130, 20, 88, 24);
		
		Label lblNewLabel_15 = new Label(composite_1, SWT.NONE);
		lblNewLabel_15.setBounds(59, 67, 61, 17);
		lblNewLabel_15.setText("预订时段:");
		
		combo_3 = new Combo(composite_1, SWT.DROP_DOWN | SWT.READ_ONLY);
		combo_3.add("");
		combo_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (combo_3.getText().equals("早市")) {
					dateTime_1.setTime(6, 0, 0);
					dateTime_2.setTime(7, 0, 0);
				}else if(combo_3.getText().equals("午市")){
					dateTime_1.setTime(11, 0, 0);
					dateTime_2.setTime(12, 0, 0);
				}else if (combo_3.getText().equals("晚市")) {
					dateTime_1.setTime(16, 0, 0);
					dateTime_2.setTime(17, 0, 0);
				}else if (combo_3.getText().equals("夜市")){
					dateTime_1.setTime(22, 0, 0);
					dateTime_2.setTime(23, 0, 0);
				}
			}
		});
		combo_3.setItems(new String[] {"早市", "午市", "晚市", "夜市"});
		combo_3.setBounds(130, 64, 118, 25);
		

		Label lblNewLabel_5 = new Label(composite_1, SWT.NONE);
		lblNewLabel_5.setBounds(59, 113, 51, 17);
		lblNewLabel_5.setText("保留时间:");
		
		dateTime_1 = new DateTime(composite_1, SWT.BORDER | SWT.TIME | SWT.SHORT);
		dateTime_1.setBounds(130, 109, 88, 24);
		
		dateTime_2 = new DateTime(composite_1, SWT.BORDER | SWT.TIME | SWT.SHORT);
		dateTime_2.setBounds(239, 109, 80, 24);
		
		
		Label lblNewLabel_6 = new Label(composite_1, SWT.NONE);
		lblNewLabel_6.setBounds(59, 161, 51, 17);
		lblNewLabel_6.setText("预计人数:");
		
		text_3 = new Text(composite_1, SWT.BORDER);
		text_3.setBounds(130, 158, 88, 23);
	
		TabItem tbtmNewItem_2 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_2.setText("预定餐台");
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_2.setControl(composite_2);
		
		Label lblNewLabel_2 = new Label(composite_2, SWT.NONE);
		lblNewLabel_2.setBounds(54, 25, 51, 17);
		lblNewLabel_2.setText("选择餐台:");
		
		combo_1 = new Combo(composite_2, SWT.READ_ONLY);
		//下拉列表插入所有可选座位
		try {
			List<Map<String, Object>> list = obligateDao.findAllkxDesk();
			for (Map<String, Object> map : list) {
				combo_1.add(map.get("DID").toString());
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		combo_1.setBounds(123, 21, 110, 25);
		combo_1.setToolTipText("仅可选择空闲座位");
		
		table = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(58, 62, 345, 130);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.setWidth(163);
		tblclmnNewColumn.setText("             台号");
		
		table.getColumn(0).addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 调用排序文件，处理排序
				new TableColumnSorter().addNumberSorter(table, table.getColumn(0));
			}
		});
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.setWidth(160);
		tblclmnNewColumn_1.setText("座位数");
		
		table.getColumn(1).addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 调用排序文件，处理排序
				new TableColumnSorter().addNumberSorter(table, table.getColumn(1));
			}
		});
		
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("预订菜品");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(composite);
		
		Label lblNewLabel_11 = new Label(composite, SWT.NONE);
		lblNewLabel_11.setBounds(20, 14, 51, 17);
		lblNewLabel_11.setText("添加菜品:");
		
		combo_2 = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
		//下拉列表插入所有可选菜品
		try {
			List<Map<String, Object>> list = obligateDao.findAllMenu();
			for (Map<String, Object> map : list) {
				combo_2.add(map.get("MNAME").toString());
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		combo_2.setBounds(81, 10, 110, 25);
		
		Label lblNewLabel_12 = new Label(composite, SWT.NONE);
		lblNewLabel_12.setBounds(203, 14, 51, 17);
		lblNewLabel_12.setText("消费数量:");
		
		spinner_2 = new Spinner(composite, SWT.BORDER | SWT.READ_ONLY);
		spinner_2.setBounds(264, 12, 47, 23);
		
		Button btnNewButton_3 = new Button(composite, SWT.NONE);
		btnNewButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String mname = combo_2.getText();
				String number = spinner_2.getText();
				TableItem[] allmname= table_1.getItems();
				for (TableItem tableItem : allmname) {
					if (mname.equals(tableItem.getText(0))) {
						tableItem.setText(3, Integer.parseInt(tableItem.getText(3))+Integer.parseInt(number)+"");
						tableItem.setText(4, Integer.parseInt(tableItem.getText(2))*Integer.parseInt(tableItem.getText(3))+"");
						return;
					}
				}
				List<Object> params = new ArrayList<Object>();
				params.add(mname);
				if (getSelection()) {
					Map<String, String> map = null;
					try {
						map =obligateDao.findSelectMenu(params);
					} catch (SQLException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					tableItem = new TableItem(table_1, SWT.NONE);
					tableItem.setText(new String[] { map.get("MNAME").toString(), 
								map.get("UNIT").toString(),map.get("UNIT_PRICE").toString(),number,
								Integer.parseInt(map.get("UNIT_PRICE").toString())*Integer.parseInt(number)+""});
				}else {
					MessageUtil.promt(shell, "温馨提示", "请选择菜品和数量");
				}
			}
		});
		btnNewButton_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnNewButton_3.setBounds(343, 10, 80, 27);
		btnNewButton_3.setText("确定");
		
		table_1 = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setBounds(20, 55, 403, 137);
		//已选菜品表点击得到该行行数
		table_1.addListener(SWT.MouseDown, new Listener() {
			@Override
			public void handleEvent(Event event) {
				tableItem = table_1.getItem(new Point(event.x, event.y));
				row = table_1.getSelectionIndex();
			}
		});
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_2.setWidth(95);
		tblclmnNewColumn_2.setText("    消费项目");
		

		table_1.getColumn(0).addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 调用排序文件，处理排序
				new TableColumnSorter().addStringSorter(table_1, table_1.getColumn(0));
			}
		});
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_3.setWidth(69);
		tblclmnNewColumn_3.setText("单位");
		
		table_1.getColumn(1).addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 调用排序文件，处理排序
				new TableColumnSorter().addStringSorter(table_1, table_1.getColumn(1));
			}
		});
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_4.setWidth(68);
		tblclmnNewColumn_4.setText("单价");
		
		table_1.getColumn(2).addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 调用排序文件，处理排序
				new TableColumnSorter().addNumberSorter(table_1, table_1.getColumn(2));
			}
		});
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_5.setWidth(78);
		tblclmnNewColumn_5.setText("数量");
		
		table_1.getColumn(3).addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 调用排序文件，处理排序
				new TableColumnSorter().addNumberSorter(table_1, table_1.getColumn(3));
			}
		});
		
		TableColumn tblclmnNewColumn_6 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_6.setWidth(88);
		tblclmnNewColumn_6.setText("金额");
		
		table_1.getColumn(4).addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 调用排序文件，处理排序
				new TableColumnSorter().addNumberSorter(table_1, table_1.getColumn(4));
			}
		});
		
		Menu menu = new Menu(table_1);
		table_1.setMenu(menu);
		
		MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
		//预订菜品table右击删除某行已选菜品
		mntmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (tableItem == null) {
					return;
				}else{
					table_1.remove(row);
				}
			}
		});
		mntmNewItem.setText("删除");
		
		Label label_2 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_2.setBounds(10, 386, 454, 2);
		
		Label lblNewLabel_7 = new Label(shell, SWT.NONE);
		lblNewLabel_7.setBounds(10, 394, 61, 17);
		lblNewLabel_7.setText("顾客留言:");
		
		text_4 = new Text(shell, SWT.BORDER);
		text_4.setBounds(80, 394, 384, 43);
		
		Label label_3 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_3.setBounds(10, 449, 454, 2);
		
		label_date = new Label(shell, SWT.NONE);
		label_date.setBounds(107, 502, 135, 17);
		
		Label lblNewLabel_9 = new Label(shell, SWT.NONE);
		lblNewLabel_9.setBounds(251, 502, 39, 17);
		lblNewLabel_9.setText("登记人:");
		
		Label lblNewLabel_10 = new Label(shell, SWT.NONE);
		lblNewLabel_10.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_10.setBounds(296, 502, 168, 17);
		//显示当前用户
		lblNewLabel_10.setText(LoginUserInfo.username);
		
		Label lblNewLabel_13 = new Label(shell, SWT.NONE);
		lblNewLabel_13.setBounds(20, 96, 51, 17);
		lblNewLabel_13.setText("顾客名称:");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(80, 92, 162, 23);
		
		Label lblNewLabel_14 = new Label(shell, SWT.NONE);
		lblNewLabel_14.setBounds(20, 125, 51, 17);
		lblNewLabel_14.setText("联系电话:");
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(80, 119, 162, 23);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!getObligateSelection()) {
					MessageUtil.promt(shell, "温馨提示", "请完善预订信息");
					return;
				}
				if (getVipSelection()) {
					String ordertime = dateTime.getYear()+"-0"+(dateTime.getMonth()+1)+"-0"+dateTime.getDay()+"-"+dateTime_1.getHours()+":"+dateTime_1.getMinutes();
					String effective = dateTime.getYear()+"-"+(dateTime.getMonth()+1)+"-"+dateTime.getDay()+"-"+dateTime_2.getHours()+":"+dateTime_2.getMinutes();
					String people = text_3.getText();
					String did = combo_1.getText();
					String vip_name = combo.getText();
					String vip_id = vip_name.split("  ")[0];
					String cname = vip_name.split("  ")[1];
					String ctel = text_2.getText();
					String message = text_4.getText();
					List<Object> params = new ArrayList<Object>();
					params.add(ordertime);
					params.add(effective);
					params.add(people);
					params.add(did);
					params.add(vip_id);
					params.add(cname);
					params.add(ctel);
					params.add(message);
					try {

						boolean flag = obligateDao.registerObligate(params);

						if (flag) {
							MessageUtil.promt(shell, "温馨提示", "预订成功！");
							// 清除页面上的数据
							text_3.setText("");
							combo_1.select(0);;
							combo.select(0);;
							text_1.setText("");
							text_2.setText("");
							text_4.setText("");
							table_1.removeAll();
							}else {
							MessageUtil.promt(shell, "出错了", "预订失败!");
						}

					} catch (SQLException e1) {
						MessageUtil.promt(shell, "出错了", e1.getMessage());
					} catch (IOException e1) {
						MessageUtil.promt(shell, "出错了", e1.getMessage());
					}
					
				} else if (getCustomerSelection()) {
					String ordertime = dateTime.getYear()+"-"+(dateTime.getMonth()+1)+"-"+dateTime.getDay()+"-"+dateTime_1.getHours()+"-"+dateTime_1.getMinutes();
					String effective = dateTime.getYear()+"-"+(dateTime.getMonth()+1)+"-"+dateTime.getDay()+"-"+dateTime_2.getHours()+"-"+dateTime_2.getMinutes();
					String people = text_3.getText();
					String did = combo_1.getText();
					String vip_id = "";
					String cname = text_1.getText();
					String ctel = text_2.getText();
					String message = text_4.getText();
					List<Object> params = new ArrayList<Object>();
					params.add(ordertime);
					params.add(effective);
					params.add(people);
					params.add(did);
					params.add(vip_id);
					params.add(cname);
					params.add(ctel);
					params.add(message);
					try {

						boolean flag = obligateDao.registerObligate(params);

						if (flag) {
							MessageUtil.promt(shell, "温馨提示", "预订成功！");
							// 清除页面上的数据
							text_3.setText("");
							combo_1.select(0);;
							combo.select(0);;
							text_1.setText("");
							text_2.setText("");
							text_4.setText("");
							table_1.removeAll();
							}else {
							MessageUtil.promt(shell, "出错了", "预订失败!");
						}

					} catch (SQLException e1) {
						MessageUtil.promt(shell, "出错了", e1.getMessage());
					} catch (IOException e1) {
						MessageUtil.promt(shell, "出错了", e1.getMessage());
					}
					
					
				} else {
					MessageUtil.promt(shell, "温馨提示", "请完善预订信息");
				}
	
			}
		});
		btnNewButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnNewButton.setBounds(265, 457, 112, 43);
		btnNewButton.setText("预订");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		//关闭界面
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnNewButton_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnNewButton_1.setBounds(384, 457, 80, 43);
		btnNewButton_1.setText("取消");
		
		//底部动态显示时间
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while (1 == 1) {
//					date = new Date();
//					format = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss");
//					Display.getDefault().asyncExec(new Runnable() {
//						@Override
//						public void run() {
//							label_date.setText(format.format(date));
//						}
//					});
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}).start();
		
		//显示所有座位
		try {
			List<Map<String, Object>> list = obligateDao.findAllDesk();
			for (Map<String, Object> map : list) {
				tableItem = new TableItem(table, SWT.NONE);
				tableItem.setText(new String[] { map.get("DID").toString(),map.get("QUANTITY").toString()});
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	// 预订菜品表--判断菜品和数量是否选择
	public boolean getSelection() {
		if (combo_2.getText().equals("") || spinner_2.getText().equals("0") ){
			return false;
		} else {
			return true;
		}
	}
	
	//vip预订
	public boolean getVipSelection() {
		if ( combo.getText().equals("") || combo_3.getText().equals("")
				|| combo_1.getText().equals("") || table_1.getItems().equals("")) {
			return false;
		} else {
			return true;
		}
	}
	
	//普通顾客预订
	public boolean getCustomerSelection() {
		if ( text_1.getText().equals("") || text_2.getText().equals("") || combo_3.getText().equals("")
				|| combo_1.getText().equals("") || table_1.getItems().equals("")) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean getObligateSelection() {
		if ( combo_3.getText().equals("")|| combo_1.getText().equals("") || table_1.getItems().equals("")) {
			return false;
		} else {
			return true;
		}
	}
}

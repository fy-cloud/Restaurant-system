package com.yc.ui;

import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;


import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;

import com.yc.commons.MessageUtil;
import com.yc.commons.TableColumnSorter;
import com.yc.dao.MenuDao;

public class FoodManagementUI {

	Shell shell;
	Display display;
	Text text_1;
	Text text_2;
	Text text_3;
	Text text_4;
	Combo combo;
	Table table;
	TableItem tableItem;
	String mid = "";
	MenuDao menuDao = new MenuDao();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			FoodManagementUI window = new FoodManagementUI();
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
		shell = new Shell(display, SWT.CLOSE | SWT.MIN | SWT.ON_TOP);
		shell.setImage(SWTResourceManager.getImage(FoodManagementUI.class, "/image/menu.jpg"));
		shell.setSize(680, 390);
		shell.setText("菜品管理");
		// 得到屏幕的宽度和高度
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		// 得到Shell窗口的宽度和高度
		int shellHeight = shell.getBounds().height;
		int shellWidth = shell.getBounds().width;
		// 让窗口在屏幕中间显示
		shell.setLocation(((screenWidth - shellWidth) / 2), ((screenHeight - shellHeight) / 2));
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(252, 19, 46, 17);
		label_1.setText("助记码:");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(302, 13, 100, 23);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setBounds(81, 19, 36, 17);
		label_2.setText("名称:");
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(123, 13, 112, 23);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setBounds(81, 47, 36, 17);
		label_3.setText("菜系:");
		
		combo = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
		try {
			List<Map<String, Object>> list = menuDao.findAllSort();
			for (Map<String, Object> map : list) {
				combo.add(map.get("SNAME").toString());
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		combo.setBounds(123, 42, 112, 25);
		
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(423, 22, 36, 17);
		lblNewLabel.setText("单位:");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(423, 52, 36, 17);
		lblNewLabel_1.setText("单价:");
		
		text_3 = new Text(shell, SWT.BORDER);
		text_3.setBounds(459, 16, 73, 23);
		
		text_4 = new Text(shell, SWT.BORDER);
		text_4.setBounds(459, 46, 73, 23);
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setBounds(538, 52, 17, 17);
		label_4.setText("元");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String sname = combo.getText();
				String mname = text_2.getText();
				String code = text_1.getText();
				String unit = text_3.getText();
				String unit_price = text_4.getText();
				List<Object> params = new ArrayList<Object>();
				params.add(sname);
				params.add(mname);
				params.add(code);
				params.add(unit);
				params.add(unit_price);
				if (getSelection()) {
					try {
						boolean flag = menuDao.registerMenu(params);
						if (flag) {
							MessageUtil.promt(shell, "温馨提示", "菜品添加成功！");
							// 清除页面上的数据
							text_2.setText("");
							text_1.setText("");
							text_3.setText("");
							text_4.setText("");
							table.removeAll();
							List<Map<String, Object>> list = menuDao.findAllMenu();
							for (Map<String, Object> map : list) {
								tableItem = new TableItem(table, SWT.NONE);
								tableItem.setText(new String[] { map.get("MID").toString(), 
										map.get("MNAME").toString(),map.get("CODE").toString(), 
										map.get("SNAME").toString(), map.get("UNIT").toString(),
										map.get("UNIT_PRICE").toString()  });
								
							}
						}else {
							MessageUtil.promt(shell, "出错了", "菜品添加失败");
						}

					} catch (SQLException e1) {
						MessageUtil.promt(shell, "出错了", e1.getMessage());
					} catch (IOException e1) {
						MessageUtil.promt(shell, "出错了", e1.getMessage());
					}
				}else {
					MessageUtil.promt(shell, "温馨提示", "请完善菜品信息");
				}
			}
		});
		btnNewButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		btnNewButton.setBounds(423, 86, 80, 27);
		btnNewButton.setText("添加");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!mid.equals("")) {
					List<Object> params = new ArrayList<Object>();
					params.add(0);
					params.add(mid);
					try {
						boolean falg = menuDao.deleteMenu(params);
						if (falg) {
							MessageUtil.promt(shell, "温馨提示", "菜品删除成功！");

							table.removeAll();
							List<Map<String, Object>> list = menuDao.findAllMenu();
							for (Map<String, Object> map : list) {
								tableItem = new TableItem(table, SWT.NONE);
								tableItem.setText(new String[] { map.get("MID").toString(), 
										map.get("MNAME").toString(),map.get("CODE").toString(), 
										map.get("SNAME").toString(), map.get("UNIT").toString(),
										map.get("UNIT_PRICE").toString()  });
							}
						} else {
							MessageUtil.promt(shell, "出错了", "菜品删除失败");
						}
					} catch (SQLException e1) {
						MessageUtil.promt(shell, "出错了", e1.getMessage());
					} catch (IOException e1) {
						MessageUtil.promt(shell, "出错了", e1.getMessage());
					}
					mid = "";
				} else {
					MessageUtil.promt(shell, "温馨提示", "请选择要删除的菜品!");
				}
			}
		});
		btnNewButton_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		btnNewButton_1.setBounds(525, 86, 80, 27);
		btnNewButton_1.setText("删除");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.addListener(SWT.MouseDown, new Listener() {
			@Override
			public void handleEvent(Event event) {
				tableItem = table.getItem(new Point(event.x, event.y));
				if (tableItem == null) {
					return;
				} else {
					mid = tableItem.getText(0);
				}
			}
		});
		table.setBounds(21, 131, 629, 175);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.setWidth(60);
		tblclmnNewColumn_1.setText("编号");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_2.setWidth(130);
		tblclmnNewColumn_2.setText("名称");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_3.setWidth(103);
		tblclmnNewColumn_3.setText("助记码");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_4.setWidth(116);
		tblclmnNewColumn_4.setText("菜系");
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_5.setWidth(97);
		tblclmnNewColumn_5.setText("单位");
		
		TableColumn tblclmnNewColumn_6 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_6.setWidth(102);
		tblclmnNewColumn_6.setText("单价");
		
		table.getColumn(0).addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 调用排序文件，处理排序
				new TableColumnSorter().addNumberSorter(table, table.getColumn(0));
			}
		});
		table.getColumn(1).addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 调用排序文件，处理排序
				new TableColumnSorter().addStringSorter(table, table.getColumn(1));
			}
		});
		table.getColumn(2).addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 调用排序文件，处理排序
				new TableColumnSorter().addStringSorter(table, table.getColumn(2));
			}
		});
		table.getColumn(3).addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 调用排序文件，处理排序
				new TableColumnSorter().addStringSorter(table, table.getColumn(3));
			}
		});
		table.getColumn(4).addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 调用排序文件，处理排序
				new TableColumnSorter().addStringSorter(table, table.getColumn(4));
			}
		});
		table.getColumn(5).addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 调用排序文件，处理排序
				new TableColumnSorter().addNumberSorter(table, table.getColumn(5));
			}
		});
		
		// 加载页面时显示所有菜品信息
		try {
			List<Map<String, Object>> list = menuDao.findAllMenu();
			for (Map<String, Object> map : list) {
				tableItem = new TableItem(table, SWT.NONE);
				tableItem.setText(new String[] { map.get("MID").toString(), 
						map.get("MNAME").toString(),map.get("CODE").toString(), 
						map.get("SNAME").toString(), map.get("UNIT").toString(),
						map.get("UNIT_PRICE").toString()  });
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	
	public boolean getSelection() {
		if (text_1.getText().equals("") || text_2.getText().equals("") 
			|| text_3.getText().equals("") || text_4.getText().equals("")
			|| combo.getText().equals("")){
			return false;
		} else {
			return true;
		}
	}
}

package com.yc.ui;

import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;

import com.yc.commons.MessageUtil;
import com.yc.dao.MenuDao1;

public class UpdateMenuUI {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Table table;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	MenuDao1 menuDao = new MenuDao1();
	TableItem tableItem;
	Display display;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UpdateMenuUI window = new UpdateMenuUI();
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
		shell.setImage(SWTResourceManager.getImage(UpdateMenuUI.class, "/image/ca31062b6693f580-1bb360fd7cf46844-26de80ae18859a6a8d708d698a02de94.jpg"));
		shell.setSize(464, 370);
		shell.setText("修改菜品");
		// 得到屏幕的宽度和高度
				int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
				int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
				// 得到Shell窗口的宽度和高度
				int shellHeight = shell.getBounds().height;
				int shellWidth = shell.getBounds().width;
				// 让窗口在屏幕中间显示
				shell.setLocation(((screenWidth - shellWidth) / 2), ((screenHeight - shellHeight) / 2));
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(0, 0, 448, 332);
		
		Label label = new Label(composite, SWT.NONE);
		label.setBounds(25, 20, 61, 17);
		label.setText("菜品名称");
		
		text = new Text(composite, SWT.BORDER);
		text.setEnabled(false);
		text.setBounds(116, 14, 88, 23);
		
		Button button_view = new Button(composite, SWT.NONE);
		button_view.addSelectionListener(new SelectionAdapter() {
			//显示所有菜品信息
			@Override
			public void widgetSelected(SelectionEvent e) {
				String info = text.getText().trim();
				//表示文本框没有输入查询条件
				if(null == info || "".equals(info)){
					//查看所有信息
					try{
						List <Map<String,Object>> list = menuDao.findAllMenu();
						table.removeAll();//清除表格中的数据
						if(null != list && list.size()>0){
							for(Map<String,Object> map:list){
								
								TableItem tableItem2 = new TableItem(table,SWT.NONE);
								tableItem2.setText(new String[]{
										map.get("MID").toString(),
										map.get("MNAME").toString(),
										map.get("CODE").toString(),
										map.get("SNAME").toString(),
										map.get("UNIT_PRICE").toString(),
										map.get("UNIT").toString()
										});
							}
						}else{
							MessageUtil.promt(shell, "温馨提示","无菜品信息");
						}
					}catch(SQLException e1){
						e1.printStackTrace();
					}catch(IOException e2){
						e2.printStackTrace();
					}
					return;
				}
			}
		});
		button_view.setBounds(320, 10, 80, 27);
		button_view.setText("显示");
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setBounds(25, 196, 61, 17);
		label_1.setText("编号");
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setBounds(25, 235, 61, 17);
		label_3.setText("菜品名");
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setEnabled(false);
		text_1.setBounds(92, 193, 88, 23);
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setBounds(92, 232, 88, 23);
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 43, 415, 136);
		
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				tableItem = table.getItem(new Point(e.x,e.y));
				if(tableItem == null)
					return;
				text_1.setText(tableItem.getText(0));
				text_2.setText(tableItem.getText(1));
				text_3.setText(tableItem.getText(2));
				text_4.setText(tableItem.getText(3));
				text_5.setText(tableItem.getText(4));
				text_6.setText(tableItem.getText(5));
			}
		});
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(46);
		tableColumn.setText("编号");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(101);
		tableColumn_1.setText("菜品名");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(62);
		tableColumn_2.setText("助记码");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(68);
		tableColumn_3.setText("菜系名");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(63);
		tableColumn_4.setText("单位");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(71);
		tableColumn_5.setText("单价");
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setBounds(211, 196, 61, 17);
		label_2.setText("助记码");
		
		text_3 = new Text(composite, SWT.BORDER);
		text_3.setBounds(301, 193, 73, 23);
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setBounds(211, 235, 61, 17);
		label_4.setText("菜系名");
		
		text_4 = new Text(composite, SWT.BORDER);
		text_4.setBounds(301, 229, 73, 23);
		
		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setBounds(25, 271, 61, 17);
		label_5.setText("单价");
		
		text_5 = new Text(composite, SWT.BORDER);
		text_5.setEnabled(false);
		text_5.setBounds(92, 268, 88, 23);
		
		Label label_6 = new Label(composite, SWT.NONE);
		label_6.setBounds(211, 271, 61, 17);
		label_6.setText("单位");
		
		text_6 = new Text(composite, SWT.BORDER);
		text_6.setEnabled(false);
		text_6.setBounds(301, 268, 73, 23);
		
		Button button_update = new Button(composite, SWT.NONE);
		button_update.addSelectionListener(new SelectionAdapter() {
			//修改
			@Override
			public void widgetSelected(SelectionEvent e) {
				String info = text_2.getText().trim();
				//表示文本框没有输入查询条件
				if(null == info || "".equals(info)){
					//查看所有信息
					try{
						List <Map<String,Object>> list =menuDao.findAllMenu();
						table.removeAll();//清除表格中的数据
						if(null != list && list.size()>0){
							for(Map<String,Object> map:list){
								TableItem tableItem2 = new TableItem(table,SWT.NONE);
								tableItem2.setText(new String[]{
										map.get("MID").toString(),
										map.get("MNAME").toString(),
										map.get("CODE").toString(),
										map.get("SNAME").toString(),
										map.get("UNIT_PRICE").toString(),
										map.get("UNIT").toString()});
							}
						}else{
							MessageUtil.promt(shell, "温馨提示","无菜系信息");
						}
					}catch(SQLException e1){
						e1.printStackTrace();
					}catch(IOException e2){
						e2.printStackTrace();
					}
					return;
				}		
				
				String name = text_2.getText();
				String code = text_3.getText();
				if(name.matches("[\u4E00-\u9FA5]{2,10}")){
					text_2.setText("");
					text_3.setText("");
		
				String mid1 = tableItem.getText(0);
				int mid=  Integer.parseInt(mid1);
				int update1 = -1;
				//根据座位数修改
					try{
						 update1 = menuDao.update(name,code, mid);
					}catch(Exception e3){
						MessageUtil.promt(shell, "温馨提示","违反唯一约束，请重新输入");
						text_1.setText("");
						text_2.setText("");
					}
					
					if(update1 ==0){
						MessageUtil.promt(shell, "温馨提示","无菜品信息");
					}
					if(update1 == 1){
						//加载页面时执行查看所有信息
						try{
						
							table.removeAll();//清除表格中的数据
							List <Map<String,Object>> list = menuDao.findAllMenu();
								for(Map<String,Object> map:list){
									TableItem tableItem2 = new TableItem(table,SWT.NONE);
									tableItem2.setText(new String[]{
											map.get("MID").toString(),
											map.get("MNAME").toString(),
											map.get("CODE").toString(),
											map.get("SNAME").toString(),
											map.get("UNIT_PRICE").toString(),
											map.get("UNIT").toString()});
								}
								MessageUtil.promt(shell, "温馨提示","修改成功");
								text_1.setText("");
								text_2.setText("");
						}catch(SQLException e1){
							e1.printStackTrace();
						}catch(IOException e2){
							e2.printStackTrace();
						}
					}
				}else{
					MessageUtil.promt(shell, "温馨提示","必须输入汉字，长度在2-10之间");
					text_2.setText("");
				}
				
			}
		});
		button_update.setBounds(100, 297, 80, 27);
		button_update.setText("修改");
		
		Button button_exit = new Button(composite, SWT.NONE);
		button_exit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageUtil.promt(shell, "温馨提示","请问您是否需要退出");
				shell.close();
			}
		});
		button_exit.setBounds(260, 297, 80, 27);
		button_exit.setText("退出");
		
		try{
			List <Map<String,Object>> list =menuDao.findAllMenu();
				for(Map<String,Object> map:list){
					TableItem tableItem2 = new TableItem(table,SWT.NONE);
					tableItem2.setText(new String[]{	
							map.get("MID").toString(),
							map.get("MNAME").toString(),
							map.get("CODE").toString(),
							map.get("SNAME").toString(),
							map.get("UNIT_PRICE").toString(),
							map.get("UNIT").toString()});
					}
		}catch(SQLException e1){
			e1.printStackTrace();
		}catch(IOException e2){
			e2.printStackTrace();
		}

	}

}

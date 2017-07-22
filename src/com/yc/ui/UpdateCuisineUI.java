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
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;

import com.yc.commons.MessageUtil;
import com.yc.dao.SeatDao;

public class UpdateCuisineUI {

	protected Shell shell;
	private Text text;
	private Table table;
	SeatDao seatDao = new SeatDao();
	private Text text_1;
	private Text text_2;
	TableItem tableItem;
	Composite composite;
	TableCursor tableCursor;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UpdateCuisineUI window = new UpdateCuisineUI();
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
		shell.setImage(SWTResourceManager.getImage(UpdateCuisineUI.class, "/image/ca31062b6693f580-1bb360fd7cf46844-26de80ae18859a6a8d708d698a02de94.jpg"));
		shell.setSize(434, 300);
		shell.setText("修改菜系");
		// 得到屏幕的宽度和高度
				int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
				int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
				// 得到Shell窗口的宽度和高度
				int shellHeight = shell.getBounds().height;
				int shellWidth = shell.getBounds().width;
				// 让窗口在屏幕中间显示
				shell.setLocation(((screenWidth - shellWidth) / 2), ((screenHeight - shellHeight) / 2));
		composite = new Composite(shell, SWT.NONE);
		composite.setBounds(0, 0, 434, 262);
		
		Label label = new Label(composite, SWT.NONE);
		label.setBounds(22, 23, 61, 17);
		label.setText("菜系名称");
		
		text = new Text(composite, SWT.BORDER);
		text.setEditable(false);
		text.setBounds(115, 17, 87, 23);
		
		Button button_find = new Button(composite, SWT.NONE);
		button_find.addSelectionListener(new SelectionAdapter() {
			//显示
			@Override
			public void widgetSelected(SelectionEvent e) {
				String info = text.getText().trim();
				//表示文本框没有输入查询条件
				if(null == info || "".equals(info)){
					//查看所有信息
					try{
						List <Map<String,Object>> list = seatDao.findAllCuisine();
						table.removeAll();//清除表格中的数据
						if(null != list && list.size()>0){
							for(Map<String,Object> map:list){
								
								TableItem tableItem2 = new TableItem(table,SWT.NONE);
								tableItem2.setText(new String[]{map.get("SID").toString(),map.get("SNAME").toString()});
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
			
			}
		});
		button_find.setBounds(294, 13, 80, 27);
		button_find.setText("显示");
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 59, 402, 128);
		
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				tableItem = table.getItem(new Point(e.x,e.y));
				if(tableItem == null)
					return;
				text_1.setText(tableItem.getText(0));
				text_2.setText(tableItem.getText(1));
			}
		});
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(193);
		tableColumn.setText("编号");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(212);
		tableColumn_1.setText("名称");
		
		tableCursor = new TableCursor(table, SWT.NONE);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setBounds(22, 193, 61, 17);
		label_1.setText("编号");
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setBounds(22, 235, 61, 17);
		label_2.setText("名称");
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setEnabled(false);
		text_1.setBounds(115, 187, 87, 23);
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setBounds(115, 229, 87, 23);
		
		Button button_uodate = new Button(composite, SWT.NONE);
		button_uodate.addSelectionListener(new SelectionAdapter() {
			//修改
			@Override
			public void widgetSelected(SelectionEvent e) {
				String info = text_2.getText().trim();
				//表示文本框没有输入查询条件
				if(null == info || "".equals(info)){
					//查看所有信息
					try{
						List <Map<String,Object>> list = seatDao.findAllCuisine();
						table.removeAll();//清除表格中的数据
						if(null != list && list.size()>0){
							for(Map<String,Object> map:list){
								TableItem tableItem2 = new TableItem(table,SWT.NONE);
								tableItem2.setText(new String[]{map.get("SID").toString(),map.get("SNAME").toString()});
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
				if(name.matches("[\u4E00-\u9FA5]{2,10}")){
					text_2.setText("");
		
				String sid1 = tableItem.getText(0);
				int sid=  Integer.parseInt(sid1);
				int update1 = -1;
				//根据座位数修改
					try{
						 update1 = seatDao.updateByName(name, sid);
					}catch(Exception e3){
						MessageUtil.promt(shell, "温馨提示","违反唯一约束，请重新输入");
						text_1.setText("");
						text_2.setText("");
					}
					
					if(update1 ==0){
						MessageUtil.promt(shell, "温馨提示","无菜系信息");
					}
					if(update1 == 1){
						//加载页面时执行查看所有台号信息
						try{
						
							table.removeAll();//清除表格中的数据
							List <Map<String,Object>> list = seatDao.findAllCuisine();
								for(Map<String,Object> map:list){
									TableItem tableItem2 = new TableItem(table,SWT.NONE);
									tableItem2.setText(new String[]{map.get("SID").toString(),map.get("SNAME").toString()});
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
		button_uodate.setBounds(294, 193, 80, 27);
		button_uodate.setText("修改");
		
		Button button_exit = new Button(composite, SWT.NONE);
		button_exit.addSelectionListener(new SelectionAdapter() {
			//退出
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageUtil.promt(shell, "温馨提示","请问您是否需要退出");
				shell.close();
			}
		});
		button_exit.setBounds(294, 235, 80, 27);
		button_exit.setText("退出");
		
		
		
		//加载页面时执行查看所有菜系信息
		try{
			List <Map<String,Object>> list = seatDao.findAllCuisine();
				for(Map<String,Object> map:list){
					TableItem tableItem2 = new TableItem(table,SWT.NONE);
					tableItem2.setText(new String[]{map.get("SID").toString(),map.get("SNAME").toString()});
				}
		}catch(SQLException e1){
			e1.printStackTrace();
		}catch(IOException e2){
			e2.printStackTrace();
		}


	}

}

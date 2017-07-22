package com.yc.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;


//import com.yc.commons.LogUtil;
import com.yc.commons.MessageUtil;
import com.yc.dao.SeatDao;




import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class SeatManageUI {

	protected Shell shell;
	private Text text;
	Display display;
	private Table table;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	TableItem tableItem;
	SeatDao seatDao = new SeatDao();
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SeatManageUI window = new SeatManageUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public void open() throws IOException, SQLException {
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
	 * @throws SQLException 
	 * @throws IOException 
	 */
	protected void createContents() throws IOException, SQLException {
		shell = new Shell(display,SWT.MIN | SWT.CLOSE);
		//shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		shell.setImage(SWTResourceManager.getImage(SeatManageUI.class, "/image/ca31062b6693f580-1bb360fd7cf46844-26de80ae18859a6a8d708d698a02de94.jpg"));
		shell.setSize(450, 330);
		shell.setText("座位管理");
		Dimension dem = Toolkit.getDefaultToolkit().getScreenSize();
		// 窗体居中显示
		shell.setLocation((dem.width - shell.getSize().x) / 2,
				(dem.height - shell.getSize().y) / 2);
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(0, 0, 434, 69);
		
		Label label_find = new Label(composite, SWT.NONE);
		label_find.setBounds(10, 0, 61, 17);
		label_find.setText("查询");
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBounds(10, 30, 61, 23);
		lblNewLabel.setText("台号");
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(117, 30, 73, 23);
		

		
	
		
		Button find = new Button(composite, SWT.NONE);
		find.addSelectionListener(new SelectionAdapter() {
			//根据条件查询
			@Override
			public void widgetSelected(SelectionEvent e) {
				String info = text.getText().trim();
				if(null == info || "".equals(info)){
					//查看所有信息
					try{
						List <Map<String,Object>> list = seatDao.findAllSeat();
						table.removeAll();//清除表格中的数据
						if(null != list && list.size()>0){
							for(Map<String,Object> map:list){
								TableItem tableItem2 = new TableItem(table,SWT.NONE);
								tableItem2.setText(new String[]{
										map.get("DID").toString(),
										map.get("SEATING").toString()
										.equals("0") ? "空闲" : map
										.get("SEATING").toString()
										.equals("1") ? "使用中" : "被预定",
								map.get("QUANTITY").toString() });
							}
						}else{
							MessageUtil.promt(shell, "温馨提示","无台号信息");
						}
					}catch(SQLException e1){
						e1.printStackTrace();
					}catch(IOException e2){
						e2.printStackTrace();
					}
					return;
				}
				
				 String type = text.getText();
			
				if(type.matches("[\\d]+")){
					//表示文本框没有输入查询条件
					 int did1= Integer.parseInt(type);
								try{
									List <Map<String,Object>> list = seatDao.findSeatByID(did1);
									table.removeAll();//清除表格中的数据
									if(null != list && list.size()>0){
										for(Map<String,Object> map:list){
											TableItem tableItem2 = new TableItem(table,SWT.NONE);
											tableItem2.setText(new String[]{	map.get("DID").toString(),
													map.get("SEATING").toString()
													.equals("0") ? "空闲" : map
													.get("SEATING").toString()
													.equals("1") ? "使用中" : "被预定",
											map.get("QUANTITY").toString() });
											text.setText("");
										}
									}else{
										MessageUtil.promt(shell, "温馨提示","无台号信息");
									}
								}catch(SQLException e1){
//									LogUtil.logger.info(e1.getMessage()+new Date());
								}catch(IOException e2){
//									LogUtil.logger.info(e2.getMessage()+new Date());
								}
						}else{ 
							MessageUtil.promt(shell, "温馨提示","请输入正确的台号");
							text.setText("");
						}
			
			        	}
		});
		find.setBounds(295, 26, 80, 27);
		find.setText("查询");
		
		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setBounds(0, 67, 434, 124);
		
		Label label_manage = new Label(composite_1, SWT.NONE);
		label_manage.setBounds(10, 0, 61, 17);
		label_manage.setText("台号管理");
		
		
		
		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				tableItem = table.getItem(new Point(e.x,e.y));
				if(tableItem == null)
					return;
				text_1.setText(tableItem.getText(0));
				text_2.setText(tableItem.getText(1));
				text_3.setText(tableItem.getText(2));
				
				
			}
		});
		table.setBounds(0, 21, 434, 103);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		
		TableColumn columu_1 = new TableColumn(table, SWT.NONE);
		columu_1.setWidth(138);
		columu_1.setText("台号");
		
		TableColumn column_2 = new TableColumn(table, SWT.NONE);
		column_2.setWidth(150);
		column_2.setText("状态");
		
		TableColumn column_3 = new TableColumn(table, SWT.NONE);
		column_3.setWidth(144);
		column_3.setText("座位数");
	
	
		
		Composite composite_2 = new Composite(shell, SWT.NONE);
		composite_2.setBounds(0, 190, 434, 102);
		
		Label label_seatfind = new Label(composite_2, SWT.NONE);
		label_seatfind.setBounds(10, 0, 61, 17);
		label_seatfind.setText("座位查询");
		
		Label label_id = new Label(composite_2, SWT.NONE);
		label_id.setBounds(10, 23, 61, 17);
		label_id.setText("台号");
		
		Label label_seatnum = new Label(composite_2, SWT.NONE);
		label_seatnum.setBounds(10, 53, 61, 17);
		label_seatnum.setText("状态");
		
		Label label_status = new Label(composite_2, SWT.NONE);
		label_status.setBounds(10, 85, 61, 17);
		label_status.setText("座位数 ");
		
		text_1 = new Text(composite_2, SWT.BORDER);
		text_1.setEditable(false);
		text_1.setBounds(114, 17, 73, 23);
		
		text_2 = new Text(composite_2, SWT.BORDER);
		text_2.setEditable(false);
		text_2.setBounds(114, 47, 73, 23);
		
		text_3 = new Text(composite_2, SWT.BORDER);
		text_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_3.setBounds(114, 76, 73, 23);
		
		Button button_find = new Button(composite_2, SWT.NONE);
		button_find.addSelectionListener(new SelectionAdapter() {
			//修改
			@Override
			public void widgetSelected(SelectionEvent e) {	
				String info = text_3.getText().trim();
				//表示文本框没有输入查询条件
				if(null == info || "".equals(info)){
					//查看所有信息
					try{
						List <Map<String,Object>> list = seatDao.findAllSeat();
						table.removeAll();//清除表格中的数据
						if(null != list && list.size()>0){
							for(Map<String,Object> map:list){
								TableItem tableItem2 = new TableItem(table,SWT.NONE);
								tableItem2.setText(new String[]{		map.get("DID").toString(),
										map.get("SEATING").toString()
										.equals("0") ? "空闲" : map
										.get("SEATING").toString()
										.equals("1") ? "使用中" : "被预定",
								map.get("QUANTITY").toString() });
							}
						}else{
							MessageUtil.promt(shell, "温馨提示","无台号信息");
						}
					}catch(SQLException e1){
						e1.printStackTrace();
					}catch(IOException e2){
						e2.printStackTrace();
					}
					return;
				}		
				
				String update = text_3.getText();
				String did1 = tableItem.getText(0);		
				int quantity = Integer.parseInt(update);
				int  did =  Integer.parseInt(did1);
				//根据座位数修改
				try {
					int update1 = seatDao.updateByQuantity(quantity, did);
					if(update1 ==0){
						MessageUtil.promt(shell, "温馨提示","无台号信息");
					}
					if(update1 == 1){
						//加载页面时执行查看所有台号信息
						try{
						
							table.removeAll();//清除表格中的数据
							List <Map<String,Object>> list = seatDao.findAllSeat();
								for(Map<String,Object> map:list){
									TableItem tableItem2 = new TableItem(table,SWT.NONE);
									tableItem2.setText(new String[]{	map.get("DID").toString(),
											map.get("SEATING").toString()
											.equals("0") ? "空闲" : map
											.get("SEATING").toString()
											.equals("1") ? "使用中" : "被预定",
									map.get("QUANTITY").toString() });
								}
								MessageUtil.promt(shell, "温馨提示","修改成功");
								text_1.setText("");
								text_2.setText("");
								text_3.setText("");
						}catch(SQLException e1){
							e1.printStackTrace();
						}catch(IOException e2){
							e2.printStackTrace();
						}
					}
				}catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		button_find.setBounds(286, 13, 80, 27);
		button_find.setText("修改");
		
		Button button_add = new Button(composite_2, SWT.NONE);
		button_add.addSelectionListener(new SelectionAdapter() {
			//添加
			@Override
			public void widgetSelected(SelectionEvent e) {
				String info = text_3.getText().trim();
				//表示文本框没有输入查询条件
				if(null == info || "".equals(info)){
					//查看所有信息
					try{
						List <Map<String,Object>> list = seatDao.findAllSeat();
						table.removeAll();//清除表格中的数据
						if(null != list && list.size()>0){
							for(Map<String,Object> map:list){
								TableItem tableItem2 = new TableItem(table,SWT.NONE);
								tableItem2.setText(new String[]{	map.get("DID").toString(),
										map.get("SEATING").toString()
										.equals("0") ? "空闲" : map
										.get("SEATING").toString()
										.equals("1") ? "使用中" : "被预定",
								map.get("QUANTITY").toString() });
							}
						}else{
							MessageUtil.promt(shell, "温馨提示","无台号信息");
						}
					}catch(SQLException e1){
						e1.printStackTrace();
					}catch(IOException e2){
						e2.printStackTrace();
					}
					return;
				}
				
				String content = text_3.getText();
				int quantity = Integer.parseInt(content);
				//根据座位数添加
				try {
					int add = seatDao.addByQuantity(quantity);
					if(add ==0){
						MessageUtil.promt(shell, "温馨提示","添加失败");
					}
					if(add == 1){
						//加载页面时执行查看所有台号信息
						try{
							table.removeAll();//清除表格中的数据
							List <Map<String,Object>> list = seatDao.findAllSeat();
								for(Map<String,Object> map:list){
									TableItem tableItem2 = new TableItem(table,SWT.NONE);
									tableItem2.setText(new String[]{	map.get("DID").toString(),
											map.get("SEATING").toString()
											.equals("0") ? "空闲" : map
											.get("SEATING").toString()
											.equals("1") ? "使用中" : "被预定",
									map.get("QUANTITY").toString() });
								}
								MessageUtil.promt(shell, "温馨提示","添加成功");
								text_3.setText("");
						}catch(SQLException e1){
							e1.printStackTrace();
						}catch(IOException e2){
							e2.printStackTrace();
						}
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
					
			}
		});
		button_add.setBounds(286, 43, 80, 27);
		button_add.setText("添加");
		
		Button button_quit = new Button(composite_2, SWT.NONE);
		button_quit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageUtil.promt(shell, "温馨提示","请问您是否需要退出");
				System.exit(0);
			}
		});
		button_quit.setBounds(286, 75, 80, 27);
		button_quit.setText("退出");
		//设置控件背景透明
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		//加载页面时执行查看所有台号信息
		try{
			List <Map<String,Object>> list = seatDao.findAllSeat();
				for(Map<String,Object> map:list){
					TableItem tableItem2 = new TableItem(table,SWT.NONE);
					tableItem2.setText(new String[]{		map.get("DID").toString(),
							map.get("SEATING").toString()
							.equals("0") ? "空闲" : map
							.get("SEATING").toString()
							.equals("1") ? "使用中" : "被预定",
					map.get("QUANTITY").toString() });
				}
		}catch(SQLException e1){
			e1.printStackTrace();
		}catch(IOException e2){
			e2.printStackTrace();
		}
		
	}
}

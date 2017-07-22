package com.yc.ui;

import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;



//import com.yc.commons.LogUtil;
import com.yc.commons.MessageUtil;
import com.yc.dao.MenuDao1;


public class Menu {

	protected Shell shell;
	private Table table;
	private Table table_1;
	private Text text;
	MenuDao1 menuDao = new MenuDao1();
	private Text text_1;
	TableItem tableItem;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Menu window = new Menu();
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
		shell.setImage(SWTResourceManager.getImage(Menu.class, "/image/ca31062b6693f580-1bb360fd7cf46844-26de80ae18859a6a8d708d698a02de94.jpg"));
		shell.setSize(656, 452);
		shell.setText("菜品管理");
		// 得到屏幕的宽度和高度
				int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
				int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
				// 得到Shell窗口的宽度和高度
				int shellHeight = shell.getBounds().height;
				int shellWidth = shell.getBounds().width;
				// 让窗口在屏幕中间显示
				shell.setLocation(((screenWidth - shellWidth) / 2), ((screenHeight - shellHeight) / 2));
		org.eclipse.swt.widgets.Menu menu = new org.eclipse.swt.widgets.Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.setText("菜         品");
		
		Button button_addCuisine = new Button(shell, SWT.NONE);
		button_addCuisine.addSelectionListener(new SelectionAdapter() {
			//添加菜系
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					new AddCuisineUI().open();
					List <Map<String,Object>> list = menuDao.findAllSort();
					table_1.removeAll();//清除表格中的数据
					if(null != list && list.size()>0){
						for(Map<String,Object> map:list){
							TableItem tableItem2 = new TableItem(table_1,SWT.NONE);
							tableItem2.setText(new String[]{		
									map.get("SID").toString(),
									map.get("SNAME").toString()});
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				} 
			}
		});
		button_addCuisine.setBounds(0, 0, 80, 27);
		button_addCuisine.setText("添加菜系");
		
		Button button_updateCuisine = new Button(shell, SWT.NONE);
		button_updateCuisine.addSelectionListener(new SelectionAdapter() {
			//修改菜系
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					new UpdateCuisineUI().open();
					List <Map<String,Object>> list = menuDao.findAllSort();
					table_1.removeAll();//清除表格中的数据
					if(null != list && list.size()>0){
						for(Map<String,Object> map:list){
							TableItem tableItem2 = new TableItem(table_1,SWT.NONE);
							tableItem2.setText(new String[]{		
									map.get("SID").toString(),
									map.get("SNAME").toString()});
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				} 
			}
		});
		button_updateCuisine.setBounds(209, 0, 80, 27);
		button_updateCuisine.setText("修改菜系");
		
		Button button_exit = new Button(shell, SWT.NONE);
		button_exit.addSelectionListener(new SelectionAdapter() {
			//退出
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageUtil.promt(shell, "温馨提示","请问您是否需要退出");
				System.exit(0);
			}
		});
		button_exit.setBounds(428, 23, 80, 27);
		button_exit.setText("退出");
		
		Button button_addMenu = new Button(shell, SWT.NONE);
		button_addMenu.addSelectionListener(new SelectionAdapter() {
			//添加菜品
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					new AddMenuUI().open();
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
									map.get("UNIT").toString()});
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				} 
			}
		});
		button_addMenu.setBounds(0, 42, 80, 27);
		button_addMenu.setText("添加菜品");
		
		Button button_updateMenu = new Button(shell, SWT.NONE);
		button_updateMenu.addSelectionListener(new SelectionAdapter() {
			@Override
			//修改菜品
			public void widgetSelected(SelectionEvent e) {
				try {
					new UpdateMenuUI().open();
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
									map.get("UNIT").toString()});
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				} 
			}
		});
		button_updateMenu.setBounds(209, 42, 80, 27);
		button_updateMenu.setText("修改菜品");
		
		Group group = new Group(shell, SWT.NONE);
		group.setText("菜品分类");
		group.setBounds(0, 75, 213, 210);
		
		table_1 = new Table(group, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setBounds(0, 52, 203, 148);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		table_1.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				tableItem = table_1.getItem(new Point(e.x,e.y));
				if(tableItem == null)
					return;
				try {
					List<Map<String, Object>> list = menuDao.findMenu(tableItem.getText(1));
					if(list==null||list.size()==0){
						table.removeAll();
					}
					if(list!=null&&list.size()>0){
						table.removeAll();
						for (Map<String, Object> map : list) {
							TableItem tableItem = new TableItem(table, SWT.NONE);
							tableItem.setText(new String[]{		
									map.get("MID").toString(),
									map.get("MNAME").toString(),
									map.get("CODE").toString(),
									map.get("SNAME").toString(),
									map.get("UNIT_PRICE").toString(),
									map.get("UNIT").toString()});
						}
						
						
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		TableColumn tblclmnNewColumn_6 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_6.setWidth(101);
		tblclmnNewColumn_6.setText("菜系编号");
		
		TableColumn tblclmnNewColumn_7 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_7.setWidth(98);
		tblclmnNewColumn_7.setText("菜系名");
		
		Button button_view = new Button(group, SWT.NONE);
		//显示所有菜系
		button_view.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String info = text_1.getText().trim();
				//表示文本框没有输入查询条件
				if(null == info || "".equals(info)){
					//查看所有信息
					try{
						List <Map<String,Object>> list = menuDao.findAllSort();
						table_1.removeAll();//清除表格中的数据
						if(null != list && list.size()>0){
							for(Map<String,Object> map:list){
								TableItem tableItem2 = new TableItem(table_1,SWT.NONE);
								tableItem2.setText(new String[]{		
										map.get("SID").toString(),
										map.get("SNAME").toString()});
							}
						}else{
							MessageUtil.promt(shell, "温馨提示","无菜系信息");
							text_1.setText("");
						}
					}catch(SQLException e1){
						e1.printStackTrace();
					}catch(IOException e2){
						e2.printStackTrace();
					}
					return;
				}		
				
				String type = text_1.getText();
				if(type.matches("[\\d]+")){
					int sid = Integer.parseInt(type);
					//根据姓名查询
					try{
						List <Map<String,Object>> list = menuDao.findSortByID(sid);
						table_1.removeAll();//清除表格中的数据
						if(null != list && list.size()>0){
							for(Map<String,Object> map:list){
								TableItem tableItem2 = new TableItem(table_1,SWT.NONE);
								tableItem2.setText(new String[]{
										map.get("SID").toString(),
										map.get("SNAME").toString()});
										text_1.setText("");
							}
						}else{
							MessageUtil.promt(shell, "温馨提示","无菜系信息");
							text_1.setText("");
						}
					}catch(SQLException e1){
						MessageUtil.promt(shell, "", "error"+e1.getMessage());
//						LogUtil.logger.info(e1.getMessage()+new Date());
					}catch(IOException e2){
//						LogUtil.logger.info(e2.getMessage()+new Date());
						MessageUtil.promt(shell, "", "error"+e2.getMessage());
					}
				}else{ 
					MessageUtil.promt(shell, "温馨提示","请输入正确的菜系号");
					text_1.setText("");
				}
				
			}
		});
		button_view.setBounds(136, 25, 67, 21);
		button_view.setText("查询");
		
		text_1 = new Text(group, SWT.BORDER);
		text_1.setBounds(0, 24, 102, 23);
		
		Group group_1 = new Group(shell, SWT.NONE);
		group_1.setText("视图");
		group_1.setBounds(0, 291, 213, 93);
		
		Button allMenu = new Button(group_1, SWT.NONE);
		//全部菜品
		allMenu.addSelectionListener(new SelectionAdapter() {
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
										map.get("UNIT").toString()});
							}
						}else{
							MessageUtil.promt(shell, "温馨提示","无菜品信息");
							text.setText("");
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
		allMenu.setBounds(55, 23, 80, 27);
		allMenu.setText("全部菜品");
		
		Button popularMenu = new Button(group_1, SWT.NONE);
		popularMenu.addSelectionListener(new SelectionAdapter() {
			//热门菜品
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					new popularMenuUI().open();
				} catch (Exception e1) {
					e1.printStackTrace();
				} 
			}
		});
		popularMenu.setBounds(55, 56, 80, 27);
		popularMenu.setText("热门菜品");
		
		Group group_2 = new Group(shell, SWT.NONE);
		group_2.setText("菜品");
		group_2.setBounds(219, 77, 411, 307);
		
		table = new Table(group_2, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(20, 23, 381, 274);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(46);
		tblclmnNewColumn.setText("编号");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(101);
		tblclmnNewColumn_1.setText("菜品名");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(62);
		tblclmnNewColumn_2.setText("助记码");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_4.setWidth(68);
		tblclmnNewColumn_4.setText("菜系名");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(63);
		tblclmnNewColumn_3.setText("单价");
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_5.setWidth(41);
		tblclmnNewColumn_5.setText("单位");
		
		Label label = new Label(group_2, SWT.NONE);
		label.setBounds(121, 0, 37, 17);
		label.setText("搜索：");
		
		text = new Text(group_2, SWT.BORDER);
		text.setBounds(158, 0, 103, 20);
		
		Button button_find = new Button(group_2, SWT.NONE);
		button_find.addSelectionListener(new SelectionAdapter() {
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
										map.get("UNIT").toString()});
							}
						}else{
							MessageUtil.promt(shell, "温馨提示","无菜品信息");
							text.setText("");
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
					int mid = Integer.parseInt(type);
					//根据姓名查询
					try{
						List <Map<String,Object>> list = menuDao.findMenuByID(mid);
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
										text.setText("");
							}
						}else{
							MessageUtil.promt(shell, "温馨提示","无菜品信息");
							text.setText("");
						}
					}catch(SQLException e1){
//						LogUtil.logger.info(e1.getMessage()+new Date());
						MessageUtil.promt(shell, "", "error"+e1.getMessage());
					}catch(IOException e2){
//						LogUtil.logger.info(e2.getMessage()+new Date());
						MessageUtil.promt(shell, "", "error"+e2.getMessage());
					}
				}else{ 
					MessageUtil.promt(shell, "温馨提示","请输入正确的菜品号");
					text.setText("");
				}
			}
		});
		button_find.setBounds(282, 0, 64, 20);
		button_find.setText("查找");
		
		try{
		List <Map<String,Object>> list1 = menuDao.findAllSort();
		table_1.removeAll();//清除表格中的数据
		if(null != list1 && list1.size()>0){
			for(Map<String,Object> map:list1){
				TableItem tableItem2 = new TableItem(table_1,SWT.NONE);
				tableItem2.setText(new String[]{		
						map.get("SID").toString(),
						map.get("SNAME").toString()});
			}
		}
		}catch(Exception e){}
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

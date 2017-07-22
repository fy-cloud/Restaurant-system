package com.yc.ui;

import java.awt.Dimension;
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
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;


//import com.yc.commons.LogUtil;
import com.yc.commons.MessageUtil;
import com.yc.dao.SeatDao;

public class CuisineUI {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Table table;
	TableItem tableItem;
	TableCursor tableCursor;
	Display display;
	SeatDao seatDao = new SeatDao();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CuisineUI window = new CuisineUI();
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
		shell = new Shell(display,SWT.MIN | SWT.CLOSE);
		shell.setImage(SWTResourceManager.getImage(CuisineUI.class, "/image/ca31062b6693f580-1bb360fd7cf46844-26de80ae18859a6a8d708d698a02de94.jpg"));
		shell.setSize(450, 300);
		shell.setText("菜系管理");
		Dimension dem = Toolkit.getDefaultToolkit().getScreenSize();
		// 窗体居中显示
		shell.setLocation((dem.width - shell.getSize().x) / 2,
				(dem.height - shell.getSize().y) / 2);
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(10, 0, 414, 262);
		
		Label lable = new Label(composite, SWT.NONE);
		lable.setBounds(0, 0, 61, 17);
		lable.setText("菜系管理");
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setBounds(22, 23, 61, 17);
		label_1.setText("菜系名称");
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(121, 17, 73, 23);
		
		Button button_add = new Button(composite, SWT.NONE);
		button_add.addSelectionListener(new SelectionAdapter() {
			//添加
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
				
				String name = text.getText();
				//根据菜名添加
				try {
					int add = seatDao.addByName(name);
					if(add ==0){
						MessageUtil.promt(shell, "温馨提示","添加失败");
					}
					if(add == 1){
						//加载页面时执行查看所有台号信息
						try{
							table.removeAll();//清除表格中的数据
							List <Map<String,Object>> list = seatDao.findAllCuisine();
								for(Map<String,Object> map:list){
									TableItem tableItem2 = new TableItem(table,SWT.NONE);
									tableItem2.setText(new String[]{map.get("SID").toString(),map.get("SNAME").toString()});
									
								}
								MessageUtil.promt(shell, "温馨提示","添加成功");
								text.setText("");
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
		button_add.setBounds(282, 13, 80, 27);
		button_add.setText("添加");
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setBounds(22, 198, 61, 17);
		label_2.setText("编号");
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setBounds(22, 235, 61, 17);
		label_3.setText("名称");
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setEditable(false);
		text_1.setBounds(121, 198, 73, 23);
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setBounds(121, 229, 73, 23);
		
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
		
				String sid1 = tableItem.getText(0);
				int sid=  Integer.parseInt(sid1);
				//根据座位数修改
				try {
					int update1 = seatDao.updateByName(name, sid);
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
				}catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		button_update.setBounds(282, 198, 80, 27);
		button_update.setText("修改");
		
		Button button_exit = new Button(composite, SWT.NONE);
		button_exit.addSelectionListener(new SelectionAdapter() {
			//退出
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageUtil.promt(shell, "温馨提示","请问您是否需要退出");
				System.exit(0);
			}
		});
		button_exit.setBounds(282, 229, 80, 27);
		button_exit.setText("退出");
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				tableItem = table.getItem(new Point(e.x,e.y));
				if(tableItem == null)
					return;
				text_1.setText(tableItem.getText(0));
				text_2.setText(tableItem.getText(1));
			}
		});
		table.setBounds(10, 50, 394, 142);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		tableCursor = new TableCursor(table, SWT.NONE);
		
		TableColumn column_id = new TableColumn(table, SWT.NONE);
		column_id.setWidth(193);
		column_id.setText("编号");
		
		TableColumn column_name = new TableColumn(table, SWT.NONE);
		column_name.setWidth(212);
		column_name.setText("名称");
		
		
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

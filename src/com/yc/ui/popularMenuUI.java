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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.yc.commons.MessageUtil;
import com.yc.dao.MenuDao1;

public class popularMenuUI {

	protected Shell shell;
	private Text text;
	private Table table;
	MenuDao1 menuDao = new MenuDao1();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			popularMenuUI window = new popularMenuUI();
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
		shell.setImage(SWTResourceManager.getImage(popularMenuUI.class, "/image/ca31062b6693f580-1bb360fd7cf46844-26de80ae18859a6a8d708d698a02de94.jpg"));
		shell.setSize(450, 300);
		shell.setText("热门菜品");
		// 得到屏幕的宽度和高度
				int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
				int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
				// 得到Shell窗口的宽度和高度
				int shellHeight = shell.getBounds().height;
				int shellWidth = shell.getBounds().width;
				// 让窗口在屏幕中间显示
				shell.setLocation(((screenWidth - shellWidth) / 2), ((screenHeight - shellHeight) / 2));
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(0, 0, 434, 262);
		
		Label label = new Label(composite, SWT.NONE);
		label.setBounds(28, 24, 61, 17);
		label.setText("菜品名");
		
		text = new Text(composite, SWT.BORDER);
		text.setEnabled(false);
		text.setBounds(123, 18, 89, 23);
		
		Button button_find = new Button(composite, SWT.NONE);
		button_find.addSelectionListener(new SelectionAdapter() {
			//热门菜品
			@Override
			public void widgetSelected(SelectionEvent e) {
				String info = text.getText().trim();
				//表示文本框没有输入查询条件
				if(null == info || "".equals(info)){
					//查看所有信息
					try{
						List <Map<String,Object>> list = menuDao.popularMenu();
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
										map.get("OD_AMOUNT").toString()
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
		button_find.setBounds(297, 14, 80, 27);
		button_find.setText("热门菜品");
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 60, 414, 192);
		
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
		tableColumn_5.setText("数量");
		
//		try{
//			List <Map<String,Object>> list =menuDao.popularMenu();
//				for(Map<String,Object> map:list){
//					TableItem tableItem2 = new TableItem(table,SWT.NONE);
//					tableItem2.setText(new String[]{	
//							map.get("MID").toString(),
//							map.get("MNAME").toString(),
//							map.get("CODE").toString(),
//							map.get("SNAME").toString(),
//							map.get("UNIT_PRICE").toString(),
//							map.get("OD_AMOUNT").toString()});
//					}
//		}catch(SQLException e1){
//			e1.printStackTrace();
//		}catch(IOException e2){
//			e2.printStackTrace();
//		}

	}

}

package com.yc.ui;

import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.yc.commons.MessageUtil;
import com.yc.dao.MenuDao;


public class AddMenuUI {

	protected Shell shell;
	private Text text;
	MenuDao menuDao = new MenuDao();
	private Table table;
	TableItem tableItem;
	private Text text_1;
	private Text text_3;
	Combo combo;
	Text combo_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AddMenuUI window = new AddMenuUI();
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
		shell.setImage(SWTResourceManager.getImage(AddMenuUI.class, "/image/ca31062b6693f580-1bb360fd7cf46844-26de80ae18859a6a8d708d698a02de94.jpg"));
		shell.setSize(450, 380);
		shell.setText("添加菜品");
		// 得到屏幕的宽度和高度
				int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
				int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
				// 得到Shell窗口的宽度和高度
				int shellHeight = shell.getBounds().height;
				int shellWidth = shell.getBounds().width;
				// 让窗口在屏幕中间显示
				shell.setLocation(((screenWidth - shellWidth) / 2), ((screenHeight - shellHeight) / 2));
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(0, 0, 434, 342);
		
		Label label = new Label(composite, SWT.NONE);
		label.setBounds(10, 25, 61, 17);
		label.setText("菜品名称");
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(92, 22, 110, 23);
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 135, 414, 204);
		
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
		tableColumn_4.setText("单价");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(71);
		tableColumn_5.setText("单位");
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setBounds(229, 25, 61, 17);
		label_1.setText("助记码");
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setBounds(311, 22, 113, 23);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setBounds(10, 58, 61, 17);
		label_2.setText("菜系名");
	
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setBounds(229, 58, 61, 17);
		label_3.setText("单价");
		
		text_3 = new Text(composite, SWT.BORDER);
		text_3.setBounds(311, 58, 113, 23);
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setBounds(10, 100, 61, 17);
		label_4.setText("单位");
		
		Button button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
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
			
				//String s[]=sname.trim().split("_");
				//int ss=Integer.parseInt(s[0]);
	
			
				String  mname = text.getText();
				
				//String mname = text.getText();
				String code = text_1.getText();
			
				String unit = text_3.getText();
				
				String unit_price = combo_1.getText();
				String sname = combo.getText();
				//String unit_price = text_4.getText();
				if(!unit.matches("[\\d]{1,8}")){
					MessageUtil.promt(shell, "温馨提示", "请输入正确的价格!");
					return;
				}
				if(unit.matches("[\\d]+")||mname.matches("[\u4E00-\u9FA5]{2,10}")){
				List<Object> params = new ArrayList<Object>();
				
				params.add(sname.split("_")[1]);
				params.add(mname);
				params.add(code);
				params.add(unit_price);
				params.add(unit);
//				System.out.println(params);
				
				if (getSelection()) {
					if(menuDao.isMenuExists(mname)){
						MessageUtil.promt(shell, "温馨提示", "菜品已存在!");
						return;
					}
					if(menuDao.isCExists(code)){
						MessageUtil.promt(shell, "温馨提示", "该助记码已对应其它菜品");
						return;
					}
					try {
						boolean flag = menuDao.registerMenu(params);
						if (flag) {
							MessageUtil.promt(shell, "温馨提示", "菜品添加成功！");
							// 清除页面上的数据
							text.setText("");
							text_1.setText("");
							text_3.setText("");
							
							table.removeAll();
							List<Map<String, Object>> list = menuDao.findAllMenu();
							for (Map<String, Object> map : list) {
								tableItem = new TableItem(table, SWT.NONE);
								tableItem.setText(new String[] { map.get("MID").toString(), 
										map.get("MNAME").toString(),map.get("CODE").toString(), 
										map.get("SNAME").toString(), map.get("UNIT_PRICE").toString(),
										map.get("UNIT").toString()  });
								
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
			}else{
				MessageUtil.promt(shell, "温馨提示", "单价必须是数字且菜品名是2到10之间的汉字");
				text.setText("");
				text_1.setText("");
				text_3.setText("");
				
			}
			}
		});
		button.setBounds(284, 90, 80, 27);
		button.setText("添加");
		
		combo = new Combo(composite, SWT.NONE);
		combo.setBounds(92, 56, 110, 25);
	
		try {
			List<Map<String, Object>> list = menuDao.findAllSort();
			for (Map<String, Object> map : list) {
				combo.add(map.get("SID").toString()+"_"+map.get("SNAME").toString());
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
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
		
		 combo_1 = new Text(composite, SWT.BORDER);
		combo_1.setBounds(92, 97, 110, 25);
		
//		try {
//			List<Map<String, Object>> list = menuDao.findAllMenu();
//			for (Map<String, Object> map : list) {
//				combo_1.add(map.get("UNIT").toString());
//			}
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
	}
	
	public boolean getSelection(){
		if (text_1.getText().equals("") || text.getText().equals("") 
			|| text_3.getText().equals("") 
			|| combo.getText().equals("")){
			return false;
		} else {
			return true;
		}
	}
}

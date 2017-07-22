package com.yc.ui;

import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.yc.commons.MessageUtil;
import com.yc.dao.SeatDao;

public class AddCuisineUI {

	protected Shell shell;
	private Text text;
	private Table table;
	SeatDao seatDao = new SeatDao();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AddCuisineUI window = new AddCuisineUI();
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
		shell.setImage(SWTResourceManager.getImage(AddCuisineUI.class, "/image/ca31062b6693f580-1bb360fd7cf46844-26de80ae18859a6a8d708d698a02de94.jpg"));
		shell.setSize(450, 300);
		shell.setText("添加菜系");
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
		label.setBounds(29, 25, 61, 17);
		label.setText("菜系名称");
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(126, 22, 93, 23);
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			//菜系添加
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
					if(name.matches("[\u4E00-\u9FA5]{2,10}")){
						text.setText("");
						int add = -1;
						if(seatDao.isCuisineExists(name)){
							MessageUtil.promt(shell, "温馨提示","菜系已存在请重新输入");
							return;
						}
						try{
							 add = seatDao.addByName(name);
						}catch(Exception e1){
							MessageUtil.promt(shell, "温馨提示","违反唯一约束，请重新输入");
							text.setText("");
						}
						System.out.println(add);
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
					}else{
						MessageUtil.promt(shell, "温馨提示","必须输入汉字，长度在2-10之间");
						text.setText("");
					}

			}
		});
		btnNewButton.setBounds(296, 20, 80, 27);
		btnNewButton.setText("添加");
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 63, 414, 189);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(193);
		tableColumn.setText("编号");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(212);
		tableColumn_1.setText("名称");
		
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

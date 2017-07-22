package com.yc.ui;

import java.awt.Toolkit;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.yc.commons.MessageUtil;
import com.yc.dao.ObligateDao;
import com.yc.dao.ViewObligatedao;

public class ViewObligate {

	protected Shell shell;
	private Table table;
	ObligateDao obligateDao = new ObligateDao();
	ViewObligatedao viewObligatedao = new  ViewObligatedao();
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ViewObligate window = new ViewObligate();
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
		shell.setImage(SWTResourceManager.getImage(ViewObligate.class, "/image/ca31062b6693f580-1bb360fd7cf46844-26de80ae18859a6a8d708d698a02de94.jpg"));
		shell.setSize(461, 403);
		shell.setText("预订信息");
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
		lblNewLabel.setBounds(10, 22, 82, 29);
		lblNewLabel.setText("预订信息:");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(20, 57, 402, 297);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.setWidth(84);
		tblclmnNewColumn.setText("预定编号");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.setText("人数");
		tblclmnNewColumn_1.setWidth(90);
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("桌号");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_3.setWidth(119);
		tblclmnNewColumn_3.setText("状态");
		
		//初始化时加载预订信息
		List<Map<String, Object>>  list = viewObligatedao.finddata();
		if(list==null||list.size()==0){
			MessageUtil.promt(shell, "温馨提示", "没有预订数据");
			return;
		}
		for (Map<String, Object> map : list) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(new String[]{
					map.get("OID").toString(),
					map.get("PEOPLE").toString().equals("0")?"":map.get("PEOPLE").toString(),
					map.get("DID").toString(),
					map.get("STATE").toString().equals("1")?"预定中":"预定失效"
			});
		}
	}
}

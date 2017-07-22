package com.yc.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.experimental.chart.swt.ChartComposite;

import com.yc.commons.MessageUtil;
import com.yc.dao.BillingQuerydao;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;

public class BillingQueryUI {

	protected Shell shell;
	private Table table;
	private Table table_1;
	private Text text;
	private Text text_1;
	private Table table_2;
	private Table table_3;
	private Text text_2;
	private Table table_4;
	private Text text_3;
	Composite composite_5;
	BillingQuerydao billingQuerydao = new BillingQuerydao();
	TableItem tableItem = null;
	JFreeChart chart1;
	JFreeChart chart2;
	ChartComposite chartComposite1;
	ChartComposite chartComposite2;
	Composite composite_3 ;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BillingQueryUI window = new BillingQueryUI();
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
		shell = new Shell(SWT.MIN);
		shell.setSize(1031, 498);
		shell.setText("账单查询");
		Dimension dem = Toolkit.getDefaultToolkit().getScreenSize();
		// 窗体居中显示
		shell.setLocation((dem.width - shell.getSize().x) / 2,
				(dem.height - shell.getSize().y) / 2);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);

		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("每日订单");

		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(composite);
		composite.setLayout(new FormLayout());

		Group group = new Group(composite, SWT.NONE);
		FormData fd_group = new FormData();
		fd_group.bottom = new FormAttachment(100);
		fd_group.right = new FormAttachment(0, 206);
		fd_group.top = new FormAttachment(0);
		fd_group.left = new FormAttachment(0);
		group.setLayoutData(fd_group);
		group.setText("日账单");
		group.setLayout(new FillLayout(SWT.HORIZONTAL));

		table = new Table(group, SWT.BORDER | SWT.FULL_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				tableItem = table.getItem(new Point(e.x, e.y));
				if (tableItem == null) {
					return;
				}
				table_1.removeAll();
				List<Map<String, Object>> list = billingQuerydao
						.findOrderDetails(tableItem.getText(0));
				for (Map<String, Object> map : list) {
					TableItem tableItem = new TableItem(table_1, SWT.NONE);
					tableItem.setText(new String[] {
							map.get("O_ID").toString(),
							Double.parseDouble(map.get("VIP_DISCOUNT")
									.toString()) == 0 ? "" : Double
									.parseDouble(map.get("VIP_DISCOUNT")
											.toString())
									* 10 + "折", map.get("MID").toString(),
							map.get("MNAME").toString(),
							map.get("UNIT").toString(),
							map.get("OD_AMOUNT").toString(),
							map.get("OD_PRICE").toString(),
							map.get("AMOUNT").toString(), });
				}
			}
		});
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.setWidth(65);
		tblclmnNewColumn_1.setText("订单号");

		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_2.setWidth(65);
		tblclmnNewColumn_2.setText("台号");

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.setWidth(65);
		tblclmnNewColumn.setText("金额");

		Group group_1 = new Group(composite, SWT.NONE);
		group_1.setText("订单详情");
		group_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		FormData fd_group_1 = new FormData();
		fd_group_1.bottom = new FormAttachment(0, 439);
		fd_group_1.right = new FormAttachment(0, 735);
		fd_group_1.top = new FormAttachment(0);
		fd_group_1.left = new FormAttachment(0, 207);
		group_1.setLayoutData(fd_group_1);

		table_1 = new Table(group_1, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);

		TableColumn tblclmnNewColumn_3 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_3.setWidth(58);
		tblclmnNewColumn_3.setText("订单号");

		TableColumn tblclmnNewColumn_9 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_9.setWidth(51);
		tblclmnNewColumn_9.setText("折扣");

		TableColumn tblclmnNewColumn_10 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_10.setWidth(69);
		tblclmnNewColumn_10.setText("菜品id");

		TableColumn tblclmnNewColumn_4 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_4.setWidth(109);
		tblclmnNewColumn_4.setText("菜品名称");

		TableColumn tblclmnNewColumn_8 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_8.setWidth(56);
		tblclmnNewColumn_8.setText("单位");

		TableColumn tblclmnNewColumn_7 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_7.setWidth(48);
		tblclmnNewColumn_7.setText("数量");

		TableColumn tblclmnNewColumn_6 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_6.setWidth(53);
		tblclmnNewColumn_6.setText("单价");

		TableColumn tblclmnNewColumn_5 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_5.setWidth(71);
		tblclmnNewColumn_5.setText("金额");

		Composite composite_1 = new Composite(composite, SWT.NONE);
		FormData fd_composite_1 = new FormData();
		fd_composite_1.bottom = new FormAttachment(0, 439);
		fd_composite_1.right = new FormAttachment(0, 1017);
		fd_composite_1.top = new FormAttachment(0);
		fd_composite_1.left = new FormAttachment(0, 737);
		composite_1.setLayoutData(fd_composite_1);

		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI",
				13, SWT.NORMAL));
		lblNewLabel.setBounds(10, 35, 47, 30);
		lblNewLabel.setText("日期:");

		final DateTime dateTime = new DateTime(composite_1, SWT.BORDER);
		dateTime.setBounds(100, 35, 114, 30);

		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String date = ""
						+ dateTime.getYear()
						+ "-"
						+ ((dateTime.getMonth() + 1) > 10 
								? (dateTime.getMonth() + 1) + "" 
								: "" + 0 + (dateTime.getMonth() + 1)) 
						+ "-"
						+ (dateTime.getDay() > 10 
								? dateTime.getDay() + "" 
								: "" + 0 + dateTime.getDay()) ;
				table.removeAll();
				table_1.removeAll();
				List<Map<String, Object>> list = billingQuerydao
						.findOrderListByDay(date.trim());
				if (list == null || list.size() == 0) {
					MessageUtil.promt(shell, "温馨提示", "未查到该日数据!");
					text.setText("");
					table.removeAll();
					table_1.removeAll();
					return;
				}
				for (Map<String, Object> map : list) {
					TableItem tableItem = new TableItem(table, SWT.NONE);
					tableItem.setText(new String[] {
							map.get("O_ID").toString(),
							map.get("DID").toString(),
							map.get("SM_MONEY").toString() });
				}
				double amount = billingQuerydao.findDailyAmount(date);
				if(amount>0)
					text.setText(amount+"");
			}
		});
		btnNewButton.setBounds(164, 90, 80, 27);
		btnNewButton.setText("查询");

		Label label = new Label(composite_1, SWT.NONE);
		label.setText("日总金额:");
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13,
				SWT.NORMAL));
		label.setBounds(10, 137, 74, 30);

		text = new Text(composite_1, SWT.BORDER | SWT.CENTER);
		text.setEditable(false);
		text.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		text.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 15,
				SWT.NORMAL));
		text.setBounds(43, 186, 138, 38);

		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setText("元");
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13,
				SWT.NORMAL));
		label_1.setBounds(197, 194, 29, 27);

		Label lblexcel = new Label(composite_1, SWT.NONE);
		lblexcel.setText("导出到excel");
		lblexcel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13,
				SWT.NORMAL));
		lblexcel.setBounds(10, 242, 97, 30);

		text_1 = new Text(composite_1, SWT.BORDER);
		text_1.setBounds(10, 278, 204, 23);

		Button button = new Button(composite_1, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String date = ""
						+ dateTime.getYear()
						+ "-"
						+ ((dateTime.getMonth() + 1) > 10 
								? (dateTime.getMonth() + 1) + "" 
								: "" + 0 + (dateTime.getMonth() + 1)) 
						+ "-"
						+ (dateTime.getDay() > 10 
								? dateTime.getDay() + "" 
								: "" + 0 + dateTime.getDay()) ;
				FileDialog fd = new FileDialog(shell, SWT.SAVE);
				fd.setFilterExtensions(new String[] { "*.xls" });
				fd.setFileName("boo1");
				String path = fd.open();
				List<Map<String, Object>> list = billingQuerydao.toExcelData(date);
				if(list==null||list.size()==0){
					MessageUtil.promt(shell, "温馨提示", "没有数据可以导出!");
					return;
				}
				text_1.setText(path);
				String [][] data = new String [list.size()+1][list.get(0).size()];
				TableColumn []tableColumns = table_1.getColumns();
				//插入EXCEL表头
				for (int m = 0; m < list.get(0).size(); m++) {
					data[0][m] = tableColumns[m].getText();
				}
				//插入表格数据
				for (int i = 1; i < list.size()+1; i++) {
					data[i][0] = list.get(i-1).get("O_ID").toString();
					data[i][1] = list.get(i-1).get("DISCOUNT").toString();
					data[i][2] = list.get(i-1).get("MID").toString();
					data[i][3] = list.get(i-1).get("MNAME").toString();
					data[i][4] = list.get(i-1).get("UNIT").toString();
					data[i][5] = list.get(i-1).get("OD_AMOUNT").toString();
					data[i][6] = list.get(i-1).get("OD_PRICE").toString();
					data[i][7] = list.get(i-1).get("AMOUNT").toString();
				}
				//创建excel表格
				HSSFWorkbook wb = new HSSFWorkbook();
				HSSFSheet sheet = wb.createSheet();
				//创建行和列
				Row row[] = new HSSFRow[list.size()+1];
				Cell cells[] = new HSSFCell[list.get(0).size()];
				
				//写入数据
				
				for (int i = 0; i < row.length; i++) {
					row[i] = sheet.createRow(i);
					for (int j = 0; j < cells.length; j++) {
						cells[j] = row[i].createCell(j);
						cells[j].setCellValue(data[i][j]);
					}
				}
				//写入文件
				try {
					FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
					wb.write(fileOutputStream);
					fileOutputStream.flush();
					fileOutputStream.close();
					wb.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				//提示成功
				MessageUtil.promt(shell, "温馨提示", "导出成功!");
			}
		});
		button.setBounds(229, 276, 41, 27);
		button.setText("浏览");

		TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("月查询");

		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_1.setControl(composite_2);
		composite_2.setLayout(new FormLayout());

		Group group_2 = new Group(composite_2, SWT.NONE);
		FormData fd_group_2 = new FormData();
		fd_group_2.bottom = new FormAttachment(0, 356);
		fd_group_2.right = new FormAttachment(0, 350);
		fd_group_2.top = new FormAttachment(0);
		fd_group_2.left = new FormAttachment(0);
		group_2.setLayoutData(fd_group_2);
		group_2.setLayout(new FillLayout(SWT.HORIZONTAL));

		table_2 = new Table(group_2, SWT.BORDER | SWT.FULL_SELECTION);
		table_2.setHeaderVisible(true);
		table_2.setLinesVisible(true);

		TableColumn tblclmnNewColumn_11 = new TableColumn(table_2, SWT.NONE);
		tblclmnNewColumn_11.setWidth(84);
		tblclmnNewColumn_11.setText("日期");

		TableColumn tblclmnNewColumn_12 = new TableColumn(table_2, SWT.NONE);
		tblclmnNewColumn_12.setWidth(84);
		tblclmnNewColumn_12.setText("金额");

		table_3 = new Table(group_2, SWT.BORDER | SWT.FULL_SELECTION);
		table_3.setLinesVisible(true);
		table_3.setHeaderVisible(true);

		TableColumn tblclmnNewColumn_13 = new TableColumn(table_3, SWT.NONE);
		tblclmnNewColumn_13.setWidth(84);
		tblclmnNewColumn_13.setText("日期");

		TableColumn tblclmnNewColumn_14 = new TableColumn(table_3, SWT.NONE);
		tblclmnNewColumn_14.setWidth(84);
		tblclmnNewColumn_14.setText("金额");

		final Combo combo = new Combo(composite_2, SWT.NONE);
		FormData fd_combo = new FormData();
		fd_combo.top = new FormAttachment(group_2, 29);
		fd_combo.left = new FormAttachment(0, 10);
		fd_combo.right = new FormAttachment(0, 70);
		combo.setLayoutData(fd_combo);
		combo.setText("2016");
		combo.add("2016");

		Label lblNewLabel_1 = new Label(composite_2, SWT.NONE);
		lblNewLabel_1.setAlignment(SWT.CENTER);
		FormData fd_lblNewLabel_1 = new FormData();
		fd_lblNewLabel_1.top = new FormAttachment(combo, 3, SWT.TOP);
		fd_lblNewLabel_1.left = new FormAttachment(combo, 6);
		lblNewLabel_1.setLayoutData(fd_lblNewLabel_1);
		lblNewLabel_1.setText("年");

		final Combo combo_1 = new Combo(composite_2, SWT.NONE);
		FormData fd_combo_1 = new FormData();
		fd_combo_1.top = new FormAttachment(combo, 0, SWT.TOP);
		fd_combo_1.left = new FormAttachment(lblNewLabel_1, 6);
		combo_1.setLayoutData(fd_combo_1);
		for (int i = 1; i < 13; i++) {
			combo_1.add(i+"");
		}
		combo_1.select(0);

		Label label_2 = new Label(composite_2, SWT.NONE);
		label_2.setText("月");
		label_2.setAlignment(SWT.CENTER);
		FormData fd_label_2 = new FormData();
		fd_label_2.top = new FormAttachment(combo, 3, SWT.TOP);
		fd_label_2.left = new FormAttachment(combo_1, 6);
		label_2.setLayoutData(fd_label_2);

		Button btnNewButton_1 = new Button(composite_2, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String year = combo.getText();
				String month = combo_1.getText();
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.YEAR, Integer.parseInt(year));
				calendar.set(Calendar.MONTH, Integer.parseInt(month)-1);
				int maxDate = calendar.getActualMaximum(Calendar.DATE);
				List<Map<String, Object>> list = billingQuerydao.findOrderAmountByMonth(year, month);
				double amount = billingQuerydao.findMonthlyAmount(year, month);
				if(amount>0){
					table_2.removeAll();
					table_3.removeAll();
					//输出 table part 1前十五天
					for (int i = 0; i < 15; i++) {
						TableItem tableItem = new TableItem(table_2, SWT.NONE);
						tableItem.setText(new String[]{
								list.get(i).get("DAY").toString(),
								list.get(i).get("AMOUNT").toString()
						});
					}
					//输出 table part 2后
					for (int i = 15; i < maxDate; i++) {
						TableItem tableItem = new TableItem(table_3, SWT.NONE);
						tableItem.setText(new String[]{
								list.get(i).get("DAY").toString(),
								list.get(i).get("AMOUNT").toString()
						});
					}
					text_2.setText(amount+"");
					//生成统计图
					if(chart1!=null){
						chartComposite1.dispose();
						composite_3.layout();
					}
					chart1 = getMonthChart(year, month, list, maxDate);
					chartComposite1 = new ChartComposite(composite_3, SWT.NONE, chart1);
					composite_3.layout();
					return;
				}
				MessageUtil.promt(shell, "温馨提示", "暂无"+month+"月数据!");
				table_2.removeAll();
				table_3.removeAll();
				if(chartComposite1!=null)
					chartComposite1.dispose();
				composite_3.layout();
				text_2.setText("");
			}
		});
		FormData fd_btnNewButton_1 = new FormData();
		fd_btnNewButton_1.top = new FormAttachment(combo, -2, SWT.TOP);
		fd_btnNewButton_1.left = new FormAttachment(label_2, 35);
		btnNewButton_1.setLayoutData(fd_btnNewButton_1);
		btnNewButton_1.setText("查询");

		composite_3 = new Composite(composite_2, SWT.BORDER);
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));
		FormData fd_composite_3 = new FormData();
		fd_composite_3.top = new FormAttachment(0);
		fd_composite_3.left = new FormAttachment(group_2, 6);
		fd_composite_3.right = new FormAttachment(100, -10);
		fd_composite_3.bottom = new FormAttachment(0, 356);
		composite_3.setLayoutData(fd_composite_3);

		Label label_3 = new Label(composite_2, SWT.NONE);
		label_3.setText("月总金额:");
		label_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13,
				SWT.NORMAL));
		FormData fd_label_3 = new FormData();
		fd_label_3.top = new FormAttachment(combo, 0, SWT.TOP);
		fd_label_3.left = new FormAttachment(btnNewButton_1, 102);
		label_3.setLayoutData(fd_label_3);

		text_2 = new Text(composite_2, SWT.BORDER | SWT.CENTER);
		text_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		text_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 15,
				SWT.NORMAL));
		text_2.setEditable(false);
		FormData fd_text_2 = new FormData();
		fd_text_2.right = new FormAttachment(label_3, 149, SWT.RIGHT);
		fd_text_2.bottom = new FormAttachment(combo, 0, SWT.BOTTOM);
		fd_text_2.left = new FormAttachment(label_3, 18);
		text_2.setLayoutData(fd_text_2);

		Label label_4 = new Label(composite_2, SWT.NONE);
		label_4.setText("元");
		label_4.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13,
				SWT.NORMAL));
		FormData fd_label_4 = new FormData();
		fd_label_4.top = new FormAttachment(combo, 0, SWT.TOP);
		fd_label_4.left = new FormAttachment(text_2, 17);
		label_4.setLayoutData(fd_label_4);

		TabItem tbtmNewItem_2 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_2.setText("年查询");

		Composite composite_4 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_2.setControl(composite_4);
		composite_4.setLayout(new FormLayout());

		Group group_3 = new Group(composite_4, SWT.NONE);
		FormData fd_group_3 = new FormData();
		fd_group_3.bottom = new FormAttachment(0, 383);
		fd_group_3.right = new FormAttachment(0, 198);
		fd_group_3.top = new FormAttachment(0);
		fd_group_3.left = new FormAttachment(0);
		group_3.setLayoutData(fd_group_3);
		group_3.setLayout(new FillLayout(SWT.HORIZONTAL));

		table_4 = new Table(group_3, SWT.BORDER | SWT.FULL_SELECTION);
		table_4.setHeaderVisible(true);
		table_4.setLinesVisible(true);

		TableColumn tblclmnNewColumn_15 = new TableColumn(table_4, SWT.CENTER);
		tblclmnNewColumn_15.setWidth(94);
		tblclmnNewColumn_15.setText("月份");

		TableColumn tblclmnNewColumn_16 = new TableColumn(table_4, SWT.CENTER);
		tblclmnNewColumn_16.setWidth(94);
		tblclmnNewColumn_16.setText("金额");

		final Combo combo_2 = new Combo(composite_4, SWT.NONE);
		FormData fd_combo_2 = new FormData();
		fd_combo_2.bottom = new FormAttachment(100, -10);
		fd_combo_2.left = new FormAttachment(0, 10);
		combo_2.setLayoutData(fd_combo_2);
		combo_2.add("2016");
		combo_2.add("2015");
		combo_2.select(0);

		Label label_5 = new Label(composite_4, SWT.NONE);
		FormData fd_label_5 = new FormData();
		fd_label_5.top = new FormAttachment(combo_2, 3, SWT.TOP);
		fd_label_5.left = new FormAttachment(combo_2, 6);
		label_5.setLayoutData(fd_label_5);
		label_5.setText("年");

		Button button_1 = new Button(composite_4, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String year = combo_2.getText();
				double amount = billingQuerydao.findYearAmount(year);
				List<Map<String, Object>> list = billingQuerydao.findOrderAmountByYear(year);
				if(amount>0){
					text_3.setText(amount+"");
					//生成统计图
					table_4.removeAll();
					for (Map<String, Object> map : list) {
						TableItem tableItem = new TableItem(table_4, SWT.NONE);
						tableItem.setText(new String[]{
								map.get("MONTH").toString(),
								map.get("AMOUNT").toString(),
						});
					}
					if(chart2!=null){
						chartComposite2.dispose();
						composite_5.layout();
					}
					chart2 = getYearChart(year, list);
					chartComposite2 = new ChartComposite(composite_5, SWT.NONE, chart2);
					composite_5.layout();
					return;
				}
				MessageUtil.promt(shell, "温馨提示", "暂无"+year+"年数据!");
			}
		});
		FormData fd_button_1 = new FormData();
		fd_button_1.bottom = new FormAttachment(combo_2, 0, SWT.BOTTOM);
		fd_button_1.left = new FormAttachment(label_5, 35);
		button_1.setLayoutData(fd_button_1);
		button_1.setText("查询");

		Label label_6 = new Label(composite_4, SWT.NONE);
		label_6.setText("年总营业额:");
		label_6.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13,
				SWT.NORMAL));
		FormData fd_label_6 = new FormData();
		fd_label_6.bottom = new FormAttachment(100, -18);
		fd_label_6.left = new FormAttachment(button_1, 59);
		label_6.setLayoutData(fd_label_6);

		text_3 = new Text(composite_4, SWT.BORDER | SWT.CENTER);
		text_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		text_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 15,
				SWT.NORMAL));
		text_3.setEditable(false);
		FormData fd_text_3 = new FormData();
		fd_text_3.bottom = new FormAttachment(100, -10);
		fd_text_3.right = new FormAttachment(label_6, 130, SWT.RIGHT);
		fd_text_3.left = new FormAttachment(label_6, 6);
		text_3.setLayoutData(fd_text_3);

		Label label_7 = new Label(composite_4, SWT.NONE);
		label_7.setText("元");
		label_7.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13,
				SWT.NORMAL));
		FormData fd_label_7 = new FormData();
		fd_label_7.bottom = new FormAttachment(100, -18);
		fd_label_7.left = new FormAttachment(text_3, 20);
		label_7.setLayoutData(fd_label_7);
		composite_5 = new Composite(composite_4, SWT.NONE);
		composite_5.setLayout(new FillLayout(SWT.HORIZONTAL));
		FormData fd_composite_5 = new FormData();
		fd_composite_5.bottom = new FormAttachment(group_3, 0, SWT.BOTTOM);
		fd_composite_5.top = new FormAttachment(0);
		fd_composite_5.left = new FormAttachment(group_3, 6);
		fd_composite_5.right = new FormAttachment(100, -10);
		composite_5.setLayoutData(fd_composite_5);

	}

	private JFreeChart getMonthChart(String year,String month,List<Map<String, Object>> list,int maxDate) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < maxDate; i++) {
			dataset.addValue(Double.parseDouble(list.get(i).get("AMOUNT").toString()), i+1+"", i+1+"");
		}

		JFreeChart chart = ChartFactory.createBarChart3D(
				year+"年"+month+"月收入统计表", 
				"日", 
				"销售额(元)",
				dataset, 
				PlotOrientation.VERTICAL, 
				false, 
				false, 
				false);
		return chart;
	}
	
	private JFreeChart getYearChart(String year,List<Map<String, Object>> list) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < 12; i++) {
			dataset.addValue(Double.parseDouble(list.get(i).get("AMOUNT").toString()), i+1+"月", i+1+"月");
		}

		JFreeChart chart = ChartFactory.createBarChart3D(year+"年收入统计表", 
				"月份", 
				"销售额(元)",
				dataset, 
				PlotOrientation.VERTICAL, 
				false, 
				false, 
				false);
		return chart;
	}
}

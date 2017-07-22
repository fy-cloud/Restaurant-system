package com.yc.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import swing2swt.layout.BorderLayout;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


import com.yc.commons.LoginUserInfo;
import com.yc.commons.MessageUtil;
import com.yc.dao.OrderDao;
import com.yc.dao.SeatDao;

import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

public class MainUI {

	protected Shell shell;
	private Table table;
	private Table table_1;
	private TableColumn tbl_status;
	private TableColumn tbl_price;
	private TableColumn tbl_unit;
	private TableColumn tbl_Count;
	private TableColumn tbl_per;
	private TableColumn tbl_foodname;
	private TableColumn tbl_foodno;
	private TableColumn tbl_no;
	Composite composite_1;
	private TableColumn tbl_no1;
	private TableColumn tbl_deskno;
	private TableColumn tbl_stime;
	private TableColumn tbl_pcount;
	private Composite composite_2;
	private Text text;
	private Text text_1;
	private Composite composite_5;
	private Composite composite_6;
	private Label label_2;
	private Composite composite_7;
	private Composite composite_8;
	private Composite composite_9;
	private Label lblNewLabel_1;
	private Label label_3;
	private Label label_4;
	private Composite composite_10;
	private Label lblNewLabel_2;
	private Label lblNewLabel_3;
	private Button btnNewButton;
	private Button btnNewButton_1;
	private Button btnNewButton_2;
	private Button btnNewButton_3;
	private Button btnNewButton_4;
	private Button btnNewButton_5;
	private Button button_2;
	private Button button_3;
	private Button button_4;
	private TableColumn tbl_prestatus;
	private TableColumn tbl_deskstatus;
	Date date;
	SimpleDateFormat df;
	Thread t1;
	private Group group_1;
	private Group group_2;
	private Group group;
	private Label label_5;
	private Label label_6;
	private Label label_7;
	private Label label_8;
	private Text text_amount;
	private Text text_money;
	private Text text_off;
	private Text text_fre;
	private Label label_9;
	private Label label_10;
	private Label label_11;
	private Label label_12;
	private Button button_5;
	private Label label_13;
	private Combo combo_1;
	private Label label_14;
	private Text text_6;
	private Composite composite_11;
	OrderDao orderDao = new OrderDao();
	Spinner spinner;
	SeatDao seatDao = new SeatDao();
	TableItem tableItem;
	Combo combo;
	private Label lblid;
	private Text text_id;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainUI window = new MainUI();
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
		setTableColumns();
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
		shell.setImage(SWTResourceManager.getImage(MainUI.class, "/image/ca31062b6693f580-1bb360fd7cf46844-26de80ae18859a6a8d708d698a02de94.jpg"));
		shell.setText("酒店管理系统");
		shell.addShellListener(new ShellAdapter() {
			@Override
			public void shellClosed(ShellEvent e) {
				System.exit(0);
			}
		});
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);// 窗体上的控件背景透明
		shell.setSize(1267, 703);
		Dimension dem = Toolkit.getDefaultToolkit().getScreenSize();
		// 窗体居中显示
		shell.setLocation((dem.width - shell.getSize().x) / 2,
				(dem.height - shell.getSize().y) / 2);
		shell.setLayout(new GridLayout(1, false));
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		composite.setLayout(new BorderLayout(0, 0));
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setImage(SWTResourceManager.getImage(MainUI.class,
				"/image/top.jpg"));

		composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_composite_1 = new GridData(GridData.FILL_HORIZONTAL
				| GridData.FILL_VERTICAL);
		gd_composite_1.heightHint = 295;
		gd_composite_1.widthHint = 1244;
		composite_1.setLayoutData(gd_composite_1);

		group_1 = new Group(composite_1, SWT.NONE);
		group_1.setText("签单列表");
		group_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		table = new Table(group_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			//鼠标右键减菜
			public void mouseDown(MouseEvent e) {
				if(e.button == 3){
					tableItem = table.getItem(new Point(e.x, e.y));
					if(tableItem!=null){
						if(tableItem.getText(0).equals("order")){
							MessageUtil.promt(shell, "温馨提示", "您不可修改已签单的订单!");
							return;
						}
						if(tableItem.getText(5).equals("1")){
							boolean flag = MessageDialog.openConfirm(shell, "确认", "您确认要移除此菜品吗?");
							if(flag){
								orderDao.deleteOrderDetail(tableItem.getText(2));
								List<Map<String, Object>> list = orderDao.getOrderInfo(tableItem.getText(1));
								if (list != null && list.size() > 0) {
									table.removeAll();
									for (Map<String, Object> map : list) {
										TableItem tableItem = new TableItem(table,
												SWT.NONE);
										combo_1.setText(map.get("O_ID").toString());
										tableItem.setText(new String[] {
												map.get("O_STATUS").toString()
														.equals("0") ? "新订单" : "已签单",
												map.get("O_ID").toString(),
												map.get("MID").toString(),
												map.get("MNAME").toString(),
												map.get("UNIT").toString(),
												map.get("OD_AMOUNT").toString(),
												map.get("OD_PRICE").toString(),
												Double.parseDouble(map.get("PRICE")
														.toString()) + "" });
									}

								}
							}
							return;
						}
						//菜品数量-1
						int amount = Integer.parseInt(tableItem.getText(5));
						amount--;
						String orderid = tableItem.getText(1);
						String foodid = tableItem.getText(2);
						List<Object> params = new ArrayList<Object>();
						params.add(amount+"");
						params.add(orderid);
						params.add(foodid);
						orderDao.updateOrderDetailAmount(params);
						//刷新订单信息
						List<Map<String, Object>> list = orderDao.getOrderInfo(orderid);
						if (list != null && list.size() > 0) {
							table.removeAll();
							for (Map<String, Object> map : list) {
								TableItem tableItem = new TableItem(table,
										SWT.NONE);
								combo_1.setText(map.get("O_ID").toString());
								tableItem.setText(new String[] {
										map.get("O_STATUS").toString()
												.equals("0") ? "新订单" : "已签单",
										map.get("O_ID").toString(),
										map.get("MID").toString(),
										map.get("MNAME").toString(),
										map.get("UNIT").toString(),
										map.get("OD_AMOUNT").toString(),
										map.get("OD_PRICE").toString(),
										Double.parseDouble(map.get("PRICE")
												.toString()) + "" });
							}

						}
					}
				}
			}
		});
		table.setLinesVisible(true);
		table.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				if (tbl_status != null) {
					setTableColumns();
				}
			}
		});
		table.setHeaderVisible(true);

		tbl_status = new TableColumn(table, SWT.CENTER);
		tbl_status.setText("    状态");

		tbl_no = new TableColumn(table, SWT.CENTER);
		tbl_no.setText("订单号");

		tbl_foodno = new TableColumn(table, SWT.CENTER);
		tbl_foodno.setText("商品编号");

		tbl_foodname = new TableColumn(table, SWT.CENTER);
		tbl_foodname.setText("商品名称");

		tbl_per = new TableColumn(table, SWT.CENTER);
		tbl_per.setText("单位");

		tbl_Count = new TableColumn(table, SWT.CENTER);
		tbl_Count.setText("数量");

		tbl_unit = new TableColumn(table, SWT.CENTER);
		tbl_unit.setText("单价");

		tbl_price = new TableColumn(table, SWT.CENTER);
		tbl_price.setText("金额");

		group_2 = new Group(composite_1, SWT.NONE);
		group_2.setText("开台列表");
		group_2.setLayout(new FillLayout(SWT.HORIZONTAL));

		table_1 = new Table(group_2, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			
			public void mouseDown(MouseEvent e) {
				tableItem = table_1.getItem(new Point(e.x, e.y));
				if(tableItem != null)
					text_amount.setText("") ;
				if (tableItem != null) {
					combo.setText(tableItem.getText(1));
					if (!tableItem.getText(0).equals("")) {
						List<Map<String, Object>> list = orderDao
								.getOrderInfo(tableItem.getText(0));
						if (list != null && list.size() > 0) {
							//如果是已签单的订单获取总金额
							table.removeAll();
							for (Map<String, Object> map : list) {
								TableItem tableItem = new TableItem(table,
										SWT.NONE);
								combo_1.setText(map.get("O_ID").toString());
								tableItem.setText(new String[] {
										map.get("O_STATUS").toString()
												.equals("0") ? "新建订单" : "已签单",
										map.get("O_ID").toString(),
										map.get("MID").toString(),
										map.get("MNAME").toString(),
										map.get("UNIT").toString(),
										map.get("OD_AMOUNT").toString(),
										map.get("OD_PRICE").toString(),
										Double.parseDouble(map.get("PRICE")
												.toString()) + "" });
							}
							if(orderDao.getOrderStatus(tableItem.getText(0)) == 1 ){
								TableItem [] tableItems = table.getItems();
								double sum = 0;
								for (TableItem tableItem : tableItems) {
									sum+=Double.parseDouble(tableItem.getText(7));
								}
								text_amount.setText(sum + "") ;
							}

						}
					}
				}
			}
		});
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		table_1.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				if (tbl_status != null) {
					setTableColumns();
				}
			}
		});

		tbl_no1 = new TableColumn(table_1, SWT.CENTER);
		tbl_no1.setText("    订单号");

		tbl_deskno = new TableColumn(table_1, SWT.CENTER);
		tbl_deskno.setText("台号");

		tbl_deskstatus = new TableColumn(table_1, SWT.CENTER);
		tbl_deskstatus.setText("桌子状态");

		tbl_pcount = new TableColumn(table_1, SWT.CENTER);
		tbl_pcount.setText("可坐人数");

		tbl_stime = new TableColumn(table_1, SWT.CENTER);
		tbl_stime.setText("开台时间");

		tbl_prestatus = new TableColumn(table_1, SWT.CENTER);
		tbl_prestatus.setText("预定状态");

		composite_2 = new Composite(shell, SWT.NONE);
		composite_2.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		composite_2.setLayout(new FormLayout());
		GridData gd_composite_2 = new GridData(GridData.FILL_HORIZONTAL);
		gd_composite_2.heightHint = 39;
		gd_composite_2.widthHint = 1242;
		composite_2.setLayoutData(gd_composite_2);

		composite_11 = new Composite(composite_2, SWT.NONE);
		FormData fd_composite_11 = new FormData();
		fd_composite_11.top = new FormAttachment(0, 8);
		fd_composite_11.left = new FormAttachment(0, 10);
		composite_11.setLayoutData(fd_composite_11);

		Label label = new Label(composite_11, SWT.NONE);
		label.setBounds(137, 5, 28, 17);
		label.setText("台号");

		combo = new Combo(composite_11, SWT.NONE);
		combo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
			}
		});
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
		combo.setBounds(171, 2, 88, 25);

		final Button button = new Button(composite_11, SWT.RADIO);
		button.setBounds(265, 5, 45, 17);
		button.setText("编号");

		final Button button_1 = new Button(composite_11, SWT.RADIO);
		button_1.setBounds(314, 5, 61, 17);
		button_1.setText("助记码");

		Label label_1 = new Label(composite_11, SWT.NONE);
		label_1.setBounds(484, 5, 45, 17);
		label_1.setText("商品名:");

		text = new Text(composite_11, SWT.BORDER);
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.CR) {
					if (!text.getText().equals("")) {
						if (button.getSelection()) {
							Map<String, String> map = orderDao
									.findSingleFoodById(text.getText());
							if (map != null && map.size() > 0) {
								text_1.setText(map.get("MNAME").toString());
								text_6.setText(map.get("UNIT").toString());
							}
						}
						if (button_1.getSelection()) {
							Map<String, String> map = orderDao
									.findSingleFoodByCode(text.getText());
							if (map != null && map.size() > 0) {
								text_1.setText(map.get("MNAME").toString());
								text_6.setText(map.get("UNIT").toString());
							}
						}
					}
				}
			}
		});
		text.setBounds(379, 2, 88, 23);

		text_1 = new Text(composite_11, SWT.BORDER);
		text_1.setBounds(534, 2, 148, 23);

		label_13 = new Label(composite_11, SWT.NONE);
		label_13.setBounds(0, 5, 51, 17);
		label_13.setText("订单号");

		combo_1 = new Combo(composite_11, SWT.NONE);
		combo_1.setBounds(57, 2, 74, 25);
		combo_1.setText("新建订单");
		combo_1.add("新建订单");

		label_14 = new Label(composite_11, SWT.NONE);
		label_14.setBounds(688, 5, 34, 17);
		label_14.setText("单位:");

		text_6 = new Text(composite_11, SWT.BORDER);
		text_6.setBounds(722, 2, 28, 23);
		text_6.setEditable(false);

		Label label_15 = new Label(composite_11, SWT.NONE);
		label_15.setBounds(761, 5, 28, 17);
		label_15.setText("数量:");

		spinner = new Spinner(composite_11, SWT.BORDER);
		spinner.setMinimum(1);
		spinner.setBounds(795, 2, 47, 23);

		Button btnNewButton_6 = new Button(composite_11, SWT.NONE);
		btnNewButton_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (combo_1.getText().equals("新建订单")) { // 如果是新开单
						if(text_1.getText().trim().equals("")){
							MessageUtil.promt(shell, "温馨提示", "请输入正确的菜名!");
							return;
						}
						if (orderDao.getDeskStatus(combo.getText()) == 1) {
							MessageUtil.promt(shell, "温馨提示", "此桌已经被占用!");
							return;
						}
						table.removeAll();
						Map<String, String> map = orderDao
								.findSingleFoodByName(text_1.getText());
						double amount = Double.parseDouble(map
								.get("UNIT_PRICE").toString())
								* Double.parseDouble(spinner.getText());
						List<Object> params = new ArrayList<Object>();
						params.add(combo.getText());
						orderDao.insertOrderData(params);// 更新订单表
						seatDao.updateSeatStatus(combo.getText()); // 更新座位状态
						int currorder = orderDao.getOrderVal();
						combo_1.add(currorder + ""); // 添加当前订单号
						combo_1.setText(currorder + "");//
						tableItem = new TableItem(table, SWT.NONE);
						tableItem.setText(new String[] { "新建订单", currorder + "",
								map.get("MID").toString(),
								map.get("MNAME").toString(),
								map.get("UNIT").toString(), spinner.getText(),
								map.get("UNIT_PRICE").toString(), amount + "" }); // 显示订单数据
						params = new ArrayList<Object>();
						params.add(currorder);
						params.add(map.get("MID"));
						params.add(spinner.getText());
						params.add(map.get("UNIT_PRICE"));
						orderDao.insertOrderDetail(params);
						table_1.removeAll(); // 刷新座位状态
						List<Map<String, Object>> list = orderDao.getSeatInfo();
						for (Map<String, Object> map2 : list) {
							tableItem = new TableItem(table_1, SWT.NONE);
							tableItem
									.setText(new String[] {
											map2.get("O_ID").toString()
													.equals("0") ? "" : map2
													.get("O_ID").toString(),
											map2.get("DID").toString(),
											map2.get("SEATING").toString()
													.equals("0") ? "空闲" : "使用中",
											map2.get("QUANTITY").toString(),
											map2.get("O_TIME").toString()
													.equals("2000-01-01") ? ""
													: map2.get("O_TIME")
															.toString()
											 });

						}
						// for (Map<String, Object> map1 : list) {
						// tableItem = new TableItem(table_1, SWT.NONE);
						// tableItem.setText(new String[] {
						// "",
						// map1.get("DID").toString(),
						// map1.get("SEATING").toString().equals("0") ? "空闲":
						// "使用中",
						// map1.get("QUANTITY").toString() });
						// }
						// TableItem [] tableItems = table_1.getItems();
						// for (TableItem tableItem : tableItems) {
						// if(tableItem.getText(1).equals(combo.getText())){
						// tableItem.setText(0, currorder+"");
						// tableItem.setText(4,
						// orderDao.getOrderTime(currorder+""));
						// }
						// }
					} else {
						TableItem[] tableItems = table.getItems(); // 更改表格信息
						String oid = null;
						String mid = null;
						int amount = 0;
						int flag = 0;
						for (TableItem tableItem : tableItems) {
							oid = tableItem.getText(1);
							mid = tableItem.getText(2);
							if (tableItem.getText(1).equals(combo_1.getText())
									&& tableItem.getText(3).equals(
											text_1.getText())) {
								amount = Integer.parseInt(tableItem.getText(5));
								int add = Integer.parseInt(spinner.getText());
								amount += add;
								tableItem.setText(5, amount + "");
								tableItem.setText(
										7,
										amount
												* Double.parseDouble(tableItem
														.getText(6)) + "");
								flag = 1;
								break;
							}
						}
						// 更改订单详细
						if (flag == 1) {
							List<Object> params = new ArrayList<Object>();
							params.add(amount + "");
							params.add(oid);
							params.add(mid);
							orderDao.updateOrderDetailAmount(params);
						}
						if (flag == 0) {
							Map<String, String> map = orderDao
									.findSingleFoodByName(text_1.getText());
							double amount1 = Double.parseDouble(map.get(
									"UNIT_PRICE").toString())
									* Double.parseDouble(spinner.getText());
							int currorder1 = Integer.parseInt(combo_1.getText());
							tableItem = new TableItem(table, SWT.NONE);
							tableItem.setText(new String[] { "新建订单",
									currorder1 + "", map.get("MID").toString(),
									map.get("MNAME").toString(),
									map.get("UNIT").toString(),
									spinner.getText(),
									map.get("UNIT_PRICE").toString(),
									amount + "" }); // 显示订单数据
							List<Object> params = new ArrayList<Object>();
							params.add(currorder1);
							params.add(map.get("MID"));
							params.add(spinner.getText());
							params.add(map.get("UNIT_PRICE"));
							orderDao.insertOrderDetail(params);
						}

					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					// MessageUtil.promt(shell, "", "Error!"+e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_6.setBounds(852, 0, 61, 27);
		btnNewButton_6.setText("开单");

		Button button_6 = new Button(composite_11, SWT.NONE);
		button_6.setBounds(919, 0, 61, 27);
		button_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (tableItem != null) {
					if (!tableItem.getText(0).equals("")) {
						List<Object> params = new ArrayList<Object>();
						params.add(tableItem.getText(0));
						int flag = orderDao.updateOrderStatusToOrdered(params);
						//seatDao.updateSeatStatusToFree(combo.getText());
						if (flag == 0) {
							MessageUtil.promt(shell, "", "签单失败");
						}
						List<Map<String, Object>> list = orderDao
								.getOrderInfo(tableItem.getText(0));
						if (list != null && list.size() > 0) {
							table.removeAll();
							for (Map<String, Object> map : list) {
								TableItem tableItem = new TableItem(table,
										SWT.NONE);
								combo_1.setText(map.get("O_ID").toString());
								tableItem.setText(new String[] {
										map.get("O_STATUS").toString()
												.equals("0") ? "新建订单" : "已签单",
										map.get("O_ID").toString(),
										map.get("MID").toString(),
										map.get("MNAME").toString(),
										map.get("UNIT").toString(),
										map.get("OD_AMOUNT").toString(),
										map.get("OD_PRICE").toString(),
										Double.parseDouble(map.get("PRICE")
												.toString()) + "" });
							}
							
						}
						//统计总消费金额 
						TableItem [] tableItems = table.getItems();
						double sum = 0;
						for (TableItem tableItem : tableItems) {
							sum+=Double.parseDouble(tableItem.getText(7));
						}
						text_amount.setText(sum + "") ;
						
						
						
						
						
					}
				}

			}
		});
		button_6.setText("签单");

		Button button_7 = new Button(composite_11, SWT.NONE);
		button_7.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new ObligateMain().open();
			}
		});
		button_7.setBounds(986, 0, 61, 27);
		button_7.setText("预定");

		Composite composite_3 = new Composite(shell, SWT.BORDER);
		composite_3.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		composite_3.setLayout(new GridLayout(3, false));
		GridData gd_composite_3 = new GridData(GridData.FILL_HORIZONTAL
				| GridData.FILL_VERTICAL);
		gd_composite_3.heightHint = 207;
		gd_composite_3.widthHint = 1242;
		composite_3.setLayoutData(gd_composite_3);

		Composite composite_4 = new Composite(composite_3, SWT.BORDER);
		composite_4.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite_4.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		composite_4.setLayout(new GridLayout(1, false));
		GridData gd_composite_4 = new GridData(GridData.FILL_HORIZONTAL
				| GridData.FILL_VERTICAL);
		gd_composite_4.heightHint = 194;
		gd_composite_4.widthHint = 531;
		composite_4.setLayoutData(gd_composite_4);

		date = new Date();
		df = new SimpleDateFormat("yyyy年MM月dd日");
		label_2 = new Label(composite_4, SWT.NONE);
		label_2.setText("今天是:");
		GridData gd_label_2 = new GridData(GridData.FILL_HORIZONTAL
				| GridData.FILL_VERTICAL);
		gd_label_2.heightHint = 26;
		label_2.setLayoutData(gd_label_2);

		composite_7 = new Composite(composite_4, SWT.NONE);
		composite_7.setLayout(new GridLayout(1, false));
		GridData gd_composite_7 = new GridData(GridData.FILL_HORIZONTAL
				| GridData.FILL_VERTICAL);
		gd_composite_7.widthHint = 256;
		composite_7.setLayoutData(gd_composite_7);

		lblNewLabel_1 = new Label(composite_7, SWT.NONE);
		lblNewLabel_1.setAlignment(SWT.CENTER);
		lblNewLabel_1.setText(df.format(date));
		lblNewLabel_1.setLayoutData(new GridData(GridData.FILL_HORIZONTAL
				| GridData.FILL_VERTICAL));

		composite_8 = new Composite(composite_4, SWT.NONE);
		composite_8.setLayout(new GridLayout(1, false));
		GridData gd_composite_8 = new GridData(GridData.FILL_HORIZONTAL
				| GridData.FILL_VERTICAL);
		gd_composite_8.widthHint = 257;
		composite_8.setLayoutData(gd_composite_8);

		df = new SimpleDateFormat("EEE");
		label_3 = new Label(composite_8, SWT.NONE);
		label_3.setText(df.format(date));
		label_3.setAlignment(SWT.CENTER);
		label_3.setLayoutData(new GridData(GridData.FILL_HORIZONTAL
				| GridData.FILL_VERTICAL));

		composite_9 = new Composite(composite_4, SWT.NONE);
		composite_9.setLayout(new GridLayout(1, false));
		GridData gd_composite_9 = new GridData(GridData.FILL_HORIZONTAL
				| GridData.FILL_VERTICAL);
		gd_composite_9.widthHint = 256;
		composite_9.setLayoutData(gd_composite_9);

		label_4 = new Label(composite_9, SWT.NONE);
		label_4.setAlignment(SWT.CENTER);
		label_4.setLayoutData(new GridData(GridData.FILL_HORIZONTAL
				| GridData.FILL_VERTICAL));
		time();
		composite_10 = new Composite(composite_4, SWT.NONE);
		composite_10.setLayout(new GridLayout(1, false));
		GridData gd_composite_10 = new GridData(GridData.FILL_HORIZONTAL
				| GridData.FILL_VERTICAL);
		gd_composite_10.widthHint = 255;
		gd_composite_10.heightHint = 43;
		composite_10.setLayoutData(gd_composite_10);

		lblNewLabel_3 = new Label(composite_10, SWT.NONE);
		lblNewLabel_3.setText("操作员:");

		lblNewLabel_2 = new Label(composite_10, SWT.NONE);
		lblNewLabel_2.setAlignment(SWT.CENTER);
		lblNewLabel_2.setText(LoginUserInfo.username);
		lblNewLabel_2.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		composite_5 = new Composite(composite_3, SWT.BORDER);
		composite_5.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite_5.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		composite_5.setLayout(new BorderLayout(0, 0));
		GridData gd_composite_5 = new GridData(GridData.FILL_HORIZONTAL
				| GridData.FILL_VERTICAL);
		gd_composite_5.heightHint = 194;
		gd_composite_5.widthHint = 868;
		composite_5.setLayoutData(gd_composite_5);

		group = new Group(composite_5, SWT.NONE);
		group.setLayoutData(BorderLayout.EAST);
		group.setText("结账区");

		label_5 = new Label(group, SWT.NONE);
		label_5.setText("消费金额:");
		label_5.setBounds(3, 20, 61, 17);

		label_6 = new Label(group, SWT.NONE);
		label_6.setText("实收金额:");
		label_6.setBounds(3, 51, 61, 17);
		
		lblid = new Label(group, SWT.NONE);
		lblid.setText("会员id:");
		lblid.setBounds(3, 82, 61, 17);
		
		label_7 = new Label(group, SWT.NONE);
		label_7.setText("折后金额:");
		label_7.setBounds(3, 113, 61, 17);

		label_8 = new Label(group, SWT.NONE);
		label_8.setText("找零金额:");
		label_8.setBounds(3, 144, 61, 17);

		text_amount = new Text(group, SWT.BORDER);
		text_amount.setEditable(false);
		text_amount.setBounds(70, 17, 73, 23);

		text_money = new Text(group, SWT.BORDER);
		text_money.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == SWT.CR){
					if(text_money.getText().trim().equals("")||!text_money.getText().matches("[\\d]+")){
						MessageUtil.promt(shell, "温馨提示", "请输入正确的金额!");
						return;
					}
					double amount = Double.parseDouble(text_amount.getText());
					double money  = Double.parseDouble(text_money.getText());
					if(amount>money){
						MessageUtil.promt(shell, "温馨提示", "请输入正确的金额!");
						return;
					}
					text_fre.setText(money-amount +"");
					
				}
			}
		});
		text_money.setBounds(70, 48, 73, 23);
		text_id = new Text(group, SWT.BORDER);
		text_off = new Text(group, SWT.BORDER);
		text_off.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		text_off.setEditable(false);
		text_off.setBounds(70, 110, 73, 23);

		text_fre = new Text(group, SWT.BORDER);
		text_fre.setEditable(false);
		text_fre.setBounds(70, 141, 73, 23);

		label_9 = new Label(group, SWT.NONE);
		label_9.setText("元");
		label_9.setBounds(149, 20, 19, 17);

		label_10 = new Label(group, SWT.NONE);
		label_10.setText("元");
		label_10.setBounds(149, 51, 19, 17);

		label_11 = new Label(group, SWT.NONE);
		label_11.setText("元");
		label_11.setBounds(149, 113, 19, 17);

		label_12 = new Label(group, SWT.NONE);
		label_12.setText("元");
		label_12.setBounds(149, 144, 19, 17);

		button_5 = new Button(group, SWT.NONE);
		button_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(text_money.getText().trim().equals("")){
					MessageUtil.promt(shell, "温馨提示", "请输入金额!");
					return;
				}
				if(!text_id.getText().trim().equals("")){//会员结账
					if(orderDao.isVipExists(text_id.getText())){
						List<Object> params = new ArrayList<Object>();
						params.add(combo_1.getText());
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						params.add(df.format(new Date()));
						params.add(text_off.getText());
						params.add(text_money.getText());
						params.add(orderDao.findUserId(LoginUserInfo.username));
						params.add(text_id.getText());
						int flag = orderDao.insertToSettlementVip(params);
						if(flag>0){
							MessageUtil.promt(shell, "温馨提示", "结账成功");
							params = new ArrayList<Object>();
							params.add(combo_1.getText());
							orderDao.updateOrderStatusToPaid(params);
							seatDao.updateSeatStatusToFree(combo.getText());
							combo_1.remove(combo_1.getText());
							combo_1.select(0);
							table.removeAll();
							text_amount.setText("");
							text_money.setText("");
							text_id.setText("");
							text_off.setText("");
							text_fre.setText("");
							text.setText("");
							text_1.setText("");
							params = new ArrayList<Object>();
							table_1.removeAll();
							List<Map<String, Object>> list = orderDao.getSeatInfo();
							for (Map<String, Object> map2 : list) {
								tableItem = new TableItem(table_1, SWT.NONE);
								tableItem
										.setText(new String[] {
												map2.get("O_ID").toString()
														.equals("0") ? "" : map2
														.get("O_ID").toString(),
												map2.get("DID").toString(),
												map2.get("SEATING").toString()
														.equals("0") ? "空闲" : "使用中",
												map2.get("QUANTITY").toString(),
												map2.get("O_TIME").toString()
														.equals("2000-01-01") ? ""
														: map2.get("O_TIME")
																.toString() });

							}
							return;
						}
					}else {
						MessageUtil.promt(shell, "温馨提示", "该会员不存在,请重新输入!");
						return;
					}
				}
				//非会员结账
				List<Object> params = new ArrayList<Object>();
				params.add(combo_1.getText());
				orderDao.updateOrderStatusToPaid(params);
				seatDao.updateSeatStatusToFree(combo.getText());
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				params.add(df.format(new Date()));
				params.add(text_amount.getText());
				params.add(text_money.getText());
				params.add(orderDao.findUserId(LoginUserInfo.username));
				int flag = orderDao.insertToSettlement(params);
				if(flag>0){
					MessageUtil.promt(shell, "温馨提示", "结账成功");
					combo_1.remove(combo_1.getText());
					combo_1.select(0);
					table.removeAll();
					text_amount.setText("");
					text_money.setText("");
					text_id.setText("");
					text_off.setText("");
					text_fre.setText("");
					text.setText("");
					text_1.setText("");
					params = new ArrayList<Object>();
					table_1.removeAll();
					List<Map<String, Object>> list = orderDao.getSeatInfo();
					for (Map<String, Object> map2 : list) {
						tableItem = new TableItem(table_1, SWT.NONE);
						tableItem
								.setText(new String[] {
										map2.get("O_ID").toString()
												.equals("0") ? "" : map2
												.get("O_ID").toString(),
										map2.get("DID").toString(),
										map2.get("SEATING").toString()
												.equals("0") ? "空闲" : "使用中",
										map2.get("QUANTITY").toString(),
										map2.get("O_TIME").toString()
												.equals("2000-01-01") ? ""
												: map2.get("O_TIME")
														.toString() });

					}
				}
			}
		});
		button_5.setText("结账");
		button_5.setBounds(174, 111, 46, 27);
		
		
		
		
		text_id.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == SWT.CR){
					if(text_id.getText().trim().equals("")||!text_id.getText().matches("[\\d]+")){
						MessageUtil.promt(shell, "温馨提示", "请输入正确的id信息!");
						return;
					}
					if(!orderDao.isVipExists(text_id.getText().trim())){
						MessageUtil.promt(shell, "温馨提示", "该会员不存在,请重新输入!");
						return;
					}
					if(text_money.getText().trim().equals("")||!text_money.getText().matches("[\\d]+")){
						MessageUtil.promt(shell, "温馨提示", "请输入正确的金额!");
						return;
					}
					double discount = orderDao.getDiscount(text_id.getText().trim());
					if(discount>0){
						double amount = Double.parseDouble(text_amount.getText());
//						text_amount.setText("");
						text_off.setText(amount*discount+"");
						
						double amount1 = Double.parseDouble(text_off.getText());
						double money  = Double.parseDouble(text_money.getText());
						if(amount>money){
							MessageUtil.promt(shell, "温馨提示", "请输入正确的金额!");
							return;
						}
						DecimalFormat df = new DecimalFormat("#.##");
						text_fre.setText(df.format(money-amount1));
					}
				}				
			}
		});
		text_id.setBounds(70, 79, 73, 23);

		composite_6 = new Composite(composite_3, SWT.BORDER);
		composite_6.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite_6.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		GridData gd_composite_6 = new GridData(GridData.FILL_HORIZONTAL
				| GridData.FILL_VERTICAL);
		gd_composite_6.heightHint = 194;
		gd_composite_6.widthHint = 615;
		composite_6.setLayoutData(gd_composite_6);

		btnNewButton = new Button(composite_6, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Mainpwd mainpwd = new Mainpwd();
				mainpwd.open();
			}
		});
		btnNewButton.setBounds(254, 24, 80, 27);
		btnNewButton.setText("修改密码");

		btnNewButton_1 = new Button(composite_6, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (LoginUserInfo.type == 1) {
					MessageUtil.promt(shell, "温馨提示", "您不是管理员!无法进行用户管理");
					return;
				}
				new AdminMain().open();
			}
		});
		btnNewButton_1.setBounds(254, 74, 80, 27);
		btnNewButton_1.setText("用户管理");

		btnNewButton_2 = new Button(composite_6, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean flag = MessageDialog.openConfirm(shell, "确认",
						"是否要退出系统?");
				if (flag) {
					System.exit(0);
				}
			}
		});
		btnNewButton_2.setBounds(254, 127, 80, 27);
		btnNewButton_2.setText("退出系统");

		btnNewButton_3 = new Button(composite_6, SWT.NONE);
		btnNewButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new BillingQueryUI().open();
			}
		});
		btnNewButton_3.setText("账单查询");
		btnNewButton_3.setBounds(142, 24, 80, 27);

		btnNewButton_4 = new Button(composite_6, SWT.NONE);
		btnNewButton_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new MailLogin().open();
			}
		});
		btnNewButton_4.setBounds(142, 74, 80, 27);
		btnNewButton_4.setText("邮件发送");

		btnNewButton_5 = new Button(composite_6, SWT.NONE);
		btnNewButton_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new VipUI().open();
			}
		});
		btnNewButton_5.setBounds(142, 127, 80, 27);
		btnNewButton_5.setText("会员管理");

		button_2 = new Button(composite_6, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new Menu().open();
			}
		});
		button_2.setText("菜品管理");
		button_2.setBounds(29, 24, 80, 27);

		button_3 = new Button(composite_6, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new ViewObligate().open();
			}
		});
		button_3.setText("预订信息");
		button_3.setBounds(29, 74, 80, 27);

		button_4 = new Button(composite_6, SWT.NONE);
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					new SeatManageUI().open();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				List<Map<String, Object>> list = orderDao.getSeatInfo();
				combo.removeAll();
				table_1.removeAll();
				for (Map<String, Object> map2 : list) {
					tableItem = new TableItem(table_1, SWT.NONE);
					tableItem
							.setText(new String[] {
									map2.get("O_ID").toString()
											.equals("0") ? "" : map2
											.get("O_ID").toString(),
									map2.get("DID").toString(),
									map2.get("SEATING").toString()
											.equals("0") ? "空闲" : "使用中",
									map2.get("QUANTITY").toString(),
									map2.get("O_TIME").toString()
											.equals("2000-01-01") ? ""
											: map2.get("O_TIME")
													.toString(),
									 });
					combo.add(map2.get("DID").toString());
				}
				combo.select(0);
			}
		});
		button_4.setText("台号管理");
		button_4.setBounds(29, 127, 80, 27);
		List<Map<String, Object>> list = orderDao.getSeatInfo();
		for (Map<String, Object> map2 : list) {
			tableItem = new TableItem(table_1, SWT.NONE);
			tableItem
					.setText(new String[] {
							map2.get("O_ID").toString()
									.equals("0") ? "" : map2
									.get("O_ID").toString(),
							map2.get("DID").toString(),
							map2.get("SEATING").toString()
									.equals("0") ? "空闲" : "使用中",
							map2.get("QUANTITY").toString(),
							map2.get("O_TIME").toString()
									.equals("2000-01-01") ? ""
									: map2.get("O_TIME")
											.toString(),
							 });
			combo.add(map2.get("DID").toString());
		}
		combo.select(0);
		List<Map<String, Object>> list2 = orderDao.findOrder();
		for (Map<String, Object> map : list2) {
			combo_1.add(map.get("O_ID").toString());
		}
	}

	private void setTableColumns() {
		tbl_status.setWidth(table.getClientArea().width / 8);
		tbl_no.setWidth(table.getClientArea().width / 8);
		tbl_unit.setWidth(table.getClientArea().width / 8);
		tbl_Count.setWidth(table.getClientArea().width / 8);
		tbl_per.setWidth(table.getClientArea().width / 8);
		tbl_foodname.setWidth(table.getClientArea().width / 8);
		tbl_foodno.setWidth(table.getClientArea().width / 8);
		tbl_price.setWidth(table.getClientArea().width - tbl_status.getWidth()
				- tbl_no.getWidth() - tbl_unit.getWidth()
				- tbl_Count.getWidth() - tbl_per.getWidth()
				- tbl_foodname.getWidth() - tbl_foodno.getWidth());
		tbl_no1.setWidth(table_1.getClientArea().width / 5);
		tbl_deskno.setWidth(table_1.getClientArea().width / 5);
		tbl_deskstatus.setWidth(table_1.getClientArea().width / 5);
		tbl_stime.setWidth(table_1.getClientArea().width / 5);
		tbl_pcount.setWidth(table_1.getClientArea().width
				- tbl_no1.getWidth() - tbl_deskno.getWidth()
				- tbl_stime.getWidth() - tbl_deskstatus.getWidth());
//		tbl_prestatus.setWidth(table_1.getClientArea().width
//				- tbl_no1.getWidth() - tbl_deskno.getWidth()
//				- tbl_stime.getWidth() - tbl_deskstatus.getWidth()
//				- tbl_pcount.getWidth());
	}

	private void time() {
		t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				df = new SimpleDateFormat("HH:mm:ss");
				while (true) {
					date = new Date();
					Display.getDefault().syncExec(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							label_4.setText(df.format(date));
						}
					});
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});
		t1.start();
	}
}

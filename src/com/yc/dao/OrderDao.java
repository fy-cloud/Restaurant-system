package com.yc.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;



import com.yc.commons.DBHelper;

public class OrderDao {
	DBHelper db = new DBHelper();
	
	/**
	 * 按id查找菜品
	 * @param code
	 * @return
	 */
	public Map<String, String> findSingleFoodById(String code) {
		Map<String, String> map = null;
		List<Object> params = new ArrayList<Object>();
		String sql = "select * from tb_menu where mid = ?";
		params.add(code);
		try {
			map = db.findSingleObject(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;

	}
	/**
	 * 按助记码查找菜品
	 * @param code
	 * @return
	 */
	public Map<String, String> findSingleFoodByCode(String code) {
		Map<String, String> map = null;
		List<Object> params = new ArrayList<Object>();
		String sql = "select * from tb_menu where code = ?";
		params.add(code);
		try {
			map = db.findSingleObject(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;

	}
	/**
	 * 按菜名查找菜品
	 * @param name
	 * @return
	 */
	public Map<String, String> findSingleFoodByName(String name) {
		Map<String, String> map = null;
		List<Object> params = new ArrayList<Object>();
		String sql = "select * from tb_menu where mname = ?";
		params.add(name);
		try {
			map = db.findSingleObject(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;

	}
	/**
	 * 向订单表中插入数据
	 * @param params
	 * @return
	 */
	public int insertOrderData(List<Object> params) {

		String sql = "insert into tb_order values (seq_oid.nextval,0,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'))";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		params.add(df.format(new Date()));
		return db.doUpdate(sql, params);

	}
	/**
	 * 向订单详情表插入数据
	 * @param params
	 * @return
	 */
	public int insertOrderDetail(List<Object> params) {
		String sql = "insert into orderdetail values(seq_odid.nextval,?,?,?,?)";
		return db.doUpdate(sql, params);
	}
	/**
	 * 修改菜品数量
	 * @param params
	 * @return
	 */
	public int updateOrderDetailAmount(List<Object> params) {
		String sql = "update orderdetail set od_amount = ? where o_id = ? and mid = ?";
		return db.doUpdate(sql, params);
	}
	/**
	 * 把订单状态修改为已签单
	 * @param params
	 * @return
	 */
	public int updateOrderStatusToOrdered(List<Object> params) {
		String sql = "update tb_order set o_status = 1 where o_id = ?";
		return db.doUpdate(sql, params);
	}
	
	public int updateOrderStatusToPaid(List<Object> params) {
		String sql = "update tb_order set o_status = 2 where o_id = ?";
		return db.doUpdate(sql, params);
	}

	public int deleteOrderDetail(String mid){
		String sql = "delete orderdetail where mid = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(mid);
		return db.doUpdate(sql, params);
	}
	
	public int getOrderVal() {
		String sql = "select max(o_id) a from tb_order";
		try {
			Map<String, String> map = db.findSingleObject(sql, null);
			return Integer.parseInt(map.get("A").toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public int getDeskStatus(String did) {
		String sql = "select seating from tb_desk where did = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(did);
		try {
			return Integer.parseInt(db.findSingleObject(sql, params)
					.get("SEATING").toString());
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public String getOrderTime(String oid) {
		String sql = "select o_time from tb_order where o_status = 0 and o_id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(oid);
		try {
			return db.findSingleObject(sql, params).get("O_TIME").toString()
					.substring(0, 10);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Map<String, Object>> getSeatInfo() {
		String sql = "select nvl(o.o_id,0) o_id ,t.did,seating,quantity,nvl(to_char(o_time,'hh24:mi:ss'),'2000-01-01') o_time  from "
				+ "(select * from tb_order where o_status = 0 or o_status = 1) o "
				+ "right join  tb_desk t on t.did=o.did "
				+ "order by did";
		try {
			return db.findMultiObject(sql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Map<String, Object>> getOrderInfo(String orderID){
		String sql = "select o_status ,t.o_id,mid,mname,unit,od_amount,od_price, od_amount*od_price price  from  "
				+ "tb_order t inner join orderdetail o on t.o_id = o.o_id "
				+ "inner join tb_menu m on o.mid = m.mid where t.o_id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(orderID);
		try {
			return db.findMultiObject(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public int insertToSettlement(List<Object> params){
		String sql = "insert into settlement values(seq_smid.nextval,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?,0)";
		return db.doUpdate(sql, params);
		
	}
	
	public int insertToSettlementVip(List<Object> params){
		String sql = "insert into settlement values(seq_smid.nextval,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?,?)";
		return db.doUpdate(sql, params);
	}
	
	public boolean isVipExists(String id){
		String sql = "select * from vip where vip_id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		Map<String, String> map = null;
		try {
			map = db.findSingleObject(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(map!=null&&map.size()>0)
			return true;
		else
			return false;
	}
	public String findUserId(String name){
		String sql = "select userid from tb_user where name = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(name);
		try {
			Map<String, String> map = db.findSingleObject(sql, params);
			return map.get("USERID");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
//	public int deleteDeskIdFromOrder(String orderid){
//		String  sql = "update tb_order set did = null where o_id = ?";
//		List<Object> params = new ArrayList<Object>();
//		params.add(orderid);
//		return db.doUpdate(sql, params);
//	}
	
	public List<Map<String, Object>> findOrder(){
		String sql = "select o_id from tb_order where o_status = 0 or o_status = 1";
		try {
			return db.findMultiObject(sql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public double getDiscount(String vipid){
		String sql = "select vip_discount from vip where vip_id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(vipid);
		try {
			Map<String,String> map = db.findSingleObject(sql, params);
			if(map!= null && map.size() >0)
				return Double.parseDouble(db.findSingleObject(sql, params).get("VIP_DISCOUNT").toString());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public int getOrderStatus(String  orderid){
		String sql = "select o_status from tb_order where o_id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(orderid);
		try {
			return Integer.parseInt(db.findSingleObject(sql, params).get("O_STATUS").toString());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
}

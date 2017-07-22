package com.yc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.commons.DBHelper;

public class BillingQuerydao {
	DBHelper db = new DBHelper();
	
	public List<Map<String, Object>> findOrderListByDay(String date){
		String sql = "select o_id,d.did,sm_money from tb_order t "
				+ "inner join tb_desk d on t.did=d.did "
				+ "inner join settlement s on s.o_id = t.o_id "
				+ "where to_char(s.sm_datetime,'yyyy-mm-dd') = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(date);
		try {
			return db.findMultiObject(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<Map<String, Object>> findOrderDetails(String orderid){
		String sql = "select o_id,nvl(vip_discount,0) vip_discount ,mid,mname,unit,od_amount,od_price,od_amount*od_price amount "
						+"from tb_order t inner join orderdetail r "
						+"on t.o_id=r.o_id inner join settlement s "
						+"on s.o_id = t.o_id inner join vip v "
						+"on v.vip_id = s.vip_id inner join tb_menu m "
						+"on m.mid =r.mid "
						+"where o_id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(orderid);
		try {
			return db.findMultiObject(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public double findDailyAmount(String date){
		String sql = "select nvl(sum(s.sm_money),0) amount from settlement s "
				+ "where to_char(s.sm_datetime,'yyyy-mm-dd') = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(date);
		try {
			return Double.parseDouble(db.findSingleObject(sql, params).get("AMOUNT").toString());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public List<Map<String, Object>> findOrderAmountByMonth(String year,String month){
		String sql = "select a.year,a.month,support.day,nvl(a.amount,0)amount from "
				+ "(select extract(year from s.sm_datetime)year,extract(month from s.sm_datetime)as month,extract(day from s.sm_datetime)day,nvl(sum(od_amount*od_price),0) amount "
				+ "from orderdetail o "
				+"inner join settlement s on o.o_id = s.o_id "
				+"group by extract(year from s.sm_datetime),extract(month from s.sm_datetime),extract(day from s.sm_datetime) "
				+"having extract(year from s.sm_datetime) = ? and extract(month from s.sm_datetime) = ? "
				+"order by day) a right join support on a.day = support.day "
				+"order by day ";
		List<Object> params = new ArrayList<Object>();
		params.add(year);
		params.add(month);
		try {
			return db.findMultiObject(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public double findMonthlyAmount(String year,String month){
		String sql = "select extract(year from s.sm_datetime)year,extract(month from s.sm_datetime)as month,nvl(sum(od_amount*od_price),0) amount from orderdetail o "
				+"inner join settlement s on o.o_id = s.o_id "
				+"group by extract(year from s.sm_datetime),extract(month from s.sm_datetime) "
				+"having extract(year from s.sm_datetime) = ? and extract(month from s.sm_datetime) = ? "
				+"order by month";
		List<Object> params = new ArrayList<Object>();
		params.add(year);
		params.add(month);
		try {
			Map<String, String> map = db.findSingleObject(sql, params);
			if(map!=null&&map.size()>0)
				return Double.parseDouble(map.get("AMOUNT").toString());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public List<Map<String, Object>> findOrderAmountByYear(String year){
		String sql = "select a.year,support2.month,nvl(a.amount,0)amount from (select extract(year from s.sm_datetime)year,extract(month from s.sm_datetime)as month,nvl(sum(od_amount*od_price),0) amount "
				+"from orderdetail o "
				+"inner join settlement s on o.o_id = s.o_id "
				+"group by extract(year from s.sm_datetime),extract(month from s.sm_datetime) "
				+"having extract(year from s.sm_datetime) = ? ) a "
				+"right join support2 on a.month = support2.month "
				+"order by month";
		List<Object> params = new ArrayList<Object>();
		params.add(year);
		try {
			return db.findMultiObject(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public double findYearAmount(String year){
		String sql = "select extract(year from s.sm_datetime)year,nvl(sum(od_amount*od_price),0) amount from orderdetail o "
				+"inner join settlement s on o.o_id = s.o_id "
				+"group by extract(year from s.sm_datetime) "
				+"having extract(year from s.sm_datetime) = ? "
				+"order by year";
		List<Object> params = new ArrayList<Object>();
		params.add(year);
		try {
			Map<String, String> map = db.findSingleObject(sql, params);
			if(map!=null&&map.size()>0)
				return Double.parseDouble(map.get("AMOUNT").toString());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public List<Map<String, Object>> toExcelData(String date){
		String sql = "select o_id,nvl(vip_discount,0) discount,mid,mname,unit,od_amount,od_price,od_amount*od_price amount from "
				+ "tb_order t inner join orderdetail r on t.o_id=r.o_id inner join settlement s on s.o_id = t.o_id "
				+ "inner join vip v on v.vip_id = s.vip_id inner join tb_menu m on m.mid =r.mid "
				+ "where to_char(s.sm_datetime,'yyyy-mm-dd') = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(date);
		try {
			return db.findMultiObject(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

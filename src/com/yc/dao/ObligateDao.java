package com.yc.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.yc.commons.DBHelper;

public class ObligateDao {
	
	DBHelper db = new DBHelper();
	//显示所有会员
	public List<Map<String, Object>> findAllVip() throws SQLException,IOException{
		String sql = "select vip_id,vip_name from vip where vip_id != 0";
		return db.findMultiObject(sql, null);
	}
	
	//table显示所有座位
	public List<Map<String, Object>> findAllDesk() throws SQLException,IOException{
		String sql = "select did,quantity from tb_desk";
		return db.findMultiObject(sql, null);
	}
	
	//显示可选座位
	public List<Map<String, Object>> findAllkxDesk() throws SQLException,IOException{
		String sql = "select did from tb_desk";
		return db.findMultiObject(sql, null);
	}
	
	//显示所有菜品
	public List<Map<String, Object>> findAllMenu() throws SQLException,IOException{
		String sql = "select mname from tb_menu where status=1";
		return db.findMultiObject(sql, null);
	}
	
	//显示选择菜品
	public Map<String, String> findSelectMenu(List<Object> params)throws SQLException,IOException{
		String sql="select mname,unit,unit_price from tb_menu where mname=?";
		return db.findSingleObject(sql, params);
		 	
	}
	
	//插入预订信息
	public boolean registerObligate(List<Object>params)throws SQLException,IOException{
		String sql="insert into obligate values(seq_oid.nextval,to_date(?,'yyyy-MM-dd-hh24:mi:ss'),to_date(?,'yyyy-MM-dd-hh24:mi:ss'),?,1,?,?,?,?,?)";
		
		int i=db.doUpdate(sql, params);
		if(i>0){
			return true;
		}else{
			return false;
		}	 	
	}
	

}

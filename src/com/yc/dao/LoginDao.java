package com.yc.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.commons.DBHelper;

public class LoginDao {
	DBHelper db = new DBHelper();
	
	public boolean login(String user , String password) throws SQLException{
		List<Object> params = new ArrayList<Object>();
		params.add(user);
		params.add(password);
		String sql = "select * from tb_user where name = ? and password = ? and freeze = 0";
		Map<String, String> map = db.findSingleObject(sql, params);
		if(map!=null&&map.size()>0)
			return true;
		return false;
	}
	
	public List<Map<String,Object>> findAllusers (int i) throws SQLException{
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		String sql = "select name from tb_user and freeze = 0";
		String sql1 = "select name from tb_user where type = 1 and freeze = 0";
		String sql2 = "select name from tb_user where type = 0 and freeze = 0";
		if(i == 0)
			list = db.findMultiObject(sql2, null);
		else if(i == 1)
			list = db.findMultiObject(sql1, null);
		else 
			list = db.findMultiObject(sql, null);
		return list;
	}
}

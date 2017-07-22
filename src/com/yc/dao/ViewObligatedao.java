package com.yc.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.yc.commons.DBHelper;

public class ViewObligatedao {
	DBHelper db = new DBHelper();
	
	public List<Map<String, Object>> finddata(){
		String sql = "select oid,nvl(people,0) people,did,state from obligate";
		try {
			return db.findMultiObject(sql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

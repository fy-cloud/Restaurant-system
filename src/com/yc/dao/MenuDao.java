package com.yc.dao;

import java.io.IOException;
import java.security.Policy.Parameters;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.commons.DBHelper;


public class MenuDao {
	
	DBHelper db = new DBHelper();
	//combo显示所有菜系
		public List<Map<String, Object>> findAllSort() throws SQLException,IOException{
			String sql = "select sid,sname from tb_sort";
			return db.findMultiObject(sql, null);
		}
		
		//显示所有菜品
		public List<Map<String, Object>> findAllMenu() throws SQLException,IOException{
			String sql = "select mid,mname,code,sname,unit,unit_price from tb_menu,tb_sort where tb_menu.sid=tb_sort.sid and status=1";
			return db.findMultiObject(sql, null);
		}

		//插入菜品
		public boolean registerMenu(List<Object>params)throws SQLException,IOException{
			String sql="insert into tb_menu values(seq_mid.nextval,(select sid from tb_sort where sname=?),?,?,?,?,1)";
			int i=db.doUpdate(sql, params);
			if(i>0){
				return true;
			}else{
				return false;
			}
			 	
		}
		
		//删除菜品(status=0)
		public boolean deleteMenu(List<Object>params)throws SQLException,IOException{
			String sql="update tb_menu set status=? where mid = ?";
			int i=db.doUpdate(sql, params);
			if(i>0){
				return true;
			}else{
				return false;
			}
			 	
		}
		public boolean isMenuExists(String name){
			String sql = "select * from tb_menu where mname = ?";
			List<Object> params = new ArrayList<Object>();
			params.add(name);
			Map<String, String> map = null;
			try {
				map = db.findSingleObject(sql, params);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(map!=null&&map.size()>0){
				return true;
			}else {
				return false;
			}
		}
		
		public boolean isCExists(String code){
			String sql = "select * from tb_menu where code = ?";
			List<Object> params = new ArrayList<Object>();
			params.add(code);
			Map<String, String> map = null;
			try {
				map = db.findSingleObject(sql, params);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(map!=null&&map.size()>0){
				return true;
			}else {
				return false;
			}
		}
}

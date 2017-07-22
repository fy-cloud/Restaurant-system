package com.yc.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.commons.DBHelper;


public class AdminDao {
	
	DBHelper db = new DBHelper();
	//显示所有未删除用户信息
	public List<Map<String, Object>> findAllAdmin() throws SQLException,IOException{
		String sql = "select userid,name,sex,to_char(birthday,'yyyy-MM-dd') birthday,id_card from tb_user where type=1 and freeze=0";
		return db.findMultiObject(sql, null);
	}
	
	//显示所有管理员信息
		public List<Map<String, Object>> findAllManager() throws SQLException,IOException{
			String sql = "select userid,name,sex,to_char(birthday,'yyyy-MM-dd') birthday,id_card from tb_user where type=0";
			return db.findMultiObject(sql, null);
		}
		
	
	//显示已删除用户信息
		public List<Map<String, Object>> findDeleteAdmin() throws SQLException,IOException{
			String sql = "select userid,name,sex,to_char(birthday,'yyyy-MM-dd') birthday,id_card from tb_user where type=1 and freeze=1";
			return db.findMultiObject(sql, null);
		}
	
	//判断闰年
	public boolean isRun(int a){
			int year=a;
			if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0){
			   return true;
			}else{
			   return false;
			}
	}
	
	//添加用户
	public boolean registerAdmin(List<Object>params)throws SQLException,IOException{
		String sql="insert into tb_user values(seq_userid.nextval,?,?,to_date(?,'yyyy-MM-dd'),?,?,1,0)";
		int i=db.doUpdate(sql, params);
		if(i>0){
			return true;
		}else{
			return false;
		}	 	
	}
	
	
	//添加管理员
		public boolean registerManager(List<Object>params)throws SQLException,IOException{
			String sql="insert into tb_user values(seq_userid.nextval,?,?,to_date(?,'yyyy-MM-dd'),?,?,0,0)";
			int i=db.doUpdate(sql, params);
			if(i>0){
				return true;
			}else{
				return false;
			}		 	
		}
	
	//删除用户(冻结用户)
	public boolean deleteAdmin(List<Object>params)throws SQLException,IOException{
		String sql="update tb_user set freeze= ? where userid = ?";
		int i=db.doUpdate(sql, params);
		if(i>0){
			return true;
		}else{
			return false;
		}
		 	
	}
	
	public boolean isIDCardExist(String id){
		String sql = "select * from tb_user where id_card = ?";
		Map<String, String> map = null;
		List<Object> params = new ArrayList<Object>();
		params.add(id);
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
}



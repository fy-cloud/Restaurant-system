package com.yc.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.commons.DBHelper;

public class SeatDao1 {
		DBHelper db = new DBHelper();
		
		/**
		 * 查看座位所有
		 * @return
		 * @throws SQLException 
		 * @throws IOException 
		 */
		public List<Map<String,Object>>  findAllSeat() throws IOException, SQLException{
			String sql = "select  did,seating,quantity from  tb_desk";
			return db.findMultiObject(sql, null);
		}
		
		
		/**
		 * 查看菜系所有
		 * @return
		 * @throws SQLException 
		 * @throws IOException 
		 */
		public List<Map<String,Object>>  findAllCuisine() throws IOException, SQLException{
			String sql = "select  sid,sname from  tb_sort";
			return db.findMultiObject(sql, null);
		}
		
		/**
		 * 根据台号查询
		 * @return
		 * @throws SQLException 
		 * @throws IOException 
		 */
		public  List<Map<String,Object>> findSeatByID(int did) throws IOException, SQLException{
			String sql = "select did,seating,quantity from  tb_desk where did = ?";
			List<Object> params = new ArrayList<Object>();
			params.add(did);
			return db.findMultiObject(sql, params);
		}
		
		/**
		 * 根据菜名添加
		 * @return
		 * @throws SQLException 
		 * @throws IOException 
		 */
		
		public int addByName(String name) throws IOException, SQLException{
			String sql = "insert into tb_sort values(seq_sid.nextval,?,'','') ";
			List<Object> params = new ArrayList<Object>();
			params.add(name);
			return db.doUpdate(sql, params);
		}
		
		/**
		 * 根据座位数添加
		 * @return
		 * @throws SQLException 
		 * @throws IOException 
		 */
		
		public int addByQuantity(int quantity) throws IOException, SQLException{
			String sql = "insert into tb_desk values(seq_did.nextval,0,?,'','') ";
			List<Object> params = new ArrayList<Object>();
			params.add(quantity);
			return db.doUpdate(sql, params);
		}
		
		/**
		 *根据座位数 修改
		 * @return
		 * @throws SQLException 
		 * @throws IOException 
		 */
		
		public int updateByQuantity(int quantity,int did) throws IOException, SQLException{
			String sql = "update tb_desk set quantity = ? where  did =? ";
			List<Object> params = new ArrayList<Object>();
			params.add(quantity);
			params.add(did);
			return db.doUpdate(sql, params);
		}
		
		/**
		 * 根据菜名修改菜系
		 * @return
		 * @throws SQLException 
		 * @throws IOException 
		 */
		
		public int updateByName(String name,int sid) throws IOException, SQLException{
			String sql = "update tb_sort set sname = ? where sid =? ";
			List<Object> params = new ArrayList<Object>();
			params.add(name);
			params.add(sid);
			return db.doUpdate(sql, params);
		}
		
		/**
		 * 会员信息注册
		 * @return
		 * @throws SQLException 
		 * @throws IOException 
		 */
		public boolean registerVip(List<Object> params) throws IOException, SQLException{
			//插入基本信息
			String sql = "insert into vip( vip_id,vip_name,vip_mailbox,vip_tel,vip_discount)  values(seq_vip_id.nextval,?,?,?,?)";
			int i = db.doUpdate(sql, params);
			if(i>0){
				return true;
			}else{
				return false;
			}
		}
		
		/**
		 * 查询会员信息
		 * @return
		 * @throws SQLException 
		 * @throws IOException 
		 */
		
		public List<Map<String,Object>>  findAllVip() throws IOException, SQLException{
		String sql = "select   vip_id, vip_name,vip_mailbox, vip_tel,vip_discount from  vip";
			return db.findMultiObject(sql, null);
		} 
		
		/**
		 * 根据姓名查询
		 * @return
		 * @throws SQLException 
		 * @throws IOException 
		 */
		public  List<Map<String,Object>> findVipByName(int id) throws IOException, SQLException{
			String sql = "select vip_id, vip_name,vip_mailbox, vip_tel,vip_discount from vip where  vip_id = ?";
			List<Object> params = new ArrayList<Object>();
			params.add(id);
			return db.findMultiObject(sql, params);
		}
		
	
		
}

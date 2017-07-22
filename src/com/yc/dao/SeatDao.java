package com.yc.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.commons.DBHelper;
import com.yc.commons.LoginUserInfo;

public class SeatDao {
	DBHelper db = new DBHelper();

	/**
	 * 查看所有
	 * 
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public List<Map<String, Object>> findAllSeat() throws IOException,
			SQLException {
		String sql = "select  did,seating,quantity from  tb_desk";
		return db.findMultiObject(sql, null);
	}

	/**
	 * 根据台号查询
	 * 
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public List<Map<String, Object>> findSeatByID(int did) throws IOException,
			SQLException {
		String sql = "select did,seating,quantity from  tb_desk where did = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(did);
		return db.findMultiObject(sql, params);
	}

	/**
	 * 添加
	 * 
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */

	public int addByQuantity(int quantity) throws IOException, SQLException {
		String sql = "insert into tb_desk values(seq_did.nextval,0,?) ";
		List<Object> params = new ArrayList<Object>();
		params.add(quantity);
		return db.doUpdate(sql, params);
	}

	/**
	 * 修改
	 * 
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */

	public int updateByQuantity(int quantity, int did) throws IOException,
			SQLException {
		String sql = "update tb_desk set quantity = ? where  did =? ";
		List<Object> params = new ArrayList<Object>();
		params.add(quantity);
		params.add(did);
		return db.doUpdate(sql, params);
	}
	/**
	 * 修改座位状态为使用中
	 * @param seatID
	 * @return
	 */
	public int updateSeatStatus(String seatID){
		String sql = "update tb_desk set seating = 1 where did = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(seatID);
		return db.doUpdate(sql, params);
	}
	
	/**
	 * 修改座位状态为空闲
	 * @param seatID
	 * @return
	 */
	public int updateSeatStatusToFree(String seatID){
		String sql = "update tb_desk set seating = 0 where did = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(seatID);
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
	String sql = "select   vip_id, vip_name,vip_mailbox, vip_tel,vip_discount from  vip where vip_id != 0";
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
	 * 根据菜名添加
	 * @return
	 * @throws SQLException 
	 * @throws IOException 
	 */
	
	public int addByName(String name) throws IOException, SQLException{
		String sql = "insert into tb_sort values(seq_sid.nextval,?) ";
		List<Object> params = new ArrayList<Object>();
		params.add(name);
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
	
	public boolean isCuisineExists(String name){
		String sql = "select * from tb_sort where sname = ?";
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
	
}
